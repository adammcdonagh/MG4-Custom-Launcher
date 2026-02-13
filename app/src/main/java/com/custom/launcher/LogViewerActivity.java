package com.custom.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

                    // Get all logs, filter for our app's process ID
                    // First get our PID, then filter logs by that PID
                    String[] command = {
                            "sh", "-c",
                            "MY_PID=$(ps | grep com.custom.launcher | grep -v grep | awk '{print $2}' | head -1); " +
                                    "if [ -n \"$MY_PID\" ]; then " +
                                    "  logcat -d -v time | grep \" $MY_PID \" | tail -100; " +
                                    "else " +
                                    "  echo 'App process not found'; " +
                                    "fi"
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
                    if (!logContent.isEmpty() && !logContent.contains("App process not found")) {
                        String[] lines = logContent.split("\n");
                        StringBuilder reversed = new StringBuilder();
                        for (int i = lines.length - 1; i >= 0; i--) {
                            reversed.append(lines[i]).append("\n");
                        }
                        newContent = reversed.toString();
                    } else {
                        newContent = "No logs found for com.custom.launcher\n\nMake sure the app is running and generating logs.";
                    }

                    // Only update UI if content actually changed and not paused
                    if (!isPaused && !newContent.equals(lastLogContent)) {
                        lastLogContent = newContent;
                        runOnUiThread(() -> {
                            // Update text - newest logs now at top, no scrolling needed
                            logText.setText(newContent);
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
