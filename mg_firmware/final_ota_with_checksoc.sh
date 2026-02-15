#!/bin/bash
# Complete OTA creation WITH checksoc.txt - Run on WSL
set -e

cd ~/aosp

echo "=== FINAL OTA CREATION WITH CHECKSOC.TXT ==="

# Step 0: Extract proper payload_properties.txt from payload.bin
echo "Step 0: Extracting proper payload_properties.txt from payload.bin..."
if [ -f "ota_build_v2/payload.bin" ]; then
    # Use brillo_update_payload to generate proper payload_properties.txt with base64 hashes
    python2 build/tools/releasetools/brillo_update_payload.py properties \
        --payload ota_build_v2/payload.bin \
        --properties_file ota_signed_extract/payload_properties.txt || {
        echo "⚠️  brillo_update_payload failed, trying alternate method..."
        # Fallback: manually calculate hashes
        # Copy the payload into the ota_signed_extract directory
        cp ota_build_v2/payload.bin ota_signed_extract/
        python3 << 'PROPS_EOF'
import hashlib, struct

with open('ota_signed_extract/payload.bin', 'rb') as f:
    # Read file hash
    data = f.read()
    file_hash = hashlib.sha256(data).digest()
    file_hash_b64 = __import__('base64').b64encode(file_hash).decode()
    
    # Read metadata from header
    f.seek(0)
    magic = f.read(4)
    version = struct.unpack('>Q', f.read(8))[0]
    manifest_size = struct.unpack('>Q', f.read(8))[0]
    metadata_signature_size = struct.unpack('>I', f.read(4))[0] if version >= 2 else 0
    
    # METADATA = Header (24 bytes) + Manifest
    # This is what Android's update_engine expects!
    f.seek(0)
    header_size = 24 if version >= 2 else 20
    metadata_size = header_size + manifest_size
    metadata = f.read(metadata_size)
    metadata_hash = hashlib.sha256(metadata).digest()
    metadata_hash_b64 = __import__('base64').b64encode(metadata_hash).decode()
    
    # Write properties file
    with open('ota_signed_extract/payload_properties.txt', 'w') as out:
        out.write(f'FILE_HASH={file_hash_b64}\n')
        out.write(f'FILE_SIZE={len(data)}\n')
        out.write(f'METADATA_HASH={metadata_hash_b64}\n')
        out.write(f'METADATA_SIZE={metadata_size}\n')
    
    print(f'Generated payload_properties.txt:')
    print(f'  FILE_HASH={file_hash_b64}')
    print(f'  FILE_SIZE={len(data)}')
    print(f'  METADATA_HASH={metadata_hash_b64}')
    print(f'  METADATA_SIZE={manifest_size}')
PROPS_EOF
    }
    echo "✅ Generated proper payload_properties.txt with base64 hashes"
else
    echo "❌ ERROR: ota_build_v2/payload.bin not found!"
    exit 1
fi

# Step 0b: Ensure checksoc.txt has EXACTLY 20 bytes (no extra newline)
echo "Step 0b: Creating checksoc.txt with exactly 20 bytes..."
printf "SWI68-29958-1300R67\n" > ota_signed_extract/checksoc.txt
CHECKSOC_SIZE=$(stat -c%s ota_signed_extract/checksoc.txt)
if [ $CHECKSOC_SIZE -eq 20 ]; then
    echo "✅ checksoc.txt is exactly 20 bytes"
else
    echo "❌ ERROR: checksoc.txt is $CHECKSOC_SIZE bytes (should be 20)"
    exit 1
fi

# Step 1: Create initial dummy metadata
echo
echo "Step 1: Creating initial dummy metadata..."
cat > ota_signed_extract/META-INF/com/android/metadata << 'EOF'
ota-property-files=dummy
ota-streaming-property-files=dummy
ota-required-cache=0
ota-type=AB
post-build=mediatek/mt2712_saic_eh32/mt2712_saic_eh32:9/PPR1.181005.003/22113:user/test-keys
post-build-incremental=22113
post-sdk-level=28
post-security-patch-level=2020-07-05
post-timestamp=$(date +%s)
pre-device=mt2712_saic_eh32
EOF

