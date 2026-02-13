package com.saicmotor.sdk.vehiclesettings.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.saicmotor.sdk.vehiclesettings.LogUtil;

/* loaded from: classes2.dex */
public abstract class BaseManager {
    private static final String ACTION_NAME = "com.saicmotor.service.vehicle.VehicleService";
    private static final int MSG_RETRY_BIND_SERVICE = 0;
    private static final String PACKAGE_NAME = "com.saicmotor.service.vehicle";
    private static final String RETRY = "retry_thread";
    private static final String TAG = BaseManager.class.getSimpleName();
    private static final int TIME_RETRY_BIND_SERVICE = 1000;
    private Context mContext;
    private RetryHandler mRetryHandler;
    private HandlerThread mRetryHandlerThread;
    private boolean mServiceBound;
    private boolean mServiceConected;
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.saicmotor.sdk.vehiclesettings.manager.BaseManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.logE(BaseManager.TAG, "onServiceConnected: service is connected!");
            BaseManager.this.mServiceConected = true;
            if (service == null) {
                LogUtil.logE(BaseManager.TAG, "onServiceConnected: service is null!");
            } else {
                BaseManager.this.setBinder(service);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.logE(BaseManager.TAG, "onServiceDisconnected: service is disconnected!");
            BaseManager.this.mServiceConected = false;
            BaseManager.this.unbind();
            BaseManager.this.rebindService();
        }
    };

    protected abstract void setBinder(IBinder iBinder);

    protected abstract void unbind();

    protected BaseManager(Context context) {
        this.mContext = context.getApplicationContext();
        if (this.mRetryHandlerThread == null) {
            this.mRetryHandlerThread = new HandlerThread(RETRY);
            this.mRetryHandlerThread.start();
        }
        if (this.mRetryHandler == null) {
            this.mRetryHandler = new RetryHandler(this.mRetryHandlerThread.getLooper());
        }
    }

    protected void bindService() {
        Intent intent = new Intent();
        intent.setPackage(PACKAGE_NAME);
        intent.setAction(ACTION_NAME);
        this.mServiceBound = this.mContext.bindService(intent, this.mServiceConnection, 1);
        LogUtil.logE(TAG, "bindService: mServiceBound = " + this.mServiceBound);
        if (!this.mServiceBound) {
            if (this.mRetryHandler.hasMessages(0)) {
                this.mRetryHandler.removeMessages(0);
            }
            this.mRetryHandler.sendEmptyMessageDelayed(0, 1000L);
        }
    }

    protected void rebindService() {
        if (!this.mServiceConected) {
            bindService();
        }
    }

    protected void unbindService() {
        if (this.mServiceConected && this.mServiceBound) {
            this.mContext.unbindService(this.mServiceConnection);
            this.mServiceConected = false;
            this.mServiceBound = false;
        }
    }

    private class RetryHandler extends Handler {
        public RetryHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                BaseManager.this.rebindService();
            }
        }
    }
}
