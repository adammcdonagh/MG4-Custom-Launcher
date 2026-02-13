package com.custom.launcher.service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.custom.launcher.util.LogUtils;
import com.saicmotor.sdk.vehiclesettings.VehicleServiceContract;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleChargingBean;
import com.saicmotor.sdk.vehiclesettings.manager.BaseManager;
import com.saicmotor.sdk.vehiclesettings.manager.VehicleChargingManager;

/**
 * Service to connect to SAIC vehicle data services
 * Uses direct SDK imports (requires platform signing with android.uid.system)
 */
public class VehicleDataService {
    private static final String TAG = "VehicleDataService";

    private Context context;
    private VehicleDataListener listener;
    private boolean isBound = false;
    private boolean usingMockData = false;
    private VehicleChargingManager vehicleManager = null;

    // Polling for updates
    private Handler updateHandler;
    private static final long UPDATE_INTERVAL_MS = 10000; // Poll every 10 seconds
    private boolean isPolling = false;

    // Vehicle data
    private int batteryLevel = 0;
    private int rangeKm = 0;

    public interface VehicleDataListener {
        void onBatteryLevelChanged(int level);

        void onRangeChanged(int rangeKm);

        void onConnectionStatusChanged(boolean connected);
    }

    public VehicleDataService(Context context, VehicleDataListener listener) {
        this.context = context;
        this.listener = listener;
        this.updateHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Initialize SAIC SDK and access vehicle data directly
     */
    public void bind() {
        Log.i(TAG, "=================================================");
        Log.i(TAG, "[INIT] Starting SAIC SDK initialization...");
        Log.i(TAG, "=================================================");

        try {
            Log.i(TAG, "[SDK] Creating VehicleChargingManager with direct imports...");

            // Create service listener
            VehicleServiceContract.IVehicleServiceListener serviceListener = new VehicleServiceContract.IVehicleServiceListener() {
                @Override
                public void onServiceConnected(BaseManager manager) {
                    Log.i(TAG, "[SDK] ✓✓✓ Service connected! Manager: " + manager.getClass().getSimpleName());
                    vehicleManager = (VehicleChargingManager) manager;
                    isBound = true;
                    usingMockData = false;

                    if (listener != null) {
                        listener.onConnectionStatusChanged(true);
                    }

                    // Register for charging updates
                    registerChargingCallback();

                    // Read initial data
                    readVehicleData();
                    startPolling();
                }

                @Override
                public void onServiceDisconnected() {
                    Log.w(TAG, "[SDK] ✗ Service disconnected");
                    isBound = false;

                    if (listener != null) {
                        listener.onConnectionStatusChanged(false);
                    }
                }
            };

            // Initialize the SDK
            Log.i(TAG, "[SDK] Calling VehicleChargingManager.init()...");
            VehicleChargingManager.init(context, serviceListener, 1500L);
            Log.i(TAG, "[SDK] ✓ Init called, waiting for service connection callback...");

        } catch (Exception e) {
            Log.e(TAG, "[SDK] ✗✗✗ FAILED: Exception during SDK initialization");
            LogUtils.logError(TAG, "[SDK] Exception details", e);
            usingMockData = true;

            if (listener != null) {
                listener.onConnectionStatusChanged(false);
            }

            startMockDataUpdates();
        }
    }

    /**
     * Register callback for charging status updates
     */
    private void registerChargingCallback() {
        if (vehicleManager == null) {
            Log.w(TAG, "[SDK] Cannot register callback - manager is null");
            return;
        }

        try {
            VehicleServiceContract.IVehicleChargingCallback chargingCallback = new VehicleServiceContract.IVehicleChargingCallback() {
                @Override
                public void onVehicleChargingChangeEvent(VehicleChargingBean bean) {
                    Log.d(TAG, "[SDK] Charging status changed event received");
                    updateFromBean(bean);
                }

                @Override
                public void onVehicleChargingErrorEvent(String error, int code) {
                    Log.e(TAG, "[SDK] Charging error: " + error + " (code: " + code + ")");
                }
            };

            vehicleManager.registerVehicleChargingCallback(chargingCallback);
            Log.i(TAG, "[SDK] ✓ Registered charging callback");

        } catch (Exception e) {
            Log.e(TAG, "[SDK] Failed to register callback: " + e.getMessage());
            LogUtils.logError(TAG, "[SDK] Callback registration failed", e);
        }
    }

    /**
     * Read vehicle data from the SDK
     */
    private void readVehicleData() {
        if (vehicleManager == null) {
            Log.w(TAG, "[SDK] Cannot read data - manager is null");
            return;
        }

        try {
            VehicleChargingBean bean = vehicleManager.getVehicleChargingStatus();
            if (bean != null) {
                updateFromBean(bean);
            } else {
                Log.w(TAG, "[SDK] getVehicleChargingStatus() returned null");
            }
        } catch (Exception e) {
            Log.e(TAG, "[SDK] Error reading vehicle data: " + e.getMessage());
            LogUtils.logError(TAG, "[SDK] Read data exception", e);
        }
    }

    /**
     * Update local data from VehicleChargingBean
     */
    private void updateFromBean(VehicleChargingBean bean) {
        try {
            // Get battery percentage (returns Float like 78.5)
            Float batteryFloat = bean.getCurrentElectricQuantity();
            if (batteryFloat != null) {
                int newBatteryLevel = Math.round(batteryFloat);
                if (newBatteryLevel != batteryLevel) {
                    batteryLevel = newBatteryLevel;
                    Log.i(TAG, "[DATA] Battery: " + batteryLevel + "%");
                    if (listener != null) {
                        listener.onBatteryLevelChanged(batteryLevel);
                    }
                }
            }

            // Get range in kilometers
            Integer newRangeKm = bean.getCurrentEnduranceMileage();
            if (newRangeKm != null && newRangeKm != rangeKm) {
                rangeKm = newRangeKm;
                Log.i(TAG, "[DATA] Range: " + rangeKm + " km");
                if (listener != null) {
                    listener.onRangeChanged(rangeKm);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "[SDK] Error extracting data from bean: " + e.getMessage());
            LogUtils.logError(TAG, "[SDK] Bean extraction exception", e);
        }
    }

    /**
     * Start polling for vehicle data updates
     */
    private void startPolling() {
        if (isPolling) {
            Log.d(TAG, "[POLLING] Already polling");
            return;
        }

        isPolling = true;
        Log.i(TAG, "[POLLING] Starting updates every " + (UPDATE_INTERVAL_MS / 1000) + " seconds");

        updateHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isPolling && isBound) {
                    readVehicleData();
                    updateHandler.postDelayed(this, UPDATE_INTERVAL_MS);
                }
            }
        }, UPDATE_INTERVAL_MS);
    }

    /**
     * Stop polling for updates
     */
    private void stopPolling() {
        if (isPolling) {
            Log.i(TAG, "[POLLING] Stopping updates");
            isPolling = false;
            updateHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * Start mock data updates (fallback when SDK unavailable)
     */
    private void startMockDataUpdates() {
        Log.w(TAG, "[MOCK] Using mock vehicle data");
        usingMockData = true;

        // Simulate realistic battery/range data
        batteryLevel = 78;
        rangeKm = 285;

        if (listener != null) {
            listener.onBatteryLevelChanged(batteryLevel);
            listener.onRangeChanged(rangeKm);
        }

        // Update mock data every 30 seconds
        updateHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (usingMockData) {
                    // Slowly drain battery in mock mode
                    if (batteryLevel > 0) {
                        batteryLevel = Math.max(0, batteryLevel - 1);
                        rangeKm = Math.max(0, rangeKm - 4);

                        if (listener != null) {
                            listener.onBatteryLevelChanged(batteryLevel);
                            listener.onRangeChanged(rangeKm);
                        }
                    }
                    updateHandler.postDelayed(this, 30000);
                }
            }
        }, 30000);
    }

    /**
     * Unbind from vehicle service
     */
    public void unbind() {
        Log.i(TAG, "[SDK] Unbinding from vehicle service");
        stopPolling();
        isBound = false;

        if (vehicleManager != null) {
            try {
                // Cleanup if needed
                vehicleManager = null;
            } catch (Exception e) {
                Log.e(TAG, "[SDK] Error during unbind: " + e.getMessage());
            }
        }
    }

    /**
     * Get current battery level
     */
    public int getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * Get current range in kilometers
     */
    public int getRangeKm() {
        return rangeKm;
    }

    /**
     * Check if using mock data
     */
    public boolean isUsingMockData() {
        return usingMockData;
    }

    /**
     * Check if bound to vehicle service
     */
    public boolean isBound() {
        return isBound;
    }

    /**
     * Check if connected to vehicle service (alias for isBound)
     */
    public boolean isConnected() {
        return isBound;
    }
}
