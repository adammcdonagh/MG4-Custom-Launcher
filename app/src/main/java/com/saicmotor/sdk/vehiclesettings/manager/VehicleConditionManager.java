package com.saicmotor.sdk.vehiclesettings.manager;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.IHubService;
import com.saicmotor.sdk.vehiclesettings.IVehicleConditionListener;
import com.saicmotor.sdk.vehiclesettings.IVehicleConditionService;
import com.saicmotor.sdk.vehiclesettings.LogUtil;
import com.saicmotor.sdk.vehiclesettings.VehicleServiceContract;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleConditionBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class VehicleConditionManager extends BaseManager {
    private static final String VEHICLE_CONDITION = "vehiclecondition";
    private static VehicleServiceContract.IVehicleServiceListener sServiceListener;
    private static VehicleConditionManager sVehicleConditionManager;
    private List<VehicleServiceContract.IVehicleConditionCallback> mCallbackList;
    private IVehicleConditionListener mIVehicleConditionListener;
    private IVehicleConditionService mIVehicleConditionService;
    private static final String TAG = VehicleConditionManager.class.getSimpleName();
    private static boolean sIsServiceConnected = false;

    protected VehicleConditionManager(Context context) {
        super(context);
        this.mCallbackList = new ArrayList();
        this.mIVehicleConditionListener = new IVehicleConditionListener.Stub() { // from class: com.saicmotor.sdk.vehiclesettings.manager.VehicleConditionManager.1
            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionListener
            public void onChangeEvent(VehicleConditionBean vehicleStatusBean) throws RemoteException {
                if (VehicleConditionManager.this.mCallbackList != null) {
                    synchronized (VehicleConditionManager.this.mCallbackList) {
                        for (VehicleServiceContract.IVehicleConditionCallback callback : VehicleConditionManager.this.mCallbackList) {
                            callback.onVehicleConditionChangeEvent(vehicleStatusBean);
                        }
                    }
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionListener
            public void onErrorEvent(String signalName, int code) throws RemoteException {
                if (VehicleConditionManager.this.mCallbackList != null) {
                    synchronized (VehicleConditionManager.this.mCallbackList) {
                        for (VehicleServiceContract.IVehicleConditionCallback callback : VehicleConditionManager.this.mCallbackList) {
                            callback.onVehicleConditionErrorEvent(signalName, code);
                        }
                    }
                }
            }
        };
        bindService();
    }

    public static synchronized void init(Context context, VehicleServiceContract.IVehicleServiceListener listener) {
        sServiceListener = listener;
        LogUtil.logD(TAG, "init: VehicleConditionManager=" + sVehicleConditionManager + ", sIsServiceConnected=" + sIsServiceConnected);
        if (sVehicleConditionManager == null) {
            sVehicleConditionManager = new VehicleConditionManager(context);
        }
        if (sIsServiceConnected) {
            listener.onServiceConnected(sVehicleConditionManager);
        }
    }

    public synchronized void release() {
        if (sIsServiceConnected) {
            unbindService();
            sIsServiceConnected = false;
        }
        sVehicleConditionManager = null;
    }

    @Override // com.saicmotor.sdk.vehiclesettings.manager.BaseManager
    protected void setBinder(IBinder binder) {
        LogUtil.logD(TAG, " sIsServiceConnected setBinder");
        sIsServiceConnected = true;
        IHubService hubService = IHubService.Stub.asInterface(binder);
        try {
            this.mIVehicleConditionService = IVehicleConditionService.Stub.asInterface(hubService.getService(VEHICLE_CONDITION));
        } catch (RemoteException except) {
            except.printStackTrace();
        }
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                iVehicleConditionService.addVehicleConditionListener(this.mIVehicleConditionListener);
            } catch (RemoteException except2) {
                except2.printStackTrace();
            }
        }
        sServiceListener.onServiceConnected(sVehicleConditionManager);
    }

    @Override // com.saicmotor.sdk.vehiclesettings.manager.BaseManager
    protected void unbind() {
        sIsServiceConnected = false;
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                iVehicleConditionService.removeVehicleConditionListener(this.mIVehicleConditionListener);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        sServiceListener.onServiceDisconnected();
    }

    public void registerVehicleConditionCallback(VehicleServiceContract.IVehicleConditionCallback resultCallback) {
        List<VehicleServiceContract.IVehicleConditionCallback> list = this.mCallbackList;
        if (list != null && !list.contains(resultCallback)) {
            synchronized (this.mCallbackList) {
                this.mCallbackList.add(resultCallback);
            }
        }
    }

    public void unregisterVehicleConditionCallback(VehicleServiceContract.IVehicleConditionCallback resultCallback) {
        List<VehicleServiceContract.IVehicleConditionCallback> list = this.mCallbackList;
        if (list != null && list.contains(resultCallback)) {
            synchronized (this.mCallbackList) {
                this.mCallbackList.remove(resultCallback);
            }
        }
    }

    public float getCarSpeed() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getCarSpeed();
            } catch (RemoteException exception) {
                exception.printStackTrace();
                return -1.0f;
            }
        }
        return -1.0f;
    }

    public int getVehicleIgnition() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getVehicleIgnition();
            } catch (RemoteException exception) {
                exception.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getEngineState() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getEngineState();
            } catch (RemoteException exception) {
                exception.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getCarGear() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getCarGear();
            } catch (RemoteException exception) {
                exception.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public VehicleConditionBean getVehicleCondition() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getVehicleCondition();
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        return new VehicleConditionBean();
    }

    public int getVehicleType() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getVehicleType();
            } catch (RemoteException except) {
                except.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getEcallState() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getEcallState();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getCrashSignal() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getCrashSignal();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getConfig360() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getConfig360();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getAmplifierType() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getAmplifierType();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getAirFollowEconConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getAirFollowEconConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getRearWindowAutoConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getRearWindowAutoConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getTailgateControlConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getTailgateControlConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getLightSettings() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getLightingSettings();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getEleBackMirrorConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getEleBackMirrorConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getSpeedAssistConfig() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getSpeedAssistConfig();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getDriverSeatConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getDriverSeatConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getBlindSpotDetection() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getBlindSpotDetection();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getSeatHeatingConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getSeatHeatingConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getEp21ConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getEp21CarConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getTrafficJamAssistance() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getTrafficJamAssistance();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getPedestrianWarningConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getPedestrianWarningConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getForwardCollisionAssistance() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getForwardCollisionAssistance();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getWindowControl() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getWindowControl();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getSteepDescentControl() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getSteepDescentControl();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getFrontRadarControl() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getFrontRadarControl();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getAutomaticCall() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getAutomaticCall();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getAqsConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getAqsConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getLdwsConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getLdwsConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getDmsConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getDmsConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getAirbagConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getAirbagConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getPmDetectionConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getPmDetectionConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getAirFilterConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getAirFilterConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getAnionPurifyConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getAnionPurifyConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getFrontSeatConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getFrontSeatConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getSunRoofControlConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getSunRoofControlConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getDoorAutoLockConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getDoorAutoLockConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getAirConditionConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getAirConditionConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getOnePedalConfigCode() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getOnePedalConfigCode();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getTransferCaseConfigCode() throws RemoteException {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            return iVehicleConditionService.getTransferCaseConfigCode();
        }
        return -1;
    }

    public int getEnergyPredictionConfigCode() throws RemoteException {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            return iVehicleConditionService.getEnergyPredictionConfigCode();
        }
        return -1;
    }

    public int getTailerElecticConfigCode() throws RemoteException {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            return iVehicleConditionService.getTailerElecticConfigCode();
        }
        return -1;
    }

    public int getLightFrontFogConfigCode() throws RemoteException {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            return iVehicleConditionService.getLightFrontFogConfigCode();
        }
        return -1;
    }

    public int getTransmissionConfigCode() throws RemoteException {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            return iVehicleConditionService.getTransmissionConfigCode();
        }
        return -1;
    }

    public int getBatteryConfigCode() throws RemoteException {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            return iVehicleConditionService.getBatteryConfigCode();
        }
        return -1;
    }

    public int getSpeakerConfigCode() throws RemoteException {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            return iVehicleConditionService.getSpeakerConfigCode();
        }
        return -1;
    }

    public int getAcAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getAcAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getBcmAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getBcmAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getScsAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getScsAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getApaAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getApaAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getPepsAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getPepsAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getFvcmAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getFvcmAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getPlcmAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getPlcmAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getBmsAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getBmsAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getHcuAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getHcuAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getRadarAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getRadarAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getMsmAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getMsmAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getVcuAvlbly() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getVcuAvlbly();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getCarGearV() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getCarGearV();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getVehicleNameInfo() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getVehicleNameInfo();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public String getVinNumber() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getVinNumber();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public int getVehicleExteriorColor() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getVehicleExteriorColor();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getMileageUnit() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getMileageUnit();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getNextResetMileage() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getNextResetMileage();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getNextResetDay() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getNextResetDay();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int getMaintenanceStatus() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getMaintenanceStatus();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void resetCarMileageInfo() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                iVehicleConditionService.resetCarMileageInfo();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public long getResetTime() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getResetTime();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return -1L;
            }
        }
        return -1L;
    }

    public boolean getEngModeStackStatus() {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            try {
                return iVehicleConditionService.getEngModeStackStatus();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public int getPeripheralKeyModule() throws RemoteException {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            return iVehicleConditionService.getPeripheralKeyModule();
        }
        return -1;
    }

    public int getSwcFunctionChangeSwa() throws RemoteException {
        IVehicleConditionService iVehicleConditionService = this.mIVehicleConditionService;
        if (iVehicleConditionService != null) {
            return iVehicleConditionService.getSwcFunctionChangeSwa();
        }
        return -1;
    }
}
