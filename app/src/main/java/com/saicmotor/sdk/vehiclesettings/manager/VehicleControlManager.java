package com.saicmotor.sdk.vehiclesettings.manager;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.IHubService;
import com.saicmotor.sdk.vehiclesettings.IVehicleControlListener;
import com.saicmotor.sdk.vehiclesettings.IVehicleControlService;
import com.saicmotor.sdk.vehiclesettings.LogUtil;
import com.saicmotor.sdk.vehiclesettings.VehicleServiceContract;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleControlBean;
import com.saicmotor.sdk.vehiclesettings.constant.VehicleControlConst;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class VehicleControlManager extends BaseManager {
    private static final String VEHICLE_CONTROL = "vehiclecontrol";
    private static VehicleServiceContract.IVehicleServiceListener sServiceListener;
    private static VehicleControlManager sVehicleControlManager;
    private List<VehicleServiceContract.IVehicleControlCallback> mCallbackList;
    private HandlerThread mHandlerThread;
    private long mPresentTimeout;
    private RecoveryHandler mRecoveryHandler;
    private IVehicleControlListener mVehicleControlListener;
    private IVehicleControlService mVehicleControlService;
    private static final String TAG = VehicleControlManager.class.getSimpleName();
    private static boolean sIsServiceConnected = false;
    private static boolean sRecoveryMode = false;
    private static long sTimeout = 0;

    private VehicleControlManager(Context context) {
        super(context);
        this.mCallbackList = new ArrayList();
        this.mPresentTimeout = 0L;
        this.mVehicleControlListener = new IVehicleControlListener.Stub() { // from class: com.saicmotor.sdk.vehiclesettings.manager.VehicleControlManager.1
            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlListener
            public void onChangeEvent(VehicleControlBean vehicleControlBean) throws RemoteException {
                int signalId;
                if (VehicleControlManager.sRecoveryMode && (signalId = vehicleControlBean.getMarkedSignalId()) != 0) {
                    LogUtil.logD(VehicleControlManager.TAG, "onChangeEvent: removeMessages signalId = " + signalId);
                    VehicleControlManager.this.mRecoveryHandler.removeMessages(signalId);
                }
                if (VehicleControlManager.this.mCallbackList != null) {
                    synchronized (VehicleControlManager.this.mCallbackList) {
                        for (VehicleServiceContract.IVehicleControlCallback callback : VehicleControlManager.this.mCallbackList) {
                            callback.onVehicleControlChangeEvent(vehicleControlBean);
                        }
                    }
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlListener
            public void onErrorEvent(String signalName, int code) throws RemoteException {
                if (VehicleControlManager.this.mCallbackList != null) {
                    synchronized (VehicleControlManager.this.mCallbackList) {
                        for (VehicleServiceContract.IVehicleControlCallback callback : VehicleControlManager.this.mCallbackList) {
                            callback.onVehicleControlErrorEvent(signalName, code);
                        }
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
        sRecoveryMode = false;
        sServiceListener = listener;
        if (sVehicleControlManager == null) {
            sVehicleControlManager = new VehicleControlManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sVehicleControlManager);
        }
    }

    public static synchronized void init(Context context, VehicleServiceContract.IVehicleServiceListener listener, long timeout) {
        sTimeout = timeout;
        sRecoveryMode = true;
        sServiceListener = listener;
        if (sVehicleControlManager == null) {
            sVehicleControlManager = new VehicleControlManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sVehicleControlManager);
        }
    }

    public void setTimeoutOnce(long timeout) {
        this.mPresentTimeout = timeout;
    }

    public synchronized void release() {
        if (sIsServiceConnected) {
            unbindService();
            sIsServiceConnected = false;
        }
        sVehicleControlManager = null;
        sRecoveryMode = false;
        if (this.mHandlerThread != null) {
            this.mRecoveryHandler.removeCallbacksAndMessages(null);
            this.mHandlerThread.quitSafely();
        }
    }

    private void sendMessage(int what) {
        LogUtil.logD(TAG, "sendMessage: what = " + what + "; sTimeout = " + sTimeout + "; sRecoveryMode = " + sRecoveryMode + "; mPresentTimeout = " + this.mPresentTimeout);
        if (this.mPresentTimeout != 0) {
            this.mRecoveryHandler.removeMessages(what);
            this.mRecoveryHandler.sendEmptyMessageDelayed(what, this.mPresentTimeout);
            this.mPresentTimeout = 0L;
        } else if (sRecoveryMode) {
            this.mRecoveryHandler.removeMessages(what);
            this.mRecoveryHandler.sendEmptyMessageDelayed(what, sTimeout);
        }
    }

    @Override // com.saicmotor.sdk.vehiclesettings.manager.BaseManager
    protected void setBinder(IBinder binder) {
        IVehicleControlListener iVehicleControlListener;
        sIsServiceConnected = true;
        IHubService hubService = IHubService.Stub.asInterface(binder);
        try {
            this.mVehicleControlService = IVehicleControlService.Stub.asInterface(hubService.getService(VEHICLE_CONTROL));
        } catch (RemoteException except) {
            except.printStackTrace();
        }
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null && (iVehicleControlListener = this.mVehicleControlListener) != null) {
            try {
                iVehicleControlService.addVehicleControlListener(iVehicleControlListener);
            } catch (RemoteException except2) {
                except2.printStackTrace();
            }
        }
        sServiceListener.onServiceConnected(sVehicleControlManager);
    }

    @Override // com.saicmotor.sdk.vehiclesettings.manager.BaseManager
    protected void unbind() {
        IVehicleControlListener iVehicleControlListener;
        sIsServiceConnected = false;
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null && (iVehicleControlListener = this.mVehicleControlListener) != null) {
            try {
                iVehicleControlService.removeVehicleControlListener(iVehicleControlListener);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        sServiceListener.onServiceDisconnected();
    }

    public void registerVehicleControlCallback(VehicleServiceContract.IVehicleControlCallback resultCallback) {
        List<VehicleServiceContract.IVehicleControlCallback> list = this.mCallbackList;
        if (list != null && !list.contains(resultCallback)) {
            synchronized (this.mCallbackList) {
                this.mCallbackList.add(resultCallback);
            }
        }
    }

    public void unregisterVehicleControlCallback(VehicleServiceContract.IVehicleControlCallback resultCallback) {
        List<VehicleServiceContract.IVehicleControlCallback> list = this.mCallbackList;
        if (list != null && list.contains(resultCallback)) {
            synchronized (this.mCallbackList) {
                this.mCallbackList.remove(resultCallback);
            }
        }
    }

    public int getDoorLock() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getDoorLock();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDoorLock(int signalValue) {
        sendMessage(1001);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setDoorLock(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public float getDriveWindow() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getDriveWindow();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public void setDriveWindow(float signalValue) {
        sendMessage(1002);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setDriveWindow(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public float getPassengerWindow() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getPassengerWindow();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public void setPassengerWindow(float signalValue) {
        sendMessage(1003);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setPassengerWindow(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public float getLeftRearWindow() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getLeftRearWindow();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public void setLeftRearWindow(float signalValue) {
        sendMessage(1004);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setLeftRearWindow(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public float getRightRearWindow() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getRightRearWindow();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public void setRightRearWindow(float signalValue) {
        sendMessage(VehicleControlConst.SIGNAL_RIGHT_REAR_WINDOW);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setRightRearWindow(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public float getElectricTailgateLock() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getElectricTailgateLock();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public void setElectricTailgateLock(float signalValue) {
        sendMessage(1006);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setElectricTailgateLock(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getSunroofSwitch() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getSunroofSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setSunroofSwitch(int signalValue) {
        sendMessage(1007);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setSunroofSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getSunroofVentilation() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getSunroofVentilation();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setSunroofVentilation(int signalValue) {
        sendMessage(1007);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setSunroofVentilation(signalValue);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getEspSwitch() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getEspSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setEspSwitch(int signalValue) {
        sendMessage(1009);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setEspSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getHdcSwitch() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getHdcSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setHdcSwitch(int signalValue) {
        sendMessage(1010);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setHdcSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getPdcSwitch() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getPdcSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setPdcSwitch(int signalValue) {
        sendMessage(1011);
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                iVehicleControlService.setPdcSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public VehicleControlBean getVehicleControlStatus() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getVehicleControlStatus();
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        return new VehicleControlBean();
    }

    public boolean resetVehicleSettings(int toReset) {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.resetVehicleSettings(toReset);
            } catch (RemoteException except) {
                except.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private final class RecoveryHandler extends Handler {
        public RecoveryHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            LogUtil.logD(VehicleControlManager.TAG, "handleMessage: what = " + msg.what);
            if (VehicleControlManager.this.mCallbackList != null && VehicleControlManager.this.mCallbackList.size() != 0) {
                synchronized (VehicleControlManager.this.mCallbackList) {
                    VehicleControlBean vehicleControlBean = VehicleControlManager.this.getVehicleControlStatus();
                    vehicleControlBean.setMarkedSignalId(msg.what);
                    for (VehicleServiceContract.IVehicleControlCallback callback : VehicleControlManager.this.mCallbackList) {
                        callback.onVehicleControlChangeEvent(vehicleControlBean);
                    }
                }
            }
        }
    }

    public int getVseSts() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getVseSts();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getTcsOpngMd() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getTcsOpngMd();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getTcsOpngSts() {
        IVehicleControlService iVehicleControlService = this.mVehicleControlService;
        if (iVehicleControlService != null) {
            try {
                return iVehicleControlService.getTcsOpngSts();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }
}
