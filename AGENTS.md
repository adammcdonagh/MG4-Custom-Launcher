# IMPORTANT: Java Package Path and Source Root

**Do NOT change the package declaration to `main.java.com.custom.launcher` or similar.**

- The correct package for all app code is `com.custom.launcher` (and subpackages).
- The source root is `app/src/main/java/`.
- The directory structure under `java/` must be `com/custom/launcher/...`.
- Do NOT add or expect a `main/java/com/custom/launcher` package or directory.
- If you see errors about `main.java.com.custom.launcher`, it means the IDE or build system is misconfigured, not the code.

**If you are an agent or developer, do NOT edit package declarations or move files to match a `main.java` prefix.**

This note is to prevent repeated, unnecessary changes that break the build.

---

# CRITICAL: OTA Firmware Modification Project - STATUS UPDATE (3 Feb 2026)

## ⚠️ WHY ADB IS NOT AN OPTION - READ THIS FIRST ⚠️

**The car's firewall BLOCKS all ADB access:**

- iptables rules in `/system/bin/arp_update.sh` DROP packets on port 5555 (ADB)
- These rules run on EVERY boot
- Cannot use `adb shell` to fix the problem that blocks `adb shell`
- This is a chicken-and-egg problem

**Why "just use ADB" suggestions are WRONG:**

- ❌ "Use adb shell to modify the script" - ADB port is blocked by the script!
- ❌ "Use adb to push files" - Cannot connect via ADB at all
- ❌ "Flash with fastboot" - Bootloader is locked, fastboot not accessible
- ❌ "Root the device first" - Cannot root without ADB/fastboot access

**The ONLY solution:**
Create a modified OTA update package that the car will accept via USB stick, which contains the patched firewall script that removes the ADB blocks.

## Current Status: ⚠️ BLOCKED - Car Rejecting All Modified OTA Packages

### What's Been Completed:

1. ✅ Extracted OTA package (R67 firmware) using payload-dumper-go
2. ✅ Decompressed payload.bin to partition images (system.img, boot.img, vendor.img)
3. ✅ Extracted system.img filesystem
4. ✅ Modified `/system/bin/arp_update.sh` - commented out 8 firewall rules
5. ✅ Created new modified system.img (1.9GB sparse format) with patched script
6. ✅ Set up WSL2 on Windows (Ubuntu 24.04) with 400GB space on E: drive
7. ✅ Built complete AOSP Android 9 source tree (200GB+)
8. ✅ Compiled delta_generator and all OTA tools
9. ✅ Generated modified payload.bin from patched system.img
10. ✅ Fixed ALL metadata format issues:
    - checksoc.txt: Exactly 20 bytes (no extra newline)
    - payload_properties.txt: Base64 hash format (not hex)
    - ota-property-files: Excludes checksoc.txt and otacert (matches original)
    - ota-streaming-property-files: Includes metadata field at end
11. ✅ Created multiple OTA packages with correct structure and signatures

### Current Problem:

**Car STILL rejects the modified OTA package** despite all metadata being correct and matching the original format exactly. All visible format checks pass:

- ✅ File structure matches
- ✅ Metadata offsets correct
- ✅ Signature valid (AOSP test keys)
- ✅ All file sizes and checksums correct

**Hypothesis:** The payload.bin itself may have issues:

- Partition sizes might be incorrect
- Delta operations may be malformed
- Manifest proto format may have subtle differences
- Recovery may be validating payload internals we haven't checked yet

### Next Steps - Payload Investigation Required:

Since all metadata checks pass but car still rejects the OTA, we need to investigate the payload.bin file itself:

1. **Extract and Examine Payload Manifest:**
   - Use `delta_generator` or Python script to extract the manifest protobuf
   - Compare manifest structure with original payload.bin
   - Check partition list, operations, and signatures

2. **Validate Partition Compatibility:**
   - Check if modified partition sizes match expected sizes
   - Verify partition signatures/checksums
   - Ensure target partition versions are compatible

3. **Compare Delta Operations:**
   - Extract operation lists from both payloads
   - Check if delta operations reference correct partition offsets
   - Verify install operations are valid

4. **Alternative Approaches if Payload is Broken:**
   - Try full partition images instead of delta operations
   - Use different delta_generator flags
   - Consider modifying original payload.bin directly instead of regenerating

### Working Script: final_ota_with_checksoc.sh ✅

**Location:** `/Users/adam/Downloads/1300 SWI68 R67/ota_modification_workspace/final_ota_with_checksoc.sh`

**Purpose:** Automated OTA package builder with all metadata fixes applied

**Status:** Fully functional, generates properly structured OTA packages

**Key Features:**

- Extracts proper base64 hashes from payload.bin using brillo_update_payload.py
- Creates exact 20-byte checksoc.txt using printf
- Iterative metadata offset calculation (handles size convergence)
- Excludes checksoc.txt/otacert from ota-property-files (car requires this)
- Includes metadata field in ota-streaming-property-files (critical)
- Signs with AOSP test keys matching original
- Comprehensive verification at end

**Usage:**

```bash
cd /Users/adam/Downloads/1300\ SWI68\ R67/ota_modification_workspace
./final_ota_with_checksoc.sh
# Output: usb_ota_update_TRULY_FINAL.zip
```

**Prerequisites:**

- payload.bin must exist in ~/aosp/ota_build/payload.bin (WSL path)
- All original files in ota_signed_extract/: care_map.txt, compatibility.zip, metadata, otacert, checksoc.txt
- Python 2.7 and 3.11 available
- Java 11 with signapk.jar configured

**Script Breakdown (418 lines):**

```bash
# Step 0: Extract payload_properties.txt with correct base64 hashes
cd ~/aosp
python3 build/tools/releasetools/brillo_update_payload.py properties \
  ota_build/payload.bin > payload_properties.txt

# Step 0b: Create checksoc.txt with EXACTLY 20 bytes
printf "SWI68-29958-1300R67\n" > checksoc.txt

# Step 1: Create dummy metadata for iteration
cat > metadata << 'EOF'
ota-property-files=payload_metadata.bin:0:88046,payload.bin:0:918571585,...
ota-streaming-property-files=payload.bin:0:918571585,...,metadata:0:602
EOF

# Step 2: Iterative convergence loop (max 5 iterations)
for i in 1 2 3 4 5; do
  # Create unsigned ZIP with current metadata
  cd ~/aosp/ota_signed_extract
  zip -X ../usb_ota_update_unsigned.zip ./*

  # Calculate actual byte offsets from ZIP
  python3 << 'PYTHON'
import zipfile
with zipfile.ZipFile('usb_ota_update_unsigned.zip', 'r') as z:
    for info in z.infolist():
        print(f"{info.filename}:{info.header_offset}:{info.file_size}")
PYTHON

  # Update metadata with new offsets
  # Check if metadata size changed (indicates need for another iteration)
  if [ "$old_size" == "$new_size" ]; then
    echo "Converged after $i iterations"
    break
  fi
done

# Step 3-7: Sign, extract, recalculate post-signing offsets, reassemble, sign again
java -jar signapk.jar -w testkey.x509.pem testkey.pk8 \
  usb_ota_update_unsigned.zip usb_ota_update_signed.zip

# Step 8: Verification
echo "Verifying all offsets match metadata..."
# Checks each file's offset matches metadata values
```

**Critical Metadata Format (Fixed):**

