package com.saicmotor.sdk.vehiclesettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.IVehicleConditionListener;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleConditionBean;

/* loaded from: classes2.dex */
public interface IVehicleConditionService extends IInterface {
    void addVehicleConditionListener(IVehicleConditionListener iVehicleConditionListener) throws RemoteException;

    int getAcAvlbly() throws RemoteException;

    int getAirConditionConfigCode() throws RemoteException;

    int getAirFilterConfigCode() throws RemoteException;

    int getAirFollowEconConfigCode() throws RemoteException;

    int getAirbagConfigCode() throws RemoteException;

    int getAmplifierType() throws RemoteException;

    int getAnionPurifyConfigCode() throws RemoteException;

    int getApaAvlbly() throws RemoteException;

    int getAqsConfigCode() throws RemoteException;

    int getAutomaticCall() throws RemoteException;

    int getBatteryConfigCode() throws RemoteException;

    int getBcmAvlbly() throws RemoteException;

    int getBlindSpotDetection() throws RemoteException;

    int getBmsAvlbly() throws RemoteException;

    int getCarGear() throws RemoteException;

    int getCarGearV() throws RemoteException;

    float getCarSpeed() throws RemoteException;

    int getConfig360() throws RemoteException;

    int getCrashSignal() throws RemoteException;

    int getDmsConfigCode() throws RemoteException;

    int getDoorAutoLockConfigCode() throws RemoteException;

    int getDriverSeatConfigCode() throws RemoteException;

    int getEcallState() throws RemoteException;

    int getEleBackMirrorConfigCode() throws RemoteException;

    int getEnergyPredictionConfigCode() throws RemoteException;

    boolean getEngModeStackStatus() throws RemoteException;

    int getEngineState() throws RemoteException;

    int getEp21CarConfigCode() throws RemoteException;

    int getForwardCollisionAssistance() throws RemoteException;

    int getFrontRadarControl() throws RemoteException;

    int getFrontSeatConfigCode() throws RemoteException;

    int getFvcmAvlbly() throws RemoteException;

    int getHcuAvlbly() throws RemoteException;

    int getLdwsConfigCode() throws RemoteException;

    int getLightFrontFogConfigCode() throws RemoteException;

    int getLightingSettings() throws RemoteException;

    int getMaintenanceStatus() throws RemoteException;

    int getMileageUnit() throws RemoteException;

    int getMsmAvlbly() throws RemoteException;

    int getNextResetDay() throws RemoteException;

    int getNextResetMileage() throws RemoteException;

    int getOnePedalConfigCode() throws RemoteException;

    int getPedestrianWarningConfigCode() throws RemoteException;

    int getPepsAvlbly() throws RemoteException;

    int getPeripheralKeyModule() throws RemoteException;

    int getPlcmAvlbly() throws RemoteException;

    int getPmDetectionConfigCode() throws RemoteException;

    int getRadarAvlbly() throws RemoteException;

    int getRearWindowAutoConfigCode() throws RemoteException;

    long getResetTime() throws RemoteException;

    int getScsAvlbly() throws RemoteException;

    int getSeatHeatingConfigCode() throws RemoteException;

    int getSpeakerConfigCode() throws RemoteException;

    int getSpeedAssistConfig() throws RemoteException;

    int getSteepDescentControl() throws RemoteException;

    int getSunRoofControlConfigCode() throws RemoteException;

    int getSwcFunctionChangeSwa() throws RemoteException;

    int getTailerElecticConfigCode() throws RemoteException;

    int getTailgateControlConfigCode() throws RemoteException;

    int getTrafficJamAssistance() throws RemoteException;

    int getTransferCaseConfigCode() throws RemoteException;

    int getTransmissionConfigCode() throws RemoteException;

    int getVcuAvlbly() throws RemoteException;

    VehicleConditionBean getVehicleCondition() throws RemoteException;

    int getVehicleExteriorColor() throws RemoteException;

    int getVehicleIgnition() throws RemoteException;

    int getVehicleNameInfo() throws RemoteException;

    int getVehicleType() throws RemoteException;

    String getVinNumber() throws RemoteException;

    int getWindowControl() throws RemoteException;

    void removeVehicleConditionListener(IVehicleConditionListener iVehicleConditionListener) throws RemoteException;

    void resetCarMileageInfo() throws RemoteException;

