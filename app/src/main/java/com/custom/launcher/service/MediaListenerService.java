package com.custom.launcher.service;

import java.util.List;

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
import main.java.com.custom.launcher.util.LogUtils;

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
                        Uri artUri;

                        // Check if it's a file path that needs conversion
                        if (artUriString.startsWith("/")) {
                            // Convert absolute file path to file:// URI
                            artUri = Uri.parse("file://" + artUriString);
                            Log.d(TAG, "Converted file path to URI: " + artUri);
                        } else {
                            artUri = Uri.parse(artUriString);
                        }

                        // Try multiple methods to load the bitmap
                        try {
                            // Method 1: ContentResolver (works for content:// URIs)
                            ContentResolver resolver = getContentResolver();
                            albumArt = BitmapFactory.decodeStream(resolver.openInputStream(artUri));
                            Log.d(TAG, "✓ Loaded album art via ContentResolver");
                        } catch (Exception e1) {
                            Log.d(TAG, "ContentResolver failed, trying direct file access: " + e1.getMessage());

                            try {
                                // Method 2: Direct file path (for file:// URIs)
                                if (artUriString.startsWith("/")) {
                                    java.io.File file = new java.io.File(artUriString);
                                    if (file.exists() && file.canRead()) {
                                        albumArt = BitmapFactory.decodeFile(artUriString);
                                        Log.d(TAG, "✓ Loaded album art via direct file access");
                                    } else {
                                        Log.w(TAG, "File doesn't exist or can't be read: " + artUriString);
                                    }
                                }
                            } catch (Exception e2) {
                                Log.w(TAG, "Direct file access also failed: " + e2.getMessage());
                            }
                        }

                        if (albumArt == null) {
                            // Only log once per URI to avoid spam
                            if (trackChanged) {
                                Log.w(TAG, "✗ Failed to load album art from URI after all attempts: " + artUriString);
                            }
                            lastFailedArtUri = artUriString;
                        }
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
                        Log.d(TAG, "✓ Using embedded METADATA_KEY_ALBUM_ART");
                    }
                }
                if (albumArt == null) {
                    albumArt = metadata.getBitmap(MediaMetadata.METADATA_KEY_ART);
                    if (albumArt != null) {
                        Log.d(TAG, "✓ Using embedded METADATA_KEY_ART");
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
