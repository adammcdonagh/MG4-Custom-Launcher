package com.saicmotor.sdk.vehiclesettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.IAirConditionListener;
import com.saicmotor.sdk.vehiclesettings.bean.AirConditionBean;

/* loaded from: classes2.dex */
public interface IAirConditionService extends IInterface {
    void addAirConditionListener(IAirConditionListener iAirConditionListener) throws RemoteException;

    void closeBackWindowDefroster() throws RemoteException;

    void closeDrvSeatHeat() throws RemoteException;

    void closeFrontWindowDefroster() throws RemoteException;

    void closeHvacPower() throws RemoteException;

    void closePsgSeatHeat() throws RemoteException;

    int getAcSwitch() throws RemoteException;

    AirConditionBean getAirConditionStatus() throws RemoteException;

    int getAirVolumeLevel() throws RemoteException;

    int getAnionStatus() throws RemoteException;

    int getAutoStatus() throws RemoteException;

    int getBackWindowDefroster() throws RemoteException;

    int getBlowerDirectionMode() throws RemoteException;

    float getDrvLeftMiddleWindOutletAngle() throws RemoteException;

    float getDrvLeftWindOutletAngle() throws RemoteException;

    int getDrvSeatHeatLevel() throws RemoteException;

    int getDrvSeatWindLevel() throws RemoteException;

    int getDrvTemp() throws RemoteException;

    int getDrvWindOutletAvoidPersonModeStatus() throws RemoteException;

    int getDrvWindOutletBlowPersonModeStatus() throws RemoteException;

    int getDrvWindOutletOffModeStatus() throws RemoteException;

    int getDrvWindOutletSwingModeStatus() throws RemoteException;

    int getEconStatus() throws RemoteException;

    int getFrontWindowDefroster() throws RemoteException;

    int getHvacPowerStatus() throws RemoteException;

    int getLoopMode() throws RemoteException;

    int getMaxAirVolume() throws RemoteException;

    int getMaxTemp() throws RemoteException;

    int getMinAirVolume() throws RemoteException;

    int getMinTemp() throws RemoteException;

    float getOutCarTemp() throws RemoteException;

    int getPm25Concentration() throws RemoteException;

    int getPm25Filter() throws RemoteException;

    float getPsgRightMiddleWindOutletAngle() throws RemoteException;

    float getPsgRightWindOutletAngle() throws RemoteException;

    int getPsgSeatHeatLevel() throws RemoteException;

    int getPsgSeatWindLevel() throws RemoteException;

    int getPsgTemp() throws RemoteException;

    int getPsgWindOutletAvoidPersonModeStatus() throws RemoteException;

    int getPsgWindOutletBlowPersonModeStatus() throws RemoteException;

    int getPsgWindOutletOffModeStatus() throws RemoteException;

    int getPsgWindOutletSwingModeStatus() throws RemoteException;

    int getSmartBlowerStatus() throws RemoteException;

    int getSteeringWheelHeatLevel() throws RemoteException;

    int getTempDualZoneOn() throws RemoteException;

    int getWindOutletCanStatus() throws RemoteException;

    void increaseAirVolumeLevel(int i) throws RemoteException;

    void increaseTemp(int i, boolean z) throws RemoteException;

    boolean isCurrentAirVolumeMax() throws RemoteException;

    boolean isCurrentAirVolumeMin() throws RemoteException;

    boolean isCurrentTempMax(boolean z) throws RemoteException;

    boolean isCurrentTempMin(boolean z) throws RemoteException;

    void openBackWindowDefroster() throws RemoteException;

    void openDrvSeatHeat() throws RemoteException;

    void openFrontWindowDefroster() throws RemoteException;

    void openHvacPower() throws RemoteException;

    void openLoopAuto() throws RemoteException;

    void openLoopInner() throws RemoteException;

    void openLoopOutside() throws RemoteException;

    void openPsgSeatHeat() throws RemoteException;

    void reduceAirVolumeLevel(int i) throws RemoteException;

    void reduceTemp(int i, boolean z) throws RemoteException;

