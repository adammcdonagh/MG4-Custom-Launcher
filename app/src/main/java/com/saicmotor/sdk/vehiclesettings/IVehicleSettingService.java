package com.saicmotor.sdk.vehiclesettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.IVehicleSettingListener;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleSettingBean;

/* loaded from: classes2.dex */
public interface IVehicleSettingService extends IInterface {
    void addVehicleSettingListener(IVehicleSettingListener iVehicleSettingListener) throws RemoteException;

    int getAccTjaMode() throws RemoteException;

    int getAmbtLightAvlbly() throws RemoteException;

    int getAmbtLightBreathingOn() throws RemoteException;

    int getAmbtLightBrightness() throws RemoteException;

    int getAmbtLightColor() throws RemoteException;

    int getAmbtLightDrvMode() throws RemoteException;

    int getAmbtLightGlbOn() throws RemoteException;

    int getAmbtLightOpenMode() throws RemoteException;

    int getAmbtLightWlcmMode() throws RemoteException;

    int getAmbtLightWlcmOn() throws RemoteException;

    int getApproachUnlockMode() throws RemoteException;

    int getAqsSensitivity() throws RemoteException;

    int getAutoEmergencyBraking() throws RemoteException;

    int getAutoHoldSwitch() throws RemoteException;

    int getAutoMainBeamControl() throws RemoteException;

    int getAutoModeAirVolume() throws RemoteException;

    int getBlindSpotDetection() throws RemoteException;

    int getBrakePedalLevel() throws RemoteException;

    int getBrakeToStandstill() throws RemoteException;

    int getBusOffStatus() throws RemoteException;

    int getCarSearchFeedback() throws RemoteException;

    int getCarSearchFeedbackSwitch() throws RemoteException;

    int getDefrostLinkage() throws RemoteException;

    int getDriveMode() throws RemoteException;

    int getDriverMonitorSys() throws RemoteException;

    int getDriverMonitorSysVibration() throws RemoteException;

    int getDriverMonitorSysWarningSound() throws RemoteException;

    int getDriverSeatAutoWlcm() throws RemoteException;

    int getDrivingAutoLock() throws RemoteException;

    int getElectricPowertrainLevel() throws RemoteException;

    float getElectricTailgatePos() throws RemoteException;

    int getFarewellLightMode() throws RemoteException;

    int getFcwAlarmMode() throws RemoteException;

    int getFcwAutoBrakeMode() throws RemoteException;

    int getFcwSensitivity() throws RemoteException;

    int getFcwSysFailure() throws RemoteException;

    int getHomeLightTime() throws RemoteException;

    int getHvacCustomSwitch() throws RemoteException;

    int getHvacEconLinkage() throws RemoteException;

    int getInductiveDoorHandle() throws RemoteException;

    int getInductiveTailgate() throws RemoteException;

    int getKeyUnlockMode() throws RemoteException;

    int getLampHomeOn() throws RemoteException;

    int getLampWelcomeOn() throws RemoteException;

    int getLaneChangeAsst() throws RemoteException;

    int getLaneKeepingAsstMode() throws RemoteException;

    int getLaneKeepingAsstSen() throws RemoteException;

    int getLaneKeepingVibration() throws RemoteException;

    int getLaneKeepingWarningSound() throws RemoteException;

    int getLastAmbtLightOpenMode() throws RemoteException;

    int getLastHomeLightTime() throws RemoteException;

    int getLastLaneKeepingAsstMode() throws RemoteException;

    int getLastWelcomeLightTime() throws RemoteException;

    int getLdwSysFailure() throws RemoteException;

    int getLeaveAutoLockMode() throws RemoteException;

    int getLeftRearviewDowndip() throws RemoteException;

    int getLightFrontFogSwitch() throws RemoteException;

    int getLightRearFogSwitch() throws RemoteException;

    int getLkaSysFailure() throws RemoteException;

    int getLongerEndurance() throws RemoteException;

    int getLongerEnduranceDisable() throws RemoteException;

    int getLongerEnduranceRecommend() throws RemoteException;

    int getMusicLinkage() throws RemoteException;

    int getNearfieldUnlockMode() throws RemoteException;

    int getOuterRearviewFold() throws RemoteException;

    int getParkingWarning() throws RemoteException;

    int getPdcChangeSts() throws RemoteException;

    int getPowerModeSwitch() throws RemoteException;

    int getPsgSafetyAirbagOn() throws RemoteException;

    int getPsgSafetyAirbagStatus() throws RemoteException;

    int getRctbSysFailure() throws RemoteException;

    int getRdaAvlbly() throws RemoteException;

    int getRdaSysStatus() throws RemoteException;

    int getRearCollisionWarning() throws RemoteException;

    int getRearCrossTrafficSys() throws RemoteException;

    int getRearDriveAsstSys() throws RemoteException;

    int getRegenerativeBrakeSwitch() throws RemoteException;

    int getRegenerativeLevel() throws RemoteException;

    int getRegenerativeLevelDisable() throws RemoteException;

    int getRightRearviewDowndip() throws RemoteException;

    int getSdmAvailableStatus() throws RemoteException;

    int getSeatAutoAdjust() throws RemoteException;

    int getSeatHeatVentLinkage() throws RemoteException;

    int getSignalPedal() throws RemoteException;

    int getSignalPedalLnhbReg() throws RemoteException;

    int getSpeedAsstMode() throws RemoteException;

    int getSpeedAsstSlifWarning() throws RemoteException;

    int getStallingAutoUnlock() throws RemoteException;

    int getStartLinkage() throws RemoteException;

    int getSteeringLevel() throws RemoteException;

    int getSteeringWheelDefine() throws RemoteException;

    int getTdmAvlbly() throws RemoteException;

    int getTowingModeSwitch() throws RemoteException;

    int getTrafficJamAsstOn() throws RemoteException;

    int getTrlrCnctSts() throws RemoteException;

    int getUdwSysFailure() throws RemoteException;

    int getUnsteadyDrivingWarning() throws RemoteException;

    int getUnsteadyDrivingWarningSen() throws RemoteException;

    VehicleSettingBean getVehicleSettingStatus() throws RemoteException;

    int getVoiceLinkage() throws RemoteException;

    int getWelcomeLightTime() throws RemoteException;

    int getWelcomeSoundAvlbly() throws RemoteException;

    int getWelcomeSoundOn() throws RemoteException;

    int getWelcomeSoundType() throws RemoteException;

    void removeVehicleSettingListener(IVehicleSettingListener iVehicleSettingListener) throws RemoteException;

    void setAccTjaMode(int i) throws RemoteException;

    void setAmbtLightBreathingOn(int i) throws RemoteException;

    void setAmbtLightBrightness(int i) throws RemoteException;

    void setAmbtLightColor(int i) throws RemoteException;

    void setAmbtLightDrvMode(int i) throws RemoteException;

    void setAmbtLightGlbOn(int i) throws RemoteException;

    void setAmbtLightOpenMode(int i) throws RemoteException;

    void setAmbtLightWlcmMode(int i) throws RemoteException;

    void setAmbtLightWlcmOn(int i) throws RemoteException;

    void setApproachUnlockMode(int i) throws RemoteException;

    void setAqsSensitivity(int i) throws RemoteException;

    void setAutoEmergencyBraking(int i) throws RemoteException;

    void setAutoHoldSwitch(int i) throws RemoteException;

    void setAutoMainBeamControl(int i) throws RemoteException;

    void setAutoModeAirVolume(int i) throws RemoteException;

    void setBlindSpotDetection(int i) throws RemoteException;

    void setBrakePedalLevel(int i) throws RemoteException;

    void setBrakeToStandstill(int i) throws RemoteException;

    void setCarSearchFeedback(int i) throws RemoteException;

    void setCarSearchFeedbackSwitch(int i) throws RemoteException;

    void setDefrostLinkage(int i) throws RemoteException;

    void setDriveMode(int i) throws RemoteException;

    void setDriverMonitorSys(int i) throws RemoteException;

    void setDriverMonitorSysVibration(int i) throws RemoteException;

    void setDriverMonitorSysWarningSound(int i) throws RemoteException;

    void setDriverSeatAutoWlcm(int i) throws RemoteException;

    void setDrivingAutoLock(int i) throws RemoteException;

    void setElectricPowertrainLevel(int i) throws RemoteException;

    void setElectricTailgatePos(float f) throws RemoteException;

    void setFarewellLightMode(int i) throws RemoteException;

    void setFcwAlarmMode(int i) throws RemoteException;

    void setFcwAutoBrakeMode(int i) throws RemoteException;

    void setFcwSensitivity(int i) throws RemoteException;

    void setHomeLightTime(int i) throws RemoteException;

    void setHvacCustomSwitch(int i) throws RemoteException;

    void setHvacEconLinkage(int i) throws RemoteException;

    void setInductiveDoorHandle(int i) throws RemoteException;

    void setInductiveTailgate(int i) throws RemoteException;

    void setKeyUnlockMode(int i) throws RemoteException;

    void setLampHomeOn(int i) throws RemoteException;

    void setLampWelcomeOn(int i) throws RemoteException;

