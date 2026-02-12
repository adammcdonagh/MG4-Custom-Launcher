#!/bin/bash
# Rebuild payload.bin with ALL partitions (corrected version)
# Run this in WSL Ubuntu ~/aosp directory
# Date: 3 Feb 2026

set -e  # Exit on error

echo "=========================================="
echo "Rebuilding payload.bin with ALL partitions"
echo "=========================================="

cd ~/aosp
# Umount any previous mounts if it exists
if mountpoint -q system_mount; then echo "Unmounting previous system_mount..."; sudo umount system_mount; fi

# Step 1: Use pre-extracted partitions from Mac (already done with payload-dumper-go)
echo ""
echo "Step 1: Using pre-extracted partitions from Mac..."
echo "⚠️  NOTE: You need to transfer the extracted partitions from Mac first!"
echo "    Mac location: /Users/adam/Downloads/1300 SWI68 R67/ota_modification_workspace/original_extracted/extracted_*/"
echo "    Run on Mac: scp -r '/Users/adam/Downloads/1300 SWI68 R67/ota_modification_workspace/original_extracted/extracted_'*/* adam@192.168.13.145:~/aosp/original_partitions/"
echo ""

if [ ! -d "original_partitions" ] || [ ! -f "original_partitions/system.img" ]; then
    echo "❌ ERROR: original_partitions/ not found or incomplete"
    echo "   Please transfer the extracted partitions from Mac first (see command above)"
    exit 1
fi

echo "✅ Found original_partitions/ with extracted files"

# Step 0: Setup signing keys
echo ""
echo "Step 0: Checking AOSP signing keys..."
KEYS_DIR=~/aosp/build/make/target/product/security
if [ ! -f "$KEYS_DIR/testkey.pk8" ] || [ ! -f "$KEYS_DIR/testkey.x509.pem" ]; then
    echo "❌ ERROR: AOSP test keys not found in $KEYS_DIR"
    exit 1
fi

# Convert PKCS#8 to PEM format for openssl (if not already converted)
if [ ! -f "$KEYS_DIR/testkey.pem" ]; then
    echo "  Converting testkey.pk8 to PEM format for signing..."
    openssl pkcs8 -inform DER -nocrypt -in "$KEYS_DIR/testkey.pk8" -out "$KEYS_DIR/testkey.pem"
    if [ $? -ne 0 ]; then
        echo "  ❌ ERROR: Failed to convert key format"
        exit 1
    fi
    echo "  ✅ Private key converted to PEM format"
else
    echo "  ✅ Private key PEM already exists"
fi

# Extract public key from X.509 certificate for delta_generator verification
if [ ! -f "$KEYS_DIR/testkey_public.pem" ]; then
    echo "  Extracting public key from X.509 certificate for verification..."
    openssl x509 -pubkey -noout -in "$KEYS_DIR/testkey.x509.pem" -out "$KEYS_DIR/testkey_public.pem"
    if [ $? -ne 0 ]; then
        echo "  ❌ ERROR: Failed to extract public key"
        exit 1
    fi
    echo "  ✅ Public key extracted"
else
    echo "  ✅ Public key already exists"
fi

