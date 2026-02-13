#!/bin/bash
set -e

echo "=========================================="
echo "Platform Key APK Signing Tool"
echo "=========================================="

cd "$(dirname "$0")"

APK_IN="app/build/outputs/apk/car/debug/app-car-debug.apk"
APK_OUT="app/build/outputs/apk/car/debug/app-car-debug-signed.apk"

if [ ! -f "$APK_IN" ]; then
    echo "❌ Error: APK not found at $APK_IN"
    echo "Run ./gradlew assembleDebug first"
    exit 1
fi

echo "✓ Found APK: $APK_IN"

# Check if keys exist
if [ ! -f "platform.pk8" ] || [ ! -f "platform.x509.pem" ]; then
    echo "❌ Error: Platform keys not found"
    echo "Downloading AOSP platform test keys..."
    curl -o platform.x509.pem https://raw.githubusercontent.com/aosp-mirror/platform_build/master/target/product/security/platform.x509.pem
    curl -o platform.pk8 https://raw.githubusercontent.com/aosp-mirror/platform_build/master/target/product/security/platform.pk8
    echo "✓ Keys downloaded"
fi

# Convert PK8 to PEM if not already done
if [ ! -f "platform.key" ]; then
    echo "Converting private key to PEM format..."
    openssl pkcs8 -inform DER -in platform.pk8 -out platform.key -nocrypt
    echo "✓ Key converted"
fi

# Create PKCS12 keystore
if [ ! -f "platform.p12" ]; then
    echo "Creating PKCS12 keystore..."
    openssl pkcs12 -export -in platform.x509.pem -inkey platform.key -out platform.p12 -name platform -passout pass:android
    echo "✓ Keystore created"
fi

# Copy APK to output location
cp "$APK_IN" "$APK_OUT"
echo "✓ Copied APK for signing"

# Sign with apksigner
APKSIGNER="$HOME/Library/Android/sdk/build-tools/34.0.0/apksigner"

if [ ! -f "$APKSIGNER" ]; then
    echo "❌ Error: apksigner not found at $APKSIGNER"
    echo "Install Android SDK build-tools"
    exit 1
fi

echo "Signing APK with platform keys..."
"$APKSIGNER" sign --ks platform.p12 --ks-key-alias platform --ks-pass pass:android "$APK_OUT"

echo ""
echo "=========================================="
echo "✅ APK signed successfully!"
echo "=========================================="
echo "Output: $APK_OUT"
echo ""
echo "Verifying signature..."
"$APKSIGNER" verify --print-certs "$APK_OUT"
echo ""
echo "Install with:"
echo "  adb install -r $APK_OUT"
echo ""
