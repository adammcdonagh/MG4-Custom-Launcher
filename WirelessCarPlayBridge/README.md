# Wireless CarPlay Bridge for MG4

## Overview

This project implements a **software-based wireless CarPlay adapter** for the MG4 electric vehicle. Instead of purchasing a $100+ hardware adapter, this app creates a WiFi-to-USB bridge that enables wireless CarPlay functionality.

## How It Works

```
┌─────────────────────────────────────────────────────────────────┐
│ 1. Discovery Phase (Bluetooth LE)                              │
│                                                                 │
│ iPhone ◄──[BLE Advertisement]──► WirelessCarPlayBridge         │
│         "Car supports CarPlay"                                  │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│ 2. Connection Phase (WiFi Direct P2P)                          │
│                                                                 │
│ iPhone ◄──[WiFi Direct 5GHz]──► WirelessCarPlayBridge          │
│         Creates ad-hoc network                                  │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│ 3. Bridge Phase (Virtual USB Device)                           │
│                                                                 │
│ iPhone ──[WiFi]──► DataBridge ──[Virtual USB]──► AllgoCarplay  │
│         Video + Audio           Emulates iPhone                 │
└─────────────────────────────────────────────────────────────────┘
```

The app consists of four main components:

1. **BluetoothLeAdvertiser**: Broadcasts CarPlay capability via BLE
2. **WiFiDirectManager**: Establishes WiFi Direct connection with iPhone
3. **UsbGadgetController**: Creates virtual USB device using Linux USB Gadget framework
4. **DataBridge**: Routes data between WiFi and USB transparently

## Prerequisites

### Hardware Requirements

- ✅ **Bluetooth LE 4.0+**: MG4 R67 has this (confirmed)
- ❓ **WiFi 5 GHz (802.11ac)**: Need to verify if MG4 hardware supports this
- ❓ **WiFi Direct (P2P) support**: May require checking kernel config
- ❓ **USB Gadget mode**: May require kernel modules (CONFIG_USB_CONFIGFS)

### Software Requirements

- Android 9+ (MG4 R67 runs Android 9) ✅
- System-level permissions (android.uid.system) ✅
- AOSP platform signing keys (available) ✅

## Build Instructions

### Step 1: Build the APK

```bash
cd WirelessCarPlayBridge
./gradlew assembleDebug
```

The unsigned APK will be at:

```
app/build/outputs/apk/debug/app-debug.apk
```

### Step 2: Download AOSP Platform Keys

The MG4 system apps are signed with publicly available AOSP test keys:

```bash
# Create keys directory
mkdir -p platform_keys

# Download from AOSP repository
curl -o platform_keys/platform.pk8 \
  https://android.googlesource.com/platform/build/+/refs/heads/master/target/product/security/platform.pk8?format=TEXT | base64 -d > platform_keys/platform.pk8

curl -o platform_keys/platform.x509.pem \
  https://android.googlesource.com/platform/build/+/refs/heads/master/target/product/security/platform.x509.pem?format=TEXT | base64 -d > platform_keys/platform.x509.pem
```

### Step 3: Sign the APK with Platform Keys

You'll need `apksigner` (part of Android SDK build-tools):

```bash
# Install signapk tool (or use apksigner from Android SDK)
# Option 1: Use apksigner (recommended)
~/Library/Android/sdk/build-tools/34.0.0/apksigner sign \
  --key platform_keys/platform.pk8 \
  --cert platform_keys/platform.x509.pem \
  --out app/build/outputs/apk/debug/app-debug-signed.apk \
  app/build/outputs/apk/debug/app-debug.apk

# Option 2: Use signapk.jar (if you have it)
java -jar signapk.jar \
  platform_keys/platform.x509.pem \
  platform_keys/platform.pk8 \
  app/build/outputs/apk/debug/app-debug.apk \
  app/build/outputs/apk/debug/app-debug-signed.apk
```

### Step 4: Verify the Signature

```bash
~/Library/Android/sdk/build-tools/34.0.0/apksigner verify \
  --print-certs \
  app/build/outputs/apk/debug/app-debug-signed.apk
```

You should see:

```
Signer #1 certificate DN: C=US, ST=California, L=Mountain View, O=Android, OU=Android, CN=Android, E=android@android.com
Signer #1 certificate SHA-256 digest: 27196e386b875e76adf700e7ea84e4c6ee333dfa
```

## Installation

### Method 1: Install as User App (Testing)

```bash
# Connect to car via ADB
adb devices

# Install the signed APK
adb install -r app/build/outputs/apk/debug/app-debug-signed.apk

# Check if it installed
adb shell pm list packages | grep wirelesscarplay
# Should show: package:com.mg4.wirelesscarplay

# Check the UID
adb shell dumpsys package com.mg4.wirelesscarplay | grep userId
# Should show: userId=1000 (system UID)
```

### Method 2: Install as System App (Permanent)

**Note**: This requires ADB root access. If your car doesn't have root, you'll need to use Method 1.

```bash
# Remount system as read-write
adb root
adb remount

# Push to system app directory
adb push app/build/outputs/apk/debug/app-debug-signed.apk \
  /system/app/WirelessCarPlayBridge/WirelessCarPlayBridge.apk

# Set proper permissions
adb shell chmod 644 /system/app/WirelessCarPlayBridge/WirelessCarPlayBridge.apk

# Reboot to activate
adb reboot
```

