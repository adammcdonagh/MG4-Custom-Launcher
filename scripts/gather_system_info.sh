#!/bin/bash
# MG4 System Information Gathering Script
# This script collects information needed to determine the best rooting method
# Run this on your Mac while connected to the car via ADB

set -e

echo "=========================================="
echo "MG4 System Information Gathering"
echo "=========================================="
echo ""

# Check if device is connected
echo "[1/10] Checking ADB connection..."
if ! adb devices | grep -q "device$"; then
    echo "ERROR: No device connected via ADB"
    echo "Please enable USB debugging and connect your car"
    exit 1
fi
echo "✓ Device connected"
echo ""

# Create output directory
OUTPUT_DIR="mg4_system_info_$(date +%Y%m%d_%H%M%S)"
mkdir -p "$OUTPUT_DIR"
cd "$OUTPUT_DIR"

echo "[2/10] Gathering system properties..."
adb shell getprop > mg4_properties.txt
echo "✓ Saved to mg4_properties.txt"

echo "[3/10] Gathering CPU information..."
adb shell cat /proc/cpuinfo > mg4_cpuinfo.txt
echo "✓ Saved to mg4_cpuinfo.txt"

echo "[4/10] Gathering partition layout..."
adb shell "ls -lR /dev/block/platform/*/by-name/" 2>/dev/null > mg4_partitions.txt || \
adb shell "ls -lR /dev/block/by-name/" 2>/dev/null > mg4_partitions.txt || \
echo "Could not list partitions (may need root)" > mg4_partitions.txt
echo "✓ Saved to mg4_partitions.txt"

echo "[5/10] Gathering storage information..."
adb shell df -h > mg4_storage.txt
echo "✓ Saved to mg4_storage.txt"

echo "[6/10] Gathering mount points..."
adb shell mount > mg4_mounts.txt
echo "✓ Saved to mg4_mounts.txt"

echo "[7/10] Checking bootloader status..."
{
    echo "=== Bootloader Status ==="
    echo "Verified boot state:"
    adb shell getprop ro.boot.verifiedbootstate
    echo ""
    echo "OEM unlock supported:"
    adb shell getprop ro.oem_unlock_supported
    echo ""
    echo "Boot flash locked:"
    adb shell getprop ro.boot.flash.locked
    echo ""
    echo "Bootloader version:"
    adb shell getprop ro.bootloader
    echo ""
    echo "Secure boot:"
    adb shell getprop ro.secure
    echo ""
    echo "Debuggable:"
    adb shell getprop ro.debuggable
} > mg4_bootloader_status.txt
echo "✓ Saved to mg4_bootloader_status.txt"

echo "[8/10] Checking WiFi hardware..."
{
    echo "=== WiFi Hardware Info ==="
    echo "WiFi interface:"
    adb shell "ifconfig wlan0 2>&1 || echo 'No wlan0 interface'"
    echo ""
    echo "WiFi capabilities:"
    adb shell "iw phy phy0 info 2>&1 || echo 'iw command not available'"
    echo ""
    echo "WiFi Direct:"
    adb shell "iw dev 2>&1 | grep p2p || echo 'No P2P interface found'"
} > mg4_wifi_info.txt
echo "✓ Saved to mg4_wifi_info.txt"

echo "[9/10] Checking installed packages..."
adb shell pm list packages -f > mg4_packages.txt
echo "✓ Saved to mg4_packages.txt"

echo "[10/10] Gathering hardware info..."
{
    echo "=== Hardware Information ==="
    echo "Board platform:"
    adb shell getprop ro.board.platform
    echo ""
    echo "Product board:"
    adb shell getprop ro.product.board
    echo ""
    echo "Hardware:"
    adb shell getprop ro.hardware
    echo ""
    echo "SoC manufacturer:"
    adb shell getprop ro.soc.manufacturer
    echo ""
    echo "SoC model:"
    adb shell getprop ro.soc.model
    echo ""
    echo "Total RAM:"
    adb shell cat /proc/meminfo | grep MemTotal
    echo ""
    echo "Kernel version:"
    adb shell uname -a
} > mg4_hardware_info.txt
echo "✓ Saved to mg4_hardware_info.txt"

echo ""
echo "=========================================="
echo "Information gathering complete!"
echo "=========================================="
echo ""
echo "Output saved to: $OUTPUT_DIR/"
echo ""
echo "Next steps:"
echo "1. Review the collected files"
echo "2. Share these files for analysis"
echo "3. Determine best rooting method based on findings"
echo ""
echo "Key files to check:"
echo "  - mg4_bootloader_status.txt : Check if bootloader is unlockable"
echo "  - mg4_hardware_info.txt     : Identify SoC for exploit search"
echo "  - mg4_wifi_info.txt         : Check wireless CarPlay compatibility"
echo "  - mg4_partitions.txt        : Partition layout for backup"
echo ""

# Create summary
echo "Creating summary..."
{
    echo "=========================================="
    echo "MG4 SYSTEM INFORMATION SUMMARY"
    echo "Collected: $(date)"
    echo "=========================================="
    echo ""
    
    echo "--- ANDROID VERSION ---"
    grep "ro.build.version.release" mg4_properties.txt | head -1
    grep "ro.build.version.sdk" mg4_properties.txt | head -1
    grep "ro.build.version.security_patch" mg4_properties.txt | head -1
    echo ""
    
    echo "--- DEVICE MODEL ---"
    grep "ro.product.model" mg4_properties.txt | head -1
    grep "ro.product.manufacturer" mg4_properties.txt | head -1
    grep "ro.product.brand" mg4_properties.txt | head -1
    echo ""
    
    echo "--- BOOTLOADER STATUS ---"
    cat mg4_bootloader_status.txt | grep -A1 "Verified boot state:"
    cat mg4_bootloader_status.txt | grep -A1 "OEM unlock supported:"
    cat mg4_bootloader_status.txt | grep -A1 "Secure boot:"
    echo ""
    
    echo "--- HARDWARE ---"
    grep "ro.board.platform" mg4_properties.txt | head -1
    grep "ro.hardware" mg4_properties.txt | head -1
    grep "MemTotal" mg4_hardware_info.txt
    echo ""
    
    echo "--- WIFI CAPABILITY ---"
    grep -A1 "WiFi interface:" mg4_wifi_info.txt | head -2
    echo ""
    
    echo "=========================================="
    echo "For full details, see individual files in:"
    echo "$OUTPUT_DIR/"
    echo "=========================================="
} > SUMMARY.txt

cat SUMMARY.txt

echo ""
echo "Summary saved to: $OUTPUT_DIR/SUMMARY.txt"
echo ""
echo "To compress all files for sharing:"
echo "  cd .."
echo "  tar -czf $OUTPUT_DIR.tar.gz $OUTPUT_DIR/"
echo ""
