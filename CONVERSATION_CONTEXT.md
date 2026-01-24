# Conversation Context - MG4 Custom Launcher

## Session Goal

Create a custom Android launcher for MG4 electric vehicle that displays essential information (battery, range, time, media) in a cleaner interface than the stock SAIC launcher.

## User's Requirements

- Show battery level and range (primary feature)
- Display current time and date
- Show now playing music information
- Clean, modern UI (not interested in radio/charging control buttons)

## What We Discovered

### Original Widget Investigation

User wanted to extract APK files from Android OTA packages to find the battery widget from an older firmware version (R40).

**Process:**

1. Extracted `usb_ota_update.zip` → `payload.bin`
2. Used `payload-dumper-go` to extract partition images
3. Used `7zip` to extract `system.img` and `vendor.img`
4. Found 94 APKs in R40, including `EvCharge_eh32_ll.apk`

**Widget Details Found:**

- EvCharge had 2 widgets: 2x1 battery widget and 1x1 icon widget
- The 2x1 widget matched the green battery card in user's screenshot
- Showed "Battery", "Range: 136km", "Battery: 39%"
- Color-coded background based on battery level

### Version Comparison

Extracted and analyzed 3 firmware versions:

| Version          | Battery App             | Notes                         |
| ---------------- | ----------------------- | ----------------------------- |
| R40 (1100 SWI69) | ✅ EvCharge_eh32_ll.apk | Has 2 widgets, standalone app |
| R46 (1300 SWI68) | ❌ Removed              | Only VehicleSettings apps     |
| R67 (1300 SWI68) | ❌ Removed              | Integrated into launcher      |

**User's current system:** R67 (no EvCharge app, charging info built into launcher)

### Why Backporting Won't Work

- EvCharge depends on specific vehicle service APIs
- System integration changed between versions
- Signature verification issues
- Missing dependencies in newer firmware

### Alternative Solution: Custom Launcher

Instead of backporting the old app, we created a new custom launcher from scratch that:

- Replicates the essential features user wants
- Uses the same vehicle service APIs as R67 launcher
- Cleaner, more modern UI
- Only shows what user cares about

## Technical Implementation

### How Stock Launcher Gets Battery Data

Analysis of `launcher_eh32_eu_P.apk` revealed:

- Binds to `com.saicmotor.telematics.VehicleService`
- Uses `VehicleStatusManager` and `VehicleChargingCallback`
- Layouts: `fragment_energy_charging.xml`, `function_card_energy.xml`
- ViewModels: `EnergyViewModel`, `ChargingViewModel`

### Custom Launcher Architecture

**3 main components:**

1. **VehicleDataService.java**
   - Binds to SAIC vehicle service
   - Provides battery level and range data
   - Includes mock data fallback for testing

2. **MediaListenerService.java**
   - NotificationListenerService for media tracking
   - Uses Android MediaSession API
   - Shows current song/artist, play/pause state

3. **MainActivity.java**
   - Displays 3 cards: Time/Date, Battery, Music
   - Auto-updates time every second
   - Color-codes battery card (green/yellow/red)
   - Shows app shortcuts at bottom

### UI Design

- Dark theme (#1a1a1a background)
- Material Design cards with rounded corners
- Battery card: 400x250dp, color-coded
- Time card: 400x200dp
- Music card: 600x250dp with playback controls
- App grid: 6 icons at bottom

## Current State

### Completed

✅ Project structure created
✅ All source files written
✅ Build configuration set up
✅ Vehicle service bindings implemented
✅ Media integration implemented
✅ UI layouts designed
✅ README and documentation created
✅ **First successful build completed (20 Jan 2026)**
✅ **APK generated and tested on Android Automotive emulator**
✅ **Display specifications determined (1920×1080 @ 160 dpi)**

### Testing Results

**Emulator Testing (20 Jan 2026):**

- ✅ App launches without crashes (332ms startup time)
- ✅ UI renders correctly at proper resolution/DPI
- ✅ Time/date updates automatically
- ✅ Battery card displays with fallback to mock data
- ✅ Vehicle service binding fails gracefully (expected on emulator)
- ⚠️ Music card blank (no media playing in emulator)

**Build Issues Resolved:**

1. Android SDK directory structure created at `/Users/adam/Library/Android/sdk`
2. Installed SDK platforms (33, 34) and build-tools
3. Added `gradle.properties` with AndroidX support
4. Updated compileSdk to 34 (required by Material Design 1.11.0)
5. Fixed invalid `auto` margin in XML layout
6. Corrected Java imports (Handler, ImageView, Context)

### Ready for Next Steps

📦 **APK Location**: `app/build/outputs/apk/debug/app-debug.apk` (11MB)
🔨 **Build command**: `./gradlew assembleDebug`
📱 **Deploy to emulator**: `adb install -r app/build/outputs/apk/debug/app-debug.apk`
🚗 **Deploy to car**: Same command when connected via ADB

### Not Yet Done

- ⏳ Test on actual vehicle (emulator testing complete)
- ⏳ Verify vehicle service connection works on real car
- ⏳ May need platform signature if permissions fail
- ⏳ Set as default launcher on device

## User's Questions Answered

**Q: Can I load R40 EvCharge on R67?**
A: Not recommended - dependencies changed, signature issues, API incompatibilities.

**Q: Can I use VS Code instead of Android Studio?**
A: Yes! Just need Java JDK and Android SDK command line tools. Build with Gradle.

**Q: Can I test on Mac?**
A: Limited - emulator won't have vehicle services. Best to deploy directly to car via ADB.

## Key Insights

1. **Firmware Evolution**: SAIC consolidated charging features from standalone app (R40) into main launcher (R67)

2. **Widget Support**: R67 launcher doesn't implement Android widget framework, so old widgets won't work anyway

3. **Vehicle Service Binding**: The vehicle data API exists and is used by stock launcher, so our custom launcher can use it too

4. **Testing Strategy**: Since this requires vehicle-specific services, testing on actual car is most practical

5. **Display Specifications**: Confirmed from firmware analysis:
   - Resolution: 1920×1080 pixels (Full HD)
   - Density: 160 dpi (mdpi) from `vendor/build.prop`
   - Layout qualifier: `layout-w1778dp` (minimum width)
   - Android Automotive emulator needs: `adb shell wm density 160 && adb shell wm size 1920x1080`

6. **Build Environment**: Homebrew-installed Android SDK tools require manual setup of SDK directory structure at `/Users/adam/Library/Android/sdk`

## Important Notes

- User has 1100 model MG4 (older, not Trophy)
- Currently running R67 firmware
- Wants cleaner interface, doesn't need charging control buttons
- Mac user, prefers VS Code over Android Studio
- Has all firmware versions extracted and available

## Files to Reference

**For future agent:**

- See AGENTS.md for technical details (in this directory)
- CustomLauncher/ directory has complete project (current location)
- Decompiled launchers in parent MG4 directory: `../*/extracted_apks/`
- Widget configs in `../1100 SWI69 R40 11950861 13.06.25/extracted_apks/EvCharge_eh32_ll/res/xml/`

## Next Agent Should

1. Help with deployment to actual car via ADB
2. Debug vehicle service connection on real vehicle
3. Help extract platform keys if signature needed for system permissions
4. Add AIDL interface if specifications can be obtained
5. Customize UI based on user feedback after testing on car
6. Optimize layouts if needed based on real display testing
7. Help set as default launcher on the vehicle
