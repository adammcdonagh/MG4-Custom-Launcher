# Can AllgoCarplay Be Modified Without Root? - Investigation Results

## Date: 30 January 2026

---

## Location Correction ✅

**YOU WERE RIGHT!** I incorrectly stated the location in previous documents.

**Actual location:**

```
/system/app/AllgoCarplay_EH32/AllgoCarplay_EH32.apk
```

**NOT** `/system/priv-app/` as I previously stated.

---

## Key Finding: sharedUserId="android.uid.system"

From `AndroidManifest.xml`:

```xml
<manifest
    android:sharedUserId="android.uid.system"
    package="com.allgo.carplay.service">
```

### What This Means:

**android.uid.system = UID 1000**

- This is the **system server** user ID
- Apps with this UID have elevated privileges
- Can access protected system APIs
- Runs with same permissions as core Android system services

---

## Can You Modify Without Root? Analysis

### Option 1: Replace in /system/app/ (Requires Root ❌)

```bash
# Without root - FAILS
adb push modified_carplay.apk /system/app/AllgoCarplay_EH32/
# Error: Read-only filesystem

# With root - WORKS
adb shell su -c "mount -o remount,rw /system"
adb shell su -c "cp modified_carplay.apk /system/app/AllgoCarplay_EH32/"
```

**Verdict:** ❌ Replacing in `/system/app/` requires root access.

---

### Option 2: Install as User App (No Root, But Limited ⚠️)

**Can you install a modified APK as a regular app?**

```bash
# Decompile AllgoCarplay
apktool d AllgoCarplay_EH32.apk

# Modify code (e.g., enable wireless support)
# ... make changes ...

# Rebuild
apktool b AllgoCarplay_EH32_decompiled -o AllgoCarplay_modified.apk

# Sign with your debug key (NOT platform key)
jarsigner -keystore ~/.android/debug.keystore AllgoCarplay_modified.apk androiddebugkey

# Try to install
adb install AllgoCarplay_modified.apk
```

**What happens:**

✅ **Installation will succeed** IF you remove `android:sharedUserId="android.uid.system"`

❌ **Installation will FAIL** with error: `INSTALL_FAILED_SHARED_USER_INCOMPATIBLE` if you keep the sharedUserId

**If you remove the sharedUserId:**