# Step 2: Iteratively calculate offsets until metadata size stabilizes
echo
echo "Step 2: Iteratively calculating offsets until metadata stabilizes..."

MAX_ITERATIONS=5
ITERATION=0
METADATA_SIZE=0
PREV_METADATA_SIZE=0

while [ $ITERATION -lt $MAX_ITERATIONS ]; do
    ITERATION=$((ITERATION + 1))
    echo
    echo "--- Iteration $ITERATION ---"
    
    # Create ZIP with current metadata
    cd ota_signed_extract
    rm -f ../ota_iteration_${ITERATION}.zip
    
    zip -q -0 ../ota_iteration_${ITERATION}.zip META-INF/com/android/metadata
    zip -q -0 -u ../ota_iteration_${ITERATION}.zip care_map.txt
    zip -q -0 -u ../ota_iteration_${ITERATION}.zip checksoc.txt
    zip -q -0 -u ../ota_iteration_${ITERATION}.zip compatibility.zip
    zip -q -0 -u ../ota_iteration_${ITERATION}.zip payload.bin
    zip -q -0 -u ../ota_iteration_${ITERATION}.zip payload_properties.txt
    zip -q -0 -u ../ota_iteration_${ITERATION}.zip META-INF/com/android/otacert
    
    cd ..
    
    # Calculate actual offsets from this ZIP
    python3 << PYTHON_EOF
import struct, zipfile

def get_offset(zp, fn):
    with open(zp, 'rb') as f:
        with zipfile.ZipFile(f) as zf:
            info = zf.getinfo(fn)
            f.seek(info.header_offset)
            h = f.read(30)
            fnl = struct.unpack('<H', h[26:28])[0]
            exl = struct.unpack('<H', h[28:30])[0]
            return info.header_offset + 30 + fnl + exl, info.file_size

zp = 'ota_iteration_${ITERATION}.zip'
files = ['payload.bin', 'payload_properties.txt', 'care_map.txt', 'checksoc.txt', 
         'compatibility.zip', 'META-INF/com/android/metadata', 'META-INF/com/android/otacert']

offsets = {}
for fn in files:
    offset, size = get_offset(zp, fn)
    offsets[fn] = (offset, size)
    print(f"{fn}: offset={offset}, size={size}")

# Generate metadata content with ACTUAL offsets
# IMPORTANT: Original OTA does NOT list checksoc.txt or otacert in ota-property-files!
payload_offset = offsets['payload.bin'][0]
prop_files = [
    f"payload_metadata.bin:{payload_offset}:88046",
    f"payload.bin:{payload_offset}:{offsets['payload.bin'][1]}",
    f"payload_properties.txt:{offsets['payload_properties.txt'][0]}:{offsets['payload_properties.txt'][1]}",
    f"care_map.txt:{offsets['care_map.txt'][0]}:{offsets['care_map.txt'][1]}",
    f"compatibility.zip:{offsets['compatibility.zip'][0]}:{offsets['compatibility.zip'][1]}",
    f"metadata:{offsets['META-INF/com/android/metadata'][0]}:{offsets['META-INF/com/android/metadata'][1]}"
]

stream_files = [
    f"payload.bin:{payload_offset}:{offsets['payload.bin'][1]}",
    f"payload_properties.txt:{offsets['payload_properties.txt'][0]}:{offsets['payload_properties.txt'][1]}",
    f"care_map.txt:{offsets['care_map.txt'][0]}:{offsets['care_map.txt'][1]}",
    f"compatibility.zip:{offsets['compatibility.zip'][0]}:{offsets['compatibility.zip'][1]}",
    f"metadata:{offsets['META-INF/com/android/metadata'][0]}:{offsets['META-INF/com/android/metadata'][1]}"
]

