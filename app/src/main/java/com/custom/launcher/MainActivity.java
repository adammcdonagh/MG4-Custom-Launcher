package com.custom.launcher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.custom.launcher.service.HeatingControlService;
import com.custom.launcher.service.MediaListenerService;
import com.custom.launcher.service.VehicleDataService;
import com.custom.launcher.util.LogUtils;

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

    // Heating control views
    private ImageView leftSeatIcon;
    private ImageView rightSeatIcon;
    private ImageView wheelIcon;

    // Heating control states (0 = off, 1-3 = heat levels)
    private int leftSeatLevel = 0;
    private int rightSeatLevel = 0;
    private boolean wheelHeating = false;

    private VehicleDataService vehicleDataService;
    private HeatingControlService heatingControlService;
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
            // Retry vehicle data service
            if (vehicleDataService != null && !vehicleDataService.isConnected()) {
                Log.i(TAG, "[RETRY] Retrying vehicle service connection (attempt at " +
                        new SimpleDateFormat("HH:mm:ss", Locale.UK).format(new Date()) + ")");
                try {
                    vehicleDataService.bind();
                } catch (Exception e) {
                    LogUtils.logError(TAG, "[RETRY] ✗ Exception during retry", e);
                }
            } else if (vehicleDataService != null && vehicleDataService.isConnected()) {
                Log.i(TAG, "[RETRY] Vehicle service is now connected");
            }

            // Retry heating control service
            if (heatingControlService != null && !heatingControlService.isConnected()) {
                Log.i(TAG, "[RETRY] Retrying heating service connection (attempt at " +
                        new SimpleDateFormat("HH:mm:ss", Locale.UK).format(new Date()) + ")");
                try {
                    heatingControlService.bind();
                } catch (Exception e) {
                    LogUtils.logError(TAG, "[RETRY] ✗ Exception during heating retry", e);
                }
            } else if (heatingControlService != null && heatingControlService.isConnected()) {
                Log.i(TAG, "[RETRY] Heating service is now connected");
            }

            // Continue retrying if either service is not connected
            boolean shouldRetry = (vehicleDataService != null && !vehicleDataService.isConnected()) ||
                    (heatingControlService != null && !heatingControlService.isConnected());
            if (shouldRetry) {
                retryHandler.removeCallbacks(this);
                retryHandler.postDelayed(this, RETRY_INTERVAL_MS);
                Log.i(TAG, "[RETRY] Next retry scheduled in " + (RETRY_INTERVAL_MS / 1000) + " seconds");
            } else {
                Log.i(TAG, "[RETRY] All services connected, stopping retry loop");
            }
        }
    };

    private boolean isMediaPlaying = false;
    private boolean lastCommandWasPlay = false;
    private int currentBatteryLevel = 39;

    // Debug dialog fields
    private int clockTapCount = 0;
    private long lastClockTapTime = 0;
    private static final long TAP_TIMEOUT = 500; // ms between taps
    // Debug dialog removed - now using LogViewerActivity full-screen
    private TextView adbStatusView;
    private android.widget.Button enableAdbButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize logging FIRST to ensure all log levels are enabled
        LogUtils.initializeLogging();

        setContentView(R.layout.activity_main);

        // Log display metrics immediately on startup
        logDisplayMetrics();

        initializeViews();
        requestStoragePermissions();
        checkNotificationListenerPermission();
        setupVehicleService();
        setupMediaService();
        startTimeUpdates();
    }

    /**
     * Request storage permissions for accessing Bluetooth album art
     */
    private void requestStoragePermissions() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            String[] permissions = {
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            boolean needsPermission = false;
            for (String permission : permissions) {
                if (checkSelfPermission(permission) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    needsPermission = true;
                    Log.i(TAG, "Missing permission: " + permission);
                }
            }

            if (needsPermission) {
                Log.i(TAG, "Requesting storage permissions for Bluetooth album art access...");
                requestPermissions(permissions, 1001);
            } else {
                Log.i(TAG, "✓ Storage permissions already granted");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001) {
            boolean allGranted = true;
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "✓ Permission granted: " + permissions[i]);
                } else {
                    Log.w(TAG, "✗ Permission denied: " + permissions[i]);
                    allGranted = false;
                }
            }
            if (allGranted) {
                Log.i(TAG, "✓ All storage permissions granted - album art should now work");
            } else {
                Log.w(TAG, "⚠ Some permissions denied - album art may not work for Bluetooth sources");
            }
        }
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

        // Initialize heating control views (with null safety)
        leftSeatIcon = findViewById(R.id.leftSeatIcon);
        rightSeatIcon = findViewById(R.id.rightSeatIcon);
        wheelIcon = findViewById(R.id.wheelIcon);

        // Setup heating control click listeners (only if views exist)
        View leftSeatButton = findViewById(R.id.leftSeatButton);
        if (leftSeatButton != null) {
            leftSeatButton.setOnClickListener(v -> toggleLeftSeat());
        } else {
            Log.w(TAG, "leftSeatButton not found in layout");
        }

        View rightSeatButton = findViewById(R.id.rightSeatButton);
        if (rightSeatButton != null) {
            rightSeatButton.setOnClickListener(v -> toggleRightSeat());
        } else {
            Log.w(TAG, "rightSeatButton not found in layout");
        }

        View wheelButton = findViewById(R.id.wheelButton);
        if (wheelButton != null) {
            wheelButton.setOnClickListener(v -> toggleWheel());
        } else {
            Log.w(TAG, "wheelButton not found in layout");
        }

        // Setup debug triple-tap on clock
        timeText.setOnClickListener(v -> handleClockTap());

        // Long press on clock to show context menu
        registerForContextMenu(timeText);
        timeText.setOnLongClickListener(v -> {
            v.showContextMenu();
            return true;
        });

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
            Log.i(TAG, "CarPlay button clicked!");
            openCarPlay();
        });

        findViewById(R.id.hvacButton).setOnClickListener(v -> {
            Log.i(TAG, "HVAC button clicked!");
            openHVAC();
        });

        findViewById(R.id.launcherButton).setOnClickListener(v -> {
            Log.i(TAG, "Launcher button clicked!");
            openOriginalLauncher();
        });
    }

    private void openChargeManagement() {
        try {
            Log.i(TAG, "Attempting to open Charge Management Activity...");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                    "com.saicmotor.hmi.vehiclesettings",
                    "com.saicmotor.hmi.vehiclesettings.chargemanagement.ui.ChargeManagementActivity"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Log.i(TAG, "✓ Successfully launched Charge Management Activity");
        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to open Charge Management (expected on emulator): " + e.getMessage());
            Log.i(TAG, "This will work on the actual MG4 car where vehiclesettings app is installed");
        }
    }

    private void openCarPlay() {
        try {
            Log.i(TAG, "Attempting to open CarPlay...");
            // Try to launch CarPlay service/app
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.allgo.carplay.service");
            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Log.i(TAG, "✓ Successfully launched CarPlay");
            } else {
                // Fallback: try broadcast intent
                Intent broadcastIntent = new Intent("com.allgo.carplay");
                sendBroadcast(broadcastIntent);
                Log.i(TAG, "Sent CarPlay broadcast intent");
            }
        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to open CarPlay (expected on emulator): " + e.getMessage());
            Log.i(TAG, "This will work on the actual MG4 car where CarPlay is installed");
        }
    }

    private void openHVAC() {
        try {
            Log.i(TAG, "Attempting to open dedicated HVAC app...");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                    "com.saicmotor.hmi.hvac",
                    "com.saicmotor.hmi.hvac.HvacActivity"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Log.i(TAG, "✓ Successfully launched HVAC app");
        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to open HVAC app: " + e.getMessage());
            Log.i(TAG, "This will work on the actual MG4 car where the HVAC app is installed");
        }
    }

    private void openOriginalLauncher() {
        try {
            Log.i(TAG, "Attempting to open original SAIC launcher...");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                    "com.saicmotor.hmi.launcher",
                    "com.saicmotor.hmi.launcher.ui.MainActivity"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Log.i(TAG, "✓ Successfully launched original SAIC launcher");
        } catch (Exception e) {
            Log.e(TAG, "✗ Failed to open original launcher (expected on emulator): " + e.getMessage());
            Log.i(TAG, "This will work on the actual MG4 car where SAIC launcher is installed");
        }
    }

    private void openUsbDebugScreen() {
        try {
            Log.i(TAG, "Opening USB Debug Screen...");
            Intent intent = new Intent(this, UsbDebugActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Failed to open USB Debug Screen: " + e.getMessage());
            Toast.makeText(this, "Error opening debug screen", Toast.LENGTH_SHORT).show();
        }
    }

    private void openShellActivity() {
        try {
            Log.i(TAG, "Opening Shell Command Interface...");
            Intent intent = new Intent(this, ShellActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Failed to open Shell Activity: " + e.getMessage());
            Toast.makeText(this, "Error opening shell", Toast.LENGTH_SHORT).show();
        }
    }

    private void openLogViewer() {
        try {
            Log.i(TAG, "Opening Log Viewer...");
            Intent intent = new Intent(this, LogViewerActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Failed to open Log Viewer: " + e.getMessage());
            Toast.makeText(this, "Error opening log viewer", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Heating control methods
     * Seats have 3 levels (off -> 3 -> 2 -> 1 -> off)
     * Wheel is binary (off -> on -> off)
     */
    private void toggleLeftSeat() {
        // Calculate next level: Off -> High (3) -> Medium (2) -> Low (1) -> Off
        int nextLevel;
        if (leftSeatLevel == 0) {
            nextLevel = 3; // Off -> High
        } else if (leftSeatLevel == 3) {
            nextLevel = 2; // High -> Medium
        } else if (leftSeatLevel == 2) {
            nextLevel = 1; // Medium -> Low
        } else {
            nextLevel = 0; // Low -> Off
        }

        // Update UI immediately (optimistic)
        leftSeatLevel = nextLevel;
        updateSeatDisplay(leftSeatIcon, leftSeatLevel);

        // SAIC API is inverted: API 1=High, 2=Med, 3=Low
        int vehicleLevel = (nextLevel == 0) ? 0 : (4 - nextLevel);
        Log.i(TAG, String.format("Left seat: UI level %d -> Vehicle API level %d", nextLevel, vehicleLevel));

        // Send command to vehicle
        if (heatingControlService != null && heatingControlService.isConnected()) {
            heatingControlService.setDriverSeatHeating(vehicleLevel);
        } else {
            Log.w(TAG, "Heating service not connected, command not sent");
        }
    }

    private void toggleRightSeat() {
        // Calculate next level: Off -> High (3) -> Medium (2) -> Low (1) -> Off
        int nextLevel;
        if (rightSeatLevel == 0) {
            nextLevel = 3; // Off -> High
        } else if (rightSeatLevel == 3) {
            nextLevel = 2; // High -> Medium
        } else if (rightSeatLevel == 2) {
            nextLevel = 1; // Medium -> Low
        } else {
            nextLevel = 0; // Low -> Off
        }

        // Update UI immediately (optimistic)
        rightSeatLevel = nextLevel;
        updateSeatDisplay(rightSeatIcon, rightSeatLevel);

        // SAIC API is inverted: API 1=High, 2=Med, 3=Low
        int vehicleLevel = (nextLevel == 0) ? 0 : (4 - nextLevel);
        Log.i(TAG, String.format("Right seat: UI level %d -> Vehicle API level %d", nextLevel, vehicleLevel));

        // Send command to vehicle
        if (heatingControlService != null && heatingControlService.isConnected()) {
            heatingControlService.setPassengerSeatHeating(vehicleLevel);
        } else {
            Log.w(TAG, "Heating service not connected, command not sent");
        }
    }

    private void toggleWheel() {
        // Toggle state: OFF (false) <-> ON (true)
        boolean nextState = !wheelHeating;
        int nextLevel = nextState ? 1 : 0;

        // Update UI immediately (optimistic)
        wheelHeating = nextState;
        updateWheelDisplay();
        Log.i(TAG, String.format("Steering wheel heating: %s -> %s (sending level %d)",
                !nextState ? "OFF" : "ON",
                nextState ? "ON" : "OFF",
                nextLevel));

        // Send command to vehicle
        if (heatingControlService != null && heatingControlService.isConnected()) {
            heatingControlService.setSteeringWheelHeating(nextLevel);
        } else {
            Log.w(TAG, "Heating service not connected, command not sent");
        }
    }

    private void updateSeatDisplay(ImageView icon, int level) {
        switch (level) {
            case 0:
                icon.setImageResource(R.drawable.ic_heated_seat_off);
                break;
            case 1:
                icon.setImageResource(R.drawable.ic_heated_seat_level1);
                break;
            case 2:
                icon.setImageResource(R.drawable.ic_heated_seat_level2);
                break;
            case 3:
                icon.setImageResource(R.drawable.ic_heated_seat_level3);
                break;
        }
    }

    private void updateWheelDisplay() {
        if (wheelHeating) {
            wheelIcon.setImageResource(R.drawable.ic_heated_wheel_on);
        } else {
            wheelIcon.setImageResource(R.drawable.ic_heated_wheel_off);
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
                    Log.i(TAG, "[RETRY] Vehicle service connected successfully, stopping retry loop");
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

        // Initialize heating control service
        heatingControlService = new HeatingControlService(this);
        heatingControlService.setStatusListener(new HeatingControlService.HeatingStatusListener() {
            @Override
            public void onHeatingStatusChanged(int drvSeatLevel, int psgSeatLevel, int wheelLevel) {
                // Update UI with actual vehicle heating status
                // SAIC API is inverted: API 1=High, 2=Med, 3=Low, so invert for UI
                runOnUiThread(() -> {
                    leftSeatLevel = (drvSeatLevel == 0) ? 0 : (4 - drvSeatLevel);
                    rightSeatLevel = (psgSeatLevel == 0) ? 0 : (4 - psgSeatLevel);
                    wheelHeating = (wheelLevel > 0);
                    updateSeatDisplay(leftSeatIcon, leftSeatLevel);
                    updateSeatDisplay(rightSeatIcon, rightSeatLevel);
                    updateWheelDisplay();
                    Log.d(TAG, String.format("Heating status from vehicle: L_API=%d->UI=%d, R_API=%d->UI=%d, W=%d",
                            drvSeatLevel, leftSeatLevel, psgSeatLevel, rightSeatLevel, wheelLevel));
                });
            }

            @Override
            public void onConnectionStatusChanged(boolean connected) {
                if (connected) {
                    Log.i(TAG, "[RETRY] Heating service connected successfully");
                    // Check if vehicle service is also connected to stop retry loop
                    if (vehicleDataService != null && vehicleDataService.isConnected()) {
                        stopRetryLoop();
                    }
                } else {
                    Log.w(TAG, "[RETRY] Heating service connection failed, will retry");
                    startRetryLoop();
                }
            }
        });

        heatingControlService.bind();
    }

    private void startRetryLoop() {
        Log.i(TAG, "[RETRY] Starting retry loop");
        retryHandler.removeCallbacks(retryRunnable);
        retryHandler.postDelayed(retryRunnable, RETRY_INTERVAL_MS);
    }

    private void stopRetryLoop() {
        Log.i(TAG, "[RETRY] Stopping retry loop");
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

        // Log.i(TAG, "updateActiveMediaController: " + activeMediaController);
    }

    private void sendMediaButtonCommand(int keyCode) {
        // Get active controller from MediaListenerService
        updateActiveMediaController();

        Log.i(TAG, "sendMediaButtonCommand: keyCode=" + keyCode + ", controller=" + activeMediaController);

        if (activeMediaController != null) {
            // Use MediaController transport controls
            MediaController.TransportControls controls = activeMediaController.getTransportControls();
            PlaybackState state = activeMediaController.getPlaybackState();

            Log.i(TAG, "Current playback state: " + (state != null ? state.getState() : "null"));

            switch (keyCode) {
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    Log.i(TAG, "Sending skipToPrevious");
                    controls.skipToPrevious();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                    // Check actual state - if stopped/paused, resume; if playing, pause
                    boolean shouldPause = state != null &&
                            (state.getState() == PlaybackState.STATE_PLAYING ||
                                    state.getState() == PlaybackState.STATE_BUFFERING);

                    if (shouldPause) {
                        Log.i(TAG, "Currently playing, sending pause");
                        controls.pause();
                        lastCommandWasPlay = false;
                        isMediaPlaying = false;
                    } else {
                        Log.i(TAG, "Currently stopped/paused, checking if we should resume or play");
                        // If state is stopped (1), it might have lost context
                        // Try to use the last playing state to resume
                        if (state != null && state.getState() == PlaybackState.STATE_STOPPED) {
                            Log.i(TAG, "State is STOPPED - Radio FM lost context, play will start default");
                        }
                        controls.play();
                        lastCommandWasPlay = true;
                        isMediaPlaying = true;
                    }
                    updatePlayPauseButton();
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    Log.i(TAG, "Sending skipToNext");
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

        if (heatingControlService != null) {
            heatingControlService.release();
        }

        if (timeHandler != null) {
            timeHandler.removeCallbacks(timeRunnable);
        }

        if (retryHandler != null && retryRunnable != null) {
            retryHandler.removeCallbacks(retryRunnable);
        }
        // Debug dialog removed - LogViewerActivity handles its own lifecycle
    }

    @Override
    public void onBackPressed() {
        // Don't allow back button to exit launcher
        // Call super to satisfy lint, but it won't actually exit since this is a
        // launcher
        super.onBackPressed();
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
     * Show debug log viewer in full-screen activity (triple-tap clock to activate)
     */
    private void showDebugDialog() {
        Intent intent = new Intent(MainActivity.this, LogViewerActivity.class);
        startActivity(intent);
    }

    /**
     * Update ADB diagnostics display
     */
    private void updateAdbDiagnostics() {
        new Thread(() -> {
            try {
                StringBuilder diag = new StringBuilder();
                diag.append("=== ADB DIAGNOSTICS ===").append("\n\n");

                // Check ADB status via Settings
                String adbEnabled = "unknown";
                try {
                    int adb = Settings.Global.getInt(getContentResolver(), Settings.Global.ADB_ENABLED);
                    adbEnabled = adb == 1 ? "ENABLED" : "DISABLED";
                } catch (Exception e) {
                    adbEnabled = "error: " + e.getMessage();
                }
                diag.append("ADB Settings: ").append(adbEnabled).append("\n");

                // Check USB configuration
                String usbConfig = getSystemProperty("sys.usb.config");
                diag.append("USB Config: ").append(usbConfig).append("\n");
                diag.append("Has ADB: ").append(usbConfig != null && usbConfig.contains("adb") ? "YES" : "NO")
                        .append("\n");

                // Check persist config
                String persistConfig = getSystemProperty("persist.sys.usb.config");
                diag.append("Persist Config: ").append(persistConfig).append("\n");

                // Check USB state
                String usbState = getSystemProperty("sys.usb.state");
                diag.append("USB State: ").append(usbState).append("\n");

                // Check adbd service
                String adbdService = getSystemProperty("init.svc.adbd");
                diag.append("adbd Service: ").append(adbdService).append("\n\n");

                // Check network interfaces
                diag.append("=== NETWORK INTERFACES ===").append("\n");
                Process ifconfig = Runtime.getRuntime().exec("ip addr show");
                java.io.BufferedReader reader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(ifconfig.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("usb") || line.contains("inet ")) {
                        diag.append(line.trim()).append("\n");
                    }
                }
                reader.close();
                ifconfig.destroy();
                diag.append("\n");

                // Check iptables rules (may require root)
                diag.append("=== IPTABLES RULES ===").append("\n");
                try {
                    Process iptables = Runtime.getRuntime().exec(new String[] { "iptables", "-L", "INPUT", "-n" });
                    reader = new java.io.BufferedReader(
                            new java.io.InputStreamReader(iptables.getInputStream()));
                    boolean foundAdb = false;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("5555") || line.contains("adb")) {
                            diag.append(line.trim()).append("\n");
                            foundAdb = true;
                        }
                    }
                    if (!foundAdb) {
                        diag.append("No ADB-related rules found\n");
                    }
                    reader.close();
                    iptables.destroy();
                } catch (Exception e) {
                    diag.append("Cannot read iptables: ").append(e.getMessage()).append("\n");
                }
                diag.append("\n");

                // Check SELinux status
                diag.append("=== SELINUX STATUS ===").append("\n");
                String selinux = getSystemProperty("ro.build.selinux");
                diag.append("SELinux Build: ").append(selinux).append("\n");
                try {
                    Process getenforce = Runtime.getRuntime().exec("getenforce");
                    reader = new java.io.BufferedReader(
                            new java.io.InputStreamReader(getenforce.getInputStream()));
                    String mode = reader.readLine();
                    diag.append("Current Mode: ").append(mode != null ? mode : "unknown").append("\n");
                    reader.close();
                    getenforce.destroy();
                } catch (Exception e) {
                    diag.append("Cannot check enforce: ").append(e.getMessage()).append("\n");
                }
                diag.append("\n");

                // Check system security
                diag.append("=== SYSTEM SECURITY ===").append("\n");
                diag.append("ro.secure: ").append(getSystemProperty("ro.secure")).append("\n");
                diag.append("ro.adb.secure: ").append(getSystemProperty("ro.adb.secure")).append("\n");
                diag.append("ro.debuggable: ").append(getSystemProperty("ro.debuggable")).append("\n");

                String finalDiag = diag.toString();
                final String finalAdbEnabled = adbEnabled;
                final String finalUsbConfig = usbConfig;
                runOnUiThread(() -> {
                    if (adbStatusView != null) {
                        adbStatusView.setText(finalDiag);
                        // Update button text based on ADB status
                        if (enableAdbButton != null) {
                            if (finalAdbEnabled.equals("ENABLED") && finalUsbConfig != null
                                    && finalUsbConfig.contains("adb")) {
                                enableAdbButton.setText("ADB Active");
                                enableAdbButton.setEnabled(false);
                            } else {
                                enableAdbButton.setText("Enable ADB");
                                enableAdbButton.setEnabled(true);
                            }
                        }
                    }
                });

            } catch (Exception e) {
                Log.e(TAG, "Error updating ADB diagnostics", e);
                runOnUiThread(() -> {
                    if (adbStatusView != null) {
                        adbStatusView.setText("Error loading diagnostics: " + e.getMessage());
                    }
                });
            }
        }).start();
    }

    /**
     * Attempt to enable ADB using multiple approaches
     */
    private void attemptEnableAdb() {
        if (enableAdbButton != null) {
            enableAdbButton.setEnabled(false);
            enableAdbButton.setText("Enabling...");
        }

        new Thread(() -> {
            StringBuilder result = new StringBuilder();
            result.append("=== ATTEMPTING TO ENABLE ADB ===").append("\n\n");

            int successCount = 0;
            int totalAttempts = 0;

            // Approach 1: Enable ADB via Settings.Global
            totalAttempts++;
            result.append("[1] Settings.Global.ADB_ENABLED...\n");
            try {
                Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, 1);
                int check = Settings.Global.getInt(getContentResolver(), Settings.Global.ADB_ENABLED);
                if (check == 1) {
                    result.append("✓ SUCCESS: ADB enabled in settings\n\n");
                    successCount++;
                } else {
                    result.append("✗ FAILED: Setting did not persist\n\n");
                }
            } catch (Exception e) {
                result.append("✗ FAILED: ").append(e.getMessage()).append("\n\n");
            }

            // Approach 2: Set USB configuration to include ADB
            totalAttempts++;
            result.append("[2] SystemProperties: persist.sys.usb.config...\n");
            try {
                String currentConfig = getSystemProperty("persist.sys.usb.config");
                String newConfig = currentConfig != null && !currentConfig.isEmpty()
                        ? (currentConfig.contains("adb") ? currentConfig : currentConfig + ",adb")
                        : "mtp,adb";
                setSystemProperty("persist.sys.usb.config", newConfig);
                Thread.sleep(500);
                String check = getSystemProperty("persist.sys.usb.config");
                if (check != null && check.contains("adb")) {
                    result.append("✓ SUCCESS: USB config set to ").append(check).append("\n\n");
                    successCount++;
                } else {
                    result.append("✗ FAILED: Property did not change\n\n");
                }
            } catch (Exception e) {
                result.append("✗ FAILED: ").append(e.getMessage()).append("\n\n");
            }

            // Approach 3: Trigger USB configuration change
            totalAttempts++;
            result.append("[3] SystemProperties: sys.usb.config...\n");
            try {
                setSystemProperty("sys.usb.config", "mtp,adb");
                Thread.sleep(500);
                String check = getSystemProperty("sys.usb.config");
                if (check != null && check.contains("adb")) {
                    result.append("✓ SUCCESS: Active USB config includes ADB\n\n");
                    successCount++;
                } else {
                    result.append("✗ FAILED: Config = ").append(check).append("\n\n");
                }
            } catch (Exception e) {
                result.append("✗ FAILED: ").append(e.getMessage()).append("\n\n");
            }

            // Approach 4: Remove iptables firewall rule (if exists)
            totalAttempts++;
            result.append("[4] iptables: Remove ADB block...\n");
            try {
                Process iptables = Runtime.getRuntime().exec(new String[] {
                        "iptables", "-D", "INPUT", "-p", "tcp", "--dport", "5555", "-j", "DROP"
                });
                int exitCode = iptables.waitFor();
                if (exitCode == 0) {
                    result.append("✓ SUCCESS: Firewall rule removed\n\n");
                    successCount++;
                } else {
                    result.append("✗ FAILED: iptables returned code ").append(exitCode).append("\n\n");
                }
            } catch (Exception e) {
                result.append("✗ FAILED: ").append(e.getMessage()).append("\n\n");
            }

            // Approach 5: Start adbd service
            totalAttempts++;
            result.append("[5] Start adbd service...\n");
            try {
                setSystemProperty("ctl.start", "adbd");
                Thread.sleep(1000);
                String check = getSystemProperty("init.svc.adbd");
                if ("running".equals(check)) {
                    result.append("✓ SUCCESS: adbd service started\n\n");
                    successCount++;
                } else {
                    result.append("✗ FAILED: Service state = ").append(check).append("\n\n");
                }
            } catch (Exception e) {
                result.append("✗ FAILED: ").append(e.getMessage()).append("\n\n");
            }

            // Approach 6: Use alternative ADB port (bypass firewall on 5555)
            totalAttempts++;
            result.append("[6] Alternative ADB ports (bypass firewall)...\n");
            int altPort = 0;
            try {
                // Try alternative ports: 5556, 5557, 5558
                for (int port : new int[] { 5556, 5557, 5558 }) {
                    setSystemProperty("service.adb.tcp.port", String.valueOf(port));
                    Thread.sleep(500);
                    String check = getSystemProperty("service.adb.tcp.port");
                    if (String.valueOf(port).equals(check)) {
                        altPort = port;
                        break;
                    }
                }

                if (altPort > 0) {
                    // Restart adbd to apply new port
                    setSystemProperty("ctl.restart", "adbd");
                    Thread.sleep(1000);
                    String adbdState = getSystemProperty("init.svc.adbd");
                    if ("running".equals(adbdState)) {
                        result.append("✓ SUCCESS: ADB listening on port ").append(altPort).append("\n");
                        result.append("  (bypasses firewall on default port 5555)\n\n");
                        successCount++;
                    } else {
                        result.append("✗ FAILED: Port set but adbd not running\n\n");
                    }
                } else {
                    result.append("✗ FAILED: Could not set alternative port\n\n");
                }
            } catch (Exception e) {
                result.append("✗ FAILED: ").append(e.getMessage()).append("\n\n");
            }

            // Summary
            result.append("===========================\n");
            result.append("SUMMARY: ").append(successCount).append("/").append(totalAttempts)
                    .append(" approaches succeeded\n\n");

            if (successCount > 0) {
                result.append("✓ Some methods succeeded!\n");
                result.append("Try connecting with:\n");
                if (altPort > 0) {
                    result.append("  adb connect <car-ip>:").append(altPort).append("\n");
                    result.append("  (using alternative port to bypass firewall)\n\n");
                } else {
                    result.append("  adb connect <car-ip>:5555\n\n");
                }
            } else {
                result.append("✗ All methods failed.\n");
                result.append("Possible causes:\n");
                result.append("• SELinux blocking modifications\n");
                result.append("• Vendor-specific security policies\n");
                result.append("• Need root access\n");
                result.append("• USB hardware locked to host mode\n\n");
            }

            String finalResult = result.toString();
            final int finalSuccessCount = successCount;
            runOnUiThread(() -> {
                if (adbStatusView != null) {
                    adbStatusView.setText(finalResult);
                }
                if (enableAdbButton != null) {
                    enableAdbButton.setEnabled(true);
                    enableAdbButton.setText(finalSuccessCount > 0 ? "Try Again" : "Enable ADB");
                }
                Toast.makeText(MainActivity.this,
                        finalSuccessCount > 0 ? "Some methods succeeded!" : "All methods failed",
                        Toast.LENGTH_LONG).show();

                // Refresh diagnostics after 2 seconds
                new Handler(Looper.getMainLooper()).postDelayed(() -> updateAdbDiagnostics(), 2000);
            });

        }).start();
    }

    /**
     * Get system property via reflection
     */
    private String getSystemProperty(String key) {
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            java.lang.reflect.Method get = systemProperties.getMethod("get", String.class);
            String value = (String) get.invoke(null, key);
            return value != null && !value.isEmpty() ? value : "(not set)";
        } catch (Exception e) {
            return "(error)";
        }
    }

    /**
     * Set system property via reflection
     */
    private void setSystemProperty(String key, String value) throws Exception {
        Class<?> systemProperties = Class.forName("android.os.SystemProperties");
        java.lang.reflect.Method set = systemProperties.getMethod("set", String.class, String.class);
        set.invoke(null, key, value);
        Log.i(TAG, "Set system property: " + key + " = " + value);
    }

    /**
     * Create context menu for clock long press
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.launcher_menu, menu);
    }

    /**
     * Handle context menu item selection
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_shell) {
            openShellActivity();
            return true;
        } else if (id == R.id.menu_usb_debug) {
            openUsbDebugScreen();
            return true;
        } else if (id == R.id.menu_log_viewer) {
            openLogViewer();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
