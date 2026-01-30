# MG4 Scripts

This directory contains utility scripts for MG4 infotainment system development and modification.

## Available Scripts

### 1. gather_system_info.sh

**Purpose:** Collect comprehensive system information from your MG4 to determine the best rooting method.

**Requirements:**

- ADB connection to car
- USB debugging enabled

**Usage:**

```bash
cd scripts
./gather_system_info.sh
```

**Output:**

- Creates directory `mg4_system_info_YYYYMMDD_HHMMSS/` with:
  - `mg4_properties.txt` - All system properties
  - `mg4_cpuinfo.txt` - CPU/SoC information
  - `mg4_partitions.txt` - Partition layout
  - `mg4_bootloader_status.txt` - Bootloader unlock status
  - `mg4_wifi_info.txt` - WiFi capabilities (for wireless CarPlay)
  - `mg4_hardware_info.txt` - Hardware specifications
  - `SUMMARY.txt` - Quick overview of key information

**What to do with the output:**

1. Review `SUMMARY.txt` for quick overview
2. Check `mg4_bootloader_status.txt` to see if bootloader is unlockable
3. Check `mg4_wifi_info.txt` to see if wireless CarPlay is possible
4. Share files for analysis to determine best rooting method

---

### 2. backup_firmware.sh

**Purpose:** Create backups of critical system partitions and files before making modifications.

**Requirements:**

- ADB connection to car
- **Root access** (for full backup)
- Sufficient storage space (~4-8 GB)

**Usage:**

```bash
cd scripts
./backup_firmware.sh
```

**What it backs up (with root):**

- ✅ Boot partition (boot.img)
- ✅ Recovery partition (recovery.img)
- ✅ System apps tarball
- ✅ Build properties
- ✅ Partition layout
- ⚠️ System/vendor partitions (optional, 2-4 GB each)

**What it backs up (without root):**

- ✅ Installed packages list
- ✅ System properties
- ✅ User data file list

**Output:**

- Creates directory `mg4_backup_YYYYMMDD_HHMMSS/` with all backup files
- Includes `BACKUP_INFO.txt` with backup details

**IMPORTANT:**

- Store backups in multiple locations (USB drive, cloud, etc.)
- Test restore procedure before making system changes
- Keep backups even after successful modifications

---

## Recommended Workflow

### Phase 1: Information Gathering (SAFE)

```bash
# 1. Connect car via USB
# 2. Enable USB debugging in car settings
# 3. Run info gathering script
./gather_system_info.sh

# 4. Review output
cd mg4_system_info_*/
cat SUMMARY.txt
```

### Phase 2: Backup (SAFE, but requires root for full backup)

```bash
# Only run after you have root access
./backup_firmware.sh

# Compress backup for storage
cd ..
tar -czf mg4_backup_YYYYMMDD.tar.gz mg4_backup_YYYYMMDD_HHMMSS/

# Store in multiple locations
cp mg4_backup_YYYYMMDD.tar.gz /Volumes/USB_DRIVE/
# Also upload to cloud storage
```

### Phase 3: Rooting (HIGH RISK)

See `docs/ROOTING_GUIDE.md` for detailed instructions.

### Phase 4: Modifications (Requires root)

After rooting:

- Replace stock launcher with custom launcher
- Modify CarPlay APK for wireless support
- Enable hidden features
- Install Magisk modules

---

## Troubleshooting

### "ERROR: No device connected via ADB"

```bash
# Check connection
adb devices

# If no device listed:
# 1. Check USB cable is data-capable (not charge-only)
# 2. Enable USB debugging in car settings
# 3. Accept "Allow USB debugging" prompt on car
```

### "Permission denied" errors

Most system modifications require root access. Run `gather_system_info.sh` first to determine if rooting is possible.

### Scripts don't run

```bash
# Make executable
chmod +x gather_system_info.sh
chmod +x backup_firmware.sh
```

---

## Safety Notes

⚠️ **Before running any scripts:**

1. Ensure car is parked and engine off
2. Keep car connected to charger (prevent battery drain)
3. Do not interrupt running scripts
4. Read all prompts carefully

🔴 **Rooting risks:**

- Can brick the infotainment system
- Voids warranty
- May disable vehicle features
- May trigger anti-theft systems

✅ **Safe operations:**

- Running `gather_system_info.sh` is safe (read-only)
- Backing up is safe (read-only if no root)
- Always keep multiple backups

---

## Additional Resources

- **Rooting Guide:** `../docs/ROOTING_GUIDE.md`
- **CarPlay Analysis:** `../docs/CARPLAY_USB_ANALYSIS.md`
- **Wireless CarPlay:** `../docs/WIRELESS_CARPLAY_FEASIBILITY.md`
- **Main Development Docs:** `../AGENTS.md`

---

**Created:** 30 January 2026  
**For:** MG4 Custom Launcher Project
