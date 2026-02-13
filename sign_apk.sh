#!/bin/bash

# AOSP Platform Key Signing Script for Custom Launcher
# This script signs the APK with the AOSP platform test keys found in SAIC's firmware
#
# These keys allow the app to run with system-level privileges (android.uid.system)
# enabling direct access to SAIC SDK classes without reflection

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Default to car flavor if not specified
FLAVOR="${1:-car}"
BUILD_TYPE="${2:-debug}"

APK_PATH="$SCRIPT_DIR/app/build/outputs/apk/$FLAVOR/$BUILD_TYPE/app-$FLAVOR-$BUILD_TYPE.apk"
SIGNED_APK_PATH="$SCRIPT_DIR/app/build/outputs/apk/$FLAVOR/$BUILD_TYPE/app-$FLAVOR-$BUILD_TYPE-signed.apk"

# AOSP platform test keys URLs (public keys from Android source)
PLATFORM_PEM_URL="https://raw.githubusercontent.com/aosp-mirror/platform_build/master/target/product/security/platform.x509.pem"
PLATFORM_PK8_URL="https://raw.githubusercontent.com/aosp-mirror/platform_build/master/target/product/security/platform.pk8"

PLATFORM_PEM="$SCRIPT_DIR/platform.x509.pem"
PLATFORM_PK8="$SCRIPT_DIR/platform.pk8"

echo "========================================================"
echo "AOSP Platform Key Signing Script"
echo "========================================================"
echo "Flavor: $FLAVOR"
echo "Build Type: $BUILD_TYPE"
echo ""

# Check if APK exists
if [ ! -f "$APK_PATH" ]; then
    echo "❌ Error: APK not found at $APK_PATH"
    echo ""
    echo "Build it first with:"
    # Capitalize first letter of flavor and build type
    FLAVOR_CAP="$(echo ${FLAVOR:0:1} | tr '[:lower:]' '[:upper:]')${FLAVOR:1}"
    BUILD_TYPE_CAP="$(echo ${BUILD_TYPE:0:1} | tr '[:lower:]' '[:upper:]')${BUILD_TYPE:1}"
    echo "  ./gradlew assemble${FLAVOR_CAP}${BUILD_TYPE_CAP}"
    echo ""
    echo "Usage: $0 [flavor] [buildType]"
    echo "  flavor: car (default) or emulator"
    echo "  buildType: debug (default) or release"
    exit 1
fi

echo "✓ Found unsigned APK: $APK_PATH"

# Download platform keys if not present
if [ ! -f "$PLATFORM_PEM" ] || [ ! -f "$PLATFORM_PK8" ]; then
    echo "📥 Downloading AOSP platform keys..."
    
    if command -v curl &> /dev/null; then
        curl -L -o "$PLATFORM_PEM" "$PLATFORM_PEM_URL" --silent --show-error
        curl -L -o "$PLATFORM_PK8" "$PLATFORM_PK8_URL" --silent --show-error
    elif command -v wget &> /dev/null; then
        wget -O "$PLATFORM_PEM" "$PLATFORM_PEM_URL" -q
        wget -O "$PLATFORM_PK8" "$PLATFORM_PK8_URL" -q
    else
        echo "❌ Error: Neither curl nor wget found. Please install one of them."
        exit 1
    fi
    
    echo "✓ Downloaded AOSP platform keys"
else
    echo "✓ Using cached AOSP platform keys"
fi

# Check if apksigner is available
if ! command -v apksigner &> /dev/null; then
    # Try to find apksigner in Android SDK
    if [ -n "$ANDROID_HOME" ]; then
        APKSIGNER="$ANDROID_HOME/build-tools/$(ls -1 "$ANDROID_HOME/build-tools" | tail -1)/apksigner"
        if [ ! -f "$APKSIGNER" ]; then
            echo "❌ Error: apksigner not found in Android SDK"
            echo "Install it with: sdkmanager \"build-tools;34.0.0\""
            exit 1
        fi
    else
        echo "❌ Error: apksigner not found and ANDROID_HOME not set"
        echo "Install Android SDK command-line tools and set ANDROID_HOME"
        exit 1
    fi
else
    APKSIGNER="apksigner"
fi

echo "✓ Found apksigner: $APKSIGNER"

# Sign the APK
echo "🔐 Signing APK with AOSP platform keys..."

# Remove old signed APK if exists
rm -f "$SIGNED_APK_PATH"

# Sign with apksigner (supports v2/v3 signing)
$APKSIGNER sign \
    --key "$PLATFORM_PK8" \
    --cert "$PLATFORM_PEM" \
    --out "$SIGNED_APK_PATH" \
    "$APK_PATH"

echo "✓ APK signed successfully"

# Verify signature
echo "🔍 Verifying signature..."
$APKSIGNER verify --verbose "$SIGNED_APK_PATH"

# Get APK info
APK_SIZE=$(du -h "$SIGNED_APK_PATH" | awk '{print $1}')

echo "========================================================"
echo "✅ SUCCESS!"
echo "========================================================"
echo "Signed APK: $SIGNED_APK_PATH"
echo "Size: $APK_SIZE"
echo ""
echo "Install with:"
echo "  adb install -r \"$SIGNED_APK_PATH\""
echo ""
echo "⚠️  IMPORTANT: This APK has android.uid.system privileges"
echo "    It can access SAIC SDK classes directly without reflection"
echo "========================================================"