```
ota-property-files=payload_metadata.bin:6809:88046,payload.bin:6809:918571585,
  payload_properties.txt:918578452:154,care_map.txt:719:217,
  compatibility.zip:1057:5705,metadata:69:602

ota-streaming-property-files=payload.bin:6809:918571585,
  payload_properties.txt:918578452:154,care_map.txt:719:217,
  compatibility.zip:1057:5705,metadata:69:602
```

**NOTE:** This script generates valid OTA packages, but the car still rejects them. The problem appears to be inside payload.bin, not the package structure.

## Files Ready for Payload Investigation

**WSL (Windows) Workspace: `~/aosp/` (Ubuntu 24.04 on E: drive)**

```
~/aosp/
├── build/                              # AOSP build system
│   └── tools/releasetools/
│       ├── ota_from_target_files.py
│       ├── brillo_update_payload.py
│       └── signapk.jar
├── out/host/linux-x86/bin/
│   └── delta_generator               # OTA payload generator
├── ota_build/
│   ├── payload.bin                   # ⭐ Modified payload (918MB, UNDER INVESTIGATION)
│   ├── system.img                    # Modified partition with patched arp_update.sh
│   ├── boot.img                      # Unmodified
│   └── vendor.img                    # Unmodified
└── ota_signed_extract/               # Final OTA package files
    ├── payload.bin → ../ota_build/payload.bin
    ├── payload_properties.txt        # Base64 hashes (✅ CORRECT)
    ├── checksoc.txt                  # 20 bytes: "SWI68-29958-1300R67\n" (✅ CORRECT)
    ├── care_map.txt                  # Partition verification map
    ├── compatibility.zip             # Device compatibility checks
    ├── otacert                       # AOSP test certificate
    └── META-INF/com/android/
        └── metadata                  # Streaming offsets (✅ CORRECT)
```

**Mac Workspace: `/Users/adam/Downloads/1300 SWI68 R67/ota_modification_workspace/`**

```
ota_modification_workspace/
├── final_ota_with_checksoc.sh        # ⭐ Working OTA builder script (418 lines)
├── usb_ota_update_TRULY_FINAL.zip    # Generated OTA (876MB, CAR REJECTS)
├── ota_comparison/
│   ├── original/                     # Extracted original OTA for comparison
│   │   ├── payload.bin               # Original (952MB)
│   │   ├── payload_properties.txt
│   │   ├── checksoc.txt
│   │   ├── care_map.txt
│   │   └── META-INF/com/android/metadata
│   └── new/                          # Extracted generated OTA
│       ├── payload.bin               # Modified (918MB, 34MB smaller)
│       ├── payload_properties.txt
│       ├── checksoc.txt
│       └── META-INF/com/android/metadata
└── modified_ota_final/
    ├── system.img                    # Modified partition (1.9GB)
    └── system_mount/system/bin/
        ├── arp_update.sh             # ⭐ MODIFIED - 8 lines commented
        └── arp_update.sh.original    # Backup
```

**Original OTA:** `/Users/adam/Downloads/1300 SWI68 R67/AVN_MPU/usb_ota_update.zip` (909MB)

## Payload Investigation Guide

### Why Metadata Fixes Didn't Work

Despite fixing all 4 critical metadata issues, the car's recovery still rejects the OTA. This means the validation failure occurs **inside** the payload.bin processing, not at the package level.

**What We Fixed (All Confirmed Working):**

1. ✅ checksoc.txt format (20 bytes exactly)
2. ✅ payload_properties.txt hashes (base64 format)
3. ✅ ota-property-files structure (excludes checksoc/otacert)
4. ✅ ota-streaming-property-files structure (includes metadata)

**What Remains to Check (Payload Internals):**

### 1. Payload Manifest Extraction

The payload.bin file has a protobuf manifest at the beginning that describes the update:

```bash
# Extract manifest from payload (WSL)
cd ~/aosp
python3 build/tools/releasetools/brillo_update_payload.py info \
  ota_build/payload.bin > payload_manifest.txt

# Compare with original
python3 build/tools/releasetools/brillo_update_payload.py info \
  /path/to/original/payload.bin > original_manifest.txt

diff -u original_manifest.txt payload_manifest.txt
```

**Look for:**

- Partition list differences (system, boot, vendor)
- Partition sizes mismatches
- Operation counts (REPLACE vs REPLACE_BZ, SOURCE_COPY)
- Signature differences
- Manifest hash differences

### 2. Partition Compatibility Checks

The car's recovery validates partition sizes and versions:

```bash
# Check partition sizes in manifest
grep -E "partition_name|new_partition_info" payload_manifest.txt

# Expected sizes (from original OTA):
# system: ~2.5GB
# boot: ~32MB
# vendor: ~512MB
```

**Possible Issues:**

- Modified system.img is wrong size (should be exactly 2621440000 bytes for R67)
- Sparse image not converted correctly
- Partition version/timestamp mismatch

### 3. Delta Operations Analysis

The payload contains update operations. If delta_generator created incompatible operations:

```bash
# Count operation types
grep "type:" payload_manifest.txt | sort | uniq -c

# Original OTA should use:
# REPLACE_BZ - Compressed replacement
# ZERO - Zero out blocks
# SOURCE_COPY - Copy blocks from current partition
```

**Red Flags:**

- If modified payload has different operation types
- If operation block ranges are invalid
- If operations reference non-existent source blocks

### 4. Payload Signature Verification

The payload.bin has internal signatures that the recovery verifies:

```bash
# Check signature information
grep -A 10 "signatures_size" payload_manifest.txt
```

**Possible Issues:**

- Payload signed with wrong keys
- Signature offset incorrect
- Hash tree descriptor mismatch

### 5. Device-Specific Validation

The manifest may contain device-specific checks:

```bash
# Look for device fingerprints
grep -E "pre-device|post-device|fingerprint" payload_manifest.txt
```

**Expected:**

- `pre-device: mt2712_saic_eh32`
- No `pre-build` field (this is a FULL OTA, not incremental)

### Alternative: Direct Partition Modification

If payload regeneration is broken, try modifying the **original** payload.bin:

```bash
# Extract original payload partitions
cd ~/aosp
python3 build/tools/releasetools/brillo_update_payload.py extract \
  /path/to/original/payload.bin extracted_partitions/

# Replace system.img with modified one
cp ~/ota_build/system.img extracted_partitions/system.img

# Repack payload (if tool exists)
# This may not be possible - delta_generator doesn't have a "repack" mode
```

### 6. Full OTA vs Incremental Delta

Verify that delta_generator created a FULL OTA, not an incremental delta:

```bash
# Check if payload has "old_partition_info" fields
grep "old_partition_info" payload_manifest.txt

# Should be EMPTY for full OTA
# If present, delta_generator incorrectly made an incremental update
```

### Tools Available

**WSL (Ubuntu 24.04):**

- `delta_generator` - Create new payloads
- `brillo_update_payload.py` - Extract manifest, verify signatures
- `payload_info.py` - Parse protobuf manifest
- `ota_from_target_files.py` - Generate complete OTA packages

**Commands:**

```bash
# Extract full manifest details
python3 build/tools/releasetools/brillo_update_payload.py info payload.bin

# Verify payload signature
python3 build/tools/releasetools/brillo_update_payload.py verify payload.bin

# Extract partition images from payload
python3 build/tools/releasetools/brillo_update_payload.py extract payload.bin output_dir/
```

## Solution Path: Build AOSP on Windows WSL2

## Solution Path: Build AOSP on Windows WSL2

**Current Progress:**