    void setLaneChangeAsst(int i) throws RemoteException;

    void setLaneKeepingAsstMode(int i) throws RemoteException;

    void setLaneKeepingAsstSen(int i) throws RemoteException;

    void setLaneKeepingVibration(int i) throws RemoteException;

    void setLaneKeepingWarningSound(int i) throws RemoteException;

    void setLeaveAutoLockMode(int i) throws RemoteException;

    void setLeftRearviewDowndip(int i) throws RemoteException;

    void setLightFrontFogSwitch(int i) throws RemoteException;

    void setLightRearFogSwitch(int i) throws RemoteException;

    void setLongerEndurance(int i) throws RemoteException;

    void setMusicLinkage(int i) throws RemoteException;

    void setNearfieldUnlockMode(int i) throws RemoteException;

    void setOuterRearviewFold(int i) throws RemoteException;

    void setParkingWarning(int i) throws RemoteException;

    void setPdcChangeSts(int i) throws RemoteException;

    void setPowerModeSwitch(int i) throws RemoteException;

    void setPsgSafetyAirbagOn(int i) throws RemoteException;

    void setRearCollisionWarning(int i) throws RemoteException;

    void setRearCrossTrafficSys(int i) throws RemoteException;

    void setRearDriveAsstSys(int i) throws RemoteException;

    void setRegenerativeBrakeSwitch(int i) throws RemoteException;

    void setRegenerativeLevel(int i) throws RemoteException;

    void setRightRearviewDowndip(int i) throws RemoteException;

    void setSeatAutoAdjust(int i) throws RemoteException;

    void setSeatHeatVentLinkage(int i) throws RemoteException;

    void setSignalPedal(int i) throws RemoteException;

    void setSpeedAsstMode(int i) throws RemoteException;

    void setSpeedAsstSlifWarning(int i) throws RemoteException;

    void setStallingAutoUnlock(int i) throws RemoteException;

    void setStartLinkage(int i) throws RemoteException;

    void setSteeringLevel(int i) throws RemoteException;

    void setSteeringWheelDefine(int i) throws RemoteException;

    void setTowingModeSwitch(int i) throws RemoteException;

    void setTrafficJamAsstOn(int i) throws RemoteException;

    void setTrlrMdSts(int i) throws RemoteException;

    void setUnsteadyDrivingWarning(int i) throws RemoteException;

    void setUnsteadyDrivingWarningSen(int i) throws RemoteException;

    void setVoiceLinkage(int i) throws RemoteException;

    void setWelcomeLightTime(int i) throws RemoteException;

    void setWelcomeSoundOn(int i) throws RemoteException;

    void setWelcomeSoundType(int i) throws RemoteException;

