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
                Bitmap albumArt = null;

                // Try to get ART_URI or ALBUM_ART_URI first (R67 stock launcher method)
                String artUriString = metadata.getString("android.media.metadata.ART_URI");
                if (artUriString == null) {
                    artUriString = metadata.getString("android.media.metadata.ALBUM_ART_URI");
                }
                if (artUriString != null) {
                    try {
                        Uri artUri = Uri.parse(artUriString);
                        ContentResolver resolver = getContentResolver();
                        albumArt = BitmapFactory.decodeStream(resolver.openInputStream(artUri));
                    } catch (Exception e) {
                        Log.w(TAG, "Failed to load album art from URI: " + artUriString, e);
                        albumArt = null;
                    }
                }

                // Fallback to embedded bitmaps if URI loading fails
                if (albumArt == null) {
                    albumArt = metadata.getBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART);
                }
                if (albumArt == null) {
                    albumArt = metadata.getBitmap(MediaMetadata.METADATA_KEY_ART);
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
            Log.e(TAG, "Failed to get media info", e);
        }
    }

    @Override
    public void onDestroy() {
        try {
            MediaMetadata metadata = activeController.getMetadata();
            PlaybackState state = activeController.getPlaybackState();

            if (metadata != null) {
                String title = metadata.getString(MediaMetadata.METADATA_KEY_TITLE);
                String artist = metadata.getString(MediaMetadata.METADATA_KEY_ARTIST);
                Bitmap albumArt = null;

                // Try to get ART_URI or ALBUM_ART_URI first (R67 stock launcher method)
                String artUriString = metadata.getString("android.media.metadata.ART_URI");
                if (artUriString == null) {
                    artUriString = metadata.getString("android.media.metadata.ALBUM_ART_URI");
                }

                // Use a track ID to reset log suppression when track changes
                String trackId = (title != null ? title : "") + "|" + (artist != null ? artist : "");
                if (lastTrackId == null || !lastTrackId.equals(trackId)) {
                    lastFailedArtUri = null;
                    lastTrackId = trackId;
                }

                if (artUriString != null) {
                    try {
                        Uri artUri = Uri.parse(artUriString);
                        ContentResolver resolver = getContentResolver();
                        boolean loaded = false;
                        if ("content".equals(artUri.getScheme())) {
                            albumArt = BitmapFactory.decodeStream(resolver.openInputStream(artUri));
                            loaded = (albumArt != null);
                        } else if ("file".equals(artUri.getScheme()) || artUri.getScheme() == null) {
                            // Handle file path or plain path
                            java.io.File file = new java.io.File(artUri.getPath());
                            if (file.exists() && file.canRead()) {
                                albumArt = BitmapFactory.decodeFile(file.getAbsolutePath());
                                loaded = (albumArt != null);
                            } else {
                                if (lastFailedArtUri == null || !lastFailedArtUri.equals(artUriString)) {
                                    Log.w(TAG, "Album art file does not exist or is not readable: " + artUriString);
                                    lastFailedArtUri = artUriString;
                                }
                            }
                        } else {
                            if (lastFailedArtUri == null || !lastFailedArtUri.equals(artUriString)) {
                                Log.w(TAG, "Unsupported album art URI scheme: " + artUriString);
                                lastFailedArtUri = artUriString;
                            }
                        }
                        if (!loaded && (lastFailedArtUri == null || !lastFailedArtUri.equals(artUriString))) {
                            Log.w(TAG, "Failed to load album art from URI: " + artUriString);
                            lastFailedArtUri = artUriString;
                        }
                    } catch (Exception e) {
                        if (lastFailedArtUri == null || !lastFailedArtUri.equals(artUriString)) {
                            Log.w(TAG, "Failed to load album art from URI: " + artUriString + " " + e);
                            lastFailedArtUri = artUriString;
                        }
                        albumArt = null;
                    }
                }

                // Fallback to embedded bitmaps if URI loading fails
                if (albumArt == null) {
                    albumArt = metadata.getBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART);
                }
                if (albumArt == null) {
                    albumArt = metadata.getBitmap(MediaMetadata.METADATA_KEY_ART);
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
            Log.e(TAG, "Failed to get media info", e);
        }
    }
}