- ✅ WSL2 installed (Ubuntu 24.04)
- ✅ Moved to E: drive (400GB available)
- ✅ SSH access configured
- ✅ All build dependencies installed
- ✅ AOSP 9.0.0_r61 downloaded and built (~200GB)
- ✅ delta_generator compiled successfully
- ✅ Modified payload.bin generated
- ✅ All metadata format issues fixed
- ⚠️ Car still rejects OTA - payload.bin investigation required

**Requirements Met:**

- ✅ ~400GB free disk space on E: drive
- ✅ Ubuntu 24.04 (WSL2 on Windows)
- ✅ 16GB+ RAM
- ✅ Fast SSD

**Steps Completed:**

**Steps Completed:**

### 1. Install Build Dependencies ✅

```bash
# Updated for Ubuntu 24.04
sudo apt-get update
sudo apt-get install -y \
    git-core gnupg flex bison build-essential zip curl \
    zlib1g-dev libc6-dev-i386 libncurses5 libncurses5-dev \
    x11proto-core-dev libx11-dev libgl1-mesa-dev \
    libxml2-utils xsltproc unzip fontconfig \
    python3 python3-pip gcc g++ make

pip3 install --user protobuf==3.20.3 --break-system-packages
```

### 2. Install Repo Tool ✅

```bash
mkdir -p ~/bin
echo 'export PATH=~/bin:$PATH' >> ~/.bashrc
source ~/.bashrc

curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
chmod a+x ~/bin/repo

git config --global user.email "adam@example.com"
git config --global user.name "Adam"
```

### 3. Download AOSP Source ✅

```bash
mkdir ~/aosp
cd ~/aosp
repo init -u https://android.googlesource.com/platform/manifest -b android-9.0.0_r61
repo sync -c -j8  # Took 4-8 hours, downloaded ~200GB
```

### 4. Build delta_generator ✅

```bash
cd ~/aosp
source build/envsetup.sh
lunch aosp_arm64-eng
make delta_generator -j$(nproc)

# Binary created at: out/host/linux-x86/bin/delta_generator
```

### 5. Create payload.bin with Modified System Image ✅

**Transferred modified system.img from Mac to WSL, then generated payload.bin:**

```bash
cd ~/aosp

# Created target_files structure
mkdir -p ota_build
cp ~/modified_ota_final/system.img ota_build/
cp ~/modified_ota_final/boot.img ota_build/
cp ~/modified_ota_final/vendor.img ota_build/

# Generated payload.bin using delta_generator
out/host/linux-x86/bin/delta_generator \
  --partition_names=system,boot,vendor \
  --new_partitions=ota_build/system.img,ota_build/boot.img,ota_build/vendor.img \
  --out_file=ota_build/payload.bin

# Result: payload.bin (918MB) with modified system partition
```

### 6. Assemble Final OTA ZIP ✅

**Used final_ota_with_checksoc.sh script to create complete OTA package:**

```bash
# On Mac, transferred files back from WSL
# Ran final_ota_with_checksoc.sh with all metadata fixes

cd /Users/adam/Downloads/1300\ SWI68\ R67/ota_modification_workspace
./final_ota_with_checksoc.sh

# Generated: usb_ota_update_TRULY_FINAL.zip (876MB)
# Contains: payload.bin, payload_properties.txt, checksoc.txt,
#           care_map.txt, compatibility.zip, otacert, metadata
```

### 7. Install on Car ⚠️ BLOCKED

**Installation steps ready, but car rejects OTA:**

1. ~~Copy `usb_ota_update_TRULY_FINAL.zip` to USB stick root~~
2. ~~Insert USB into car~~
3. ~~Navigate to Settings → System → Software Update → USB Update~~
4. ~~Car will install modified OTA~~
5. ~~Reboot~~
6. ~~**NOW** ADB will work: `adb connect car-ip:5555`~~

**Current Issue:** Car rejects OTA during recovery validation - need to investigate payload.bin internals.

## Technical Details

### Modified Script Content

```bash
# /system/bin/arp_update.sh (lines 11-15, 23-26 commented out)

### ADB BLOCK REMOVED - Network ADB now enabled ###
# iptables -I INPUT -p tcp --dport 5555 -j DROP
# iptables -I OUTPUT -p tcp --sport 5555 -j DROP

### CARPLAY WIRELESS BLOCK REMOVED ###
# iptables -I INPUT -p tcp --dport 7000 -j DROP
# iptables -I INPUT -p udp --dport 5353 -j DROP
```

### Why This Blocked

**Google's OTA tools are NOT standalone** - they require:

1. Full AOSP source tree (~200GB)
2. Complete build environment with all dependencies compiled
3. Generated protobuf Python modules (apex_manifest, ota_metadata_pb2, care_map_pb2)
4. Compiled C++ binaries (delta_generator, img2simg, simg2img, bsdiff, etc.)
5. Build configuration files and Android.bp (Soong) build system

**Attempted approaches that failed:**

- ❌ Clone only build + update_engine repos → missing dependencies
- ❌ Use brillo_update_payload script → needs delta_generator binary
- ❌ Use ota_from_target_files.py directly → needs protobuf modules from full build
- ❌ Compile delta_generator standalone → uses Android.bp, needs full AOSP tree
- ❌ Find pre-built delta_generator → none available for Android 9 Pie

**The ONLY solution:** Build full AOSP source tree (Android 9 to match MG4 firmware).

## DO NOT SUGGEST THESE - THEY WILL NOT WORK:

❌ **"Just use adb shell to modify the script"**

- ADB port is blocked by iptables
- Cannot connect via ADB

❌ **"Use adb push to replace the file"**

- ADB is blocked, cannot push anything

❌ **"Flash with fastboot"**

- Bootloader is locked
- No unlock method available

❌ **"Root the device first"**

- Cannot root without ADB or fastboot
- Catch-22 situation

❌ **"Use an init.d script to override firewall"**

- Cannot install init.d script without ADB or root
- /system is read-only

❌ **"Fix the checksoc.txt format"** (ALREADY DONE)

- Fixed to exactly 20 bytes with printf
- Not the issue

❌ **"Fix the payload_properties.txt hashes"** (ALREADY DONE)

- Switched from hex to base64 format
- Not the issue

❌ **"Fix the metadata structure"** (ALREADY DONE)

- Excluded checksoc.txt/otacert from ota-property-files
- Added metadata to ota-streaming-property-files
- All metadata validated correct
- Not the issue

✅ **THE ONLY SOLUTION:**

1. Investigate payload.bin internals (manifest, signatures, partition compatibility)
2. Fix whatever is causing the car's recovery to reject the payload
3. Build modified OTA package with corrected payload
4. Install via USB stick
5. THEN ADB works

---

# MG4 Custom Launcher Development - Agent Context

## Project Overview

Developing a custom Android launcher for MG4 electric vehicle to replace the stock SAIC launcher with a cleaner, more focused interface showing battery level, range, time/date, and media playback.

## Work Completed

### Phase 1: OTA Package Analysis

- **Extracted APKs from multiple firmware versions:**
  - R40 (1100 SWI69 R40): Original firmware with `EvCharge_eh32_ll.apk`
  - R46 (1300 SWI68 R46): Intermediate version
  - R67 (1300 SWI68 R67): Current firmware on user's car

- **Tools Used:**
  - `payload-dumper-go` - Extract payload.bin from OTA packages
  - `7zip` - Extract ext4 filesystem images (system.img, vendor.img)
  - `apktool` - Decompile APKs for analysis

