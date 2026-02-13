package com.saicmotor.sdk.vehiclesettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.IBleStateCallback;
import com.saicmotor.sdk.vehiclesettings.IVehicleChargingListener;
import com.saicmotor.sdk.vehiclesettings.bean.ChargingPile;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleChargingBean;
import java.util.List;

/* loaded from: classes2.dex */
public interface IVehicleChargingService extends IInterface {
    void addChargingPile(String str) throws RemoteException;

    void addVehicleChargingListener(IVehicleChargingListener iVehicleChargingListener) throws RemoteException;

    void connectChargingPile(ChargingPile chargingPile) throws RemoteException;

    void deleteChargingPile(ChargingPile chargingPile) throws RemoteException;

    void disconnectChargingPile(ChargingPile chargingPile) throws RemoteException;

    float getAcCurrent() throws RemoteException;

    float getAcVoltage() throws RemoteException;

    float getAccConsumptionAfterCharge() throws RemoteException;

    float getAccConsumptionAfterStart() throws RemoteException;

    float getActualChargingCurrent() throws RemoteException;

    boolean getActualChargingCurrentV() throws RemoteException;

    boolean getBtEnabled() throws RemoteException;

    int getChargingClosePredictMileage() throws RemoteException;

    boolean getChargingClosePredictMileageV() throws RemoteException;

    int getChargingCloseSoc() throws RemoteException;

    int getChargingControlSwitch() throws RemoteException;

    int getChargingCurrent() throws RemoteException;

    int getChargingLockSwitch() throws RemoteException;

    List<ChargingPile> getChargingPileList() throws RemoteException;

    int getChargingStatus() throws RemoteException;

    int getChargingStopReason() throws RemoteException;

    int getChrgngDoorPos() throws RemoteException;

    int getClstrDistUnit() throws RemoteException;

    int getConnectionState(ChargingPile chargingPile) throws RemoteException;

    float getCurrentElectricQuantity() throws RemoteException;

    boolean getCurrentElectricQuantityV() throws RemoteException;

    int getCurrentEnduranceMileage() throws RemoteException;

    boolean getCurrentEnduranceMileageV() throws RemoteException;

    int getDischrgClosePredictMileage() throws RemoteException;

    int getDischrgClosePredictMileageV() throws RemoteException;

    int getDischrgCloseSoc() throws RemoteException;

    int getDischrgCloseSocResp() throws RemoteException;

    int getDischrgControlStatus() throws RemoteException;

    int getDischrgControlSwitch() throws RemoteException;

    int getDrivingBatteryHeat() throws RemoteException;

    float getElecCsumpPerKm() throws RemoteException;

    String getElecCsumpPerKmList() throws RemoteException;

    int getElecCsumpPerKmV() throws RemoteException;

    float getElecCsumpPerKmh() throws RemoteException;

    String getElecCsumpPerKmhList() throws RemoteException;

    int getElecCsumpPerKmhV() throws RemoteException;

    int getElectricityLevel() throws RemoteException;

    int getEnergyFlowInfo() throws RemoteException;

    float getExpectedCurrent() throws RemoteException;

    boolean getExpectedCurrentV() throws RemoteException;

    float getPowerBatteryVol() throws RemoteException;

    int getPowerBatteryVolV() throws RemoteException;

    int getPredictChargingTime() throws RemoteException;

    boolean getPredictChargingTimeV() throws RemoteException;

    int getPredictDischrgTime() throws RemoteException;

    int getPredictDischrgTimeV() throws RemoteException;

    int getReserChrgAdpPileType() throws RemoteException;

    int getReserChrgControl() throws RemoteException;

    int getReserChrgStartHour() throws RemoteException;

    int getReserChrgStartMinute() throws RemoteException;

    int getReserChrgStopHour() throws RemoteException;

    int getReserChrgStopMinute() throws RemoteException;

    int getSuspendDischrgReason() throws RemoteException;

    int getSwrnngInfo() throws RemoteException;

    int getSwrnngInfoPv() throws RemoteException;

    int getSwrnngInfoRc() throws RemoteException;

    float getTotalConsumptionAfterCharge() throws RemoteException;

    float getTotalConsumptionAfterStart() throws RemoteException;

    float getTotalRegenEnrgAfterCharge() throws RemoteException;

    float getTotalRegenEnrgAfterStart() throws RemoteException;

    float getTotalRegenRngAfterCharge() throws RemoteException;

    float getTotalRegenRngAfterStart() throws RemoteException;

    VehicleChargingBean getVehicleChargingStatus() throws RemoteException;

