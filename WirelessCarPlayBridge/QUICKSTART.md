# Wireless CarPlay Bridge - Quick Start Guide

## What This Does

Enables wireless CarPlay on MG4 by creating a WiFi-to-USB bridge that makes your iPhone appear as a wired connection to the existing AllgoCarplay system.

## Architecture

```
iPhone (WiFi) → WirelessCarPlayBridge → Virtual USB Device → AllgoCarplay → Display
```

## One-Command Build & Sign

```bash
cd WirelessCarPlayBridge
./gradlew assembleDebug && ./sign_apk.sh
```

## Install on Car

```bash
adb install -r app/build/outputs/apk/debug/app-debug-signed.apk
```

## What Happens Next

1. **On boot**: Service starts automatically
2. **BLE advertising**: Your iPhone will see the car in Settings → CarPlay
3. **Pair once**: iPhone connects via WiFi Direct
4. **Auto-reconnect**: Future connections are automatic

## Testing

Open the app on the car's launcher to see:

- Service status
- Real-time logs
- Connection status

## Troubleshooting

### "Service not starting"

```bash
# Check logs
adb logcat | grep WirelessCarPlayBridge

# Manually start
adb shell am startservice com.mg4.wirelesscarplay/.WirelessCarPlayBridgeService
```

### "iPhone not appearing in CarPlay settings"

- Check Bluetooth is enabled on car
- Check logs for "BLE advertisement started"
- Restart the service

### "USB Gadget not working"

- This requires kernel CONFIG_USB_CONFIGFS support
- Check: `adb shell ls /config/usb_gadget`
- If missing: Consider hardware adapter instead (see README.md)

## Success Criteria

✅ BLE advertisement working → iPhone sees car  
✅ WiFi Direct connecting → iPhone connects  
✅ USB Gadget created → Virtual device appears  
✅ AllgoCarplay launches → CarPlay on display

## Alternative: Hardware Adapter

If USB Gadget doesn't work (kernel limitation), use:

- Carlinkit 3.0/4.0 ($80-120)
- OTTOCAST U2-AIR ($90-130)
- 95%+ success rate, plug-and-play

## More Info

See README.md for detailed documentation.