    void removeAirConditionListener(IAirConditionListener iAirConditionListener) throws RemoteException;

    void setAcStatus(int i) throws RemoteException;

    void setAirVolumeLevel(int i) throws RemoteException;

    void setAirVolumeMax() throws RemoteException;

    void setAirVolumeMin() throws RemoteException;

    void setAutoStatus(int i) throws RemoteException;

    void setBlowerDirectionMode(int i) throws RemoteException;

    void setDrvLeftMiddleWindOutletAngle(float f) throws RemoteException;

    void setDrvLeftWindOutletAngle(float f) throws RemoteException;

    void setDrvSeatHeatLevel(int i) throws RemoteException;

    void setDrvSeatWindLevel(int i) throws RemoteException;

    void setDrvTemp(int i) throws RemoteException;

    void setDrvWindOutletAvoidPersonModeStatus(int i) throws RemoteException;

    void setDrvWindOutletBlowPersonModeStatus(int i) throws RemoteException;

    void setDrvWindOutletOffModeStatus(int i) throws RemoteException;

    void setDrvWindOutletSwingModeStatus(int i) throws RemoteException;

    void setEconStatus(int i) throws RemoteException;

    void setHvacPowerStatus(int i) throws RemoteException;

    void setLoopMode(int i) throws RemoteException;

    void setPsgRightMiddleWindOutletAngle(float f) throws RemoteException;

    void setPsgRightWindOutletAngle(float f) throws RemoteException;

    void setPsgSeatHeatLevel(int i) throws RemoteException;

    void setPsgSeatWindLevel(int i) throws RemoteException;

    void setPsgTemp(int i) throws RemoteException;

    void setPsgWindOutletAvoidPersonModeStatus(int i) throws RemoteException;

    void setPsgWindOutletBlowPersonModeStatus(int i) throws RemoteException;

    void setPsgWindOutletOffModeStatus(int i) throws RemoteException;

    void setPsgWindOutletSwingModeStatus(int i) throws RemoteException;

    void setSteeringWheelHeat(int i) throws RemoteException;

    void setTempDualZoneOn(int i) throws RemoteException;

    void setTempMax(boolean z) throws RemoteException;

    void setTempMin(boolean z) throws RemoteException;

    void setWindOutletSmartStatus(int i) throws RemoteException;