    public static class Default implements IVehicleConditionService {
        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public void addVehicleConditionListener(IVehicleConditionListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public void removeVehicleConditionListener(IVehicleConditionListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public float getCarSpeed() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getVehicleIgnition() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getCarGear() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public VehicleConditionBean getVehicleCondition() throws RemoteException {
            return null;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getVehicleType() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getEngineState() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getEcallState() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getCrashSignal() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getConfig360() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getAmplifierType() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getAirFollowEconConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getRearWindowAutoConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getTailgateControlConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getEleBackMirrorConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getSeatHeatingConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getPedestrianWarningConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getDriverSeatConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getLightingSettings() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getSpeedAssistConfig() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getBlindSpotDetection() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getTrafficJamAssistance() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getForwardCollisionAssistance() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getWindowControl() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getSteepDescentControl() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getFrontRadarControl() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getAutomaticCall() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getEp21CarConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getAqsConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getLdwsConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getDmsConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getAirbagConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getPmDetectionConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getAirFilterConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getAnionPurifyConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getFrontSeatConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getSunRoofControlConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getDoorAutoLockConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getAirConditionConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getOnePedalConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getTransferCaseConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getEnergyPredictionConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getTailerElecticConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getLightFrontFogConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getTransmissionConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getBatteryConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getSpeakerConfigCode() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getAcAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getBcmAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getScsAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getApaAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getPepsAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getFvcmAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getPlcmAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getBmsAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getHcuAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getRadarAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getMsmAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getVcuAvlbly() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getCarGearV() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getVehicleNameInfo() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public String getVinNumber() throws RemoteException {
            return null;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getVehicleExteriorColor() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getMileageUnit() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getNextResetMileage() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getNextResetDay() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getMaintenanceStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public long getResetTime() throws RemoteException {
            return 0L;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public void resetCarMileageInfo() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public boolean getEngModeStackStatus() throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getPeripheralKeyModule() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
        public int getSwcFunctionChangeSwa() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVehicleConditionService {
        private static final String DESCRIPTOR = "com.saicmotor.sdk.vehiclesettings.IVehicleConditionService";
        static final int TRANSACTION_addVehicleConditionListener = 1;
        static final int TRANSACTION_getAcAvlbly = 49;
        static final int TRANSACTION_getAirConditionConfigCode = 40;
        static final int TRANSACTION_getAirFilterConfigCode = 35;
        static final int TRANSACTION_getAirFollowEconConfigCode = 13;
        static final int TRANSACTION_getAirbagConfigCode = 33;
        static final int TRANSACTION_getAmplifierType = 12;
        static final int TRANSACTION_getAnionPurifyConfigCode = 36;
        static final int TRANSACTION_getApaAvlbly = 52;
        static final int TRANSACTION_getAqsConfigCode = 30;
        static final int TRANSACTION_getAutomaticCall = 28;
        static final int TRANSACTION_getBatteryConfigCode = 47;
        static final int TRANSACTION_getBcmAvlbly = 50;
        static final int TRANSACTION_getBlindSpotDetection = 22;
        static final int TRANSACTION_getBmsAvlbly = 56;
        static final int TRANSACTION_getCarGear = 5;
        static final int TRANSACTION_getCarGearV = 61;
        static final int TRANSACTION_getCarSpeed = 3;
        static final int TRANSACTION_getConfig360 = 11;
        static final int TRANSACTION_getCrashSignal = 10;
        static final int TRANSACTION_getDmsConfigCode = 32;
        static final int TRANSACTION_getDoorAutoLockConfigCode = 39;
        static final int TRANSACTION_getDriverSeatConfigCode = 19;
        static final int TRANSACTION_getEcallState = 9;
        static final int TRANSACTION_getEleBackMirrorConfigCode = 16;
        static final int TRANSACTION_getEnergyPredictionConfigCode = 43;
        static final int TRANSACTION_getEngModeStackStatus = 71;
        static final int TRANSACTION_getEngineState = 8;
        static final int TRANSACTION_getEp21CarConfigCode = 29;
        static final int TRANSACTION_getForwardCollisionAssistance = 24;
        static final int TRANSACTION_getFrontRadarControl = 27;
        static final int TRANSACTION_getFrontSeatConfigCode = 37;
        static final int TRANSACTION_getFvcmAvlbly = 54;
        static final int TRANSACTION_getHcuAvlbly = 57;
        static final int TRANSACTION_getLdwsConfigCode = 31;
        static final int TRANSACTION_getLightFrontFogConfigCode = 45;
        static final int TRANSACTION_getLightingSettings = 20;
        static final int TRANSACTION_getMaintenanceStatus = 68;
        static final int TRANSACTION_getMileageUnit = 65;
        static final int TRANSACTION_getMsmAvlbly = 59;
        static final int TRANSACTION_getNextResetDay = 67;
        static final int TRANSACTION_getNextResetMileage = 66;
        static final int TRANSACTION_getOnePedalConfigCode = 41;
        static final int TRANSACTION_getPedestrianWarningConfigCode = 18;
        static final int TRANSACTION_getPepsAvlbly = 53;
        static final int TRANSACTION_getPeripheralKeyModule = 72;
        static final int TRANSACTION_getPlcmAvlbly = 55;
        static final int TRANSACTION_getPmDetectionConfigCode = 34;
        static final int TRANSACTION_getRadarAvlbly = 58;
        static final int TRANSACTION_getRearWindowAutoConfigCode = 14;
        static final int TRANSACTION_getResetTime = 69;
        static final int TRANSACTION_getScsAvlbly = 51;
        static final int TRANSACTION_getSeatHeatingConfigCode = 17;
        static final int TRANSACTION_getSpeakerConfigCode = 48;
        static final int TRANSACTION_getSpeedAssistConfig = 21;
        static final int TRANSACTION_getSteepDescentControl = 26;
        static final int TRANSACTION_getSunRoofControlConfigCode = 38;
        static final int TRANSACTION_getSwcFunctionChangeSwa = 73;
        static final int TRANSACTION_getTailerElecticConfigCode = 44;
        static final int TRANSACTION_getTailgateControlConfigCode = 15;
        static final int TRANSACTION_getTrafficJamAssistance = 23;
        static final int TRANSACTION_getTransferCaseConfigCode = 42;
        static final int TRANSACTION_getTransmissionConfigCode = 46;
        static final int TRANSACTION_getVcuAvlbly = 60;
        static final int TRANSACTION_getVehicleCondition = 6;
        static final int TRANSACTION_getVehicleExteriorColor = 64;
        static final int TRANSACTION_getVehicleIgnition = 4;
        static final int TRANSACTION_getVehicleNameInfo = 62;
        static final int TRANSACTION_getVehicleType = 7;
        static final int TRANSACTION_getVinNumber = 63;
        static final int TRANSACTION_getWindowControl = 25;
        static final int TRANSACTION_removeVehicleConditionListener = 2;
        static final int TRANSACTION_resetCarMileageInfo = 70;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVehicleConditionService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IVehicleConditionService)) {
                return (IVehicleConditionService) iin;
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
                    addVehicleConditionListener(IVehicleConditionListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeVehicleConditionListener(IVehicleConditionListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    float carSpeed = getCarSpeed();
                    parcel2.writeNoException();
                    parcel2.writeFloat(carSpeed);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int vehicleIgnition = getVehicleIgnition();
                    parcel2.writeNoException();
                    parcel2.writeInt(vehicleIgnition);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int carGear = getCarGear();
                    parcel2.writeNoException();
                    parcel2.writeInt(carGear);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    VehicleConditionBean vehicleCondition = getVehicleCondition();
                    parcel2.writeNoException();
                    if (vehicleCondition != null) {
                        parcel2.writeInt(1);
                        vehicleCondition.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int vehicleType = getVehicleType();
                    parcel2.writeNoException();
                    parcel2.writeInt(vehicleType);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int engineState = getEngineState();
                    parcel2.writeNoException();
                    parcel2.writeInt(engineState);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ecallState = getEcallState();
                    parcel2.writeNoException();
                    parcel2.writeInt(ecallState);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int crashSignal = getCrashSignal();
                    parcel2.writeNoException();
                    parcel2.writeInt(crashSignal);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int config360 = getConfig360();
                    parcel2.writeNoException();
                    parcel2.writeInt(config360);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int amplifierType = getAmplifierType();
                    parcel2.writeNoException();
                    parcel2.writeInt(amplifierType);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int airFollowEconConfigCode = getAirFollowEconConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(airFollowEconConfigCode);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int rearWindowAutoConfigCode = getRearWindowAutoConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(rearWindowAutoConfigCode);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tailgateControlConfigCode = getTailgateControlConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(tailgateControlConfigCode);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int eleBackMirrorConfigCode = getEleBackMirrorConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(eleBackMirrorConfigCode);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int seatHeatingConfigCode = getSeatHeatingConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(seatHeatingConfigCode);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int pedestrianWarningConfigCode = getPedestrianWarningConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(pedestrianWarningConfigCode);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int driverSeatConfigCode = getDriverSeatConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(driverSeatConfigCode);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lightingSettings = getLightingSettings();
                    parcel2.writeNoException();
                    parcel2.writeInt(lightingSettings);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int speedAssistConfig = getSpeedAssistConfig();
                    parcel2.writeNoException();
                    parcel2.writeInt(speedAssistConfig);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int blindSpotDetection = getBlindSpotDetection();
                    parcel2.writeNoException();
                    parcel2.writeInt(blindSpotDetection);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int trafficJamAssistance = getTrafficJamAssistance();
                    parcel2.writeNoException();
                    parcel2.writeInt(trafficJamAssistance);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int forwardCollisionAssistance = getForwardCollisionAssistance();
                    parcel2.writeNoException();
                    parcel2.writeInt(forwardCollisionAssistance);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int windowControl = getWindowControl();
                    parcel2.writeNoException();
                    parcel2.writeInt(windowControl);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int steepDescentControl = getSteepDescentControl();
                    parcel2.writeNoException();
                    parcel2.writeInt(steepDescentControl);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int frontRadarControl = getFrontRadarControl();
                    parcel2.writeNoException();
                    parcel2.writeInt(frontRadarControl);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    int automaticCall = getAutomaticCall();
                    parcel2.writeNoException();
                    parcel2.writeInt(automaticCall);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ep21CarConfigCode = getEp21CarConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(ep21CarConfigCode);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int aqsConfigCode = getAqsConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(aqsConfigCode);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ldwsConfigCode = getLdwsConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(ldwsConfigCode);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dmsConfigCode = getDmsConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(dmsConfigCode);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    int airbagConfigCode = getAirbagConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(airbagConfigCode);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    int pmDetectionConfigCode = getPmDetectionConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(pmDetectionConfigCode);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    int airFilterConfigCode = getAirFilterConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(airFilterConfigCode);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    int anionPurifyConfigCode = getAnionPurifyConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(anionPurifyConfigCode);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    int frontSeatConfigCode = getFrontSeatConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(frontSeatConfigCode);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sunRoofControlConfigCode = getSunRoofControlConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(sunRoofControlConfigCode);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    int doorAutoLockConfigCode = getDoorAutoLockConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(doorAutoLockConfigCode);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    int airConditionConfigCode = getAirConditionConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(airConditionConfigCode);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    int onePedalConfigCode = getOnePedalConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(onePedalConfigCode);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    int transferCaseConfigCode = getTransferCaseConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(transferCaseConfigCode);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    int energyPredictionConfigCode = getEnergyPredictionConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(energyPredictionConfigCode);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tailerElecticConfigCode = getTailerElecticConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(tailerElecticConfigCode);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lightFrontFogConfigCode = getLightFrontFogConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(lightFrontFogConfigCode);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    int transmissionConfigCode = getTransmissionConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(transmissionConfigCode);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    int batteryConfigCode = getBatteryConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(batteryConfigCode);
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    int speakerConfigCode = getSpeakerConfigCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(speakerConfigCode);
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    int acAvlbly = getAcAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(acAvlbly);
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    int bcmAvlbly = getBcmAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(bcmAvlbly);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    int scsAvlbly = getScsAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(scsAvlbly);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    int apaAvlbly = getApaAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(apaAvlbly);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    int pepsAvlbly = getPepsAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(pepsAvlbly);
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    int fvcmAvlbly = getFvcmAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(fvcmAvlbly);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    int plcmAvlbly = getPlcmAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(plcmAvlbly);
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    int bmsAvlbly = getBmsAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(bmsAvlbly);
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hcuAvlbly = getHcuAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(hcuAvlbly);
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    int radarAvlbly = getRadarAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(radarAvlbly);
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    int msmAvlbly = getMsmAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(msmAvlbly);
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    int vcuAvlbly = getVcuAvlbly();
                    parcel2.writeNoException();
                    parcel2.writeInt(vcuAvlbly);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    int carGearV = getCarGearV();
                    parcel2.writeNoException();
                    parcel2.writeInt(carGearV);
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    int vehicleNameInfo = getVehicleNameInfo();
                    parcel2.writeNoException();
                    parcel2.writeInt(vehicleNameInfo);
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    String vinNumber = getVinNumber();
                    parcel2.writeNoException();
                    parcel2.writeString(vinNumber);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    int vehicleExteriorColor = getVehicleExteriorColor();
                    parcel2.writeNoException();
                    parcel2.writeInt(vehicleExteriorColor);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    int mileageUnit = getMileageUnit();
                    parcel2.writeNoException();
                    parcel2.writeInt(mileageUnit);
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    int nextResetMileage = getNextResetMileage();
                    parcel2.writeNoException();
                    parcel2.writeInt(nextResetMileage);
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    int nextResetDay = getNextResetDay();
                    parcel2.writeNoException();
                    parcel2.writeInt(nextResetDay);
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    int maintenanceStatus = getMaintenanceStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(maintenanceStatus);
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    long resetTime = getResetTime();
                    parcel2.writeNoException();
                    parcel2.writeLong(resetTime);
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetCarMileageInfo();
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean engModeStackStatus = getEngModeStackStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(engModeStackStatus ? 1 : 0);
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    int peripheralKeyModule = getPeripheralKeyModule();
                    parcel2.writeNoException();
                    parcel2.writeInt(peripheralKeyModule);
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    int swcFunctionChangeSwa = getSwcFunctionChangeSwa();
                    parcel2.writeNoException();
                    parcel2.writeInt(swcFunctionChangeSwa);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IVehicleConditionService {
            public static IVehicleConditionService sDefaultImpl;
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

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public void addVehicleConditionListener(IVehicleConditionListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addVehicleConditionListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public void removeVehicleConditionListener(IVehicleConditionListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeVehicleConditionListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public float getCarSpeed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarSpeed();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getVehicleIgnition() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleIgnition();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getCarGear() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarGear();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public VehicleConditionBean getVehicleCondition() throws RemoteException {
                VehicleConditionBean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleCondition();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VehicleConditionBean.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getVehicleType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleType();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getEngineState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEngineState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getEcallState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEcallState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getCrashSignal() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCrashSignal();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getConfig360() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfig360();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getAmplifierType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAmplifierType();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getAirFollowEconConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAirFollowEconConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getRearWindowAutoConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRearWindowAutoConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getTailgateControlConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTailgateControlConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getEleBackMirrorConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEleBackMirrorConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getSeatHeatingConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeatHeatingConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getPedestrianWarningConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPedestrianWarningConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getDriverSeatConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDriverSeatConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getLightingSettings() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLightingSettings();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getSpeedAssistConfig() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpeedAssistConfig();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getBlindSpotDetection() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
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

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getTrafficJamAssistance() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTrafficJamAssistance();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getForwardCollisionAssistance() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getForwardCollisionAssistance();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getWindowControl() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowControl();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getSteepDescentControl() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSteepDescentControl();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getFrontRadarControl() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFrontRadarControl();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getAutomaticCall() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutomaticCall();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getEp21CarConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEp21CarConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getAqsConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAqsConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getLdwsConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLdwsConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getDmsConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDmsConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getAirbagConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAirbagConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getPmDetectionConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPmDetectionConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getAirFilterConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAirFilterConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getAnionPurifyConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAnionPurifyConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getFrontSeatConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFrontSeatConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getSunRoofControlConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSunRoofControlConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getDoorAutoLockConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDoorAutoLockConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getAirConditionConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAirConditionConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getOnePedalConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOnePedalConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getTransferCaseConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTransferCaseConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getEnergyPredictionConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnergyPredictionConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getTailerElecticConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTailerElecticConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getLightFrontFogConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLightFrontFogConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getTransmissionConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTransmissionConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getBatteryConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBatteryConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getSpeakerConfigCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpeakerConfigCode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getAcAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAcAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getBcmAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBcmAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getScsAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getScsAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getApaAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApaAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getPepsAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPepsAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getFvcmAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFvcmAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getPlcmAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlcmAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getBmsAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBmsAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getHcuAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHcuAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getRadarAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(58, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadarAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getMsmAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMsmAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getVcuAvlbly() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(60, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVcuAvlbly();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getCarGearV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarGearV();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getVehicleNameInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(62, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleNameInfo();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public String getVinNumber() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(63, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVinNumber();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getVehicleExteriorColor() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleExteriorColor();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getMileageUnit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(65, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMileageUnit();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getNextResetMileage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNextResetMileage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getNextResetDay() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNextResetDay();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getMaintenanceStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMaintenanceStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public long getResetTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getResetTime();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public void resetCarMileageInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(70, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetCarMileageInfo();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public boolean getEngModeStackStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEngModeStackStatus();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getPeripheralKeyModule() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPeripheralKeyModule();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleConditionService
            public int getSwcFunctionChangeSwa() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSwcFunctionChangeSwa();
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

        public static boolean setDefaultImpl(IVehicleConditionService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IVehicleConditionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