- **Key Findings:**
  - EvCharge app with widgets exists in R40 only
  - R46 and R67 removed the standalone EvCharge app
  - Charging functionality integrated into launcher in R67
  - Stock launcher: `launcher_eh32_eu_P.apk` (package: `com.saicmotor.hmi.launcher`)

### Phase 2: Widget Analysis

**EvCharge_eh32_ll.apk (R40 only):**

- **Two widgets identified:**
  1. `EVAppWidget2x1` - 2x1 rectangular battery widget (500x250dp)
     - Shows: "Battery" title, battery icon, range (km), battery percentage
     - Layout: `res/layout-w1778dp/ev_app_widget_2x1.xml`
     - Config: `res/xml/ev_app_widget2x1_info.xml`
     - Background: Color-coded (green/yellow/red)
  2. `EVAppWidgetIcon1x1` - 1x1 compact icon (250x250dp)
     - Layout: `res/layout-w1778dp/ev_app_widget_icon1x1.xml`
     - Config: `res/xml/ev_app_widget_icon1x1_info.xml`

### Phase 3: Vehicle Data Integration Research

**R67 Launcher Analysis:**

- Vehicle data accessed via: `com.saicmotor.telematics.VehicleService`
- Key classes found in decompiled launcher:
  - `VehicleStatusManager` - Manages vehicle state
  - `VehicleChargingCallback` - Receives charging updates
  - `ChargingViewModel` - Business logic
  - `ChargingFragment` - UI display
  - Layout: `fragment_energy_charging.xml` & `function_card_energy.xml`

### Phase 4: Custom Launcher Development

**Created Complete Android Project:**

- Location: `CustomLauncher/` (current directory)
- Package: `com.custom.launcher`
- Target: Android 9+ (API 28+)

**Project Structure:**

```
CustomLauncher/
├── app/
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/custom/launcher/
│       │   ├── MainActivity.java
│       │   └── service/
│       │       ├── VehicleDataService.java
│       │       └── MediaListenerService.java
│       └── res/
│           ├── layout/activity_main.xml
│           ├── drawable/*.xml (icons)
│           └── values/*.xml (colors, strings, styles)
├── build.gradle
├── settings.gradle
└── README.md
```

**Features Implemented:**

1. ✅ Battery & Range Display (color-coded card)
2. ✅ Time & Date Widget (auto-updating)
3. ✅ Now Playing Card (media info + controls)
4. ✅ Heated Seats & Steering Wheel Controls (with vehicle sync)
5. ✅ App Shortcuts Grid
6. ✅ Vehicle Service Binding (with mock fallback)
7. ✅ Media Session Integration
8. ✅ Dark Theme UI
9. ✅ Debug Dialog (triple-tap clock to view live logs)

## Technical Details

### Vehicle Service Integration ✅ WORKING

**CRITICAL DISCOVERY**: SAIC SDK classes are NOT in the system classloader - they are packaged in the stock launcher APK.

**Working Implementation** (tested on car 28 Jan 2026):

```java
// Step 1: Load SDK from launcher package context
Context launcherContext = context.createPackageContext(
    "com.saicmotor.hmi.launcher",
    Context.CONTEXT_INCLUDE_CODE
);
ClassLoader launcherClassLoader = launcherContext.getClassLoader();

// Step 2: Load VehicleChargingManager class via reflection
Class<?> managerClass = launcherClassLoader.loadClass(
    "com.saicmotor.sdk.vehiclesettings.manager.VehicleChargingManager"
);

// Step 3: Create dynamic proxy for IVehicleServiceListener (MUST NOT BE NULL)
Class<?> listenerInterface = launcherClassLoader.loadClass(
    "com.saicmotor.sdk.vehiclesettings.listener.IVehicleServiceListener"
);
Object listenerProxy = Proxy.newProxyInstance(
    launcherClassLoader,
    new Class<?>[] { listenerInterface },
    (proxy, method, args) -> {
        if ("onServiceConnected".equals(method.getName())) {
            // Save manager instance and read data
            managerInstance = args[0];
            readVehicleData();
        }
        return null;
    }
);

// Step 4: Initialize SDK with listener proxy
Method initMethod = managerClass.getMethod(
    "init",
    Context.class,
    listenerInterface,
    Long.TYPE
);
initMethod.invoke(null, context, listenerProxy, 1500L);

// Step 5: Get singleton instance
Method getInstanceMethod = managerClass.getMethod("getInstance");
Object manager = getInstanceMethod.invoke(null);

// Step 6: Get vehicle data bean
Method getStatusMethod = managerClass.getMethod("getVehicleChargingStatus");
Object chargingBean = getStatusMethod.invoke(manager);

// Step 7: Extract battery percentage (CORRECT METHOD)
Method getBatteryMethod = chargingBean.getClass().getMethod("getCurrentElectricQuantity");
Float batteryPercent = (Float) getBatteryMethod.invoke(chargingBean);
int batteryLevel = Math.round(batteryPercent); // Convert 78.5 → 79

// Step 8: Extract range in kilometers
Method getRangeMethod = chargingBean.getClass().getMethod("getCurrentEnduranceMileage");
Integer rangeKm = (Integer) getRangeMethod.invoke(chargingBean);
```

**Key SDK Classes and Methods** (from `com.saicmotor.hmi.launcher` APK):

- **VehicleChargingManager**
  - `init(Context, IVehicleServiceListener, long)` - Initialize SDK (listener MUST NOT be null)
  - `getInstance()` - Get singleton instance
  - `getVehicleChargingStatus()` - Returns VehicleChargingBean

- **VehicleChargingBean** (data container)
  - ✅ `getCurrentElectricQuantity()` → Float - Battery percentage (e.g., 78.5)
  - ✅ `getCurrentEnduranceMileage()` → Integer - Range in kilometers
  - ❌ `getElectricityLevel()` → Integer - NOT battery percentage (different metric)

- **IVehicleServiceListener** (callback interface)
  - `onServiceConnected(BaseManager)` - Called when service binds successfully
  - `onServiceDisconnected()` - Called when service unbinds

**Common Pitfalls to Avoid**:

1. ❌ Using `Class.forName()` - SDK classes not in app classloader
2. ❌ Using `ClassLoader.getSystemClassLoader()` - SDK not in system classpath
3. ❌ Passing `null` listener to `init()` - Causes NullPointerException crash
4. ❌ Using `getElectricityLevel()` for battery - Returns wrong metric (not percentage)
5. ✅ Use `createPackageContext()` to access launcher's classloader
6. ✅ Use dynamic `Proxy.newProxyInstance()` for listener interface
7. ✅ Use `getCurrentElectricQuantity()` for battery percentage

### Bluetooth Album Art Integration ✅ WORKING

**Tested on car**: 28 Jan 2026 - Album art successfully loads from Bluetooth storage

**Implementation** (MediaListenerService.java):

```java
// Bluetooth stores album art in specific directory structure
// Path: /storage/emulated/0/bluetooth/[MAC_ADDRESS]/AVRCP_BIP_IMG_*.JPEG

// Step 1: Extract URI from media metadata
String albumArtUriString = metadata.getString(MediaMetadata.METADATA_KEY_ART_URI);
if (albumArtUriString == null || albumArtUriString.isEmpty()) {
    albumArtUriString = metadata.getString(MediaMetadata.METADATA_KEY_ALBUM_ART_URI);
}

// Step 2: Decode URI (handles %20 spaces, etc.)
String decodedPath = URLDecoder.decode(albumArtUriString, "UTF-8");

// Step 3: Handle Bluetooth MAC format (colons vs encoded)
// Bluetooth paths may have "AA:BB:CC:DD:EE:FF" or "AA%3ABB%3ACC..."
decodedPath = decodedPath.replaceAll(": ", ":"); // Normalize spacing

// Step 4: Extract file path from URI
Uri albumArtUri = Uri.parse(decodedPath);
String filePath = albumArtUri.getPath(); // e.g., "/storage/emulated/0/bluetooth/..."

// Step 5: Use ContentResolver to open file (avoids permission issues)
try {
    InputStream inputStream = getContentResolver().openInputStream(albumArtUri);
    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
    inputStream.close();

    // Update UI with bitmap
    if (listener != null) {
        listener.onAlbumArtChanged(bitmap);
    }
} catch (IOException e) {
    Log.e(TAG, "Failed to load album art: " + e.getMessage());
}
```

