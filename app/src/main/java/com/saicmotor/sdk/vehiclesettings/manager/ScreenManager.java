package com.saicmotor.sdk.vehiclesettings.manager;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.saicmotor.sdk.screen.IScreenManagerService;
import com.saicmotor.sdk.screen.IScreenStateListener;
import com.saicmotor.sdk.vehiclesettings.IHubService;
import com.saicmotor.sdk.vehiclesettings.VehicleServiceContract;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ScreenManager extends BaseManager {
    public static final int REASON_AVM = 4;
    public static final int REASON_CALL = 3;
    public static final int REASON_DEFAULT = 0;
    public static final int REASON_PWM = 2;
    public static final int REASON_USR = 1;
    public static final int STATE_OFF = 0;
    public static final int STATE_OFF_TIME = 1;
    public static final int STATE_ON = 2;
    public static final int STATE_ON_TRANSIENT = 3;
    public static final int THEME_1 = 1;
    public static final int THEME_2 = 2;
    public static final int THEME_3 = 3;
    public static final int THEME_DEFAULT = 0;
    public static final int TYPE_OFF = 0;
    public static final int TYPE_ON = 2;
    public static final int TYPE_ON_TRANSIENT = 1;
    private static final String VEHICLE_SCREEN = "vehiclescreen";
    private static ScreenManager sScreenManager;
    private static VehicleServiceContract.IVehicleServiceListener sServiceListener;
    private List<VehicleServiceContract.IVehicleScreenCallback> mCallbackList;
    private HandlerThread mHandlerThread;
    private RecoveryHandler mRecoveryHandler;
    private IScreenStateListener mScreenOffListener;
    private IScreenManagerService mScreenService;
    private static final String TAG = ScreenManager.class.getSimpleName();
    public static boolean sIsServiceConnected = false;

    protected ScreenManager(Context context) {
        super(context);
        this.mCallbackList = new ArrayList();
        this.mScreenOffListener = new IScreenStateListener.Stub() { // from class: com.saicmotor.sdk.vehiclesettings.manager.ScreenManager.1
            @Override // com.saicmotor.sdk.screen.IScreenStateListener
            public void onScreenOffStateChanged(int state, int theme) throws RemoteException {
                synchronized (ScreenManager.this.mCallbackList) {
                    for (VehicleServiceContract.IVehicleScreenCallback callback : ScreenManager.this.mCallbackList) {
                        callback.onScreenStateChange(state, theme);
                    }
                }
            }

            @Override // com.saicmotor.sdk.screen.IScreenStateListener
            public void onStateChanged(int var1) throws RemoteException {
                synchronized (ScreenManager.this.mCallbackList) {
                    for (VehicleServiceContract.IVehicleScreenCallback callback : ScreenManager.this.mCallbackList) {
                        callback.onStateChanged(var1);
                    }
                }
            }
        };
        this.mHandlerThread = new HandlerThread(getClass().getSimpleName());
        this.mHandlerThread.start();
        this.mRecoveryHandler = new RecoveryHandler(this.mHandlerThread.getLooper());
        bindService();
    }

    public static synchronized void init(Context context, VehicleServiceContract.IVehicleServiceListener listener) {
        sServiceListener = listener;
        if (sScreenManager == null) {
            sScreenManager = new ScreenManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sScreenManager);
        }
    }

    public void wakeupScreen(int pid, boolean trans) throws RemoteException {
        this.mScreenService.screenWakeup(pid, trans);
    }

    public void screenSleep(int pid, boolean showTime, int theme) throws RemoteException {
        this.mScreenService.screenSleep(pid, showTime, theme);
    }

    public void resumeScreenSleep(int pid) throws RemoteException {
        this.mScreenService.resumeScreenSleep(pid);
    }

    public void registerCallback(VehicleServiceContract.IVehicleScreenCallback callback) {
        synchronized (this.mCallbackList) {
            if (!this.mCallbackList.contains(callback)) {
                this.mCallbackList.add(callback);
            }
        }
    }

    public void unregisterCallback(VehicleServiceContract.IVehicleScreenCallback callback) {
        synchronized (this.mCallbackList) {
            if (this.mCallbackList.contains(callback)) {
                this.mCallbackList.remove(callback);
            }
        }
    }

    public int getCurrentPowerMode() {
        IScreenManagerService iScreenManagerService = this.mScreenService;
        if (iScreenManagerService != null) {
            try {
                return iScreenManagerService.getCurrentPowerMode();
            } catch (RemoteException exception) {
                exception.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    @Override // com.saicmotor.sdk.vehiclesettings.manager.BaseManager
    protected void setBinder(IBinder binder) {
        IScreenStateListener iScreenStateListener;
        sIsServiceConnected = true;
        IHubService hubService = IHubService.Stub.asInterface(binder);
        try {
            this.mScreenService = IScreenManagerService.Stub.asInterface(hubService.getService(VEHICLE_SCREEN));
        } catch (RemoteException except) {
            except.printStackTrace();
        }
        IScreenManagerService iScreenManagerService = this.mScreenService;
        if (iScreenManagerService != null && (iScreenStateListener = this.mScreenOffListener) != null) {
            try {
                iScreenManagerService.registerListener(iScreenStateListener);
            } catch (RemoteException except2) {
                except2.printStackTrace();
            }
        }
        sServiceListener.onServiceConnected(sScreenManager);
    }

    public synchronized void release() {
        if (sIsServiceConnected) {
            unbindService();
            sIsServiceConnected = false;
        }
        sScreenManager = null;
    }

    @Override // com.saicmotor.sdk.vehiclesettings.manager.BaseManager
    protected void unbind() {
        IScreenStateListener iScreenStateListener;
        sIsServiceConnected = false;
        IScreenManagerService iScreenManagerService = this.mScreenService;
        if (iScreenManagerService != null && (iScreenStateListener = this.mScreenOffListener) != null) {
            try {
                iScreenManagerService.unregisterListener(iScreenStateListener);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        sServiceListener.onServiceDisconnected();
    }

    private final class RecoveryHandler extends Handler {
        public RecoveryHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
        }
    }
}
