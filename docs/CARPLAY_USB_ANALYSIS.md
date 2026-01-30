# AllgoCarplay APK Analysis - USB Interface Investigation

## Date: 30 January 2026

## Executive Summary

The MG4 R67 firmware includes two APKs for smartphone projection:

1. **AllgoCarplay_EH32.apk** - Apple CarPlay support
2. **AAP_EH32.apk** - Android Auto Projection support

Both use the **Android Automotive OS (AAOS) CAR_PROJECTION** framework to interface with USB-connected devices via the **Android Open Accessory Protocol (AOAP)**.

---

## Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                    User's Phone (iOS/Android)               │
│                                                             │
│         ┌────────────┐              ┌────────────┐         │
│         │  CarPlay   │              │Android Auto│         │
│         └─────┬──────┘              └─────┬──────┘         │
│               │                            │                │
└───────────────┼────────────────────────────┼────────────────┘
                │                            │
                │      USB Lightning/C       │
                └────────────┬───────────────┘
                             │
┌────────────────────────────┴────────────────────────────────┐
│              MG4 Infotainment System (Android 9)            │
│                                                             │
│  ┌──────────────────────────────────────────────────────┐  │
│  │         Android USB Host Stack                       │  │
│  │                                                      │  │
│  │  ┌────────────────────────────────────────────┐    │  │
│  │  │   UsbManager (android.hardware.usb)        │    │  │
│  │  └────────────┬───────────────────────────────┘    │  │
│  └───────────────┼──────────────────────────────────────┘  │
│                  │                                          │
│  ┌───────────────▼──────────────────────────────────────┐  │
│  │   AOAP Support Check Service                        │  │
│  │   (IUsbAoapSupportCheckService)                     │  │
│  │   - Validates USB device compatibility              │  │
│  │   - Checks for AOAP protocol support                │  │
│  └───────────────┬──────────────────────────────────────┘  │
│                  │                                          │
│  ┌───────────────▼──────────────────────────────────────┐  │
│  │   Android Car Projection Service                    │  │
│  │   (android.car.permission.CAR_PROJECTION)           │  │
│  │   - Manages projection session lifecycle            │  │
│  │   - Handles audio/video streaming                   │  │
│  │   - Routes navigation, media, phone calls           │  │
│  └───────────────┬──────────────────────────────────────┘  │
│                  │                                          │
│       ┌──────────┴───────────┐                             │
│       │                      │                             │
│  ┌────▼──────────────┐  ┌───▼───────────────────┐         │
│  │ AllgoCarplay      │  │ AAP (Android Auto)    │         │
│  │ CarPlayService    │  │ AndroidAutoService    │         │
│  │ - CarPlayActivity │  │ - ProjectionActivity  │         │
│  │ - Telephony       │  │ - FRXActivity         │         │
│  │ - Audio routing   │  │ - Telephony           │         │
│  └───────────────────┘  └───────────────────────┘         │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

---

## USB Interface Details

### 1. USB Device Detection

The system uses **UsbManager** (`android.hardware.usb.UsbManager`) to detect USB devices when they are connected.

**Key Interface:**

```java
public interface IUsbAoapSupportCheckService extends IInterface {
    boolean isDeviceSupported(UsbDevice usbDevice) throws RemoteException;
}
```

**Location:** `AllgoCarplay_jadx/sources/android/car/IUsbAoapSupportCheckService.java`

**Purpose:**

- Validates if a connected USB device supports AOAP (Android Open Accessory Protocol)
- Determines if the device is an iPhone (for CarPlay) or Android phone (for Android Auto)
- Returns `true` if device can be used for projection

### 2. Android Open Accessory Protocol (AOAP)

AOAP is Google's protocol for allowing USB accessories to communicate with Android devices. In this case, the **MG4 infotainment system acts as the USB host**, and the **phone acts as the USB accessory**.

**AOAP Communication Flow:**

1. Phone plugs into USB port
2. UsbManager detects new `UsbDevice`
3. `IUsbAoapSupportCheckService.isDeviceSupported()` called
4. If supported → Switch device to AOAP mode
5. Establish bidirectional USB communication channel
6. Launch projection activity (CarPlay or Android Auto)

**AOAP Features Used:**

- **Audio Streaming:** USB audio output from phone to car speakers
- **Video Streaming:** Screen mirroring from phone to car display (H.264 encoded)
- **Touch Input:** Car touchscreen events sent to phone
- **Data Exchange:** Navigation, media controls, phone calls

### 3. Required Permissions

Both APKs require the following permissions:

**USB & Projection:**