**Required Permissions** (AndroidManifest.xml):

```xml
<!-- Bluetooth permissions -->
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />

<!-- Storage permissions for album art access -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />

<!-- Notification listener for media metadata -->
<uses-permission android:name="android.permission.BIND_NOTIFICATION_LISTENER_SERVICE" />

<!-- Legacy storage access for Android 10+ -->
<application
    android:requestLegacyExternalStorage="true">
```

**Grant Permissions via ADB** (if not auto-granted):

```bash
adb shell pm grant com.custom.launcher android.permission.READ_EXTERNAL_STORAGE
adb shell pm grant com.custom.launcher android.permission.WRITE_EXTERNAL_STORAGE
```

**Bluetooth Album Art File Structure**:

```
/storage/emulated/0/bluetooth/
├── AA_BB_CC_DD_EE_FF/          # Bluetooth MAC address (underscores)
│   ├── AVRCP_BIP_IMG_001.JPEG  # Current track album art
│   ├── AVRCP_BIP_IMG_002.JPEG  # Previous/next track art
│   └── ...
```

**Key Points**:

- Use `ContentResolver.openInputStream()` instead of direct file access
- Decode URI with `URLDecoder.decode()` to handle encoded characters
- Bluetooth MAC addresses may use colons or underscores in path
- AVRCP (Audio/Video Remote Control Profile) handles album art transfer
- BIP (Basic Imaging Profile) is the Bluetooth protocol for image transfer
- Album art updates automatically when track changes

### Build Configuration

- Gradle build system
- Min SDK: 28 (Android 9)
- Target SDK: 33
- Dependencies: AndroidX, Material Design, ConstraintLayout, CardView

### Key Files to Understand

1. **MainActivity.java** - Main launcher logic, updates UI with vehicle/media data
2. **VehicleDataService.java** - Binds to SAIC vehicle service for battery/range
3. **MediaListenerService.java** - NotificationListenerService for media tracking
4. **activity_main.xml** - Material Design layout with cards

## Environment Setup

### Required Tools

- Java JDK 17: `brew install openjdk@17`
- Android SDK tools: `brew install --cask android-commandlinetools`
- Gradle (included in project)

### Build Commands

```bash
# From the CustomLauncher directory
./gradlew assembleDebug    # Build debug APK
./gradlew assembleRelease  # Build release APK
```

### Emulator Testing

```bash
# Set correct display settings (160 dpi for MG4)
adb shell wm density 160 && adb shell wm size 1920x1080

# Install and launch
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.custom.launcher/.MainActivity

# Check logs for errors
adb logcat | grep -E "custom.launcher|AndroidRuntime"
```

### Deployment to Car

```bash
# Connect via ADB to car's Android system
adb devices
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Set as default launcher
# Settings → Apps → Default Apps → Home app → Custom Launcher
```

## Important Paths

### Firmware Directory Structure

All firmware files are located in the `mg_firmware/` directory within this repository:

- **R40**: `mg_firmware/1100 SWI69 R40 11950861 13.06.25/`
- **R46**: `mg_firmware/1300 SWI68 R46/`
- **R67**: `mg_firmware/1300 SWI68 R67/`

**Note**: The `mg_firmware/` directory is git-ignored (except for `.gitkeep`) to keep firmware files out of version control.

### Extracted APKs

- R40: `mg_firmware/1100 SWI69 R40 11950861 13.06.25/extracted_apks/EvCharge_eh32_ll.apk` ✅ (has widgets)
- R46: `mg_firmware/1300 SWI68 R46/extracted_apks/` ❌ (no EvCharge)
- R67: `mg_firmware/1300 SWI68 R67/extracted_apks/launcher_eh32_eu_P.apk` (current system launcher)

### Decompiled APKs

- EvCharge: `mg_firmware/1100 SWI69 R40 11950861 13.06.25/extracted_apks/EvCharge_eh32_ll/`
- R67 Launcher: `mg_firmware/1300 SWI68 R67/extracted_apks/launcher_eh32_eu_P/`

## Known Challenges

1. **Vehicle Service Access** ✅ SOLVED
   - ~~May require system-level permissions~~ - No special permissions needed
   - ~~APK might need platform signature~~ - Regular APK works fine
   - ~~Consider installing as system app~~ - User app installation works
   - **Solution**: Use `createPackageContext()` to access launcher's SDK classes

2. **Testing Limitations** ✅ PARTIALLY SOLVED
   - Cannot fully test vehicle data on Mac/emulator (still true)
   - Mock data included for development (fallback works well)
   - Direct deployment to car recommended (confirmed working)
   - **Debug Dialog**: Triple-tap clock to view logs on car without ADB

3. **Signature Requirements** ✅ NOT NEEDED
   - ~~System apps may need platform certificate~~ - Not required
   - ~~May need to extract signing keys~~ - Not required
   - **Confirmed**: Regular debug APK works perfectly on car

4. **New Challenge: Display Resolution** ⚠️ IMPORTANT
   - Car display is **1778×720** NOT 1920×1080 as initially documented
   - Use `layout-w1778dp/` qualifier for car-specific layouts
   - Test on car, not emulator, for accurate UI sizing

## Next Steps

### Immediate Tasks

1. Build the project: `./gradlew assembleDebug`
2. Enable ADB debugging on car
3. Connect Mac to car via USB
4. Install and test: `adb install -r app/build/outputs/apk/debug/app-debug.apk`

### Future Enhancements

- Add AIDL interface for vehicle service (if specifications available)
- Implement actual media control commands
- Add more customization options
- Extract and use actual platform keys if system permissions needed
- Consider creating widget version if launcher widgets are supported

### If Vehicle Service Fails

- Analyze logcat output: `adb logcat | grep VehicleData`
- Check available services: `adb shell service list | grep vehicle`
- May need to reverse engineer AIDL interface from stock launcher
- Alternative: Monitor system broadcasts for battery/charging events

## Display Specifications (Extracted from Firmware)

**MG4 Infotainment Display:**

- **Resolution**: 1920×1080 pixels (Full HD)
- **Density**: 160 dpi (mdpi)
- **Width**: 1778+ dp (layout-w1778dp qualifier found in R40 widgets)
- **Orientation**: Landscape (forced)
- **Aspect Ratio**: 16:9

**Widget Dimensions (from EvCharge R40):**

- 2×1 Battery Widget: 500×250 dp
- 1×1 Icon Widget: 250×250 dp

**Extracted from:**

- `vendor/build.prop`: `ro.sf.lcd_density=160`
- EvCharge layouts: `res/layout-w1778dp/`

**Android Studio AVD Config:**

- Resolution: 1920 × 1080 pixels
- Density: 160 dpi (mdpi)
- API Level: 28 (Android 9)
- Orientation: Landscape

## User's Car Details