ls -lh original_partitions/*.img | awk '{print "   - " $9 " (" $5 ")"}'

# Step 2: Prepare build directory
echo ""
echo "Step 2: Preparing build directory..."
mkdir -p ota_build_v2
cd ota_build_v2

# Step 3: Copy ALL partitions (use modified system.img)
echo ""
echo "Step 3: Copying partitions..."

# Copy bootloader partitions (unchanged)
echo "  - Copying bl2.img (bootloader stage 2)..."
cp ../original_partitions/bl2.img .

echo "  - Copying bl33.img (bootloader stage 3)..."
cp ../original_partitions/bl33.img .

# Copy boot partition (unchanged)
echo "  - Copying boot.img (kernel)..."
cp ../original_partitions/boot.img .

# Copy dtbo (unchanged)
echo "  - Copying dtbo.img (device tree)..."
cp ../original_partitions/dtbo.img .

# Modify system partition - start fresh from original
echo "  - Modifying system.img (starting from original)..."

# Copy original system.img to working directory
cp ../original_partitions/system.img system.img.original
echo "    ✅ Copied original system.img ($(stat -c %s system.img.original | numfmt --to=iec-i --suffix=B))"

# Convert from sparse to raw if needed
file_size=$(stat -c %s system.img.original)
if [ $file_size -lt 2400000000 ]; then
    echo "    ⚠️  Converting sparse to raw format..."
    ../out/host/linux-x86/bin/simg2img system.img.original system.img.raw
    mv system.img.raw system.img.original
    echo "    ✅ Converted to raw format ($(stat -c %s system.img.original | numfmt --to=iec-i --suffix=B))"
fi

# Mount the system image
echo "    - Mounting system.img for modification..."
mkdir -p system_mount
sudo mount -o loop system.img.original system_mount/

if [ ! -f system_mount/system/bin/arp_update.sh ]; then
    echo "    ❌ ERROR: arp_update.sh not found in mounted system image"
    sudo umount system_mount
    exit 1
fi

# Backup original script
echo "    - Creating backup of arp_update.sh..."
sudo cp system_mount/system/bin/arp_update.sh system_mount/system/bin/arp_update.sh.original

# Modify the arp_update.sh script - comment out firewall rules
echo "    - Modifying arp_update.sh (commenting out 8 firewall rules)..."
sudo sed -i '1,45 s/^iptables/# iptables/' system_mount/system/bin/arp_update.sh  # Comment out all ipv4 rules
sudo sed -i '1,45 s/^ip6tables/# ip6tables/' system_mount/system/bin/arp_update.sh  # Comment out all ipv6 rules

# Verify modifications
echo "    - Verifying modifications..."
modified_lines=$(sudo grep -e "^# iptables" -e "^# ip6tables" system_mount/system/bin/arp_update.sh | wc -l)
if [ $modified_lines -ge 8 ]; then
    echo "    ✅ Successfully commented out $modified_lines iptables lines"
else
    echo "    ⚠️  Only $modified_lines lines commented (expected 8+)"
fi

# Unmount
echo "    - Unmounting system.img..."
sudo umount system_mount

# Use the modified system.img
mv system.img.original system.img
echo "    ✅ Modified system.img ready ($(stat -c %s system.img | numfmt --to=iec-i --suffix=B))"

# Copy TEE (unchanged)
echo "  - Copying tee.img (trusted execution environment)..."
cp ../original_partitions/tee.img .

# Copy vbmeta (unchanged)
echo "  - Copying vbmeta.img (verified boot metadata)..."
cp ../original_partitions/vbmeta.img .

# Copy vendor partition (unchanged)
echo "  - Copying vendor.img..."
cp ../original_partitions/vendor.img .

# Step 4: Verify all files present
echo ""
echo "Step 4: Verifying all partition files..."
required_partitions="bl2.img bl33.img boot.img dtbo.img system.img tee.img vbmeta.img vendor.img"
all_present=true
for part in $required_partitions; do
    if [ -f "$part" ]; then
        size=$(stat -c %s "$part" | numfmt --to=iec-i --suffix=B)
        echo "  ✅ $part ($size)"
    else
        echo "  ❌ $part MISSING"
        all_present=false
    fi
done

if [ "$all_present" = false ]; then
    echo ""
    echo "❌ ERROR: Not all partitions present, cannot continue"
    exit 1
fi

# Step 5: Ensure 4096-byte alignment for all images
echo ""
echo "Step 5: Ensuring 4096-byte alignment..."
for img in *.img; do
    size=$(stat -c %s "$img")
    aligned_size=$(( (size + 4095) / 4096 * 4096 ))
    if [ $size -ne $aligned_size ]; then
        echo "  - Aligning $img: $size → $aligned_size bytes"
        truncate -s $aligned_size "$img"
    fi
done

# Step 6: Generate payload.bin with ALL 8 partitions
echo ""
echo "Step 6: Generating signed payload.bin with delta_generator..."
echo "  Partition order: bl2, bl33, boot, dtbo, system, tee, vbmeta, vendor"
# CORRECT AOSP WORKFLOW: generate → hash → sign (3 steps)
echo "  Using proper AOSP signing workflow..."

# Step 6a: Generate UNSIGNED payload
echo "  Step 6a: Generating unsigned payload..."
../out/host/linux-x86/bin/delta_generator \
    --out_file=payload.bin.unsigned \
    --partition_names=bl2:bl33:boot:dtbo:system:tee:vbmeta:vendor \
    --new_partitions=bl2.img:bl33.img:boot.img:dtbo.img:system.img:tee.img:vbmeta.img:vendor.img \
    --major_version=2

if [ $? -ne 0 ] || [ ! -f payload.bin.unsigned ]; then
    echo "  ❌ ERROR: Failed to generate unsigned payload"
    exit 1
fi

# Step 6b: Calculate hash with signature_size to reserve space
echo "  Step 6b: Calculating payload hash (signature_size=256 for RSA-2048)..."
../out/host/linux-x86/bin/delta_generator \
    --in_file=payload.bin.unsigned \
    --signature_size=256 \
    --out_hash_file=payload_hash.dat \
    --out_metadata_hash_file=metadata_hash.dat

if [ $? -ne 0 ]; then
    echo "  ❌ ERROR: Failed to calculate hashes"
    exit 1
fi

# Step 6c: Sign the hashes with openssl
echo "  Step 6c: Signing hashes with AOSP testkey..."
openssl pkeyutl -sign -inkey ~/aosp/build/make/target/product/security/testkey.pem \
    -pkeyopt digest:sha256 \
    -in metadata_hash.dat \
    -out metadata_signature.bin

openssl pkeyutl -sign -inkey ~/aosp/build/make/target/product/security/testkey.pem \
    -pkeyopt digest:sha256 \
    -in payload_hash.dat \
    -out payload_signature.bin

if [ ! -f metadata_signature.bin ] || [ ! -f payload_signature.bin ]; then
    echo "  ❌ ERROR: Failed to create signatures"
    exit 1
fi

# Step 6d: Inject signatures into payload
echo "  Step 6d: Injecting signatures into payload..."
../out/host/linux-x86/bin/delta_generator \
    --in_file=payload.bin.unsigned \
    --signature_size=256 \
    --signature_file=payload_signature.bin \
    --metadata_signature_file=metadata_signature.bin \
    --out_file=payload.bin

if [ $? -eq 0 ] && [ -f payload.bin ]; then
    echo "  ✅ Signed payload generated successfully"
    rm -f payload.bin.unsigned payload_hash.dat metadata_hash.dat \
          payload_signature.bin metadata_signature.bin
    
    # Verify the signature
    echo ""
    echo "Step 6e: Verifying payload signature..."
    ../out/host/linux-x86/bin/delta_generator \
        --in_file=payload.bin \
        --public_key=$HOME/aosp/build/make/target/product/security/testkey_public.pem
    
    if [ $? -eq 0 ]; then
        echo "  ✅ Payload signature verified successfully"
    else
        echo "  ⚠️  WARNING: Signature verification failed"
    fi
else
    echo "  ❌ ERROR: delta_generator failed to generate payload"
    exit 1
fi

# Step 7: Verify payload.bin
echo ""
echo "Step 7: Verifying payload.bin..."
payload_size=$(stat -c %s payload.bin | numfmt --to=iec-i --suffix=B)
echo "  Size: $payload_size"

# Expected size should be close to original (909MB)
payload_bytes=$(stat -c %s payload.bin)
if [ $payload_bytes -gt 900000000 ] && [ $payload_bytes -lt 950000000 ]; then
    echo "  ✅ Size looks correct (expected ~909MB)"
else
    echo "  ⚠️  Size is unexpected (should be ~909MB, got $payload_size)"
    echo "      This may indicate an issue with the payload generation"
fi

# Step 8: Basic verification
echo ""
echo "Step 8: Basic verification..."
cd ~/aosp

# Check that payload.bin was created
if [ -f ota_build_v2/payload.bin ]; then
    echo "  ✅ payload.bin created successfully"
    
    # List all .img files used to create the payload
    echo "  ✅ Used 8 partition images:"
    ls -1 ota_build_v2/*.img | wc -l | xargs -I {} echo "     {} partition images were processed"
    
    # Show a few bytes from the payload to confirm it's not empty
    payload_header=$(xxd -l 16 -p ota_build_v2/payload.bin)
    if [ ! -z "$payload_header" ]; then
        echo "  ✅ Payload header present (contains data)"
    else
        echo "  ⚠️  Payload may be empty or corrupted"
    fi
else
    echo "  ❌ ERROR: payload.bin was not created"
    exit 1
fi

# Step 9: Summary
echo ""
echo "=========================================="
echo "BUILD COMPLETE"
echo "=========================================="
echo ""
echo "Output files:"
echo "  - Payload: ~/aosp/ota_build_v2/payload.bin ($payload_size)"
echo ""
echo "Next steps:"
echo "  1. Copy payload.bin to Mac for OTA packaging:"
echo "     scp adam@192.168.13.145:~/aosp/ota_build_v2/payload.bin ~/Downloads/"
echo "  2. On Mac, copy to ota workspace and run final_ota_with_checksoc.sh"
echo "  3. Install on car via USB stick"
echo ""