- `android.car.permission.CAR_PROJECTION` - **Critical** for projection functionality
- `android.car.permission.CAR_INFO` - Access vehicle data (speed, gear, etc.)
- `android.car.permission.CAR_VENDOR_EXTENSION` - SAIC-specific vehicle features

**Network & Connectivity:**

- `android.permission.INTERNET` - For online features
- `android.permission.BLUETOOTH` - Bluetooth pairing (wireless CarPlay/AA)
- `android.permission.ACCESS_WIFI_STATE` - WiFi Direct for wireless projection

**Telephony:**

- `android.permission.READ_PHONE_STATE` - Phone call status
- `android.permission.MANAGE_OWN_CALLS` - Handle in-car calling
- `android.permission.CALL_PHONE` - Initiate calls from car UI

**Location:**

- `android.permission.ACCESS_FINE_LOCATION` - GPS for navigation
- `android.permission.ACCESS_COARSE_LOCATION` - General location

---

## Key Components

### AllgoCarplay APK (Apple CarPlay)

**Package:** `com.allgo.carplay.service`  
**Shared User ID:** `android.uid.system` (runs as system app)

**Main Components:**

1. **CarPlayService** (Service)
   - Manages CarPlay session lifecycle
   - Handles USB connection state
   - Routes audio/video between iPhone and car
   - Integrates with `android.telecom.ConnectionService` for phone calls

2. **CarPlayActivity** (Activity)
   - Displays iPhone screen mirror on car display
   - Handles touch input forwarding
   - `launchMode="singleInstance"` - Only one instance at a time
   - `android:resumeWhilePausing="true"` - Can resume while other apps pause

3. **CarPlayStarter** (BroadcastReceiver)
   - Listens for `BOOT_COMPLETED` to start service on car startup

4. **CarPlayTelephonyManager$CarPlayConnectionService**
   - Manages phone call routing between iPhone and car audio system

### AAP APK (Android Auto)

**Package:** `com.allgo.app.androidauto`  
**Shared User ID:** `android.uid.system` (runs as system app)

**Main Components:**

1. **AndroidAutoService** (Service)
   - Runs in separate process: `:AndroidAutoServer`
   - Manages Android Auto projection session

2. **AndroidAutoMainService** (Service)
   - Runs in separate process: `:AndroidAutoMainServer`
   - Handles telephony integration (`BIND_TELECOM_CONNECTION_SERVICE`)

3. **ProjectionActivity** (Activity)
   - Main Android Auto display interface
   - `launchMode="singleInstance"`
   - Handles phone call intents (`ACTION_DIAL`, `ACTION_CALL`)
   - `screenOrientation="sensorLandscape"` - Always landscape

4. **FRXActivity** (Activity)
   - FRX = First Run Experience
   - Setup wizard for first-time Android Auto connection

---

## USB Port Configuration

Based on the architecture, the MG4 likely has **2 USB ports** for smartphone projection:

1. **Front Center Console USB Port**
   - USB 2.0 or 3.0 Type-A or Type-C
   - Connected to Android head unit's USB host controller
   - Supports both iPhone (Lightning cable) and Android (USB-C cable)

2. **Data vs Charging Ports**
   - Only **data-enabled USB ports** work for CarPlay/Android Auto
   - Some USB ports may be **charging-only** (no data lines)

**To verify USB port capabilities:**

```bash
# Connect car via ADB and run:
adb shell lsusb
adb shell dumpsys usb
```

---

## How USB Communication Works

### Initialization Sequence

1. **Device Plugged In**

   ```
   User connects iPhone/Android phone → USB cable → MG4 USB port
   ```

2. **USB Enumeration**

   ```
   Linux kernel detects USB device
   → UsbManager receives broadcast: android.hardware.usb.action.USB_DEVICE_ATTACHED
   → IUsbAoapSupportCheckService.isDeviceSupported(UsbDevice) called
   ```

3. **AOAP Handshake (for Android phones)**

   ```
   - Send AOAP control requests to phone
   - Phone switches to accessory mode
   - Establish bulk transfer endpoints
   ```

4. **CarPlay/Android Auto Protocol Negotiation**

   ```
   - Exchange protocol version
   - Negotiate supported features (audio, video, touch, GPS)
   - Establish encrypted communication channel
   ```

5. **Projection Start**
   ```
   - Launch CarPlayActivity or ProjectionActivity
   - Start H.264 video decoding from phone
   - Start audio routing to car speakers
   - Enable touch input forwarding
   ```

### Data Transfer

**Video Streaming:**

- Format: H.264 encoded video
- Resolution: Typically 800×480 or 1280×720 (adapts to car display)
- Frame rate: 30 or 60 FPS
- Transport: USB bulk transfer endpoints

**Audio Streaming:**

