package com.custom.launcher.service;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Service to control and monitor vehicle heating (heated seats and steering
 * wheel)
 * using the SAIC SDK's AirConditionManager via reflection.
 */
public class HeatingControlService {
    private static final String TAG = "HeatingControlService";
    private static final String LAUNCHER_PACKAGE = "com.saicmotor.hmi.hvac";
    private static final String SDK_MANAGER_CLASS = "com.saicmotor.sdk.vehiclesettings.manager.AirConditionManager";
    private static final String SDK_BEAN_CLASS = "com.saicmotor.sdk.vehiclesettings.bean.AirConditionBean";
    private static final String SDK_LISTENER_CLASS = "com.saicmotor.sdk.vehiclesettings.VehicleServiceContract$IVehicleServiceListener";
    private static final String SDK_CALLBACK_CLASS = "com.saicmotor.sdk.vehiclesettings.VehicleServiceContract$IAirConditionCallback";

    private final Context context;
    private ClassLoader launcherClassLoader;
    private Object managerInstance;
    private Object callbackProxy;
    private boolean isConnected = false;
    private HeatingStatusListener statusListener;

    public boolean isConnected() {
        return isConnected;
    }

    public interface HeatingStatusListener {
        void onHeatingStatusChanged(int drvSeatLevel, int psgSeatLevel, int wheelLevel);

        void onConnectionStatusChanged(boolean connected);
    }

    public HeatingControlService(Context context) {
        this.context = context;
    }

    public void setStatusListener(HeatingStatusListener listener) {
        this.statusListener = listener;
    }

    /**
     * Initialize and bind to the SAIC SDK AirConditionManager
     */
    public void bind() {
        try {
            Log.i(TAG, "[HEATING] Initializing HeatingControlService...");

            // Step 1: Load SDK classes from HVAC app package
            Context launcherContext = context.createPackageContext(
                    LAUNCHER_PACKAGE,
                    Context.CONTEXT_INCLUDE_CODE);
            launcherClassLoader = launcherContext.getClassLoader();
            Log.i(TAG, "✓ Loaded HVAC package classloader");

            // Step 2: Load manager and callback classes
            Class<Object> managerClass = (Class<Object>) launcherClassLoader.loadClass(SDK_MANAGER_CLASS);
            Class<Object> listenerInterface = (Class<Object>) launcherClassLoader.loadClass(SDK_LISTENER_CLASS);
            Class<Object> callbackInterface = (Class<Object>) launcherClassLoader.loadClass(SDK_CALLBACK_CLASS);
            Log.i(TAG, "✓ Loaded SDK classes via reflection");

            // Step 3: Create listener proxy for service connection
            Object listenerProxy = Proxy.newProxyInstance(
                    launcherClassLoader,
                    new Class[] { listenerInterface },
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if ("onServiceConnected".equals(method.getName())) {
                                Log.i(TAG, "✓ AirConditionManager service connected");
                                managerInstance = args[0];
                                isConnected = true;
                                if (statusListener != null) {
                                    statusListener.onConnectionStatusChanged(true);
                                }
                                // Register callback for status updates
                                registerCallback();
                                // Read initial status
                                readHeatingStatus();
                            } else if ("onServiceDisconnected".equals(method.getName())) {
                                Log.w(TAG, "✗ AirConditionManager service disconnected");
                                isConnected = false;
                                if (statusListener != null) {
                                    statusListener.onConnectionStatusChanged(false);
                                }
                            }
                            return null;
                        }
                    });

