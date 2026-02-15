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
import android.os.RemoteException;
import com.saicmotor.sdk.external.IPageService;
import com.saicmotor.sdk.vehiclesettings.LogUtil;
import com.saicmotor.sdk.vehiclesettings.VehicleServiceContract;

/* loaded from: classes2.dex */
public class PageManager {
    private static final int MSG_REBIND_SERVICE = 100;
    private static final String REBIND = "rebind_thread";
    public static final String SYSTEMUI_PACKAGE = "com.android.systemui";
    public static final String SYSTEMUI_PACKAGE_NAME_CLASS = "com.android.systemui.StartActivityService";
    public static final String SYSTEMUI_SAICMOTOR_ACTION = "com.android.systemui.saicmotor.action.StartActivity";
    private static final int TIME_REBIND_SERVICE = 1000;
    private static PageManager sPageManager;
    private static VehicleServiceContract.IPageServiceListener sServiceListener;
    private Context mContext;
    private IPageService mPageService;
    private RebindHandler mRebindHandler;
    private HandlerThread mRebindHandlerThread;
    private boolean mServiceBound;
    private boolean mServiceConnected;
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class:
                                                                                   // com.saicmotor.sdk.vehiclesettings.manager.PageManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.logE(PageManager.TAG, "onServiceConnected: service is connected!");
            PageManager.this.mServiceConnected = true;
            if (service == null) {
                LogUtil.logE(PageManager.TAG, "onServiceConnected: service is null!");
            } else {
                PageManager.this.setBinder(service);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.logE(PageManager.TAG, "onServiceDisconnected: service is disconnected!");
            PageManager.this.mServiceConnected = false;
            PageManager.this.mPageService = null;
            PageManager.this.unbind();
            PageManager.this.rebindService();
        }
    };
    private static final String TAG = PageManager.class.getSimpleName();
    private static boolean sIsServiceConnected = false;

    protected PageManager(Context context) {
        this.mContext = context.getApplicationContext();
        if (this.mRebindHandlerThread == null) {
            this.mRebindHandlerThread = new HandlerThread(REBIND);
            this.mRebindHandlerThread.start();
        }
        if (this.mRebindHandler == null) {
            this.mRebindHandler = new RebindHandler(this.mRebindHandlerThread.getLooper());
        }
        bindService();
    }

    public static synchronized void init(Context context, VehicleServiceContract.IPageServiceListener listener) {
        sServiceListener = listener;
        LogUtil.logD(TAG, "init: PageManager=" + sPageManager + ", sIsServiceConnected=" + sIsServiceConnected);
        if (sPageManager == null) {
            sPageManager = new PageManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sPageManager);
        }
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setAction(SYSTEMUI_SAICMOTOR_ACTION);
        intent.setComponent(new ComponentName(SYSTEMUI_PACKAGE, SYSTEMUI_PACKAGE_NAME_CLASS));
        this.mServiceBound = this.mContext.bindService(intent, this.mServiceConnection, 1);
        LogUtil.logE(TAG, "bindService: mServiceBound = " + this.mServiceBound);
        if (!this.mServiceBound) {
            if (this.mRebindHandler.hasMessages(100)) {
                this.mRebindHandler.removeMessages(100);
            }
            this.mRebindHandler.sendEmptyMessageDelayed(100, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rebindService() {
        if (!this.mServiceConnected) {
            bindService();
        }
    }

    private void unbindService() {
        if (this.mServiceConnected && this.mServiceBound) {
            this.mContext.unbindService(this.mServiceConnection);
            this.mServiceConnected = false;
            this.mServiceBound = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBinder(IBinder binder) {
        LogUtil.logD(TAG, " sIsServiceConnected setBinder");
        sIsServiceConnected = true;
        this.mPageService = IPageService.Stub.asInterface(binder);
        sServiceListener.onServiceConnected(sPageManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unbind() {
        sIsServiceConnected = false;
        sServiceListener.onServiceDisconnected();
    }

    public synchronized void release() {
        if (sIsServiceConnected) {
            unbindService();
            sIsServiceConnected = false;
        }
        sPageManager = null;
    }

    public void openHvac() {
        IPageService iPageService = this.mPageService;
        if (iPageService != null) {
            try {
                iPageService.openHvac();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void closeHvac() {
        IPageService iPageService = this.mPageService;
        if (iPageService != null) {
            try {
                iPageService.closeHvac();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    private class RebindHandler extends Handler {
        public RebindHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                PageManager.this.rebindService();
            }
        }
    }
}