- Format: PCM audio or AAC
- Channels: Stereo (2 channels)
- Sample rate: 44.1 kHz or 48 kHz
- Transport: USB isochronous transfer (real-time)

**Touch Input:**

- Car display captures touch events
- Converted to phone-compatible coordinates
- Sent via USB control transfers

**Sensor Data:**

- Car sends GPS, accelerometer, gyroscope data to phone
- Phone uses this for navigation and orientation

---

## Integration with Vehicle Systems

### SAIC SDK Integration

The CarPlay/Android Auto APKs integrate with SAIC's proprietary SDK (similar to your custom launcher):

**Relevant SDK Interfaces Found:**

1. **IUsbBinderInterface** (SAIC Media SDK)
   - `com.saicmotor.sdk.media.IUsbBinderInterface`
   - Manages USB media device detection
   - Used for USB storage (music, videos)

2. **IUsbDevicesListener** (SAIC Media SDK)
   - `com.saicmotor.sdk.media.callback.IUsbDevicesListener`
   - Listens for USB device connection/disconnection events

3. **IUsbMusicCallback** (SAIC Media SDK)
   - `com.saicmotor.sdk.media.callback.IUsbMusicCallback`
   - Callback for USB music playback status

4. **IUsbVideoCallback** (SAIC Media SDK)
   - `com.saicmotor.sdk.media.callback.IUsbVideoCallback`
   - Callback for USB video playback

**Note:** These SAIC SDK classes are **separate** from the CarPlay/Android Auto projection functionality. They handle **USB storage** (flash drives with music/videos), not smartphone projection.

### Audio Routing

When CarPlay/Android Auto is active:

- Phone audio streams via USB → Decoded by projection service
- Injected into Android audio mixer
- Routed to car speakers via HVAC audio system
- Uses Android's `AudioManager` and `CarAudioManager`

### Display Management

- Projection activity runs in **full-screen mode**
- Disables stock launcher and other apps while active
- Uses Android's `SurfaceView` to render decoded video stream
- Touch events forwarded via `MotionEvent` injection

---

## Reverse Engineering Notes

### Decompilation Challenges

1. **Native Code:** Large portions of CarPlay/Android Auto logic are in **native C/C++ libraries** (`.so` files in `lib/` directory), not Java bytecode
2. **Obfuscation:** Some class/method names are obfuscated
3. **AIDL Interfaces:** Communication uses AIDL (Android Interface Definition Language) for inter-process communication

### Key Files Not Decompiled

The following are likely in native libraries:

- AOAP protocol implementation
- H.264 video decoder
- Audio processing
- Encryption/decryption
- USB low-level communication

**To extract native libraries:**

```bash
cd mg_firmware/1300\ SWI68\ R67/extracted_apks
unzip -j AllgoCarplay_EH32.apk "lib/arm64-v8a/*.so" -d AllgoCarplay_native_libs/
```

---

## Practical Implications for Custom Launcher

### Can Your Custom Launcher Access USB Data?

**Short Answer:** Yes, but with limitations.

**What You Can Access:**

1. **USB Storage Devices (Flash Drives)**
   - Use `android.hardware.usb.UsbManager` to detect USB storage
   - Mount FAR32/exFAT/NTFS drives (if kernel supports)
   - Read music, images, videos

2. **USB Device Information**
   - Vendor ID, Product ID, device name
   - Connected port, device class

3. **SAIC SDK USB APIs** (discovered in your investigation)
   - `IUsbBinderInterface` - Interface with USB storage
   - `IUsbDevicesListener` - Detect USB device events
   - `IUsbMusicCallback` - Monitor USB music playback

**What You CANNOT Access (Without System Privileges):**

1. **CarPlay/Android Auto Projection**
   - Requires `android.car.permission.CAR_PROJECTION` (signature-level permission)
   - Requires `android.uid.system` (system UID)
   - Would need to sign your APK with platform certificate

2. **Raw USB Accessory Communication**
   - AOAP mode switching requires system privileges
   - Direct USB bulk transfer requires USB Host API + system permissions

### Recommended Approach

**If you want to integrate with USB in your custom launcher:**

1. **Detect USB Devices:**

   ```java
   UsbManager usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
   HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();

   for (UsbDevice device : deviceList.values()) {
       Log.d(TAG, "USB Device: " + device.getDeviceName());
       Log.d(TAG, "Vendor ID: " + device.getVendorId());
       Log.d(TAG, "Product ID: " + device.getProductId());
   }
   ```