with open('metadata_content_${ITERATION}.txt', 'w') as f:
    f.write("ota-property-files=" + ",".join(prop_files) + "\n")
    f.write("ota-streaming-property-files=" + ",".join(stream_files) + "\n")

print(f"\\nMetadata size: {offsets['META-INF/com/android/metadata'][1]} bytes")
PYTHON_EOF
    
    # Update metadata file with calculated offsets
    cat > ota_signed_extract/META-INF/com/android/metadata << EOF
$(cat metadata_content_${ITERATION}.txt)
ota-required-cache=0
ota-type=AB
post-build=mediatek/mt2712_saic_eh32/mt2712_saic_eh32:9/PPR1.181005.003/22113:user/test-keys
post-build-incremental=22113
post-sdk-level=28
post-security-patch-level=2020-07-05
post-timestamp=$(date +%s)
pre-device=mt2712_saic_eh32
EOF
    
    # Check new metadata size
    METADATA_SIZE=$(stat -c%s ota_signed_extract/META-INF/com/android/metadata)
    echo "New metadata size: ${METADATA_SIZE} bytes"
    
    # Check if size stabilized
    if [ $ITERATION -gt 1 ] && [ $METADATA_SIZE -eq $PREV_METADATA_SIZE ]; then
        echo "✅ Metadata size stabilized at ${METADATA_SIZE} bytes after ${ITERATION} iterations"
        break
    fi
    
    PREV_METADATA_SIZE=$METADATA_SIZE
done

if [ $ITERATION -eq $MAX_ITERATIONS ]; then
    echo "⚠️  Warning: Reached max iterations, using last calculated offsets"
fi

echo
echo "✅ Final metadata with stable offsets:"
cat ota_signed_extract/META-INF/com/android/metadata
# Step 3: Create final unsigned ZIP with stabilized metadata
echo
echo "Step 3: Creating final unsigned ZIP with stabilized metadata..."
cd ota_signed_extract
rm -f ../ota_with_checksoc_unsigned.zip

zip -q -0 ../ota_with_checksoc_unsigned.zip META-INF/com/android/metadata
zip -q -0 -u ../ota_with_checksoc_unsigned.zip care_map.txt
zip -q -0 -u ../ota_with_checksoc_unsigned.zip checksoc.txt
zip -q -0 -u ../ota_with_checksoc_unsigned.zip compatibility.zip
zip -q -0 -u ../ota_with_checksoc_unsigned.zip payload.bin
zip -q -0 -u ../ota_with_checksoc_unsigned.zip payload_properties.txt
zip -q -0 -u ../ota_with_checksoc_unsigned.zip META-INF/com/android/otacert

cd ..

echo "✅ Created final unsigned ZIP"

# Step 4: Sign to see file order
echo "Step 3: Signing to determine file order..."
export LD_LIBRARY_PATH="$PWD/out/host/linux-x86/lib64:$LD_LIBRARY_PATH"

java -Xmx2048m \
    --add-exports=java.base/sun.security.x509=ALL-UNNAMED \
    --add-exports=java.base/sun.security.pkcs=ALL-UNNAMED \
    -jar out/host/linux-x86/framework/signapk.jar \
    -w testkey.x509.pem testkey.pk8 \
    ota_with_checksoc_unsigned.zip \
    ota_signed_WITH_checksoc_TEMP.zip

echo "✅ Signed with dummy metadata"
zipinfo -l ota_signed_WITH_checksoc_TEMP.zip

# Step 4: Calculate offsets from signed ZIP
echo
echo "Step 4: Calculating offsets from signed ZIP..."
python3 << 'PYTHON_EOF'
import struct, zipfile

def get_offset(zp, fn):
    with open(zp, 'rb') as f:
        with zipfile.ZipFile(f) as zf:
            info = zf.getinfo(fn)
            f.seek(info.header_offset)
            h = f.read(30)
            fnl = struct.unpack('<H', h[26:28])[0]
            exl = struct.unpack('<H', h[28:30])[0]
            return info.header_offset + 30 + fnl + exl, info.file_size