    int getWirelessChargeStat() throws RemoteException;

    int getWrnngInfo() throws RemoteException;

    int getWrnngInfoPv() throws RemoteException;

    int getWrnngInfoRc() throws RemoteException;

    void registerBleStateCallback(IBleStateCallback iBleStateCallback) throws RemoteException;

    void removeVehicleChargingListener(IVehicleChargingListener iVehicleChargingListener) throws RemoteException;

    void setBtEnabled(boolean z) throws RemoteException;

    void setChargingCloseSoc(int i) throws RemoteException;

    void setChargingControlSwitch(int i) throws RemoteException;

    void setChargingCurrent(int i) throws RemoteException;

    void setChargingLockSwitch(int i) throws RemoteException;

    void setClstrDistUnit(int i) throws RemoteException;

    void setDischrgCloseSoc(int i) throws RemoteException;

    void setDischrgControlSwitch(int i) throws RemoteException;

    void setDrivingBatteryHeat(int i) throws RemoteException;

    void setReserChrgAdpPileType(int i) throws RemoteException;

    void setReserChrgControl(int i) throws RemoteException;

    void setReserChrgStartHour(int i) throws RemoteException;

    void setReserChrgStartMinute(int i) throws RemoteException;

    void setReserChrgStopHour(int i) throws RemoteException;

    void setReserChrgStopMinute(int i) throws RemoteException;

    void startScan(boolean z) throws RemoteException;

    void stopScan() throws RemoteException;

    void unregisterBleStateCallback(IBleStateCallback iBleStateCallback) throws RemoteException;

