package com.custom.launcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Handler;

import javax.swing.text.View;
import javax.swing.text.html.ImageView;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import main.java.com.custom.launcher.service.MediaListenerService;
import main.java.com.custom.launcher.service.VehicleDataService;
import main.java.com.custom.launcher.util.LogUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "CustomLauncher";

    private TextView timeText;
    private TextView dateText;
    private TextView batteryText;
    private TextView rangeText;
    private TextView songTitle;
    private TextView artistName;
    private TextView currentTime;
    private TextView totalTime;
    private ImageButton playPauseButton;
    private ImageView albumArt;
    private CardView batteryCard;
    private View batteryFill;
    private android.widget.SeekBar progressBar;

    private VehicleDataService vehicleDataService;
    private Handler timeHandler;
    private Runnable timeRunnable;
    private Handler progressHandler;
    private Runnable progressRunnable;
    private MediaController activeMediaController;
    private Handler retryHandler = new Handler(Looper.getMainLooper());
    private final int RETRY_INTERVAL_MS = 10000;
    private final Runnable retryRunnable = new Runnable() {
        @Override
        public void run() {
            if (vehicleDataService != null && !vehicleDataService.isConnected()) {
                Log.i(TAG, "[RETRY] Retrying vehicle service connection (attempt at " +
                        new SimpleDateFormat("HH:mm:ss", Locale.UK).format(new Date()) + ")");
                try {
                    vehicleDataService.bind();
                } catch (Exception e) {
                    LogUtils.logError(TAG, "[RETRY] ✗ Exception during retry", e);
                }
            } else if (vehicleDataService != null && vehicleDataService.isConnected()) {
                Log.i(TAG, "[RETRY] Service is now connected, stopping retry loop");
                return; // Don't schedule another retry
            }
            // Always schedule next retry if not connected
            retryHandler.removeCallbacks(this);
            retryHandler.postDelayed(this, RETRY_INTERVAL_MS);
            Log.d(TAG, "[RETRY] Next retry scheduled in " + (RETRY_INTERVAL_MS / 1000) + " seconds");
        }
    };

    private boolean isMediaPlaying = false;
    private boolean lastCommandWasPlay = false;
    private int currentBatteryLevel = 39;

    // Debug dialog fields
    private int clockTapCount = 0;
    private long lastClockTapTime = 0;
    private static final long TAP_TIMEOUT = 500; // ms between taps
    private AlertDialog debugDialog = null;
    private Handler debugHandler;
    private Runnable logReaderRunnable;
    private boolean showingSaicLogs = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize logging FIRST to ensure all log levels are enabled
        LogUtils.initializeLogging();

        setContentView(R.layout.activity_main);

        // Log display metrics immediately on startup
        logDisplayMetrics();

        initializeViews();
        checkNotificationListenerPermission();
        setupVehicleService();
        setupMediaService();
        startTimeUpdates();
    }

    /**
     * Log display metrics for debugging
     */
    private void logDisplayMetrics() {
        try {
            android.util.DisplayMetrics displayMetrics = new android.util.DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int dpi = displayMetrics.densityDpi;
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;
            float density = displayMetrics.density;
            int widthDp = (int) (width / density);
            int heightDp = (int) (height / density);

            Log.i(TAG, "=================================================");
            Log.i(TAG, "DISPLAY METRICS:");
            Log.i(TAG, "  Resolution: " + width + "x" + height + " pixels");
            Log.i(TAG, "  DPI: " + dpi);
            Log.i(TAG, "  Density: " + density);
            Log.i(TAG, "  Size in DP: " + widthDp + "x" + heightDp + " dp");
            Log.i(TAG, "=================================================");
        } catch (Exception e) {
            Log.e(TAG, "Failed to log display metrics: " + e.getMessage());
        }
    }

    private void initializeViews() {
        timeText = findViewById(R.id.timeText);
        dateText = findViewById(R.id.dateText);
        batteryText = findViewById(R.id.batteryText);
        rangeText = findViewById(R.id.rangeText);
        songTitle = findViewById(R.id.songTitle);
        artistName = findViewById(R.id.artistName);
        currentTime = findViewById(R.id.currentTime);
        totalTime = findViewById(R.id.totalTime);
        playPauseButton = findViewById(R.id.playPauseButton);
        albumArt = findViewById(R.id.albumArt);
        batteryCard = findViewById(R.id.batteryCard);
        batteryFill = findViewById(R.id.batteryFill);

        // Setup debug triple-tap on clock
        timeText.setOnClickListener(v -> handleClockTap());
        progressBar = findViewById(R.id.progressBar);

        // Disable SeekBar interaction (read-only progress indicator)
        progressBar.setEnabled(false);

        // Setup media control buttons
        findViewById(R.id.prevButton).setOnClickListener(v -> {
            sendMediaButtonCommand(KeyEvent.KEYCODE_MEDIA_PREVIOUS);
        });

        playPauseButton.setOnClickListener(v -> {
            sendMediaButtonCommand(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
        });

        findViewById(R.id.nextButton).setOnClickListener(v -> {
            sendMediaButtonCommand(KeyEvent.KEYCODE_MEDIA_NEXT);
        });

        // Setup battery card click to open Charge Management
        batteryCard.setOnClickListener(v -> {
            openChargeManagement();
        });

        // Setup quick action buttons
        findViewById(R.id.carPlayButton).setOnClickListener(v -> {
            Log.d(TAG, "CarPlay button clicked!");
            openCarPlay();
        });

        findViewById(R.id.hvacButton).setOnClickListener(v -> {
            Log.d(TAG, "HVAC button clicked!");
            openHVAC();
        });

        findViewById(R.id.launcherButton).setOnClickListener(v -> {
            Log.d(TAG, "Launcher button clicked!");
            openOriginalLauncher();
        });
    }

    private void openChargeManagement() {
        try {
            Log.d(TAG, "Attempting to open Charge Management Activity...");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                    "com.saicmotor.hmi.vehiclesettings",
                    "com.saicmotor.hmi.vehiclesettings.chargemanagement.ui.ChargeManagementActivity"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Log.d(TAG, "✓ Successfully launched Charge Management Activity");
        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to open Charge Management (expected on emulator): " + e.getMessage());
            Log.d(TAG, "This will work on the actual MG4 car where vehiclesettings app is installed");
        }
    }

    private void openCarPlay() {
        try {
            Log.d(TAG, "Attempting to open CarPlay...");
            // Try to launch CarPlay service/app
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.allgo.carplay.service");
            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Log.d(TAG, "✓ Successfully launched CarPlay");
            } else {
                // Fallback: try broadcast intent
                Intent broadcastIntent = new Intent("com.allgo.carplay");
                sendBroadcast(broadcastIntent);
                Log.d(TAG, "Sent CarPlay broadcast intent");
            }
        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to open CarPlay (expected on emulator): " + e.getMessage());
            Log.d(TAG, "This will work on the actual MG4 car where CarPlay is installed");
        }
    }

    private void openHVAC() {
        try {
            Log.d(TAG, "Attempting to open dedicated HVAC app...");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                    "com.saicmotor.hmi.hvac",
                    "com.saicmotor.hmi.hvac.HvacActivity"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Log.d(TAG, "✓ Successfully launched HVAC app");
        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to open HVAC app: " + e.getMessage());
            Log.d(TAG, "This will work on the actual MG4 car where the HVAC app is installed");
        }
    }

    private void openOriginalLauncher() {
        try {
            Log.d(TAG, "Attempting to open original SAIC launcher...");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                    "com.saicmotor.hmi.launcher",
                    "com.saicmotor.hmi.launcher.ui.MainActivity"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Log.d(TAG, "✓ Successfully launched original SAIC launcher");
        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to open original launcher (expected on emulator): " + e.getMessage());
            Log.d(TAG, "This will work on the actual MG4 car where SAIC launcher is installed");
        }
    }

    private void checkNotificationListenerPermission() {
        // Check if notification listener permission is granted
        if (!isNotificationServiceEnabled()) {
            // Show dialog to guide user to settings
            new AlertDialog.Builder(this)
                    .setTitle("Media Controls Setup")
                    .setMessage(
                            "To enable media playback controls, please allow notification access in Settings.\n\nSettings → Apps → Custom Launcher → Notifications → Notification access")
                    .setPositiveButton("Open Settings", (dialog, which) -> {
                        Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
                        startActivity(intent);
                    })
                    .setNegativeButton("Skip", null)
                    .show();
        }
    }

    private boolean isNotificationServiceEnabled() {
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                "enabled_notification_listeners");
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (String name : names) {
                final ComponentName cn = ComponentName.unflattenFromString(name);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void setupVehicleService() {
        vehicleDataService = new VehicleDataService(this, new VehicleDataService.VehicleDataListener() {
            @Override
            public void onBatteryLevelChanged(int level) {
                runOnUiThread(() -> {
                    currentBatteryLevel = level;
                    batteryText.setText(level + "%");
                    updateBatteryFill(level);
                });
            }

            @Override
            public void onRangeChanged(int rangeKm) {
                runOnUiThread(() -> {
                    // Convert km to miles (1 km = 0.621371 miles)
                    int rangeMiles = (int) Math.round(rangeKm * 0.621371);
                    rangeText.setText(rangeMiles + " miles");
                });
            }

            @Override
            public void onConnectionStatusChanged(boolean connected) {
                if (connected) {
                    Log.d(TAG, "[RETRY] Vehicle service connected successfully, stopping retry loop");
                    stopRetryLoop();
                } else {
                    Log.w(TAG, "[RETRY] Vehicle service connection failed, will retry");
                    startRetryLoop();
                }
            }
        });

        vehicleDataService.bind();
        // Start retry loop immediately in case first bind fails
        startRetryLoop();
    }

    private void startRetryLoop() {
        Log.d(TAG, "[RETRY] Starting retry loop");
        retryHandler.removeCallbacks(retryRunnable);
        retryHandler.postDelayed(retryRunnable, RETRY_INTERVAL_MS);
    }

    private void stopRetryLoop() {
        Log.d(TAG, "[RETRY] Stopping retry loop");
        retryHandler.removeCallbacks(retryRunnable);
    }

    // scheduleVehicleServiceRetry() is now replaced by
    // startRetryLoop()/stopRetryLoop()

    private void setupMediaService() {
        MediaListenerService.setListener((title, artist, isPlaying, albumArtBitmap) -> {
            runOnUiThread(() -> {
                songTitle.setText(title);
                artistName.setText(artist);
                isMediaPlaying = isPlaying;

                // Sync our toggle state with the actual state when it changes externally
                lastCommandWasPlay = isPlaying;

                updatePlayPauseButton();
                updateMediaProgress();

                // Update album art
                if (albumArtBitmap != null) {
                    albumArt.setImageBitmap(albumArtBitmap);
                } else {
                    albumArt.setImageResource(R.drawable.ic_launcher);
                }
            });
        });
    }

    private void updateActiveMediaController() {
        // Get controller from MediaListenerService which already has access
        MediaController newController = MediaListenerService.getActiveController();

        // Keep the old controller if new one is null (session might have become
        // inactive but is still valid)
        if (newController != null) {
            activeMediaController = newController;
        }

        Log.d(TAG, "updateActiveMediaController: " + activeMediaController);
    }

    private void sendMediaButtonCommand(int keyCode) {
        // Get active controller from MediaListenerService
        updateActiveMediaController();

        Log.d(TAG, "sendMediaButtonCommand: keyCode=" + keyCode + ", controller=" + activeMediaController);

        if (activeMediaController != null) {
            // Use MediaController transport controls
            MediaController.TransportControls controls = activeMediaController.getTransportControls();
            PlaybackState state = activeMediaController.getPlaybackState();

            Log.d(TAG, "Current playback state: " + (state != null ? state.getState() : "null"));

            switch (keyCode) {
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    Log.d(TAG, "Sending skipToPrevious");
                    controls.skipToPrevious();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                    // Check actual state - if stopped/paused, resume; if playing, pause
                    boolean shouldPause = state != null &&
                            (state.getState() == PlaybackState.STATE_PLAYING ||
                                    state.getState() == PlaybackState.STATE_BUFFERING);

                    if (shouldPause) {
                        Log.d(TAG, "Currently playing, sending pause");
                        controls.pause();
                        lastCommandWasPlay = false;
                        isMediaPlaying = false;
                    } else {
                        Log.d(TAG, "Currently stopped/paused, checking if we should resume or play");
                        // If state is stopped (1), it might have lost context
                        // Try to use the last playing state to resume
                        if (state != null && state.getState() == PlaybackState.STATE_STOPPED) {
                            Log.d(TAG, "State is STOPPED - Radio FM lost context, play will start default");
                        }
                        controls.play();
                        lastCommandWasPlay = true;
                        isMediaPlaying = true;
                    }
                    updatePlayPauseButton();
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    Log.d(TAG, "Sending skipToNext");
                    controls.skipToNext();
                    break;
            }
        } else {
            Log.w(TAG, "No active MediaController available");
        }
    }

    private void startTimeUpdates() {
        timeHandler = new Handler(Looper.getMainLooper());
        timeRunnable = new Runnable() {
            @Override
            public void run() {
                updateTimeAndDate();
                timeHandler.postDelayed(this, 1000); // Update every second
            }
        };
        timeHandler.post(timeRunnable);

        // Also start progress updates
        startProgressUpdates();
    }

    private void startProgressUpdates() {
        progressHandler = new Handler(Looper.getMainLooper());
        progressRunnable = new Runnable() {
            @Override
            public void run() {
                if (isMediaPlaying) {
                    updateMediaProgress();
                }
                progressHandler.postDelayed(this, 500); // Update every 500ms for smooth progress
            }
        };
        progressHandler.post(progressRunnable);
    }

    private void updateTimeAndDate() {
        Date now = new Date();

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.UK);
        timeText.setText(timeFormat.format(now));

        // Shorter date format to fit better: Day, dd Month
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM", Locale.UK);
        dateText.setText(dateFormat.format(now));
    }

    private void updateBatteryFill(int level) {

        // Update visual battery fill width (accounting for container padding) for
        // horizontal layout
        batteryFill.post(() -> {
            android.view.ViewGroup.LayoutParams params = batteryFill.getLayoutParams();
            // Container is 110dp wide minus 8dp padding (4dp each side) = 102dp usable
            // width
            android.view.View container = (android.view.View) batteryFill.getParent();
            int containerWidth = container.getWidth();
            int paddingHorizontal = container.getPaddingStart() + container.getPaddingEnd();
            int usableWidth = containerWidth - paddingHorizontal;
            params.width = (int) (usableWidth * level / 100f);
            batteryFill.setLayoutParams(params);

            // Update battery fill color with smooth gradient transition
            // Green (#30d158) at 50%+, transitioning through orange to red (#ff453a) at 0%
            int color;
            if (level > 50) {
                // Stay green above 50%
                color = 0xFF30D158;
            } else {
                // Much faster transition: green -> orange -> red between 50% and 0%
                // Use stronger power curve to shift colors much faster toward red
                float linearFactor = (50 - level) / 50f; // 0.0 at 50%, 1.0 at 0%
                float factor = (float) Math.pow(linearFactor, 0.4); // Power curve 0.4 for much faster transition

                // Green color: #30d158 (R:48, G:209, B:88)
                // Red color: #ff453a (R:255, G:69, B:58)
                int startR = 48, startG = 209, startB = 88;
                int endR = 255, endG = 69, endB = 58;

                // Interpolate each color channel with the power curve
                int r = (int) (startR + (endR - startR) * factor);
                int g = (int) (startG + (endG - startG) * factor);
                int b = (int) (startB + (endB - startB) * factor);

                color = 0xFF000000 | (r << 16) | (g << 8) | b;
            }

            // Create a GradientDrawable to maintain rounded corners while changing color
            android.graphics.drawable.GradientDrawable drawable = new android.graphics.drawable.GradientDrawable();
            drawable.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
            drawable.setColor(color);
            drawable.setCornerRadius(8 * getResources().getDisplayMetrics().density); // 8dp radius to match outline
            batteryFill.setBackground(drawable);
        });
    }

    private void updatePlayPauseButton() {
        // Update play/pause icon based on state
        playPauseButton.setImageResource(isMediaPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
    }

    private void updateMediaProgress() {
        updateActiveMediaController();

        if (activeMediaController != null) {
            PlaybackState state = activeMediaController.getPlaybackState();
            android.media.MediaMetadata metadata = activeMediaController.getMetadata();

            if (state != null) {
                long position = state.getPosition();
                long duration = -1;

                // Get duration from metadata (most reliable source)
                if (metadata != null) {
                    duration = metadata.getLong(android.media.MediaMetadata.METADATA_KEY_DURATION);
                }

                // Update UI
                currentTime.setText(formatTime(position));

                if (duration > 0 && position >= 0) {
                    // We have a duration - show actual progress and remaining time
                    int progress = (int) ((position * 100) / duration);
                    progressBar.setProgress(Math.min(100, Math.max(0, progress)));

                    // Show remaining time as countdown (negative)
                    long remaining = Math.max(0, duration - position);
                    totalTime.setText("-" + formatTime(remaining));
                } else if (duration <= 0) {
                    // No duration (live stream/radio) - show "Live"
                    progressBar.setProgress(position > 0 ? 50 : 0);
                    totalTime.setText("Live");
                } else {
                    // Position not available yet, show placeholder
                    progressBar.setProgress(0);
                    totalTime.setText("-0:00");
                }
            } else {
                // Reset to defaults
                progressBar.setProgress(0);
                currentTime.setText("0:00");
                totalTime.setText("-0:00");
            }
        } else {
            progressBar.setProgress(0);
            currentTime.setText("0:00");
            totalTime.setText("-0:00");
        }
    }

    private String formatTime(long milliseconds) {
        int totalSeconds = (int) (milliseconds / 1000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format(java.util.Locale.US, "%d:%02d", minutes, seconds);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (vehicleDataService != null) {
            vehicleDataService.unbind();
        }

        if (timeHandler != null) {
            timeHandler.removeCallbacks(timeRunnable);
        }

        if (retryHandler != null && retryRunnable != null) {
            retryHandler.removeCallbacks(retryRunnable);
        }

        if (debugHandler != null && logReaderRunnable != null) {
            debugHandler.removeCallbacks(logReaderRunnable);
        }

        if (debugDialog != null && debugDialog.isShowing()) {
            debugDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        // Don't allow back button to exit launcher
    }

    /**
     * Handle clock tap for debug dialog (triple-tap to open)
     */
    private void handleClockTap() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastClockTapTime > TAP_TIMEOUT) {
            // Reset if too much time passed
            clockTapCount = 1;
        } else {
            clockTapCount++;
        }

        lastClockTapTime = currentTime;

        if (clockTapCount >= 3) {
            clockTapCount = 0;
            showDebugDialog();
        }
    }

    /**
     * Show debug dialog with live log output
     */
    private void showDebugDialog() {
        if (debugDialog != null && debugDialog.isShowing()) {
            return;
        }

        // Log screen DPI and resolution for debugging
        android.util.DisplayMetrics displayMetrics = new android.util.DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Debug Logs - Custom Launcher");

        // Create TextView for log output
        TextView logView = new TextView(this);
        logView.setTextSize(10);
        logView.setTypeface(android.graphics.Typeface.MONOSPACE);
        logView.setPadding(20, 20, 20, 20);
        logView.setTextIsSelectable(true);
        logView.setText("Loading logs...\n");

        // Wrap in ScrollView
        android.widget.ScrollView scrollView = new android.widget.ScrollView(this);
        scrollView.addView(logView);

        // Add a button to scroll to bottom
        android.widget.Button scrollToBottomButton = new android.widget.Button(this);
        scrollToBottomButton.setText("Scroll to Bottom");
        scrollToBottomButton.setOnClickListener(v -> scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN)));

        // Use a vertical LinearLayout to hold the ScrollView and button
        android.widget.LinearLayout container = new android.widget.LinearLayout(this);
        container.setOrientation(android.widget.LinearLayout.VERTICAL);
        container.addView(scrollView, new android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
        container.addView(scrollToBottomButton, new android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));

        builder.setView(container);
        builder.setPositiveButton("Close", (dialog, which) -> {
            if (debugHandler != null && logReaderRunnable != null) {
                debugHandler.removeCallbacks(logReaderRunnable);
            }
            showingSaicLogs = false;
            dialog.dismiss();
        });

        builder.setNeutralButton("Switch Logs", null); // Set listener after creation

        builder.setNegativeButton("Clear", (dialog, which) -> {
            try {
                // Clear logcat
                Process process = Runtime.getRuntime().exec("logcat -c");
                process.waitFor();
                logView.setText("Logs cleared.\n");
            } catch (Exception e) {
                logView.setText("Failed to clear logs: " + e.getMessage() + "\n");
            }
        });

        debugDialog = builder.create();
        debugDialog.show();

        // Set custom click listener for neutral button to prevent dialog from closing
        android.widget.Button switchButton = debugDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        if (switchButton != null) {
            switchButton.setOnClickListener(v -> {
                // Stop current log reader
                if (debugHandler != null && logReaderRunnable != null) {
                    debugHandler.removeCallbacks(logReaderRunnable);
                }

                // Toggle log source
                showingSaicLogs = !showingSaicLogs;

                // Update title
                debugDialog.setTitle(showingSaicLogs ? "Debug Logs - SAIC Launcher" : "Debug Logs - Custom Launcher");

                // Clear display and restart log reader
                logView.setText("Loading logs...\n");
                startLogReader(logView, scrollView);
            });
        }

        // Set dialog window size to 90% width, 90% height after showing
        android.view.Window window = debugDialog.getWindow();
        if (window != null) {
            int screenWidth = displayMetrics.widthPixels;
            int screenHeight = displayMetrics.heightPixels;
            window.setLayout(
                    (int) (screenWidth * 0.90),
                    (int) (screenHeight * 0.90));
        }

        // Start log reader
        startLogReader(logView, scrollView);
    }

    /**
     * Start reading logs and updating the TextView
     */
    private void startLogReader(TextView logView, android.widget.ScrollView scrollView) {
        debugHandler = new Handler(Looper.getMainLooper());

        // Log display metrics again when debug dialog opens
        logDisplayMetrics();

        // Start background thread for log reading
        new Thread(() -> {
            try {
                // Load initial logs
                StringBuilder logBuffer = new StringBuilder();
                Process initialLogcat;
                if (showingSaicLogs) {
                    initialLogcat = Runtime.getRuntime().exec(new String[] {
                            "logcat", "-d", "-v", "time", "-t", "100",
                            "-s",
                            "SaicLoader:V",
                            "com.saicmotor.hmi.launcher:V",
                            "VehicleStatusManager:V",
                            "ChargingViewModel:V",
                            "AndroidRuntime:E"
                    });
                } else {
                    initialLogcat = Runtime.getRuntime().exec(new String[] {
                            "logcat", "-d", "-v", "time", "-t", "100",
                            "-s",
                            TAG + ":V",
                            "VehicleDataService:V",
                            "MediaListenerService:V",
                            "SaicMediaService:V",
                            "AndroidRuntime:E"
                    });
                }
                java.io.BufferedReader initialReader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(initialLogcat.getInputStream()));
                String line;
                while ((line = initialReader.readLine()) != null) {
                    logBuffer.append(line).append("\n");
                }
                initialReader.close();
                initialLogcat.destroy();

                // Update UI with initial logs
                String initialText = logBuffer.toString();
                runOnUiThread(() -> logView.setText(initialText));

                // Start tailing from current time
                String timestamp = new java.text.SimpleDateFormat("MM-dd HH:mm:ss.SSS", java.util.Locale.US)
                        .format(new java.util.Date());

                Process tailProcess;
                if (showingSaicLogs) {
                    tailProcess = Runtime.getRuntime().exec(new String[] {
                            "logcat",
                            "-v", "time",
                            "-T", timestamp,
                            "-s",
                            "SaicLoader:V",
                            "com.saicmotor.hmi.launcher:V",
                            "VehicleStatusManager:V",
                            "ChargingViewModel:V",
                            "AndroidRuntime:E"
                    });
                } else {
                    tailProcess = Runtime.getRuntime().exec(new String[] {
                            "logcat",
                            "-v", "time",
                            "-T", timestamp,
                            "-s",
                            TAG + ":V",
                            "VehicleDataService:V",
                            "MediaListenerService:V",
                            "SaicMediaService:V",
                            "AndroidRuntime:E"
                    });
                }

                java.io.BufferedReader reader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(tailProcess.getInputStream()));

                Log.i(TAG, "Started logcat tail from time: " + timestamp);

                // Blocking read loop in background thread
                while ((line = reader.readLine()) != null) {
                    logBuffer.append(line).append("\n");

                    // Keep only last 500 lines
                    String[] lines = logBuffer.toString().split("\n");
                    if (lines.length > 500) {
                        logBuffer = new StringBuilder();
                        for (int i = lines.length - 500; i < lines.length; i++) {
                            logBuffer.append(lines[i]).append("\n");
                        }
                    }

                    // Update UI
                    String finalText = logBuffer.toString();
                    runOnUiThread(() -> {
                        logView.setText(finalText);
                        scrollView.post(() -> scrollView.fullScroll(android.view.View.FOCUS_DOWN));
                    });
                }

                reader.close();
                tailProcess.destroy();

            } catch (Exception e) {
                Log.e(TAG, "Log reader thread error: " + e.getMessage());
                runOnUiThread(() -> logView.setText("Error reading logs: " + e.getMessage()));
            }
        }).start();
    }
}
