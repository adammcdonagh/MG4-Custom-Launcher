package com.saicmotor.sdk.vehiclesettings.manager;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.IAirConditionListener;
import com.saicmotor.sdk.vehiclesettings.IAirConditionService;
import com.saicmotor.sdk.vehiclesettings.IHubService;
import com.saicmotor.sdk.vehiclesettings.LogUtil;
import com.saicmotor.sdk.vehiclesettings.VehicleServiceContract;
import com.saicmotor.sdk.vehiclesettings.bean.AirConditionBean;
import com.saicmotor.sdk.vehiclesettings.constant.AirConditionConst;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class AirConditionManager extends BaseManager {
    private static final String AIR_CONDITION = "aircondition";
    private static final int DATA_GET_ERROR = -1;
    private static final String TAG = "AirConditionManager";
    private static final float TEMPERATURE_ERROR_CONST = -10000.0f;
    private static AirConditionManager sAirConditionManager;
    private static VehicleServiceContract.IVehicleServiceListener sServiceListener;
    private IAirConditionListener mAirConditionListener;
    private IAirConditionService mAirConditionService;
    private List<VehicleServiceContract.IAirConditionCallback> mCallbackList;
    private HandlerThread mHandlerThread;
    private RecoveryHandler mRecoveryHandler;
    private static boolean sIsServiceConnected = false;
    private static boolean sRecoveryMode = false;
    private static long sTimeout = 0;
    private static int sIsRecoveryMarkID = 0;

    private AirConditionManager(Context context) {
        super(context);
        this.mCallbackList = new ArrayList();
        this.mAirConditionListener = new IAirConditionListener.Stub() { // from class:
                                                                        // com.saicmotor.sdk.vehiclesettings.manager.AirConditionManager.1
            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionListener
            public void onChangeEvent(AirConditionBean airConditionBean) throws RemoteException {
                int signalId;
                if (AirConditionManager.sRecoveryMode && (signalId = airConditionBean.getMarkedSignalId()) != 0) {
                    LogUtil.logD(AirConditionManager.TAG, "onChangeEvent: removeMessages id = " + signalId);
                    AirConditionManager.this.mRecoveryHandler.removeMessages(signalId);
                }
                if (AirConditionManager.this.mCallbackList != null) {
                    synchronized (AirConditionManager.this.mCallbackList) {
                        for (VehicleServiceContract.IAirConditionCallback callback : AirConditionManager.this.mCallbackList) {
                            callback.onAirConditionChangeEvent(airConditionBean);
                        }
                    }
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionListener
            public void onErrorEvent(String signalName, int code) throws RemoteException {
                if (AirConditionManager.this.mCallbackList != null) {
                    synchronized (AirConditionManager.this.mCallbackList) {
                        for (VehicleServiceContract.IAirConditionCallback callback : AirConditionManager.this.mCallbackList) {
                            callback.onAirConditionErrorEvent(signalName, code);
                        }
                    }
                }
            }
        };
        this.mHandlerThread = new HandlerThread(TAG);
        this.mHandlerThread.start();
        this.mRecoveryHandler = new RecoveryHandler(this.mHandlerThread.getLooper());
        bindService();
    }

    public static synchronized void init(Context context, VehicleServiceContract.IVehicleServiceListener listener) {
        sRecoveryMode = false;
        sServiceListener = listener;
        if (sAirConditionManager == null) {
            sAirConditionManager = new AirConditionManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sAirConditionManager);
        }
    }

    public static synchronized void init(Context context, VehicleServiceContract.IVehicleServiceListener listener,
            long timeout) {
        sTimeout = timeout;
        sRecoveryMode = true;
        sServiceListener = listener;
        if (sAirConditionManager == null) {
            sAirConditionManager = new AirConditionManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sAirConditionManager);
        }
    }

    public static AirConditionManager getInstance() {
        return sAirConditionManager;
    }

    public static int getIsRecovery() {
        return sIsRecoveryMarkID;
    }

    public synchronized void release() {
        if (sIsServiceConnected) {
            unbindService();
            sIsServiceConnected = false;
        }
        sAirConditionManager = null;
        if (this.mHandlerThread != null) {
            this.mRecoveryHandler.removeCallbacksAndMessages(null);
            this.mHandlerThread.quitSafely();
        }
    }

    private void sendMessage(int what) {
        LogUtil.logD(TAG,
                "sendMessage: what = " + what + "; sTimeout = " + sTimeout + "; sRecoveryMode = " + sRecoveryMode);
        if (sRecoveryMode) {
            this.mRecoveryHandler.removeMessages(what);
            this.mRecoveryHandler.sendEmptyMessageDelayed(what, sTimeout);
        }
    }

    @Override // com.saicmotor.sdk.vehiclesettings.manager.BaseManager
    protected void setBinder(IBinder binder) {
        IAirConditionListener iAirConditionListener;
        sIsServiceConnected = true;
        IHubService hubService = IHubService.Stub.asInterface(binder);
        try {
            this.mAirConditionService = IAirConditionService.Stub.asInterface(hubService.getService(AIR_CONDITION));
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null && (iAirConditionListener = this.mAirConditionListener) != null) {
            try {
                iAirConditionService.addAirConditionListener(iAirConditionListener);
            } catch (RemoteException exception2) {
                exception2.printStackTrace();
            }
        }
        sServiceListener.onServiceConnected(sAirConditionManager);
    }

    @Override // com.saicmotor.sdk.vehiclesettings.manager.BaseManager
    protected void unbind() {
        IAirConditionListener iAirConditionListener;
        sIsServiceConnected = false;
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null && (iAirConditionListener = this.mAirConditionListener) != null) {
            try {
                iAirConditionService.removeAirConditionListener(iAirConditionListener);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
        sServiceListener.onServiceDisconnected();
    }

    public void registerAirConditionCallback(VehicleServiceContract.IAirConditionCallback resultCallback) {
        List<VehicleServiceContract.IAirConditionCallback> list = this.mCallbackList;
        if (list != null && !list.contains(resultCallback)) {
            synchronized (this.mCallbackList) {
                this.mCallbackList.add(resultCallback);
            }
        }
    }

    public void unregisterAirConditionCallback(VehicleServiceContract.IAirConditionCallback resultCallback) {
        List<VehicleServiceContract.IAirConditionCallback> list = this.mCallbackList;
        if (list != null && list.contains(resultCallback)) {
            synchronized (this.mCallbackList) {
                this.mCallbackList.remove(resultCallback);
            }
        }
    }

    public AirConditionBean getAirConditionStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getAirConditionStatus();
        }
        return null;
    }

    public void setHvacPowerStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_STATUS_POWER);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setHvacPowerStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void openHvacPower() {
        sendMessage(AirConditionConst.SIGNAL_AIR_STATUS_POWER);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.openHvacPower();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void closeHvacPower() {
        sendMessage(AirConditionConst.SIGNAL_AIR_STATUS_POWER);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.closeHvacPower();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setAutoStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_STATUS_AUTO);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setAutoStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setAcStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_STATUS_AC);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setAcStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setLoopMode(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_MODE_LOOP);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setLoopMode(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void openLoopInner() {
        sendMessage(AirConditionConst.SIGNAL_AIR_MODE_LOOP);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.openLoopInner();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void openLoopOutside() {
        sendMessage(AirConditionConst.SIGNAL_AIR_MODE_LOOP);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.openLoopOutside();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void openLoopAuto() {
        sendMessage(AirConditionConst.SIGNAL_AIR_MODE_LOOP);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.openLoopAuto();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setEconStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_STATUS_ECON);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setEconStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setBlowerDirectionMode(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_MODE_BLOWER_DIRECTION);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setBlowerDirectionMode(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setAirVolumeLevel(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_AIR_VOLUME);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setAirVolumeLevel(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void increaseAirVolumeLevel(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_AIR_VOLUME);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.increaseAirVolumeLevel(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void reduceAirVolumeLevel(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_AIR_VOLUME);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.reduceAirVolumeLevel(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setAirVolumeMax() {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_AIR_VOLUME);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setAirVolumeMax();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setAirVolumeMin() {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_AIR_VOLUME);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setAirVolumeMin();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setDrvWindOutletBlowPersonModeStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_OUTLET_PERSON_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setDrvWindOutletBlowPersonModeStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setDrvWindOutletAvoidPersonModeStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_OUTLET_AVOID_PERSON_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setDrvWindOutletAvoidPersonModeStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setDrvWindOutletSwingModeStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_OUTLET_SWING_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setDrvWindOutletSwingModeStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setDrvWindOutletOffModeStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_OUTLET_OFF_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setDrvWindOutletOffModeStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setDrvLeftWindOutletAngle(float signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_ANGLE_LEFT_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setDrvLeftWindOutletAngle(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setDrvLeftMiddleWindOutletAngle(float signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_ANGLE_LEFT_MID_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setDrvLeftMiddleWindOutletAngle(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setPsgWindOutletBlowPersonModeStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_OUTLET_PERSON_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setPsgWindOutletBlowPersonModeStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setPsgWindOutletAvoidPersonModeStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_OUTLET_AVOID_PERSON_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setPsgWindOutletAvoidPersonModeStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setPsgWindOutletSwingModeStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_OUTLET_SWING_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setPsgWindOutletSwingModeStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setPsgWindOutletOffModeStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_OUTLET_OFF_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setPsgWindOutletOffModeStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setPsgRightWindOutletAngle(float signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_ANGLE_RIGHT_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setPsgRightWindOutletAngle(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setPsgRightMiddleWindOutletAngle(float signalValue) {
        sendMessage(AirConditionConst.SIGNAL_WIND_ANGLE_RIGHT_MID_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setPsgRightMiddleWindOutletAngle(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setWindOutletSmartStatus(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_STATUS_BLOWER_SMART);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setWindOutletSmartStatus(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setDrvSeatHeatLevel(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_SEAT_HEAT_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setDrvSeatHeatLevel(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void openDrvSeatHeat() {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_SEAT_HEAT_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.openDrvSeatHeat();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void closeDrvSeatHeat() {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_SEAT_HEAT_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.closeDrvSeatHeat();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setDrvSeatWindLevel(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_SEAT_WIND_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setDrvSeatWindLevel(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setPsgSeatHeatLevel(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_SEAT_HEAT_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setPsgSeatHeatLevel(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void openPsgSeatHeat() {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_SEAT_HEAT_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.openPsgSeatHeat();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void closePsgSeatHeat() {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_SEAT_HEAT_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.closePsgSeatHeat();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setPsgSeatWindLevel(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_LEVEL_SEAT_WIND_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setPsgSeatWindLevel(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setDrvTemp(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_TEMP_DRV);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setDrvTemp(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setPsgTemp(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_TEMP_PSG);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setPsgTemp(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void increaseTemp(int signalValue, boolean isDrv) {
        if (isDrv) {
            sendMessage(AirConditionConst.SIGNAL_AIR_TEMP_DRV);
        } else {
            sendMessage(AirConditionConst.SIGNAL_AIR_TEMP_PSG);
        }
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.increaseTemp(signalValue, isDrv);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void reduceTemp(int signalValue, boolean isDrv) {
        if (isDrv) {
            sendMessage(AirConditionConst.SIGNAL_AIR_TEMP_DRV);
        } else {
            sendMessage(AirConditionConst.SIGNAL_AIR_TEMP_PSG);
        }
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.reduceTemp(signalValue, isDrv);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setTempMax(boolean isDrv) {
        if (isDrv) {
            sendMessage(AirConditionConst.SIGNAL_AIR_TEMP_DRV);
        } else {
            sendMessage(AirConditionConst.SIGNAL_AIR_TEMP_PSG);
        }
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setTempMax(isDrv);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setTempMin(boolean isDrv) {
        if (isDrv) {
            sendMessage(AirConditionConst.SIGNAL_AIR_TEMP_DRV);
        } else {
            sendMessage(AirConditionConst.SIGNAL_AIR_TEMP_PSG);
        }
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setTempMin(isDrv);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setTempDualZoneOn(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_AIR_STATUS_TEMP_DUAL);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setTempDualZoneOn(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void openFrontWindowDefroster() {
        sendMessage(AirConditionConst.SIGNAL_FRONT_WINDOW_DEFROSTER);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.openFrontWindowDefroster();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void closeFrontWindowDefroster() {
        sendMessage(AirConditionConst.SIGNAL_FRONT_WINDOW_DEFROSTER);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.closeFrontWindowDefroster();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void openBackWindowDefroster() {
        sendMessage(AirConditionConst.SIGNAL_BACK_WINDOW_DEFROSTER);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.openBackWindowDefroster();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void closeBackWindowDefroster() {
        sendMessage(AirConditionConst.SIGNAL_BACK_WINDOW_DEFROSTER);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.closeBackWindowDefroster();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setSteeringWheelHeat(int signalValue) {
        sendMessage(AirConditionConst.SIGNAL_STEERING_WHEEL_HEAT);
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            try {
                iAirConditionService.setSteeringWheelHeat(signalValue);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public int getHvacPowerStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getHvacPowerStatus();
        }
        return -1;
    }

    public int getAutoStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getAutoStatus();
        }
        return -1;
    }

    public int getAcSwitch() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getAcSwitch();
        }
        return -1;
    }

    public int getLoopMode() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getLoopMode();
        }
        return -1;
    }

    public int getEconStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getEconStatus();
        }
        return -1;
    }

    public int getBlowerDirectionMode() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getBlowerDirectionMode();
        }
        return -1;
    }

    public int getAirVolumeLevel() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getAirVolumeLevel();
        }
        return -1;
    }

    public int getMaxAirVolume() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getMaxAirVolume();
        }
        return -1;
    }

    public int getMinAirVolume() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getMinAirVolume();
        }
        return -1;
    }

    public boolean isCurrentAirVolumeMin() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.isCurrentAirVolumeMin();
        }
        return false;
    }

    public boolean isCurrentAirVolumeMax() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.isCurrentAirVolumeMax();
        }
        return false;
    }

    public int getDrvWindOutletBlowPersonModeStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getDrvWindOutletBlowPersonModeStatus();
        }
        return -1;
    }

    public int getDrvWindOutletAvoidPersonModeStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getDrvWindOutletAvoidPersonModeStatus();
        }
        return -1;
    }

    public int getDrvWindOutletSwingModeStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getDrvWindOutletSwingModeStatus();
        }
        return -1;
    }

    public int getDrvWindOutletOffModeStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getDrvWindOutletOffModeStatus();
        }
        return -1;
    }

    public float getDrvLeftWindOutletAngle() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getDrvLeftWindOutletAngle();
        }
        return -1.0f;
    }

    public float getDrvLeftMiddleWindOutletAngle() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getDrvLeftMiddleWindOutletAngle();
        }
        return -1.0f;
    }

    public int getPsgWindOutletBlowPersonModeStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPsgWindOutletBlowPersonModeStatus();
        }
        return -1;
    }

    public int getPsgWindOutletAvoidPersonModeStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPsgWindOutletAvoidPersonModeStatus();
        }
        return -1;
    }

    public int getPsgWindOutletSwingModeStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPsgWindOutletSwingModeStatus();
        }
        return -1;
    }

    public int getPsgWindOutletOffModeStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPsgWindOutletOffModeStatus();
        }
        return -1;
    }

    public float getPsgRightWindOutletAngle() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPsgRightWindOutletAngle();
        }
        return -1.0f;
    }

    public float getPsgRightMiddleWindOutletAngle() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPsgRightMiddleWindOutletAngle();
        }
        return -1.0f;
    }

    public int getSmartBlowerStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getSmartBlowerStatus();
        }
        return -1;
    }

    public int getDrvSeatHeatLevel() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getDrvSeatHeatLevel();
        }
        return -1;
    }

    public int getDrvSeatWindLevel() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getDrvSeatWindLevel();
        }
        return -1;
    }

    public int getPsgSeatHeatLevel() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPsgSeatHeatLevel();
        }
        return -1;
    }

    public int getPsgSeatWindLevel() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPsgSeatWindLevel();
        }
        return -1;
    }

    public int getDrvTemp() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getDrvTemp();
        }
        return -1;
    }

    public int getPsgTemp() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPsgTemp();
        }
        return -1;
    }

    public int getMaxTemp() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getMaxTemp();
        }
        return -1;
    }

    public int getMinTemp() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getMinTemp();
        }
        return -1;
    }

    public boolean isCurrentTempMin(boolean isDrv) throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.isCurrentTempMin(isDrv);
        }
        return false;
    }

    public boolean isCurrentTempMax(boolean isDrv) throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.isCurrentTempMax(isDrv);
        }
        return false;
    }

    public int getTempDualZoneOn() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getTempDualZoneOn();
        }
        return -1;
    }

    public float getOutCarTemp() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getOutCarTemp();
        }
        return TEMPERATURE_ERROR_CONST;
    }

    public int getPm25Concentration() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPm25Concentration();
        }
        return -1;
    }

    public int getAnionStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getAnionStatus();
        }
        return -1;
    }

    public int getPm25Filter() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getPm25Filter();
        }
        return -1;
    }

    public int getFrontWindowDefroster() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getFrontWindowDefroster();
        }
        return -1;
    }

    public int getBackWindowDefroster() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getBackWindowDefroster();
        }
        return -1;
    }

    public int getSteeringWheelHeatLevel() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getSteeringWheelHeatLevel();
        }
        return -1;
    }

    public int getWindOutletCanStatus() throws RemoteException {
        IAirConditionService iAirConditionService = this.mAirConditionService;
        if (iAirConditionService != null) {
            return iAirConditionService.getWindOutletCanStatus();
        }
        return -1;
    }

    private final class RecoveryHandler extends Handler {
        public RecoveryHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            try {
                LogUtil.logD(AirConditionManager.TAG, "handleMessage: what = " + msg.what);
                int unused = AirConditionManager.sIsRecoveryMarkID = msg.what;
                if (AirConditionManager.this.mCallbackList != null
                        && AirConditionManager.this.mCallbackList.size() != 0) {
                    synchronized (AirConditionManager.this.mCallbackList) {
                        AirConditionBean airConditionBean = null;
                        try {
                            airConditionBean = AirConditionManager.this.getAirConditionStatus();
                        } catch (RemoteException exception) {
                            exception.printStackTrace();
                        }
                        if (airConditionBean != null) {
                            airConditionBean.setMarkedSignalId(msg.what);
                            for (VehicleServiceContract.IAirConditionCallback callback : AirConditionManager.this.mCallbackList) {
                                callback.onAirConditionChangeEvent(airConditionBean);
                            }
                        }
                    }
                    int unused2 = AirConditionManager.sIsRecoveryMarkID = 0;
                }
            } catch (Exception e) {
                LogUtil.logE(AirConditionManager.TAG, "handleMessage exception: " + e.getMessage());
            }
        }
    }
}
