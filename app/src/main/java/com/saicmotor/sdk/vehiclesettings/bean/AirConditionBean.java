package com.saicmotor.sdk.vehiclesettings.bean;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

/* loaded from: classes2.dex */
public class AirConditionBean implements Parcelable, Cloneable {
    public static final Parcelable.Creator<AirConditionBean> CREATOR = new Parcelable.Creator<AirConditionBean>() { // from class: com.saicmotor.sdk.vehiclesettings.bean.AirConditionBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AirConditionBean createFromParcel(Parcel in) {
            return new AirConditionBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AirConditionBean[] newArray(int size) {
            return new AirConditionBean[size];
        }
    };
    private static final int DEFAULT_AIR_VOLUME = 1;
    private int mAcSwitch;
    private int mAirVolumeLevel;
    private int mAnionStatus;
    private int mAutoStatus;
    private int mBackWindowDefroster;
    private int mBlowerDirectionMode;
    private float mDrvLeftMiddleWindOutletAngle;
    private float mDrvLeftWindOutletAngle;
    private int mDrvSeatHeatLevel;
    private int mDrvSeatWindLevel;
    private int mDrvTemp;
    private int mDrvWindOutletAvoidPersonModeStatus;
    private int mDrvWindOutletBlowPersonModeStatus;
    private int mDrvWindOutletOffModeStatus;
    private int mDrvWindOutletSwingModeStatus;
    private int mEconStatus;
    private int mFrontWindowDefroster;
    private int mHvacPowerStatus;
    private int mIsWindOutletDisabled;
    private int mLoopMode;
    private int mMarkedSignalId;
    private int mMemoryBlowerDirectionMode;
    private float mOutCarTemp;
    private int mPm25Concentration;
    private int mPm25Filter;
    private float mPsgRightMiddleWindOutletAngle;
    private float mPsgRightWindOutletAngle;
    private int mPsgSeatHeatLevel;
    private int mPsgSeatWindLevel;
    private int mPsgTemp;
    private int mPsgWindOutletAvoidPersonModeStatus;
    private int mPsgWindOutletBlowPersonModeStatus;
    private int mPsgWindOutletOffModeStatus;
    private int mPsgWindOutletSwingModeStatus;
    private int mSmartBlowerStatus;
    private int mSteeringWheelHeatLevel;
    private int mTempDualZoneOn;
    private int mWindOutletCanStatus;

    public AirConditionBean() {
        this.mAirVolumeLevel = 1;
        this.mIsWindOutletDisabled = -1;
        this.mMemoryBlowerDirectionMode = -1;
        this.mWindOutletCanStatus = 0;
        this.mSteeringWheelHeatLevel = 0;
    }

    protected AirConditionBean(Parcel in) {
        this.mAirVolumeLevel = 1;
        this.mIsWindOutletDisabled = -1;
        this.mMemoryBlowerDirectionMode = -1;
        this.mWindOutletCanStatus = 0;
        this.mSteeringWheelHeatLevel = 0;
        this.mMarkedSignalId = in.readInt();
        this.mHvacPowerStatus = in.readInt();
        this.mAutoStatus = in.readInt();
        this.mAcSwitch = in.readInt();
        this.mLoopMode = in.readInt();
        this.mEconStatus = in.readInt();
        this.mBlowerDirectionMode = in.readInt();
        this.mAirVolumeLevel = in.readInt();
        this.mDrvWindOutletBlowPersonModeStatus = in.readInt();
        this.mDrvWindOutletAvoidPersonModeStatus = in.readInt();
        this.mDrvWindOutletSwingModeStatus = in.readInt();
        this.mDrvWindOutletOffModeStatus = in.readInt();
        this.mDrvLeftWindOutletAngle = in.readFloat();
        this.mDrvLeftMiddleWindOutletAngle = in.readFloat();
        this.mPsgWindOutletBlowPersonModeStatus = in.readInt();
        this.mPsgWindOutletAvoidPersonModeStatus = in.readInt();
        this.mPsgWindOutletSwingModeStatus = in.readInt();
        this.mPsgWindOutletOffModeStatus = in.readInt();
        this.mPsgRightWindOutletAngle = in.readFloat();
        this.mPsgRightMiddleWindOutletAngle = in.readFloat();
        this.mSmartBlowerStatus = in.readInt();
        this.mDrvTemp = in.readInt();
        this.mPsgTemp = in.readInt();
        this.mTempDualZoneOn = in.readInt();
        this.mDrvSeatHeatLevel = in.readInt();
        this.mDrvSeatWindLevel = in.readInt();
        this.mPsgSeatHeatLevel = in.readInt();
        this.mPsgSeatWindLevel = in.readInt();
        this.mOutCarTemp = in.readFloat();
        this.mPm25Concentration = in.readInt();
        this.mPm25Filter = in.readInt();
        this.mAnionStatus = in.readInt();
        this.mMemoryBlowerDirectionMode = in.readInt();
        this.mIsWindOutletDisabled = in.readInt();
        this.mWindOutletCanStatus = in.readInt();
        this.mFrontWindowDefroster = in.readInt();
        this.mBackWindowDefroster = in.readInt();
        this.mSteeringWheelHeatLevel = in.readInt();
    }