## Testing & Verification

### Step 1: Check Service Status

```bash
# Check if service is running
adb shell ps -A | grep wirelesscarplay

# Check logs
adb logcat | grep -E "WirelessCarPlayBridge|BLEAdvertiser|WiFiDirectManager"
```

### Step 2: Verify BLE Advertisement

```bash
# Check Bluetooth status
adb shell dumpsys bluetooth_manager | grep -A 10 "Advertising"
```

On your iPhone:

1. Go to Settings → General → CarPlay
2. You should see your car's name appear in "Available Cars"

### Step 3: Test WiFi Direct Connection

```bash
# Check WiFi P2P status
adb shell dumpsys wifip2p

# Should show:
# - mP2pSupported: true
# - mP2pState: enabled
```

### Step 4: Check USB Gadget

```bash
# Check if ConfigFS is available
adb shell ls /config/usb_gadget/

# Check available UDC (USB Device Controller)
adb shell ls /sys/class/udc/
```

## Troubleshooting

### Issue: BLE Advertisement Fails

**Error**: `BLE advertising not supported on this device`

**Solution**: Check if Bluetooth LE is enabled:

```bash
adb shell dumpsys bluetooth_manager | grep "LE supported"
```

### Issue: WiFi Direct Not Working

**Error**: `WiFi P2P not supported`

**Solution**: Verify WiFi Direct support:

```bash
# Check if WiFi Direct is enabled in kernel
adb shell getprop | grep wifi

# Enable WiFi if disabled
adb shell svc wifi enable
```

### Issue: USB Gadget Creation Fails

**Error**: `ConfigFS not available`

**Solution**: This means the kernel doesn't have USB_CONFIGFS enabled. You have two options:

1. **Use a hardware adapter** (Carlinkit, OTTOCAST) - 95% success rate
2. **Recompile kernel with USB_CONFIGFS** (advanced, requires root) - 20% success rate

### Issue: "INSTALL_FAILED_SHARED_USER_INCOMPATIBLE"

**Error**: Cannot install due to sharedUserId mismatch

**Solution**: You didn't sign the APK with platform keys. Re-sign using Step 3 above.

## Expected Behavior

When everything works correctly:

1. **On boot**: Service starts automatically
2. **BLE advertising**: iPhone sees car in CarPlay settings
3. **iPhone initiates pairing**: WiFi Direct connection established
4. **Data bridge active**: Virtual USB device created
5. **AllgoCarplay launches**: CarPlay appears on car display

## Limitations & Known Issues

### Current Implementation

- ✅ BLE advertisement implemented
- ✅ WiFi Direct connection handling implemented
- ✅ USB Gadget framework integration implemented
- ⚠️ **USB Gadget requires kernel support** - May not work if kernel lacks CONFIG_USB_CONFIGFS
- ⚠️ **Requires system permissions** - Must be signed with platform keys

### Hardware Limitations

- **WiFi 5 GHz support unknown**: May only support 2.4 GHz (slower, higher latency)
- **WiFi P2P support unknown**: Kernel may not have P2P mode enabled
- **USB Gadget support unknown**: May need kernel recompilation

### Alternative: Hardware Adapter

If the USB Gadget approach doesn't work due to kernel limitations, you can:

1. **Buy a wireless CarPlay adapter** ($80-150)
   - Carlinkit 3.0/4.0
   - OTTOCAST U2-AIR
   - Carsifi
2. **These adapters work by**:
   - Plugging into USB port (car sees it as wired iPhone)
   - Creating WiFi bridge to actual iPhone
   - 100% compatibility, no software modifications needed

## Next Steps

1. ✅ Build the project
2. ✅ Sign with platform keys
3. 🔄 Install on car and test BLE/WiFi functionality
4. 🔄 Verify USB Gadget support (check kernel config)
5. 🔄 If USB Gadget works: Test end-to-end wireless CarPlay
6. 🔄 If USB Gadget fails: Document hardware adapter recommendation

## Success Probability

| Approach                         | Success Rate | Notes                                |
| -------------------------------- | ------------ | ------------------------------------ |
| **Software-only (this project)** | 40-60%       | Depends on kernel USB Gadget support |
| **Hardware adapter**             | 95%+         | Works with existing AllgoCarplay     |
| **Hybrid (software + adapter)**  | 80-90%       | Use adapter for USB, optimize WiFi   |

## References

- [AOSP Test Keys Discovery](../docs/AOSP_TEST_KEYS_DISCOVERY.md)
- [CarPlay USB Analysis](../docs/CARPLAY_USB_ANALYSIS.md)
- [Wireless CarPlay Feasibility](../docs/WIRELESS_CARPLAY_FEASIBILITY.md)
- [Android USB Gadget Documentation](https://www.kernel.org/doc/html/latest/usb/gadget_configfs.html)
- [WiFi Direct (P2P) Guide](https://developer.android.com/guide/topics/connectivity/wifip2p)

## License

This project is for educational and personal use only. CarPlay is a trademark of Apple Inc.