    public static class Default implements IAirConditionService {
        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void addAirConditionListener(IAirConditionListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void removeAirConditionListener(IAirConditionListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public AirConditionBean getAirConditionStatus() throws RemoteException {
            return null;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setHvacPowerStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void openHvacPower() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void closeHvacPower() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setAutoStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setAcStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setLoopMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void openLoopInner() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void openLoopOutside() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void openLoopAuto() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setEconStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setBlowerDirectionMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setAirVolumeLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void increaseAirVolumeLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void reduceAirVolumeLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setAirVolumeMax() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setAirVolumeMin() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setDrvWindOutletBlowPersonModeStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setDrvWindOutletAvoidPersonModeStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setDrvWindOutletSwingModeStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setDrvWindOutletOffModeStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setDrvLeftWindOutletAngle(float signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setDrvLeftMiddleWindOutletAngle(float signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setPsgWindOutletBlowPersonModeStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setPsgWindOutletAvoidPersonModeStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setPsgWindOutletSwingModeStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setPsgWindOutletOffModeStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setPsgRightWindOutletAngle(float signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setPsgRightMiddleWindOutletAngle(float signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setWindOutletSmartStatus(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setDrvSeatHeatLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void openDrvSeatHeat() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void closeDrvSeatHeat() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setDrvSeatWindLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setPsgSeatHeatLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void openPsgSeatHeat() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void closePsgSeatHeat() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setPsgSeatWindLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setDrvTemp(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setPsgTemp(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void increaseTemp(int signalValue, boolean isDrv) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void reduceTemp(int signalValue, boolean isDrv) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setTempMax(boolean isDrv) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setTempMin(boolean isDrv) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setTempDualZoneOn(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void openFrontWindowDefroster() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void closeFrontWindowDefroster() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void openBackWindowDefroster() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void closeBackWindowDefroster() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public void setSteeringWheelHeat(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getHvacPowerStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getAutoStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getAcSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getLoopMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getEconStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getBlowerDirectionMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getAirVolumeLevel() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getMaxAirVolume() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getMinAirVolume() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public boolean isCurrentAirVolumeMin() throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public boolean isCurrentAirVolumeMax() throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getDrvWindOutletBlowPersonModeStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getDrvWindOutletAvoidPersonModeStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getDrvWindOutletSwingModeStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getDrvWindOutletOffModeStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public float getDrvLeftWindOutletAngle() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public float getDrvLeftMiddleWindOutletAngle() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getPsgWindOutletBlowPersonModeStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getPsgWindOutletAvoidPersonModeStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getPsgWindOutletSwingModeStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getPsgWindOutletOffModeStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public float getPsgRightWindOutletAngle() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public float getPsgRightMiddleWindOutletAngle() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getSmartBlowerStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getDrvSeatHeatLevel() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getDrvSeatWindLevel() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getPsgSeatHeatLevel() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getPsgSeatWindLevel() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getDrvTemp() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getPsgTemp() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getMaxTemp() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getMinTemp() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public boolean isCurrentTempMin(boolean isDrv) throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public boolean isCurrentTempMax(boolean isDrv) throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getTempDualZoneOn() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public float getOutCarTemp() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getPm25Concentration() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getPm25Filter() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getAnionStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getFrontWindowDefroster() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getBackWindowDefroster() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getSteeringWheelHeatLevel() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
        public int getWindOutletCanStatus() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAirConditionService {
        private static final String DESCRIPTOR = "com.saicmotor.sdk.vehiclesettings.IAirConditionService";
        static final int TRANSACTION_addAirConditionListener = 1;
        static final int TRANSACTION_closeBackWindowDefroster = 51;
        static final int TRANSACTION_closeDrvSeatHeat = 35;
        static final int TRANSACTION_closeFrontWindowDefroster = 49;
        static final int TRANSACTION_closeHvacPower = 6;
        static final int TRANSACTION_closePsgSeatHeat = 39;
        static final int TRANSACTION_getAcSwitch = 55;
        static final int TRANSACTION_getAirConditionStatus = 3;
        static final int TRANSACTION_getAirVolumeLevel = 59;
        static final int TRANSACTION_getAnionStatus = 91;
        static final int TRANSACTION_getAutoStatus = 54;
        static final int TRANSACTION_getBackWindowDefroster = 93;
        static final int TRANSACTION_getBlowerDirectionMode = 58;
        static final int TRANSACTION_getDrvLeftMiddleWindOutletAngle = 69;
        static final int TRANSACTION_getDrvLeftWindOutletAngle = 68;
        static final int TRANSACTION_getDrvSeatHeatLevel = 77;
        static final int TRANSACTION_getDrvSeatWindLevel = 78;
        static final int TRANSACTION_getDrvTemp = 81;
        static final int TRANSACTION_getDrvWindOutletAvoidPersonModeStatus = 65;
        static final int TRANSACTION_getDrvWindOutletBlowPersonModeStatus = 64;
        static final int TRANSACTION_getDrvWindOutletOffModeStatus = 67;
        static final int TRANSACTION_getDrvWindOutletSwingModeStatus = 66;
        static final int TRANSACTION_getEconStatus = 57;
        static final int TRANSACTION_getFrontWindowDefroster = 92;
        static final int TRANSACTION_getHvacPowerStatus = 53;
        static final int TRANSACTION_getLoopMode = 56;
        static final int TRANSACTION_getMaxAirVolume = 60;
        static final int TRANSACTION_getMaxTemp = 83;
        static final int TRANSACTION_getMinAirVolume = 61;
        static final int TRANSACTION_getMinTemp = 84;
        static final int TRANSACTION_getOutCarTemp = 88;
        static final int TRANSACTION_getPm25Concentration = 89;
        static final int TRANSACTION_getPm25Filter = 90;
        static final int TRANSACTION_getPsgRightMiddleWindOutletAngle = 75;
        static final int TRANSACTION_getPsgRightWindOutletAngle = 74;
        static final int TRANSACTION_getPsgSeatHeatLevel = 79;
        static final int TRANSACTION_getPsgSeatWindLevel = 80;
        static final int TRANSACTION_getPsgTemp = 82;
        static final int TRANSACTION_getPsgWindOutletAvoidPersonModeStatus = 71;
        static final int TRANSACTION_getPsgWindOutletBlowPersonModeStatus = 70;
        static final int TRANSACTION_getPsgWindOutletOffModeStatus = 73;
        static final int TRANSACTION_getPsgWindOutletSwingModeStatus = 72;
        static final int TRANSACTION_getSmartBlowerStatus = 76;
        static final int TRANSACTION_getSteeringWheelHeatLevel = 94;
        static final int TRANSACTION_getTempDualZoneOn = 87;
        static final int TRANSACTION_getWindOutletCanStatus = 95;
        static final int TRANSACTION_increaseAirVolumeLevel = 16;
        static final int TRANSACTION_increaseTemp = 43;
        static final int TRANSACTION_isCurrentAirVolumeMax = 63;
        static final int TRANSACTION_isCurrentAirVolumeMin = 62;
        static final int TRANSACTION_isCurrentTempMax = 86;
        static final int TRANSACTION_isCurrentTempMin = 85;
        static final int TRANSACTION_openBackWindowDefroster = 50;
        static final int TRANSACTION_openDrvSeatHeat = 34;
        static final int TRANSACTION_openFrontWindowDefroster = 48;
        static final int TRANSACTION_openHvacPower = 5;
        static final int TRANSACTION_openLoopAuto = 12;
        static final int TRANSACTION_openLoopInner = 10;
        static final int TRANSACTION_openLoopOutside = 11;
        static final int TRANSACTION_openPsgSeatHeat = 38;
        static final int TRANSACTION_reduceAirVolumeLevel = 17;
        static final int TRANSACTION_reduceTemp = 44;
        static final int TRANSACTION_removeAirConditionListener = 2;
        static final int TRANSACTION_setAcStatus = 8;
        static final int TRANSACTION_setAirVolumeLevel = 15;
        static final int TRANSACTION_setAirVolumeMax = 18;
        static final int TRANSACTION_setAirVolumeMin = 19;
        static final int TRANSACTION_setAutoStatus = 7;
        static final int TRANSACTION_setBlowerDirectionMode = 14;
        static final int TRANSACTION_setDrvLeftMiddleWindOutletAngle = 25;
        static final int TRANSACTION_setDrvLeftWindOutletAngle = 24;
        static final int TRANSACTION_setDrvSeatHeatLevel = 33;
        static final int TRANSACTION_setDrvSeatWindLevel = 36;
        static final int TRANSACTION_setDrvTemp = 41;
        static final int TRANSACTION_setDrvWindOutletAvoidPersonModeStatus = 21;
        static final int TRANSACTION_setDrvWindOutletBlowPersonModeStatus = 20;
        static final int TRANSACTION_setDrvWindOutletOffModeStatus = 23;
        static final int TRANSACTION_setDrvWindOutletSwingModeStatus = 22;
        static final int TRANSACTION_setEconStatus = 13;
        static final int TRANSACTION_setHvacPowerStatus = 4;
        static final int TRANSACTION_setLoopMode = 9;
        static final int TRANSACTION_setPsgRightMiddleWindOutletAngle = 31;
        static final int TRANSACTION_setPsgRightWindOutletAngle = 30;
        static final int TRANSACTION_setPsgSeatHeatLevel = 37;
        static final int TRANSACTION_setPsgSeatWindLevel = 40;
        static final int TRANSACTION_setPsgTemp = 42;
        static final int TRANSACTION_setPsgWindOutletAvoidPersonModeStatus = 27;
        static final int TRANSACTION_setPsgWindOutletBlowPersonModeStatus = 26;
        static final int TRANSACTION_setPsgWindOutletOffModeStatus = 29;
        static final int TRANSACTION_setPsgWindOutletSwingModeStatus = 28;
        static final int TRANSACTION_setSteeringWheelHeat = 52;
        static final int TRANSACTION_setTempDualZoneOn = 47;
        static final int TRANSACTION_setTempMax = 45;
        static final int TRANSACTION_setTempMin = 46;
        static final int TRANSACTION_setWindOutletSmartStatus = 32;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAirConditionService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAirConditionService)) {
                return (IAirConditionService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    addAirConditionListener(IAirConditionListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeAirConditionListener(IAirConditionListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    AirConditionBean airConditionStatus = getAirConditionStatus();
                    parcel2.writeNoException();
                    if (airConditionStatus != null) {
                        parcel2.writeInt(1);
                        airConditionStatus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setHvacPowerStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    openHvacPower();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeHvacPower();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAutoStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAcStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLoopMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    openLoopInner();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    openLoopOutside();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    openLoopAuto();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setEconStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBlowerDirectionMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAirVolumeLevel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    increaseAirVolumeLevel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    reduceAirVolumeLevel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAirVolumeMax();
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAirVolumeMin();
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDrvWindOutletBlowPersonModeStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDrvWindOutletAvoidPersonModeStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDrvWindOutletSwingModeStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDrvWindOutletOffModeStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDrvLeftWindOutletAngle(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDrvLeftMiddleWindOutletAngle(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsgWindOutletBlowPersonModeStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsgWindOutletAvoidPersonModeStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsgWindOutletSwingModeStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsgWindOutletOffModeStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsgRightWindOutletAngle(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsgRightMiddleWindOutletAngle(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWindOutletSmartStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDrvSeatHeatLevel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    openDrvSeatHeat();
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeDrvSeatHeat();
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDrvSeatWindLevel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsgSeatHeatLevel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    openPsgSeatHeat();
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    closePsgSeatHeat();
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsgSeatWindLevel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDrvTemp(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsgTemp(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    increaseTemp(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    reduceTemp(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTempMax(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTempMin(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTempDualZoneOn(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    openFrontWindowDefroster();
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeFrontWindowDefroster();
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    openBackWindowDefroster();
                    parcel2.writeNoException();
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeBackWindowDefroster();
                    parcel2.writeNoException();
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSteeringWheelHeat(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hvacPowerStatus = getHvacPowerStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(hvacPowerStatus);
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    int autoStatus = getAutoStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(autoStatus);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    int acSwitch = getAcSwitch();
                    parcel2.writeNoException();
                    parcel2.writeInt(acSwitch);
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    int loopMode = getLoopMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(loopMode);
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    int econStatus = getEconStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(econStatus);
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    int blowerDirectionMode = getBlowerDirectionMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(blowerDirectionMode);
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    int airVolumeLevel = getAirVolumeLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(airVolumeLevel);
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    int maxAirVolume = getMaxAirVolume();
                    parcel2.writeNoException();
                    parcel2.writeInt(maxAirVolume);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    int minAirVolume = getMinAirVolume();
                    parcel2.writeNoException();
                    parcel2.writeInt(minAirVolume);
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCurrentAirVolumeMin = isCurrentAirVolumeMin();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCurrentAirVolumeMin ? 1 : 0);
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCurrentAirVolumeMax = isCurrentAirVolumeMax();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCurrentAirVolumeMax ? 1 : 0);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    int drvWindOutletBlowPersonModeStatus = getDrvWindOutletBlowPersonModeStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(drvWindOutletBlowPersonModeStatus);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    int drvWindOutletAvoidPersonModeStatus = getDrvWindOutletAvoidPersonModeStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(drvWindOutletAvoidPersonModeStatus);
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    int drvWindOutletSwingModeStatus = getDrvWindOutletSwingModeStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(drvWindOutletSwingModeStatus);
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    int drvWindOutletOffModeStatus = getDrvWindOutletOffModeStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(drvWindOutletOffModeStatus);
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    float drvLeftWindOutletAngle = getDrvLeftWindOutletAngle();
                    parcel2.writeNoException();
                    parcel2.writeFloat(drvLeftWindOutletAngle);
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    float drvLeftMiddleWindOutletAngle = getDrvLeftMiddleWindOutletAngle();
                    parcel2.writeNoException();
                    parcel2.writeFloat(drvLeftMiddleWindOutletAngle);
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    int psgWindOutletBlowPersonModeStatus = getPsgWindOutletBlowPersonModeStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(psgWindOutletBlowPersonModeStatus);
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    int psgWindOutletAvoidPersonModeStatus = getPsgWindOutletAvoidPersonModeStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(psgWindOutletAvoidPersonModeStatus);
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    int psgWindOutletSwingModeStatus = getPsgWindOutletSwingModeStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(psgWindOutletSwingModeStatus);
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    int psgWindOutletOffModeStatus = getPsgWindOutletOffModeStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(psgWindOutletOffModeStatus);
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    float psgRightWindOutletAngle = getPsgRightWindOutletAngle();
                    parcel2.writeNoException();
                    parcel2.writeFloat(psgRightWindOutletAngle);
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    float psgRightMiddleWindOutletAngle = getPsgRightMiddleWindOutletAngle();
                    parcel2.writeNoException();
                    parcel2.writeFloat(psgRightMiddleWindOutletAngle);
                    return true;
                case 76:
                    parcel.enforceInterface(DESCRIPTOR);
                    int smartBlowerStatus = getSmartBlowerStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(smartBlowerStatus);
                    return true;
                case 77:
                    parcel.enforceInterface(DESCRIPTOR);
                    int drvSeatHeatLevel = getDrvSeatHeatLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(drvSeatHeatLevel);
                    return true;
                case 78:
                    parcel.enforceInterface(DESCRIPTOR);
                    int drvSeatWindLevel = getDrvSeatWindLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(drvSeatWindLevel);
                    return true;
                case 79:
                    parcel.enforceInterface(DESCRIPTOR);
                    int psgSeatHeatLevel = getPsgSeatHeatLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(psgSeatHeatLevel);
                    return true;
                case 80:
                    parcel.enforceInterface(DESCRIPTOR);
                    int psgSeatWindLevel = getPsgSeatWindLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(psgSeatWindLevel);
                    return true;
                case 81:
                    parcel.enforceInterface(DESCRIPTOR);
                    int drvTemp = getDrvTemp();
                    parcel2.writeNoException();
                    parcel2.writeInt(drvTemp);
                    return true;
                case 82:
                    parcel.enforceInterface(DESCRIPTOR);
                    int psgTemp = getPsgTemp();
                    parcel2.writeNoException();
                    parcel2.writeInt(psgTemp);
                    return true;
                case 83:
                    parcel.enforceInterface(DESCRIPTOR);
                    int maxTemp = getMaxTemp();
                    parcel2.writeNoException();
                    parcel2.writeInt(maxTemp);
                    return true;
                case 84:
                    parcel.enforceInterface(DESCRIPTOR);
                    int minTemp = getMinTemp();
                    parcel2.writeNoException();
                    parcel2.writeInt(minTemp);
                    return true;
                case 85:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCurrentTempMin = isCurrentTempMin(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCurrentTempMin ? 1 : 0);
                    return true;
                case 86:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCurrentTempMax = isCurrentTempMax(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCurrentTempMax ? 1 : 0);
                    return true;
                case 87:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tempDualZoneOn = getTempDualZoneOn();
                    parcel2.writeNoException();
                    parcel2.writeInt(tempDualZoneOn);
                    return true;
                case 88:
                    parcel.enforceInterface(DESCRIPTOR);
                    float outCarTemp = getOutCarTemp();
                    parcel2.writeNoException();
                    parcel2.writeFloat(outCarTemp);
                    return true;
                case 89:
                    parcel.enforceInterface(DESCRIPTOR);
                    int pm25Concentration = getPm25Concentration();
                    parcel2.writeNoException();
                    parcel2.writeInt(pm25Concentration);
                    return true;
                case 90:
                    parcel.enforceInterface(DESCRIPTOR);
                    int pm25Filter = getPm25Filter();
                    parcel2.writeNoException();
                    parcel2.writeInt(pm25Filter);
                    return true;
                case 91:
                    parcel.enforceInterface(DESCRIPTOR);
                    int anionStatus = getAnionStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(anionStatus);
                    return true;
                case 92:
                    parcel.enforceInterface(DESCRIPTOR);
                    int frontWindowDefroster = getFrontWindowDefroster();
                    parcel2.writeNoException();
                    parcel2.writeInt(frontWindowDefroster);
                    return true;
                case 93:
                    parcel.enforceInterface(DESCRIPTOR);
                    int backWindowDefroster = getBackWindowDefroster();
                    parcel2.writeNoException();
                    parcel2.writeInt(backWindowDefroster);
                    return true;
                case 94:
                    parcel.enforceInterface(DESCRIPTOR);
                    int steeringWheelHeatLevel = getSteeringWheelHeatLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(steeringWheelHeatLevel);
                    return true;
                case 95:
                    parcel.enforceInterface(DESCRIPTOR);
                    int windOutletCanStatus = getWindOutletCanStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(windOutletCanStatus);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAirConditionService {
            public static IAirConditionService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void addAirConditionListener(IAirConditionListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addAirConditionListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void removeAirConditionListener(IAirConditionListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeAirConditionListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public AirConditionBean getAirConditionStatus() throws RemoteException {
                AirConditionBean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAirConditionStatus();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AirConditionBean.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setHvacPowerStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHvacPowerStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void openHvacPower() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openHvacPower();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void closeHvacPower() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeHvacPower();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setAutoStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAutoStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setAcStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAcStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setLoopMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLoopMode(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void openLoopInner() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openLoopInner();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void openLoopOutside() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openLoopOutside();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void openLoopAuto() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openLoopAuto();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setEconStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setEconStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setBlowerDirectionMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBlowerDirectionMode(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setAirVolumeLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAirVolumeLevel(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void increaseAirVolumeLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().increaseAirVolumeLevel(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void reduceAirVolumeLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reduceAirVolumeLevel(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setAirVolumeMax() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAirVolumeMax();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setAirVolumeMin() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAirVolumeMin();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setDrvWindOutletBlowPersonModeStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrvWindOutletBlowPersonModeStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setDrvWindOutletAvoidPersonModeStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrvWindOutletAvoidPersonModeStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setDrvWindOutletSwingModeStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrvWindOutletSwingModeStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setDrvWindOutletOffModeStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrvWindOutletOffModeStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setDrvLeftWindOutletAngle(float signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(signalValue);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrvLeftWindOutletAngle(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setDrvLeftMiddleWindOutletAngle(float signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(signalValue);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrvLeftMiddleWindOutletAngle(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setPsgWindOutletBlowPersonModeStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsgWindOutletBlowPersonModeStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setPsgWindOutletAvoidPersonModeStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsgWindOutletAvoidPersonModeStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setPsgWindOutletSwingModeStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsgWindOutletSwingModeStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setPsgWindOutletOffModeStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsgWindOutletOffModeStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setPsgRightWindOutletAngle(float signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(signalValue);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsgRightWindOutletAngle(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setPsgRightMiddleWindOutletAngle(float signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(signalValue);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsgRightMiddleWindOutletAngle(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setWindOutletSmartStatus(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWindOutletSmartStatus(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setDrvSeatHeatLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrvSeatHeatLevel(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void openDrvSeatHeat() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openDrvSeatHeat();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void closeDrvSeatHeat() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeDrvSeatHeat();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setDrvSeatWindLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrvSeatWindLevel(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setPsgSeatHeatLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsgSeatHeatLevel(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void openPsgSeatHeat() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openPsgSeatHeat();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void closePsgSeatHeat() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closePsgSeatHeat();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setPsgSeatWindLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsgSeatWindLevel(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setDrvTemp(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrvTemp(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setPsgTemp(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsgTemp(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void increaseTemp(int signalValue, boolean isDrv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    _data.writeInt(isDrv ? 1 : 0);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().increaseTemp(signalValue, isDrv);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void reduceTemp(int signalValue, boolean isDrv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    _data.writeInt(isDrv ? 1 : 0);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reduceTemp(signalValue, isDrv);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setTempMax(boolean isDrv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isDrv ? 1 : 0);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTempMax(isDrv);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setTempMin(boolean isDrv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isDrv ? 1 : 0);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTempMin(isDrv);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setTempDualZoneOn(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTempDualZoneOn(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void openFrontWindowDefroster() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openFrontWindowDefroster();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void closeFrontWindowDefroster() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeFrontWindowDefroster();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void openBackWindowDefroster() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openBackWindowDefroster();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void closeBackWindowDefroster() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeBackWindowDefroster();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public void setSteeringWheelHeat(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSteeringWheelHeat(signalValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getHvacPowerStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHvacPowerStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getAutoStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getAcSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAcSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getLoopMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLoopMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getEconStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEconStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getBlowerDirectionMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(58, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBlowerDirectionMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getAirVolumeLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAirVolumeLevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getMaxAirVolume() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(60, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMaxAirVolume();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getMinAirVolume() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMinAirVolume();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public boolean isCurrentAirVolumeMin() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(62, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCurrentAirVolumeMin();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public boolean isCurrentAirVolumeMax() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(63, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCurrentAirVolumeMax();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getDrvWindOutletBlowPersonModeStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrvWindOutletBlowPersonModeStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getDrvWindOutletAvoidPersonModeStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(65, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrvWindOutletAvoidPersonModeStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getDrvWindOutletSwingModeStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrvWindOutletSwingModeStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getDrvWindOutletOffModeStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrvWindOutletOffModeStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public float getDrvLeftWindOutletAngle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrvLeftWindOutletAngle();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public float getDrvLeftMiddleWindOutletAngle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrvLeftMiddleWindOutletAngle();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getPsgWindOutletBlowPersonModeStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(70, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgWindOutletBlowPersonModeStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getPsgWindOutletAvoidPersonModeStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgWindOutletAvoidPersonModeStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getPsgWindOutletSwingModeStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgWindOutletSwingModeStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getPsgWindOutletOffModeStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgWindOutletOffModeStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public float getPsgRightWindOutletAngle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(74, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgRightWindOutletAngle();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public float getPsgRightMiddleWindOutletAngle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(75, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgRightMiddleWindOutletAngle();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getSmartBlowerStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(76, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSmartBlowerStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getDrvSeatHeatLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrvSeatHeatLevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getDrvSeatWindLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(78, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrvSeatWindLevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getPsgSeatHeatLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgSeatHeatLevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getPsgSeatWindLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(80, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgSeatWindLevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getDrvTemp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(81, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrvTemp();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getPsgTemp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(82, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgTemp();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getMaxTemp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(83, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMaxTemp();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getMinTemp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(84, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMinTemp();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public boolean isCurrentTempMin(boolean isDrv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isDrv ? 1 : 0);
                    boolean _status = this.mRemote.transact(85, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCurrentTempMin(isDrv);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public boolean isCurrentTempMax(boolean isDrv) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isDrv ? 1 : 0);
                    boolean _status = this.mRemote.transact(86, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCurrentTempMax(isDrv);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getTempDualZoneOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(87, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTempDualZoneOn();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public float getOutCarTemp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(88, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOutCarTemp();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getPm25Concentration() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(89, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPm25Concentration();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getPm25Filter() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(90, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPm25Filter();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getAnionStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(91, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAnionStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getFrontWindowDefroster() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(92, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFrontWindowDefroster();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getBackWindowDefroster() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(93, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBackWindowDefroster();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getSteeringWheelHeatLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(94, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSteeringWheelHeatLevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IAirConditionService
            public int getWindOutletCanStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(95, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindOutletCanStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAirConditionService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAirConditionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
