package com.saicmotor.sdk.vehiclesettings.manager;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.IBleStateCallback;
import com.saicmotor.sdk.vehiclesettings.IHubService;
import com.saicmotor.sdk.vehiclesettings.IVehicleChargingListener;
import com.saicmotor.sdk.vehiclesettings.IVehicleChargingService;
import com.saicmotor.sdk.vehiclesettings.LogUtil;
import com.saicmotor.sdk.vehiclesettings.VehicleServiceContract;
import com.saicmotor.sdk.vehiclesettings.bean.ChargingPile;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleChargingBean;
import com.saicmotor.sdk.vehiclesettings.constant.VehicleChargingConst;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class VehicleChargingManager extends BaseManager {
    private static final int DEFALUT_ELEC_CSUMP_PER_SIZE = 50;
    private static final String VEHICLE_CHARGING = "vehiclecharging";
    private static VehicleServiceContract.IVehicleServiceListener sServiceListener;
    private static VehicleChargingManager sVehicleChargingManager;
    private List<VehicleServiceContract.IVehicleChargingCallback> mCallbackList;
    private HandlerThread mHandlerThread;
    private long mPresentTimeout;
    private RecoveryHandler mRecoveryHandler;
    private IVehicleChargingListener mVehicleChargingListener;
    private IVehicleChargingService mVehicleChargingService;
    private static final String TAG = VehicleChargingManager.class.getSimpleName();
    private static boolean sIsServiceConnected = false;
    private static boolean sRecoveryMode = false;
    private static long sTimeout = 0;

    private VehicleChargingManager(Context context) {
        super(context);
        this.mCallbackList = new ArrayList();
        this.mPresentTimeout = 0L;
        this.mVehicleChargingListener = new IVehicleChargingListener.Stub() { // from class: com.saicmotor.sdk.vehiclesettings.manager.VehicleChargingManager.1
            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingListener
            public void onChangeEvent(VehicleChargingBean vehicleChargingBean) throws RemoteException {
                int signalId = vehicleChargingBean.getMarkedSignalId();
                if (signalId != 0 && !VehicleChargingManager.this.mRecoveryHandler.hasMessages(signalId) && VehicleChargingManager.this.mCallbackList != null) {
                    synchronized (VehicleChargingManager.this.mCallbackList) {
                        for (VehicleServiceContract.IVehicleChargingCallback callback : VehicleChargingManager.this.mCallbackList) {
                            callback.onVehicleChargingChangeEvent(vehicleChargingBean);
                        }
                    }
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingListener
            public void onErrorEvent(String signalName, int code) throws RemoteException {
                if (VehicleChargingManager.this.mCallbackList != null) {
                    synchronized (VehicleChargingManager.this.mCallbackList) {
                        for (VehicleServiceContract.IVehicleChargingCallback callback : VehicleChargingManager.this.mCallbackList) {
                            callback.onVehicleChargingErrorEvent(signalName, code);
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
        if (sVehicleChargingManager == null) {
            sVehicleChargingManager = new VehicleChargingManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sVehicleChargingManager);
        }
    }

    public static synchronized void init(Context context, VehicleServiceContract.IVehicleServiceListener listener, long timeout) {
        sTimeout = timeout;
        sRecoveryMode = true;
        sServiceListener = listener;
        if (sVehicleChargingManager == null) {
            sVehicleChargingManager = new VehicleChargingManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sVehicleChargingManager);
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
        sVehicleChargingManager = null;
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
        IVehicleChargingListener iVehicleChargingListener;
        sIsServiceConnected = true;
        IHubService hubService = IHubService.Stub.asInterface(binder);
        try {
            this.mVehicleChargingService = IVehicleChargingService.Stub.asInterface(hubService.getService(VEHICLE_CHARGING));
        } catch (RemoteException except) {
            except.printStackTrace();
        }
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null && (iVehicleChargingListener = this.mVehicleChargingListener) != null) {
            try {
                iVehicleChargingService.addVehicleChargingListener(iVehicleChargingListener);
            } catch (RemoteException except2) {
                except2.printStackTrace();
            }
        }
        sServiceListener.onServiceConnected(sVehicleChargingManager);
    }

    @Override // com.saicmotor.sdk.vehiclesettings.manager.BaseManager
    protected void unbind() {
        IVehicleChargingListener iVehicleChargingListener;
        sIsServiceConnected = false;
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null && (iVehicleChargingListener = this.mVehicleChargingListener) != null) {
            try {
                iVehicleChargingService.removeVehicleChargingListener(iVehicleChargingListener);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        sServiceListener.onServiceDisconnected();
    }

    public void registerVehicleChargingCallback(VehicleServiceContract.IVehicleChargingCallback resultCallback) {
        List<VehicleServiceContract.IVehicleChargingCallback> list = this.mCallbackList;
        if (list != null && !list.contains(resultCallback)) {
            synchronized (this.mCallbackList) {
                this.mCallbackList.add(resultCallback);
            }
        }
    }

    public void unregisterVehicleChargingCallback(VehicleServiceContract.IVehicleChargingCallback resultCallback) {
        List<VehicleServiceContract.IVehicleChargingCallback> list = this.mCallbackList;
        if (list != null && list.contains(resultCallback)) {
            synchronized (this.mCallbackList) {
                this.mCallbackList.remove(resultCallback);
            }
        }
    }

    public float getCurrentElectricQuantity() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getCurrentElectricQuantity();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public int getCurrentEnduranceMileage() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getCurrentEnduranceMileage();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getPredictChargingTime() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getPredictChargingTime();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getChargingClosePredictMileage() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getChargingClosePredictMileage();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public float getActualChargingCurrent() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getActualChargingCurrent();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public float getExpectedCurrent() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getExpectedCurrent();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public int getChargingStatus() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getChargingStatus();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getChargingStopReason() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getChargingStopReason();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getChargingCurrent() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getChargingCurrent();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setChargingCurrent(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_CHARGING_CURRENT);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setChargingCurrent(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getChargingCloseSoc() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getChargingCloseSoc();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setChargingCloseSoc(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_CHARGING_CLOSE_SOC);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setChargingCloseSoc(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getChargingLockSwitch() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getChargingLockSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setChargingLockSwitch(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_CHARGING_LOCK_SWITCH);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setChargingLockSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getChargingControlSwitch() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getChargingControlSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setChargingControlSwitch(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_CHARGING_CONTROL_SWITCH);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setChargingControlSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getReserChrgControl() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getReserChrgControl();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setReserChrgControl(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_RESER_CHRG_CONTROL);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setReserChrgControl(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getReserChrgStartHour() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getReserChrgStartHour();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setReserChrgStartHour(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_RESER_CHRG_START_HOUR);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setReserChrgStartHour(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getReserChrgStartMinute() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getReserChrgStartMinute();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setReserChrgStartMinute(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_RESER_CHRG_START_MINUTE);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setReserChrgStartMinute(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getReserChrgStopHour() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getReserChrgStopHour();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setReserChrgStopHour(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_RESER_CHRG_STOP_HOUR);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setReserChrgStopHour(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getReserChrgStopMinute() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getReserChrgStopMinute();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setReserChrgStopMinute(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_RESER_CHRG_STOP_MINUTE);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setReserChrgStopMinute(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public boolean getCurrentElectricQuantityV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getCurrentElectricQuantityV();
            } catch (RemoteException except) {
                except.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean getCurrentEnduranceMileageV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getCurrentEnduranceMileageV();
            } catch (RemoteException except) {
                except.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean getPredictChargingTimeV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getPredictChargingTimeV();
            } catch (RemoteException except) {
                except.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean getChargingClosePredictMileageV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getChargingClosePredictMileageV();
            } catch (RemoteException except) {
                except.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean getActualChargingCurrentV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getActualChargingCurrentV();
            } catch (RemoteException except) {
                except.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean getExpectedCurrentV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getExpectedCurrentV();
            } catch (RemoteException except) {
                except.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public int getPredictDischrgTimeV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getPredictDischrgTimeV();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getDischrgClosePredictMileageV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getDischrgClosePredictMileageV();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getDrivingBatteryHeat() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getDrivingBatteryHeat();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDrivingBatteryHeat(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_DRIVING_BATTERY_HEAT);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setDrivingBatteryHeat(signalValue);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getReserChrgAdpPileType() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getReserChrgAdpPileType();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setReserChrgAdpPileType(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_RESER_CHRG_ADP_PILE_TYPE);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setReserChrgAdpPileType(signalValue);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getPredictDischrgTime() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getPredictDischrgTime();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getSuspendDischrgReason() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getSuspendDischrgReason();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getDischrgClosePredictMileage() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getDischrgClosePredictMileage();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getDischrgCloseSoc() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getDischrgCloseSoc();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDischrgCloseSoc(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_DISCHARGING_CLOSE_SOC);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setDischrgCloseSoc(signalValue);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getDischrgCloseSocResp() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getDischrgCloseSocResp();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getDischrgControlSwitch() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getDischrgControlSwitch();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDischrgControlSwitch(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_DISCHARGING_CONTROL_SWITCH);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setDischrgControlSwitch(signalValue);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getDischrgControlStatus() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getDischrgControlStatus();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public VehicleChargingBean getVehicleChargingStatus() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getVehicleChargingStatus();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        return new VehicleChargingBean();
    }

    public void registerBleStateCallback(IBleStateCallback callback) {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.registerBleStateCallback(callback);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void unregisterBleStateCallback(IBleStateCallback callback) {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.unregisterBleStateCallback(callback);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean getBtEnabled() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getBtEnabled();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void setBtEnabled(boolean enable) {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setBtEnabled(enable);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void startScan(boolean autoConnect) {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.startScan(autoConnect);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void stopScan() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.stopScan();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getConnectionState(ChargingPile device) {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getConnectionState(device);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getWirelessChargeState() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getWirelessChargeStat();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getClstrDistUnit() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getClstrDistUnit();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setClstrDistUnit(int signalValue) {
        sendMessage(VehicleChargingConst.SIGNAL_CLSTR_DIST_UNIT);
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.setClstrDistUnit(signalValue);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getElectricityLevel() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getElectricityLevel();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void connectChargingPile(ChargingPile device) {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.connectChargingPile(device);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void disconnectChargingPile(ChargingPile device) {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.disconnectChargingPile(device);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addChargingPile(String name) {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.addChargingPile(name);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteChargingPile(ChargingPile device) {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                iVehicleChargingService.deleteChargingPile(device);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<ChargingPile> getChargingPileList() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getChargingPileList();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        return new ArrayList();
    }

    public float getPowerBatteryVol() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getPowerBatteryVol();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public int getPowerBatteryVolV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getPowerBatteryVolV();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public float getElecCsumpPerKm() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getElecCsumpPerKm();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public int getElecCsumpPerKmV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getElecCsumpPerKmV();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public String getElecCsumpPerKmList() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getElecCsumpPerKmList();
            } catch (RemoteException except) {
                except.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public float getElecCsumpPerKmh() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getElecCsumpPerKmh();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public int getElecCsumpPerKmhV() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getElecCsumpPerKmhV();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public String getElecCsumpPerKmhList() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getElecCsumpPerKmhList();
            } catch (RemoteException except) {
                except.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public int getEnergyFlowInfo() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getEnergyFlowInfo();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public float getAccConsumptionAfterCharge() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getAccConsumptionAfterCharge();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public float getAccConsumptionAfterStart() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getAccConsumptionAfterStart();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public float getTotalConsumptionAfterCharge() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getTotalConsumptionAfterCharge();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public float getTotalConsumptionAfterStart() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getTotalConsumptionAfterStart();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public float getTotalRegenEnrgAfterCharge() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getTotalRegenEnrgAfterCharge();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public float getTotalRegenEnrgAfterStart() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getTotalRegenEnrgAfterStart();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public float getTotalRegenRngAfterCharge() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getTotalRegenRngAfterCharge();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public float getTotalRegenRngAfterStart() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getTotalRegenRngAfterStart();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public int getWrnngInfo() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getWrnngInfo();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getWrnngInfoPv() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getWrnngInfoPv();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getWrnngInfoRc() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getWrnngInfoRc();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getSwrnngInfo() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getSwrnngInfo();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getSwrnngInfoPv() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getSwrnngInfoPv();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getSwrnngInfoRc() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getSwrnngInfoRc();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getChrgngDoorPos() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getChrgngDoorPos();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public float getAcCurrent() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getAcCurrent();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public float getAcVoltage() {
        IVehicleChargingService iVehicleChargingService = this.mVehicleChargingService;
        if (iVehicleChargingService != null) {
            try {
                return iVehicleChargingService.getAcVoltage();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    private final class RecoveryHandler extends Handler {
        public RecoveryHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            LogUtil.logD(VehicleChargingManager.TAG, "handleMessage: what = " + msg.what);
            if (VehicleChargingManager.this.mCallbackList != null && VehicleChargingManager.this.mCallbackList.size() != 0) {
                synchronized (VehicleChargingManager.this.mCallbackList) {
                    VehicleChargingBean vehicleChargingBean = VehicleChargingManager.this.getVehicleChargingStatus();
                    vehicleChargingBean.setMarkedSignalId(msg.what);
                    for (VehicleServiceContract.IVehicleChargingCallback callback : VehicleChargingManager.this.mCallbackList) {
                        callback.onVehicleChargingChangeEvent(vehicleChargingBean);
                    }
                }
            }
        }
    }
}