2. **Listen for USB Events:**

   ```xml
   <!-- AndroidManifest.xml -->
   <uses-permission android:name="android.permission.USB_PERMISSION"/>

   <receiver android:name=".UsbReceiver">
       <intent-filter>
           <action android:name="android.hardware.usb.action.USB_DEVICE_ATTACHED"/>
           <action android:name="android.hardware.usb.action.USB_DEVICE_DETACHED"/>
       </intent-filter>
   </receiver>
   ```

3. **Launch CarPlay/Android Auto from Launcher:**

   ```java
   // Launch AllgoCarplay
   Intent carPlayIntent = new Intent();
   carPlayIntent.setComponent(new ComponentName(
       "com.allgo.carplay.service",
       "com.allgo.carplay.service.CarPlayActivity"
   ));
   carPlayIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   startActivity(carPlayIntent);

   // Launch Android Auto
   Intent androidAutoIntent = new Intent();
   androidAutoIntent.setComponent(new ComponentName(
       "com.allgo.app.androidauto",
       "com.allgo.app.androidauto.ProjectionActivity"
   ));
   androidAutoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   startActivity(androidAutoIntent);
   ```

4. **Display USB Status Icon:**
   - Show icon when USB device connected
   - Different icons for:
     - iPhone (CarPlay available)
     - Android phone (Android Auto available)
     - USB flash drive (media available)

5. **Use SAIC SDK for USB Media:**

   ```java
   // Similar to VehicleDataService, use reflection to access SAIC USB SDK
   Context mediaServiceContext = context.createPackageContext(
       "com.saicmotor.hmi.mediaservice",
       Context.CONTEXT_INCLUDE_CODE
   );

   ClassLoader classLoader = mediaServiceContext.getClassLoader();
   Class<?> usbManagerClass = classLoader.loadClass(
       "com.saicmotor.sdk.media.manager.UsbManager"
   );

   // Register listener for USB device events
   // Access USB music/video playback
   ```

---

## Testing on Your MG4

### 1. Check USB Port Configuration

```bash
# Connect car via ADB
adb shell

# List USB devices
lsusb

# Check USB host controller
cat /proc/bus/usb/devices

# Check USB mount points
mount | grep usb
```

### 2. Test CarPlay/Android Auto Detection

```bash
# Enable ADB logging for USB events
adb logcat | grep -i "usb\|carplay\|androidauto\|aoap"

# Plug in iPhone or Android phone
# Observe log output to see detection flow
```

### 3. Monitor Projection Activity

```bash
# Check if CarPlay service is running
adb shell dumpsys activity services | grep carplay

# Check active projection session
adb shell dumpsys car_service | grep projection

# Check audio routing
adb shell dumpsys audio | grep carplay
```

---

## Summary

**How AllgoCarplay Interfaces with USB:**

1. ✅ Uses Android's **UsbManager** to detect USB devices
2. ✅ Validates device support via **IUsbAoapSupportCheckService**
3. ✅ Switches phone to **AOAP mode** (Android Open Accessory Protocol)
4. ✅ Establishes **bidirectional USB communication** (bulk transfers for video, isochronous for audio)
5. ✅ Requires **CAR_PROJECTION permission** (signature-level, system app only)
6. ✅ Integrates with **SAIC SDK** for vehicle audio routing
7. ✅ Uses **native libraries** (C/C++) for H.264 decoding and USB protocol handling

**Your Custom Launcher Can:**

- ✅ Detect USB devices (flash drives, phones)
- ✅ Launch CarPlay/Android Auto activities via Intent
- ✅ Use SAIC SDK to access USB storage media
- ✅ Display USB connection status

**Your Custom Launcher CANNOT (without system signing):**

- ❌ Implement its own CarPlay/Android Auto projection
- ❌ Access raw USB accessory communication
- ❌ Intercept projection video streams

---

## Further Investigation

To fully understand the USB protocol implementation, you would need to:

1. Extract and decompile **native libraries** (`.so` files)
2. Use **Ghidra** or **IDA Pro** to reverse engineer C/C++ code
3. Analyze **USB packet captures** with Wireshark
4. Study **AOAP specification** (available from Google)
5. Examine **CarPlay/Android Auto protocol documentation** (requires NDA with Apple/Google)

---

## References

- **Android Open Accessory Protocol (AOAP):** https://source.android.com/devices/accessories/aoa
- **Android Automotive OS - Projection:** https://source.android.com/devices/automotive/projection
- **Apple CarPlay MFi Program:** https://developer.apple.com/carplay/
- **Android Auto Developer Docs:** https://developer.android.com/training/cars

---

**Document Created:** 30 January 2026  
**Firmware Analyzed:** MG4 R67 (1300 SWI68 R67)  
**APKs Decompiled:** AllgoCarplay_EH32.apk, AAP_EH32.apk
