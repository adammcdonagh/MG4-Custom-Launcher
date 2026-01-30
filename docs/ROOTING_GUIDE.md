# MG4 Firmware Rooting Guide - Gaining System Access

## Date: 30 January 2026

---

## Executive Summary

**Current Problem:** Without root access, you cannot:

- ❌ Replace system apps (launcher, CarPlay, vehicle services)
- ❌ Modify `/system` partition (read-only)
- ❌ Install APKs with system signature
- ❌ Access protected APIs and services
- ❌ Enable ADB as root (`adb shell` runs as `shell` user, not `root`)
- ❌ Modify framework files (`/system/framework/*.jar`)

**Solution:** Root the MG4's Android Automotive OS to gain full system control.

**Difficulty:** 🔴 **HIGH** (9/10)  
**Risk Level:** 🔴 **HIGH** - Potential to brick the infotainment system  
**Estimated Time:** 4-20 hours (depending on bootloader status)  
**Reversibility:** Possible with proper backups

---

## Understanding the Current Limitations

### What You Can Do NOW (Without Root):

```bash
# Current ADB access level
adb shell whoami
# Output: shell (UID 2000, limited permissions)

# Try to remount system as read-write
adb shell mount -o remount,rw /system
# Output: mount: Permission denied (you're not root)

# Try to install system app
adb install -r --user 0 custom-launcher.apk
# Output: Works, but runs as user app (no system permissions)

# Try to replace system app
adb push custom-launcher.apk /system/app/launcher/
# Output: Permission denied (read-only filesystem)
```

### What You NEED Root For:

1. **Replace Stock Launcher:**

   ```bash
   # Requires root to replace:
   /system/app/launcher_eh32_eu_P/launcher_eh32_eu_P.apk
   ```

2. **Modify AllgoCarplay for Wireless Support:**

   ```bash
   # Requires root to replace:
   /system/app/AllgoCarplay_EH32/AllgoCarplay_EH32.apk
   ```

3. **Install Custom SAIC SDK:**

   ```bash
   # Requires root to access:
   /system/framework/com.saicmotor.sdk.jar
   ```

4. **Enable Hidden Features:**

   ```bash
   # Requires root to modify:
   /system/build.prop
   /vendor/build.prop
   ```

5. **Disable OTA Updates (Optional):**
   ```bash
   # Requires root to disable:
   /system/app/SaicUpdate_overseas_eh32/SaicUpdate_overseas_eh32.apk
   ```

---

## Rooting Methods for Android Automotive

### Method 1: Magisk (Systemless Root) - RECOMMENDED

**Magisk** is the modern rooting solution that doesn't modify `/system` directly.

**Advantages:**

- ✅ Systemless (can pass SafetyNet checks)
- ✅ Modular (install/uninstall root easily)
- ✅ OTA-friendly (can survive updates with patch)
- ✅ Most documented method

**Requirements:**

- Unlocked bootloader
- Custom recovery (TWRP) OR ability to patch boot.img
- USB access to car's head unit
- Fastboot tools

#### Step 1: Check Bootloader Status

```bash
# Connect via ADB
adb shell getprop ro.boot.verifiedbootstate
# Possible outputs:
# - "green"  → Bootloader locked, stock firmware ✅ (default)
# - "orange" → Bootloader unlocked, custom firmware ⚠️
# - "red"    → Verification failed, invalid signature ❌

# Check if bootloader can be unlocked
adb shell getprop ro.oem_unlock_supported
# Output: 1 (true) or 0 (false)

# Check current lock state
adb shell getprop ro.boot.flash.locked
# Output: 1 (locked) or 0 (unlocked)
```

#### Step 2: Enable Developer Options & OEM Unlock

**On the car's head unit (if accessible via touchscreen):**

1. Go to **Settings → About**
2. Tap **Build Number** 7 times
3. Developer Options should appear in Settings
4. Enable **OEM Unlocking** (if option exists)
5. Enable **USB Debugging** (should already be on)

