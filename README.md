# Custom Launcher for MG4

A clean, modern Android launcher designed for the MG4 vehicle infotainment system.

## Features

- **Battery & Range Display**: Shows real-time battery level and estimated range
- **Time & Date Widget**: Clean display of current time and date
- **Now Playing Card**: Displays current media playback information with controls
- **App Shortcuts**: Quick access to installed applications
- **Clean Dark UI**: Modern Material Design with dark theme optimized for automotive use

## Key Components

### 1. Battery/Range Display

- Connects to SAIC vehicle service to get real-time battery data
- Color-coded battery card (green/yellow/red based on level)
- Shows remaining range in kilometers

### 2. Media Integration

- Uses Android MediaSession API to track playback
- Displays song title and artist
- Provides play/pause, previous, and next controls

### 3. Vehicle Service Integration

The launcher connects to the SAIC vehicle service using:

```java
com.saicmotor.telematics.VehicleService
```

## Installation

1. Build the APK using Android Studio or Gradle:

```bash
./gradlew assembleRelease
```

2. Install on the vehicle system:

```bash
adb install app/build/outputs/apk/release/app-release.apk
```

3. Set as default launcher:

- Go to Settings → Apps → Default Apps → Home app
- Select "Custom Launcher"

## Development Notes

### Vehicle Data Access

The vehicle service binding may require system-level permissions. To access vehicle data:

- The APK may need to be signed with the platform certificate
- Install as a system app in `/system/priv-app/`

### Testing

For development without vehicle access:

- The VehicleDataService includes mock data mode
- Media controls work with any media app using MediaSession

### Customization

Edit these files to customize the UI:

- `res/layout/activity_main.xml` - Main layout
- `res/values/colors.xml` - Color scheme
- `MainActivity.java` - Logic and behavior

## Based on Analysis

This launcher is based on analysis of the SAIC launcher from firmware R67:

- Package: `com.saicmotor.hmi.launcher`
- Vehicle service bindings extracted from decompiled APK
- UI inspired by the original charging display

## Requirements

- Android 9 (API 28) or higher
- MG4 vehicle system with SAIC vehicle services
- Notification listener permission for media tracking

## License

For personal use only.