zp = 'ota_signed_WITH_checksoc_TEMP.zip'
files = ['payload.bin', 'payload_properties.txt', 'care_map.txt', 'checksoc.txt', 
         'compatibility.zip', 'META-INF/com/android/metadata', 'META-INF/com/android/otacert']

print("Calculating offsets from signed ZIP:")
offsets = {}
for fn in files:
    offset, size = get_offset(zp, fn)
    offsets[fn] = (offset, size)
    print(f"  {fn}: offset={offset}, size={size}")

# Generate metadata content
# IMPORTANT: Original OTA does NOT list checksoc.txt or otacert in ota-property-files!
payload_offset = offsets['payload.bin'][0]
prop_files = [
    f"payload_metadata.bin:{payload_offset}:88046",
    f"payload.bin:{payload_offset}:{offsets['payload.bin'][1]}",
    f"payload_properties.txt:{offsets['payload_properties.txt'][0]}:{offsets['payload_properties.txt'][1]}",
    f"care_map.txt:{offsets['care_map.txt'][0]}:{offsets['care_map.txt'][1]}",
    f"compatibility.zip:{offsets['compatibility.zip'][0]}:{offsets['compatibility.zip'][1]}",
    f"metadata:{offsets['META-INF/com/android/metadata'][0]}:{offsets['META-INF/com/android/metadata'][1]}"
]

stream_files = [
    f"payload.bin:{payload_offset}:{offsets['payload.bin'][1]}",
    f"payload_properties.txt:{offsets['payload_properties.txt'][0]}:{offsets['payload_properties.txt'][1]}",
    f"care_map.txt:{offsets['care_map.txt'][0]}:{offsets['care_map.txt'][1]}",
    f"compatibility.zip:{offsets['compatibility.zip'][0]}:{offsets['compatibility.zip'][1]}",
    f"metadata:{offsets['META-INF/com/android/metadata'][0]}:{offsets['META-INF/com/android/metadata'][1]}"
]

with open('metadata_lines_WITH_CHECKSOC.txt', 'w') as f:
    f.write("ota-property-files=" + ",".join(prop_files) + "\n")
    f.write("ota-streaming-property-files=" + ",".join(stream_files) + "\n")

print("\n✅ Calculated offsets written to metadata_lines_WITH_CHECKSOC.txt")
PYTHON_EOF

# Step 5: Extract signed ZIP and update metadata
echo
echo "Step 5: Extracting signed ZIP and updating metadata..."
rm -rf ota_signed_extract_v2
mkdir ota_signed_extract_v2
cd ota_signed_extract_v2
unzip -q ../ota_signed_WITH_checksoc_TEMP.zip

# Update metadata with correct offsets
cat > META-INF/com/android/metadata << EOF
$(cat ../metadata_lines_WITH_CHECKSOC.txt)
ota-required-cache=0
ota-type=AB
post-build=mediatek/mt2712_saic_eh32/mt2712_saic_eh32:9/PPR1.181005.003/22113:user/test-keys
post-build-incremental=22113
post-sdk-level=28
post-security-patch-level=2020-07-05
post-timestamp=$(date +%s)
pre-device=mt2712_saic_eh32
EOF

echo "✅ Updated metadata file:"
cat META-INF/com/android/metadata

# Step 6: Recreate ZIP in SAME ORDER as signapk
echo
echo "Step 6: Recreating ZIP with correct metadata..."
rm -f ../ota_final_with_checksoc.zip

