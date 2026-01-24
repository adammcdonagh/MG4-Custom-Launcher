package com.custom.launcher.service;

import java.lang.reflect.Method;

import javax.naming.Context;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import main.java.com.custom.launcher.util.LogUtils;

/**
 * Service to monitor SAIC media service
 * Binds to com.saicmotor.service.media.MediaService
 */
public class SaicMediaService {
    private static final String TAG = "SaicMediaService";

    // SAIC Media Service constants
    private static final String MEDIA_SERVICE_PACKAGE = "com.saicmotor.service.media";
    private static final String MEDIA_SERVICE_CLASS = "com.saicmotor.service.media.MediaService";
    private static final String ACTION_PLAY_STATUS = "com.saicmotor.service.media.PLAY_STATUS_ACTION";

    // Media source types
    public static final int SOURCE_BT_MUSIC = 0;
    public static final int SOURCE_ONLINE_MUSIC = 1;
    public static final int SOURCE_USB_MUSIC = 2;

    private Context context;
    private IBinder mediaServiceBinder;
    private Object mediaServiceProxy;
    private MediaInfoListener listener;
    private boolean isBound = false;

    public interface MediaInfoListener {
        void onMediaInfoChanged(String title, String artist, String album, int duration, int position,
                boolean isPlaying);

        void onMediaSourceChanged(int source);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "MediaService connected");
            mediaServiceBinder = service;
            isBound = true;

            try {
                // Use reflection to create proxy since we don't have the AIDL stub
                Class<?> stubClass = Class.forName("com.saicmotor.sdk.media.IPlayStatusBinderInterface$Stub");
                Method asInterface = stubClass.getMethod("asInterface", IBinder.class);
                mediaServiceProxy = asInterface.invoke(null, service);

                // Register callback
                registerCallback();

                // Get current playback info
                getCurrentMediaInfo();

            } catch (Exception e) {
                Log.e(TAG, "Failed to create media service proxy", e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "MediaService disconnected");
            mediaServiceBinder = null;
            mediaServiceProxy = null;
            isBound = false;
        }
    };

    public SaicMediaService(Context context, MediaInfoListener listener) {
        this.context = context.getApplicationContext();
        this.listener = listener;
    }

    public void bind() {
        try {
            Intent intent = new Intent();
            intent.setClassName(MEDIA_SERVICE_PACKAGE, MEDIA_SERVICE_CLASS);
            intent.setAction(ACTION_PLAY_STATUS);

            boolean bound = context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            Log.d(TAG, "Binding to MediaService: " + bound);

        } catch (Exception e) {
            LogUtils.logError(TAG, "Failed to bind to MediaService", e);
        }
    }

    public void unbind() {
        if (isBound) {
            try {
                context.unbindService(serviceConnection);
                isBound = false;
            } catch (Exception e) {
                LogUtils.logError(TAG, "Failed to unbind from MediaService", e);
            }
        }
    }

    private void registerCallback() {
        if (mediaServiceProxy == null)
            return;

        try {
            // TODO: Implement callback registration using reflection
            // This would require implementing the AIDL callback interface
            Log.d(TAG, "TODO: Register media callback");

        } catch (Exception e) {
            LogUtils.logError(TAG, "Failed to register callback", e);
        }
    }

    private void getCurrentMediaInfo() {
        if (mediaServiceProxy == null)
            return;

        try {
            // Use reflection to call getCurrentMediaSource()
            Class<?> proxyClass = mediaServiceProxy.getClass();
            Method getCurrentSource = proxyClass.getMethod("getCurrentMediaSource");
            int source = (Integer) getCurrentSource.invoke(mediaServiceProxy);

            if (listener != null) {
                listener.onMediaSourceChanged(source);
            }

            Log.d(TAG, "Current media source: " + source);

        } catch (Exception e) {
            LogUtils.logError(TAG, "Failed to get current media info", e);
        }
    }

    public void sendPlayPauseCommand() {
        if (mediaServiceProxy == null)
            return;

        try {
            // Use reflection to call play/pause methods
            Class<?> proxyClass = mediaServiceProxy.getClass();
            // Note: Actual method signatures would need to be determined from the AIDL
            Log.d(TAG, "TODO: Send play/pause command");

        } catch (Exception e) {
            LogUtils.logError(TAG, "Failed to send play/pause command", e);
        }
    }

    public void sendNextCommand() {
        if (mediaServiceProxy == null)
            return;

        try {
            Log.d(TAG, "TODO: Send next command");
        } catch (Exception e) {
            LogUtils.logError(TAG, "Failed to send next command", e);
        }
    }

    public void sendPreviousCommand() {
        if (mediaServiceProxy == null)
            return;

        try {
            Log.d(TAG, "TODO: Send previous command");
        } catch (Exception e) {
            LogUtils.logError(TAG, "Failed to send previous command", e);
        }
    }
}
