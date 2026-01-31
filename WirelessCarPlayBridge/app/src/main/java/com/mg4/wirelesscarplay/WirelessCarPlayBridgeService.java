package com.mg4.wirelesscarplay;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

/**
 * Main service that bridges WiFi Direct CarPlay connections to USB virtual device.
 * 
 * Architecture:
 * 1. BLE Advertiser: Announces CarPlay capability to nearby iPhones
 * 2. WiFi P2P Manager: Establishes WiFi Direct connection when iPhone pairs
 * 3. USB Gadget Controller: Creates virtual USB device that AllgoCarplay sees
 * 4. Data Bridge: Routes WiFi data through virtual USB interface
 */
public class WirelessCarPlayBridgeService extends Service {
    private static final String TAG = "WirelessCarPlayBridge";
    
    private HandlerThread serviceThread;
    private Handler serviceHandler;
    
    private CarPlayBleAdvertiser bleAdvertiser;
    private WiFiDirectManager wifiDirectManager;
    private UsbGadgetController usbGadgetController;
    private DataBridge dataBridge;
    
    private volatile boolean isRunning = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "WirelessCarPlayBridgeService created");
        
        // Create background thread for service operations
        serviceThread = new HandlerThread("WirelessCarPlayBridgeThread");
        serviceThread.start();
        serviceHandler = new Handler(serviceThread.getLooper());
        
        // Initialize components
        serviceHandler.post(() -> {
            try {
                initializeComponents();
            } catch (Exception e) {
                Log.e(TAG, "Failed to initialize components", e);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "WirelessCarPlayBridgeService started");
        
        if (!isRunning) {
            serviceHandler.post(() -> {
                try {
                    startBridge();
                } catch (Exception e) {
                    Log.e(TAG, "Failed to start bridge", e);
                }
            });
        }
        
        return START_STICKY; // Auto-restart if killed
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "WirelessCarPlayBridgeService destroyed");
        
        isRunning = false;
        
        // Clean up components
        serviceHandler.post(() -> {
            try {
                stopBridge();
                cleanupComponents();
            } catch (Exception e) {
                Log.e(TAG, "Error during cleanup", e);
            }
        });
        
        serviceThread.quitSafely();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // Not a bound service
    }

    private void initializeComponents() {
        Log.d(TAG, "Initializing components...");
        
        // Initialize BLE advertiser
        bleAdvertiser = new CarPlayBleAdvertiser(this);
        
        // Initialize WiFi Direct manager
        wifiDirectManager = new WiFiDirectManager(this, new WiFiDirectManager.ConnectionCallback() {
            @Override
            public void onDeviceConnected(String deviceAddress) {
                Log.i(TAG, "iPhone connected via WiFi Direct: " + deviceAddress);
                serviceHandler.post(() -> handlePhoneConnected(deviceAddress));
            }

            @Override
            public void onDeviceDisconnected() {
                Log.i(TAG, "iPhone disconnected from WiFi Direct");
                serviceHandler.post(() -> handlePhoneDisconnected());
            }

            @Override
            public void onConnectionFailed(String reason) {
                Log.e(TAG, "WiFi Direct connection failed: " + reason);
            }
        });
        
        // Initialize USB gadget controller
        usbGadgetController = new UsbGadgetController(this);
        
        // Initialize data bridge
        dataBridge = new DataBridge(this);
        
        Log.i(TAG, "All components initialized");
    }

    private void startBridge() {
        Log.i(TAG, "Starting wireless CarPlay bridge...");
        
        isRunning = true;
        
        // Step 1: Start BLE advertisement to announce CarPlay capability
        bleAdvertiser.startAdvertising();
        Log.d(TAG, "BLE advertisement started");
        
        // Step 2: Start listening for WiFi Direct connections
        wifiDirectManager.startListening();
        Log.d(TAG, "WiFi Direct listener started");
        
        // Step 3: Create virtual USB device (ready for connection)
        usbGadgetController.createVirtualDevice();
        Log.d(TAG, "Virtual USB device created");
        
        Log.i(TAG, "Wireless CarPlay bridge is running - waiting for iPhone...");
    }

    private void stopBridge() {
        Log.i(TAG, "Stopping wireless CarPlay bridge...");
        
        isRunning = false;
        
        // Stop all components
        if (dataBridge != null) {
            dataBridge.stop();
        }
        
        if (usbGadgetController != null) {
            usbGadgetController.destroyVirtualDevice();
        }
        
        if (wifiDirectManager != null) {
            wifiDirectManager.stopListening();
        }
        
        if (bleAdvertiser != null) {
            bleAdvertiser.stopAdvertising();
        }
        
        Log.i(TAG, "Bridge stopped");
    }

    private void cleanupComponents() {
        if (bleAdvertiser != null) {
            bleAdvertiser.cleanup();
        }
        if (wifiDirectManager != null) {
            wifiDirectManager.cleanup();
        }
        if (usbGadgetController != null) {
            usbGadgetController.cleanup();
        }
        if (dataBridge != null) {
            dataBridge.cleanup();
        }
    }

    private void handlePhoneConnected(String deviceAddress) {
        Log.i(TAG, "Handling phone connection: " + deviceAddress);
        
        // Step 1: Activate virtual USB device (appear as connected iPhone)
        boolean usbReady = usbGadgetController.activateDevice();
        if (!usbReady) {
            Log.e(TAG, "Failed to activate USB virtual device");
            return;
        }
        
        // Step 2: Start data bridge between WiFi and USB
        boolean bridgeStarted = dataBridge.start(
            wifiDirectManager.getSocket(),
            usbGadgetController.getFileDescriptor()
        );
        
        if (bridgeStarted) {
            Log.i(TAG, "Data bridge active - CarPlay should launch now");
        } else {
            Log.e(TAG, "Failed to start data bridge");
        }
    }

    private void handlePhoneDisconnected() {
        Log.i(TAG, "Handling phone disconnection");
        
        // Stop data bridge
        dataBridge.stop();
        
        // Deactivate USB virtual device
        usbGadgetController.deactivateDevice();
        
        Log.i(TAG, "Ready for next connection");
    }
}