    public int getMarkedSignalId() {
        return this.mMarkedSignalId;
    }

    public void setMarkedSignalId(int signalId) {
        this.mMarkedSignalId = signalId;
    }

    public int getHvacPowerStatus() {
        return this.mHvacPowerStatus;
    }

    public void setHvacPowerStatus(int hvacPowerStatus) {
        this.mHvacPowerStatus = hvacPowerStatus;
    }

    public int getAutoStatus() {
        return this.mAutoStatus;
    }

    public void setAutoStatus(int autoStatus) {
        this.mAutoStatus = autoStatus;
    }

    public int getAcSwitch() {
        return this.mAcSwitch;
    }

    public void setAcSwitch(int acSwitch) {
        this.mAcSwitch = acSwitch;
    }

    public int getLoopMode() {
        return this.mLoopMode;
    }

    public void setLoopMode(int loopMode) {
        this.mLoopMode = loopMode;
    }

    public int getEconStatus() {
        return this.mEconStatus;
    }

    public void setEconStatus(int econStatus) {
        this.mEconStatus = econStatus;
    }

    public int getBlowerDirectionMode() {
        return this.mBlowerDirectionMode;
    }

    public void setBlowerDirectionMode(int blowerDirectionMode) {
        this.mBlowerDirectionMode = blowerDirectionMode;
    }

    public int getAirVolumeLevel() {
        return this.mAirVolumeLevel;
    }

    public void setAirVolumeLevel(int airVolumeLevel) {
        this.mAirVolumeLevel = airVolumeLevel;
    }

    public int getDrvWindOutletBlowPersonModeStatus() {
        return this.mDrvWindOutletBlowPersonModeStatus;
    }

    public void setDrvWindOutletBlowPersonModeStatus(int drvWindOutletBlowPersonModeStatus) {
        this.mDrvWindOutletBlowPersonModeStatus = drvWindOutletBlowPersonModeStatus;
    }

    public int getDrvWindOutletAvoidPersonModeStatus() {
        return this.mDrvWindOutletAvoidPersonModeStatus;
    }

    public void setDrvWindOutletAvoidPersonModeStatus(int drvWindOutletAvoidPersonModeStatus) {
        this.mDrvWindOutletAvoidPersonModeStatus = drvWindOutletAvoidPersonModeStatus;
    }

    public int getDrvWindOutletSwingModeStatus() {
        return this.mDrvWindOutletSwingModeStatus;
    }

    public void setDrvWindOutletSwingModeStatus(int drvWindOutletSwingModeStatus) {
        this.mDrvWindOutletSwingModeStatus = drvWindOutletSwingModeStatus;
    }

    public int getDrvWindOutletOffModeStatus() {
        return this.mDrvWindOutletOffModeStatus;
    }

    public void setDrvWindOutletOffModeStatus(int drvWindOutletOffModeStatus) {
        this.mDrvWindOutletOffModeStatus = drvWindOutletOffModeStatus;
    }

    public float getDrvLeftWindOutletAngle() {
        return this.mDrvLeftWindOutletAngle;
    }

