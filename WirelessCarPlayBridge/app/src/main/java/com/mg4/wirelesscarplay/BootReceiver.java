package com.mg4.wirelesscarplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Boot receiver to automatically start the wireless CarPlay bridge service
 * when the car's infotainment system boots up.
 */
public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        
        if (Intent.ACTION_BOOT_COMPLETED.equals(action) ||
            "android.intent.action.QUICKBOOT_POWERON".equals(action)) {
            
            Log.i(TAG, "Boot completed - starting Wireless CarPlay Bridge service");
            
            // Start the service
            Intent serviceIntent = new Intent(context, WirelessCarPlayBridgeService.class);
            context.startService(serviceIntent);
        }
    }
}
