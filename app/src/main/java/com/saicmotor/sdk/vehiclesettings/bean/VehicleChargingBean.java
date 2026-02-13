package com.saicmotor.sdk.vehiclesettings.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class VehicleChargingBean implements Parcelable, Cloneable {
    public static final Parcelable.Creator<VehicleChargingBean> CREATOR = new Parcelable.Creator<VehicleChargingBean>() { // from class: com.saicmotor.sdk.vehiclesettings.bean.VehicleChargingBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VehicleChargingBean createFromParcel(Parcel in) {
            return new VehicleChargingBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VehicleChargingBean[] newArray(int size) {
            return new VehicleChargingBean[size];
        }
    };
    private static final int DEFALUT_CHARGING_CLOSE_SOC = 7;
    private static final int DEFALUT_CHARGING_CURRENT = 4;
    private static final int DEFALUT_CHRG_START_HOUR = 22;
    private static final int DEFALUT_CHRG_STOP_HOUR = 6;
    private static final int DEFALUT_DISCHARGING_CLOSE_SOC = 4;
    private static final int DEFALUT_ELECTRIC_QUANTITY = 100;
    private static final int DEFALUT_ELEC_CSUMP_PER_SIZE = 50;
    private float mAcCurrent;
    private float mAcVoltage;
    private float mAccConsumptionAfterCharge;
    private float mAccConsumptionAfterStart;
    private float mActualChargingCurrent;
    private boolean mActualChargingCurrentV;
    private int mChargingClosePredictMileage;
    private boolean mChargingClosePredictMileageV;
    private int mChargingCloseSoc;
    private int mChargingControlSwitch;
    private int mChargingCurrent;
    private int mChargingLockSwitch;
    private int mChargingStatus;
    private int mChargingStopReason;
    private int mChrgngDoorPos;
    private int mClstrDistUnit;
    private float mCurrentElectricQuantity;
    private boolean mCurrentElectricQuantityV;
    private int mCurrentEnduranceMileage;
    private boolean mCurrentEnduranceMileageV;
    private int mDischrgClosePredictMileage;
    private int mDischrgClosePredictMileageV;
    private int mDischrgCloseSoc;
    private int mDischrgCloseSocResp;
    private int mDischrgControlStatus;
    private int mDischrgControlSwitch;
    private int mDrivingBatteryHeat;
    private float mElecCsumpPerKm;
    private String mElecCsumpPerKmList;
    private int mElecCsumpPerKmV;
    private float mElecCsumpPerKmh;
    private String mElecCsumpPerKmhList;
    private int mElecCsumpPerKmhV;
    private int mElectricityLevel;
    private int mEnergyFlowInfo;
    private float mExpectedCurrent;
    private boolean mExpectedCurrentV;
    private int mMarkedSignalId;
    private float mPowerBatteryVol;
    private int mPowerBatteryVolV;
    private int mPredictChargingTime;
    private boolean mPredictChargingTimeV;
    private int mPredictDischrgTime;
    private int mPredictDischrgTimeV;
    private int mReserChrgAdpPileType;
    private int mReserChrgControl;
    private int mReserChrgStartHour;
    private int mReserChrgStartMinute;
    private int mReserChrgStopHour;
    private int mReserChrgStopMinute;
    private int mSuspendDischrgReason;
    private int mSwrnngInfo;
    private int mSwrnngInfoPv;
    private int mSwrnngInfoRc;
    private float mTotalConsumptionAfterCharge;
    private float mTotalConsumptionAfterStart;
    private float mTotalRegenEnrgAfterCharge;
    private float mTotalRegenEnrgAfterStart;
    private float mTotalRegenRngAfterCharge;
    private float mTotalRegenRngAfterStart;
    private int mWirelessChargeType;
    private int mWrnngInfo;
    private int mWrnngInfoPv;
    private int mWrnngInfoRc;

    public VehicleChargingBean() {
        this.mMarkedSignalId = 0;
        this.mCurrentElectricQuantity = 100.0f;
        this.mChargingCurrent = 4;
        this.mChargingCloseSoc = 7;
        this.mChargingLockSwitch = 2;
        this.mChargingControlSwitch = 2;
        this.mReserChrgControl = 2;
        this.mReserChrgStartHour = 22;
        this.mReserChrgStartMinute = 0;
        this.mReserChrgStopHour = 6;
        this.mReserChrgStopMinute = 0;
        this.mDrivingBatteryHeat = 2;
        this.mReserChrgAdpPileType = 2;
        this.mDischrgCloseSoc = 4;
        this.mDischrgCloseSocResp = 0;
        this.mDischrgControlSwitch = 2;
        this.mPredictDischrgTimeV = 1;
        this.mDischrgClosePredictMileageV = 1;
        this.mPowerBatteryVol = 0.0f;
        this.mPowerBatteryVolV = 1;
        this.mElecCsumpPerKm = 0.0f;
        this.mElecCsumpPerKmV = 1;
        this.mElecCsumpPerKmList = "";
        this.mElecCsumpPerKmh = 0.0f;
        this.mElecCsumpPerKmhV = 1;
        this.mElecCsumpPerKmhList = "";
        this.mClstrDistUnit = 0;
        this.mCurrentElectricQuantityV = true;
        this.mCurrentEnduranceMileageV = true;
        this.mPredictChargingTimeV = true;
        this.mChargingClosePredictMileageV = true;
        this.mActualChargingCurrentV = true;
        this.mExpectedCurrentV = true;
        this.mWirelessChargeType = 0;
        this.mWrnngInfo = 0;
        this.mWrnngInfoPv = 0;
        this.mWrnngInfoRc = 0;
        this.mSwrnngInfo = 0;
        this.mSwrnngInfoPv = 0;
        this.mSwrnngInfoRc = 0;
        this.mChrgngDoorPos = 0;
        this.mElectricityLevel = 0;
    }

    protected VehicleChargingBean(Parcel in) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        this.mMarkedSignalId = 0;
        this.mCurrentElectricQuantity = 100.0f;
        this.mChargingCurrent = 4;
        this.mChargingCloseSoc = 7;
        this.mChargingLockSwitch = 2;
        this.mChargingControlSwitch = 2;
        this.mReserChrgControl = 2;
        this.mReserChrgStartHour = 22;
        this.mReserChrgStartMinute = 0;
        this.mReserChrgStopHour = 6;
        this.mReserChrgStopMinute = 0;
        this.mDrivingBatteryHeat = 2;
        this.mReserChrgAdpPileType = 2;
        this.mDischrgCloseSoc = 4;
        this.mDischrgCloseSocResp = 0;
        this.mDischrgControlSwitch = 2;
        this.mPredictDischrgTimeV = 1;
        this.mDischrgClosePredictMileageV = 1;
        this.mPowerBatteryVol = 0.0f;
        this.mPowerBatteryVolV = 1;
        this.mElecCsumpPerKm = 0.0f;
        this.mElecCsumpPerKmV = 1;
        this.mElecCsumpPerKmList = "";
        this.mElecCsumpPerKmh = 0.0f;
        this.mElecCsumpPerKmhV = 1;
        this.mElecCsumpPerKmhList = "";
        this.mClstrDistUnit = 0;
        this.mCurrentElectricQuantityV = true;
        this.mCurrentEnduranceMileageV = true;
        this.mPredictChargingTimeV = true;
        this.mChargingClosePredictMileageV = true;
        this.mActualChargingCurrentV = true;
        this.mExpectedCurrentV = true;
        this.mWirelessChargeType = 0;
        this.mWrnngInfo = 0;
        this.mWrnngInfoPv = 0;
        this.mWrnngInfoRc = 0;
        this.mSwrnngInfo = 0;
        this.mSwrnngInfoPv = 0;
        this.mSwrnngInfoRc = 0;
        this.mChrgngDoorPos = 0;
        this.mElectricityLevel = 0;
        this.mMarkedSignalId = in.readInt();
        this.mChargingStatus = in.readInt();
        this.mChargingStopReason = in.readInt();
        this.mCurrentElectricQuantity = in.readFloat();
        this.mCurrentEnduranceMileage = in.readInt();
        this.mPredictChargingTime = in.readInt();
        this.mChargingClosePredictMileage = in.readInt();
        this.mChargingCurrent = in.readInt();
        this.mActualChargingCurrent = in.readFloat();
        this.mExpectedCurrent = in.readFloat();
        this.mChargingCloseSoc = in.readInt();
        this.mChargingLockSwitch = in.readInt();
        this.mChargingControlSwitch = in.readInt();
        this.mReserChrgControl = in.readInt();
        this.mReserChrgStartHour = in.readInt();
        this.mReserChrgStartMinute = in.readInt();
        this.mReserChrgStopHour = in.readInt();
        this.mReserChrgStopMinute = in.readInt();
        this.mDrivingBatteryHeat = in.readInt();
        this.mReserChrgAdpPileType = in.readInt();
        this.mPredictDischrgTime = in.readInt();
        this.mSuspendDischrgReason = in.readInt();
        this.mDischrgClosePredictMileage = in.readInt();
        this.mDischrgCloseSoc = in.readInt();
        this.mDischrgCloseSocResp = in.readInt();
        this.mDischrgControlSwitch = in.readInt();
        this.mDischrgControlStatus = in.readInt();
        this.mPredictDischrgTimeV = in.readInt();
        this.mDischrgClosePredictMileageV = in.readInt();
        this.mPowerBatteryVol = in.readFloat();
        this.mPowerBatteryVolV = in.readInt();
        this.mElecCsumpPerKm = in.readFloat();
        this.mElecCsumpPerKmV = in.readInt();
        this.mElecCsumpPerKmList = in.readString();
        this.mElecCsumpPerKmh = in.readFloat();
        this.mElecCsumpPerKmhV = in.readInt();
        this.mElecCsumpPerKmhList = in.readString();
        this.mEnergyFlowInfo = in.readInt();
        this.mAccConsumptionAfterCharge = in.readFloat();
        this.mAccConsumptionAfterStart = in.readFloat();
        this.mTotalConsumptionAfterCharge = in.readFloat();
        this.mTotalConsumptionAfterStart = in.readFloat();
        this.mTotalRegenEnrgAfterCharge = in.readFloat();
        this.mTotalRegenEnrgAfterStart = in.readFloat();
        this.mTotalRegenRngAfterCharge = in.readFloat();
        this.mTotalRegenRngAfterStart = in.readFloat();
        this.mClstrDistUnit = in.readInt();
        if (in.readByte() == 0) {
            z = false;
        } else {
            z = true;
        }
        this.mCurrentElectricQuantityV = z;
        if (in.readByte() == 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.mCurrentEnduranceMileageV = z2;
        if (in.readByte() == 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.mPredictChargingTimeV = z3;
        if (in.readByte() == 0) {
            z4 = false;
        } else {
            z4 = true;
        }
        this.mChargingClosePredictMileageV = z4;
        if (in.readByte() == 0) {
            z5 = false;
        } else {
            z5 = true;
        }
        this.mActualChargingCurrentV = z5;
        this.mExpectedCurrentV = in.readByte() != 0;
        this.mWirelessChargeType = in.readInt();
        this.mWrnngInfo = in.readInt();
        this.mWrnngInfoPv = in.readInt();
        this.mWrnngInfoRc = in.readInt();
        this.mSwrnngInfo = in.readInt();
        this.mSwrnngInfoPv = in.readInt();
        this.mSwrnngInfoRc = in.readInt();
        this.mChrgngDoorPos = in.readInt();
        this.mAcCurrent = in.readFloat();
        this.mAcVoltage = in.readFloat();
        this.mElectricityLevel = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mMarkedSignalId);
        parcel.writeInt(this.mChargingStatus);
        parcel.writeInt(this.mChargingStopReason);
        parcel.writeFloat(this.mCurrentElectricQuantity);
        parcel.writeInt(this.mCurrentEnduranceMileage);
        parcel.writeInt(this.mPredictChargingTime);
        parcel.writeInt(this.mChargingClosePredictMileage);
        parcel.writeInt(this.mChargingCurrent);
        parcel.writeFloat(this.mActualChargingCurrent);
        parcel.writeFloat(this.mExpectedCurrent);
        parcel.writeInt(this.mChargingCloseSoc);
        parcel.writeInt(this.mChargingLockSwitch);
        parcel.writeInt(this.mChargingControlSwitch);
        parcel.writeInt(this.mReserChrgControl);
        parcel.writeInt(this.mReserChrgStartHour);
        parcel.writeInt(this.mReserChrgStartMinute);
        parcel.writeInt(this.mReserChrgStopHour);
        parcel.writeInt(this.mReserChrgStopMinute);
        parcel.writeInt(this.mDrivingBatteryHeat);
        parcel.writeInt(this.mReserChrgAdpPileType);
        parcel.writeInt(this.mPredictDischrgTime);
        parcel.writeInt(this.mSuspendDischrgReason);
        parcel.writeInt(this.mDischrgClosePredictMileage);
        parcel.writeInt(this.mDischrgCloseSoc);
        parcel.writeInt(this.mDischrgCloseSocResp);
        parcel.writeInt(this.mDischrgControlSwitch);
        parcel.writeInt(this.mDischrgControlStatus);
        parcel.writeInt(this.mPredictDischrgTimeV);
        parcel.writeInt(this.mDischrgClosePredictMileageV);
        parcel.writeFloat(this.mPowerBatteryVol);
        parcel.writeInt(this.mPowerBatteryVolV);
        parcel.writeFloat(this.mElecCsumpPerKm);
        parcel.writeInt(this.mElecCsumpPerKmV);
        parcel.writeString(this.mElecCsumpPerKmList);
        parcel.writeFloat(this.mElecCsumpPerKmh);
        parcel.writeInt(this.mElecCsumpPerKmhV);
        parcel.writeString(this.mElecCsumpPerKmhList);
        parcel.writeInt(this.mEnergyFlowInfo);
        parcel.writeFloat(this.mAccConsumptionAfterCharge);
        parcel.writeFloat(this.mAccConsumptionAfterStart);
        parcel.writeFloat(this.mTotalConsumptionAfterCharge);
        parcel.writeFloat(this.mTotalConsumptionAfterStart);
        parcel.writeFloat(this.mTotalRegenEnrgAfterCharge);
        parcel.writeFloat(this.mTotalRegenEnrgAfterStart);
        parcel.writeFloat(this.mTotalRegenRngAfterCharge);
        parcel.writeFloat(this.mTotalRegenRngAfterStart);
        parcel.writeInt(this.mClstrDistUnit);
        parcel.writeByte(this.mCurrentElectricQuantityV ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mCurrentEnduranceMileageV ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mPredictChargingTimeV ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mChargingClosePredictMileageV ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mActualChargingCurrentV ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mExpectedCurrentV ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mWirelessChargeType);
        parcel.writeInt(this.mWrnngInfo);
        parcel.writeInt(this.mWrnngInfoPv);
        parcel.writeInt(this.mWrnngInfoRc);
        parcel.writeInt(this.mSwrnngInfo);
        parcel.writeInt(this.mSwrnngInfoPv);
        parcel.writeInt(this.mSwrnngInfoRc);
        parcel.writeInt(this.mChrgngDoorPos);
        parcel.writeFloat(this.mAcCurrent);
        parcel.writeFloat(this.mAcVoltage);
        parcel.writeInt(this.mElectricityLevel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public VehicleChargingBean m7clone() throws CloneNotSupportedException {
        return (VehicleChargingBean) super.clone();
    }

    public String toString() {
        return "VehicleChargingBean{mMarkedSignalId=" + this.mMarkedSignalId + ", mChargingStatus=" + this.mChargingStatus + ", mChargingStopReason=" + this.mChargingStopReason + ", mCurrentElectricQuantity=" + this.mCurrentElectricQuantity + ", mCurrentEnduranceMileage=" + this.mCurrentEnduranceMileage + ", mPredictChargingTime=" + this.mPredictChargingTime + ", mChargingClosePredictMileage=" + this.mChargingClosePredictMileage + ", mChargingCurrent=" + this.mChargingCurrent + ", mActualChargingCurrent=" + this.mActualChargingCurrent + ", mExpectedCurrent=" + this.mExpectedCurrent + ", mChargingCloseSoc=" + this.mChargingCloseSoc + ", mChargingLockSwitch=" + this.mChargingLockSwitch + ", mChargingControlSwitch=" + this.mChargingControlSwitch + ", mReserChrgControl=" + this.mReserChrgControl + ", mReserChrgStartHour=" + this.mReserChrgStartHour + ", mReserChrgStartMinute=" + this.mReserChrgStartMinute + ", mReserChrgStopHour=" + this.mReserChrgStopHour + ", mReserChrgStopMinute=" + this.mReserChrgStopMinute + ", mDrivingBatteryHeat=" + this.mDrivingBatteryHeat + ", mReserChrgAdpPileType=" + this.mReserChrgAdpPileType + ", mPredictDischrgTime=" + this.mPredictDischrgTime + ", mSuspendDischrgReason=" + this.mSuspendDischrgReason + ", mDischrgClosePredictMileage=" + this.mDischrgClosePredictMileage + ", mDischrgCloseSoc=" + this.mDischrgCloseSoc + ", mDischrgCloseSocResp=" + this.mDischrgCloseSocResp + ", mDischrgControlSwitch=" + this.mDischrgControlSwitch + ", mDischrgControlStatus=" + this.mDischrgControlStatus + ", mPredictDischrgTimeV=" + this.mPredictDischrgTimeV + ", mDischrgClosePredictMileageV=" + this.mDischrgClosePredictMileageV + ", mPowerBatteryVol=" + this.mPowerBatteryVol + ", mPowerBatteryVolV=" + this.mPowerBatteryVolV + ", mElecCsumpPerKm=" + this.mElecCsumpPerKm + ", mElecCsumpPerKmV=" + this.mElecCsumpPerKmV + ", mElecCsumpPerKmList=" + this.mElecCsumpPerKmList + ", mElecCsumpPerKmh=" + this.mElecCsumpPerKmh + ", mElecCsumpPerKmhV=" + this.mElecCsumpPerKmhV + ", mElecCsumpPerKmhList=" + this.mElecCsumpPerKmhList + ", mEnergyFlowInfo=" + this.mEnergyFlowInfo + ", mAccConsumptionAfterCharge=" + this.mAccConsumptionAfterCharge + ", mAccConsumptionAfterStart=" + this.mAccConsumptionAfterStart + ", mTotalConsumptionAfterCharge=" + this.mTotalConsumptionAfterCharge + ", mTotalConsumptionAfterStart=" + this.mTotalConsumptionAfterStart + ", mTotalRegenEnrgAfterCharge=" + this.mTotalRegenEnrgAfterCharge + ", mTotalRegenEnrgAfterStart=" + this.mTotalRegenEnrgAfterStart + ", mTotalRegenRngAfterCharge=" + this.mTotalRegenRngAfterCharge + ", mTotalRegenRngAfterStart=" + this.mTotalRegenRngAfterStart + ", mClstrDistUnit=" + this.mClstrDistUnit + ", mCurrentElectricQuantityV=" + this.mCurrentElectricQuantityV + ", mCurrentEnduranceMileageV=" + this.mCurrentEnduranceMileageV + ", mPredictChargingTimeV=" + this.mPredictChargingTimeV + ", mChargingClosePredictMileageV=" + this.mChargingClosePredictMileageV + ", mActualChargingCurrentV=" + this.mActualChargingCurrentV + ", mExpectedCurrentV=" + this.mExpectedCurrentV + ", mWirelessChargeType=" + this.mWirelessChargeType + ", mElectricityLevel=" + this.mElectricityLevel + '}';
    }

    public int getMarkedSignalId() {
        return this.mMarkedSignalId;
    }

    public void setMarkedSignalId(int id) {
        this.mMarkedSignalId = id;
    }

    public int getChargingStatus() {
        return this.mChargingStatus;
    }

    public void setChargingStatus(int chargingStatus) {
        this.mChargingStatus = chargingStatus;
    }

    public int getChargingStopReason() {
        return this.mChargingStopReason;
    }

    public void setChargingStopReason(int chargingStopReason) {
        this.mChargingStopReason = chargingStopReason;
    }

    public float getCurrentElectricQuantity() {
        return this.mCurrentElectricQuantity;
    }

    public void setCurrentElectricQuantity(float electricQuantity) {
        this.mCurrentElectricQuantity = electricQuantity;
    }

    public int getCurrentEnduranceMileage() {
        return this.mCurrentEnduranceMileage;
    }

    public void setCurrentEnduranceMileage(int enduranceMileage) {
        this.mCurrentEnduranceMileage = enduranceMileage;
    }

    public int getPredictChargingTime() {
        return this.mPredictChargingTime;
    }

    public void setPredictChargingTime(int predictChargingTime) {
        this.mPredictChargingTime = predictChargingTime;
    }

    public int getChargingClosePredictMileage() {
        return this.mChargingClosePredictMileage;
    }

    public void setChargingClosePredictMileage(int chargingPredictMileage) {
        this.mChargingClosePredictMileage = chargingPredictMileage;
    }

    public int getChargingCurrent() {
        return this.mChargingCurrent;
    }

    public void setChargingCurrent(int chargingCurrent) {
        this.mChargingCurrent = chargingCurrent;
    }

    public float getActualChargingCurrent() {
        return this.mActualChargingCurrent;
    }

    public void setActualChargingCurrent(float actualChargingCurrent) {
        this.mActualChargingCurrent = actualChargingCurrent;
    }

    public float getExpectedCurrent() {
        return this.mExpectedCurrent;
    }

    public void setExpectedCurrent(float expectedCurrent) {
        this.mExpectedCurrent = expectedCurrent;
    }

    public int getChargingCloseSoc() {
        return this.mChargingCloseSoc;
    }

    public void setChargingCloseSoc(int chargingCloseSoc) {
        this.mChargingCloseSoc = chargingCloseSoc;
    }

    public int getChargingLockSwitch() {
        return this.mChargingLockSwitch;
    }

    public void setChargingLockSwitch(int chargingLockSwitch) {
        this.mChargingLockSwitch = chargingLockSwitch;
    }

    public int getChargingControlSwitch() {
        return this.mChargingControlSwitch;
    }

    public void setChargingControlSwitch(int chargingControlSwitch) {
        this.mChargingControlSwitch = chargingControlSwitch;
    }

    public int getReserChrgControl() {
        return this.mReserChrgControl;
    }

    public void setReserChrgControl(int reserChrgControl) {
        this.mReserChrgControl = reserChrgControl;
    }

    public int getReserChrgStartHour() {
        return this.mReserChrgStartHour;
    }

    public void setReserChrgStartHour(int reserChrgStartHour) {
        this.mReserChrgStartHour = reserChrgStartHour;
    }

    public int getReserChrgStartMinute() {
        return this.mReserChrgStartMinute;
    }

    public void setReserChrgStartMinute(int reserChrgStartMinute) {
        this.mReserChrgStartMinute = reserChrgStartMinute;
    }

    public int getReserChrgStopHour() {
        return this.mReserChrgStopHour;
    }

    public void setReserChrgStopHour(int reserChrgStopHour) {
        this.mReserChrgStopHour = reserChrgStopHour;
    }

    public int getReserChrgStopMinute() {
        return this.mReserChrgStopMinute;
    }

    public void setReserChrgStopMinute(int reserChrgStopMinute) {
        this.mReserChrgStopMinute = reserChrgStopMinute;
    }

    public int getDrivingBatteryHeat() {
        return this.mDrivingBatteryHeat;
    }

    public void setDrivingBatteryHeat(int drivingBatteryHeat) {
        this.mDrivingBatteryHeat = drivingBatteryHeat;
    }

    public int getReserChrgAdpPileType() {
        return this.mReserChrgAdpPileType;
    }

    public void setReserChrgAdpPileType(int reserChrgAdpPileType) {
        this.mReserChrgAdpPileType = reserChrgAdpPileType;
    }

    public int getPredictDischrgTime() {
        return this.mPredictDischrgTime;
    }

    public void setPredictDischrgTime(int predictDischrgTime) {
        this.mPredictDischrgTime = predictDischrgTime;
    }

    public int getSuspendDischrgReason() {
        return this.mSuspendDischrgReason;
    }

    public void setSuspendDischrgReason(int SuspendDischrgTime) {
        this.mSuspendDischrgReason = SuspendDischrgTime;
    }

    public int getDischrgClosePredictMileage() {
        return this.mDischrgClosePredictMileage;
    }

    public void setDischrgClosePredictMileage(int dischrgClosePredictMileage) {
        this.mDischrgClosePredictMileage = dischrgClosePredictMileage;
    }

    public int getDischrgCloseSoc() {
        return this.mDischrgCloseSoc;
    }

    public void setDischrgCloseSoc(int dischrgCloseSoc) {
        this.mDischrgCloseSoc = dischrgCloseSoc;
    }

    public int getDischrgCloseSocResp() {
        return this.mDischrgCloseSocResp;
    }

    public void setDischrgCloseSocResp(int dischrgCloseSocResp) {
        this.mDischrgCloseSocResp = dischrgCloseSocResp;
    }

    public int getDischrgControlSwitch() {
        return this.mDischrgControlSwitch;
    }

    public void setDischrgControlSwitch(int dischrgControlSwitch) {
        this.mDischrgControlSwitch = dischrgControlSwitch;
    }

    public int getDischrgControlStatus() {
        return this.mDischrgControlStatus;
    }

    public void setDischrgControlStatus(int dischrgControlStatus) {
        this.mDischrgControlStatus = dischrgControlStatus;
    }

    public int getPredictDischrgTimeV() {
        return this.mPredictDischrgTimeV;
    }

    public void setPredictDischrgTimeV(int predictDischrgTimeV) {
        this.mPredictDischrgTimeV = predictDischrgTimeV;
    }

    public int getDischrgClosePredictMileageV() {
        return this.mDischrgClosePredictMileageV;
    }

    public void setDischrgClosePredictMileageV(int dischrgClosePredictMileageV) {
        this.mDischrgClosePredictMileageV = dischrgClosePredictMileageV;
    }

    public float getPowerBatteryVol() {
        return this.mPowerBatteryVol;
    }

    public void setPowerBatteryVol(float powerBatteryVol) {
        this.mPowerBatteryVol = powerBatteryVol;
    }

    public int getPowerBatteryVolV() {
        return this.mPowerBatteryVolV;
    }

    public void setPowerBatteryVolV(int powerBatteryVolV) {
        this.mPowerBatteryVolV = powerBatteryVolV;
    }

    public float getElecCsumpPerKm() {
        return this.mElecCsumpPerKm;
    }

    public void setElecCsumpPerKm(float elecCsumpPerKm) {
        this.mElecCsumpPerKm = elecCsumpPerKm;
    }

    public int getElecCsumpPerKmV() {
        return this.mElecCsumpPerKmV;
    }

    public void setElecCsumpPerKmV(int elecCsumpPerKmV) {
        this.mElecCsumpPerKmV = elecCsumpPerKmV;
    }

    public String getElecCsumpPerKmList() {
        return this.mElecCsumpPerKmList;
    }

    public void setElecCsumpPerKmList(String elecCsumpPerKmList) {
        this.mElecCsumpPerKmList = elecCsumpPerKmList;
    }

    public float getElecCsumpPerKmh() {
        return this.mElecCsumpPerKmh;
    }

    public void setElecCsumpPerKmh(float elecCsumpPerKmh) {
        this.mElecCsumpPerKmh = elecCsumpPerKmh;
    }

    public int getElecCsumpPerKmhV() {
        return this.mElecCsumpPerKmhV;
    }

    public void setElecCsumpPerKmhV(int elecCsumpPerKmhV) {
        this.mElecCsumpPerKmhV = elecCsumpPerKmhV;
    }

    public String getElecCsumpPerKmhList() {
        return this.mElecCsumpPerKmhList;
    }

    public void setElecCsumpPerKmhList(String elecCsumpPerKmhList) {
        this.mElecCsumpPerKmhList = elecCsumpPerKmhList;
    }

    public int getEnergyFlowInfo() {
        return this.mEnergyFlowInfo;
    }

    public void setEnergyFlowInfo(int energyFlowInfo) {
        this.mEnergyFlowInfo = energyFlowInfo;
    }

    public float getAccConsumptionAfterCharge() {
        return this.mAccConsumptionAfterCharge;
    }

    public void setAccConsumptionAfterCharge(float accConsumptionAfterCharge) {
        this.mAccConsumptionAfterCharge = accConsumptionAfterCharge;
    }

    public float getAccConsumptionAfterStart() {
        return this.mAccConsumptionAfterStart;
    }

    public void setAccConsumptionAfterStart(float accConsumptionAfterStart) {
        this.mAccConsumptionAfterStart = accConsumptionAfterStart;
    }

    public float getTotalConsumptionAfterCharge() {
        return this.mTotalConsumptionAfterCharge;
    }

    public void setTotalConsumptionAfterCharge(float totalConsumptionAfterCharge) {
        this.mTotalConsumptionAfterCharge = totalConsumptionAfterCharge;
    }

    public float getTotalConsumptionAfterStart() {
        return this.mTotalConsumptionAfterStart;
    }

    public void setTotalConsumptionAfterStart(float totalConsumptionAfterStart) {
        this.mTotalConsumptionAfterStart = totalConsumptionAfterStart;
    }

    public float getTotalRegenEnrgAfterCharge() {
        return this.mTotalRegenEnrgAfterCharge;
    }

    public void setTotalRegenEnrgAfterCharge(float totalRegenEnrgAfterCharge) {
        this.mTotalRegenEnrgAfterCharge = totalRegenEnrgAfterCharge;
    }

    public float getTotalRegenEnrgAfterStart() {
        return this.mTotalRegenEnrgAfterStart;
    }

    public void setTotalRegenEnrgAfterStart(float totalRegenEnrgAfterStart) {
        this.mTotalRegenEnrgAfterStart = totalRegenEnrgAfterStart;
    }

    public float getTotalRegenRngAfterCharge() {
        return this.mTotalRegenRngAfterCharge;
    }

    public void setTotalRegenRngAfterCharge(float totalRegenRngAfterCharge) {
        this.mTotalRegenRngAfterCharge = totalRegenRngAfterCharge;
    }

    public float getTotalRegenRngAfterStart() {
        return this.mTotalRegenRngAfterStart;
    }

    public void setTotalRegenRngAfterStart(float totalRegenRngAfterStart) {
        this.mTotalRegenRngAfterStart = totalRegenRngAfterStart;
    }

    public int getClstrDistUnit() {
        return this.mClstrDistUnit;
    }

    public void setClstrDistUnit(int clstrDistUnit) {
        this.mClstrDistUnit = clstrDistUnit;
    }

    public boolean isCurrentElectricQuantityV() {
        return this.mCurrentElectricQuantityV;
    }

    public void setCurrentElectricQuantityV(boolean currentElectricQuantityV) {
        this.mCurrentElectricQuantityV = currentElectricQuantityV;
    }

    public boolean isCurrentEnduranceMileageV() {
        return this.mCurrentEnduranceMileageV;
    }

    public void setCurrentEnduranceMileageV(boolean currentEnduranceMileageV) {
        this.mCurrentEnduranceMileageV = currentEnduranceMileageV;
    }

    public boolean isPredictChargingTimeV() {
        return this.mPredictChargingTimeV;
    }

    public void setPredictChargingTimeV(boolean predictChargingTimeV) {
        this.mPredictChargingTimeV = predictChargingTimeV;
    }

    public boolean isChargingClosePredictMileageV() {
        return this.mChargingClosePredictMileageV;
    }

    public void setChargingClosePredictMileageV(boolean chargingClosePredictMileageV) {
        this.mChargingClosePredictMileageV = chargingClosePredictMileageV;
    }

    public boolean isActualChargingCurrentV() {
        return this.mActualChargingCurrentV;
    }

    public void setActualChargingCurrentV(boolean actualChargingCurrentV) {
        this.mActualChargingCurrentV = actualChargingCurrentV;
    }

    public boolean isExpectedCurrentV() {
        return this.mExpectedCurrentV;
    }

    public void setExpectedCurrentV(boolean expectedCurrentV) {
        this.mExpectedCurrentV = expectedCurrentV;
    }

    public int getWirelessChargeType() {
        return this.mWirelessChargeType;
    }

    public void setWirelessChargeType(int wirelessChargeType) {
        this.mWirelessChargeType = wirelessChargeType;
    }

    public int getWrnngInfo() {
        return this.mWrnngInfo;
    }

    public void setWrnngInfo(int wrnngInfo) {
        this.mWrnngInfo = wrnngInfo;
    }

    public int getWrnngInfoPv() {
        return this.mWrnngInfoPv;
    }

    public void setWrnngInfoPv(int wrnngInfoPv) {
        this.mWrnngInfoPv = wrnngInfoPv;
    }

    public int getWrnngInfoRc() {
        return this.mWrnngInfoRc;
    }

    public void setWrnngInfoRc(int wrnngInfoRc) {
        this.mWrnngInfoRc = wrnngInfoRc;
    }

    public int getSwrnngInfo() {
        return this.mSwrnngInfo;
    }

    public void setSwrnngInfo(int swrnngInfo) {
        this.mSwrnngInfo = swrnngInfo;
    }

    public int getSwrnngInfoPv() {
        return this.mSwrnngInfoPv;
    }

    public void setSwrnngInfoPv(int swrnngInfoPv) {
        this.mSwrnngInfoPv = swrnngInfoPv;
    }

    public int getSwrnngInfoRc() {
        return this.mSwrnngInfoRc;
    }

    public void setSwrnngInfoRc(int swrnngInfoRc) {
        this.mSwrnngInfoRc = swrnngInfoRc;
    }

    public int getChrgngDoorPos() {
        return this.mChrgngDoorPos;
    }

    public void setChrgngDoorPos(int chrgngDoor) {
        this.mChrgngDoorPos = chrgngDoor;
    }

    public float getAcCurrent() {
        return this.mAcCurrent;
    }

    public void setAcCurrent(float AcCurrent) {
        this.mAcCurrent = AcCurrent;
    }

    public float getAcVoltage() {
        return this.mAcVoltage;
    }

    public void setAcVoltage(float AcVoltaget) {
        this.mAcVoltage = AcVoltaget;
    }

    public int getElectricityLevel() {
        return this.mElectricityLevel;
    }

    public void setElectricityLevel(int electricityLevel) {
        this.mElectricityLevel = electricityLevel;
    }
}