    public void setDrvLeftWindOutletAngle(float drvLeftWindOutletAngle) {
        this.mDrvLeftWindOutletAngle = drvLeftWindOutletAngle;
    }

    public float getDrvLeftMiddleWindOutletAngle() {
        return this.mDrvLeftMiddleWindOutletAngle;
    }

    public void setDrvLeftMiddleWindOutletAngle(float drvLeftMiddleWindOutletAngle) {
        this.mDrvLeftMiddleWindOutletAngle = drvLeftMiddleWindOutletAngle;
    }

    public int getPsgWindOutletBlowPersonModeStatus() {
        return this.mPsgWindOutletBlowPersonModeStatus;
    }

    public void setPsgWindOutletBlowPersonModeStatus(int psgWindOutletBlowPersonModeStatus) {
        this.mPsgWindOutletBlowPersonModeStatus = psgWindOutletBlowPersonModeStatus;
    }

    public int getPsgWindOutletAvoidPersonModeStatus() {
        return this.mPsgWindOutletAvoidPersonModeStatus;
    }

    public void setPsgWindOutletAvoidPersonModeStatus(int psgWindOutletAvoidPersonModeStatus) {
        this.mPsgWindOutletAvoidPersonModeStatus = psgWindOutletAvoidPersonModeStatus;
    }

    public int getPsgWindOutletSwingModeStatus() {
        return this.mPsgWindOutletSwingModeStatus;
    }

    public void setPsgWindOutletSwingModeStatus(int psgWindOutletSwingModeStatus) {
        this.mPsgWindOutletSwingModeStatus = psgWindOutletSwingModeStatus;
    }

    public int getPsgWindOutletOffModeStatus() {
        return this.mPsgWindOutletOffModeStatus;
    }

    public void setPsgWindOutletOffModeStatus(int psgWindOutletOffModeStatus) {
        this.mPsgWindOutletOffModeStatus = psgWindOutletOffModeStatus;
    }

    public float getPsgRightWindOutletAngle() {
        return this.mPsgRightWindOutletAngle;
    }

    public void setPsgRightWindOutletAngle(float psgRightWindOutletAngle) {
        this.mPsgRightWindOutletAngle = psgRightWindOutletAngle;
    }

    public float getPsgRightMiddleWindOutletAngle() {
        return this.mPsgRightMiddleWindOutletAngle;
    }

    public void setPsgRightMiddleWindOutletAngle(float psgRightMiddleWindOutletAngle) {
        this.mPsgRightMiddleWindOutletAngle = psgRightMiddleWindOutletAngle;
    }

    public int getSmartBlowerStatus() {
        return this.mSmartBlowerStatus;
    }

    public void setSmartBlowerStatus(int smartBlowerStatus) {
        this.mSmartBlowerStatus = smartBlowerStatus;
    }

    public int getDrvTemp() {
        return this.mDrvTemp;
    }

    public void setDrvTemp(int drvTemp) {
        this.mDrvTemp = drvTemp;
    }

    public int getPsgTemp() {
        return this.mPsgTemp;
    }

    public void setPsgTemp(int psgTemp) {
        this.mPsgTemp = psgTemp;
    }

    public int getTempDualZoneOn() {
        return this.mTempDualZoneOn;
    }

    public void setTempDualZoneOn(int tempDualZoneOn) {
        this.mTempDualZoneOn = tempDualZoneOn;
    }

    public int getDrvSeatHeatLevel() {
        return this.mDrvSeatHeatLevel;
    }

    public void setDrvSeatHeatLevel(int drvSeatHeatLevel) {
        this.mDrvSeatHeatLevel = drvSeatHeatLevel;
    }

    public int getDrvSeatWindLevel() {
        return this.mDrvSeatWindLevel;
    }

    public void setDrvSeatWindLevel(int drvSeatWindLevel) {
        this.mDrvSeatWindLevel = drvSeatWindLevel;
    }

    public int getPsgSeatHeatLevel() {
        return this.mPsgSeatHeatLevel;
    }

    public void setPsgSeatHeatLevel(int psgSeatHeatLevel) {
        this.mPsgSeatHeatLevel = psgSeatHeatLevel;
    }

