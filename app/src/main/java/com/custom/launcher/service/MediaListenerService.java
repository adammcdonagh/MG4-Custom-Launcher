package com.custom.launcher.service;

import java.util.List;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.service.notification.NotificationListenerService;
import android.util.Log;
import com.custom.launcher.util.LogUtils;

/**
 * Service to listen for media playback information
 * This monitors active media sessions to display now playing information
 */
public class MediaListenerService extends NotificationListenerService {
    // Track last failed album art URI to suppress repeated log spam
    private String lastFailedArtUri = null;
    private String lastTrackId = null;
    private static final String TAG = "MediaListenerService";

    private static MediaListenerService instance;
    private static MediaInfoListener listener;

    private MediaSessionManager mediaSessionManager;
    private MediaController activeController;

    public interface MediaInfoListener {
        void onMediaChanged(String title, String artist, boolean isPlaying, Bitmap albumArt);
    }

    public static void setListener(MediaInfoListener l) {
        listener = l;
        // If service is already running, trigger immediate update
        if (instance != null) {
            instance.updateMediaInfo();
        }
    }

    public static MediaController getActiveController() {
        if (instance != null) {
            return instance.activeController;
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mediaSessionManager = (MediaSessionManager) getSystemService(MEDIA_SESSION_SERVICE);

        // Listen for active media session changes
        mediaSessionManager.addOnActiveSessionsChangedListener(
                this::onActiveSessionsChanged,
                new ComponentName(this, MediaListenerService.class));

        // Get initial active sessions
        onActiveSessionsChanged(mediaSessionManager.getActiveSessions(
                new ComponentName(this, MediaListenerService.class)));
    }

    private void onActiveSessionsChanged(List<MediaController> controllers) {
        if (controllers != null && !controllers.isEmpty()) {
            // Use the first active controller
            if (activeController != null) {
                activeController.unregisterCallback(mediaCallback);
            }

            activeController = controllers.get(0);
            activeController.registerCallback(mediaCallback);

            updateMediaInfo();
        } else {
            activeController = null;
            if (listener != null) {
                listener.onMediaChanged("No media playing", "", false, null);
            }
        }
    }

    private MediaController.Callback mediaCallback = new MediaController.Callback() {
        @Override
        public void onPlaybackStateChanged(PlaybackState state) {
            updateMediaInfo();
        }

        @Override
        public void onMetadataChanged(MediaMetadata metadata) {
            updateMediaInfo();
        }
    };

    private void updateMediaInfo() {
        if (activeController == null || listener == null) {
            return;
        }

        try {
            MediaMetadata metadata = activeController.getMetadata();
            PlaybackState state = activeController.getPlaybackState();

            if (metadata != null) {
                String title = metadata.getString(MediaMetadata.METADATA_KEY_TITLE);
                String artist = metadata.getString(MediaMetadata.METADATA_KEY_ARTIST);

                // Generate a unique track ID to detect track changes
                String currentTrackId = title + "|" + artist;
                boolean trackChanged = !currentTrackId.equals(lastTrackId);
                if (trackChanged) {
                    lastTrackId = currentTrackId;
                    lastFailedArtUri = null; // Reset failed URI on track change
                }

                Bitmap albumArt = null;

                // Try to get ART_URI or ALBUM_ART_URI first (R67 stock launcher method)
                String artUriString = metadata.getString(MediaMetadata.METADATA_KEY_ART_URI);
                if (artUriString == null) {
                    artUriString = metadata.getString(MediaMetadata.METADATA_KEY_ALBUM_ART_URI);
                }

                if (artUriString != null && !artUriString.equals(lastFailedArtUri)) {
                    try {
                        // Log the original URI for debugging (using INFO level to ensure visibility)
                        Log.i(TAG, "=== ALBUM ART DEBUG ===");
                        Log.i(TAG, "Original URI string: [" + artUriString + "]");
                        Log.i(TAG, "URI length: " + artUriString.length());

                        Uri artUri;
                        String cleanPath = artUriString;

                        // URL decode to handle encoded characters like %20 -> space
                        try {
                            String decoded = URLDecoder.decode(artUriString, "UTF-8");
                            if (!decoded.equals(artUriString)) {
                                Log.i(TAG, "After URL decode: [" + decoded + "]");
                                cleanPath = decoded;
                            }
                        } catch (UnsupportedEncodingException e) {
                            Log.w(TAG, "Failed to URL decode: " + e.getMessage());
                        }

                        // Fix malformed Bluetooth paths with extra spaces
                        // Pattern: /storage/emulated/0/bluetooth/XX:XX:XX: XX: XX: XX/...
                        // Should be: /storage/emulated/0/bluetooth/XX:XX:XX:XX:XX:XX/...
                        if (cleanPath.contains("bluetooth/") && cleanPath.contains(": ")) {
                            String fixed = cleanPath.replaceAll(": ", ":");
                            if (!fixed.equals(cleanPath)) {
                                Log.i(TAG, "Fixed Bluetooth path spaces: [" + fixed + "]");
                                cleanPath = fixed;
                            }
                        }

                        Log.i(TAG, "Final path to use: [" + cleanPath + "]");

                        // Check if it's a file path that needs conversion
                        if (cleanPath.startsWith("/")) {
                            // Convert absolute file path to file:// URI
                            artUri = Uri.parse("file://" + cleanPath);
                            Log.i(TAG, "Created file:// URI: " + artUri);
                        } else {
                            artUri = Uri.parse(cleanPath);
                            Log.i(TAG, "Parsed as URI: " + artUri);
                        }

                        // Try multiple methods to load the bitmap
                        try {
                            // Method 1: ContentResolver (works for content:// URIs)
                            Log.i(TAG, "Attempt 1: ContentResolver.openInputStream()");
                            ContentResolver resolver = getContentResolver();
                            albumArt = BitmapFactory.decodeStream(resolver.openInputStream(artUri));
                            Log.i(TAG, "✓✓ SUCCESS via ContentResolver");
                        } catch (Exception e1) {
                            Log.i(TAG, "ContentResolver failed: " + e1.getMessage());

                            try {
                                // Method 2: Direct file path (for file:// URIs)
                                Log.i(TAG, "Attempt 2: Direct file access");
                                if (cleanPath.startsWith("/")) {
                                    Log.i(TAG, "Checking file: " + cleanPath);
                                    java.io.File file = new java.io.File(cleanPath);
                                    Log.i(TAG, "File exists: " + file.exists() + ", canRead: " + file.canRead());
                                    if (file.exists() && file.canRead()) {
                                        Log.i(TAG, "File size: " + file.length() + " bytes");
                                        albumArt = BitmapFactory.decodeFile(cleanPath);
                                        if (albumArt != null) {
                                            Log.i(TAG, "✓✓ SUCCESS via direct file access");
                                        } else {
                                            Log.w(TAG, "File exists but BitmapFactory.decodeFile returned null");
                                        }
                                    } else {
                                        Log.w(TAG, "✗✗ File doesn't exist or can't be read: " + cleanPath);

                                        // Try to list parent directory to see what files are there
                                        java.io.File parentDir = file.getParentFile();
                                        if (parentDir != null && parentDir.exists()) {
                                            Log.i(TAG, "Parent directory exists: " + parentDir.getAbsolutePath());
                                            String[] files = parentDir.list();
                                            if (files != null && files.length > 0) {
                                                Log.i(TAG, "Files in directory (" + files.length + "):");
                                                for (int i = 0; i < Math.min(files.length, 10); i++) {
                                                    Log.i(TAG, "  - " + files[i]);
                                                }
                                            } else {
                                                Log.i(TAG, "Parent directory is empty or unreadable");
                                            }
                                        } else {
                                            Log.i(TAG, "Parent directory doesn't exist");
                                        }
                                    }
                                }
                            } catch (Exception e2) {
                                Log.w(TAG, "Direct file access exception: " + e2.getMessage());
                                e2.printStackTrace();
                            }
                        }

                        if (albumArt == null) {
                            // Only log failure once per URI to avoid spam
                            if (trackChanged) {
                                Log.w(TAG, "✗ Failed to load album art from URI after all attempts: " + artUriString);
                            }
                            lastFailedArtUri = artUriString;
                        } else {
                            Log.i(TAG, "Album art loaded: " + albumArt.getWidth() + "x" + albumArt.getHeight());
                        }
                        Log.i(TAG, "======================");
                    } catch (Exception e) {
                        if (trackChanged) {
                            LogUtils.logWarning(TAG, "✗ Exception loading album art from URI: " + artUriString, e);
                        }
                        lastFailedArtUri = artUriString;
                        albumArt = null;
                    }
                }

                // Fallback to embedded bitmaps if URI loading fails
                if (albumArt == null) {
                    albumArt = metadata.getBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART);
                    if (albumArt != null) {
                        Log.i(TAG, "✓ Using embedded METADATA_KEY_ALBUM_ART");
                    }
                }
                if (albumArt == null) {
                    albumArt = metadata.getBitmap(MediaMetadata.METADATA_KEY_ART);
                    if (albumArt != null) {
                        Log.i(TAG, "✓ Using embedded METADATA_KEY_ART");
                    }
                }

                boolean isPlaying = state != null &&
                        state.getState() == PlaybackState.STATE_PLAYING;

                listener.onMediaChanged(
                        title != null ? title : "Unknown",
                        artist != null ? artist : "Unknown Artist",
                        isPlaying,
                        albumArt);
            }
        } catch (Exception e) {
            LogUtils.logError(TAG, "Failed to get media info", e);
        }
    }

    @Override
    public void onNotificationPosted(android.service.notification.StatusBarNotification sbn) {
        // Not needed for media control - we use MediaSessionManager
    }

    @Override
    public void onNotificationRemoved(android.service.notification.StatusBarNotification sbn) {
        // Not needed for media control - we use MediaSessionManager
    }
}