            // Step 4: Initialize the manager
            Method initMethod = managerClass.getMethod(
                    "init",
                    Context.class,
                    listenerInterface,
                    Long.TYPE);
            initMethod.invoke(null, context, listenerProxy, 1500L);
            Log.i(TAG, "[HEATING] ✓ AirConditionManager.init() called");

        } catch (Exception e) {
            Log.e(TAG, "[HEATING] Failed to initialize HeatingControlService: " + e.getMessage(), e);
            isConnected = false;
        }
    }

    /**
     * Register callback to receive heating status updates from vehicle
     */
    private void registerCallback() {
        try {
            if (managerInstance == null) {
                Log.w(TAG, "Cannot register callback - manager not initialized");
                return;
            }

            Class<?> callbackInterface = launcherClassLoader.loadClass(SDK_CALLBACK_CLASS);

            // Create callback proxy to receive heating status updates
            callbackProxy = Proxy.newProxyInstance(
                    launcherClassLoader,
                    new Class<?>[] { callbackInterface },
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            String methodName = method.getName();

                            // Called when air condition status changes
                            if ("onAirConditionBeanChanged".equals(methodName) && args != null && args.length > 0) {
                                Object airConditionBean = args[0];
                                readHeatingStatusFromBean(airConditionBean);
                            }

                            return null;
                        }
                    });

            // Register the callback
            Method registerMethod = managerInstance.getClass().getMethod(
                    "registerAirConditionCallback",
                    callbackInterface);
            registerMethod.invoke(managerInstance, callbackProxy);
            Log.i(TAG, "✓ Registered AirConditionCallback for status updates");

        } catch (Exception e) {
            Log.e(TAG, "Failed to register heating callback: " + e.getMessage(), e);
        }
    }

    /**
     * Read current heating status from the vehicle
     */
    private void readHeatingStatus() {
        try {
            if (managerInstance == null) {
                Log.w(TAG, "Cannot read status - manager not initialized");
                return;
            }

            // Get the AirConditionBean
            Method getStatusMethod = managerInstance.getClass().getMethod("getAirConditionBean");
            Object airConditionBean = getStatusMethod.invoke(managerInstance);

            if (airConditionBean != null) {
                readHeatingStatusFromBean(airConditionBean);
            }

        } catch (Exception e) {
            Log.e(TAG, "Failed to read heating status: " + e.getMessage(), e);
        }
    }

    /**
     * Extract heating levels from AirConditionBean
     */
    private void readHeatingStatusFromBean(Object airConditionBean) {
        try {
            Class<?> beanClass = airConditionBean.getClass();

            // Get driver seat heating level (0-3)
            Method getDrvSeatMethod = beanClass.getMethod("getDrvSeatHeatLevel");
            Integer drvSeatLevel = (Integer) getDrvSeatMethod.invoke(airConditionBean);

            // Get passenger seat heating level (0-3)
            Method getPsgSeatMethod = beanClass.getMethod("getPsgSeatHeatLevel");
            Integer psgSeatLevel = (Integer) getPsgSeatMethod.invoke(airConditionBean);

            // Get steering wheel heating level (0 or 1)
            Method getWheelMethod = beanClass.getMethod("getSteeringWheelHeatLevel");
            Integer wheelLevel = (Integer) getWheelMethod.invoke(airConditionBean);

            Log.d(TAG, String.format("Heating status: DrvSeat=%d, PsgSeat=%d, Wheel=%d",
                    drvSeatLevel, psgSeatLevel, wheelLevel));

            // Notify listener
            if (statusListener != null) {
                statusListener.onHeatingStatusChanged(
                        drvSeatLevel != null ? drvSeatLevel : 0,
                        psgSeatLevel != null ? psgSeatLevel : 0,
                        wheelLevel != null ? wheelLevel : 0);
            }

        } catch (Exception e) {
            Log.e(TAG, "Failed to parse heating status from bean: " + e.getMessage(), e);
        }
    }

    /**
     * Set driver seat heating level
     * 
     * @param level 0=off, 1=low, 2=medium, 3=high
     */
    public void setDriverSeatHeating(int level) {
        try {
            if (managerInstance == null) {
                Log.w(TAG, "Cannot set driver seat - manager not initialized");
                return;
            }

            Method setMethod = managerInstance.getClass().getMethod("setDrvSeatHeatLevel", Integer.TYPE);
            setMethod.invoke(managerInstance, level);
            Log.i(TAG, "✓ Set driver seat heating to level " + level);

        } catch (Exception e) {
            Log.e(TAG, "Failed to set driver seat heating: " + e.getMessage(), e);
        }
    }

    /**
     * Set passenger seat heating level
     * 
     * @param level 0=off, 1=low, 2=medium, 3=high
     */
    public void setPassengerSeatHeating(int level) {
        try {
            if (managerInstance == null) {
                Log.w(TAG, "Cannot set passenger seat - manager not initialized");
                return;
            }

            Method setMethod = managerInstance.getClass().getMethod("setPsgSeatHeatLevel", Integer.TYPE);
            setMethod.invoke(managerInstance, level);
            Log.i(TAG, "✓ Set passenger seat heating to level " + level);

        } catch (Exception e) {
            Log.e(TAG, "Failed to set passenger seat heating: " + e.getMessage(), e);
        }
    }

    /**
     * Set steering wheel heating
     * 
     * @param level 0=off, 1=on
     */
    public void setSteeringWheelHeating(int level) {
        try {
            if (managerInstance == null) {
                Log.w(TAG, "Cannot set steering wheel - manager not initialized");
                return;
            }

            Method setMethod = managerInstance.getClass().getMethod("setSteeringWheelHeat", Integer.TYPE);
            setMethod.invoke(managerInstance, level);
            Log.i(TAG, "✓ Set steering wheel heating to level " + level);

        } catch (Exception e) {
            Log.e(TAG, "Failed to set steering wheel heating: " + e.getMessage(), e);
        }
    }

    /**
     * Release resources and unregister callbacks
     */
    public void release() {
        try {
            if (managerInstance != null && callbackProxy != null) {
                Class<?> callbackInterface = launcherClassLoader.loadClass(SDK_CALLBACK_CLASS);
                Method unregisterMethod = managerInstance.getClass().getMethod(
                        "unregisterAirConditionCallback",
                        callbackInterface);
                unregisterMethod.invoke(managerInstance, callbackProxy);
                Log.i(TAG, "✓ Unregistered heating callback");
            }

            managerInstance = null;
            callbackProxy = null;
            isConnected = false;

        } catch (Exception e) {
            Log.e(TAG, "Failed to release heating service: " + e.getMessage(), e);
        }
    }
}
