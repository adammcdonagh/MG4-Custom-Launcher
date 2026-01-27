package com.custom.launcher.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.custom.launcher.util.LogUtils;
import java.lang.reflect.Method;

/**
 * Service to connect to SAIC vehicle data services
 * This binds to the vehicle service to get battery level, range, and other
 * vehicle data
 */
public class VehicleDataService {
    private static final String TAG = "VehicleDataService";

    // SAIC Vehicle SDK package (used by R67 launcher)
    // The launcher accesses vehicle data through com.saicmotor.sdk.vehiclesettings
    // classes
    // We need to get VehicleChargingManager from the SAIC SDK
    private static final String VEHICLE_SDK_CHARGING_MANAGER = "com.saicmotor.sdk.vehiclesettings.manager.VehicleChargingManager";
    private static final String VEHICLE_CHARGING_BEAN = "com.saicmotor.sdk.vehiclesettings.bean.VehicleChargingBean";

    // Fallback: Try AIDL service (but SDK approach is preferred)
    private static final String VEHICLE_SERVICE_PACKAGE = "com.saicmotor.service.vehicle";
    private static final String VEHICLE_SERVICE_ACTION = "com.saicmotor.service.vehicle.VehicleService";

    private Context context;
    private VehicleDataListener listener;
    private boolean isBound = false;
    private boolean bindAttempted = false;
    private boolean usingMockData = false;
    private Object vehicleServiceInterface = null;

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
    }

    /**
     * Initialize SAIC SDK and access vehicle data
     * The R67 launcher calls VehicleChargingManager.init() then getInstance()
     */
    public void bind() {
        Log.i(TAG, "=================================================");
        Log.i(TAG, "[INIT] Starting SAIC SDK initialization...");
        Log.i(TAG, "=================================================");
        bindAttempted = true;

        // Try to initialize and access SAIC SDK directly (no service binding needed)
        try {
            Log.i(TAG, "[SDK] Step 1: Loading VehicleChargingManager class...");
            Class<?> managerClass = Class.forName(VEHICLE_SDK_CHARGING_MANAGER);
            Log.i(TAG, "[SDK] ✓ Class loaded: " + managerClass.getName());

            // Try init() method first (R67 launcher calls this)
            try {
                Log.i(TAG, "[SDK] Step 2: Looking for init(Context, Listener, long) method...");
                // VehicleChargingManager.init(Context, IVehicleServiceListener, long)
                // We'll pass null for listener since we'll poll data instead of using callbacks
                Class<?> listenerClass = null;
                try {
                    listenerClass = Class.forName(
                            "com.saicmotor.sdk.vehiclesettings.VehicleServiceContract$IVehicleServiceListener");
                    Log.i(TAG, "[SDK] ✓ Found listener interface: " + listenerClass.getName());
                } catch (ClassNotFoundException e) {
                    Log.w(TAG, "[SDK] Listener class not found, will pass null");
                }

                Method initMethod = managerClass.getMethod("init", Context.class, listenerClass, long.class);
                Log.i(TAG, "[SDK] ✓ Found init() method");
                Log.i(TAG, "[SDK] Step 3: Calling init(context, null, 0)...");
                initMethod.invoke(null, context, null, 0L);
                Log.i(TAG, "[SDK] ✓ init() completed successfully");
            } catch (NoSuchMethodException e) {
                Log.w(TAG, "[SDK] init() method not found: " + e.getMessage());
                Log.i(TAG, "[SDK] Will try getInstance() directly...");
            }

            // Now try getInstance()
            Log.i(TAG, "[SDK] Step 4: Calling getInstance(context)...");
            Method getInstance = managerClass.getMethod("getInstance", Context.class);
            vehicleServiceInterface = getInstance.invoke(null, context);

            if (vehicleServiceInterface != null) {
                Log.i(TAG, "[SDK] ✓✓✓ SUCCESS! Got VehicleChargingManager instance");
                Log.i(TAG, "[SDK] Instance class: " + vehicleServiceInterface.getClass().getName());
                usingMockData = false;

                if (listener != null) {
                    listener.onConnectionStatusChanged(true);
                }

                // Try to read vehicle data immediately
                Log.i(TAG, "[SDK] Step 5: Reading vehicle data...");
                readVehicleData();
            } else {
                Log.e(TAG, "[SDK] ✗✗✗ FAILED: getInstance() returned null");
                Log.e(TAG, "[SDK] The SAIC SDK may not be available on this system");
                usingMockData = true;
                if (listener != null) {
                    listener.onConnectionStatusChanged(false);
                }
                startMockDataUpdates();
            }

        } catch (ClassNotFoundException e) {
            Log.e(TAG, "[SDK] ✗✗✗ FAILED: VehicleChargingManager class not found");
            Log.e(TAG, "[SDK] Class name tried: " + VEHICLE_SDK_CHARGING_MANAGER);
            Log.e(TAG, "[SDK] This means the SAIC SDK is not present in this Android system");
            LogUtils.logError(TAG, "[SDK] Exception details", e);
            usingMockData = true;
            if (listener != null) {
                listener.onConnectionStatusChanged(false);
            }
            startMockDataUpdates();
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "[SDK] ✗✗✗ FAILED: getInstance() method not found");
            Log.e(TAG, "[SDK] The SDK API may be different than expected");
            LogUtils.logError(TAG, "[SDK] Exception details", e);
            usingMockData = true;
            if (listener != null) {
                listener.onConnectionStatusChanged(false);
            }
            startMockDataUpdates();
        } catch (Exception e) {
            Log.e(TAG, "[SDK] ✗✗✗ FAILED: Exception during SDK initialization");
            LogUtils.logError(TAG, "[SDK] Exception details", e);
            usingMockData = true;
            if (listener != null) {
                listener.onConnectionStatusChanged(false);
            }
            startMockDataUpdates();
        }

        Log.i(TAG, "=================================================");
        Log.i(TAG, "[INIT] Initialization complete. Using mock data: " + usingMockData);
        Log.i(TAG, "=================================================");
    }

    public void unbind() {
        Log.i(TAG, "[CLEANUP] Cleaning up vehicle data service...");
        vehicleServiceInterface = null;
        isBound = false;
    }

    /**
     * Try to read vehicle data from the SDK manager using reflection
     */
    private void readVehicleData() {
        Log.i(TAG, "=================================================");
        Log.i(TAG, "[DATA] Starting vehicle data read...");
        Log.i(TAG, "=================================================");

        if (vehicleServiceInterface == null) {
            Log.e(TAG, "[DATA] ✗✗✗ Vehicle service interface is null - cannot read data");
            return;
        }

        try {
            // vehicleServiceInterface is now VehicleChargingManager
            // We need to call getVehicleChargingStatus() to get VehicleChargingBean
            // Then call bean.getElectricityLevel() for battery percentage

            Class<?> managerClass = vehicleServiceInterface.getClass();
            Log.i(TAG, "[DATA] Manager instance class: " + managerClass.getName());
            Log.i(TAG, "[DATA] Manager toString: " + vehicleServiceInterface.toString());

            // List all methods for debugging
            Method[] methods = managerClass.getMethods();
            Log.i(TAG, "[DATA] Total methods available: " + methods.length);
            Log.i(TAG, "[DATA] Relevant methods:");
            int relevantCount = 0;
            for (Method method : methods) {
                String name = method.getName().toLowerCase();
                if (name.contains("battery") || name.contains("electric") || name.contains("soc") ||
                        name.contains("range") || name.contains("endurance") || name.contains("charging") ||
                        name.contains("status") || name.contains("bean") || name.contains("mileage")) {
                    Log.i(TAG, "[DATA]   ✓ " + method.getName() + "() -> " + method.getReturnType().getSimpleName());
                    relevantCount++;
                }
            }
            Log.i(TAG, "[DATA] Found " + relevantCount + " relevant methods");

            // Try to get VehicleChargingBean (R67 launcher method)
            Log.i(TAG, "[DATA] Attempting getVehicleChargingStatus()...");
            try {
                Method getStatusMethod = managerClass.getMethod("getVehicleChargingStatus");
                Log.i(TAG, "[DATA] ✓ Method found, invoking...");
                Object chargingBean = getStatusMethod.invoke(vehicleServiceInterface);

                if (chargingBean != null) {
                    Class<?> beanClass = chargingBean.getClass();
                    Log.i(TAG, "[DATA] ✓✓ Got VehicleChargingBean: " + beanClass.getName());
                    Log.i(TAG, "[DATA] Bean toString: " + chargingBean.toString());

                    // List bean methods
                    Method[] beanMethods = beanClass.getMethods();
                    Log.i(TAG, "[DATA] Bean methods (" + beanMethods.length + " total):");
                    for (Method method : beanMethods) {
                        String name = method.getName();
                        if (name.startsWith("get") && method.getParameterCount() == 0) {
                            Log.i(TAG, "[DATA]   ✓ " + name + "() -> " + method.getReturnType().getSimpleName());
                        }
                    }

                    // Get battery level (SOC percentage)
                    Log.i(TAG, "[DATA] Attempting getElectricityLevel()...");
                    try {
                        Method getBatteryMethod = beanClass.getMethod("getElectricityLevel");
                        Object result = getBatteryMethod.invoke(chargingBean);
                        Log.i(TAG, "[DATA] getElectricityLevel() returned: " + result + " (type: "
                                + result.getClass().getSimpleName() + ")");
                        if (result instanceof Integer) {
                            batteryLevel = (Integer) result;
                            Log.i(TAG, "[DATA] ✓✓✓ SUCCESS! Battery level: " + batteryLevel + "%");
                            if (listener != null) {
                                listener.onBatteryLevelChanged(batteryLevel);
                            }
                        } else {
                            Log.w(TAG, "[DATA] Result is not Integer, attempting conversion...");
                            batteryLevel = Integer.parseInt(result.toString());
                            Log.i(TAG, "[DATA] ✓ Converted to: " + batteryLevel + "%");
                            if (listener != null) {
                                listener.onBatteryLevelChanged(batteryLevel);
                            }
                        }
                    } catch (NoSuchMethodException e) {
                        Log.e(TAG, "[DATA] ✗ Method getElectricityLevel() not found on bean");
                    }

                    // Try to get range
                    Log.i(TAG, "[DATA] Attempting getCurrentEnduranceMileage()...");
                    try {
                        Method getRangeMethod = beanClass.getMethod("getCurrentEnduranceMileage");
                        Object result = getRangeMethod.invoke(chargingBean);
                        Log.i(TAG, "[DATA] getCurrentEnduranceMileage() returned: " + result);
                        if (result instanceof Integer) {
                            rangeKm = (Integer) result;
                            Log.i(TAG, "[DATA] ✓✓✓ SUCCESS! Range: " + rangeKm + " km");
                            if (listener != null) {
                                listener.onRangeChanged(rangeKm);
                            }
                        }
                    } catch (NoSuchMethodException e) {
                        Log.w(TAG, "[DATA] Method getCurrentEnduranceMileage() not found on bean");
                    }
                } else {
                    Log.e(TAG, "[DATA] ✗✗ getVehicleChargingStatus() returned null");
                }
            } catch (NoSuchMethodException e) {
                Log.e(TAG, "[DATA] ✗✗ Method getVehicleChargingStatus() not found");
            }

            // Summary
            Log.i(TAG, "=================================================");
            if (batteryLevel > 0 || rangeKm > 0) {
                Log.i(TAG, "[DATA] ✓✓✓ DATA READ SUCCESS!");
                Log.i(TAG, "[DATA] Battery: " + batteryLevel + "%, Range: " + rangeKm + " km");
                Log.i(TAG, "[DATA] Using REAL vehicle data");
            } else {
                Log.w(TAG, "[DATA] ✗✗✗ Could not read vehicle data");
                Log.w(TAG, "[DATA] Falling back to MOCK data");
                usingMockData = true;
                startMockDataUpdates();
            }
            Log.i(TAG, "=================================================");

        } catch (Exception e) {
            Log.e(TAG, "[DATA] ✗✗✗ Exception while reading vehicle data");
            LogUtils.logError(TAG, "[DATA] Exception details", e);
            usingMockData = true;
            startMockDataUpdates();
            Log.i(TAG, "=================================================");
        }
    }

    /**
     * Mock data for testing when vehicle service is not available
     * In production, this would read from the actual vehicle service callbacks
     */
    private void startMockDataUpdates() {
        // Simulate initial data
        batteryLevel = 39;
        rangeKm = 136;

        if (listener != null) {
            listener.onBatteryLevelChanged(batteryLevel);
            listener.onRangeChanged(rangeKm);
        }
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public int getRangeKm() {
        return rangeKm;
    }

    public boolean isConnected() {
        return isBound;
    }

    public boolean isUsingMockData() {
        return usingMockData;
    }

    public boolean hasAttemptedBind() {
        return bindAttempted;
    }
}