    public int getPsgSeatWindLevel() {
        return this.mPsgSeatWindLevel;
    }

    public void setPsgSeatWindLevel(int psgSeatWindLevel) {
        this.mPsgSeatWindLevel = psgSeatWindLevel;
    }

    public float getOutCarTemp() {
        return this.mOutCarTemp;
    }

    public void setOutCarTemp(float outCarTemp) {
        this.mOutCarTemp = outCarTemp;
    }

    public int getPm25Concentration() {
        return this.mPm25Concentration;
    }

    public void setPm25Concentration(int pm25Concentration) {
        this.mPm25Concentration = pm25Concentration;
    }

    public int getPm25Filter() {
        return this.mPm25Filter;
    }

    public void setPm25Filter(int pm25Filter) {
        this.mPm25Filter = pm25Filter;
    }

    public int getAnionStatus() {
        return this.mAnionStatus;
    }

    public void setAnionStatus(int anionStatus) {
        this.mAnionStatus = anionStatus;
    }

    public int getMemoryBlowerDirectionMode() {
        return this.mMemoryBlowerDirectionMode;
    }

    public void setMemoryBlowerDirectionMode(int memoryBlowerDirectionMode) {
        this.mMemoryBlowerDirectionMode = memoryBlowerDirectionMode;
    }

    public int getIsWindOutletDisabled() {
        return this.mIsWindOutletDisabled;
    }

    public void setIsWindOutletDisabled(int isWindOutletDisabled) {
        this.mIsWindOutletDisabled = isWindOutletDisabled;
    }

    public int getWindOutletCanStatus() {
        return this.mWindOutletCanStatus;
    }

    public void setWindOutletCanStatus(int windOutletCanStatus) {
        this.mWindOutletCanStatus = windOutletCanStatus;
    }

    public int getFrontWindowDefroster() {
        return this.mFrontWindowDefroster;
    }

    public void setFrontWindowDefroster(int frontWindowDefroster) {
        this.mFrontWindowDefroster = frontWindowDefroster;
    }

    public int getBackWindowDefroster() {
        return this.mBackWindowDefroster;
    }

    public void setBackWindowDefroster(int backWindowDefroster) {
        this.mBackWindowDefroster = backWindowDefroster;
    }

    public int getSteeringWheelHeatLevel() {
        return this.mSteeringWheelHeatLevel;
    }

    public void setSteeringWheelHeat(int steeringWheelHeat) {
        this.mSteeringWheelHeatLevel = steeringWheelHeat;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mMarkedSignalId);
        parcel.writeInt(this.mHvacPowerStatus);
        parcel.writeInt(this.mAutoStatus);
        parcel.writeInt(this.mAcSwitch);
        parcel.writeInt(this.mLoopMode);
        parcel.writeInt(this.mEconStatus);
        parcel.writeInt(this.mBlowerDirectionMode);
        parcel.writeInt(this.mAirVolumeLevel);
        parcel.writeInt(this.mDrvWindOutletBlowPersonModeStatus);
        parcel.writeInt(this.mDrvWindOutletAvoidPersonModeStatus);
        parcel.writeInt(this.mDrvWindOutletSwingModeStatus);
        parcel.writeInt(this.mDrvWindOutletOffModeStatus);
        parcel.writeFloat(this.mDrvLeftWindOutletAngle);
        parcel.writeFloat(this.mDrvLeftMiddleWindOutletAngle);
        parcel.writeInt(this.mPsgWindOutletBlowPersonModeStatus);
        parcel.writeInt(this.mPsgWindOutletAvoidPersonModeStatus);
        parcel.writeInt(this.mPsgWindOutletSwingModeStatus);
        parcel.writeInt(this.mPsgWindOutletOffModeStatus);
        parcel.writeFloat(this.mPsgRightWindOutletAngle);
        parcel.writeFloat(this.mPsgRightMiddleWindOutletAngle);
        parcel.writeInt(this.mSmartBlowerStatus);
        parcel.writeInt(this.mDrvTemp);
        parcel.writeInt(this.mPsgTemp);
        parcel.writeInt(this.mTempDualZoneOn);
        parcel.writeInt(this.mDrvSeatHeatLevel);
        parcel.writeInt(this.mDrvSeatWindLevel);
        parcel.writeInt(this.mPsgSeatHeatLevel);
        parcel.writeInt(this.mPsgSeatWindLevel);
        parcel.writeFloat(this.mOutCarTemp);
        parcel.writeInt(this.mPm25Concentration);
        parcel.writeInt(this.mPm25Filter);
        parcel.writeInt(this.mAnionStatus);
        parcel.writeInt(this.mMemoryBlowerDirectionMode);
        parcel.writeInt(this.mIsWindOutletDisabled);
        parcel.writeInt(this.mWindOutletCanStatus);
        parcel.writeInt(this.mFrontWindowDefroster);
        parcel.writeInt(this.mBackWindowDefroster);
        parcel.writeInt(this.mSteeringWheelHeatLevel);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AirConditionBean m6clone() throws CloneNotSupportedException {
        return (AirConditionBean) super.clone();
    }