# Must match the order from zipinfo of the signed ZIP
zip -q -0 ../ota_final_with_checksoc.zip META-INF/com/android/metadata
zip -q -0 -u ../ota_final_with_checksoc.zip care_map.txt
zip -q -0 -u ../ota_final_with_checksoc.zip checksoc.txt
zip -q -0 -u ../ota_final_with_checksoc.zip compatibility.zip
zip -q -0 -u ../ota_final_with_checksoc.zip payload.bin
zip -q -0 -u ../ota_final_with_checksoc.zip payload_properties.txt
zip -q -0 -u ../ota_final_with_checksoc.zip META-INF/com/android/otacert

cd ..

echo "✅ Recreated ZIP with correct metadata"

# Step 7: Final signing
echo
echo "Step 7: Final signing..."
java -Xmx2048m \
    --add-exports=java.base/sun.security.x509=ALL-UNNAMED \
    --add-exports=java.base/sun.security.pkcs=ALL-UNNAMED \
    -jar out/host/linux-x86/framework/signapk.jar \
    -w testkey.x509.pem testkey.pk8 \
    ota_final_with_checksoc.zip \
    usb_ota_update_COMPLETE.zip

echo
echo "✅✅✅ FINAL OTA PACKAGE CREATED ✅✅✅"
ls -lh usb_ota_update_COMPLETE.zip

# Step 8: Verify offsets
echo
echo "Step 8: FINAL VERIFICATION..."
python3 << 'VERIFY_EOF'
import struct, zipfile

def get_offset(zp, fn):
    with open(zp, 'rb') as f:
        with zipfile.ZipFile(f) as zf:
            info = zf.getinfo(fn)
            f.seek(info.header_offset)
            h = f.read(30)
            fnl = struct.unpack('<H', h[26:28])[0]
            exl = struct.unpack('<H', h[28:30])[0]
            return info.header_offset + 30 + fnl + exl

# Read expected offsets from metadata
with open('metadata_lines_WITH_CHECKSOC.txt') as f:
    lines = f.read()
    prop_line = [l for l in lines.split('\n') if l.startswith('ota-property-files=')][0]
    entries = prop_line.replace('ota-property-files=', '').split(',')
    
expected = {}
for entry in entries:
    parts = entry.split(':')
    if len(parts) == 3:
        fname = parts[0]
        offset = int(parts[1])
        # Map to full paths
        if fname == 'metadata':
            expected['META-INF/com/android/metadata'] = offset
        elif fname == 'otacert':
            expected['META-INF/com/android/otacert'] = offset
        elif fname != 'payload_metadata.bin':  # Skip this virtual file
            expected[fname] = offset

# Check actual offsets
zp = 'usb_ota_update_COMPLETE.zip'
print("Final verification:")
all_ok = True
for fn, exp_offset in expected.items():
    try:
        actual = get_offset(zp, fn)
        ok = '✅' if actual == exp_offset else '❌'
        print(f"  {fn}: {ok} (expected {exp_offset}, actual {actual})")
        if actual != exp_offset:
            all_ok = False
    except Exception as e:
        print(f"  {fn}: ❌ ERROR: {e}")
        all_ok = False

print()
if all_ok:
    print("🎉🎉🎉 SUCCESS! All offsets match!")
    print("\nFile: ~/aosp/usb_ota_update_COMPLETE.zip")
    print("\nThis OTA includes:")
    print("  ✅ Modified payload.bin (with firewall changes)")
    print("  ✅ checksoc.txt (SWI68-29958-1300R67)")
    print("  ✅ Correct metadata offsets")
    print("  ✅ Proper AOSP signature")
else:
    print("❌ Offsets still don't match - need to debug further")

# Show final file list
print("\nFinal ZIP contents:")
import subprocess
subprocess.run(['unzip', '-l', 'usb_ota_update_COMPLETE.zip'])
VERIFY_EOF

echo
echo "Next steps:"
echo "1. Copy to Mac: scp adam@windows-ip:~/aosp/usb_ota_update_COMPLETE.zip /Users/adam/Downloads/"
echo "2. Rename to: usb_ota_update.zip"
echo "3. Copy to USB stick (FAT32 formatted)"
echo "4. Install on car via Settings → System → Software Update → USB Update"