**If no touchscreen access to settings:**

```bash
# Enable OEM unlock via ADB
adb shell settings put global oem_unlock_enabled 1

# Verify
adb shell settings get global oem_unlock_enabled
# Output: 1 (enabled)
```

#### Step 3: Boot to Fastboot/Bootloader

```bash
# Method 1: Via ADB
adb reboot bootloader

# Method 2: Hardware buttons (varies by head unit)
# - Power off the car
# - Hold Volume Down + Power button (check specific model)
# - Or check service manual for fastboot key combination
```

**WARNING:** ⚠️ Some automotive systems don't have fastboot mode accessible via software commands. You may need to:

- Use **UART/serial console** access
- Use **JTAG/SWD debugger**
- Access **factory test points** on the PCB

#### Step 4: Unlock Bootloader

```bash
# In fastboot mode
fastboot devices
# Should show your device

# Unlock bootloader (THIS WILL WIPE DATA!)
fastboot oem unlock
# Or on some devices:
fastboot flashing unlock

# Confirm on screen (if prompted)
# Device will reboot and factory reset
```

**⚠️ CRITICAL WARNINGS:**

- 🔴 **This WIPES all user data** (not a big issue for car head unit)
- 🔴 **May trigger anti-theft protection** (car won't start until code entered)
- 🔴 **May void warranty**
- 🔴 **May disable some features** (paid services, map updates)

#### Step 5: Extract boot.img

**Option A: From OTA Package (You Already Have This!)**

```bash
# Navigate to firmware directory
cd "/Users/adam/Downloads/MG4/CustomLauncher/mg_firmware/1300 SWI68 R67/extracted_payload"

# Check if boot.img exists
ls -lh boot.img

# If not extracted yet, extract from payload:
cd "/Users/adam/Downloads/MG4/CustomLauncher/mg_firmware/1300 SWI68 R67"
payload-dumper-go payload.bin
# This should create boot.img
```

**Option B: Pull from Device**

```bash
# Find boot partition
adb shell ls -l /dev/block/platform/*/by-name/ | grep boot

# Example output:
# lrwxrwxrwx 1 root root 20 boot -> /dev/block/mmcblk0p12

# Dump boot partition
adb shell su -c "dd if=/dev/block/mmcblk0p12 of=/sdcard/boot.img"
adb pull /sdcard/boot.img boot_original.img
```

#### Step 6: Patch boot.img with Magisk

**On your Mac:**

1. **Download Magisk APK:**

   ```bash
   # Latest version (as of Jan 2026)
   wget https://github.com/topjohnwu/Magisk/releases/download/v27.0/Magisk-v27.0.apk
   ```

2. **Install Magisk on an Android phone OR use Magisk on Mac:**

   ```bash
   # Option 1: Use Android phone
   # - Install Magisk.apk on any Android phone
   # - Transfer boot.img to phone
   # - Open Magisk app → Install → Select and Patch File
   # - Select boot.img
   # - Magisk will create magisk_patched_[random].img
   # - Transfer patched image back to Mac

   # Option 2: Use magiskboot on Mac (requires compilation)
   # Clone Magisk repo and compile magiskboot for macOS
   git clone https://github.com/topjohnwu/Magisk.git
   cd Magisk
   ./build.py binary
   ```

3. **Patch boot.img:**

   ```bash
   # If using Android phone method, you'll get:
   # magisk_patched_[random].img

   # Rename for clarity
   mv magisk_patched_*.img boot_magisk.img
   ```

#### Step 7: Flash Patched Boot Image

```bash
# Reboot to fastboot
adb reboot bootloader

# Flash patched boot
fastboot flash boot boot_magisk.img

# Reboot
fastboot reboot

# Device should boot with Magisk root
```

#### Step 8: Verify Root Access

```bash
# Check if Magisk is active
adb shell su -c "whoami"
# Output: root (SUCCESS!)

# Check Magisk version
adb shell su -c "magisk -v"
# Output: 27.0 (or your version)

# Test root mount
adb shell su -c "mount -o remount,rw /system"
# No error = Success!
```

---

### Method 2: SuperSU (Legacy Root)

**Not recommended** - SuperSU is outdated and no longer maintained. Use Magisk instead.

---

### Method 3: Direct System Modification (No Root Binary)

If you can't install Magisk but have boot partition access:

#### Option A: Modify default.prop in boot.img

```bash
# Extract boot.img
mkdir boot_extracted
cd boot_extracted
unpackbootimg -i ../boot.img

# Extract ramdisk
mkdir ramdisk
cd ramdisk
gunzip -c ../boot.img-ramdisk.gz | cpio -i

# Edit default.prop
nano default.prop

# Add these lines:
ro.debuggable=1
ro.secure=0
persist.service.adb.enable=1
persist.service.debuggable=1
persist.sys.usb.config=mtp,adb

# Repack ramdisk
find . | cpio -o -H newc | gzip > ../ramdisk_modified.gz

# Repack boot.img
mkbootimg \
  --kernel ../boot.img-zImage \
  --ramdisk ../ramdisk_modified.gz \
  --cmdline "$(cat ../boot.img-cmdline)" \
  --base 0x00000000 \
  --pagesize 2048 \
  --o ../boot_rooted.img

# Flash
fastboot flash boot boot_rooted.img
fastboot reboot
```

#### Option B: Replace su Binary in System Image

```bash
# Extract system.img
cd "/Users/adam/Downloads/MG4/CustomLauncher/mg_firmware/1300 SWI68 R67/extracted_payload"

# Mount system.img (requires ext4 tools)
mkdir system_mount
sudo mount -t ext4 -o loop system.img system_mount/

# Add su binary
sudo cp /path/to/su system_mount/xbin/su
sudo chmod 06755 system_mount/xbin/su

# Add Superuser.apk (if using SuperSU)
sudo cp Superuser.apk system_mount/app/Superuser/Superuser.apk

# Unmount
sudo umount system_mount

# Flash modified system
fastboot flash system system.img
```

---

## What If Bootloader Can't Be Unlocked?

Some automotive OEMs **lock the bootloader permanently** for security. If `fastboot oem unlock` fails:

### Alternative 1: Exploit-Based Root

Look for known exploits for your SoC:

**MediaTek-based systems:**

```bash
# Check SoC
adb shell getprop ro.board.platform
# Example: mt8666, mt6771, etc.

# Known MediaTek exploits:
# - MTK-SU (works on many MediaTek chips)
# - mtk-bypass (bootloader unlock bypass)
```

**Qualcomm-based systems:**

```bash
# Check SoC
adb shell getprop ro.board.platform
# Example: msm8953, sdm660, etc.

# Known Qualcomm exploits:
# - EDL mode (Emergency Download Mode)
# - FireHose programmer
```

### Alternative 2: Hardware-Level Access

**UART/Serial Console:**

```
1. Open the head unit case
2. Locate UART test points (TX, RX, GND)
3. Connect USB-to-TTL adapter
4. Access U-Boot bootloader
5. Modify boot arguments to enable root
```

**JTAG/SWD Debugging:**

```
1. Locate JTAG/SWD test points
2. Connect debugger (e.g., J-Link, ST-Link)
3. Dump/modify flash directly
4. Bypass security checks
```

### Alternative 3: Physical Chip Programmer

**Last resort - requires desoldering eMMC chip:**

```
1. Desolder eMMC storage chip
2. Connect to chip programmer (e.g., RT809H)
3. Read entire flash
4. Modify system partition
5. Write back to chip
6. Resolder to board
```

**⚠️ Risk:** Permanent brick if done incorrectly.

---

## After Rooting: What You Can Do

### 1. Install Custom Launcher as System App

```bash
# Build your custom launcher
cd /Users/adam/Downloads/MG4/CustomLauncher
./gradlew assembleDebug

# Push to device
adb push app/build/outputs/apk/debug/app-debug.apk /sdcard/

# Install as system app (requires root)
adb shell su -c "mount -o remount,rw /system"
adb shell su -c "mkdir -p /system/app/CustomLauncher"
adb shell su -c "cp /sdcard/app-debug.apk /system/app/CustomLauncher/CustomLauncher.apk"
adb shell su -c "chmod 644 /system/app/CustomLauncher/CustomLauncher.apk"
adb shell su -c "chown system:system /system/app/CustomLauncher/CustomLauncher.apk"

# Set as default launcher
adb shell su -c "pm disable com.saicmotor.hmi.launcher"
adb shell su -c "pm enable com.custom.launcher"

# Reboot
adb shell su -c "reboot"
```

### 2. Modify AllgoCarplay for Wireless Support

```bash
# Backup original
adb shell su -c "cp /system/app/AllgoCarplay_EH32/AllgoCarplay_EH32.apk /sdcard/AllgoCarplay_backup.apk"

# Push modified APK (after reverse engineering)
adb push AllgoCarplay_wireless.apk /sdcard/

# Replace system app
adb shell su -c "mount -o remount,rw /system"
adb shell su -c "cp /sdcard/AllgoCarplay_wireless.apk /system/app/AllgoCarplay_EH32/AllgoCarplay_EH32.apk"
adb shell su -c "chmod 644 /system/app/AllgoCarplay_EH32/AllgoCarplay_EH32.apk"

# Clear cache and reboot
adb shell su -c "pm clear com.allgo.carplay.service"
adb shell su -c "reboot"
```

### 3. Enable Hidden Features

```bash
# Edit build.prop
adb shell su -c "mount -o remount,rw /system"
adb shell su -c "echo 'persist.sys.wireless.carplay=1' >> /system/build.prop"
adb shell su -c "echo 'ro.debuggable=1' >> /system/build.prop"

# Edit vendor build.prop
adb shell su -c "mount -o remount,rw /vendor"
adb shell su -c "echo 'persist.vendor.carplay.wireless=1' >> /vendor/build.prop"

# Reboot
adb shell su -c "reboot"
```

### 4. Install Magisk Modules

```bash
# Example: Install systemless hosts file blocker
adb push systemless-hosts-v1.2.zip /sdcard/

# Install via Magisk
adb shell su -c "magisk --install-module /sdcard/systemless-hosts-v1.2.zip"

# Reboot
adb shell su -c "reboot"
```

### 5. Disable OTA Updates (Prevent Losing Root)

```bash
# Disable SAIC update service
adb shell su -c "pm disable com.saicmotor.hmi.saicupdate"

# Remove update APK (backup first!)
adb shell su -c "cp /system/app/SaicUpdate_overseas_eh32/SaicUpdate_overseas_eh32.apk /sdcard/"
adb shell su -c "mount -o remount,rw /system"
adb shell su -c "rm /system/app/SaicUpdate_overseas_eh32/SaicUpdate_overseas_eh32.apk"

# Block update server (optional)
adb shell su -c "echo '127.0.0.1 ota.saicmotor.com' >> /system/etc/hosts"
```

---

## Backup Strategy (CRITICAL!)

**Before attempting root, backup EVERYTHING:**

### Backup 1: Full Device Partitions

```bash
# List all partitions
adb shell su -c "ls -l /dev/block/platform/*/by-name/"

# Backup critical partitions
for partition in boot recovery system vendor; do
  adb shell su -c "dd if=/dev/block/by-name/$partition of=/sdcard/${partition}.img"
  adb pull /sdcard/${partition}.img backup/${partition}.img
done

# Compress backups
cd backup
tar -czf mg4_firmware_backup_$(date +%Y%m%d).tar.gz *.img
```

### Backup 2: System Apps

```bash
# Backup all system apps
adb shell su -c "tar -czf /sdcard/system_apps_backup.tar.gz /system/app"
adb pull /sdcard/system_apps_backup.tar.gz
```

### Backup 3: Critical Data

```bash
# Backup settings database
adb pull /data/system/users/0/settings_global.xml
adb pull /data/system/users/0/settings_secure.xml
adb pull /data/system/users/0/settings_system.xml

# Backup SAIC vehicle data
adb pull /data/data/com.saicmotor.hmi.vehicleservice/
```

---

## Recovery Plan

### If Device Won't Boot:

**Method 1: Fastboot Restore**

```bash
# Boot to fastboot
# (Hardware button combination or UART console)

# Flash original boot.img
fastboot flash boot boot_original.img

# Flash original system.img (if modified)
fastboot flash system system_original.img

# Reboot
fastboot reboot
```

**Method 2: EDL Mode (Qualcomm only)**

```bash
# Boot to EDL mode
# (Hardware button combination or UART console)

# Use QFIL or similar tool to flash complete firmware
# (Requires OEM flash files and firehose programmer)
```

**Method 3: UART Rescue**

```
1. Connect UART adapter
2. Access U-Boot console
3. Manually boot from backup partition
4. Or reflash from external SD card
```

---

## Risks and Mitigation

| Risk                         | Likelihood | Impact   | Mitigation                                |
| ---------------------------- | ---------- | -------- | ----------------------------------------- |
| **Brick device**             | Medium     | CRITICAL | Keep full backups, test in fastboot first |
| **Void warranty**            | High       | Medium   | May need to unroot before service         |
| **Disable anti-theft**       | Medium     | High     | Research specific vehicle first           |
| **Break OTA updates**        | High       | Low      | Disable updates or manually patch Magisk  |
| **Lose paid features**       | Low        | Medium   | May lose iSMART, map updates, etc.        |
| **Security vulnerabilities** | Medium     | Medium   | Keep Magisk updated, use secure apps only |

---

## Specific to MG4: Research Needed

### Questions to Answer:

1. **What SoC does MG4 use?**

   ```bash
   adb shell getprop ro.board.platform
   adb shell getprop ro.product.board
   adb shell cat /proc/cpuinfo
   ```

2. **Is bootloader unlockable?**

   ```bash
   adb shell getprop ro.oem_unlock_supported
   ```

3. **Are there hardware buttons for bootloader?**
   - Check service manual
   - MG forums/communities
   - YouTube teardown videos

4. **Where are UART test points?**
   - Disassemble head unit
   - Look for TX/RX/GND pins
   - Check PCB silkscreen labels

5. **Has anyone else rooted MG4?**
   - Search: "MG4 root"
   - Search: "MG ZS root" (similar platform)
   - Check XDA Developers forums
   - Check Chinese automotive forums (translated)

---

## Community Resources

### Forums to Check:

1. **XDA Developers**
   - Search: MG4, SAIC, Android Automotive root
   - Automotive section

2. **Reddit:**
   - r/AndroidAuto
   - r/CarPlay
   - r/MG4
   - r/AndroidAutoTesting

3. **Chinese Forums (may need translation):**
   - SAIC forums
   - Baidu Tieba (百度贴吧)
   - Zhihu (知乎)

4. **GitHub:**
   - Search: MG4 root, SAIC root
   - Magisk modules for automotive

---

## Next Steps

### Phase 1: Information Gathering (DO THIS FIRST)

```bash
# Run on your car via ADB
adb shell getprop > mg4_properties.txt
adb shell cat /proc/cpuinfo > mg4_cpuinfo.txt
adb shell mount > mg4_mounts.txt
adb shell df -h > mg4_storage.txt
adb shell ls -lR /dev/block/platform/*/by-name/ > mg4_partitions.txt

# Pull to your Mac
adb pull mg4_properties.txt
adb pull mg4_cpuinfo.txt
adb pull mg4_mounts.txt
adb pull mg4_storage.txt
adb pull mg4_partitions.txt
```

**Share these files, and I can:**

- Identify exact SoC model
- Find known exploits
- Determine root method feasibility
- Create custom rooting procedure

### Phase 2: Backup Everything

```bash
# Backup critical partitions (requires root OR fastboot)
# See "Backup Strategy" section above
```

### Phase 3: Attempt Root

**Based on Phase 1 findings:**

- If bootloader unlockable → Magisk method
- If exploit available → Exploit-based root
- If neither → Hardware method (UART/JTAG)

### Phase 4: Test Root

```bash
# Verify root works
adb shell su -c "whoami"
# Output: root

# Test system modification
adb shell su -c "mount -o remount,rw /system"
adb shell su -c "touch /system/test_root"
adb shell su -c "ls -l /system/test_root"
adb shell su -c "rm /system/test_root"
```

### Phase 5: Install Custom Launcher

See "After Rooting" section above.

---

## Tools You'll Need

### On Your Mac:

```bash
# Android SDK Platform Tools (you already have)
which adb
which fastboot

# Payload dumper (you already have)
which payload-dumper-go

# Boot image tools
brew install android-tools  # Includes mkbootimg, unpackbootimg

# File transfer
brew install android-file-transfer
```

### On Android Phone (for Magisk patching):

- Magisk Manager app
- File manager (to transfer boot.img)

### Optional Hardware:

- **USB-to-TTL adapter** (~$5-10)
  - For UART console access
  - CP2102, CH340G, or FTDI-based

- **JTAG/SWD debugger** (~$50-200)
  - J-Link EDU Mini (~$50)
  - ST-Link V2 (~$20)

- **eMMC chip programmer** (~$100-300)
  - Last resort for direct chip access
  - RT809H, EasyJTAG, etc.

---

## Legal and Warranty Considerations

⚠️ **DISCLAIMER:**

- Rooting **WILL void your warranty** on the infotainment system
- May violate **DMCA/anti-circumvention laws** in some jurisdictions
- Could affect **vehicle resale value**
- **May trigger anti-theft systems** (car won't start)
- SAIC may **refuse service** on rooted systems
- **Insurance implications** if system modifications cause accident

**Recommendations:**

1. Research your local laws
2. Consider impact on warranty
3. Have recovery plan ready
4. Start with non-critical test device first (if possible)
5. Document everything for potential warranty restoration

---

## Conclusion

**Rooting is NECESSARY for:**

- ✅ Installing custom launcher as system app
- ✅ Modifying CarPlay/Android Auto
- ✅ Accessing SAIC SDK internals
- ✅ Enabling hidden features

**Rooting is RISKY because:**

- ❌ Can brick the device
- ❌ Voids warranty
- ❌ Requires technical expertise
- ❌ May disable vehicle features

**My Recommendation:**

1. **First: Run information gathering commands** (Phase 1)
   - This is safe and reversible
   - Provides data needed for planning

2. **Research community** for MG4-specific guides
   - Someone may have already done this
   - Learn from others' mistakes

3. **Start with Magisk method IF:**
   - Bootloader is unlockable
   - You have full backups
   - You're comfortable with fastboot

4. **Consider alternatives IF:**
   - Too risky for daily driver vehicle
   - Warranty still important
   - Wireless CarPlay adapter sufficient for now

**I'm ready to help you through each step!** Just run the Phase 1 commands and share the output files. We'll analyze them together and create a custom rooting plan for your specific MG4 model.

---

**Would you like me to:**

1. Help you run the information gathering commands?
2. Analyze your car's hardware to determine best root method?
3. Create a custom root script based on your findings?
4. Write a recovery plan for emergency situations?

Let's do this! 🔓🚗

---

**Document Created:** 30 January 2026  
**Target Device:** MG4 R67 (1300 SWI68 R67)  
**Android Version:** Android 9 (API 28)