- Model: MG4 (1100 model, older version)
- Current Firmware: R67 (1300 SWI68 R67)
- Current Launcher: `com.saicmotor.hmi.launcher` (SaicLoader)
- Has charging display showing battery % and range
- No widget support in current firmware

## Development Environment

- Platform: macOS
- Preferred IDE: VS Code (Android Studio for emulator testing)
- Build Tool: Gradle command line
- Testing: Android Automotive emulator + Direct deployment to vehicle via ADB
- Android SDK: `/Users/adam/Library/Android/sdk`

## Root Access Status ⚠️ - **UPDATED: ROOTING NOT REQUIRED**

**CRITICAL DISCOVERY (30 Jan 2026):** All MG4 system apps are signed with publicly available **AOSP platform test keys**, eliminating the need for root access for most system modifications!

### AOSP Test Keys Discovery ✅ GAME CHANGER

**Certificate Details** (verified with keytool):

- **Serial Number**: `b3998086d056cffa`
- **SHA1 Fingerprint**: `27:19:6E:38:6B:87:5E:76:AD:F7:00:E7:EA:84:E4:C6:EE:E3:3D:FA`
- **Owner**: `android@android.com`
- **Verified APKs**: launcher_eh32_eu_P.apk, vehiclesettings_eh32_eu_P.apk, hvac_eh32_eu_P.apk, systemsettings_eh32_eu_P.apk, AllgoCarplay_EH32.apk, AAP_EH32.apk

**Public Key Location:**

- AOSP Repository: `https://android.googlesource.com/platform/build/+/refs/heads/master/target/product/security/`
- Files: `platform.pk8` (private key), `platform.x509.pem` (certificate)

**Implications:**

- ✅ We can sign custom apps with system-level privileges WITHOUT root
- ✅ Can modify system apps (AllgoCarplay, launcher) and re-sign them
- ✅ Can create system-level apps with `android:sharedUserId="android.uid.system"`
- ✅ Wireless CarPlay modifications possible without root
- ⚠️ This is a **major security vulnerability** in SAIC firmware

**Documentation:**

- **AOSP Keys Discovery**: `docs/AOSP_TEST_KEYS_DISCOVERY.md` (comprehensive analysis)
- **CarPlay USB Analysis**: `docs/CARPLAY_USB_ANALYSIS.md` (how AllgoCarplay works)
- **Wireless CarPlay Feasibility**: `docs/WIRELESS_CARPLAY_FEASIBILITY.md` (implementation paths)
- **AllgoCarplay Modification**: `docs/ALLGOCARPLAY_MODIFICATION_ANALYSIS.md` (system app modification guide)

### Original Root Access Documentation (Now Optional)

**Previous understanding:** The MG4 infotainment system is NOT rooted, which restricts modifications.

**What We Previously Thought We COULDN'T Do:**

- ❌ Replace system apps → **Now possible with platform signing**
- ❌ Modify `/system` partition → **Still read-only, but can replace system apps**
- ❌ Install APKs with system signature → **Now possible with AOSP test keys**
- ❌ Access protected APIs → **Now possible with platform signing**
- ❌ Enable wireless CarPlay → **Now possible with AllgoCarplay modification**

**Current Approach (No Root Required):**

- ✅ Custom launcher runs as **user app** (still works fine)
- ✅ Uses reflection to access SAIC SDK from launcher/HVAC packages
- ✅ Can launch system activities via Intent
- ✅ **NEW**: Can create system-level apps with platform signing
- ✅ **NEW**: Can modify and re-sign system apps like AllgoCarplay

### Rooting Documentation (Optional Path):

- **Full rooting guide:** `docs/ROOTING_GUIDE.md` (if you still want root)
- **System info script:** `scripts/gather_system_info.sh`
- **Backup script:** `scripts/backup_firmware.sh`

### Next Steps with Platform Signing (Recommended):

1. Download AOSP platform keys from repository
2. Sign custom launcher with `platform.pk8` and add `android:sharedUserId="android.uid.system"`
3. Test system-level access (vehicle data, media control, settings)
4. Modify AllgoCarplay for wireless support
5. Re-sign modified AllgoCarplay with platform keys
6. Install modified system apps via ADB (no root needed)

**NOTE:** Rooting is now **optional** for most modifications. Platform signing provides system privileges without the risks of rooting.

### Phase 7: Debug Dialog Feature ✅

**Build completed**: 24 Jan 2026
**APK Location**: `app/build/outputs/apk/debug/app-debug.apk`

**Features Added:**

1. ✅ Triple-tap clock to open debug dialog
2. ✅ Live log viewer with auto-refresh (1 second intervals)
3. ✅ Filters logs for custom launcher, vehicle service, and media service
4. ✅ Clear logs button
5. ✅ Auto-scrolls to show latest logs (only when new content arrives)
6. ✅ Scrollable and selectable text
7. ✅ Maximum 500 lines retained in buffer
8. ✅ Wider dialog (1200px height) for better readability
9. ✅ Auto-retry vehicle service binding every 10 seconds if connection fails
10. ✅ Save logs to USB stick button (searches multiple mount points)

### Phase 8: Vehicle Data & Album Art Integration ✅ WORKING ON CAR

**Testing completed on car**: 28 Jan 2026
**Status**: All features working perfectly

**Discoveries and Fixes:**

1. ✅ **SAIC SDK Access Method Found**
   - SDK classes are in `com.saicmotor.hmi.launcher` APK, not system classloader
   - Use `createPackageContext()` to load launcher's classloader
   - Use reflection to access VehicleChargingManager
   - **CRITICAL**: Must provide non-null IVehicleServiceListener or app crashes
   - Solution: Dynamic `Proxy.newProxyInstance()` handles callbacks

2. ✅ **Battery Percentage Method Corrected**
   - ❌ Initial: `getElectricityLevel()` - Wrong method, returns different metric
   - ✅ Correct: `getCurrentElectricQuantity()` - Returns Float battery percentage
   - Tested on car: Shows accurate battery level

3. ✅ **Range Data Working**
   - Method: `getCurrentEnduranceMileage()` returns Integer kilometers
   - Tested on car: Shows accurate remaining range

4. ✅ **Bluetooth Album Art Working**
   - Path: `/storage/emulated/0/bluetooth/[MAC]/AVRCP_BIP_IMG_*.JPEG`
   - Use `ContentResolver.openInputStream()` to load images
   - `URLDecoder.decode()` handles URI encoding
   - Tested on car: Album art displays correctly for Bluetooth media

**Note:** Original debug modal dialog implementation was replaced with full-screen LogViewerActivity (see Phase 10).

## Build Status

### Latest Build: Car + Emulator Signed APKs ✅

**Date**: 31 Jan 2026
**Status**: Production-ready signed APKs for both car and emulator

**Build Artifacts:**

1. **Car APK** (System UID):
   - Location: `app/build/outputs/apk/car/debug/app-car-debug-signed.apk`
   - Size: ~13 MB
   - System Privileges: ✅ (android.uid.system)
   - Direct SAIC SDK access without reflection
   - Build Command: `./gradlew assembleCarDebug && ./sign_apk.sh`

2. **Emulator APK** (System UID):
   - Location: `app/build/outputs/apk/emulator/debug/app-emulator-debug-signed.apk`
   - Size: ~12 MB
   - System Privileges: ✅ (android.uid.system)
   - Mock data fallback for vehicle service
   - Build Command: `./gradlew assembleEmulatorDebug && ./sign_apk.sh emulator`

**Signing Scripts:**

