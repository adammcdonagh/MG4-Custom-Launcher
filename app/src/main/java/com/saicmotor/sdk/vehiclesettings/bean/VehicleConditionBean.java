package com.saicmotor.sdk.vehiclesettings.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class VehicleConditionBean implements Parcelable, Cloneable {
    public static final Parcelable.Creator<VehicleConditionBean> CREATOR = new Parcelable.Creator<VehicleConditionBean>() { // from class: com.saicmotor.sdk.vehiclesettings.bean.VehicleConditionBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VehicleConditionBean createFromParcel(Parcel in) {
            return new VehicleConditionBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VehicleConditionBean[] newArray(int size) {
            return new VehicleConditionBean[size];
        }
    };
    public static final int DEFAULT_AIR_CONDITION_CONFIG_CODE = 3;
    public static final int DEFAULT_DRIVER_SEAT_CONFIG_CODE = 5;
    public static final int DEFAULT_ELE_BACK_MIRROR_CONFIG_CODE = 4;
    public static final int DEFAULT_LDWS_CONFIG_CODE = 4;
    public static final int STEEP_DESCENT_CONTROL = 3;
    public static final int WINDOW_CONTROL = 4;
    private int mAcAvlbly;
    private int mAirConditionConfigCode;
    private int mAirFilterConfigCode;
    private int mAirFollowVehicleEconConfigCode;
    private int mAirbagConfigCode;
    private int mAmplifierType;
    private int mAnionPurifyConfigCode;
    private int mApaAvlbly;
    private int mAqsConfigCode;
    private int mAutomaticCall;
    private int mBatteryConfigCode;
    private int mBcmAvlbly;
    private int mBlindSpotDetection;
    private int mBmsAvlbly;
    private int mCarGear;
    private int mCarGearV;
    private float mCarSpeed;
    private int mCarType;
    private int mConfig360;
    private int mCrashSignal;
    private int mDmsConfigCode;
    private int mDoorAutoLockConfigCode;
    private int mDriverSeatConfigCode;
    private int mEcallState;
    private int mEleBackMirrorConfigCode;
    private int mEnergyPredictionConfigCode;
    private int mEngineState;
    private int mEp21ConfigCode;
    private int mForwardCollisionAssistance;
    private int mFrontRadarControl;
    private int mFrontSeatConfigCode;
    private int mFvcmAvlbly;
    private int mHcuAvlbly;
    private int mLdwsConfigCode;
    private int mLightFrontFogConfigCode;
    private int mLightingSettings;
    private int mMaintenanceStatus;
    private int mMarkedSignalId;
    private int mMileageUnit;
    private int mMsmAvlbly;
    private int mNextResetDay;
    private int mNextResetMileage;
    private int mOnePedalConfigCode;
    private int mPedestrianWarningConfigCode;
    private int mPepsAvlbly;
    private int mPeripheralKeyModule;
    private int mPlcmAvlbly;
    private int mPmDetectionConfigCode;
    private int mRadarAvlbly;
    private int mRearWindowAutoConfigCode;
    private boolean mResetCarMileageInfoStatus;
    private int mScsAvlbly;
    private int mSeatHeatingConfigCode;
    private int mSpeakerConfigCode;
    private int mSpeedAssistConfig;
    private int mSteepDescentControl;
    private int mSunRoofControlConfigCode;
    private int mSwcFunctionChangeSwa;
    private int mTailerElecticConfigCode;
    private int mTailgateControlConfigCode;
    private int mTrafficJamAssistance;
    private int mTransferCaseConfigCode;
    private int mTransmissionConfigCode;
    private int mVcuAvlbly;
    private int mVehicleExteriorColor;
    private int mVehicleIgnition;
    private int mVehicleNameInfo;
    private String mVinNumber;
    private int mWindowControl;

    public VehicleConditionBean() {
        this.mMarkedSignalId = 0;
        this.mCarSpeed = 0.0f;
        this.mVehicleIgnition = 0;
        this.mEngineState = 0;
        this.mCarGear = 0;
        this.mCarType = -1;
        this.mEcallState = 0;
        this.mCrashSignal = 0;
        this.mEp21ConfigCode = 0;
        this.mConfig360 = 2;
        this.mAmplifierType = 0;
        this.mAirFollowVehicleEconConfigCode = 2;
        this.mRearWindowAutoConfigCode = 1;
        this.mTailgateControlConfigCode = 1;
        this.mEleBackMirrorConfigCode = 4;
        this.mSeatHeatingConfigCode = 1;
        this.mPedestrianWarningConfigCode = 0;
        this.mDriverSeatConfigCode = 5;
        this.mLightingSettings = 1;
        this.mSpeedAssistConfig = 0;
        this.mBlindSpotDetection = 1;
        this.mTrafficJamAssistance = 0;
        this.mForwardCollisionAssistance = 0;
        this.mWindowControl = 4;
        this.mSteepDescentControl = 3;
        this.mFrontRadarControl = 1;
        this.mAutomaticCall = 0;
        this.mAqsConfigCode = 0;
        this.mLdwsConfigCode = 4;
        this.mDmsConfigCode = 1;
        this.mAirbagConfigCode = 0;
        this.mPmDetectionConfigCode = 0;
        this.mAirFilterConfigCode = 1;
        this.mAnionPurifyConfigCode = 0;
        this.mFrontSeatConfigCode = 0;
        this.mSunRoofControlConfigCode = 0;
        this.mDoorAutoLockConfigCode = 0;
        this.mAirConditionConfigCode = 3;
        this.mOnePedalConfigCode = 1;
        this.mTransferCaseConfigCode = 0;
        this.mEnergyPredictionConfigCode = 0;
        this.mTailerElecticConfigCode = 0;
        this.mLightFrontFogConfigCode = 0;
        this.mTransmissionConfigCode = 0;
        this.mBatteryConfigCode = 0;
        this.mSpeakerConfigCode = 3;
        this.mAcAvlbly = 0;
        this.mBcmAvlbly = 0;
        this.mScsAvlbly = 0;
        this.mApaAvlbly = 0;
        this.mPepsAvlbly = 0;
        this.mFvcmAvlbly = 0;
        this.mPlcmAvlbly = 0;
        this.mBmsAvlbly = 0;
        this.mHcuAvlbly = 0;
        this.mRadarAvlbly = 0;
        this.mMsmAvlbly = 0;
        this.mVcuAvlbly = 0;
        this.mCarGearV = 0;
        this.mVehicleNameInfo = 0;
        this.mVehicleExteriorColor = 0;
        this.mMileageUnit = 0;
        this.mNextResetMileage = -1;
        this.mNextResetDay = -1;
        this.mMaintenanceStatus = -1;
        this.mResetCarMileageInfoStatus = false;
        this.mPeripheralKeyModule = 0;
        this.mSwcFunctionChangeSwa = 0;
    }

    protected VehicleConditionBean(Parcel in) {
        this.mMarkedSignalId = 0;
        this.mCarSpeed = 0.0f;
        this.mVehicleIgnition = 0;
        this.mEngineState = 0;
        this.mCarGear = 0;
        this.mCarType = -1;
        this.mEcallState = 0;
        this.mCrashSignal = 0;
        this.mEp21ConfigCode = 0;
        this.mConfig360 = 2;
        this.mAmplifierType = 0;
        this.mAirFollowVehicleEconConfigCode = 2;
        this.mRearWindowAutoConfigCode = 1;
        this.mTailgateControlConfigCode = 1;
        this.mEleBackMirrorConfigCode = 4;
        this.mSeatHeatingConfigCode = 1;
        this.mPedestrianWarningConfigCode = 0;
        this.mDriverSeatConfigCode = 5;
        this.mLightingSettings = 1;
        this.mSpeedAssistConfig = 0;
        this.mBlindSpotDetection = 1;
        this.mTrafficJamAssistance = 0;
        this.mForwardCollisionAssistance = 0;
        this.mWindowControl = 4;
        this.mSteepDescentControl = 3;
        this.mFrontRadarControl = 1;
        this.mAutomaticCall = 0;
        this.mAqsConfigCode = 0;
        this.mLdwsConfigCode = 4;
        this.mDmsConfigCode = 1;
        this.mAirbagConfigCode = 0;
        this.mPmDetectionConfigCode = 0;
        this.mAirFilterConfigCode = 1;
        this.mAnionPurifyConfigCode = 0;
        this.mFrontSeatConfigCode = 0;
        this.mSunRoofControlConfigCode = 0;
        this.mDoorAutoLockConfigCode = 0;
        this.mAirConditionConfigCode = 3;
        this.mOnePedalConfigCode = 1;
        this.mTransferCaseConfigCode = 0;
        this.mEnergyPredictionConfigCode = 0;
        this.mTailerElecticConfigCode = 0;
        this.mLightFrontFogConfigCode = 0;
        this.mTransmissionConfigCode = 0;
        this.mBatteryConfigCode = 0;
        this.mSpeakerConfigCode = 3;
        this.mAcAvlbly = 0;
        this.mBcmAvlbly = 0;
        this.mScsAvlbly = 0;
        this.mApaAvlbly = 0;
        this.mPepsAvlbly = 0;
        this.mFvcmAvlbly = 0;
        this.mPlcmAvlbly = 0;
        this.mBmsAvlbly = 0;
        this.mHcuAvlbly = 0;
        this.mRadarAvlbly = 0;
        this.mMsmAvlbly = 0;
        this.mVcuAvlbly = 0;
        this.mCarGearV = 0;
        this.mVehicleNameInfo = 0;
        this.mVehicleExteriorColor = 0;
        this.mMileageUnit = 0;
        this.mNextResetMileage = -1;
        this.mNextResetDay = -1;
        this.mMaintenanceStatus = -1;
        this.mResetCarMileageInfoStatus = false;
        this.mPeripheralKeyModule = 0;
        this.mSwcFunctionChangeSwa = 0;
        this.mMarkedSignalId = in.readInt();
        this.mCarSpeed = in.readFloat();
        this.mVehicleIgnition = in.readInt();
        this.mEngineState = in.readInt();
        this.mCarGear = in.readInt();
        this.mCarType = in.readInt();
        this.mEcallState = in.readInt();
        this.mCrashSignal = in.readInt();
        this.mConfig360 = in.readInt();
        this.mAmplifierType = in.readInt();
        this.mAirFollowVehicleEconConfigCode = in.readInt();
        this.mRearWindowAutoConfigCode = in.readInt();
        this.mTailgateControlConfigCode = in.readInt();
        this.mEleBackMirrorConfigCode = in.readInt();
        this.mSeatHeatingConfigCode = in.readInt();
        this.mPedestrianWarningConfigCode = in.readInt();
        this.mDriverSeatConfigCode = in.readInt();
        this.mLightingSettings = in.readInt();
        this.mSpeedAssistConfig = in.readInt();
        this.mBlindSpotDetection = in.readInt();
        this.mEp21ConfigCode = in.readInt();
        this.mTrafficJamAssistance = in.readInt();
        this.mForwardCollisionAssistance = in.readInt();
        this.mWindowControl = in.readInt();
        this.mSteepDescentControl = in.readInt();
        this.mFrontRadarControl = in.readInt();
        this.mAutomaticCall = in.readInt();
        this.mAqsConfigCode = in.readInt();
        this.mLdwsConfigCode = in.readInt();
        this.mDmsConfigCode = in.readInt();
        this.mAirbagConfigCode = in.readInt();
        this.mPmDetectionConfigCode = in.readInt();
        this.mAirFilterConfigCode = in.readInt();
        this.mAnionPurifyConfigCode = in.readInt();
        this.mFrontSeatConfigCode = in.readInt();
        this.mSunRoofControlConfigCode = in.readInt();
        this.mDoorAutoLockConfigCode = in.readInt();
        this.mAirConditionConfigCode = in.readInt();
        this.mOnePedalConfigCode = in.readInt();
        this.mTransferCaseConfigCode = in.readInt();
        this.mEnergyPredictionConfigCode = in.readInt();
        this.mTailerElecticConfigCode = in.readInt();
        this.mLightFrontFogConfigCode = in.readInt();
        this.mTransmissionConfigCode = in.readInt();
        this.mBatteryConfigCode = in.readInt();
        this.mSpeakerConfigCode = in.readInt();
        this.mAcAvlbly = in.readInt();
        this.mBcmAvlbly = in.readInt();
        this.mScsAvlbly = in.readInt();
        this.mApaAvlbly = in.readInt();
        this.mPepsAvlbly = in.readInt();
        this.mFvcmAvlbly = in.readInt();
        this.mPlcmAvlbly = in.readInt();
        this.mBmsAvlbly = in.readInt();
        this.mHcuAvlbly = in.readInt();
        this.mRadarAvlbly = in.readInt();
        this.mMsmAvlbly = in.readInt();
        this.mVcuAvlbly = in.readInt();
        this.mCarGearV = in.readInt();
        this.mVehicleNameInfo = in.readInt();
        this.mVinNumber = in.readString();
        this.mVehicleExteriorColor = in.readInt();
        this.mMileageUnit = in.readInt();
        this.mNextResetMileage = in.readInt();
        this.mNextResetDay = in.readInt();
        this.mMaintenanceStatus = in.readInt();
        this.mResetCarMileageInfoStatus = in.readByte() != 0;
        this.mPeripheralKeyModule = in.readInt();
        this.mSwcFunctionChangeSwa = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mMarkedSignalId);
        parcel.writeFloat(this.mCarSpeed);
        parcel.writeInt(this.mVehicleIgnition);
        parcel.writeInt(this.mEngineState);
        parcel.writeInt(this.mCarGear);
        parcel.writeInt(this.mCarType);
        parcel.writeInt(this.mEcallState);
        parcel.writeInt(this.mCrashSignal);
        parcel.writeInt(this.mConfig360);
        parcel.writeInt(this.mAmplifierType);
        parcel.writeInt(this.mAirFollowVehicleEconConfigCode);
        parcel.writeInt(this.mRearWindowAutoConfigCode);
        parcel.writeInt(this.mTailgateControlConfigCode);
        parcel.writeInt(this.mEleBackMirrorConfigCode);
        parcel.writeInt(this.mSeatHeatingConfigCode);
        parcel.writeInt(this.mPedestrianWarningConfigCode);
        parcel.writeInt(this.mDriverSeatConfigCode);
        parcel.writeInt(this.mLightingSettings);
        parcel.writeInt(this.mSpeedAssistConfig);
        parcel.writeInt(this.mBlindSpotDetection);
        parcel.writeInt(this.mEp21ConfigCode);
        parcel.writeInt(this.mTrafficJamAssistance);
        parcel.writeInt(this.mForwardCollisionAssistance);
        parcel.writeInt(this.mWindowControl);
        parcel.writeInt(this.mSteepDescentControl);
        parcel.writeInt(this.mFrontRadarControl);
        parcel.writeInt(this.mAutomaticCall);
        parcel.writeInt(this.mAqsConfigCode);
        parcel.writeInt(this.mLdwsConfigCode);
        parcel.writeInt(this.mDmsConfigCode);
        parcel.writeInt(this.mAirbagConfigCode);
        parcel.writeInt(this.mPmDetectionConfigCode);
        parcel.writeInt(this.mAirFilterConfigCode);
        parcel.writeInt(this.mAnionPurifyConfigCode);
        parcel.writeInt(this.mFrontSeatConfigCode);
        parcel.writeInt(this.mSunRoofControlConfigCode);
        parcel.writeInt(this.mDoorAutoLockConfigCode);
        parcel.writeInt(this.mAirConditionConfigCode);
        parcel.writeInt(this.mOnePedalConfigCode);
        parcel.writeInt(this.mTransferCaseConfigCode);
        parcel.writeInt(this.mEnergyPredictionConfigCode);
        parcel.writeInt(this.mTailerElecticConfigCode);
        parcel.writeInt(this.mLightFrontFogConfigCode);
        parcel.writeInt(this.mTransmissionConfigCode);
        parcel.writeInt(this.mBatteryConfigCode);
        parcel.writeInt(this.mSpeakerConfigCode);
        parcel.writeInt(this.mAcAvlbly);
        parcel.writeInt(this.mBcmAvlbly);
        parcel.writeInt(this.mScsAvlbly);
        parcel.writeInt(this.mApaAvlbly);
        parcel.writeInt(this.mPepsAvlbly);
        parcel.writeInt(this.mFvcmAvlbly);
        parcel.writeInt(this.mPlcmAvlbly);
        parcel.writeInt(this.mBmsAvlbly);
        parcel.writeInt(this.mHcuAvlbly);
        parcel.writeInt(this.mRadarAvlbly);
        parcel.writeInt(this.mMsmAvlbly);
        parcel.writeInt(this.mVcuAvlbly);
        parcel.writeInt(this.mCarGearV);
        parcel.writeInt(this.mVehicleNameInfo);
        parcel.writeString(this.mVinNumber);
        parcel.writeInt(this.mVehicleExteriorColor);
        parcel.writeInt(this.mMileageUnit);
        parcel.writeInt(this.mNextResetMileage);
        parcel.writeInt(this.mNextResetDay);
        parcel.writeInt(this.mMaintenanceStatus);
        parcel.writeByte(this.mResetCarMileageInfoStatus ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mPeripheralKeyModule);
        parcel.writeInt(this.mSwcFunctionChangeSwa);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public VehicleConditionBean m8clone() throws CloneNotSupportedException {
        return (VehicleConditionBean) super.clone();
    }

    public String toString() {
        return "VehicleConditionBean{mMarkedSignalId='" + this.mMarkedSignalId + "', mCarSpeed=" + this.mCarSpeed + ", mVehicleIgnition=" + this.mVehicleIgnition + ", mEngineState=" + this.mEngineState + ", mCarGear = " + this.mCarGear + ", mCarType = " + this.mCarType + ", mEcallState = " + this.mEcallState + ", mCrashSignal = " + this.mCrashSignal + ", mConfig360 = " + this.mConfig360 + ", mAmplifierType = " + this.mAmplifierType + ", mEp21ConfigCode = " + this.mEp21ConfigCode + ", mAirFollowVehicleEconConfigCode = " + this.mAirFollowVehicleEconConfigCode + ", mRearWindowAutoConfigCode = " + this.mRearWindowAutoConfigCode + ", mTailgateControlConfigCode = " + this.mTailgateControlConfigCode + ", mEleBackMirrorConfigCode = " + this.mEleBackMirrorConfigCode + ", mSeatHeatingConfigCode = " + this.mSeatHeatingConfigCode + ", mPedestrianWarningConfigCode = " + this.mPedestrianWarningConfigCode + ", mDriverSeatConfigCode = " + this.mDriverSeatConfigCode + ", mLightingSettings = " + this.mLightingSettings + ", mSpeedAssistConfig = " + this.mSpeedAssistConfig + ", mBlindSpotDetection = " + this.mBlindSpotDetection + ", mTrafficJamAssistance = " + this.mTrafficJamAssistance + ", mForwardCollisionAssistance = " + this.mForwardCollisionAssistance + ", mWindowControl = " + this.mWindowControl + ", mSteepDescentControl = " + this.mSteepDescentControl + ", mFrontRadarControl = " + this.mFrontRadarControl + ", mAutomaticCall = " + this.mAutomaticCall + ", mAqsConfigCode = " + this.mAqsConfigCode + ", mLdwsConfigCode = " + this.mLdwsConfigCode + ", mDmsConfigCode = " + this.mDmsConfigCode + ", mAirbagConfigCode = " + this.mAirbagConfigCode + ", mPmDetectionConfigCode = " + this.mPmDetectionConfigCode + ", mAirFilterConfigCode = " + this.mAirFilterConfigCode + ", mAnionPurifyConfigCode = " + this.mAnionPurifyConfigCode + ", mFrontSeatConfigCode = " + this.mFrontSeatConfigCode + ", mSunRoofControlConfigCode = " + this.mSunRoofControlConfigCode + ", mDoorAutoLockConfigCode = " + this.mDoorAutoLockConfigCode + ", mAirConditionConfigCode = " + this.mAirConditionConfigCode + ", mOnePedalConfigCode = " + this.mOnePedalConfigCode + ", mTransferCaseConfigCode = " + this.mTransferCaseConfigCode + ", mEnergyPredictionConfigCode = " + this.mEnergyPredictionConfigCode + ", mTailerElecticConfigCode = " + this.mTailerElecticConfigCode + ", mLightFrontFogConfigCode = " + this.mLightFrontFogConfigCode + ", mTransmissionConfigCode = " + this.mTransmissionConfigCode + ", mBatteryConfigCode = " + this.mBatteryConfigCode + ", mSpeakerConfigCode = " + this.mSpeakerConfigCode + ", mAcAvlbly = " + this.mAcAvlbly + ", mBcmAvlbly = " + this.mBcmAvlbly + ", mScsAvlbly = " + this.mScsAvlbly + ", mApaAvlbly = " + this.mApaAvlbly + ", mPepsAvlbly = " + this.mPepsAvlbly + ", mFvcmAvlbly = " + this.mFvcmAvlbly + ", mPlcmAvlbly = " + this.mPlcmAvlbly + ", mBmsAvlbly = " + this.mBmsAvlbly + ", mHcuAvlbly = " + this.mHcuAvlbly + ", mRadarAvlbly = " + this.mRadarAvlbly + ", mMsmAvlbly = " + this.mMsmAvlbly + ", mVcuAvlbly = " + this.mVcuAvlbly + ", mCarGearV = " + this.mCarGearV + ", mVehicleNameInfo = " + this.mVehicleNameInfo + ", mVinNumber = " + this.mVinNumber + ", mVehicleExteriorColor = " + this.mVehicleExteriorColor + ", mMileageUnit = " + this.mMileageUnit + ", mNextResetMileage = " + this.mNextResetMileage + ", mNextResetDay = " + this.mNextResetDay + ", mMaintenanceStatus = " + this.mMaintenanceStatus + ", mResetCarMileageInfoStatus = " + this.mResetCarMileageInfoStatus + ", mPeripheralKeyModule = " + this.mPeripheralKeyModule + ", mSwcFunctionChangeSwa = " + this.mSwcFunctionChangeSwa + '}';
    }

    public int getMarkedSignalId() {
        return this.mMarkedSignalId;
    }

    public void setMarkedSignalId(int markedSignalId) {
        this.mMarkedSignalId = markedSignalId;
    }

    public float getCarSpeed() {
        return this.mCarSpeed;
    }

    public void setCarSpeed(float speed) {
        this.mCarSpeed = speed;
    }

    public int getVehicleIgnition() {
        return this.mVehicleIgnition;
    }

    public void setVehicleIgnitionStatus(int ignStatus) {
        this.mVehicleIgnition = ignStatus;
    }

    public int getEngineStatus() {
        return this.mEngineState;
    }

    public void setEngineStatus(int engineStatus) {
        this.mEngineState = engineStatus;
    }

    public int getCarGear() {
        return this.mCarGear;
    }

    public void setCarGear(int gearValue) {
        this.mCarGear = gearValue;
    }

    public int getCarType() {
        return this.mCarType;
    }

    public void setCarType(int carType) {
        this.mCarType = carType;
    }

    public int getEcallState() {
        return this.mEcallState;
    }

    public void setEcallState(int ecallState) {
        this.mEcallState = ecallState;
    }

    public int getCrashSignal() {
        return this.mCrashSignal;
    }

    public void setCrashSignal(int mCrashSignal) {
        this.mCrashSignal = mCrashSignal;
    }

    public int getConfig360Value() {
        return this.mConfig360;
    }

    public void setConfig360Value(int config360Value) {
        this.mConfig360 = config360Value;
    }

    public int getAmplifierType() {
        return this.mAmplifierType;
    }

    public void setAmplifierType(int amplifierType) {
        this.mAmplifierType = amplifierType;
    }

    public int getAirFollowVehicleEconConfigCode() {
        return this.mAirFollowVehicleEconConfigCode;
    }

    public void setAirFollowVehicleEconConfigCode(int airFollowVehicleEcon) {
        this.mAirFollowVehicleEconConfigCode = airFollowVehicleEcon;
    }

    public int getRearWindowAutoConfigCode() {
        return this.mRearWindowAutoConfigCode;
    }

    public void setRearWindowAutoConfigCode(int rearWindowAutomaticStatus) {
        this.mRearWindowAutoConfigCode = rearWindowAutomaticStatus;
    }

    public int getTailgateControlConfigCode() {
        return this.mTailgateControlConfigCode;
    }

    public void setTailgateControlConfigCode(int tailgateControlConfigCode) {
        this.mTailgateControlConfigCode = tailgateControlConfigCode;
    }

    public int getEleBackMirrorConfigCode() {
        return this.mEleBackMirrorConfigCode;
    }

    public void setEleBackMirrorConfigCode(int eleBackMirrorConfigCode) {
        this.mEleBackMirrorConfigCode = eleBackMirrorConfigCode;
    }

    public int getSeatHeatingConfigCode() {
        return this.mSeatHeatingConfigCode;
    }

    public void setSeatHeatingConfigCode(int seatHeatingConfigCode) {
        this.mSeatHeatingConfigCode = seatHeatingConfigCode;
    }

    public int getPedestrianWarningConfigCode() {
        return this.mPedestrianWarningConfigCode;
    }

    public void setPedestrianWarningConfigCode(int pedestrianWarningConfigCode) {
        this.mPedestrianWarningConfigCode = pedestrianWarningConfigCode;
    }

    public int getDriverSeatConfigCode() {
        return this.mDriverSeatConfigCode;
    }

    public void setDriverSeatConfigCode(int driverSeatConfigCode) {
        this.mDriverSeatConfigCode = driverSeatConfigCode;
    }

    public int getLightingSettings() {
        return this.mLightingSettings;
    }

    public void setLightingSettings(int lightingSettingsStatus) {
        this.mLightingSettings = lightingSettingsStatus;
    }

    public int getSpeedAssistConfig() {
        return this.mSpeedAssistConfig;
    }

    public void setSpeedAssistConfig(int speedAssistConfig) {
        this.mSpeedAssistConfig = speedAssistConfig;
    }

    public int getBlindSpotDetection() {
        return this.mBlindSpotDetection;
    }

    public void setBlindSpotDetection(int blindSpotDetection) {
        this.mBlindSpotDetection = blindSpotDetection;
    }

    public void setEp21ConfigCode(int configCode) {
        this.mEp21ConfigCode = configCode;
    }

    public int getEp21ConfigCode() {
        return this.mEp21ConfigCode;
    }

    public void setTrafficJamAssistance(int trafficJamAssistance) {
        this.mTrafficJamAssistance = trafficJamAssistance;
    }

    public int getTrafficJamAssistance() {
        return this.mTrafficJamAssistance;
    }

    public void setForwardCollisionAssistance(int forwardCollisionAssistance) {
        this.mForwardCollisionAssistance = forwardCollisionAssistance;
    }

    public int getForwardCollisionAssistance() {
        return this.mForwardCollisionAssistance;
    }

    public void setWindowControl(int windowControl) {
        this.mWindowControl = windowControl;
    }

    public int getWindowControl() {
        return this.mWindowControl;
    }

    public void setSteepDescentControl(int steepDescentControl) {
        this.mSteepDescentControl = steepDescentControl;
    }

    public int getSteepDescentControl() {
        return this.mSteepDescentControl;
    }

    public void setFrontRadarControl(int frontRadarControl) {
        this.mFrontRadarControl = frontRadarControl;
    }

    public int getFrontRadarControl() {
        return this.mFrontRadarControl;
    }

    public void setAutomaticCall(int automaticCall) {
        this.mAutomaticCall = automaticCall;
    }

    public int getAutomaticCall() {
        return this.mAutomaticCall;
    }

    public int getAqsConfigCode() {
        return this.mAqsConfigCode;
    }

    public void setAqsConfigCode(int aqsConfigCode) {
        this.mAqsConfigCode = aqsConfigCode;
    }

    public int getLdwsConfigCode() {
        return this.mLdwsConfigCode;
    }

    public void setLdwsConfigCode(int ldwsConfigCode) {
        this.mLdwsConfigCode = ldwsConfigCode;
    }

    public int getDmsConfigCode() {
        return this.mDmsConfigCode;
    }

    public void setDmsConfigCode(int dmsConfigCode) {
        this.mDmsConfigCode = dmsConfigCode;
    }

    public int getAirbagConfigCode() {
        return this.mAirbagConfigCode;
    }

    public void setAirbagConfigCode(int airbagConfigCode) {
        this.mAirbagConfigCode = airbagConfigCode;
    }

    public int getPmDetectionConfigCode() {
        return this.mPmDetectionConfigCode;
    }

    public void setPmDetectionConfigCode(int pmDetectionConfigCode) {
        this.mPmDetectionConfigCode = pmDetectionConfigCode;
    }

    public int getAirFilterConfigCode() {
        return this.mAirFilterConfigCode;
    }

    public void setAirFilterConfigCode(int airFilterConfigCode) {
        this.mAirFilterConfigCode = airFilterConfigCode;
    }

    public int getAnionPurifyConfigCode() {
        return this.mAnionPurifyConfigCode;
    }

    public void setAnionPurifyConfigCode(int anionPurifyConfigCode) {
        this.mAnionPurifyConfigCode = anionPurifyConfigCode;
    }

    public int getFrontSeatConfigCode() {
        return this.mFrontSeatConfigCode;
    }

    public void setFrontSeatConfigCode(int frontSeatConfigCode) {
        this.mFrontSeatConfigCode = frontSeatConfigCode;
    }

    public int getSunRoofControlConfigCode() {
        return this.mSunRoofControlConfigCode;
    }

    public void setSunRoofControlConfigCode(int sunRoofControlConfigCode) {
        this.mSunRoofControlConfigCode = sunRoofControlConfigCode;
    }

    public int getDoorAutoLockConfigCode() {
        return this.mDoorAutoLockConfigCode;
    }

    public void setDoorAutoLockConfigCode(int doorAutoLockConfigCode) {
        this.mDoorAutoLockConfigCode = doorAutoLockConfigCode;
    }

    public int getAirConditionConfigCode() {
        return this.mAirConditionConfigCode;
    }

    public void setAirConditionConfigCode(int airConditionConfigCode) {
        this.mAirConditionConfigCode = airConditionConfigCode;
    }

    public int getOnePedalConfigCode() {
        return this.mOnePedalConfigCode;
    }

    public void setOnePedalConfigCode(int onePedalConfigCode) {
        this.mOnePedalConfigCode = onePedalConfigCode;
    }

    public int getTransferCaseConfigCode() {
        return this.mTransferCaseConfigCode;
    }

    public void setTransferCaseConfigCode(int transferCaseConfigCode) {
        this.mTransferCaseConfigCode = transferCaseConfigCode;
    }

    public int getEnergyPredictionConfigCode() {
        return this.mEnergyPredictionConfigCode;
    }

    public void setEnergyPredictionConfigCode(int energyPredictionConfigCode) {
        this.mEnergyPredictionConfigCode = energyPredictionConfigCode;
    }

    public int getTailerElecticConfigCode() {
        return this.mTailerElecticConfigCode;
    }

    public void setTailerElecticConfigCode(int tailerElecticConfigCode) {
        this.mTailerElecticConfigCode = tailerElecticConfigCode;
    }

    public int getLightFrontFogConfigCode() {
        return this.mLightFrontFogConfigCode;
    }

    public void setLightFrontFogConfigCode(int lightFrontFogConfigCode) {
        this.mLightFrontFogConfigCode = lightFrontFogConfigCode;
    }

    public int getTransmissionConfigCode() {
        return this.mTransmissionConfigCode;
    }

    public void setTransmissionConfigCode(int transmissionConfigCode) {
        this.mTransmissionConfigCode = transmissionConfigCode;
    }

    public int getBatteryConfigCode() {
        return this.mBatteryConfigCode;
    }

    public void setBatteryConfigCode(int batteryConfigCode) {
        this.mBatteryConfigCode = batteryConfigCode;
    }

    public int getSpeakerConfigCode() {
        return this.mSpeakerConfigCode;
    }

    public void setSpeakerConfigCode(int mSpeakerConfigCode) {
        this.mSpeakerConfigCode = mSpeakerConfigCode;
    }

    public void setAcAvlbly(int acAvlbly) {
        this.mAcAvlbly = acAvlbly;
    }

    public int getAcAvlbly() {
        return this.mAcAvlbly;
    }

    public void setBcmAvlbly(int bcmAvlbly) {
        this.mBcmAvlbly = bcmAvlbly;
    }

    public int getBcmAvlbly() {
        return this.mBcmAvlbly;
    }

    public void setScsAvlbly(int scsAvlbly) {
        this.mScsAvlbly = scsAvlbly;
    }

    public int getScsAvlbly() {
        return this.mScsAvlbly;
    }

    public void setApaAvlbly(int apaAvlbly) {
        this.mApaAvlbly = apaAvlbly;
    }

    public int getApaAvlbly() {
        return this.mApaAvlbly;
    }

    public void setPepsAvlbly(int pepsAvlbly) {
        this.mPepsAvlbly = pepsAvlbly;
    }

    public int getPepsAvlbly() {
        return this.mPepsAvlbly;
    }

    public void setFvcmAvlbly(int fvcmAvlbly) {
        this.mFvcmAvlbly = fvcmAvlbly;
    }

    public int getFvcmAvlbly() {
        return this.mFvcmAvlbly;
    }

    public void setPlcmAvlbly(int plcmAvlbly) {
        this.mPlcmAvlbly = plcmAvlbly;
    }

    public int getPlcmAvlbly() {
        return this.mPlcmAvlbly;
    }

    public void setBmsAvlbly(int bmsAvlbly) {
        this.mBmsAvlbly = bmsAvlbly;
    }

    public int getBmsAvlbly() {
        return this.mBmsAvlbly;
    }

    public void setHcuAvlbly(int hcuAvlbly) {
        this.mHcuAvlbly = hcuAvlbly;
    }

    public int getHcuAvlbly() {
        return this.mHcuAvlbly;
    }

    public void setRadarAvlbly(int radarAvlbly) {
        this.mRadarAvlbly = radarAvlbly;
    }

    public int getRadarAvlbly() {
        return this.mRadarAvlbly;
    }

    public void setMsmAvlbly(int msmAvlbly) {
        this.mMsmAvlbly = msmAvlbly;
    }

    public int getMsmAvlbly() {
        return this.mMsmAvlbly;
    }

    public int getVcuAvlbly() {
        return this.mVcuAvlbly;
    }

    public void setVcuAvlbly(int vcuAvlbly) {
        this.mVcuAvlbly = vcuAvlbly;
    }

    public void setCarGearV(int carGearV) {
        this.mCarGearV = carGearV;
    }

    public int getCarGearV() {
        return this.mCarGearV;
    }

    public int getVehicleNameInfo() {
        return this.mVehicleNameInfo;
    }

    public void setVehicleNameInfo(int vehicleNameInfo) {
        this.mVehicleNameInfo = vehicleNameInfo;
    }

    public String getVinNumber() {
        return this.mVinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.mVinNumber = vinNumber;
    }

    public int getVehicleExteriorColor() {
        return this.mVehicleExteriorColor;
    }

    public void setVehicleExteriorColor(int vehicleExteriorColor) {
        this.mVehicleExteriorColor = vehicleExteriorColor;
    }

    public int getMileageUnit() {
        return this.mMileageUnit;
    }

    public void setMileageUnit(int unit) {
        this.mMileageUnit = unit;
    }

    public int getNextResetMileage() {
        return this.mNextResetMileage;
    }

    public void setNextResetMileage(int nextResetMileage) {
        this.mNextResetMileage = nextResetMileage;
    }

    public int getNextResetDay() {
        return this.mNextResetDay;
    }

    public void setNextResetDay(int nextResetDay) {
        this.mNextResetDay = nextResetDay;
    }

    public int getMaintenanceStatus() {
        return this.mMaintenanceStatus;
    }

    public void setMaintenanceStatus(int maintenanceStatus) {
        this.mMaintenanceStatus = maintenanceStatus;
    }

    public boolean isResetCarMileageInfoStatus() {
        return this.mResetCarMileageInfoStatus;
    }

    public void setResetCarMileageInfoStatus(boolean resetCarInfoStatus) {
        this.mResetCarMileageInfoStatus = resetCarInfoStatus;
    }

    public void setPeripheralKeyModule(int peripheralKeyModule) {
        this.mPeripheralKeyModule = peripheralKeyModule;
    }

    public int getPeripheralKeyModule() {
        return this.mPeripheralKeyModule;
    }

    public int getSwcFunctionChangeSwa() {
        return this.mSwcFunctionChangeSwa;
    }

    public void setSwcFunctionChangeSwa(int swcFunctionChangeSwa) {
        this.mSwcFunctionChangeSwa = swcFunctionChangeSwa;
    }
}
