package com.mg4.wirelesscarplay;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Bluetooth LE advertiser that announces CarPlay capability to nearby iPhones.
 * 
 * CarPlay uses Bluetooth LE for initial discovery:
 * - Service UUID: 0x0000FD8F (CarPlay Wireless service)
 * - Manufacturer Data: Apple Inc. (0x004C) with CarPlay-specific flags
 * 
 * When an iPhone sees this advertisement, it knows the car supports wireless CarPlay
 * and will attempt to establish a WiFi Direct connection.
 */
public class CarPlayBleAdvertiser {
    private static final String TAG = "BLEAdvertiser";
    
    // CarPlay Wireless service UUID (official Apple UUID)
    private static final UUID CARPLAY_SERVICE_UUID = 
        UUID.fromString("0000FD8F-0000-1000-8000-00805F9B34FB");
    
    // Apple manufacturer ID
    private static final int APPLE_MANUFACTURER_ID = 0x004C;
    
    private final Context context;
    private BluetoothAdapter bluetoothAdapter;
    private android.bluetooth.le.BluetoothLeAdvertiser bleAdvertiser;
    private boolean isAdvertising = false;

    public CarPlayBleAdvertiser(Context context) {
        this.context = context;
        if (!BuildConfig.MOCK_MODE) {
            initializeBluetooth();
        } else {
            Log.i(TAG, "MOCK MODE: Bluetooth initialization skipped");
        }
    }

    private void initializeBluetooth() {
        BluetoothManager bluetoothManager = 
            (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        
        if (bluetoothManager == null) {
            Log.e(TAG, "BluetoothManager not available");
            return;
        }
        
        bluetoothAdapter = bluetoothManager.getAdapter();
        
        if (bluetoothAdapter == null) {
            Log.e(TAG, "BluetoothAdapter not available");
            return;
        }
        
        if (!bluetoothAdapter.isEnabled()) {
            Log.w(TAG, "Bluetooth is disabled - attempting to enable");
            // Note: In a system app with proper permissions, we could enable it automatically
            // bluetoothAdapter.enable();
        }
        
        bleAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
        
        if (bleAdvertiser == null) {
            Log.e(TAG, "BLE advertising not supported on this device");
        }
    }

    public void startAdvertising() {
        if (BuildConfig.MOCK_MODE) {
            Log.i(TAG, "MOCK MODE: BLE advertising simulated - success");
            isAdvertising = true;
            return;
        }
        
        if (bleAdvertiser == null) {
            Log.e(TAG, "Cannot start advertising - BLE not available");
            return;
        }
        
        if (isAdvertising) {
            Log.w(TAG, "Already advertising");
            return;
        }
        
        try {
            // Configure advertisement settings
            AdvertiseSettings settings = new AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
                .setConnectable(true)
                .setTimeout(0) // Advertise indefinitely
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH)
                .build();
            
            // Build advertisement data
            AdvertiseData data = new AdvertiseData.Builder()
                .setIncludeDeviceName(true) // Include car name
                .setIncludeTxPowerLevel(false)
                .addServiceUuid(new ParcelUuid(CARPLAY_SERVICE_UUID))
                .addManufacturerData(APPLE_MANUFACTURER_ID, buildManufacturerData())
                .build();
            
            // Start advertising
            bleAdvertiser.startAdvertising(settings, data, advertiseCallback);
            
            Log.i(TAG, "Started BLE advertising for CarPlay");
            
        } catch (Exception e) {
            Log.e(TAG, "Failed to start BLE advertising", e);
        }
    }

    public void stopAdvertising() {
        if (BuildConfig.MOCK_MODE) {
            Log.i(TAG, "MOCK MODE: BLE advertising stopped");
            isAdvertising = false;
            return;
        }
        
        if (bleAdvertiser != null && isAdvertising) {
            try {
                bleAdvertiser.stopAdvertising(advertiseCallback);
                isAdvertising = false;
                Log.i(TAG, "Stopped BLE advertising");
            } catch (Exception e) {
                Log.e(TAG, "Error stopping BLE advertising", e);
            }
        }
    }

    public void cleanup() {
        stopAdvertising();
    }

    /**
     * Build manufacturer-specific data for CarPlay advertisement.
     * This data tells the iPhone that this device supports wireless CarPlay.
     * 
     * Format (based on CarPlay specification):
     * Byte 0: Type (0x09 = CarPlay Wireless)
     * Byte 1: Length
     * Bytes 2-3: Flags (0x0001 = supports wireless)
     * Bytes 4-9: Device MAC address (optional)
     */
    private byte[] buildManufacturerData() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        
        // CarPlay Wireless type
        buffer.put((byte) 0x09);
        
        // Length
        buffer.put((byte) 0x08);
        
        // Flags: 0x0001 = Wireless CarPlay supported
        buffer.putShort((short) 0x0001);
        
        // MAC address (get from WiFi interface)
        // For now, use placeholder - will be set dynamically
        buffer.put(new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00});
        
        return buffer.array();
    }

    private final AdvertiseCallback advertiseCallback = new AdvertiseCallback() {
        @Override
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            isAdvertising = true;
            Log.i(TAG, "BLE advertisement started successfully");
            Log.d(TAG, "iPhone should now see this car in CarPlay settings");
        }

        @Override
        public void onStartFailure(int errorCode) {
            isAdvertising = false;
            String errorMsg;
            switch (errorCode) {
                case ADVERTISE_FAILED_DATA_TOO_LARGE:
                    errorMsg = "Advertisement data too large";
                    break;
                case ADVERTISE_FAILED_TOO_MANY_ADVERTISERS:
                    errorMsg = "Too many advertisers";
                    break;
                case ADVERTISE_FAILED_ALREADY_STARTED:
                    errorMsg = "Already started";
                    break;
                case ADVERTISE_FAILED_INTERNAL_ERROR:
                    errorMsg = "Internal error";
                    break;
                case ADVERTISE_FAILED_FEATURE_UNSUPPORTED:
                    errorMsg = "Feature unsupported";
                    break;
                default:
                    errorMsg = "Unknown error: " + errorCode;
            }
            Log.e(TAG, "BLE advertisement failed: " + errorMsg);
        }
    };
}