- ✅ `sign_apk.sh` - Feature-rich signing script (FIXED 31 Jan 2026)
  - Supports car/emulator flavors
  - Supports debug/release build types
  - Auto-detects APK paths
  - Verifies signature after signing
  - Shows helpful error messages
  - Fixed syntax errors (unclosed quote, bash-specific syntax)

- ✅ `sign_with_platform_keys.sh` - Simple alternative signing script
  - Takes APK path as argument
  - Quick and reliable
  - Less features but always works

**Usage:**

```bash
# Build and sign car APK (default)
./gradlew assembleCarDebug && ./sign_apk.sh

# Build and sign emulator APK
./gradlew assembleEmulatorDebug && ./sign_apk.sh emulator

# Install to device
adb install -r app/build/outputs/apk/car/debug/app-car-debug-signed.apk
```

### Phase 7: Debug Dialog Feature ✅ (Superseded by Phase 10)

**Build completed**: 20 Jan 2026
**Status**: ⚠️ Replaced with full-screen LogViewerActivity (see Phase 10)

**Original Implementation:**

- Modal dialog with triple-tap activation
- Auto-refreshing logs every 1 second
- Clear button and close functionality

**Issues Encountered:**

- Modal was flaky and hard to interact with
- Scrolling behavior was unreliable
- Logs would jump to top during refresh
- Difficult to pause and read specific entries

**Resolution:**

Converted to full-screen LogViewerActivity in Phase 10 with reversed log order, eliminating all scrolling issues.

### Phase 8: Vehicle Data & Album Art Integration ✅ WORKING ON CAR

**Build completed**: 24 Jan 2026
**APK Location**: `app/build/outputs/apk/debug/app-debug.apk`

**Features Added:**

1. ✅ Battery card now opens Charge Management Activity
2. ✅ Integrated with SAIC VehicleSettings app
3. ✅ Fixed all import errors (Handler, ImageView, Context)

**Implementation Details:**

- **Target Package**: `com.saicmotor.hmi.vehiclesettings`
- **Target Activity**: `com.saicmotor.hmi.vehiclesettings.chargemanagement.ui.ChargeManagementActivity`
- **Click Handler**: Added `batteryCard.setOnClickListener()` to open the activity
- **Intent Flags**: Uses `FLAG_ACTIVITY_NEW_TASK` for proper activity launch

**Charge Management Activity Features:**

The stock SAIC ChargeManagementActivity includes three tabs:

1. **Charging Management** - Charge scheduling, SOC limits, charging lock
2. **Discharging Management** - V2L (Vehicle-to-Load) settings
3. **Energy** - Energy flow visualization

**Code Changes:**

```java
// Added in MainActivity.java onCreate()
batteryCard.setOnClickListener(v -> {
    openChargeManagement();
});

// New method to launch SAIC Charge Management
private void openChargeManagement() {
    try {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(
            "com.saicmotor.hmi.vehiclesettings",
            "com.saicmotor.hmi.vehiclesettings.chargemanagement.ui.ChargeManagementActivity"
        ));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Log.d(TAG, "Opening Charge Management Activity");
    } catch (Exception e) {
        Log.e(TAG, "Failed to open Charge Management: " + e.getMessage());
    }
}
```

**Testing:**

- ✅ App compiles successfully
- ✅ APK installs on device
- ✅ Launcher starts without errors
- ⏳ Awaiting on-car testing to verify Charge Management opens correctly

**Next Steps:**

1. Test on actual MG4 vehicle
2. Verify battery card click opens Charge Management Activity
3. Confirm all three tabs are accessible in the opened activity
4. Test back navigation returns to custom launcher

### Phase 9: Heating Controls Integration ✅ WORKING

**Date**: 30 Jan 2026
**Status**: Fully functional with SAIC SDK integration

**Implementation:**

Created HeatingControlService to interface with SAIC AirConditionManager for heated seats and steering wheel control. The service uses reflection to access the SDK classes from the HVAC app package.

**Key Features:**

1. ✅ **Bidirectional Communication**
   - Send heating commands to vehicle
   - Receive status updates from vehicle
   - Automatic UI sync when vehicle changes heating levels

2. ✅ **Driver & Passenger Seat Heating**
   - 4 levels: OFF → Level 3 (High) → Level 2 (Med) → Level 1 (Low) → OFF
   - Independent control for left and right seats
   - Visual feedback with different icon states

3. ✅ **Steering Wheel Heating**
   - Binary control: OFF ↔ ON
   - Visual feedback with on/off icons

4. ✅ **Vehicle Auto-Adjustment Tracking**
   - Car automatically reduces seat heating after time
   - Callback system updates UI to match actual vehicle state
   - No desync between UI and vehicle

**SAIC SDK Methods Used:**

```java
// From AirConditionManager
setDrvSeatHeatLevel(int level)      // Set driver seat (0-3)
setPsgSeatHeatLevel(int level)      // Set passenger seat (0-3)
setSteeringWheelHeat(int level)     // Set steering wheel (0-1)

// From AirConditionBean (via callback)
getDrvSeatHeatLevel()               // Get driver seat current level
getPsgSeatHeatLevel()               // Get passenger seat current level
getSteeringWheelHeatLevel()         // Get steering wheel current state
```

**Initialization Pattern:**

```java
// Load SDK from HVAC app package
Context hvacContext = context.createPackageContext(
    "com.saicmotor.hmi.hvac",
    Context.CONTEXT_INCLUDE_CODE
);
ClassLoader hvacClassLoader = hvacContext.getClassLoader();

// Load AirConditionManager
Class<?> managerClass = hvacClassLoader.loadClass(
    "com.saicmotor.sdk.vehiclesettings.manager.AirConditionManager"
);

// Initialize with listener for connection callbacks
Method initMethod = managerClass.getMethod(
    "init",
    Context.class,
    IVehicleServiceListener.class,
    Long.TYPE
);
initMethod.invoke(null, context, listenerProxy, 1500L);

// Register callback for status updates
Method registerMethod = managerInstance.getClass().getMethod(
    "registerAirConditionCallback",
    IAirConditionCallback.class
);
registerMethod.invoke(managerInstance, callbackProxy);
```

**Files Modified:**

1. **HeatingControlService.java** (NEW)
   - Manages connection to AirConditionManager
   - Sends heating commands to vehicle
   - Receives status updates via callbacks
   - Similar architecture to VehicleDataService

2. **MainActivity.java**
   - Added HeatingControlService initialization
   - Connected toggle buttons to service methods
   - Added listener for vehicle status updates
   - Removed placeholder sendHeatingCommand()
   - UI automatically updates when vehicle changes state

**Testing Notes:**

- Service connects successfully to HVAC system
- Heating commands sent via reflection work correctly
- Callback system receives status updates from vehicle
- UI stays in sync even when car auto-adjusts heating levels
- No permission issues (uses same pattern as VehicleDataService)

**Technical Challenges Solved:**

1. ✅ Found correct SDK classes in HVAC app (not system)
2. ✅ Implemented callback proxy for bidirectional communication
3. ✅ Handled reflection for all SDK method calls
4. ✅ Synchronized UI updates on main thread from callbacks
5. ✅ Proper resource cleanup in onDestroy()

**Known Behavior:**

- Vehicle automatically reduces seat heating after ~30 minutes for safety
- HeatingControlService callback detects this and updates UI
- This is expected vehicle behavior, not a bug

### Phase 10: Log Viewer Full-Screen Conversion ✅ COMPLETED

**Date**: 31 Jan 2026
**Status**: Fully functional with reversed log order

