package com.custom.launcher.service;

import android.content.Context;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * Service to connect to SAIC vehicle data services
 * This binds to the vehicle service to get battery level, range, and other
 * vehicle data
 */
public class VehicleDataService {
    private static final String TAG = "VehicleDataService";

    // SAIC Vehicle Service package and action (extracted from R67 launcher)
    private static final String VEHICLE_SERVICE_PACKAGE = "com.saicmotor.service.vehicle";
    private static final String VEHICLE_SERVICE_ACTION = "com.saicmotor.service.vehicle.VehicleService";

    private Context context;
    private VehicleDataListener listener;
    private boolean isBound = false;

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
     * Bind to the SAIC vehicle service
     */
    public void bind() {
        try {
            Intent intent = new Intent();
            intent.setPackage(VEHICLE_SERVICE_PACKAGE);
            intent.setAction(VEHICLE_SERVICE_ACTION);

            boolean success = context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            Log.d(TAG, "Bind vehicle service: " + success);

            if (!success) {
                Log.w(TAG, "Failed to bind to vehicle service, using mock data");
                if (listener != null) {
                    listener.onConnectionStatusChanged(false);
                }
                // Use mock data when service binding fails
                startMockDataUpdates();
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to bind vehicle service, using mock data", e);
            if (listener != null) {
                listener.onConnectionStatusChanged(false);
            }
            // Use mock data when service binding fails
            startMockDataUpdates();
        }
    }

    public void unbind() {
        if (isBound) {
            try {
                context.unbindService(serviceConnection);
                isBound = false;
            } catch (Exception e) {
                Log.e(TAG, "Failed to unbind service", e);
            }
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "Vehicle service connected: " + name);
            isBound = true;

            if (listener != null) {
                listener.onConnectionStatusChanged(true);
            }

            // TODO: Register for vehicle data callbacks
            // This would use AIDL interface from the SAIC vehicle service
            // For now, we'll use mock data
            startMockDataUpdates();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "Vehicle service disconnected");
            isBound = false;

            if (listener != null) {
                listener.onConnectionStatusChanged(false);
            }
        }
    };

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
}
