#!/bin/bash
# Sign WirelessCarPlayBridge APK with AOSP platform keys

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$SCRIPT_DIR"
KEYS_DIR="$PROJECT_ROOT/platform_keys"
APK_DIR="$PROJECT_ROOT/app/build/outputs/apk/debug"
UNSIGNED_APK="$APK_DIR/app-debug.apk"
SIGNED_APK="$APK_DIR/app-debug-signed.apk"

echo "==================================="
echo "Wireless CarPlay Bridge - APK Signer"
echo "==================================="
echo ""

# Check if unsigned APK exists
if [ ! -f "$UNSIGNED_APK" ]; then
    echo "❌ Unsigned APK not found: $UNSIGNED_APK"
    echo "Run './gradlew assembleDebug' first"
    exit 1
fi

echo "✅ Found unsigned APK"

# Create keys directory if it doesn't exist
mkdir -p "$KEYS_DIR"

# Download AOSP platform keys if not present
if [ ! -f "$KEYS_DIR/platform.pk8" ] || [ ! -f "$KEYS_DIR/platform.x509.pem" ]; then
    echo ""
    echo "📥 Downloading AOSP platform keys..."
    
    # Download private key
    curl -s "https://android.googlesource.com/platform/build/+/refs/heads/master/target/product/security/platform.pk8?format=TEXT" | base64 -D > "$KEYS_DIR/platform.pk8"
    
    # Download certificate
    curl -s "https://android.googlesource.com/platform/build/+/refs/heads/master/target/product/security/platform.x509.pem?format=TEXT" | base64 -D > "$KEYS_DIR/platform.x509.pem"
    
    echo "✅ Downloaded AOSP platform keys"
else
    echo "✅ Platform keys already present"
fi

# Find apksigner
ANDROID_SDK="${ANDROID_HOME:-$HOME/Library/Android/sdk}"
APKSIGNER=""

# Try to find apksigner in SDK
if [ -d "$ANDROID_SDK/build-tools" ]; then
    LATEST_BUILD_TOOLS=$(ls -1 "$ANDROID_SDK/build-tools" | sort -V | tail -1)
    if [ -f "$ANDROID_SDK/build-tools/$LATEST_BUILD_TOOLS/apksigner" ]; then
        APKSIGNER="$ANDROID_SDK/build-tools/$LATEST_BUILD_TOOLS/apksigner"
        echo "✅ Found apksigner: $LATEST_BUILD_TOOLS"
    fi
fi

if [ -z "$APKSIGNER" ]; then
    echo "❌ apksigner not found in Android SDK"
    echo "Install Android SDK build-tools: sdkmanager 'build-tools;34.0.0'"
    exit 1
fi

# Sign the APK
echo ""
echo "🔐 Signing APK with platform keys..."

"$APKSIGNER" sign \
    --key "$KEYS_DIR/platform.pk8" \
    --cert "$KEYS_DIR/platform.x509.pem" \
    --out "$SIGNED_APK" \
    "$UNSIGNED_APK"

if [ $? -eq 0 ]; then
    echo "✅ APK signed successfully"
else
    echo "❌ Failed to sign APK"
    exit 1
fi

# Verify signature
echo ""
echo "🔍 Verifying signature..."

"$APKSIGNER" verify --print-certs "$SIGNED_APK" | grep -A 2 "Signer #1"

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Signature verified"
else
    echo "❌ Signature verification failed"
    exit 1
fi

# Show file info
echo ""
echo "📦 Signed APK info:"
echo "   Location: $SIGNED_APK"
echo "   Size: $(du -h "$SIGNED_APK" | cut -f1)"
echo ""
echo "✅ Ready to install on MG4!"
echo ""
echo "Install command:"
echo "   adb install -r $SIGNED_APK"
echo ""
