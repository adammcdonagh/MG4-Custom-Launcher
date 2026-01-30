#!/bin/bash
# MG4 Firmware Backup Script
# Creates backups of critical partitions and system files
# REQUIRES ROOT ACCESS (run after rooting)

set -e

echo "=========================================="
echo "MG4 Firmware Backup Tool"
echo "=========================================="
echo ""

# Check if device is connected
echo "[1/3] Checking ADB connection..."
if ! adb devices | grep -q "device$"; then
    echo "ERROR: No device connected via ADB"
    exit 1
fi
echo "✓ Device connected"
echo ""

# Check if we have root
echo "[2/3] Checking root access..."
ROOT_CHECK=$(adb shell "su -c 'whoami' 2>&1" || echo "no_root")
if [[ "$ROOT_CHECK" != *"root"* ]]; then
    echo "WARNING: Root access not available"
    echo "This script requires root to backup system partitions"
    echo ""
    echo "You can still backup some user data without root."
    read -p "Continue without root? (y/N) " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
    HAS_ROOT=false
else
    echo "✓ Root access confirmed"
    HAS_ROOT=true
fi
echo ""

# Create backup directory
BACKUP_DIR="mg4_backup_$(date +%Y%m%d_%H%M%S)"
mkdir -p "$BACKUP_DIR"
cd "$BACKUP_DIR"

echo "[3/3] Starting backup process..."
echo ""

if [ "$HAS_ROOT" = true ]; then
    # ROOTED DEVICE - Full backup
    
    echo "--- Backing up partition layout ---"
    adb shell "su -c 'ls -l /dev/block/platform/*/by-name/'" > partition_layout.txt 2>/dev/null || \
    adb shell "su -c 'ls -l /dev/block/by-name/'" > partition_layout.txt
    echo "✓ Partition layout saved"
    
    echo ""
    echo "--- Backing up critical partitions ---"
    echo "This may take 10-30 minutes depending on partition sizes..."
    echo ""
    
    # Find partition paths
    BOOT_PARTITION=$(adb shell "su -c 'readlink -f /dev/block/by-name/boot'" | tr -d '\r')
    RECOVERY_PARTITION=$(adb shell "su -c 'readlink -f /dev/block/by-name/recovery'" 2>/dev/null | tr -d '\r')
    SYSTEM_PARTITION=$(adb shell "su -c 'readlink -f /dev/block/by-name/system'" 2>/dev/null | tr -d '\r')
    VENDOR_PARTITION=$(adb shell "su -c 'readlink -f /dev/block/by-name/vendor'" 2>/dev/null | tr -d '\r')
    
    # Backup boot partition
    if [ -n "$BOOT_PARTITION" ]; then
        echo "Backing up boot partition ($BOOT_PARTITION)..."
        adb shell "su -c 'dd if=$BOOT_PARTITION of=/sdcard/boot.img bs=4M'"
        adb pull /sdcard/boot.img boot.img
        adb shell "su -c 'rm /sdcard/boot.img'"
        echo "✓ Boot partition backed up ($(ls -lh boot.img | awk '{print $5}'))"
    fi
    
    # Backup recovery partition
    if [ -n "$RECOVERY_PARTITION" ]; then
        echo "Backing up recovery partition ($RECOVERY_PARTITION)..."
        adb shell "su -c 'dd if=$RECOVERY_PARTITION of=/sdcard/recovery.img bs=4M'"
        adb pull /sdcard/recovery.img recovery.img
        adb shell "su -c 'rm /sdcard/recovery.img'"
        echo "✓ Recovery partition backed up ($(ls -lh recovery.img | awk '{print $5}'))"
    fi
    
    echo ""
    echo "--- Backing up system apps ---"
    echo "Creating tarball of /system/app..."
    adb shell "su -c 'tar -czf /sdcard/system_apps.tar.gz /system/app 2>/dev/null'"
    adb pull /sdcard/system_apps.tar.gz system_apps.tar.gz
    adb shell "su -c 'rm /sdcard/system_apps.tar.gz'"
    echo "✓ System apps backed up ($(ls -lh system_apps.tar.gz | awk '{print $5}'))"
    
    echo ""
    echo "--- Backing up build properties ---"
    adb shell "su -c 'cat /system/build.prop'" > build.prop
    adb shell "su -c 'cat /vendor/build.prop'" > vendor_build.prop 2>/dev/null || echo "No vendor/build.prop"
    echo "✓ Build properties saved"
    
    echo ""
    echo "NOTE: System and vendor partition backups are very large (2-4 GB each)"
    echo "They were NOT backed up automatically to save time and space."
    echo ""
    read -p "Do you want to backup system and vendor partitions? (y/N) " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        if [ -n "$SYSTEM_PARTITION" ]; then
            echo "Backing up system partition (this will take 10-20 minutes)..."
            adb shell "su -c 'dd if=$SYSTEM_PARTITION of=/sdcard/system.img bs=4M'"
            adb pull /sdcard/system.img system.img
            adb shell "su -c 'rm /sdcard/system.img'"
            echo "✓ System partition backed up ($(ls -lh system.img | awk '{print $5}'))"
        fi
        
        if [ -n "$VENDOR_PARTITION" ]; then
            echo "Backing up vendor partition..."
            adb shell "su -c 'dd if=$VENDOR_PARTITION of=/sdcard/vendor.img bs=4M'"
            adb pull /sdcard/vendor.img vendor.img
            adb shell "su -c 'rm /sdcard/vendor.img'"
            echo "✓ Vendor partition backed up ($(ls -lh vendor.img | awk '{print $5}'))"
        fi
    fi
    
else
    # NON-ROOTED DEVICE - Limited backup
    
    echo "--- Backing up user-accessible data ---"
    
    echo "Backing up installed packages list..."
    adb shell pm list packages -f > installed_packages.txt
    echo "✓ Package list saved"
    
    echo "Backing up system properties..."
    adb shell getprop > system_properties.txt
    echo "✓ System properties saved"
    
    echo ""
    echo "WARNING: Without root access, system partitions cannot be backed up."
    echo "Only user-accessible data has been saved."
    echo "Consider rooting first for complete backup."
fi

echo ""
echo "--- Backing up user data ---"
adb shell "ls /sdcard/" > sdcard_files.txt 2>/dev/null || echo "Cannot list sdcard"
echo "✓ File list saved"

# Create backup info file
{
    echo "=========================================="
    echo "MG4 BACKUP INFORMATION"
    echo "Created: $(date)"
    echo "=========================================="
    echo ""
    echo "Root access: $HAS_ROOT"
    echo "Backup directory: $BACKUP_DIR"
    echo ""
    echo "--- Files backed up ---"
    ls -lh
    echo ""
    echo "--- Device info ---"
    adb shell getprop | grep -E "ro.build|ro.product|ro.board"
    echo ""
    echo "=========================================="
} > BACKUP_INFO.txt

echo ""
echo "=========================================="
echo "Backup complete!"
echo "=========================================="
echo ""
echo "Backup saved to: $BACKUP_DIR/"
echo "Total size: $(du -sh . | awk '{print $1}')"
echo ""
echo "Files backed up:"
ls -lh | grep -v "^total" | awk '{print "  - " $9 " (" $5 ")"}'
echo ""
echo "IMPORTANT:"
echo "  1. Store this backup in a safe location"
echo "  2. Keep multiple copies (USB drive, cloud storage, etc.)"
echo "  3. Test restore procedure before making system changes"
echo ""
echo "To compress backup for storage:"
echo "  cd .."
echo "  tar -czf $BACKUP_DIR.tar.gz $BACKUP_DIR/"
echo ""