**Problem:**

Original debug logs were displayed in a modal dialog that had multiple issues:

- Modal was flaky and hard to interact with
- Scrolling was difficult and unreliable
- Logs would randomly jump to top during auto-refresh
- No way to pause log streaming when reading specific entries

**Solution Iterations:**

1. **Attempt 1**: Converted modal to full-screen `LogViewerActivity`
   - Created `activity_log_viewer.xml` with ScrollView
   - Added pause/resume button to stop refresh temporarily
   - **Issue**: Scroll jumping persisted during TextView updates

2. **Attempt 2**: Implemented scroll position tracking
   - Used ScrollView listeners to detect user scroll position
   - Preserved scroll position during TextView updates
   - **Issue**: Race conditions between scroll tracking and text updates

3. **Attempt 3**: Smart auto-scroll detection
   - Only auto-scroll when user is at bottom
   - Complex logic to determine if user is reading older logs
   - **Issue**: Still had edge cases causing jumps

4. **Final Solution**: Reversed log order (newest at top) ✅
   - Split logs by newline, reverse array, rebuild string
   - Newest logs always appear at top (default scroll position)
   - Eliminated ALL scrolling logic complexity
   - Removed scroll listeners and position tracking
   - Simple and reliable solution

**Implementation Details:**

- **New Files:**
  - `LogViewerActivity.java` - Full-screen log viewer activity
  - `activity_log_viewer.xml` - Layout with header buttons and scrollable log text

- **Key Features:**
  1. ✅ Full-screen activity (replaces modal dialog)
  2. ✅ Pause/resume button (yellow when paused, green when running)
  3. ✅ Auto-refresh every 1 second when not paused
  4. ✅ Reversed log order (newest at top)
  5. ✅ Clear logs button
  6. ✅ Refresh logs button (force immediate update)
  7. ✅ Save logs to USB button
  8. ✅ Close button returns to launcher
  9. ✅ Only updates TextView when content actually changes

- **Log Reversal Code:**

  ```java
  // Split logs into lines, reverse order, rebuild
  String[] lines = logText.split("\n");
  StringBuilder reversed = new StringBuilder();
  for (int i = lines.length - 1; i >= 0; i--) {
      reversed.append(lines[i]).append("\n");
  }
  String reversedLog = reversed.toString();
  ```

- **Activation:** Triple-tap the clock widget in main launcher

- **Log Filters:** Shows only relevant logs:
  - `CustomLauncher:V` - Main launcher logs
  - `VehicleDataService:V` - Vehicle service binding logs
  - `HeatingControlService:V` - Heating control service logs
  - `MediaListenerService:V` - Media service logs
  - `AndroidRuntime:E` - Critical errors

**Testing:**

- ✅ Activity opens smoothly from triple-tap
- ✅ No scroll jumping issues (logs stay stable)
- ✅ Pause/resume button works correctly
- ✅ Color feedback (yellow/green) is clear
- ✅ Clear button wipes logs as expected
- ✅ Save to USB searches multiple mount points
- ✅ Close button properly stops log reader and cleans up

**Lessons Learned:**

Sometimes the simplest solution (reversing order) is better than complex scroll tracking logic. By putting newest logs at top, we eliminated the need for any scroll management code entirely.

## Resources

- OTA extraction tools installed: `payload-dumper-go`, `p7zip`, `apktool`, `jadx`
- All firmware packages available in workspace
- Stock launcher decompiled and analyzed
- Custom launcher built and tested on emulator
- **Wireless CarPlay Bridge project created** - See `WirelessCarPlayBridge/` directory

### Wireless CarPlay Implementation (NEW - 31 Jan 2026)

**Project Location**: `WirelessCarPlayBridge/`

**Purpose**: Software-based wireless CarPlay adapter for MG4

**Status**: ✅ Built and ready for testing on car

**How It Works**:

1. **BLE Advertisement**: Broadcasts CarPlay capability (UUID: 0x0000FD8F)
2. **WiFi Direct**: Establishes P2P connection with iPhone (5 GHz)
3. **Virtual USB Device**: Creates USB gadget that AllgoCarplay sees as "wired iPhone"
4. **Data Bridge**: Routes WiFi packets through virtual USB interface

**Build & Install**:

```bash
cd WirelessCarPlayBridge
./gradlew assembleDebug && ./sign_apk.sh
adb install -r app/build/outputs/apk/debug/app-debug-signed.apk
```

**Key Files**:

- `WirelessCarPlayBridgeService.java` - Main service coordinator
- `CarPlayBleAdvertiser.java` - BLE advertisement (announces CarPlay)
- `WiFiDirectManager.java` - WiFi P2P connection management
- `UsbGadgetController.java` - Virtual USB device creation (Linux ConfigFS)
- `DataBridge.java` - Bidirectional WiFi ↔ USB packet routing

**Requirements**:

- ✅ Bluetooth LE 4.0+ (MG4 R67 confirmed)
- ✅ System-level permissions (android.uid.system via platform signing)
- ❓ WiFi 5 GHz with P2P support (need to verify on car)
- ❓ Kernel CONFIG_USB_CONFIGFS (need to check: `adb shell ls /config/usb_gadget`)

**Success Probability**: 40-60% (depends on USB Gadget kernel support)

**Fallback**: If USB Gadget doesn't work, recommend Carlinkit 3.0 adapter ($80-120, 95% success)

**Documentation**:

- `WirelessCarPlayBridge/README.md` - Complete technical documentation
- `WirelessCarPlayBridge/QUICKSTART.md` - Quick start guide
- `WirelessCarPlayBridge/sign_apk.sh` - Automated APK signing with AOSP keys

### Documentation Index

#### Custom Launcher Documentation:

- **Main README**: `README.md` - Project overview and build instructions
- **Agent Context**: `AGENTS.md` - This file, for session resumption

#### CarPlay & Wireless Modifications:

- **CarPlay USB Analysis**: `docs/CARPLAY_USB_ANALYSIS.md`
  - How AllgoCarplay interfaces with USB ports
  - AOAP (Android Open Accessory Protocol) architecture
  - USB communication flow and SAIC SDK integration
- **Wireless CarPlay Feasibility**: `docs/WIRELESS_CARPLAY_FEASIBILITY.md`
  - Three implementation paths (software mod, adapter, hybrid)
  - Hardware requirements (Bluetooth LE + WiFi Direct 5 GHz)
  - Cost analysis and recommendations
- **AllgoCarplay Modification Analysis**: `docs/ALLGOCARPLAY_MODIFICATION_ANALYSIS.md`
  - System app location: `/system/app/AllgoCarplay_EH32/` (corrected)
  - sharedUserId requirements
  - Platform signing requirements

#### Platform Signing & Security:

- **AOSP Test Keys Discovery**: `docs/AOSP_TEST_KEYS_DISCOVERY.md` ⭐ **CRITICAL**
  - Certificate verification results (serial: b3998086d056cffa)
  - Security implications (major vulnerability)
  - Step-by-step signing instructions
  - System-level app creation guide

#### Rooting Documentation (Optional):

- **Rooting Guide**: `docs/ROOTING_GUIDE.md`
  - Comprehensive rooting procedures
  - Magisk method, bootloader unlocking
  - Recovery plans and backup procedures
  - **Note**: Now optional due to AOSP keys discovery

#### Utility Scripts:

- **scripts/README.md** - Script documentation
- **scripts/gather_system_info.sh** - Collects system info via ADB
- **scripts/backup_firmware.sh** - Creates partition backups
