package com.saicmotor.sdk.vehiclesettings.manager;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.IHubService;
import com.saicmotor.sdk.vehiclesettings.IVehicleSettingListener;
import com.saicmotor.sdk.vehiclesettings.IVehicleSettingService;
import com.saicmotor.sdk.vehiclesettings.LogUtil;
import com.saicmotor.sdk.vehiclesettings.VehicleServiceContract;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleSettingBean;
import com.saicmotor.sdk.vehiclesettings.constant.VehicleSettingConst;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class VehicleSettingManager extends BaseManager {
    private static final String VEHICLE_SETTING = "vehiclesetting";
    private static VehicleServiceContract.IVehicleServiceListener sServiceListener;
    private static VehicleSettingManager sVehicleSettingManager;
    private List<VehicleServiceContract.IVehicleSettingCallback> mCallbackList;
    private HandlerThread mHandlerThread;
    private long mPresentTimeout;
    private RecoveryHandler mRecoveryHandler;
    private IVehicleSettingListener mVehicleSettingListener;
    private IVehicleSettingService mVehicleSettingService;
    private static final String TAG = VehicleSettingManager.class.getSimpleName();
    private static boolean sIsServiceConnected = false;
    private static boolean sRecoveryMode = false;
    private static long sTimeout = 0;

    private VehicleSettingManager(Context context) {
        super(context);
        this.mCallbackList = new ArrayList();
        this.mPresentTimeout = 0L;
        this.mVehicleSettingListener = new IVehicleSettingListener.Stub() { // from class: com.saicmotor.sdk.vehiclesettings.manager.VehicleSettingManager.1
            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingListener
            public void onChangeEvent(VehicleSettingBean vehicleSettingBean) throws RemoteException {
                int signalId = vehicleSettingBean.getMarkedSignalId();
                if (signalId != 0 && !VehicleSettingManager.this.mRecoveryHandler.hasMessages(signalId) && VehicleSettingManager.this.mCallbackList != null) {
                    synchronized (VehicleSettingManager.this.mCallbackList) {
                        for (VehicleServiceContract.IVehicleSettingCallback callback : VehicleSettingManager.this.mCallbackList) {
                            callback.onVehicleSettingChangeEvent(vehicleSettingBean);
                        }
                    }
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingListener
            public void onErrorEvent(String signalName, int code) throws RemoteException {
                if (VehicleSettingManager.this.mCallbackList != null) {
                    synchronized (VehicleSettingManager.this.mCallbackList) {
                        for (VehicleServiceContract.IVehicleSettingCallback callback : VehicleSettingManager.this.mCallbackList) {
                            callback.onVehicleSettingErrorEvent(signalName, code);
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
        if (sVehicleSettingManager == null) {
            sVehicleSettingManager = new VehicleSettingManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sVehicleSettingManager);
        }
    }

    public static synchronized void init(Context context, VehicleServiceContract.IVehicleServiceListener listener, long timeout) {
        sTimeout = timeout;
        sRecoveryMode = true;
        sServiceListener = listener;
        if (sVehicleSettingManager == null) {
            sVehicleSettingManager = new VehicleSettingManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sVehicleSettingManager);
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
        sVehicleSettingManager = null;
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
        IVehicleSettingListener iVehicleSettingListener;
        sIsServiceConnected = true;
        IHubService hubService = IHubService.Stub.asInterface(binder);
        try {
            this.mVehicleSettingService = IVehicleSettingService.Stub.asInterface(hubService.getService(VEHICLE_SETTING));
        } catch (RemoteException except) {
            except.printStackTrace();
        }
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null && (iVehicleSettingListener = this.mVehicleSettingListener) != null) {
            try {
                iVehicleSettingService.addVehicleSettingListener(iVehicleSettingListener);
            } catch (RemoteException except2) {
                except2.printStackTrace();
            }
        }
        sServiceListener.onServiceConnected(sVehicleSettingManager);
    }

    @Override // com.saicmotor.sdk.vehiclesettings.manager.BaseManager
    protected void unbind() {
        IVehicleSettingListener iVehicleSettingListener;
        sIsServiceConnected = false;
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null && (iVehicleSettingListener = this.mVehicleSettingListener) != null) {
            try {
                iVehicleSettingService.removeVehicleSettingListener(iVehicleSettingListener);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        sServiceListener.onServiceDisconnected();
    }

    public void registerVehicleSettingCallback(VehicleServiceContract.IVehicleSettingCallback resultCallback) {
        List<VehicleServiceContract.IVehicleSettingCallback> list = this.mCallbackList;
        if (list != null && !list.contains(resultCallback)) {
            synchronized (this.mCallbackList) {
                this.mCallbackList.add(resultCallback);
            }
        }
    }

    public void unregisterVehicleSettingCallback(VehicleServiceContract.IVehicleSettingCallback resultCallback) {
        List<VehicleServiceContract.IVehicleSettingCallback> list = this.mCallbackList;
        if (list != null && list.contains(resultCallback)) {
            synchronized (this.mCallbackList) {
                this.mCallbackList.remove(resultCallback);
            }
        }
    }

    public int getAutoModeAirVolume() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAutoModeAirVolume();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAutoModeAirVolume(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AUTO_MODE_AIR_VOLUME);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAutoModeAirVolume(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getHvacEconLinkage() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getHvacEconLinkage();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setHvacEconLinkage(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_HVAC_ECON_LINKAGE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setHvacEconLinkage(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getDefrostLinkage() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getDefrostLinkage();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDefrostLinkage(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_DEFROST_LINKAGE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setDefrostLinkage(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAqsSensitivity() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAqsSensitivity();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAqsSensitivity(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AQS_SENSITIVITY);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAqsSensitivity(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getSeatHeatVentLinkage() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getSeatHeatVentLinkage();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setSeatHeatVentLinkage(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_SEAT_HEAT_VENT_LINKAGE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setSeatHeatVentLinkage(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public float getElectricTailgatePos() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getElectricTailgatePos();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public void setElectricTailgatePos(float signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_ELECTRIC_TAILGATE_POS);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setElectricTailgatePos(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getDrivingAutoLock() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getDrivingAutoLock();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDrivingAutoLock(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_DRIVING_AUTO_LOCK);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setDrivingAutoLock(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getStallingAutoUnlock() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getStallingAutoUnlock();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setStallingAutoUnlock(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_STALLING_AUTO_UNLOCK);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setStallingAutoUnlock(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getKeyUnlockMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getKeyUnlockMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setKeyUnlockMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_KEY_UNLOCK_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setKeyUnlockMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getNearfieldUnlockMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getNearfieldUnlockMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setNearfieldUnlockMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_NEARFIELD_UNLOCK_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setNearfieldUnlockMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getApproachUnlockMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getApproachUnlockMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setApproachUnlockMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_APPROACH_UNLOCK_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setApproachUnlockMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLeaveAutoLockMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLeaveAutoLockMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLeaveAutoLockMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_LEAVE_AUTO_LOCK_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLeaveAutoLockMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getInductiveTailgate() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getInductiveTailgate();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setInductiveTailgate(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_INDUCTIVE_TAILGATE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setInductiveTailgate(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getInductiveDoorHandle() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getInductiveDoorHandle();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setInductiveDoorHandle(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_INDUCTIVE_DOOR_HANDLE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setInductiveDoorHandle(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLeftRearviewDowndip() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLeftRearviewDowndip();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLeftRearviewDowndip(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_LEFT_REARVIEW_DOWNDIP);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLeftRearviewDowndip(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getRightRearviewDowndip() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getRightRearviewDowndip();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setRightRearviewDowndip(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_RIGHT_REARVIEW_DOWNDIP);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setRightRearviewDowndip(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getOuterRearviewFold() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getOuterRearviewFold();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setOuterRearviewFold(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_OUTER_REARVIEW_FOLD);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setOuterRearviewFold(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getSeatAutoAdjust() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getSeatAutoAdjust();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setSeatAutoAdjust(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_SEAT_AUTO_ADJUST);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setSeatAutoAdjust(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getDriverSeatAutoWlcm() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getDriverSeatAutoWlcm();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDriverSeatAutoWlcm(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_DRIVER_SEAT_AUTO_WELCOME);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setDriverSeatAutoWlcm(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getSteeringWheelDefine() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getSteeringWheelDefine();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setSteeringWheelDefine(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_STEERING_WHEEL_DEFINE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setSteeringWheelDefine(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getHvacCustomSwitch() throws RemoteException {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            return iVehicleSettingService.getHvacCustomSwitch();
        }
        return -1;
    }

    public void setHvacCustomSwitch(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_HVAC_CUSTOM_SWITCH);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setHvacCustomSwitch(signalValue);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getWelcomeSoundOn() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getWelcomeSoundOn();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setWelcomeSoundOn(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_WELCOME_SOUND_ON);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setWelcomeSoundOn(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getWelcomeSoundType() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getWelcomeSoundType();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setWelcomeSoundType(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_WELCOME_SOUND_TYPE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setWelcomeSoundType(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getWelcomeLightTime() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getWelcomeLightTime();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setWelcomeLightTime(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_WELCOME_LIGHT_TIME);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setWelcomeLightTime(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getHomeLightTime() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getHomeLightTime();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setHomeLightTime(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_HOME_LIGHT_TIME);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setHomeLightTime(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAutoMainBeamControl() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAutoMainBeamControl();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAutoMainBeamControl(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AUTO_MAIN_BEAM_SWITCH);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAutoMainBeamControl(signalValue);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getAmbtLightGlbOn() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAmbtLightGlbOn();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAmbtLightGlbOn(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AMBT_LIGHT_GLB_ON);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAmbtLightGlbOn(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAmbtLightOpenMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAmbtLightOpenMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAmbtLightOpenMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AMBT_LIGHT_OPEN_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAmbtLightOpenMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAmbtLightDrvMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAmbtLightDrvMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAmbtLightDrvMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AMBT_LIGHT_DRV_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAmbtLightDrvMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAmbtLightColor() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAmbtLightColor();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAmbtLightColor(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AMBT_LIGHT_CUST);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAmbtLightColor(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAmbtLightBrightness() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAmbtLightBrightness();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAmbtLightBrightness(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AMBT_LIGHT_CUST);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAmbtLightBrightness(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAmbtLightBreathingOn() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAmbtLightBreathingOn();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAmbtLightBreathingOn(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AMBT_LIGHT_CUST);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAmbtLightBreathingOn(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAmbtLightWlcmOn() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAmbtLightWlcmOn();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAmbtLightWlcmOn(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AMBT_LIGHT_WLCM_ON);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAmbtLightWlcmOn(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAmbtLightWlcmMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAmbtLightWlcmMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAmbtLightWlcmMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AMBT_LIGHT_WLCM_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAmbtLightWlcmMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getStartLinkage() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getStartLinkage();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setStartLinkage(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_START_LINKAGE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setStartLinkage(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getVoiceLinkage() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getVoiceLinkage();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setVoiceLinkage(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_VOICE_LINKAGE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setVoiceLinkage(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getMusicLinkage() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getMusicLinkage();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setMusicLinkage(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_MUSIC_LINKAGE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setMusicLinkage(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getSpeedAsstSlifWarning() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getSpeedAsstSlifWarning();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setSpeedAsstSlifWarning(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_SPEED_ASST_SLIF_WARNING);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setSpeedAsstSlifWarning(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getSpeedAsstMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getSpeedAsstMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setSpeedAsstMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_SPEED_ASST_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setSpeedAsstMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAccTjaMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAccTjaMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAccTjaMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_ACC_TJA_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAccTjaMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLaneKeepingAsstMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLaneKeepingAsstMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLaneKeepingAsstMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_LANE_KEEPING_ASST_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLaneKeepingAsstMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLaneKeepingAsstSen() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLaneKeepingAsstSen();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLaneKeepingAsstSen(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_LANE_KEEPING_ASST_SEN);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLaneKeepingAsstSen(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLaneKeepingWarningSound() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLaneKeepingWarningSound();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLaneKeepingWarningSound(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_LANE_KEEPING_WARNING_SOUND);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLaneKeepingWarningSound(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLaneKeepingVibration() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLaneKeepingVibration();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLaneKeepingVibration(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_LANE_KEEPING_VIBRATION);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLaneKeepingVibration(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLkaSysFailure() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLkaSysFailure();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getLdwSysFailure() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLdwSysFailure();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getTrafficJamAsstOn() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getTrafficJamAsstOn();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setTrafficJamAsstOn(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_TRAFFIC_JAM_ASST_ON);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setTrafficJamAsstOn(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getFcwAlarmMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getFcwAlarmMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setFcwAlarmMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_FCW_ALARM_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setFcwAlarmMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getFcwAutoBrakeMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getFcwAutoBrakeMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setFcwAutoBrakeMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_FCW_AUTO_BRAKE_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setFcwAutoBrakeMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getFcwSensitivity() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getFcwSensitivity();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setFcwSensitivity(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_FCW_SENSITIVITY);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setFcwSensitivity(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getFcwSysFailure() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getFcwSysFailure();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getAutoEmergencyBraking() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAutoEmergencyBraking();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAutoEmergencyBraking(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AUTO_EMERGENCY_BRAKING);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAutoEmergencyBraking(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getRearDriveAsstSys() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getRearDriveAsstSys();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setRearDriveAsstSys(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_REAR_DRIVE_ASST_SYS);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setRearDriveAsstSys(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getBlindSpotDetection() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getBlindSpotDetection();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setBlindSpotDetection(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_BLIND_SPOT_DETECTION);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setBlindSpotDetection(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLaneChangeAsst() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLaneChangeAsst();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLaneChangeAsst(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_LANE_CHANGE_ASST);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLaneChangeAsst(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getRearCrossTrafficSys() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getRearCrossTrafficSys();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setRearCrossTrafficSys(int signalValue) {
        sendMessage(2048);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setRearCrossTrafficSys(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getRearCollisionWarning() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getRearCollisionWarning();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setRearCollisionWarning(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_REAR_COLLISION_WARNING);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setRearCollisionWarning(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getRctbSysFailure() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getRctbSysFailure();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getRdaSysStatus() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getRdaSysStatus();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getParkingWarning() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getParkingWarning();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setParkingWarning(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_PARKING_WARNING);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setParkingWarning(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getUnsteadyDrivingWarning() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getUnsteadyDrivingWarning();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setUnsteadyDrivingWarning(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_UNSTEADY_DRIVING_WARNING);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setUnsteadyDrivingWarning(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getUnsteadyDrivingWarningSen() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getUnsteadyDrivingWarningSen();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setUnsteadyDrivingWarningSen(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_UNSTEADY_DRIVING_WARNING_SEN);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setUnsteadyDrivingWarningSen(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getUdwSysFailure() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getUdwSysFailure();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getDriverMonitorSys() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getDriverMonitorSys();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDriverMonitorSys(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_DRIVER_MONITOR_SYS);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setDriverMonitorSys(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getDriverMonitorSysWarningSound() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getDriverMonitorSysWarningSound();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDriverMonitorSysWarningSound(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_DRIVER_MONITOR_SYS_WARNING_SOUND);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setDriverMonitorSysWarningSound(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getDriverMonitorSysVibration() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getDriverMonitorSysVibration();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDriverMonitorSysVibration(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_DRIVER_MONITOR_SYS_VIBRATION);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setDriverMonitorSysVibration(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getDriveMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getDriveMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setDriveMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_DRIVE_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setDriveMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getElectricPowertrainLevel() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getElectricPowertrainLevel();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setElectricPowertrainLevel(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_ELECTRIC_POWERTRAIN_LEVEL);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setElectricPowertrainLevel(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getSteeringLevel() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getSteeringLevel();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setSteeringLevel(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_STEERING_LEVEL);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setSteeringLevel(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getBrakePedalLevel() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getBrakePedalLevel();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setBrakePedalLevel(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_BRAKE_PEDAL_LEVEL);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setBrakePedalLevel(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getRegenerativeBrakeSwitch() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getRegenerativeBrakeSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setRegenerativeBrakeSwitch(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_REGENERATIVE_BRAKE_SWITCH);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setRegenerativeBrakeSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getPsgSafetyAirbagOn() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getPsgSafetyAirbagOn();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getPsgSafetyAirbagStatus() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getPsgSafetyAirbagStatus();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setPsgSafetyAirbagOn(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_PSG_AIRBAG_SWITCH);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setPsgSafetyAirbagOn(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public VehicleSettingBean getVehicleSettingStatus() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getVehicleSettingStatus();
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        return new VehicleSettingBean();
    }

    public int getSdmAvailableStatus() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getSdmAvailableStatus();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getLastWelcomeLightTime() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLastWelcomeLightTime();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getLastHomeLightTime() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLastHomeLightTime();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getLastAmbtLightOpenMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLastAmbtLightOpenMode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getLastLaneKeepingAsstMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLastLaneKeepingAsstMode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getRdaAvlbly() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getRdaAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getWelcomeSoundAvlbly() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getWelcomeSoundAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getBusStatus() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getBusOffStatus();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getAmbtLightAvlbly() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAmbtLightAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLampHomeOn(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_LAMP_HOME_ON);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLampHomeOn(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLampHomeOn() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLampHomeOn();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLampWelcomeOn(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_LAMP_WELCOME_ON);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLampWelcomeOn(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLampWelcomeOn() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLampWelcomeOn();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setCarSearchFeedback(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_CAR_SEARCH_FEEDBACK);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setCarSearchFeedback(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getCarSearchFeedback() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getCarSearchFeedback();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setCarSearchFeedbackSwitch(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_CAR_SEARCH_FEEDBACK_SWITCH);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setCarSearchFeedbackSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getCarSearchFeedbackSwitch() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getCarSearchFeedbackSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLightRearFogSwitch(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_MASTER_LIGHT_REAR_FOG_SWITCH);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLightRearFogSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLightRearFogSwitch() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLightRearFogSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setFarewellLightMode(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_FAREWELL_LIGHT_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setFarewellLightMode(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getFarewellLightMode() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getFarewellLightMode();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getRegenerativeLevel() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getRegenerativeLevel();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setRegenerativeLevel(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_REGENERATIVE_LEVEL);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setRegenerativeLevel(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getRegenerativeLevelDisable() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getRegenerativeLevelDisable();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getSignalPedal() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getSignalPedal();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setSignalPedal(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_SIGNAL_PEDAL_ON);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setSignalPedal(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getSignalPedalLnhbReg() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getSignalPedalLnhbReg();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getLongerEndurance() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLongerEndurance();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setLongerEndurance(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_LONGER_ENDURANCE_MODE);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLongerEndurance(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLongerEnduranceRecommend() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLongerEnduranceRecommend();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getLongerEnduranceDisable() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLongerEnduranceDisable();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getBrakeToStandstill() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getBrakeToStandstill();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setBrakeToStandstill(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_BRAKE_TO_STANDSTILL);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setBrakeToStandstill(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getPowerModeSwitch() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getPowerModeSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setPowerModeSwitch(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_BRAKE_TO_STANDSTILL);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setPowerModeSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getAutoHoldSwitch() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getAutoHoldSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setAutoHoldSwitch(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_AUTO_HOLD_SWITCH);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setAutoHoldSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getTowingModeSwitch() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getTowingModeSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setTowingModeSwitch(int signalValue) {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setTowingModeSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getTdmAvlbly() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getTdmAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getTrlrCnctSts() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getTrlrCnctSts();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setTrlrMdSts(int signalValue) {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setTrlrMdSts(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getPdcChangeSts() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getPdcChangeSts();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void setPdcChangeSts(int signalValue) {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setPdcChangeSts(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public void setLightFrontFogSwitch(int signalValue) {
        sendMessage(VehicleSettingConst.SIGNAL_MASTER_LIGHT_FRONT_FOG_SWITCH);
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                iVehicleSettingService.setLightFrontFogSwitch(signalValue);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public int getLightFrontFogSwitch() {
        IVehicleSettingService iVehicleSettingService = this.mVehicleSettingService;
        if (iVehicleSettingService != null) {
            try {
                return iVehicleSettingService.getLightFrontFogSwitch();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    private final class RecoveryHandler extends Handler {
        public RecoveryHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            LogUtil.logD(VehicleSettingManager.TAG, "handleMessage: what = " + msg.what);
            if (VehicleSettingManager.this.mCallbackList != null && VehicleSettingManager.this.mCallbackList.size() != 0) {
                synchronized (VehicleSettingManager.this.mCallbackList) {
                    VehicleSettingBean vehicleSettingBean = VehicleSettingManager.this.getVehicleSettingStatus();
                    vehicleSettingBean.setMarkedSignalId(msg.what);
                    for (VehicleServiceContract.IVehicleSettingCallback callback : VehicleSettingManager.this.mCallbackList) {
                        callback.onVehicleSettingChangeEvent(vehicleSettingBean);
                    }
                }
            }
        }
    }
}