- ✅ App installs successfully
- ✅ App runs (won't crash immediately)
- ❌ **BUT** it won't have system permissions
- ❌ Won't be able to access `CAR_PROJECTION` permission
- ❌ Won't be able to bind to system services
- ❌ **USB device access will likely fail** (needs system permissions for AOAP)

---

### Option 3: Sign with Platform Certificate (No Root, But Need Certificate 🔐)

**Platform signing** allows a user app to have system-level permissions.

**Requirements:**

1. Extract platform signing keys from MG4 firmware
2. Sign your modified APK with those keys
3. Install as user app (no root needed!)

#### Step 1: Extract Platform Keys from Firmware

Platform keys are typically in:

```
/system/etc/security/
  - platform.pk8 (private key)
  - platform.x509.pem (certificate)
```

**Try to extract:**

```bash
# Check if keys are in system image
cd "/Users/adam/Downloads/MG4/CustomLauncher/mg_firmware/1300 SWI68 R67/extracted_payload/system_extracted"
find . -name "*.pk8" -o -name "*.x509.pem" -o -name "platform*"

# Keys are usually NOT included in OTA packages (security risk)
# They're only on build servers
```

**If keys not found:** You'd need to extract from a signed APK, but that's not possible (private keys can't be extracted from signed APKs).

#### Step 2: Alternative - Sign with Allgo's Keys

AllgoCarplay is already signed with **some** certificate. You could:

1. Try to sign with the same certificate (if you can extract it)
2. But you won't have the private key, so you can't

**Verdict:** ❌ Platform signing requires keys you don't have.

---

## The /system/app vs /system/priv-app Difference

**Key Differences:**

| Feature               | /system/app/                                | /system/priv-app/                            |
| --------------------- | ------------------------------------------- | -------------------------------------------- |
| **Permissions**       | Can request privileged permissions          | Automatically granted privileged permissions |
| **Install location**  | Regular system apps                         | Core system apps                             |
| **User installation** | Can install signed APK (if no sharedUserId) | Cannot install as user app                   |
| **Modification**      | Requires root OR platform signing           | Requires root                                |

**Important:** Even though AllgoCarplay is in `/system/app/`, it has `sharedUserId="android.uid.system"` which gives it elevated privileges **equivalent** to priv-app apps.

---

## Practical Workarounds (Without Root)

### Workaround 1: Create a Wrapper/Launcher App ✅

Instead of modifying AllgoCarplay, create your own app that:

```java
// Your custom app (no special permissions needed)
public class CarPlayLauncher extends Activity {

    // When USB device connected
    @Override
    public void onCreate(Bundle savedInstanceState) {
        UsbDevice device = getIntent().getParcelableExtra(UsbManager.EXTRA_DEVICE);

        if (isIPhone(device)) {
            // Launch AllgoCarplay
            Intent carPlayIntent = new Intent();
            carPlayIntent.setComponent(new ComponentName(
                "com.allgo.carplay.service",
                "com.allgo.carplay.service.CarPlayActivity"
            ));
            startActivity(carPlayIntent);
        }
    }
}
```

**What this allows:**

- ✅ Detect USB devices
- ✅ Launch AllgoCarplay when iPhone connected
- ✅ Add UI improvements around CarPlay
- ❌ Still can't enable wireless CarPlay (that requires modifying AllgoCarplay itself)

---

### Workaround 2: Use Wireless CarPlay Adapter ✅

As discussed in WIRELESS_CARPLAY_FEASIBILITY.md:

- Buy a wireless CarPlay dongle ($50-150)
- Plug into USB port
- iPhone connects wirelessly to dongle
- Dongle presents as wired iPhone to AllgoCarplay
- **No system modifications needed!**

---

### Workaround 3: Modify + Install Without sharedUserId ⚠️

**Experimental approach:**

1. Decompile AllgoCarplay
2. Remove `android:sharedUserId="android.uid.system"` from manifest
3. Modify code to remove dependencies on system APIs
4. Sign with your own key
5. Install as user app

**Challenges:**

```java
// These will FAIL without system UID:
Context.checkPermission("android.car.permission.CAR_PROJECTION");
// Returns: PERMISSION_DENIED

UsbManager.requestPermission(usbDevice);
// May fail for AOAP devices

bindService(carServiceIntent, connection, BIND_AUTO_CREATE);
// May fail for system services
```

**Verdict:** ⚠️ Possible but extremely limited functionality.

---

## Conclusion: Do You Need Root?

### To Install Modified AllgoCarplay:

| Modification Goal                       | Needs Root?               | Alternative                        |
| --------------------------------------- | ------------------------- | ---------------------------------- |
| **Replace system APK in /system/app/**  | ✅ YES                    | Platform signing (if you had keys) |
| **Enable wireless CarPlay**             | ✅ YES (or platform keys) | Buy wireless adapter               |
| **Change UI/appearance**                | ✅ YES (or platform keys) | Create wrapper app                 |
| **Add logging/debugging**               | ✅ YES (or platform keys) | Use wrapper + logcat               |
| **Launch CarPlay from custom launcher** | ❌ NO                     | Already possible via Intent        |

### Summary:

**For meaningful modifications to AllgoCarplay (like enabling wireless support):**

- 🔴 **YES, you need root** (to replace in `/system/app/`)
- OR 🔐 **Need platform signing keys** (which you don't have)
- OR 🟢 **Buy wireless adapter** (easiest solution)

**For working around AllgoCarplay without modifying it:**

- 🟢 **NO root needed** - you can create wrapper apps, detect USB, launch CarPlay

---

## Corrected Rooting Documentation

I need to update these files with the correct location:

- ❌ Old: `/system/priv-app/AllgoCarplay_EH32/`
- ✅ Correct: `/system/app/AllgoCarplay_EH32/`

The rooting requirements are **still the same** - you need root to modify `/system/app/` because:

1. `/system` partition is mounted read-only
2. Requires root to remount read-write
3. Even if read-write, system file modifications need system permissions

---

## Updated Answer to Your Question:

**Q: Does AllgoCarplay modification really need root?**

**A: YES, for these reasons:**

1. **Location is `/system/app/` not `/system/priv-app/`** (you caught my error ✅)

2. **BUT the `/system` partition is read-only:**

   ```bash
   $ adb shell mount | grep system
   /dev/block/dm-0 on /system type ext4 (ro,seclabel,relatime)
                                           ^^  read-only!
   ```

3. **Remounting requires root:**

   ```bash
   $ adb shell mount -o remount,rw /system
   mount: Permission denied (you must be root)
   ```

4. **The `sharedUserId="android.uid.system"` means:**
   - If you install as user app, must remove this line
   - But removing it breaks access to system APIs
   - CarPlay projection won't work without system permissions

**Therefore:** Root is required for **any practical modification** of AllgoCarplay.

**Exception:** You can create your own wrapper apps without root (but can't modify CarPlay functionality itself).

---

**Document Created:** 30 January 2026  
**Correction:** AllgoCarplay is in `/system/app/` not `/system/priv-app/`  
**Conclusion:** Root still required for modifications
