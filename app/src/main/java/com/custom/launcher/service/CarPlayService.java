package com.custom.launcher.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * CarPlay Service - Monitors CarPlay connection status via Allgo RUI Service
 * 
 * This service binds to com.allgo.rui.RemoteUIService and uses reflection to
 * register callbacks without needing to import AIDL files.
 * 
 * Connection states:
 * - CarPlay: remoteDevice.type == 1
 * - Android Auto: remoteDevice.type == 2
 */
public class CarPlayService {
    private static final String TAG = "CarPlayService";
    private static final String RUI_SERVICE_PACKAGE = "com.allgo.rui";
    private static final String RUI_SERVICE_CLASS = "com.allgo.rui.RemoteUIService";

    private Context context;
    private Object remoteUIService; // IRemoteUIService instance
    private boolean isServiceBound = false;
    private boolean isCarPlayConnected = false;
    private boolean isAndroidAutoConnected = false;
    private ConnectionListener listener;
    private boolean isBindingInProgress = false; // Prevent concurrent bind attempts

    // Handler for retry logic
    private final Handler retryHandler = new Handler(Looper.getMainLooper());
    private long lastRetryLogTime = 0;
    private static final long RETRY_LOG_INTERVAL_MS = 5000; // Only log retry every 30 seconds
    private final Runnable retryRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isServiceBound) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastRetryLogTime >= RETRY_LOG_INTERVAL_MS) {
                    Log.i(TAG, "[RETRY] Attempting to reconnect to Allgo RUI service...");
                    lastRetryLogTime = currentTime;
                }
                bind();
                retryHandler.postDelayed(this, 5000); // Retry every 5 seconds
            }
        }
    };

    public interface ConnectionListener {
        void onCarPlayConnectionChanged(boolean isConnected);

        void onAndroidAutoConnectionChanged(boolean isConnected);
    }

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "✓ Connected to Allgo RUI Service");
            isServiceBound = true;
            isBindingInProgress = false;

            try {
                // Get IRemoteUIService via AIDL stub
                Class<?> stubClass = Class.forName("com.allgo.rui.IRemoteUIService$Stub");
                Method asInterface = stubClass.getMethod("asInterface", IBinder.class);
                remoteUIService = asInterface.invoke(null, service);

                // Register callbacks
                registerCallbacks();

                // Stop retry attempts
                retryHandler.removeCallbacks(retryRunnable);

            } catch (Exception e) {
                Log.e(TAG, "✗ Failed to initialize RUI service: " + e.getMessage(), e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.w(TAG, "✗ Disconnected from Allgo RUI Service");
            isServiceBound = false;
            isBindingInProgress = false;
            remoteUIService = null;

            // Update connection states
            updateCarPlayConnection(false);
            updateAndroidAutoConnection(false);

            // Start retry attempts
            retryHandler.postDelayed(retryRunnable, 5000);
        }
    };

    public CarPlayService(Context context) {
        this.context = context.getApplicationContext();
    }

    public void setConnectionListener(ConnectionListener listener) {
        this.listener = listener;
    }

    /**
     * Bind to the Allgo RUI service
     */
    public void bind() {
        // Prevent concurrent bind attempts
        if (isBindingInProgress) {
            return;
        }

        isBindingInProgress = true;

        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(RUI_SERVICE_PACKAGE, RUI_SERVICE_CLASS));

            boolean bindResult = context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

            if (bindResult) {
                // Only log on first attempt or every 30 seconds
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastRetryLogTime >= RETRY_LOG_INTERVAL_MS) {
                    Log.i(TAG, "Binding to Allgo RUI Service...");
                    lastRetryLogTime = currentTime;
                }
            } else {
                // Only log failure occasionally to avoid spam
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastRetryLogTime >= RETRY_LOG_INTERVAL_MS) {
                    Log.w(TAG, "Failed to bind to Allgo RUI Service (service may not exist on emulator)");
                    lastRetryLogTime = currentTime;
                }
                // Start retry mechanism if not already running
                if (!retryHandler.hasCallbacks(retryRunnable)) {
                    retryHandler.postDelayed(retryRunnable, 5000);
                }
            }

        } catch (Exception e) {
            // Only log exceptions occasionally to avoid spam
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastRetryLogTime >= RETRY_LOG_INTERVAL_MS) {
                Log.e(TAG, "✗ Exception while binding to RUI service: " + e.getMessage());
                lastRetryLogTime = currentTime;
            }
            // Start retry mechanism if not already running
            if (!retryHandler.hasCallbacks(retryRunnable)) {
                retryHandler.postDelayed(retryRunnable, 5000);
            }
        } finally {
            isBindingInProgress = false;
        }
    }

    /**
     * Unbind from the service
     */
    public void unbind() {
        try {
            if (isServiceBound) {
                context.unbindService(serviceConnection);
                isServiceBound = false;
            }
            retryHandler.removeCallbacks(retryRunnable);
        } catch (Exception e) {
            Log.e(TAG, "✗ Error unbinding service: " + e.getMessage());
        }
    }

    /**
     * Register device and session callbacks using reflection
     */
    private void registerCallbacks() {
        try {
            // Create IRemoteDeviceCallback proxy
            Class<?> deviceCallbackClass = Class.forName("com.allgo.rui.IRemoteDeviceCallback");
            Object deviceCallback = Proxy.newProxyInstance(
                    context.getClassLoader(),
                    new Class<?>[] { deviceCallbackClass },
                    new DeviceCallbackHandler());

            // Create IRemoteSessionCallback proxy
            Class<?> sessionCallbackClass = Class.forName("com.allgo.rui.IRemoteSessionCallback");
            Object sessionCallback = Proxy.newProxyInstance(
                    context.getClassLoader(),
                    new Class<?>[] { sessionCallbackClass },
                    new SessionCallbackHandler());

            // Call register method: register(deviceCallback, sessionCallback, null)
            Method registerMethod = remoteUIService.getClass().getMethod(
                    "register",
                    deviceCallbackClass,
                    sessionCallbackClass,
                    android.os.Bundle.class);

            registerMethod.invoke(remoteUIService, deviceCallback, sessionCallback, null);
            Log.i(TAG, "✓ Registered callbacks with RUI service");

        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to register callbacks: " + e.getMessage(), e);
        }
    }

    /**
     * Handler for IRemoteDeviceCallback interface
     */
    private class DeviceCallbackHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();

            switch (methodName) {
                case "onDeviceConnected":
                    Log.d(TAG, "[DEVICE] onDeviceConnected");
                    if (args != null && args.length > 0) {
                        handleDeviceConnection(args[0], true);
                    }
                    break;

                case "onDeviceDisconnected":
                    Log.d(TAG, "[DEVICE] onDeviceDisconnected");
                    if (args != null && args.length > 0) {
                        handleDeviceConnection(args[0], false);
                    }
                    break;

                case "onDeviceUpdated":
                    Log.d(TAG, "[DEVICE] onDeviceUpdated");
                    break;

                case "onDeviceNotCapable":
                    Log.d(TAG, "[DEVICE] onDeviceNotCapable");
                    break;

                case "asBinder":
                    return null;
            }

            return null;
        }
    }

    /**
     * Handler for IRemoteSessionCallback interface
     */
    private class SessionCallbackHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();

            switch (methodName) {
                case "onSessionStarted":
                    Log.i(TAG, "[SESSION] ✓ Session started");
                    if (args != null && args.length > 0) {
                        handleSessionStarted(args[0]);
                    }
                    break;

                case "onSessionClosed":
                    Log.i(TAG, "[SESSION] ✗ Session closed");
                    if (args != null && args.length > 0) {
                        handleSessionClosed(args[0]);
                    }
                    break;

                case "onSessionFailed":
                    Log.w(TAG, "[SESSION] Session failed");
                    if (args != null && args.length > 0) {
                        handleSessionClosed(args[0]);
                    }
                    break;

                case "onScreenStarted":
                    Log.d(TAG, "[SESSION] Screen started");
                    break;

                case "onScreenStopped":
                    Log.d(TAG, "[SESSION] Screen stopped");
                    break;

                case "onNativeUILaunch":
                    Log.d(TAG, "[SESSION] Native UI launch");
                    break;

                case "onPhoneStateUpdate":
                    if (args != null && args.length >= 2) {
                        int callState = (Integer) args[0];
                        Log.d(TAG, "[SESSION] Phone state update: " + callState);
                    }
                    break;

                case "onPlayBackUpdate":
                    Log.d(TAG, "[SESSION] Playback update");
                    break;

                case "onExtrasChanged":
                    Log.d(TAG, "[SESSION] Extras changed");
                    break;

                case "asBinder":
                    return null;
            }

            return null;
        }
    }

    /**
     * Handle device connection/disconnection
     */
    private void handleDeviceConnection(Object remoteDevice, boolean isConnected) {
        try {
            // Get device type via reflection
            Method getTypeMethod = remoteDevice.getClass().getField("type").getType().equals(int.class)
                    ? null
                    : remoteDevice.getClass().getMethod("getType");

            int deviceType;
            if (getTypeMethod != null) {
                deviceType = (Integer) getTypeMethod.invoke(remoteDevice);
            } else {
                deviceType = remoteDevice.getClass().getField("type").getInt(remoteDevice);
            }

            Log.i(TAG, "[DEVICE] Type: " + (deviceType == 1 ? "CarPlay" : "Android Auto") +
                    ", Connected: " + isConnected);

            if (deviceType == 1) {
                // CarPlay
                updateCarPlayConnection(isConnected);
            } else if (deviceType == 2) {
                // Android Auto
                updateAndroidAutoConnection(isConnected);
            }

        } catch (Exception e) {
            Log.e(TAG, "✗ Error handling device connection: " + e.getMessage());
        }
    }

    /**
     * Handle session started
     */
    private void handleSessionStarted(Object remoteDevice) {
        handleDeviceConnection(remoteDevice, true);
    }

    /**
     * Handle session closed
     */
    private void handleSessionClosed(Object remoteDevice) {
        handleDeviceConnection(remoteDevice, false);
    }

    /**
     * Update CarPlay connection state
     */
    private void updateCarPlayConnection(boolean isConnected) {
        if (this.isCarPlayConnected != isConnected) {
            this.isCarPlayConnected = isConnected;
            Log.i(TAG, "CarPlay connection: " + (isConnected ? "CONNECTED" : "DISCONNECTED"));

            if (listener != null) {
                // Post to main thread
                new Handler(Looper.getMainLooper()).post(() -> listener.onCarPlayConnectionChanged(isConnected));
            }
        }
    }

    /**
     * Update Android Auto connection state
     */
    private void updateAndroidAutoConnection(boolean isConnected) {
        if (this.isAndroidAutoConnected != isConnected) {
            this.isAndroidAutoConnected = isConnected;
            Log.i(TAG, "Android Auto connection: " + (isConnected ? "CONNECTED" : "DISCONNECTED"));

            if (listener != null) {
                // Post to main thread
                new Handler(Looper.getMainLooper()).post(() -> listener.onAndroidAutoConnectionChanged(isConnected));
            }
        }
    }

    /**
     * Launch CarPlay activity
     */
    public void launchCarPlay() {
        if (!isCarPlayConnected) {
            Log.w(TAG, "CarPlay not connected, cannot launch");
            return;
        }

        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                    "com.allgo.carplay.service",
                    "com.allgo.carplay.service.CarPlayActivity"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Log.i(TAG, "✓ Launched CarPlay activity");
        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to launch CarPlay: " + e.getMessage());
        }
    }

    /**
     * Launch Android Auto activity
     */
    public void launchAndroidAuto() {
        if (!isAndroidAutoConnected) {
            Log.w(TAG, "Android Auto not connected, cannot launch");
            return;
        }

        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                    "com.allgo.rui",
                    "com.allgo.rui.RemoteUIActivity"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Log.i(TAG, "✓ Launched Android Auto activity");
        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to launch Android Auto: " + e.getMessage());
        }
    }

    // Getters
    public boolean isConnected() {
        return isServiceBound;
    }

    public boolean isCarPlayConnected() {
        return isCarPlayConnected;
    }

    public boolean isAndroidAutoConnected() {
        return isAndroidAutoConnected;
    }
}
