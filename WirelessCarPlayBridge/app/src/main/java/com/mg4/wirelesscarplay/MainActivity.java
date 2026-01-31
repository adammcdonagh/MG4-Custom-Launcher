package com.mg4.wirelesscarplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ScrollView;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Simple status/debug activity for the Wireless CarPlay Bridge.
 * Shows service status and logs.
 */
public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    
    private TextView statusText;
    private TextView logText;
    private ScrollView logScroll;
    private Button startButton;
    private Button stopButton;
    private Button clearLogsButton;
    
    private Handler handler = new Handler();
    private Runnable logUpdater;
    private boolean isUpdatingLogs = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Create layout programmatically (no XML needed)
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setBackgroundColor(0xFF1E1E1E);
        
        // Status text
        statusText = new TextView(this);
        statusText.setText("Wireless CarPlay Bridge\n\nService Status: Checking...");
        statusText.setTextSize(16);
        statusText.setTextColor(0xFFFFFFFF);
        statusText.setPadding(0, 0, 0, 40);
        layout.addView(statusText);
        
        // Buttons
        android.widget.LinearLayout buttonLayout = new android.widget.LinearLayout(this);
        buttonLayout.setOrientation(android.widget.LinearLayout.HORIZONTAL);
        
        startButton = new Button(this);
        startButton.setText("Start Service");
        startButton.setOnClickListener(v -> startService());
        buttonLayout.addView(startButton);
        
        stopButton = new Button(this);
        stopButton.setText("Stop Service");
        stopButton.setOnClickListener(v -> stopService());
        buttonLayout.addView(stopButton);
        
        clearLogsButton = new Button(this);
        clearLogsButton.setText("Clear Logs");
        clearLogsButton.setOnClickListener(v -> clearLogs());
        buttonLayout.addView(clearLogsButton);
        
        layout.addView(buttonLayout);
        
        // Log text
        TextView logLabel = new TextView(this);
        logLabel.setText("\nLogs:");
        logLabel.setTextSize(14);
        logLabel.setTextColor(0xFFFFFFFF);
        logLabel.setPadding(0, 40, 0, 20);
        layout.addView(logLabel);
        
        logScroll = new ScrollView(this);
        logScroll.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
            android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
            0,
            1.0f
        ));
        
        logText = new TextView(this);
        logText.setText("Loading logs...");
        logText.setTextSize(12);
        logText.setTextColor(0xFFCCCCCC);
        logText.setTypeface(android.graphics.Typeface.MONOSPACE);
        logText.setPadding(20, 20, 20, 20);
        logText.setBackgroundColor(0xFF000000);
        logScroll.addView(logText);
        
        layout.addView(logScroll);
        
        setContentView(layout);
        
        // Start log updates
        startLogUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLogUpdates();
    }

    private void startService() {
        Log.i(TAG, "Starting Wireless CarPlay Bridge service");
        Intent serviceIntent = new Intent(this, WirelessCarPlayBridgeService.class);
        startService(serviceIntent);
        
        statusText.setText("Wireless CarPlay Bridge\n\nService Status: Starting...");
    }

    private void stopService() {
        Log.i(TAG, "Stopping Wireless CarPlay Bridge service");
        Intent serviceIntent = new Intent(this, WirelessCarPlayBridgeService.class);
        stopService(serviceIntent);
        
        statusText.setText("Wireless CarPlay Bridge\n\nService Status: Stopped");
    }

    private void clearLogs() {
        try {
            Runtime.getRuntime().exec(new String[]{"logcat", "-c"});
            logText.setText("");
        } catch (Exception e) {
            Log.e(TAG, "Failed to clear logs", e);
        }
    }

    private void startLogUpdates() {
        if (isUpdatingLogs) {
            return;
        }
        
        isUpdatingLogs = true;
        
        logUpdater = new Runnable() {
            @Override
            public void run() {
                if (isUpdatingLogs) {
                    updateLogs();
                    handler.postDelayed(this, 1000); // Update every second
                }
            }
        };
        
        handler.post(logUpdater);
    }

    private void stopLogUpdates() {
        isUpdatingLogs = false;
        if (logUpdater != null) {
            handler.removeCallbacks(logUpdater);
        }
    }

    private void updateLogs() {
        new Thread(() -> {
            try {
                Process process = Runtime.getRuntime().exec(new String[]{
                    "logcat", "-d", "-v", "time",
                    "WirelessCarPlayBridge:V",
                    "BLEAdvertiser:V",
                    "WiFiDirectManager:V",
                    "UsbGadgetController:V",
                    "DataBridge:V",
                    "AndroidRuntime:E",
                    "*:S"
                });
                
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
                );
                
                StringBuilder logs = new StringBuilder();
                String line;
                int lineCount = 0;
                int maxLines = 500;
                
                while ((line = reader.readLine()) != null && lineCount < maxLines) {
                    logs.append(line).append("\n");
                    lineCount++;
                }
                
                final String logContent = logs.toString();
                
                runOnUiThread(() -> {
                    if (logText != null) {
                        logText.setText(logContent);
                        
                        // Auto-scroll to bottom
                        logScroll.post(() -> logScroll.fullScroll(ScrollView.FOCUS_DOWN));
                    }
                });
                
            } catch (Exception e) {
                Log.e(TAG, "Error reading logs", e);
            }
        }).start();
    }
}
