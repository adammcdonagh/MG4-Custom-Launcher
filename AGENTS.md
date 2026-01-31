# IMPORTANT: Java Package Path and Source Root

**Do NOT change the package declaration to `main.java.com.custom.launcher` or similar.**

- The correct package for all app code is `com.custom.launcher` (and subpackages).
- The source root is `app/src/main/java/`.
- The directory structure under `java/` must be `com/custom/launcher/...`.
- Do NOT add or expect a `main/java/com/custom/launcher` package or directory.
- If you see errors about `main.java.com.custom.launcher`, it means the IDE or build system is misconfigured, not the code.

**If you are an agent or developer, do NOT edit package declarations or move files to match a `main.java` prefix.**

This note is to prevent repeated, unnecessary changes that break the build.

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

**Implementation Details:**

- **Activation**: Triple-tap the clock (time display) within 500ms between taps
- **Log Filters**: Shows only relevant logs:
  - `CustomLauncher:V` - Main launcher logs
  - `VehicleDataService:V` - Vehicle service binding logs
  - `HeatingControlService:V` - Heating control service logs (seats & steering wheel)
  - `MediaListenerService:V` - Media service logs
  - `AndroidRuntime:E` - Critical errors
- **Permission**: Added `android.permission.READ_LOGS`
- **Log Command**: Uses `logcat -v time` with tag filtering
- **Auto-refresh**: Updates every 1 second while dialog is open
- **Memory**: Keeps last 500 lines to prevent memory issues

**Usage on Car:**

Since ADB is not available on the car, this debug dialog allows you to:

1. See if vehicle service is connecting
2. Check for any binding errors
3. Monitor media service status
4. View any runtime exceptions
5. Clear logs to start fresh debugging session

**Testing:**

- ✅ Dialog opens on triple-tap
- ✅ Logs display in real-time
- ✅ Auto-scrolls to bottom
- ✅ Clear button works
- ✅ Close button stops log reader and cleans up resources

## Build Status

### Phase 7: Debug Dialog Feature ✅

**Build completed**: 20 Jan 2026
**APK Location**: `app/build/outputs/apk/debug/app-debug.apk` (11MB)
**Build Issues Resolved:**

1. ✅ Created Android SDK directory structure
2. ✅ Installed platforms 33, 34 and build-tools
3. ✅ Added `gradle.properties` with AndroidX support
4. ✅ Updated compileSdk to 34 (required by Material Design 1.11.0)
5. ✅ Fixed XML layout error (invalid `auto` margin)
6. ✅ Corrected Java imports (Handler, ImageView, Context)

**Emulator Testing:**

- ✅ App launches successfully without crashes
- ✅ UI renders correctly at 1920×1080 @ 160 dpi
- ⚠️ Vehicle service binding fails gracefully (expected - uses mock data)
- ⚠️ Media listener requires notification permissions

### Phase 6: Battery Card Integration ✅

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
