package com.custom.launcher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Full-screen log viewer for debugging on the car without ADB
 */
public class LogViewerActivity extends AppCompatActivity {
    private static final String TAG = "LogViewerActivity";
    private static final int MAX_LOG_LINES = 500;

    private TextView logText;
    private ScrollView logScrollView;
    private Handler logHandler;
    private Runnable logReader;
    private Process logcatProcess;
    private volatile boolean isReading = false;
    private volatile boolean isPaused = false;
    private String lastLogContent = "";
    private Button btnPauseLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_viewer);

        logText = findViewById(R.id.logText);
        logScrollView = findViewById(R.id.logScrollView);
        btnPauseLogs = findViewById(R.id.btnPauseLogs);
        Button btnRefreshLogs = findViewById(R.id.btnRefreshLogs);
        Button btnClearLogs = findViewById(R.id.btnClearLogs);
        Button btnSaveLogs = findViewById(R.id.btnSaveLogs);
        Button btnClose = findViewById(R.id.btnClose);
        Button btnUsbDebug = findViewById(R.id.btnUsbDebug);

        logHandler = new Handler(Looper.getMainLooper());

        // Pause/Resume button
        btnPauseLogs.setOnClickListener(v -> {
            isPaused = !isPaused;
            if (isPaused) {
                btnPauseLogs.setText("Resume");
                btnPauseLogs.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
                        getResources().getColor(R.color.battery_green, null)));
                Toast.makeText(this, "Log refresh paused", Toast.LENGTH_SHORT).show();
            } else {
                btnPauseLogs.setText("Pause");
                btnPauseLogs.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
                        getResources().getColor(R.color.battery_yellow, null)));
                Toast.makeText(this, "Log refresh resumed", Toast.LENGTH_SHORT).show();
            }
        });

        // Refresh button
        btnRefreshLogs.setOnClickListener(v -> {
            // Remember exact scroll position
            final int scrollX = logScrollView.getScrollX();
            final int scrollY = logScrollView.getScrollY();

            stopLogReader();
            logHandler.postDelayed(() -> {
                startLogReader();
                // Restore exact scroll position
                logHandler.postDelayed(() -> logScrollView.scrollTo(scrollX, scrollY), 150);
            }, 100);
        });

        // Clear button
        btnClearLogs.setOnClickListener(v -> {
            stopLogReader();
            try {
                Runtime.getRuntime().exec("logcat -c");
                logText.setText("Logs cleared. Restarting log reader...");
                logHandler.postDelayed(this::startLogReader, 500);
            } catch (Exception e) {
                logText.setText("Error clearing logs: " + e.getMessage());
                Log.e(TAG, "Error clearing logs", e);
            }
        });

        // Save to USB button
        btnSaveLogs.setOnClickListener(v -> saveLogsToUsb());

        // Close button
        btnClose.setOnClickListener(v -> finish());

        // USB/ADB Detail button
        btnUsbDebug.setOnClickListener(v -> {
            Intent intent = new Intent(LogViewerActivity.this, UsbDebugActivity.class);
            startActivity(intent);
        });

        // Start reading logs
        startLogReader();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLogReader();
    }

    /**
     * Start reading logs in background
     */
    private void startLogReader() {
        if (isReading)
            return;

        isReading = true;
        logReader = new Runnable() {
            @Override
            public void run() {
                if (!isReading)
                    return;

                try {
                    // Kill previous process if exists
                    if (logcatProcess != null) {
                        logcatProcess.destroy();
                    }

                    // Get ALL system logs (last 200 lines)
                    String[] command = {
                            "logcat", "-d", "-v", "time", "-t", "200"
                    };
                    logcatProcess = Runtime.getRuntime().exec(command);

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(logcatProcess.getInputStream()));

                    StringBuilder logs = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null && isReading) {
                        logs.append(line).append("\n");
                    }

                    String logContent = logs.toString();

                    // Reverse the log order so newest appears at top
                    final String newContent;
                    if (!logContent.isEmpty()) {
                        String[] lines = logContent.split("\n");
                        StringBuilder reversed = new StringBuilder();
                        for (int i = lines.length - 1; i >= 0; i--) {
                            reversed.append(lines[i]).append("\n");
                        }
                        newContent = reversed.toString();
                    } else {
                        newContent = "No logs found\n";
                    }

                    // Only update UI if content actually changed and not paused
                    if (!isPaused && !newContent.equals(lastLogContent)) {
                        lastLogContent = newContent;
                        runOnUiThread(() -> {
                            // Highlight custom launcher logs in red, saicmotor logs in orange
                            SpannableString spannableText = new SpannableString(newContent);

                            // All classes from com.custom.launcher package (logs show class name only)
                            String[] customClassNames = {
                                    "custom.launcher",
                                    "CustomLauncher",
                                    "MainActivity",
                                    "LogViewerActivity",
                                    "UsbDebugActivity",
                                    "VehicleDataService",
                                    "HeatingControlService",
                                    "MediaListenerService",
                                    "CarPlayService",
                                    "SaicMediaService"
                            };

                            // SAIC SDK classes
                            String[] saicClassNames = {
                                    "PageManager",
                                    "BaseManager",
                                    "VehicleChargingManager",
                                    "AirConditionManager",
                                    "LogUtil",
                                    "VehicleServiceContract",
                                    "IVehicleControlService",
                                    "IVehicleControlListener",
                                    "IVehicleSettingListener",
                                    "IVehicleConditionListener",
                                    "IScreenManagerService",
                                    "IScreenStateListener",
                                    "IPageService"
                            };

                            String[] logLines = newContent.split("\n");
                            int currentPos = 0;

                            for (String logLine : logLines) {
                                // Check for custom launcher classes (red)
                                boolean isCustomClass = false;
                                for (String className : customClassNames) {
                                    if (logLine.contains(className)) {
                                        isCustomClass = true;
                                        break;
                                    }
                                }

                                // Check for SAIC classes (orange)
                                boolean isSaicClass = false;
                                if (!isCustomClass) {
                                    for (String className : saicClassNames) {
                                        if (logLine.contains(className)) {
                                            isSaicClass = true;
                                            break;
                                        }
                                    }
                                }

                                if (isCustomClass) {
                                    int lineLength = logLine.length() + 1; // +1 for newline
                                    spannableText.setSpan(
                                            new ForegroundColorSpan(Color.RED),
                                            currentPos,
                                            currentPos + lineLength - 1,
                                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                } else if (isSaicClass) {
                                    int lineLength = logLine.length() + 1; // +1 for newline
                                    spannableText.setSpan(
                                            new ForegroundColorSpan(0xFFFF8C00), // Orange color
                                            currentPos,
                                            currentPos + lineLength - 1,
                                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }

                                currentPos += logLine.length() + 1;
                            }

                            logText.setText(spannableText);
                        });
                    } else if (isPaused && !newContent.equals(lastLogContent)) {
                        // Update cache while paused, but don't update UI
                        lastLogContent = newContent;
                    }

                    reader.close();

                } catch (Exception e) {
                    Log.e(TAG, "Error reading logs", e);
                    runOnUiThread(() -> logText.setText("Error reading logs: " + e.getMessage()));
                }

                // Schedule next read
                if (isReading) {
                    logHandler.postDelayed(this, 1000);
                }
            }
        };

        logHandler.post(logReader);
    }

    /**
     * Stop reading logs
     */
    private void stopLogReader() {
        isReading = false;
        if (logHandler != null && logReader != null) {
            logHandler.removeCallbacks(logReader);
        }
        if (logcatProcess != null) {
            logcatProcess.destroy();
            logcatProcess = null;
        }
    }

    /**
     * Save logs to USB stick
     */
    private void saveLogsToUsb() {
        new Thread(() -> {
            try {
                // Common USB mount points
                String[] usbPaths = {
                        "/storage/usb",
                        "/mnt/usb",
                        "/mnt/media_rw",
                        "/storage/usbdisk",
                        "/mnt/usb_storage",
                        "/mnt/usbhost"
                };

                File usbDir = null;
                for (String path : usbPaths) {
                    File dir = new File(path);
                    if (dir.exists() && dir.isDirectory() && dir.canWrite()) {
                        usbDir = dir;
                        break;
                    }
                    // Also check subdirectories
                    if (dir.exists() && dir.isDirectory()) {
                        File[] subdirs = dir.listFiles();
                        if (subdirs != null) {
                            for (File subdir : subdirs) {
                                if (subdir.isDirectory() && subdir.canWrite()) {
                                    usbDir = subdir;
                                    break;
                                }
                            }
                        }
                    }
                    if (usbDir != null)
                        break;
                }

                if (usbDir == null) {
                    runOnUiThread(() -> Toast.makeText(this,
                            "USB stick not found. Check if USB is connected.",
                            Toast.LENGTH_LONG).show());
                    return;
                }

                // Create log file with timestamp
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
                        .format(new Date());
                File logFile = new File(usbDir, "custom_launcher_logs_" + timestamp + ".txt");

                // Write logs to file
                FileWriter writer = new FileWriter(logFile);
                writer.write(lastLogContent);
                writer.close();

                String finalPath = logFile.getAbsolutePath();
                runOnUiThread(() -> Toast.makeText(this,
                        "Logs saved to: " + finalPath,
                        Toast.LENGTH_LONG).show());

            } catch (Exception e) {
                Log.e(TAG, "Error saving logs to USB", e);
                runOnUiThread(() -> Toast.makeText(this,
                        "Error saving logs: " + e.getMessage(),
                        Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}