    public static class Default implements IVehicleSettingService {
        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void addVehicleSettingListener(IVehicleSettingListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void removeVehicleSettingListener(IVehicleSettingListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAutoModeAirVolume() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAutoModeAirVolume(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getHvacEconLinkage() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setHvacEconLinkage(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getDefrostLinkage() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setDefrostLinkage(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAqsSensitivity() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAqsSensitivity(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getSeatHeatVentLinkage() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setSeatHeatVentLinkage(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public float getElectricTailgatePos() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setElectricTailgatePos(float signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getDrivingAutoLock() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setDrivingAutoLock(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getStallingAutoUnlock() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setStallingAutoUnlock(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getKeyUnlockMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setKeyUnlockMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getNearfieldUnlockMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setNearfieldUnlockMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getApproachUnlockMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setApproachUnlockMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLeaveAutoLockMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLeaveAutoLockMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getInductiveTailgate() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setInductiveTailgate(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getInductiveDoorHandle() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setInductiveDoorHandle(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLeftRearviewDowndip() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLeftRearviewDowndip(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getRightRearviewDowndip() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setRightRearviewDowndip(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getOuterRearviewFold() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setOuterRearviewFold(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getSeatAutoAdjust() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setSeatAutoAdjust(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getDriverSeatAutoWlcm() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setDriverSeatAutoWlcm(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getSteeringWheelDefine() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setSteeringWheelDefine(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getHvacCustomSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setHvacCustomSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getWelcomeSoundOn() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setWelcomeSoundOn(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getWelcomeSoundType() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setWelcomeSoundType(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getWelcomeLightTime() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setWelcomeLightTime(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getHomeLightTime() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setHomeLightTime(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAutoMainBeamControl() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAutoMainBeamControl(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAmbtLightGlbOn() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAmbtLightGlbOn(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAmbtLightOpenMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAmbtLightOpenMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAmbtLightDrvMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAmbtLightDrvMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAmbtLightColor() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAmbtLightColor(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAmbtLightBrightness() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAmbtLightBrightness(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAmbtLightBreathingOn() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAmbtLightBreathingOn(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAmbtLightWlcmOn() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAmbtLightWlcmOn(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAmbtLightWlcmMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAmbtLightWlcmMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getStartLinkage() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setStartLinkage(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getVoiceLinkage() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setVoiceLinkage(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getMusicLinkage() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setMusicLinkage(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getSpeedAsstSlifWarning() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setSpeedAsstSlifWarning(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getSpeedAsstMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setSpeedAsstMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAccTjaMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAccTjaMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLaneKeepingAsstMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLaneKeepingAsstMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLaneKeepingAsstSen() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLaneKeepingAsstSen(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLaneKeepingWarningSound() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLaneKeepingWarningSound(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLaneKeepingVibration() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLaneKeepingVibration(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLkaSysFailure() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLdwSysFailure() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getTrafficJamAsstOn() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setTrafficJamAsstOn(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getFcwAlarmMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setFcwAlarmMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getFcwAutoBrakeMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setFcwAutoBrakeMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getFcwSensitivity() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setFcwSensitivity(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getFcwSysFailure() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAutoEmergencyBraking() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAutoEmergencyBraking(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getRearDriveAsstSys() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setRearDriveAsstSys(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getBlindSpotDetection() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setBlindSpotDetection(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLaneChangeAsst() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLaneChangeAsst(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getRearCrossTrafficSys() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setRearCrossTrafficSys(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getRearCollisionWarning() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setRearCollisionWarning(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getRctbSysFailure() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getRdaSysStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getParkingWarning() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setParkingWarning(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getUnsteadyDrivingWarning() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setUnsteadyDrivingWarning(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getUnsteadyDrivingWarningSen() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setUnsteadyDrivingWarningSen(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getUdwSysFailure() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getDriverMonitorSys() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setDriverMonitorSys(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getDriverMonitorSysWarningSound() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setDriverMonitorSysWarningSound(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getDriverMonitorSysVibration() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setDriverMonitorSysVibration(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getDriveMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setDriveMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getElectricPowertrainLevel() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setElectricPowertrainLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getSteeringLevel() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setSteeringLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getBrakePedalLevel() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setBrakePedalLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getRegenerativeBrakeSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setRegenerativeBrakeSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getPsgSafetyAirbagOn() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getPsgSafetyAirbagStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setPsgSafetyAirbagOn(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public VehicleSettingBean getVehicleSettingStatus() throws RemoteException {
            return null;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getSdmAvailableStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLastWelcomeLightTime() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLastHomeLightTime() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLastAmbtLightOpenMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLastLaneKeepingAsstMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getRdaAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getWelcomeSoundAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAmbtLightAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getBusOffStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLampHomeOn() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLampHomeOn(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLampWelcomeOn() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLampWelcomeOn(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getCarSearchFeedback() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setCarSearchFeedback(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getFarewellLightMode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setFarewellLightMode(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getRegenerativeLevel() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setRegenerativeLevel(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getRegenerativeLevelDisable() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getSignalPedal() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setSignalPedal(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getSignalPedalLnhbReg() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLongerEndurance() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLongerEndurance(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLongerEnduranceRecommend() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLongerEnduranceDisable() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getBrakeToStandstill() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setBrakeToStandstill(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getPowerModeSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setPowerModeSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getAutoHoldSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setAutoHoldSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getCarSearchFeedbackSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setCarSearchFeedbackSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLightRearFogSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLightRearFogSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getTowingModeSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setTowingModeSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getTdmAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getTrlrCnctSts() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setTrlrMdSts(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getPdcChangeSts() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setPdcChangeSts(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public int getLightFrontFogSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
        public void setLightFrontFogSwitch(int signalValue) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVehicleSettingService {
        private static final String DESCRIPTOR = "com.saicmotor.sdk.vehiclesettings.IVehicleSettingService";
        static final int TRANSACTION_addVehicleSettingListener = 1;
        static final int TRANSACTION_getAccTjaMode = 81;
        static final int TRANSACTION_getAmbtLightAvlbly = 150;
        static final int TRANSACTION_getAmbtLightBreathingOn = 65;
        static final int TRANSACTION_getAmbtLightBrightness = 63;
        static final int TRANSACTION_getAmbtLightColor = 61;
        static final int TRANSACTION_getAmbtLightDrvMode = 59;
        static final int TRANSACTION_getAmbtLightGlbOn = 55;
        static final int TRANSACTION_getAmbtLightOpenMode = 57;
        static final int TRANSACTION_getAmbtLightWlcmMode = 69;
        static final int TRANSACTION_getAmbtLightWlcmOn = 67;
        static final int TRANSACTION_getApproachUnlockMode = 23;
        static final int TRANSACTION_getAqsSensitivity = 9;
        static final int TRANSACTION_getAutoEmergencyBraking = 102;
        static final int TRANSACTION_getAutoHoldSwitch = 174;
        static final int TRANSACTION_getAutoMainBeamControl = 53;
        static final int TRANSACTION_getAutoModeAirVolume = 3;
        static final int TRANSACTION_getBlindSpotDetection = 106;
        static final int TRANSACTION_getBrakePedalLevel = 135;
        static final int TRANSACTION_getBrakeToStandstill = 170;
        static final int TRANSACTION_getBusOffStatus = 151;
        static final int TRANSACTION_getCarSearchFeedback = 156;
        static final int TRANSACTION_getCarSearchFeedbackSwitch = 176;
        static final int TRANSACTION_getDefrostLinkage = 7;
        static final int TRANSACTION_getDriveMode = 129;
        static final int TRANSACTION_getDriverMonitorSys = 123;
        static final int TRANSACTION_getDriverMonitorSysVibration = 127;
        static final int TRANSACTION_getDriverMonitorSysWarningSound = 125;
        static final int TRANSACTION_getDriverSeatAutoWlcm = 39;
        static final int TRANSACTION_getDrivingAutoLock = 15;
        static final int TRANSACTION_getElectricPowertrainLevel = 131;
        static final int TRANSACTION_getElectricTailgatePos = 13;
        static final int TRANSACTION_getFarewellLightMode = 158;
        static final int TRANSACTION_getFcwAlarmMode = 95;
        static final int TRANSACTION_getFcwAutoBrakeMode = 97;
        static final int TRANSACTION_getFcwSensitivity = 99;
        static final int TRANSACTION_getFcwSysFailure = 101;
        static final int TRANSACTION_getHomeLightTime = 51;
        static final int TRANSACTION_getHvacCustomSwitch = 43;
        static final int TRANSACTION_getHvacEconLinkage = 5;
        static final int TRANSACTION_getInductiveDoorHandle = 29;
        static final int TRANSACTION_getInductiveTailgate = 27;
        static final int TRANSACTION_getKeyUnlockMode = 19;
        static final int TRANSACTION_getLampHomeOn = 152;
        static final int TRANSACTION_getLampWelcomeOn = 154;
        static final int TRANSACTION_getLaneChangeAsst = 108;
        static final int TRANSACTION_getLaneKeepingAsstMode = 83;
        static final int TRANSACTION_getLaneKeepingAsstSen = 85;
        static final int TRANSACTION_getLaneKeepingVibration = 89;
        static final int TRANSACTION_getLaneKeepingWarningSound = 87;
        static final int TRANSACTION_getLastAmbtLightOpenMode = 146;
        static final int TRANSACTION_getLastHomeLightTime = 145;
        static final int TRANSACTION_getLastLaneKeepingAsstMode = 147;
        static final int TRANSACTION_getLastWelcomeLightTime = 144;
        static final int TRANSACTION_getLdwSysFailure = 92;
        static final int TRANSACTION_getLeaveAutoLockMode = 25;
        static final int TRANSACTION_getLeftRearviewDowndip = 31;
        static final int TRANSACTION_getLightFrontFogSwitch = 187;
        static final int TRANSACTION_getLightRearFogSwitch = 178;
        static final int TRANSACTION_getLkaSysFailure = 91;
        static final int TRANSACTION_getLongerEndurance = 166;
        static final int TRANSACTION_getLongerEnduranceDisable = 169;
        static final int TRANSACTION_getLongerEnduranceRecommend = 168;
        static final int TRANSACTION_getMusicLinkage = 75;
        static final int TRANSACTION_getNearfieldUnlockMode = 21;
        static final int TRANSACTION_getOuterRearviewFold = 35;
        static final int TRANSACTION_getParkingWarning = 116;
        static final int TRANSACTION_getPdcChangeSts = 185;
        static final int TRANSACTION_getPowerModeSwitch = 172;
        static final int TRANSACTION_getPsgSafetyAirbagOn = 139;
        static final int TRANSACTION_getPsgSafetyAirbagStatus = 140;
        static final int TRANSACTION_getRctbSysFailure = 114;
        static final int TRANSACTION_getRdaAvlbly = 148;
        static final int TRANSACTION_getRdaSysStatus = 115;
        static final int TRANSACTION_getRearCollisionWarning = 112;
        static final int TRANSACTION_getRearCrossTrafficSys = 110;
        static final int TRANSACTION_getRearDriveAsstSys = 104;
        static final int TRANSACTION_getRegenerativeBrakeSwitch = 137;
        static final int TRANSACTION_getRegenerativeLevel = 160;
        static final int TRANSACTION_getRegenerativeLevelDisable = 162;
        static final int TRANSACTION_getRightRearviewDowndip = 33;
        static final int TRANSACTION_getSdmAvailableStatus = 143;
        static final int TRANSACTION_getSeatAutoAdjust = 37;
        static final int TRANSACTION_getSeatHeatVentLinkage = 11;
        static final int TRANSACTION_getSignalPedal = 163;
        static final int TRANSACTION_getSignalPedalLnhbReg = 165;
        static final int TRANSACTION_getSpeedAsstMode = 79;
        static final int TRANSACTION_getSpeedAsstSlifWarning = 77;
        static final int TRANSACTION_getStallingAutoUnlock = 17;
        static final int TRANSACTION_getStartLinkage = 71;
        static final int TRANSACTION_getSteeringLevel = 133;
        static final int TRANSACTION_getSteeringWheelDefine = 41;
        static final int TRANSACTION_getTdmAvlbly = 182;
        static final int TRANSACTION_getTowingModeSwitch = 180;
        static final int TRANSACTION_getTrafficJamAsstOn = 93;
        static final int TRANSACTION_getTrlrCnctSts = 183;
        static final int TRANSACTION_getUdwSysFailure = 122;
        static final int TRANSACTION_getUnsteadyDrivingWarning = 118;
        static final int TRANSACTION_getUnsteadyDrivingWarningSen = 120;
        static final int TRANSACTION_getVehicleSettingStatus = 142;
        static final int TRANSACTION_getVoiceLinkage = 73;
        static final int TRANSACTION_getWelcomeLightTime = 49;
        static final int TRANSACTION_getWelcomeSoundAvlbly = 149;
        static final int TRANSACTION_getWelcomeSoundOn = 45;
        static final int TRANSACTION_getWelcomeSoundType = 47;
        static final int TRANSACTION_removeVehicleSettingListener = 2;
        static final int TRANSACTION_setAccTjaMode = 82;
        static final int TRANSACTION_setAmbtLightBreathingOn = 66;
        static final int TRANSACTION_setAmbtLightBrightness = 64;
        static final int TRANSACTION_setAmbtLightColor = 62;
        static final int TRANSACTION_setAmbtLightDrvMode = 60;
        static final int TRANSACTION_setAmbtLightGlbOn = 56;
        static final int TRANSACTION_setAmbtLightOpenMode = 58;
        static final int TRANSACTION_setAmbtLightWlcmMode = 70;
        static final int TRANSACTION_setAmbtLightWlcmOn = 68;
        static final int TRANSACTION_setApproachUnlockMode = 24;
        static final int TRANSACTION_setAqsSensitivity = 10;
        static final int TRANSACTION_setAutoEmergencyBraking = 103;
        static final int TRANSACTION_setAutoHoldSwitch = 175;
        static final int TRANSACTION_setAutoMainBeamControl = 54;
        static final int TRANSACTION_setAutoModeAirVolume = 4;
        static final int TRANSACTION_setBlindSpotDetection = 107;
        static final int TRANSACTION_setBrakePedalLevel = 136;
        static final int TRANSACTION_setBrakeToStandstill = 171;
        static final int TRANSACTION_setCarSearchFeedback = 157;
        static final int TRANSACTION_setCarSearchFeedbackSwitch = 177;
        static final int TRANSACTION_setDefrostLinkage = 8;
        static final int TRANSACTION_setDriveMode = 130;
        static final int TRANSACTION_setDriverMonitorSys = 124;
        static final int TRANSACTION_setDriverMonitorSysVibration = 128;
        static final int TRANSACTION_setDriverMonitorSysWarningSound = 126;
        static final int TRANSACTION_setDriverSeatAutoWlcm = 40;
        static final int TRANSACTION_setDrivingAutoLock = 16;
        static final int TRANSACTION_setElectricPowertrainLevel = 132;
        static final int TRANSACTION_setElectricTailgatePos = 14;
        static final int TRANSACTION_setFarewellLightMode = 159;
        static final int TRANSACTION_setFcwAlarmMode = 96;
        static final int TRANSACTION_setFcwAutoBrakeMode = 98;
        static final int TRANSACTION_setFcwSensitivity = 100;
        static final int TRANSACTION_setHomeLightTime = 52;
        static final int TRANSACTION_setHvacCustomSwitch = 44;
        static final int TRANSACTION_setHvacEconLinkage = 6;
        static final int TRANSACTION_setInductiveDoorHandle = 30;
        static final int TRANSACTION_setInductiveTailgate = 28;
        static final int TRANSACTION_setKeyUnlockMode = 20;
        static final int TRANSACTION_setLampHomeOn = 153;
        static final int TRANSACTION_setLampWelcomeOn = 155;
        static final int TRANSACTION_setLaneChangeAsst = 109;
        static final int TRANSACTION_setLaneKeepingAsstMode = 84;
        static final int TRANSACTION_setLaneKeepingAsstSen = 86;
        static final int TRANSACTION_setLaneKeepingVibration = 90;
        static final int TRANSACTION_setLaneKeepingWarningSound = 88;
        static final int TRANSACTION_setLeaveAutoLockMode = 26;
        static final int TRANSACTION_setLeftRearviewDowndip = 32;
        static final int TRANSACTION_setLightFrontFogSwitch = 188;
        static final int TRANSACTION_setLightRearFogSwitch = 179;
        static final int TRANSACTION_setLongerEndurance = 167;
        static final int TRANSACTION_setMusicLinkage = 76;
        static final int TRANSACTION_setNearfieldUnlockMode = 22;
        static final int TRANSACTION_setOuterRearviewFold = 36;
        static final int TRANSACTION_setParkingWarning = 117;
        static final int TRANSACTION_setPdcChangeSts = 186;
        static final int TRANSACTION_setPowerModeSwitch = 173;
        static final int TRANSACTION_setPsgSafetyAirbagOn = 141;
        static final int TRANSACTION_setRearCollisionWarning = 113;
        static final int TRANSACTION_setRearCrossTrafficSys = 111;
        static final int TRANSACTION_setRearDriveAsstSys = 105;
        static final int TRANSACTION_setRegenerativeBrakeSwitch = 138;
        static final int TRANSACTION_setRegenerativeLevel = 161;
        static final int TRANSACTION_setRightRearviewDowndip = 34;
        static final int TRANSACTION_setSeatAutoAdjust = 38;
        static final int TRANSACTION_setSeatHeatVentLinkage = 12;
        static final int TRANSACTION_setSignalPedal = 164;
        static final int TRANSACTION_setSpeedAsstMode = 80;
        static final int TRANSACTION_setSpeedAsstSlifWarning = 78;
        static final int TRANSACTION_setStallingAutoUnlock = 18;
        static final int TRANSACTION_setStartLinkage = 72;
        static final int TRANSACTION_setSteeringLevel = 134;
        static final int TRANSACTION_setSteeringWheelDefine = 42;
        static final int TRANSACTION_setTowingModeSwitch = 181;
        static final int TRANSACTION_setTrafficJamAsstOn = 94;
        static final int TRANSACTION_setTrlrMdSts = 184;
        static final int TRANSACTION_setUnsteadyDrivingWarning = 119;
        static final int TRANSACTION_setUnsteadyDrivingWarningSen = 121;
        static final int TRANSACTION_setVoiceLinkage = 74;
        static final int TRANSACTION_setWelcomeLightTime = 50;
        static final int TRANSACTION_setWelcomeSoundOn = 46;
        static final int TRANSACTION_setWelcomeSoundType = 48;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVehicleSettingService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IVehicleSettingService)) {
                return (IVehicleSettingService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    IVehicleSettingListener _arg0 = IVehicleSettingListener.Stub.asInterface(data.readStrongBinder());
                    addVehicleSettingListener(_arg0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    IVehicleSettingListener _arg02 = IVehicleSettingListener.Stub.asInterface(data.readStrongBinder());
                    removeVehicleSettingListener(_arg02);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getAutoModeAirVolume();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    setAutoModeAirVolume(_arg03);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = getHvacEconLinkage();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    setHvacEconLinkage(_arg04);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = getDefrostLinkage();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    setDefrostLinkage(_arg05);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = getAqsSensitivity();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg06 = data.readInt();
                    setAqsSensitivity(_arg06);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _result5 = getSeatHeatVentLinkage();
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg07 = data.readInt();
                    setSeatHeatVentLinkage(_arg07);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    float _result6 = getElectricTailgatePos();
                    reply.writeNoException();
                    reply.writeFloat(_result6);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    float _arg08 = data.readFloat();
                    setElectricTailgatePos(_arg08);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    int _result7 = getDrivingAutoLock();
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    setDrivingAutoLock(_arg09);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    int _result8 = getStallingAutoUnlock();
                    reply.writeNoException();
                    reply.writeInt(_result8);
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    setStallingAutoUnlock(_arg010);
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    int _result9 = getKeyUnlockMode();
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg011 = data.readInt();
                    setKeyUnlockMode(_arg011);
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    int _result10 = getNearfieldUnlockMode();
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                case 22:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg012 = data.readInt();
                    setNearfieldUnlockMode(_arg012);
                    return true;
                case 23:
                    data.enforceInterface(DESCRIPTOR);
                    int _result11 = getApproachUnlockMode();
                    reply.writeNoException();
                    reply.writeInt(_result11);
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg013 = data.readInt();
                    setApproachUnlockMode(_arg013);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    int _result12 = getLeaveAutoLockMode();
                    reply.writeNoException();
                    reply.writeInt(_result12);
                    return true;
                case 26:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg014 = data.readInt();
                    setLeaveAutoLockMode(_arg014);
                    return true;
                case 27:
                    data.enforceInterface(DESCRIPTOR);
                    int _result13 = getInductiveTailgate();
                    reply.writeNoException();
                    reply.writeInt(_result13);
                    return true;
                case 28:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg015 = data.readInt();
                    setInductiveTailgate(_arg015);
                    return true;
                case 29:
                    data.enforceInterface(DESCRIPTOR);
                    int _result14 = getInductiveDoorHandle();
                    reply.writeNoException();
                    reply.writeInt(_result14);
                    return true;
                case 30:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg016 = data.readInt();
                    setInductiveDoorHandle(_arg016);
                    return true;
                case 31:
                    data.enforceInterface(DESCRIPTOR);
                    int _result15 = getLeftRearviewDowndip();
                    reply.writeNoException();
                    reply.writeInt(_result15);
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg017 = data.readInt();
                    setLeftRearviewDowndip(_arg017);
                    return true;
                case 33:
                    data.enforceInterface(DESCRIPTOR);
                    int _result16 = getRightRearviewDowndip();
                    reply.writeNoException();
                    reply.writeInt(_result16);
                    return true;
                case 34:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg018 = data.readInt();
                    setRightRearviewDowndip(_arg018);
                    return true;
                case 35:
                    data.enforceInterface(DESCRIPTOR);
                    int _result17 = getOuterRearviewFold();
                    reply.writeNoException();
                    reply.writeInt(_result17);
                    return true;
                case 36:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg019 = data.readInt();
                    setOuterRearviewFold(_arg019);
                    return true;
                case 37:
                    data.enforceInterface(DESCRIPTOR);
                    int _result18 = getSeatAutoAdjust();
                    reply.writeNoException();
                    reply.writeInt(_result18);
                    return true;
                case 38:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg020 = data.readInt();
                    setSeatAutoAdjust(_arg020);
                    return true;
                case 39:
                    data.enforceInterface(DESCRIPTOR);
                    int _result19 = getDriverSeatAutoWlcm();
                    reply.writeNoException();
                    reply.writeInt(_result19);
                    return true;
                case 40:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg021 = data.readInt();
                    setDriverSeatAutoWlcm(_arg021);
                    return true;
                case 41:
                    data.enforceInterface(DESCRIPTOR);
                    int _result20 = getSteeringWheelDefine();
                    reply.writeNoException();
                    reply.writeInt(_result20);
                    return true;
                case 42:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg022 = data.readInt();
                    setSteeringWheelDefine(_arg022);
                    return true;
                case 43:
                    data.enforceInterface(DESCRIPTOR);
                    int _result21 = getHvacCustomSwitch();
                    reply.writeNoException();
                    reply.writeInt(_result21);
                    return true;
                case 44:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg023 = data.readInt();
                    setHvacCustomSwitch(_arg023);
                    return true;
                case 45:
                    data.enforceInterface(DESCRIPTOR);
                    int _result22 = getWelcomeSoundOn();
                    reply.writeNoException();
                    reply.writeInt(_result22);
                    return true;
                case 46:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg024 = data.readInt();
                    setWelcomeSoundOn(_arg024);
                    return true;
                case 47:
                    data.enforceInterface(DESCRIPTOR);
                    int _result23 = getWelcomeSoundType();
                    reply.writeNoException();
                    reply.writeInt(_result23);
                    return true;
                case 48:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg025 = data.readInt();
                    setWelcomeSoundType(_arg025);
                    return true;
                case 49:
                    data.enforceInterface(DESCRIPTOR);
                    int _result24 = getWelcomeLightTime();
                    reply.writeNoException();
                    reply.writeInt(_result24);
                    return true;
                case 50:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg026 = data.readInt();
                    setWelcomeLightTime(_arg026);
                    return true;
                case 51:
                    data.enforceInterface(DESCRIPTOR);
                    int _result25 = getHomeLightTime();
                    reply.writeNoException();
                    reply.writeInt(_result25);
                    return true;
                case 52:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg027 = data.readInt();
                    setHomeLightTime(_arg027);
                    return true;
                case 53:
                    data.enforceInterface(DESCRIPTOR);
                    int _result26 = getAutoMainBeamControl();
                    reply.writeNoException();
                    reply.writeInt(_result26);
                    return true;
                case 54:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg028 = data.readInt();
                    setAutoMainBeamControl(_arg028);
                    return true;
                case 55:
                    data.enforceInterface(DESCRIPTOR);
                    int _result27 = getAmbtLightGlbOn();
                    reply.writeNoException();
                    reply.writeInt(_result27);
                    return true;
                case 56:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg029 = data.readInt();
                    setAmbtLightGlbOn(_arg029);
                    return true;
                case 57:
                    data.enforceInterface(DESCRIPTOR);
                    int _result28 = getAmbtLightOpenMode();
                    reply.writeNoException();
                    reply.writeInt(_result28);
                    return true;
                case 58:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg030 = data.readInt();
                    setAmbtLightOpenMode(_arg030);
                    return true;
                case 59:
                    data.enforceInterface(DESCRIPTOR);
                    int _result29 = getAmbtLightDrvMode();
                    reply.writeNoException();
                    reply.writeInt(_result29);
                    return true;
                case 60:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg031 = data.readInt();
                    setAmbtLightDrvMode(_arg031);
                    return true;
                case 61:
                    data.enforceInterface(DESCRIPTOR);
                    int _result30 = getAmbtLightColor();
                    reply.writeNoException();
                    reply.writeInt(_result30);
                    return true;
                case 62:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg032 = data.readInt();
                    setAmbtLightColor(_arg032);
                    return true;
                case 63:
                    data.enforceInterface(DESCRIPTOR);
                    int _result31 = getAmbtLightBrightness();
                    reply.writeNoException();
                    reply.writeInt(_result31);
                    return true;
                case 64:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg033 = data.readInt();
                    setAmbtLightBrightness(_arg033);
                    return true;
                case 65:
                    data.enforceInterface(DESCRIPTOR);
                    int _result32 = getAmbtLightBreathingOn();
                    reply.writeNoException();
                    reply.writeInt(_result32);
                    return true;
                case 66:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg034 = data.readInt();
                    setAmbtLightBreathingOn(_arg034);
                    return true;
                case 67:
                    data.enforceInterface(DESCRIPTOR);
                    int _result33 = getAmbtLightWlcmOn();
                    reply.writeNoException();
                    reply.writeInt(_result33);
                    return true;
                case 68:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg035 = data.readInt();
                    setAmbtLightWlcmOn(_arg035);
                    return true;
                case 69:
                    data.enforceInterface(DESCRIPTOR);
                    int _result34 = getAmbtLightWlcmMode();
                    reply.writeNoException();
                    reply.writeInt(_result34);
                    return true;
                case 70:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg036 = data.readInt();
                    setAmbtLightWlcmMode(_arg036);
                    return true;
                case 71:
                    data.enforceInterface(DESCRIPTOR);
                    int _result35 = getStartLinkage();
                    reply.writeNoException();
                    reply.writeInt(_result35);
                    return true;
                case 72:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg037 = data.readInt();
                    setStartLinkage(_arg037);
                    return true;
                case 73:
                    data.enforceInterface(DESCRIPTOR);
                    int _result36 = getVoiceLinkage();
                    reply.writeNoException();
                    reply.writeInt(_result36);
                    return true;
                case 74:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg038 = data.readInt();
                    setVoiceLinkage(_arg038);
                    return true;
                case 75:
                    data.enforceInterface(DESCRIPTOR);
                    int _result37 = getMusicLinkage();
                    reply.writeNoException();
                    reply.writeInt(_result37);
                    return true;
                case 76:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg039 = data.readInt();
                    setMusicLinkage(_arg039);
                    return true;
                case 77:
                    data.enforceInterface(DESCRIPTOR);
                    int _result38 = getSpeedAsstSlifWarning();
                    reply.writeNoException();
                    reply.writeInt(_result38);
                    return true;
                case 78:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg040 = data.readInt();
                    setSpeedAsstSlifWarning(_arg040);
                    return true;
                case 79:
                    data.enforceInterface(DESCRIPTOR);
                    int _result39 = getSpeedAsstMode();
                    reply.writeNoException();
                    reply.writeInt(_result39);
                    return true;
                case 80:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg041 = data.readInt();
                    setSpeedAsstMode(_arg041);
                    return true;
                case 81:
                    data.enforceInterface(DESCRIPTOR);
                    int _result40 = getAccTjaMode();
                    reply.writeNoException();
                    reply.writeInt(_result40);
                    return true;
                case 82:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg042 = data.readInt();
                    setAccTjaMode(_arg042);
                    return true;
                case 83:
                    data.enforceInterface(DESCRIPTOR);
                    int _result41 = getLaneKeepingAsstMode();
                    reply.writeNoException();
                    reply.writeInt(_result41);
                    return true;
                case 84:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg043 = data.readInt();
                    setLaneKeepingAsstMode(_arg043);
                    return true;
                case 85:
                    data.enforceInterface(DESCRIPTOR);
                    int _result42 = getLaneKeepingAsstSen();
                    reply.writeNoException();
                    reply.writeInt(_result42);
                    return true;
                case 86:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg044 = data.readInt();
                    setLaneKeepingAsstSen(_arg044);
                    return true;
                case 87:
                    data.enforceInterface(DESCRIPTOR);
                    int _result43 = getLaneKeepingWarningSound();
                    reply.writeNoException();
                    reply.writeInt(_result43);
                    return true;
                case 88:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg045 = data.readInt();
                    setLaneKeepingWarningSound(_arg045);
                    return true;
                case 89:
                    data.enforceInterface(DESCRIPTOR);
                    int _result44 = getLaneKeepingVibration();
                    reply.writeNoException();
                    reply.writeInt(_result44);
                    return true;
                case 90:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg046 = data.readInt();
                    setLaneKeepingVibration(_arg046);
                    return true;
                case 91:
                    data.enforceInterface(DESCRIPTOR);
                    int _result45 = getLkaSysFailure();
                    reply.writeNoException();
                    reply.writeInt(_result45);
                    return true;
                case 92:
                    data.enforceInterface(DESCRIPTOR);
                    int _result46 = getLdwSysFailure();
                    reply.writeNoException();
                    reply.writeInt(_result46);
                    return true;
                case 93:
                    data.enforceInterface(DESCRIPTOR);
                    int _result47 = getTrafficJamAsstOn();
                    reply.writeNoException();
                    reply.writeInt(_result47);
                    return true;
                case 94:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg047 = data.readInt();
                    setTrafficJamAsstOn(_arg047);
                    return true;
                case 95:
                    data.enforceInterface(DESCRIPTOR);
                    int _result48 = getFcwAlarmMode();
                    reply.writeNoException();
                    reply.writeInt(_result48);
                    return true;
                case 96:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg048 = data.readInt();
                    setFcwAlarmMode(_arg048);
                    return true;
                case 97:
                    data.enforceInterface(DESCRIPTOR);
                    int _result49 = getFcwAutoBrakeMode();
                    reply.writeNoException();
                    reply.writeInt(_result49);
                    return true;
                case 98:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg049 = data.readInt();
                    setFcwAutoBrakeMode(_arg049);
                    return true;
                case 99:
                    data.enforceInterface(DESCRIPTOR);
                    int _result50 = getFcwSensitivity();
                    reply.writeNoException();
                    reply.writeInt(_result50);
                    return true;
                case 100:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg050 = data.readInt();
                    setFcwSensitivity(_arg050);
                    return true;
                case 101:
                    data.enforceInterface(DESCRIPTOR);
                    int _result51 = getFcwSysFailure();
                    reply.writeNoException();
                    reply.writeInt(_result51);
                    return true;
                case 102:
                    data.enforceInterface(DESCRIPTOR);
                    int _result52 = getAutoEmergencyBraking();
                    reply.writeNoException();
                    reply.writeInt(_result52);
                    return true;
                case 103:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg051 = data.readInt();
                    setAutoEmergencyBraking(_arg051);
                    return true;
                case 104:
                    data.enforceInterface(DESCRIPTOR);
                    int _result53 = getRearDriveAsstSys();
                    reply.writeNoException();
                    reply.writeInt(_result53);
                    return true;
                case 105:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg052 = data.readInt();
                    setRearDriveAsstSys(_arg052);
                    return true;
                case 106:
                    data.enforceInterface(DESCRIPTOR);
                    int _result54 = getBlindSpotDetection();
                    reply.writeNoException();
                    reply.writeInt(_result54);
                    return true;
                case 107:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg053 = data.readInt();
                    setBlindSpotDetection(_arg053);
                    return true;
                case 108:
                    data.enforceInterface(DESCRIPTOR);
                    int _result55 = getLaneChangeAsst();
                    reply.writeNoException();
                    reply.writeInt(_result55);
                    return true;
                case 109:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg054 = data.readInt();
                    setLaneChangeAsst(_arg054);
                    return true;
                case 110:
                    data.enforceInterface(DESCRIPTOR);
                    int _result56 = getRearCrossTrafficSys();
                    reply.writeNoException();
                    reply.writeInt(_result56);
                    return true;
                case 111:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg055 = data.readInt();
                    setRearCrossTrafficSys(_arg055);
                    return true;
                case 112:
                    data.enforceInterface(DESCRIPTOR);
                    int _result57 = getRearCollisionWarning();
                    reply.writeNoException();
                    reply.writeInt(_result57);
                    return true;
                case 113:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg056 = data.readInt();
                    setRearCollisionWarning(_arg056);
                    return true;
                case 114:
                    data.enforceInterface(DESCRIPTOR);
                    int _result58 = getRctbSysFailure();
                    reply.writeNoException();
                    reply.writeInt(_result58);
                    return true;
                case 115:
                    data.enforceInterface(DESCRIPTOR);
                    int _result59 = getRdaSysStatus();
                    reply.writeNoException();
                    reply.writeInt(_result59);
                    return true;
                case 116:
                    data.enforceInterface(DESCRIPTOR);
                    int _result60 = getParkingWarning();
                    reply.writeNoException();
                    reply.writeInt(_result60);
                    return true;
                case 117:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg057 = data.readInt();
                    setParkingWarning(_arg057);
                    return true;
                case 118:
                    data.enforceInterface(DESCRIPTOR);
                    int _result61 = getUnsteadyDrivingWarning();
                    reply.writeNoException();
                    reply.writeInt(_result61);
                    return true;
                case 119:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg058 = data.readInt();
                    setUnsteadyDrivingWarning(_arg058);
                    return true;
                case 120:
                    data.enforceInterface(DESCRIPTOR);
                    int _result62 = getUnsteadyDrivingWarningSen();
                    reply.writeNoException();
                    reply.writeInt(_result62);
                    return true;
                case 121:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg059 = data.readInt();
                    setUnsteadyDrivingWarningSen(_arg059);
                    return true;
                case 122:
                    data.enforceInterface(DESCRIPTOR);
                    int _result63 = getUdwSysFailure();
                    reply.writeNoException();
                    reply.writeInt(_result63);
                    return true;
                case 123:
                    data.enforceInterface(DESCRIPTOR);
                    int _result64 = getDriverMonitorSys();
                    reply.writeNoException();
                    reply.writeInt(_result64);
                    return true;
                case 124:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg060 = data.readInt();
                    setDriverMonitorSys(_arg060);
                    return true;
                case TRANSACTION_getDriverMonitorSysWarningSound /* 125 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result65 = getDriverMonitorSysWarningSound();
                    reply.writeNoException();
                    reply.writeInt(_result65);
                    return true;
                case TRANSACTION_setDriverMonitorSysWarningSound /* 126 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg061 = data.readInt();
                    setDriverMonitorSysWarningSound(_arg061);
                    return true;
                case TRANSACTION_getDriverMonitorSysVibration /* 127 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result66 = getDriverMonitorSysVibration();
                    reply.writeNoException();
                    reply.writeInt(_result66);
                    return true;
                case 128:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg062 = data.readInt();
                    setDriverMonitorSysVibration(_arg062);
                    return true;
                case TRANSACTION_getDriveMode /* 129 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result67 = getDriveMode();
                    reply.writeNoException();
                    reply.writeInt(_result67);
                    return true;
                case TRANSACTION_setDriveMode /* 130 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg063 = data.readInt();
                    setDriveMode(_arg063);
                    return true;
                case TRANSACTION_getElectricPowertrainLevel /* 131 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result68 = getElectricPowertrainLevel();
                    reply.writeNoException();
                    reply.writeInt(_result68);
                    return true;
                case TRANSACTION_setElectricPowertrainLevel /* 132 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg064 = data.readInt();
                    setElectricPowertrainLevel(_arg064);
                    return true;
                case TRANSACTION_getSteeringLevel /* 133 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result69 = getSteeringLevel();
                    reply.writeNoException();
                    reply.writeInt(_result69);
                    return true;
                case TRANSACTION_setSteeringLevel /* 134 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg065 = data.readInt();
                    setSteeringLevel(_arg065);
                    return true;
                case TRANSACTION_getBrakePedalLevel /* 135 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result70 = getBrakePedalLevel();
                    reply.writeNoException();
                    reply.writeInt(_result70);
                    return true;
                case TRANSACTION_setBrakePedalLevel /* 136 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg066 = data.readInt();
                    setBrakePedalLevel(_arg066);
                    return true;
                case TRANSACTION_getRegenerativeBrakeSwitch /* 137 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result71 = getRegenerativeBrakeSwitch();
                    reply.writeNoException();
                    reply.writeInt(_result71);
                    return true;
                case TRANSACTION_setRegenerativeBrakeSwitch /* 138 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg067 = data.readInt();
                    setRegenerativeBrakeSwitch(_arg067);
                    return true;
                case TRANSACTION_getPsgSafetyAirbagOn /* 139 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result72 = getPsgSafetyAirbagOn();
                    reply.writeNoException();
                    reply.writeInt(_result72);
                    return true;
                case TRANSACTION_getPsgSafetyAirbagStatus /* 140 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result73 = getPsgSafetyAirbagStatus();
                    reply.writeNoException();
                    reply.writeInt(_result73);
                    return true;
                case TRANSACTION_setPsgSafetyAirbagOn /* 141 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg068 = data.readInt();
                    setPsgSafetyAirbagOn(_arg068);
                    return true;
                case TRANSACTION_getVehicleSettingStatus /* 142 */:
                    data.enforceInterface(DESCRIPTOR);
                    VehicleSettingBean _result74 = getVehicleSettingStatus();
                    reply.writeNoException();
                    if (_result74 != null) {
                        reply.writeInt(1);
                        _result74.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_getSdmAvailableStatus /* 143 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result75 = getSdmAvailableStatus();
                    reply.writeNoException();
                    reply.writeInt(_result75);
                    return true;
                case TRANSACTION_getLastWelcomeLightTime /* 144 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result76 = getLastWelcomeLightTime();
                    reply.writeNoException();
                    reply.writeInt(_result76);
                    return true;
                case TRANSACTION_getLastHomeLightTime /* 145 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result77 = getLastHomeLightTime();
                    reply.writeNoException();
                    reply.writeInt(_result77);
                    return true;
                case TRANSACTION_getLastAmbtLightOpenMode /* 146 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result78 = getLastAmbtLightOpenMode();
                    reply.writeNoException();
                    reply.writeInt(_result78);
                    return true;
                case TRANSACTION_getLastLaneKeepingAsstMode /* 147 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result79 = getLastLaneKeepingAsstMode();
                    reply.writeNoException();
                    reply.writeInt(_result79);
                    return true;
                case TRANSACTION_getRdaAvlbly /* 148 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result80 = getRdaAvlbly();
                    reply.writeNoException();
                    reply.writeInt(_result80);
                    return true;
                case TRANSACTION_getWelcomeSoundAvlbly /* 149 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result81 = getWelcomeSoundAvlbly();
                    reply.writeNoException();
                    reply.writeInt(_result81);
                    return true;
                case TRANSACTION_getAmbtLightAvlbly /* 150 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result82 = getAmbtLightAvlbly();
                    reply.writeNoException();
                    reply.writeInt(_result82);
                    return true;
                case TRANSACTION_getBusOffStatus /* 151 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result83 = getBusOffStatus();
                    reply.writeNoException();
                    reply.writeInt(_result83);
                    return true;
                case TRANSACTION_getLampHomeOn /* 152 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result84 = getLampHomeOn();
                    reply.writeNoException();
                    reply.writeInt(_result84);
                    return true;
                case TRANSACTION_setLampHomeOn /* 153 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg069 = data.readInt();
                    setLampHomeOn(_arg069);
                    return true;
                case TRANSACTION_getLampWelcomeOn /* 154 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result85 = getLampWelcomeOn();
                    reply.writeNoException();
                    reply.writeInt(_result85);
                    return true;
                case TRANSACTION_setLampWelcomeOn /* 155 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg070 = data.readInt();
                    setLampWelcomeOn(_arg070);
                    return true;
                case TRANSACTION_getCarSearchFeedback /* 156 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result86 = getCarSearchFeedback();
                    reply.writeNoException();
                    reply.writeInt(_result86);
                    return true;
                case TRANSACTION_setCarSearchFeedback /* 157 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg071 = data.readInt();
                    setCarSearchFeedback(_arg071);
                    return true;
                case TRANSACTION_getFarewellLightMode /* 158 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result87 = getFarewellLightMode();
                    reply.writeNoException();
                    reply.writeInt(_result87);
                    return true;
                case TRANSACTION_setFarewellLightMode /* 159 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg072 = data.readInt();
                    setFarewellLightMode(_arg072);
                    return true;
                case TRANSACTION_getRegenerativeLevel /* 160 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result88 = getRegenerativeLevel();
                    reply.writeNoException();
                    reply.writeInt(_result88);
                    return true;
                case TRANSACTION_setRegenerativeLevel /* 161 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg073 = data.readInt();
                    setRegenerativeLevel(_arg073);
                    return true;
                case TRANSACTION_getRegenerativeLevelDisable /* 162 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result89 = getRegenerativeLevelDisable();
                    reply.writeNoException();
                    reply.writeInt(_result89);
                    return true;
                case TRANSACTION_getSignalPedal /* 163 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result90 = getSignalPedal();
                    reply.writeNoException();
                    reply.writeInt(_result90);
                    return true;
                case TRANSACTION_setSignalPedal /* 164 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg074 = data.readInt();
                    setSignalPedal(_arg074);
                    return true;
                case TRANSACTION_getSignalPedalLnhbReg /* 165 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result91 = getSignalPedalLnhbReg();
                    reply.writeNoException();
                    reply.writeInt(_result91);
                    return true;
                case TRANSACTION_getLongerEndurance /* 166 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result92 = getLongerEndurance();
                    reply.writeNoException();
                    reply.writeInt(_result92);
                    return true;
                case TRANSACTION_setLongerEndurance /* 167 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg075 = data.readInt();
                    setLongerEndurance(_arg075);
                    return true;
                case TRANSACTION_getLongerEnduranceRecommend /* 168 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result93 = getLongerEnduranceRecommend();
                    reply.writeNoException();
                    reply.writeInt(_result93);
                    return true;
                case TRANSACTION_getLongerEnduranceDisable /* 169 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result94 = getLongerEnduranceDisable();
                    reply.writeNoException();
                    reply.writeInt(_result94);
                    return true;
                case TRANSACTION_getBrakeToStandstill /* 170 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result95 = getBrakeToStandstill();
                    reply.writeNoException();
                    reply.writeInt(_result95);
                    return true;
                case TRANSACTION_setBrakeToStandstill /* 171 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg076 = data.readInt();
                    setBrakeToStandstill(_arg076);
                    return true;
                case TRANSACTION_getPowerModeSwitch /* 172 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result96 = getPowerModeSwitch();
                    reply.writeNoException();
                    reply.writeInt(_result96);
                    return true;
                case TRANSACTION_setPowerModeSwitch /* 173 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg077 = data.readInt();
                    setPowerModeSwitch(_arg077);
                    return true;
                case TRANSACTION_getAutoHoldSwitch /* 174 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result97 = getAutoHoldSwitch();
                    reply.writeNoException();
                    reply.writeInt(_result97);
                    return true;
                case TRANSACTION_setAutoHoldSwitch /* 175 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg078 = data.readInt();
                    setAutoHoldSwitch(_arg078);
                    return true;
                case TRANSACTION_getCarSearchFeedbackSwitch /* 176 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result98 = getCarSearchFeedbackSwitch();
                    reply.writeNoException();
                    reply.writeInt(_result98);
                    return true;
                case TRANSACTION_setCarSearchFeedbackSwitch /* 177 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg079 = data.readInt();
                    setCarSearchFeedbackSwitch(_arg079);
                    return true;
                case TRANSACTION_getLightRearFogSwitch /* 178 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result99 = getLightRearFogSwitch();
                    reply.writeNoException();
                    reply.writeInt(_result99);
                    return true;
                case TRANSACTION_setLightRearFogSwitch /* 179 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg080 = data.readInt();
                    setLightRearFogSwitch(_arg080);
                    return true;
                case TRANSACTION_getTowingModeSwitch /* 180 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result100 = getTowingModeSwitch();
                    reply.writeNoException();
                    reply.writeInt(_result100);
                    return true;
                case TRANSACTION_setTowingModeSwitch /* 181 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg081 = data.readInt();
                    setTowingModeSwitch(_arg081);
                    return true;
                case TRANSACTION_getTdmAvlbly /* 182 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result101 = getTdmAvlbly();
                    reply.writeNoException();
                    reply.writeInt(_result101);
                    return true;
                case TRANSACTION_getTrlrCnctSts /* 183 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result102 = getTrlrCnctSts();
                    reply.writeNoException();
                    reply.writeInt(_result102);
                    return true;
                case TRANSACTION_setTrlrMdSts /* 184 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg082 = data.readInt();
                    setTrlrMdSts(_arg082);
                    return true;
                case TRANSACTION_getPdcChangeSts /* 185 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result103 = getPdcChangeSts();
                    reply.writeNoException();
                    reply.writeInt(_result103);
                    return true;
                case TRANSACTION_setPdcChangeSts /* 186 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg083 = data.readInt();
                    setPdcChangeSts(_arg083);
                    return true;
                case TRANSACTION_getLightFrontFogSwitch /* 187 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result104 = getLightFrontFogSwitch();
                    reply.writeNoException();
                    reply.writeInt(_result104);
                    return true;
                case TRANSACTION_setLightFrontFogSwitch /* 188 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg084 = data.readInt();
                    setLightFrontFogSwitch(_arg084);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IVehicleSettingService {
            public static IVehicleSettingService sDefaultImpl;
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

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void addVehicleSettingListener(IVehicleSettingListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addVehicleSettingListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void removeVehicleSettingListener(IVehicleSettingListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeVehicleSettingListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAutoModeAirVolume() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoModeAirVolume();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAutoModeAirVolume(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAutoModeAirVolume(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getHvacEconLinkage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHvacEconLinkage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setHvacEconLinkage(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHvacEconLinkage(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getDefrostLinkage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefrostLinkage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setDefrostLinkage(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDefrostLinkage(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAqsSensitivity() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAqsSensitivity();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAqsSensitivity(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAqsSensitivity(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getSeatHeatVentLinkage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeatHeatVentLinkage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setSeatHeatVentLinkage(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSeatHeatVentLinkage(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public float getElectricTailgatePos() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElectricTailgatePos();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setElectricTailgatePos(float signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(signalValue);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setElectricTailgatePos(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getDrivingAutoLock() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrivingAutoLock();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setDrivingAutoLock(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrivingAutoLock(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getStallingAutoUnlock() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStallingAutoUnlock();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setStallingAutoUnlock(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setStallingAutoUnlock(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getKeyUnlockMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyUnlockMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setKeyUnlockMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setKeyUnlockMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getNearfieldUnlockMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNearfieldUnlockMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setNearfieldUnlockMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNearfieldUnlockMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getApproachUnlockMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApproachUnlockMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setApproachUnlockMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setApproachUnlockMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLeaveAutoLockMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeaveAutoLockMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLeaveAutoLockMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(26, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLeaveAutoLockMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getInductiveTailgate() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInductiveTailgate();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setInductiveTailgate(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(28, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInductiveTailgate(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getInductiveDoorHandle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInductiveDoorHandle();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setInductiveDoorHandle(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(30, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInductiveDoorHandle(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLeftRearviewDowndip() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeftRearviewDowndip();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLeftRearviewDowndip(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(32, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLeftRearviewDowndip(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getRightRearviewDowndip() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRightRearviewDowndip();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setRightRearviewDowndip(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(34, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRightRearviewDowndip(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getOuterRearviewFold() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOuterRearviewFold();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setOuterRearviewFold(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(36, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setOuterRearviewFold(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getSeatAutoAdjust() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeatAutoAdjust();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setSeatAutoAdjust(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(38, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSeatAutoAdjust(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getDriverSeatAutoWlcm() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDriverSeatAutoWlcm();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setDriverSeatAutoWlcm(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(40, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDriverSeatAutoWlcm(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getSteeringWheelDefine() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSteeringWheelDefine();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setSteeringWheelDefine(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(42, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSteeringWheelDefine(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getHvacCustomSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHvacCustomSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setHvacCustomSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(44, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHvacCustomSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getWelcomeSoundOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWelcomeSoundOn();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setWelcomeSoundOn(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(46, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWelcomeSoundOn(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getWelcomeSoundType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWelcomeSoundType();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setWelcomeSoundType(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(48, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWelcomeSoundType(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getWelcomeLightTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWelcomeLightTime();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setWelcomeLightTime(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(50, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWelcomeLightTime(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getHomeLightTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHomeLightTime();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setHomeLightTime(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(52, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHomeLightTime(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAutoMainBeamControl() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoMainBeamControl();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAutoMainBeamControl(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(54, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAutoMainBeamControl(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAmbtLightGlbOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbtLightGlbOn();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAmbtLightGlbOn(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(56, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbtLightGlbOn(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAmbtLightOpenMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbtLightOpenMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAmbtLightOpenMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(58, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbtLightOpenMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAmbtLightDrvMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbtLightDrvMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAmbtLightDrvMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(60, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbtLightDrvMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAmbtLightColor() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbtLightColor();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAmbtLightColor(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(62, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbtLightColor(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAmbtLightBrightness() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(63, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbtLightBrightness();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAmbtLightBrightness(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(64, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbtLightBrightness(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAmbtLightBreathingOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(65, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbtLightBreathingOn();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAmbtLightBreathingOn(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(66, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbtLightBreathingOn(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAmbtLightWlcmOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbtLightWlcmOn();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAmbtLightWlcmOn(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(68, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbtLightWlcmOn(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAmbtLightWlcmMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbtLightWlcmMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAmbtLightWlcmMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(70, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmbtLightWlcmMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getStartLinkage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStartLinkage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setStartLinkage(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(72, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setStartLinkage(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getVoiceLinkage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceLinkage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setVoiceLinkage(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(74, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVoiceLinkage(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getMusicLinkage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(75, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicLinkage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setMusicLinkage(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(76, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMusicLinkage(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getSpeedAsstSlifWarning() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpeedAsstSlifWarning();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setSpeedAsstSlifWarning(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(78, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSpeedAsstSlifWarning(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getSpeedAsstMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpeedAsstMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setSpeedAsstMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(80, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSpeedAsstMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAccTjaMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(81, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAccTjaMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAccTjaMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(82, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAccTjaMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLaneKeepingAsstMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(83, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLaneKeepingAsstMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLaneKeepingAsstMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(84, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLaneKeepingAsstMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLaneKeepingAsstSen() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(85, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLaneKeepingAsstSen();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLaneKeepingAsstSen(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(86, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLaneKeepingAsstSen(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLaneKeepingWarningSound() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(87, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLaneKeepingWarningSound();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLaneKeepingWarningSound(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(88, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLaneKeepingWarningSound(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLaneKeepingVibration() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(89, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLaneKeepingVibration();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLaneKeepingVibration(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(90, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLaneKeepingVibration(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLkaSysFailure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(91, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLkaSysFailure();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLdwSysFailure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(92, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLdwSysFailure();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getTrafficJamAsstOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(93, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTrafficJamAsstOn();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setTrafficJamAsstOn(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(94, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTrafficJamAsstOn(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getFcwAlarmMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(95, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFcwAlarmMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setFcwAlarmMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(96, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFcwAlarmMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getFcwAutoBrakeMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(97, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFcwAutoBrakeMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setFcwAutoBrakeMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(98, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFcwAutoBrakeMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getFcwSensitivity() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(99, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFcwSensitivity();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setFcwSensitivity(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(100, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFcwSensitivity(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getFcwSysFailure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(101, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFcwSysFailure();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAutoEmergencyBraking() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(102, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoEmergencyBraking();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAutoEmergencyBraking(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(103, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAutoEmergencyBraking(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getRearDriveAsstSys() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(104, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRearDriveAsstSys();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setRearDriveAsstSys(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(105, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRearDriveAsstSys(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getBlindSpotDetection() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(106, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBlindSpotDetection();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setBlindSpotDetection(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(107, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBlindSpotDetection(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLaneChangeAsst() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(108, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLaneChangeAsst();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLaneChangeAsst(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(109, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLaneChangeAsst(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getRearCrossTrafficSys() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(110, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRearCrossTrafficSys();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setRearCrossTrafficSys(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(111, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRearCrossTrafficSys(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getRearCollisionWarning() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(112, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRearCollisionWarning();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setRearCollisionWarning(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(113, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRearCollisionWarning(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getRctbSysFailure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(114, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRctbSysFailure();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getRdaSysStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(115, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRdaSysStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getParkingWarning() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(116, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getParkingWarning();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setParkingWarning(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(117, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setParkingWarning(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getUnsteadyDrivingWarning() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(118, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUnsteadyDrivingWarning();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setUnsteadyDrivingWarning(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(119, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUnsteadyDrivingWarning(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getUnsteadyDrivingWarningSen() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(120, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUnsteadyDrivingWarningSen();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setUnsteadyDrivingWarningSen(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(121, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUnsteadyDrivingWarningSen(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getUdwSysFailure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(122, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUdwSysFailure();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getDriverMonitorSys() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(123, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDriverMonitorSys();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setDriverMonitorSys(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(124, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDriverMonitorSys(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getDriverMonitorSysWarningSound() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getDriverMonitorSysWarningSound, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDriverMonitorSysWarningSound();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setDriverMonitorSysWarningSound(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setDriverMonitorSysWarningSound, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDriverMonitorSysWarningSound(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getDriverMonitorSysVibration() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getDriverMonitorSysVibration, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDriverMonitorSysVibration();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setDriverMonitorSysVibration(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(128, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDriverMonitorSysVibration(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getDriveMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getDriveMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDriveMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setDriveMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setDriveMode, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDriveMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getElectricPowertrainLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getElectricPowertrainLevel, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElectricPowertrainLevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setElectricPowertrainLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setElectricPowertrainLevel, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setElectricPowertrainLevel(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getSteeringLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSteeringLevel, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSteeringLevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setSteeringLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setSteeringLevel, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSteeringLevel(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getBrakePedalLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBrakePedalLevel, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBrakePedalLevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setBrakePedalLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setBrakePedalLevel, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBrakePedalLevel(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getRegenerativeBrakeSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getRegenerativeBrakeSwitch, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRegenerativeBrakeSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setRegenerativeBrakeSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setRegenerativeBrakeSwitch, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRegenerativeBrakeSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getPsgSafetyAirbagOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getPsgSafetyAirbagOn, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgSafetyAirbagOn();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getPsgSafetyAirbagStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getPsgSafetyAirbagStatus, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsgSafetyAirbagStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setPsgSafetyAirbagOn(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setPsgSafetyAirbagOn, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsgSafetyAirbagOn(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public VehicleSettingBean getVehicleSettingStatus() throws RemoteException {
                VehicleSettingBean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getVehicleSettingStatus, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleSettingStatus();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VehicleSettingBean.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getSdmAvailableStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSdmAvailableStatus, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSdmAvailableStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLastWelcomeLightTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLastWelcomeLightTime, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastWelcomeLightTime();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLastHomeLightTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLastHomeLightTime, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastHomeLightTime();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLastAmbtLightOpenMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLastAmbtLightOpenMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastAmbtLightOpenMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLastLaneKeepingAsstMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLastLaneKeepingAsstMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastLaneKeepingAsstMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getRdaAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getRdaAvlbly, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRdaAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getWelcomeSoundAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getWelcomeSoundAvlbly, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWelcomeSoundAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAmbtLightAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAmbtLightAvlbly, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmbtLightAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getBusOffStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBusOffStatus, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBusOffStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLampHomeOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLampHomeOn, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLampHomeOn();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLampHomeOn(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setLampHomeOn, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLampHomeOn(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLampWelcomeOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLampWelcomeOn, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLampWelcomeOn();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLampWelcomeOn(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setLampWelcomeOn, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLampWelcomeOn(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getCarSearchFeedback() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getCarSearchFeedback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarSearchFeedback();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setCarSearchFeedback(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setCarSearchFeedback, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCarSearchFeedback(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getFarewellLightMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getFarewellLightMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFarewellLightMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setFarewellLightMode(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setFarewellLightMode, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFarewellLightMode(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getRegenerativeLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getRegenerativeLevel, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRegenerativeLevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setRegenerativeLevel(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setRegenerativeLevel, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRegenerativeLevel(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getRegenerativeLevelDisable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getRegenerativeLevelDisable, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRegenerativeLevelDisable();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getSignalPedal() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSignalPedal, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSignalPedal();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setSignalPedal(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setSignalPedal, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSignalPedal(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getSignalPedalLnhbReg() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSignalPedalLnhbReg, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSignalPedalLnhbReg();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLongerEndurance() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLongerEndurance, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLongerEndurance();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLongerEndurance(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setLongerEndurance, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLongerEndurance(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLongerEnduranceRecommend() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLongerEnduranceRecommend, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLongerEnduranceRecommend();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLongerEnduranceDisable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLongerEnduranceDisable, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLongerEnduranceDisable();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getBrakeToStandstill() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBrakeToStandstill, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBrakeToStandstill();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setBrakeToStandstill(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setBrakeToStandstill, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBrakeToStandstill(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getPowerModeSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getPowerModeSwitch, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPowerModeSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setPowerModeSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setPowerModeSwitch, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPowerModeSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getAutoHoldSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAutoHoldSwitch, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoHoldSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setAutoHoldSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setAutoHoldSwitch, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAutoHoldSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getCarSearchFeedbackSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getCarSearchFeedbackSwitch, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarSearchFeedbackSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setCarSearchFeedbackSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setCarSearchFeedbackSwitch, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCarSearchFeedbackSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLightRearFogSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLightRearFogSwitch, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLightRearFogSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLightRearFogSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setLightRearFogSwitch, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLightRearFogSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getTowingModeSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTowingModeSwitch, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTowingModeSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setTowingModeSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setTowingModeSwitch, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTowingModeSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getTdmAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTdmAvlbly, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTdmAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getTrlrCnctSts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTrlrCnctSts, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTrlrCnctSts();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setTrlrMdSts(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setTrlrMdSts, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTrlrMdSts(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getPdcChangeSts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getPdcChangeSts, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPdcChangeSts();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setPdcChangeSts(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setPdcChangeSts, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPdcChangeSts(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public int getLightFrontFogSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLightFrontFogSwitch, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLightFrontFogSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleSettingService
            public void setLightFrontFogSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setLightFrontFogSwitch, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLightFrontFogSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVehicleSettingService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IVehicleSettingService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