    @NonNull
    public String toString() {
        return "AirConditionBean {mMarkedSignalId='" + this.mMarkedSignalId + "', mHvacPowerStatus = " + this.mHvacPowerStatus + ", mAutoStatus = " + this.mAutoStatus + ", mAcSwitch = " + this.mAcSwitch + ", mLoopMode = " + this.mLoopMode + ", mEconStatus = " + this.mEconStatus + ", mBlowerDirectionMode = " + this.mBlowerDirectionMode + ", mAirVolumeLevel = " + this.mAirVolumeLevel + ", mDrvWindOutletBlowPersonModeStatus = " + this.mDrvWindOutletBlowPersonModeStatus + ", mDrvWindOutletAvoidPersonModeStatus = " + this.mDrvWindOutletAvoidPersonModeStatus + ", mDrvWindOutletSwingModeStatus = " + this.mDrvWindOutletSwingModeStatus + ", mDrvWindOutletOffModeStatus = " + this.mDrvWindOutletOffModeStatus + ", mDrvLeftWindOutletAngle = " + this.mDrvLeftWindOutletAngle + ", mDrvLeftMiddleWindOutletAngle = " + this.mDrvLeftMiddleWindOutletAngle + ", mPsgWindOutletBlowPersonModeStatus = " + this.mPsgWindOutletBlowPersonModeStatus + ", mPsgWindOutletAvoidPersonModeStatus = " + this.mPsgWindOutletAvoidPersonModeStatus + ", mPsgWindOutletSwingModeStatus = " + this.mPsgWindOutletSwingModeStatus + ", mPsgWindOutletOffModeStatus = " + this.mPsgWindOutletOffModeStatus + ", mPsgRightWindOutletAngle = " + this.mPsgRightWindOutletAngle + ", mPsgRightMiddleWindOutletAngle = " + this.mPsgRightMiddleWindOutletAngle + ", mSmartBlowerStatus = " + this.mSmartBlowerStatus + ", mDrvTemp = " + this.mDrvTemp + ", mPsgTemp = " + this.mPsgTemp + ", mTempDualZoneOn = " + this.mTempDualZoneOn + ", mDrvSeatHeatLevel = " + this.mDrvSeatHeatLevel + ", mDrvSeatWindLevel = " + this.mDrvSeatWindLevel + ", mPsgSeatHeatLevel = " + this.mPsgSeatHeatLevel + ", mPsgSeatWindLevel = " + this.mPsgSeatWindLevel + ", mOutCarTemp = " + this.mOutCarTemp + ", mPm25Concentration = " + this.mPm25Concentration + ", mPm25Filter = " + this.mPm25Filter + ", mAnionStatus = " + this.mAnionStatus + ", mFrontWindowDefroster = " + this.mFrontWindowDefroster + ", mBackWindowDefroster = " + this.mBackWindowDefroster + ", mMemoryBlowerDirectionMode = " + this.mMemoryBlowerDirectionMode + ", mIsWindOutletDisabled = " + this.mIsWindOutletDisabled + ", mWindOutletCanStatus = " + this.mWindOutletCanStatus + ", mSteeringWheelHeatLevel = " + this.mSteeringWheelHeatLevel + '}';
    }
}