    public static class Default implements IVehicleChargingService {
        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void addVehicleChargingListener(IVehicleChargingListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void removeVehicleChargingListener(IVehicleChargingListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getCurrentElectricQuantity() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getCurrentEnduranceMileage() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getPredictChargingTime() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getChargingClosePredictMileage() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getActualChargingCurrent() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getExpectedCurrent() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getChargingStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getChargingStopReason() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getChargingCurrent() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setChargingCurrent(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getChargingCloseSoc() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setChargingCloseSoc(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getChargingLockSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setChargingLockSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getChargingControlSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setChargingControlSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getReserChrgControl() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setReserChrgControl(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getReserChrgStartHour() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setReserChrgStartHour(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getReserChrgStartMinute() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setReserChrgStartMinute(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getReserChrgStopHour() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setReserChrgStopHour(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getReserChrgStopMinute() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setReserChrgStopMinute(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public boolean getCurrentElectricQuantityV() throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public boolean getCurrentEnduranceMileageV() throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public boolean getPredictChargingTimeV() throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public boolean getChargingClosePredictMileageV() throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public boolean getActualChargingCurrentV() throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public boolean getExpectedCurrentV() throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getPredictDischrgTimeV() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getDischrgClosePredictMileageV() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getDrivingBatteryHeat() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setDrivingBatteryHeat(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getReserChrgAdpPileType() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setReserChrgAdpPileType(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getPredictDischrgTime() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getDischrgClosePredictMileage() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getDischrgCloseSoc() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setDischrgCloseSoc(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getDischrgCloseSocResp() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getDischrgControlSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setDischrgControlSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getDischrgControlStatus() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public VehicleChargingBean getVehicleChargingStatus() throws RemoteException {
            return null;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void registerBleStateCallback(IBleStateCallback callback) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void unregisterBleStateCallback(IBleStateCallback callback) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public boolean getBtEnabled() throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setBtEnabled(boolean enable) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void startScan(boolean autoConnect) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void stopScan() throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getConnectionState(ChargingPile device) throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void connectChargingPile(ChargingPile device) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void disconnectChargingPile(ChargingPile device) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void addChargingPile(String name) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void deleteChargingPile(ChargingPile device) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public List<ChargingPile> getChargingPileList() throws RemoteException {
            return null;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getWirelessChargeStat() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getSuspendDischrgReason() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getPowerBatteryVol() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getPowerBatteryVolV() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getElecCsumpPerKm() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getElecCsumpPerKmV() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public String getElecCsumpPerKmList() throws RemoteException {
            return null;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getElecCsumpPerKmh() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getElecCsumpPerKmhV() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public String getElecCsumpPerKmhList() throws RemoteException {
            return null;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getEnergyFlowInfo() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getAccConsumptionAfterCharge() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getAccConsumptionAfterStart() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getTotalConsumptionAfterCharge() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getTotalConsumptionAfterStart() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getTotalRegenEnrgAfterCharge() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getTotalRegenEnrgAfterStart() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getTotalRegenRngAfterCharge() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getTotalRegenRngAfterStart() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public void setClstrDistUnit(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getClstrDistUnit() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getWrnngInfo() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getWrnngInfoPv() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getWrnngInfoRc() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getSwrnngInfo() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getSwrnngInfoPv() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getSwrnngInfoRc() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getChrgngDoorPos() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getAcCurrent() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public float getAcVoltage() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
        public int getElectricityLevel() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVehicleChargingService {
        private static final String DESCRIPTOR = "com.saicmotor.sdk.vehiclesettings.IVehicleChargingService";
        static final int TRANSACTION_addChargingPile = 59;
        static final int TRANSACTION_addVehicleChargingListener = 1;
        static final int TRANSACTION_connectChargingPile = 57;
        static final int TRANSACTION_deleteChargingPile = 60;
        static final int TRANSACTION_disconnectChargingPile = 58;
        static final int TRANSACTION_getAcCurrent = 90;
        static final int TRANSACTION_getAcVoltage = 91;
        static final int TRANSACTION_getAccConsumptionAfterCharge = 73;
        static final int TRANSACTION_getAccConsumptionAfterStart = 74;
        static final int TRANSACTION_getActualChargingCurrent = 7;
        static final int TRANSACTION_getActualChargingCurrentV = 33;
        static final int TRANSACTION_getBtEnabled = 52;
        static final int TRANSACTION_getChargingClosePredictMileage = 6;
        static final int TRANSACTION_getChargingClosePredictMileageV = 32;
        static final int TRANSACTION_getChargingCloseSoc = 13;
        static final int TRANSACTION_getChargingControlSwitch = 17;
        static final int TRANSACTION_getChargingCurrent = 11;
        static final int TRANSACTION_getChargingLockSwitch = 15;
        static final int TRANSACTION_getChargingPileList = 61;
        static final int TRANSACTION_getChargingStatus = 9;
        static final int TRANSACTION_getChargingStopReason = 10;
        static final int TRANSACTION_getChrgngDoorPos = 89;
        static final int TRANSACTION_getClstrDistUnit = 82;
        static final int TRANSACTION_getConnectionState = 56;
        static final int TRANSACTION_getCurrentElectricQuantity = 3;
        static final int TRANSACTION_getCurrentElectricQuantityV = 29;
        static final int TRANSACTION_getCurrentEnduranceMileage = 4;
        static final int TRANSACTION_getCurrentEnduranceMileageV = 30;
        static final int TRANSACTION_getDischrgClosePredictMileage = 42;
        static final int TRANSACTION_getDischrgClosePredictMileageV = 36;
        static final int TRANSACTION_getDischrgCloseSoc = 43;
        static final int TRANSACTION_getDischrgCloseSocResp = 45;
        static final int TRANSACTION_getDischrgControlStatus = 48;
        static final int TRANSACTION_getDischrgControlSwitch = 46;
        static final int TRANSACTION_getDrivingBatteryHeat = 37;
        static final int TRANSACTION_getElecCsumpPerKm = 66;
        static final int TRANSACTION_getElecCsumpPerKmList = 68;
        static final int TRANSACTION_getElecCsumpPerKmV = 67;
        static final int TRANSACTION_getElecCsumpPerKmh = 69;
        static final int TRANSACTION_getElecCsumpPerKmhList = 71;
        static final int TRANSACTION_getElecCsumpPerKmhV = 70;
        static final int TRANSACTION_getElectricityLevel = 92;
        static final int TRANSACTION_getEnergyFlowInfo = 72;
        static final int TRANSACTION_getExpectedCurrent = 8;
        static final int TRANSACTION_getExpectedCurrentV = 34;
        static final int TRANSACTION_getPowerBatteryVol = 64;
        static final int TRANSACTION_getPowerBatteryVolV = 65;
        static final int TRANSACTION_getPredictChargingTime = 5;
        static final int TRANSACTION_getPredictChargingTimeV = 31;
        static final int TRANSACTION_getPredictDischrgTime = 41;
        static final int TRANSACTION_getPredictDischrgTimeV = 35;
        static final int TRANSACTION_getReserChrgAdpPileType = 39;
        static final int TRANSACTION_getReserChrgControl = 19;
        static final int TRANSACTION_getReserChrgStartHour = 21;
        static final int TRANSACTION_getReserChrgStartMinute = 23;
        static final int TRANSACTION_getReserChrgStopHour = 25;
        static final int TRANSACTION_getReserChrgStopMinute = 27;
        static final int TRANSACTION_getSuspendDischrgReason = 63;
        static final int TRANSACTION_getSwrnngInfo = 86;
        static final int TRANSACTION_getSwrnngInfoPv = 87;
        static final int TRANSACTION_getSwrnngInfoRc = 88;
        static final int TRANSACTION_getTotalConsumptionAfterCharge = 75;
        static final int TRANSACTION_getTotalConsumptionAfterStart = 76;
        static final int TRANSACTION_getTotalRegenEnrgAfterCharge = 77;
        static final int TRANSACTION_getTotalRegenEnrgAfterStart = 78;
        static final int TRANSACTION_getTotalRegenRngAfterCharge = 79;
        static final int TRANSACTION_getTotalRegenRngAfterStart = 80;
        static final int TRANSACTION_getVehicleChargingStatus = 49;
        static final int TRANSACTION_getWirelessChargeStat = 62;
        static final int TRANSACTION_getWrnngInfo = 83;
        static final int TRANSACTION_getWrnngInfoPv = 84;
        static final int TRANSACTION_getWrnngInfoRc = 85;
        static final int TRANSACTION_registerBleStateCallback = 50;
        static final int TRANSACTION_removeVehicleChargingListener = 2;
        static final int TRANSACTION_setBtEnabled = 53;
        static final int TRANSACTION_setChargingCloseSoc = 14;
        static final int TRANSACTION_setChargingControlSwitch = 18;
        static final int TRANSACTION_setChargingCurrent = 12;
        static final int TRANSACTION_setChargingLockSwitch = 16;
        static final int TRANSACTION_setClstrDistUnit = 81;
        static final int TRANSACTION_setDischrgCloseSoc = 44;
        static final int TRANSACTION_setDischrgControlSwitch = 47;
        static final int TRANSACTION_setDrivingBatteryHeat = 38;
        static final int TRANSACTION_setReserChrgAdpPileType = 40;
        static final int TRANSACTION_setReserChrgControl = 20;
        static final int TRANSACTION_setReserChrgStartHour = 22;
        static final int TRANSACTION_setReserChrgStartMinute = 24;
        static final int TRANSACTION_setReserChrgStopHour = 26;
        static final int TRANSACTION_setReserChrgStopMinute = 28;
        static final int TRANSACTION_startScan = 54;
        static final int TRANSACTION_stopScan = 55;
        static final int TRANSACTION_unregisterBleStateCallback = 51;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVehicleChargingService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IVehicleChargingService)) {
                return (IVehicleChargingService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            ChargingPile chargingPileCreateFromParcel;
            ChargingPile chargingPileCreateFromParcel2;
            ChargingPile chargingPileCreateFromParcel3;
            ChargingPile chargingPileCreateFromParcel4;
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    addVehicleChargingListener(IVehicleChargingListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeVehicleChargingListener(IVehicleChargingListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    float currentElectricQuantity = getCurrentElectricQuantity();
                    parcel2.writeNoException();
                    parcel2.writeFloat(currentElectricQuantity);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentEnduranceMileage = getCurrentEnduranceMileage();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentEnduranceMileage);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int predictChargingTime = getPredictChargingTime();
                    parcel2.writeNoException();
                    parcel2.writeInt(predictChargingTime);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int chargingClosePredictMileage = getChargingClosePredictMileage();
                    parcel2.writeNoException();
                    parcel2.writeInt(chargingClosePredictMileage);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    float actualChargingCurrent = getActualChargingCurrent();
                    parcel2.writeNoException();
                    parcel2.writeFloat(actualChargingCurrent);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    float expectedCurrent = getExpectedCurrent();
                    parcel2.writeNoException();
                    parcel2.writeFloat(expectedCurrent);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int chargingStatus = getChargingStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(chargingStatus);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int chargingStopReason = getChargingStopReason();
                    parcel2.writeNoException();
                    parcel2.writeInt(chargingStopReason);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int chargingCurrent = getChargingCurrent();
                    parcel2.writeNoException();
                    parcel2.writeInt(chargingCurrent);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setChargingCurrent(parcel.readInt());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int chargingCloseSoc = getChargingCloseSoc();
                    parcel2.writeNoException();
                    parcel2.writeInt(chargingCloseSoc);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setChargingCloseSoc(parcel.readInt());
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int chargingLockSwitch = getChargingLockSwitch();
                    parcel2.writeNoException();
                    parcel2.writeInt(chargingLockSwitch);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    setChargingLockSwitch(parcel.readInt());
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int chargingControlSwitch = getChargingControlSwitch();
                    parcel2.writeNoException();
                    parcel2.writeInt(chargingControlSwitch);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setChargingControlSwitch(parcel.readInt());
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int reserChrgControl = getReserChrgControl();
                    parcel2.writeNoException();
                    parcel2.writeInt(reserChrgControl);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setReserChrgControl(parcel.readInt());
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int reserChrgStartHour = getReserChrgStartHour();
                    parcel2.writeNoException();
                    parcel2.writeInt(reserChrgStartHour);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    setReserChrgStartHour(parcel.readInt());
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int reserChrgStartMinute = getReserChrgStartMinute();
                    parcel2.writeNoException();
                    parcel2.writeInt(reserChrgStartMinute);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    setReserChrgStartMinute(parcel.readInt());
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int reserChrgStopHour = getReserChrgStopHour();
                    parcel2.writeNoException();
                    parcel2.writeInt(reserChrgStopHour);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    setReserChrgStopHour(parcel.readInt());
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int reserChrgStopMinute = getReserChrgStopMinute();
                    parcel2.writeNoException();
                    parcel2.writeInt(reserChrgStopMinute);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    setReserChrgStopMinute(parcel.readInt());
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean currentElectricQuantityV = getCurrentElectricQuantityV();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentElectricQuantityV ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean currentEnduranceMileageV = getCurrentEnduranceMileageV();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentEnduranceMileageV ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean predictChargingTimeV = getPredictChargingTimeV();
                    parcel2.writeNoException();
                    parcel2.writeInt(predictChargingTimeV ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean chargingClosePredictMileageV = getChargingClosePredictMileageV();
                    parcel2.writeNoException();
                    parcel2.writeInt(chargingClosePredictMileageV ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean actualChargingCurrentV = getActualChargingCurrentV();
                    parcel2.writeNoException();
                    parcel2.writeInt(actualChargingCurrentV ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean expectedCurrentV = getExpectedCurrentV();
                    parcel2.writeNoException();
                    parcel2.writeInt(expectedCurrentV ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    int predictDischrgTimeV = getPredictDischrgTimeV();
                    parcel2.writeNoException();
                    parcel2.writeInt(predictDischrgTimeV);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dischrgClosePredictMileageV = getDischrgClosePredictMileageV();
                    parcel2.writeNoException();
                    parcel2.writeInt(dischrgClosePredictMileageV);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    int drivingBatteryHeat = getDrivingBatteryHeat();
                    parcel2.writeNoException();
                    parcel2.writeInt(drivingBatteryHeat);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDrivingBatteryHeat(parcel.readInt());
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    int reserChrgAdpPileType = getReserChrgAdpPileType();
                    parcel2.writeNoException();
                    parcel2.writeInt(reserChrgAdpPileType);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    setReserChrgAdpPileType(parcel.readInt());
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    int predictDischrgTime = getPredictDischrgTime();
                    parcel2.writeNoException();
                    parcel2.writeInt(predictDischrgTime);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dischrgClosePredictMileage = getDischrgClosePredictMileage();
                    parcel2.writeNoException();
                    parcel2.writeInt(dischrgClosePredictMileage);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dischrgCloseSoc = getDischrgCloseSoc();
                    parcel2.writeNoException();
                    parcel2.writeInt(dischrgCloseSoc);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDischrgCloseSoc(parcel.readInt());
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dischrgCloseSocResp = getDischrgCloseSocResp();
                    parcel2.writeNoException();
                    parcel2.writeInt(dischrgCloseSocResp);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dischrgControlSwitch = getDischrgControlSwitch();
                    parcel2.writeNoException();
                    parcel2.writeInt(dischrgControlSwitch);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDischrgControlSwitch(parcel.readInt());
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dischrgControlStatus = getDischrgControlStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(dischrgControlStatus);
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    VehicleChargingBean vehicleChargingStatus = getVehicleChargingStatus();
                    parcel2.writeNoException();
                    if (vehicleChargingStatus != null) {
                        parcel2.writeInt(1);
                        vehicleChargingStatus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerBleStateCallback(IBleStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterBleStateCallback(IBleStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean btEnabled = getBtEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(btEnabled ? 1 : 0);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBtEnabled(parcel.readInt() != 0);
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    startScan(parcel.readInt() != 0);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopScan();
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        chargingPileCreateFromParcel = ChargingPile.CREATOR.createFromParcel(parcel);
                    } else {
                        chargingPileCreateFromParcel = null;
                    }
                    int connectionState = getConnectionState(chargingPileCreateFromParcel);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionState);
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        chargingPileCreateFromParcel2 = ChargingPile.CREATOR.createFromParcel(parcel);
                    } else {
                        chargingPileCreateFromParcel2 = null;
                    }
                    connectChargingPile(chargingPileCreateFromParcel2);
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        chargingPileCreateFromParcel3 = ChargingPile.CREATOR.createFromParcel(parcel);
                    } else {
                        chargingPileCreateFromParcel3 = null;
                    }
                    disconnectChargingPile(chargingPileCreateFromParcel3);
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    addChargingPile(parcel.readString());
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        chargingPileCreateFromParcel4 = ChargingPile.CREATOR.createFromParcel(parcel);
                    } else {
                        chargingPileCreateFromParcel4 = null;
                    }
                    deleteChargingPile(chargingPileCreateFromParcel4);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ChargingPile> chargingPileList = getChargingPileList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(chargingPileList);
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    int wirelessChargeStat = getWirelessChargeStat();
                    parcel2.writeNoException();
                    parcel2.writeInt(wirelessChargeStat);
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    int suspendDischrgReason = getSuspendDischrgReason();
                    parcel2.writeNoException();
                    parcel2.writeInt(suspendDischrgReason);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    float powerBatteryVol = getPowerBatteryVol();
                    parcel2.writeNoException();
                    parcel2.writeFloat(powerBatteryVol);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    int powerBatteryVolV = getPowerBatteryVolV();
                    parcel2.writeNoException();
                    parcel2.writeInt(powerBatteryVolV);
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    float elecCsumpPerKm = getElecCsumpPerKm();
                    parcel2.writeNoException();
                    parcel2.writeFloat(elecCsumpPerKm);
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    int elecCsumpPerKmV = getElecCsumpPerKmV();
                    parcel2.writeNoException();
                    parcel2.writeInt(elecCsumpPerKmV);
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    String elecCsumpPerKmList = getElecCsumpPerKmList();
                    parcel2.writeNoException();
                    parcel2.writeString(elecCsumpPerKmList);
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    float elecCsumpPerKmh = getElecCsumpPerKmh();
                    parcel2.writeNoException();
                    parcel2.writeFloat(elecCsumpPerKmh);
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    int elecCsumpPerKmhV = getElecCsumpPerKmhV();
                    parcel2.writeNoException();
                    parcel2.writeInt(elecCsumpPerKmhV);
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    String elecCsumpPerKmhList = getElecCsumpPerKmhList();
                    parcel2.writeNoException();
                    parcel2.writeString(elecCsumpPerKmhList);
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    int energyFlowInfo = getEnergyFlowInfo();
                    parcel2.writeNoException();
                    parcel2.writeInt(energyFlowInfo);
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    float accConsumptionAfterCharge = getAccConsumptionAfterCharge();
                    parcel2.writeNoException();
                    parcel2.writeFloat(accConsumptionAfterCharge);
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    float accConsumptionAfterStart = getAccConsumptionAfterStart();
                    parcel2.writeNoException();
                    parcel2.writeFloat(accConsumptionAfterStart);
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    float totalConsumptionAfterCharge = getTotalConsumptionAfterCharge();
                    parcel2.writeNoException();
                    parcel2.writeFloat(totalConsumptionAfterCharge);
                    return true;
                case 76:
                    parcel.enforceInterface(DESCRIPTOR);
                    float totalConsumptionAfterStart = getTotalConsumptionAfterStart();
                    parcel2.writeNoException();
                    parcel2.writeFloat(totalConsumptionAfterStart);
                    return true;
                case 77:
                    parcel.enforceInterface(DESCRIPTOR);
                    float totalRegenEnrgAfterCharge = getTotalRegenEnrgAfterCharge();
                    parcel2.writeNoException();
                    parcel2.writeFloat(totalRegenEnrgAfterCharge);
                    return true;
                case 78:
                    parcel.enforceInterface(DESCRIPTOR);
                    float totalRegenEnrgAfterStart = getTotalRegenEnrgAfterStart();
                    parcel2.writeNoException();
                    parcel2.writeFloat(totalRegenEnrgAfterStart);
                    return true;
                case 79:
                    parcel.enforceInterface(DESCRIPTOR);
                    float totalRegenRngAfterCharge = getTotalRegenRngAfterCharge();
                    parcel2.writeNoException();
                    parcel2.writeFloat(totalRegenRngAfterCharge);
                    return true;
                case 80:
                    parcel.enforceInterface(DESCRIPTOR);
                    float totalRegenRngAfterStart = getTotalRegenRngAfterStart();
                    parcel2.writeNoException();
                    parcel2.writeFloat(totalRegenRngAfterStart);
                    return true;
                case 81:
                    parcel.enforceInterface(DESCRIPTOR);
                    setClstrDistUnit(parcel.readInt());
                    return true;
                case 82:
                    parcel.enforceInterface(DESCRIPTOR);
                    int clstrDistUnit = getClstrDistUnit();
                    parcel2.writeNoException();
                    parcel2.writeInt(clstrDistUnit);
                    return true;
                case 83:
                    parcel.enforceInterface(DESCRIPTOR);
                    int wrnngInfo = getWrnngInfo();
                    parcel2.writeNoException();
                    parcel2.writeInt(wrnngInfo);
                    return true;
                case 84:
                    parcel.enforceInterface(DESCRIPTOR);
                    int wrnngInfoPv = getWrnngInfoPv();
                    parcel2.writeNoException();
                    parcel2.writeInt(wrnngInfoPv);
                    return true;
                case 85:
                    parcel.enforceInterface(DESCRIPTOR);
                    int wrnngInfoRc = getWrnngInfoRc();
                    parcel2.writeNoException();
                    parcel2.writeInt(wrnngInfoRc);
                    return true;
                case 86:
                    parcel.enforceInterface(DESCRIPTOR);
                    int swrnngInfo = getSwrnngInfo();
                    parcel2.writeNoException();
                    parcel2.writeInt(swrnngInfo);
                    return true;
                case 87:
                    parcel.enforceInterface(DESCRIPTOR);
                    int swrnngInfoPv = getSwrnngInfoPv();
                    parcel2.writeNoException();
                    parcel2.writeInt(swrnngInfoPv);
                    return true;
                case 88:
                    parcel.enforceInterface(DESCRIPTOR);
                    int swrnngInfoRc = getSwrnngInfoRc();
                    parcel2.writeNoException();
                    parcel2.writeInt(swrnngInfoRc);
                    return true;
                case 89:
                    parcel.enforceInterface(DESCRIPTOR);
                    int chrgngDoorPos = getChrgngDoorPos();
                    parcel2.writeNoException();
                    parcel2.writeInt(chrgngDoorPos);
                    return true;
                case 90:
                    parcel.enforceInterface(DESCRIPTOR);
                    float acCurrent = getAcCurrent();
                    parcel2.writeNoException();
                    parcel2.writeFloat(acCurrent);
                    return true;
                case 91:
                    parcel.enforceInterface(DESCRIPTOR);
                    float acVoltage = getAcVoltage();
                    parcel2.writeNoException();
                    parcel2.writeFloat(acVoltage);
                    return true;
                case 92:
                    parcel.enforceInterface(DESCRIPTOR);
                    int electricityLevel = getElectricityLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(electricityLevel);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IVehicleChargingService {
            public static IVehicleChargingService sDefaultImpl;
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

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void addVehicleChargingListener(IVehicleChargingListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addVehicleChargingListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void removeVehicleChargingListener(IVehicleChargingListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeVehicleChargingListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getCurrentElectricQuantity() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentElectricQuantity();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getCurrentEnduranceMileage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentEnduranceMileage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getPredictChargingTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPredictChargingTime();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getChargingClosePredictMileage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargingClosePredictMileage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getActualChargingCurrent() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActualChargingCurrent();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getExpectedCurrent() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getExpectedCurrent();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getChargingStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargingStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getChargingStopReason() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargingStopReason();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getChargingCurrent() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargingCurrent();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setChargingCurrent(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setChargingCurrent(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getChargingCloseSoc() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargingCloseSoc();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setChargingCloseSoc(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setChargingCloseSoc(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getChargingLockSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargingLockSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setChargingLockSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setChargingLockSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getChargingControlSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargingControlSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setChargingControlSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setChargingControlSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getReserChrgControl() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReserChrgControl();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setReserChrgControl(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setReserChrgControl(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getReserChrgStartHour() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReserChrgStartHour();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setReserChrgStartHour(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setReserChrgStartHour(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getReserChrgStartMinute() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReserChrgStartMinute();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setReserChrgStartMinute(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setReserChrgStartMinute(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getReserChrgStopHour() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReserChrgStopHour();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setReserChrgStopHour(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(26, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setReserChrgStopHour(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getReserChrgStopMinute() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReserChrgStopMinute();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setReserChrgStopMinute(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(28, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setReserChrgStopMinute(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public boolean getCurrentElectricQuantityV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentElectricQuantityV();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public boolean getCurrentEnduranceMileageV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentEnduranceMileageV();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public boolean getPredictChargingTimeV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPredictChargingTimeV();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public boolean getChargingClosePredictMileageV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargingClosePredictMileageV();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public boolean getActualChargingCurrentV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActualChargingCurrentV();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public boolean getExpectedCurrentV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getExpectedCurrentV();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getPredictDischrgTimeV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPredictDischrgTimeV();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getDischrgClosePredictMileageV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDischrgClosePredictMileageV();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getDrivingBatteryHeat() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrivingBatteryHeat();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setDrivingBatteryHeat(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(38, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDrivingBatteryHeat(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getReserChrgAdpPileType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReserChrgAdpPileType();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setReserChrgAdpPileType(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(40, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setReserChrgAdpPileType(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getPredictDischrgTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPredictDischrgTime();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getDischrgClosePredictMileage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDischrgClosePredictMileage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getDischrgCloseSoc() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDischrgCloseSoc();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setDischrgCloseSoc(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(44, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDischrgCloseSoc(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getDischrgCloseSocResp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDischrgCloseSocResp();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getDischrgControlSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDischrgControlSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setDischrgControlSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(47, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDischrgControlSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getDischrgControlStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDischrgControlStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public VehicleChargingBean getVehicleChargingStatus() throws RemoteException {
                VehicleChargingBean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleChargingStatus();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VehicleChargingBean.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void registerBleStateCallback(IBleStateCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(50, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerBleStateCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void unregisterBleStateCallback(IBleStateCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(51, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterBleStateCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public boolean getBtEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBtEnabled();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setBtEnabled(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(53, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBtEnabled(enable);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void startScan(boolean autoConnect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(autoConnect ? 1 : 0);
                    boolean _status = this.mRemote.transact(54, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startScan(autoConnect);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void stopScan() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(55, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopScan();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getConnectionState(ChargingPile device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionState(device);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void connectChargingPile(ChargingPile device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(57, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connectChargingPile(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void disconnectChargingPile(ChargingPile device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(58, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnectChargingPile(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void addChargingPile(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(59, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addChargingPile(name);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void deleteChargingPile(ChargingPile device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(60, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteChargingPile(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public List<ChargingPile> getChargingPileList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargingPileList();
                    }
                    _reply.readException();
                    List<ChargingPile> _result = _reply.createTypedArrayList(ChargingPile.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getWirelessChargeStat() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(62, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWirelessChargeStat();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getSuspendDischrgReason() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(63, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSuspendDischrgReason();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getPowerBatteryVol() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPowerBatteryVol();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getPowerBatteryVolV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(65, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPowerBatteryVolV();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getElecCsumpPerKm() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElecCsumpPerKm();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getElecCsumpPerKmV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElecCsumpPerKmV();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public String getElecCsumpPerKmList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElecCsumpPerKmList();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getElecCsumpPerKmh() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElecCsumpPerKmh();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getElecCsumpPerKmhV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(70, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElecCsumpPerKmhV();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public String getElecCsumpPerKmhList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElecCsumpPerKmhList();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getEnergyFlowInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnergyFlowInfo();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getAccConsumptionAfterCharge() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAccConsumptionAfterCharge();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getAccConsumptionAfterStart() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(74, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAccConsumptionAfterStart();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getTotalConsumptionAfterCharge() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(75, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTotalConsumptionAfterCharge();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getTotalConsumptionAfterStart() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(76, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTotalConsumptionAfterStart();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getTotalRegenEnrgAfterCharge() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTotalRegenEnrgAfterCharge();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getTotalRegenEnrgAfterStart() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(78, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTotalRegenEnrgAfterStart();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getTotalRegenRngAfterCharge() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTotalRegenRngAfterCharge();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getTotalRegenRngAfterStart() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(80, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTotalRegenRngAfterStart();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public void setClstrDistUnit(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(81, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setClstrDistUnit(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getClstrDistUnit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(82, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getClstrDistUnit();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getWrnngInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(83, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWrnngInfo();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getWrnngInfoPv() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(84, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWrnngInfoPv();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getWrnngInfoRc() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(85, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWrnngInfoRc();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getSwrnngInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(86, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSwrnngInfo();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getSwrnngInfoPv() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(87, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSwrnngInfoPv();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getSwrnngInfoRc() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(88, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSwrnngInfoRc();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getChrgngDoorPos() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(89, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChrgngDoorPos();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getAcCurrent() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(90, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAcCurrent();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public float getAcVoltage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(91, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAcVoltage();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleChargingService
            public int getElectricityLevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(92, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElectricityLevel();
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

        public static boolean setDefaultImpl(IVehicleChargingService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IVehicleChargingService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
