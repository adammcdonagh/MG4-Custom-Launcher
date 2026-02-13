package com.saicmotor.sdk.vehiclesettings.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class VehicleSettingBean implements Parcelable, Cloneable {
    public static final Parcelable.Creator<VehicleSettingBean> CREATOR = new Parcelable.Creator<VehicleSettingBean>() { // from class: com.saicmotor.sdk.vehiclesettings.bean.VehicleSettingBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VehicleSettingBean createFromParcel(Parcel in) {
            return new VehicleSettingBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VehicleSettingBean[] newArray(int size) {
            return new VehicleSettingBean[size];
        }
    };
    private static final int DEFALUT_AMBTLIGHT_BRIGHTNESS = 5;
    private static final int DEFALUT_AMBTLIGHT_OPEN_MODE = 3;
    private static final int DEFALUT_ELECTRIC_TAILGATE_POS = 100;
    private static final int DEFALUT_LANE_KEEPING_ASST_MODE = 5;
    private int mAccTjaMode;
    private int mAmbtLightAvlbly;
    private int mAmbtLightBreathingOn;
    private int mAmbtLightBrightness;
    private int mAmbtLightColor;
    private int mAmbtLightDrvMode;
    private int mAmbtLightGlbOn;
    private int mAmbtLightOpenMode;
    private int mAmbtLightWlcmMode;
    private int mAmbtLightWlcmOn;
    private int mApproachUnlockMode;
    private int mAqsSensitivity;
    private int mAutoEmergencyBraking;
    private int mAutoHoldSwitch;
    private int mAutoMainBeamControl;
    private int mAutoModeAirVolume;
    private int mBlindSpotDetection;
    private int mBrakePedalLevel;
    private int mBrakeToStandstill;
    private int mBusOnOffStatus;
    private int mCarSearchFeedback;
    private int mCarSearchFeedbackSwitch;
    private int mDefrostLinkage;
    private int mDriveMode;
    private int mDriverMonitorSys;
    private int mDriverMonitorSysVibration;
    private int mDriverMonitorSysWarningSound;
    private int mDriverSeatAutoWlcm;
    private int mDrivingAutoLock;
    private int mElectricPowertrainLevel;
    private float mElectricTailgatePos;
    private int mFarewellLightMode;
    private int mFcwAlarmMode;
    private int mFcwAutoBrakeMode;
    private int mFcwSensitivity;
    private int mFcwSysFailure;
    private int mHomeLightTime;
    private int mHvacCustomSwitch;
    private int mHvacEconLinkage;
    private int mInductiveDoorHandle;
    private int mInductiveTailgate;
    private int mKeyUnlockMode;
    private int mLampHomeOn;
    private int mLampWelcomeOn;
    private int mLaneChangeAsst;
    private int mLaneKeepingAsstMode;
    private int mLaneKeepingAsstSen;
    private int mLaneKeepingVibration;
    private int mLaneKeepingWarningSound;
    private int mLdwSysFailure;
    private int mLeaveAutoLockMode;
    private int mLeftRearviewDowndip;
    private int mLightFrontFogSwitch;
    private int mLightRearFogSwitch;
    private int mLkaSysFailure;
    private int mLongerEndurance;
    private int mLongerEnduranceDisable;
    private int mLongerEnduranceRecommend;
    private int mMarkedSignalId;
    private int mMusicLinkage;
    private int mNearfieldUnlockMode;
    private int mOuterRearviewFold;
    private int mParkingWarning;
    private int mPdcChangeSts;
    private int mPowerModeSwitch;
    private int mPsgSafetyAirbagOn;
    private int mPsgSafetyAirbagStatus;
    private int mRctbSysFailure;
    private int mRdaAvlbly;
    private int mRdaSysStatus;
    private int mRearCollisionWarning;
    private int mRearCrossTrafficSys;
    private int mRearDriveAsstSys;
    private int mRegenerativeBrakeSwitch;
    private int mRegenerativeLevel;
    private int mRegenerativeLevelDisable;
    private int mRightRearviewDowndip;
    private int mSdmAvailable;
    private int mSeatAutoAdjust;
    private int mSeatHeatVentLinkage;
    private int mSignalPedal;
    private int mSignalPedalLnhbReg;
    private int mSpeedAsstMode;
    private int mSpeedAsstSlifWarning;
    private int mStallingAutoUnlock;
    private int mStartLinkage;
    private int mSteeringLevel;
    private int mSteeringWheelDefine;
    private int mTdmAvlbly;
    private int mTowingModeSwitch;
    private int mTrafficJamAsstOn;
    private int mTrlrCnctSts;
    private int mUdwSysFailure;
    private int mUnsteadyDrivingWarning;
    private int mUnsteadyDrivingWarningSen;
    private int mVoiceLinkage;
    private int mWelcomeLightTime;
    private int mWelcomeSoundAvlbly;
    private int mWelcomeSoundOn;
    private int mWelcomeSoundType;

    public VehicleSettingBean() {
        this.mMarkedSignalId = 0;
        this.mAutoModeAirVolume = 1;
        this.mHvacEconLinkage = 1;
        this.mDefrostLinkage = 0;
        this.mAqsSensitivity = 0;
        this.mSeatHeatVentLinkage = 1;
        this.mElectricTailgatePos = 100.0f;
        this.mDrivingAutoLock = 1;
        this.mStallingAutoUnlock = 1;
        this.mSeatAutoAdjust = 1;
        this.mKeyUnlockMode = 0;
        this.mNearfieldUnlockMode = 1;
        this.mApproachUnlockMode = 0;
        this.mLeaveAutoLockMode = 0;
        this.mInductiveTailgate = 1;
        this.mInductiveDoorHandle = 2;
        this.mLeftRearviewDowndip = 1;
        this.mRightRearviewDowndip = 1;
        this.mOuterRearviewFold = 1;
        this.mDriverSeatAutoWlcm = 1;
        this.mSteeringWheelDefine = 2;
        this.mHvacCustomSwitch = 3;
        this.mWelcomeSoundOn = 1;
        this.mWelcomeSoundType = 1;
        this.mWelcomeLightTime = 1;
        this.mHomeLightTime = 1;
        this.mAutoMainBeamControl = 1;
        this.mAmbtLightGlbOn = 1;
        this.mAmbtLightOpenMode = 3;
        this.mAmbtLightDrvMode = 1;
        this.mAmbtLightColor = 0;
        this.mAmbtLightBrightness = 5;
        this.mAmbtLightBreathingOn = 2;
        this.mAmbtLightWlcmOn = 1;
        this.mAmbtLightWlcmMode = 0;
        this.mStartLinkage = 1;
        this.mVoiceLinkage = 1;
        this.mMusicLinkage = 1;
        this.mSpeedAsstSlifWarning = 1;
        this.mSpeedAsstMode = 0;
        this.mAccTjaMode = 1;
        this.mLaneKeepingAsstMode = 5;
        this.mLaneKeepingAsstSen = 2;
        this.mLaneKeepingWarningSound = 2;
        this.mLaneKeepingVibration = 2;
        this.mLkaSysFailure = 0;
        this.mLdwSysFailure = 0;
        this.mTrafficJamAsstOn = 2;
        this.mFcwAlarmMode = 1;
        this.mFcwAutoBrakeMode = 2;
        this.mFcwSensitivity = 2;
        this.mFcwSysFailure = 0;
        this.mAutoEmergencyBraking = 2;
        this.mRearDriveAsstSys = 0;
        this.mBlindSpotDetection = 0;
        this.mLaneChangeAsst = 0;
        this.mRearCrossTrafficSys = 0;
        this.mRearCollisionWarning = 0;
        this.mRctbSysFailure = 0;
        this.mRdaSysStatus = 5;
        this.mParkingWarning = 0;
        this.mUnsteadyDrivingWarning = 2;
        this.mUnsteadyDrivingWarningSen = 2;
        this.mUdwSysFailure = 0;
        this.mDriverMonitorSys = 2;
        this.mDriverMonitorSysWarningSound = 2;
        this.mDriverMonitorSysVibration = 2;
        this.mPsgSafetyAirbagOn = 1;
        this.mPsgSafetyAirbagStatus = 0;
        this.mSdmAvailable = 0;
        this.mRdaAvlbly = 0;
        this.mWelcomeSoundAvlbly = 0;
        this.mAmbtLightAvlbly = 0;
        this.mBusOnOffStatus = 0;
        this.mDriveMode = 3;
        this.mElectricPowertrainLevel = 2;
        this.mSteeringLevel = 2;
        this.mBrakePedalLevel = 0;
        this.mRegenerativeBrakeSwitch = 0;
        this.mLampHomeOn = 0;
        this.mLampWelcomeOn = 0;
        this.mCarSearchFeedback = 1;
        this.mFarewellLightMode = 0;
        this.mRegenerativeLevel = 1;
        this.mRegenerativeLevelDisable = 0;
        this.mSignalPedal = 0;
        this.mSignalPedalLnhbReg = 0;
        this.mLongerEndurance = 0;
        this.mLongerEnduranceRecommend = 0;
        this.mLongerEnduranceDisable = 0;
        this.mBrakeToStandstill = 0;
        this.mPowerModeSwitch = 0;
        this.mAutoHoldSwitch = 0;
        this.mCarSearchFeedbackSwitch = 1;
        this.mLightRearFogSwitch = 0;
        this.mLightFrontFogSwitch = 0;
        this.mTowingModeSwitch = 0;
        this.mTdmAvlbly = 0;
        this.mTrlrCnctSts = 0;
        this.mPdcChangeSts = 0;
    }

    protected VehicleSettingBean(Parcel in) {
        this.mMarkedSignalId = 0;
        this.mAutoModeAirVolume = 1;
        this.mHvacEconLinkage = 1;
        this.mDefrostLinkage = 0;
        this.mAqsSensitivity = 0;
        this.mSeatHeatVentLinkage = 1;
        this.mElectricTailgatePos = 100.0f;
        this.mDrivingAutoLock = 1;
        this.mStallingAutoUnlock = 1;
        this.mSeatAutoAdjust = 1;
        this.mKeyUnlockMode = 0;
        this.mNearfieldUnlockMode = 1;
        this.mApproachUnlockMode = 0;
        this.mLeaveAutoLockMode = 0;
        this.mInductiveTailgate = 1;
        this.mInductiveDoorHandle = 2;
        this.mLeftRearviewDowndip = 1;
        this.mRightRearviewDowndip = 1;
        this.mOuterRearviewFold = 1;
        this.mDriverSeatAutoWlcm = 1;
        this.mSteeringWheelDefine = 2;
        this.mHvacCustomSwitch = 3;
        this.mWelcomeSoundOn = 1;
        this.mWelcomeSoundType = 1;
        this.mWelcomeLightTime = 1;
        this.mHomeLightTime = 1;
        this.mAutoMainBeamControl = 1;
        this.mAmbtLightGlbOn = 1;
        this.mAmbtLightOpenMode = 3;
        this.mAmbtLightDrvMode = 1;
        this.mAmbtLightColor = 0;
        this.mAmbtLightBrightness = 5;
        this.mAmbtLightBreathingOn = 2;
        this.mAmbtLightWlcmOn = 1;
        this.mAmbtLightWlcmMode = 0;
        this.mStartLinkage = 1;
        this.mVoiceLinkage = 1;
        this.mMusicLinkage = 1;
        this.mSpeedAsstSlifWarning = 1;
        this.mSpeedAsstMode = 0;
        this.mAccTjaMode = 1;
        this.mLaneKeepingAsstMode = 5;
        this.mLaneKeepingAsstSen = 2;
        this.mLaneKeepingWarningSound = 2;
        this.mLaneKeepingVibration = 2;
        this.mLkaSysFailure = 0;
        this.mLdwSysFailure = 0;
        this.mTrafficJamAsstOn = 2;
        this.mFcwAlarmMode = 1;
        this.mFcwAutoBrakeMode = 2;
        this.mFcwSensitivity = 2;
        this.mFcwSysFailure = 0;
        this.mAutoEmergencyBraking = 2;
        this.mRearDriveAsstSys = 0;
        this.mBlindSpotDetection = 0;
        this.mLaneChangeAsst = 0;
        this.mRearCrossTrafficSys = 0;
        this.mRearCollisionWarning = 0;
        this.mRctbSysFailure = 0;
        this.mRdaSysStatus = 5;
        this.mParkingWarning = 0;
        this.mUnsteadyDrivingWarning = 2;
        this.mUnsteadyDrivingWarningSen = 2;
        this.mUdwSysFailure = 0;
        this.mDriverMonitorSys = 2;
        this.mDriverMonitorSysWarningSound = 2;
        this.mDriverMonitorSysVibration = 2;
        this.mPsgSafetyAirbagOn = 1;
        this.mPsgSafetyAirbagStatus = 0;
        this.mSdmAvailable = 0;
        this.mRdaAvlbly = 0;
        this.mWelcomeSoundAvlbly = 0;
        this.mAmbtLightAvlbly = 0;
        this.mBusOnOffStatus = 0;
        this.mDriveMode = 3;
        this.mElectricPowertrainLevel = 2;
        this.mSteeringLevel = 2;
        this.mBrakePedalLevel = 0;
        this.mRegenerativeBrakeSwitch = 0;
        this.mLampHomeOn = 0;
        this.mLampWelcomeOn = 0;
        this.mCarSearchFeedback = 1;
        this.mFarewellLightMode = 0;
        this.mRegenerativeLevel = 1;
        this.mRegenerativeLevelDisable = 0;
        this.mSignalPedal = 0;
        this.mSignalPedalLnhbReg = 0;
        this.mLongerEndurance = 0;
        this.mLongerEnduranceRecommend = 0;
        this.mLongerEnduranceDisable = 0;
        this.mBrakeToStandstill = 0;
        this.mPowerModeSwitch = 0;
        this.mAutoHoldSwitch = 0;
        this.mCarSearchFeedbackSwitch = 1;
        this.mLightRearFogSwitch = 0;
        this.mLightFrontFogSwitch = 0;
        this.mTowingModeSwitch = 0;
        this.mTdmAvlbly = 0;
        this.mTrlrCnctSts = 0;
        this.mPdcChangeSts = 0;
        this.mMarkedSignalId = in.readInt();
        this.mAutoModeAirVolume = in.readInt();
        this.mHvacEconLinkage = in.readInt();
        this.mDefrostLinkage = in.readInt();
        this.mAqsSensitivity = in.readInt();
        this.mSeatHeatVentLinkage = in.readInt();
        this.mElectricTailgatePos = in.readFloat();
        this.mDrivingAutoLock = in.readInt();
        this.mStallingAutoUnlock = in.readInt();
        this.mSeatAutoAdjust = in.readInt();
        this.mKeyUnlockMode = in.readInt();
        this.mNearfieldUnlockMode = in.readInt();
        this.mApproachUnlockMode = in.readInt();
        this.mLeaveAutoLockMode = in.readInt();
        this.mInductiveTailgate = in.readInt();
        this.mInductiveDoorHandle = in.readInt();
        this.mLeftRearviewDowndip = in.readInt();
        this.mRightRearviewDowndip = in.readInt();
        this.mOuterRearviewFold = in.readInt();
        this.mDriverSeatAutoWlcm = in.readInt();
        this.mSteeringWheelDefine = in.readInt();
        this.mHvacCustomSwitch = in.readInt();
        this.mWelcomeSoundOn = in.readInt();
        this.mWelcomeSoundType = in.readInt();
        this.mWelcomeLightTime = in.readInt();
        this.mHomeLightTime = in.readInt();
        this.mAutoMainBeamControl = in.readInt();
        this.mAmbtLightGlbOn = in.readInt();
        this.mAmbtLightOpenMode = in.readInt();
        this.mAmbtLightDrvMode = in.readInt();
        this.mAmbtLightColor = in.readInt();
        this.mAmbtLightBrightness = in.readInt();
        this.mAmbtLightBreathingOn = in.readInt();
        this.mAmbtLightWlcmOn = in.readInt();
        this.mAmbtLightWlcmMode = in.readInt();
        this.mStartLinkage = in.readInt();
        this.mVoiceLinkage = in.readInt();
        this.mMusicLinkage = in.readInt();
        this.mSpeedAsstSlifWarning = in.readInt();
        this.mSpeedAsstMode = in.readInt();
        this.mAccTjaMode = in.readInt();
        this.mLaneKeepingAsstMode = in.readInt();
        this.mLaneKeepingAsstSen = in.readInt();
        this.mLaneKeepingWarningSound = in.readInt();
        this.mLaneKeepingVibration = in.readInt();
        this.mLkaSysFailure = in.readInt();
        this.mLdwSysFailure = in.readInt();
        this.mTrafficJamAsstOn = in.readInt();
        this.mFcwAlarmMode = in.readInt();
        this.mFcwAutoBrakeMode = in.readInt();
        this.mFcwSensitivity = in.readInt();
        this.mFcwSysFailure = in.readInt();
        this.mAutoEmergencyBraking = in.readInt();
        this.mRearDriveAsstSys = in.readInt();
        this.mBlindSpotDetection = in.readInt();
        this.mLaneChangeAsst = in.readInt();
        this.mRearCrossTrafficSys = in.readInt();
        this.mRearCollisionWarning = in.readInt();
        this.mRctbSysFailure = in.readInt();
        this.mRdaSysStatus = in.readInt();
        this.mParkingWarning = in.readInt();
        this.mUnsteadyDrivingWarning = in.readInt();
        this.mUnsteadyDrivingWarningSen = in.readInt();
        this.mUdwSysFailure = in.readInt();
        this.mDriverMonitorSys = in.readInt();
        this.mDriverMonitorSysWarningSound = in.readInt();
        this.mDriverMonitorSysVibration = in.readInt();
        this.mDriveMode = in.readInt();
        this.mElectricPowertrainLevel = in.readInt();
        this.mSteeringLevel = in.readInt();
        this.mBrakePedalLevel = in.readInt();
        this.mRegenerativeBrakeSwitch = in.readInt();
        this.mPsgSafetyAirbagOn = in.readInt();
        this.mPsgSafetyAirbagStatus = in.readInt();
        this.mSdmAvailable = in.readInt();
        this.mRdaAvlbly = in.readInt();
        this.mWelcomeSoundAvlbly = in.readInt();
        this.mAmbtLightAvlbly = in.readInt();
        this.mBusOnOffStatus = in.readInt();
        this.mLampHomeOn = in.readInt();
        this.mLampWelcomeOn = in.readInt();
        this.mCarSearchFeedback = in.readInt();
        this.mFarewellLightMode = in.readInt();
        this.mRegenerativeLevel = in.readInt();
        this.mRegenerativeLevelDisable = in.readInt();
        this.mSignalPedal = in.readInt();
        this.mSignalPedalLnhbReg = in.readInt();
        this.mLongerEndurance = in.readInt();
        this.mLongerEnduranceRecommend = in.readInt();
        this.mLongerEnduranceDisable = in.readInt();
        this.mBrakeToStandstill = in.readInt();
        this.mPowerModeSwitch = in.readInt();
        this.mAutoHoldSwitch = in.readInt();
        this.mCarSearchFeedbackSwitch = in.readInt();
        this.mLightRearFogSwitch = in.readInt();
        this.mLightFrontFogSwitch = in.readInt();
        this.mTowingModeSwitch = in.readInt();
        this.mTdmAvlbly = in.readInt();
        this.mTrlrCnctSts = in.readInt();
        this.mPdcChangeSts = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mMarkedSignalId);
        parcel.writeInt(this.mAutoModeAirVolume);
        parcel.writeInt(this.mHvacEconLinkage);
        parcel.writeInt(this.mDefrostLinkage);
        parcel.writeInt(this.mAqsSensitivity);
        parcel.writeInt(this.mSeatHeatVentLinkage);
        parcel.writeFloat(this.mElectricTailgatePos);
        parcel.writeInt(this.mDrivingAutoLock);
        parcel.writeInt(this.mStallingAutoUnlock);
        parcel.writeInt(this.mSeatAutoAdjust);
        parcel.writeInt(this.mKeyUnlockMode);
        parcel.writeInt(this.mNearfieldUnlockMode);
        parcel.writeInt(this.mApproachUnlockMode);
        parcel.writeInt(this.mLeaveAutoLockMode);
        parcel.writeInt(this.mInductiveTailgate);
        parcel.writeInt(this.mInductiveDoorHandle);
        parcel.writeInt(this.mLeftRearviewDowndip);
        parcel.writeInt(this.mRightRearviewDowndip);
        parcel.writeInt(this.mOuterRearviewFold);
        parcel.writeInt(this.mDriverSeatAutoWlcm);
        parcel.writeInt(this.mSteeringWheelDefine);
        parcel.writeInt(this.mHvacCustomSwitch);
        parcel.writeInt(this.mWelcomeSoundOn);
        parcel.writeInt(this.mWelcomeSoundType);
        parcel.writeInt(this.mWelcomeLightTime);
        parcel.writeInt(this.mHomeLightTime);
        parcel.writeInt(this.mAutoMainBeamControl);
        parcel.writeInt(this.mAmbtLightGlbOn);
        parcel.writeInt(this.mAmbtLightOpenMode);
        parcel.writeInt(this.mAmbtLightDrvMode);
        parcel.writeInt(this.mAmbtLightColor);
        parcel.writeInt(this.mAmbtLightBrightness);
        parcel.writeInt(this.mAmbtLightBreathingOn);
        parcel.writeInt(this.mAmbtLightWlcmOn);
        parcel.writeInt(this.mAmbtLightWlcmMode);
        parcel.writeInt(this.mStartLinkage);
        parcel.writeInt(this.mVoiceLinkage);
        parcel.writeInt(this.mMusicLinkage);
        parcel.writeInt(this.mSpeedAsstSlifWarning);
        parcel.writeInt(this.mSpeedAsstMode);
        parcel.writeInt(this.mAccTjaMode);
        parcel.writeInt(this.mLaneKeepingAsstMode);
        parcel.writeInt(this.mLaneKeepingAsstSen);
        parcel.writeInt(this.mLaneKeepingWarningSound);
        parcel.writeInt(this.mLaneKeepingVibration);
        parcel.writeInt(this.mLkaSysFailure);
        parcel.writeInt(this.mLdwSysFailure);
        parcel.writeInt(this.mTrafficJamAsstOn);
        parcel.writeInt(this.mFcwAlarmMode);
        parcel.writeInt(this.mFcwAutoBrakeMode);
        parcel.writeInt(this.mFcwSensitivity);
        parcel.writeInt(this.mFcwSysFailure);
        parcel.writeInt(this.mAutoEmergencyBraking);
        parcel.writeInt(this.mRearDriveAsstSys);
        parcel.writeInt(this.mBlindSpotDetection);
        parcel.writeInt(this.mLaneChangeAsst);
        parcel.writeInt(this.mRearCrossTrafficSys);
        parcel.writeInt(this.mRearCollisionWarning);
        parcel.writeInt(this.mRctbSysFailure);
        parcel.writeInt(this.mRdaSysStatus);
        parcel.writeInt(this.mParkingWarning);
        parcel.writeInt(this.mUnsteadyDrivingWarning);
        parcel.writeInt(this.mUnsteadyDrivingWarningSen);
        parcel.writeInt(this.mUdwSysFailure);
        parcel.writeInt(this.mDriverMonitorSys);
        parcel.writeInt(this.mDriverMonitorSysWarningSound);
        parcel.writeInt(this.mDriverMonitorSysVibration);
        parcel.writeInt(this.mDriveMode);
        parcel.writeInt(this.mElectricPowertrainLevel);
        parcel.writeInt(this.mSteeringLevel);
        parcel.writeInt(this.mBrakePedalLevel);
        parcel.writeInt(this.mRegenerativeBrakeSwitch);
        parcel.writeInt(this.mPsgSafetyAirbagOn);
        parcel.writeInt(this.mPsgSafetyAirbagStatus);
        parcel.writeInt(this.mSdmAvailable);
        parcel.writeInt(this.mRdaAvlbly);
        parcel.writeInt(this.mWelcomeSoundAvlbly);
        parcel.writeInt(this.mAmbtLightAvlbly);
        parcel.writeInt(this.mBusOnOffStatus);
        parcel.writeInt(this.mLampHomeOn);
        parcel.writeInt(this.mLampWelcomeOn);
        parcel.writeInt(this.mCarSearchFeedback);
        parcel.writeInt(this.mFarewellLightMode);
        parcel.writeInt(this.mRegenerativeLevel);
        parcel.writeInt(this.mRegenerativeLevelDisable);
        parcel.writeInt(this.mSignalPedal);
        parcel.writeInt(this.mSignalPedalLnhbReg);
        parcel.writeInt(this.mLongerEndurance);
        parcel.writeInt(this.mLongerEnduranceRecommend);
        parcel.writeInt(this.mLongerEnduranceDisable);
        parcel.writeInt(this.mBrakeToStandstill);
        parcel.writeInt(this.mPowerModeSwitch);
        parcel.writeInt(this.mAutoHoldSwitch);
        parcel.writeInt(this.mCarSearchFeedbackSwitch);
        parcel.writeInt(this.mLightRearFogSwitch);
        parcel.writeInt(this.mLightFrontFogSwitch);
        parcel.writeInt(this.mTowingModeSwitch);
        parcel.writeInt(this.mTdmAvlbly);
        parcel.writeInt(this.mTrlrCnctSts);
        parcel.writeInt(this.mPdcChangeSts);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public VehicleSettingBean m10clone() throws CloneNotSupportedException {
        return (VehicleSettingBean) super.clone();
    }

    public String toString() {
        return "VehicleSettingBean{mMarkedSignalId=" + this.mMarkedSignalId + ", mAutoModeAirVolume=" + this.mAutoModeAirVolume + ", mHvacEconLinkage=" + this.mHvacEconLinkage + ", mDefrostLinkage=" + this.mDefrostLinkage + ", mAqsSensitivity=" + this.mAqsSensitivity + ", mSeatHeatVentLinkage=" + this.mSeatHeatVentLinkage + ", mElectricTailgatePos=" + this.mElectricTailgatePos + ", mDrivingAutoLock=" + this.mDrivingAutoLock + ", mStallingAutoUnlock=" + this.mStallingAutoUnlock + ", mSeatAutoAdjust=" + this.mSeatAutoAdjust + ", mKeyUnlockMode=" + this.mKeyUnlockMode + ", mNearfieldUnlockMode=" + this.mNearfieldUnlockMode + ", mApproachUnlockMode=" + this.mApproachUnlockMode + ", mmLeaveAutoLockMode=" + this.mLeaveAutoLockMode + ", mInductiveTailgate=" + this.mInductiveTailgate + ", mInductiveDoorHandle=" + this.mInductiveDoorHandle + ", mLeftRearviewDowndip=" + this.mLeftRearviewDowndip + ", mRightRearviewDowndip=" + this.mRightRearviewDowndip + ", mOuterRearviewFold=" + this.mOuterRearviewFold + ", mDriverSeatAutoWlcm=" + this.mDriverSeatAutoWlcm + ", mSteeringWheelDefine=" + this.mSteeringWheelDefine + ", mHvacCustomSwitch=" + this.mHvacCustomSwitch + ", mWelcomeSoundOn=" + this.mWelcomeSoundOn + ", mWelcomeSoundType=" + this.mWelcomeSoundType + ", mWelcomeLightTime=" + this.mWelcomeLightTime + ", mHomeLightTime=" + this.mHomeLightTime + ", mAutoMainBeamControl=" + this.mAutoMainBeamControl + ", mAmbtLightGlbOn=" + this.mAmbtLightGlbOn + ", mAmbtLightOpenMode=" + this.mAmbtLightOpenMode + ", mAmbtLightDrvMode=" + this.mAmbtLightDrvMode + ", mAmbtLightColor=" + this.mAmbtLightColor + ", mAmbtLightBrightness=" + this.mAmbtLightBrightness + ", mAmbtLightBreathingOn=" + this.mAmbtLightBreathingOn + ", mAmbtLightWlcmOn=" + this.mAmbtLightWlcmOn + ", mAmbtLightWlcmMode=" + this.mAmbtLightWlcmMode + ", mStartLinkage=" + this.mStartLinkage + ", mVoiceLinkage=" + this.mVoiceLinkage + ", mMusicLinkage=" + this.mMusicLinkage + ", mSpeedAsstSlifWarning=" + this.mSpeedAsstSlifWarning + ", mSpeedAsstMode=" + this.mSpeedAsstMode + ", mAccTjaMode=" + this.mAccTjaMode + ", mLaneKeepingAsstMode=" + this.mLaneKeepingAsstMode + ", mLaneKeepingAsstSen=" + this.mLaneKeepingAsstSen + ", mLaneKeepingWarningSound=" + this.mLaneKeepingWarningSound + ", mLaneKeepingVibration=" + this.mLaneKeepingVibration + ", mLkaSysFailure=" + this.mLkaSysFailure + ", mLdwSysFailure=" + this.mLdwSysFailure + ", mTrafficJamAsstOn=" + this.mTrafficJamAsstOn + ", mFcwAlarmMode=" + this.mFcwAlarmMode + ", mFcwAutoBrakeMode=" + this.mFcwAutoBrakeMode + ", mFcwSensitivity=" + this.mFcwSensitivity + ", mFcwSysFailure=" + this.mFcwSysFailure + ", mAutoEmergencyBraking=" + this.mAutoEmergencyBraking + ", mRearDriveAsstSys=" + this.mRearDriveAsstSys + ", mBlindSpotDetection=" + this.mBlindSpotDetection + ", mLaneChangeAsst=" + this.mLaneChangeAsst + ", mRearCrossTrafficSys=" + this.mRearCrossTrafficSys + ", mRearCollisionWarning=" + this.mRearCollisionWarning + ", mRctbSysFailure=" + this.mRctbSysFailure + ", mRdaSysStatus=" + this.mRdaSysStatus + ", mParkingWarning=" + this.mParkingWarning + ", mUnsteadyDrivingWarning=" + this.mUnsteadyDrivingWarning + ", mUnsteadyDrivingWarningSen=" + this.mUnsteadyDrivingWarningSen + ", mUdwSysFailure=" + this.mUdwSysFailure + ", mDriverMonitorSys=" + this.mDriverMonitorSys + ", mDriverMonitorSysWarningSound=" + this.mDriverMonitorSysWarningSound + ", mDriverMonitorSysVibration=" + this.mDriverMonitorSysVibration + ", mDriveMode=" + this.mDriveMode + ", mElectricPowertrainCustom=" + this.mElectricPowertrainLevel + ", mSteeringCustom=" + this.mSteeringLevel + ", mBrakePedalCustom=" + this.mBrakePedalLevel + ", mRegenerativeBrakeSwitch=" + this.mRegenerativeBrakeSwitch + ", mPsgSafetyAirbagOn=" + this.mPsgSafetyAirbagOn + ", mPsgSafetyAirbagStatus=" + this.mPsgSafetyAirbagStatus + ", mSdmAvailable = " + this.mSdmAvailable + ", mRdaAvlbly=" + this.mRdaAvlbly + ", mWelcomeSoundAvlbly=" + this.mWelcomeSoundAvlbly + ", mAmbtLightAvlbly=" + this.mAmbtLightAvlbly + ", mBusOnOffStatus=" + this.mBusOnOffStatus + ", mLampHomeOn=" + this.mLampHomeOn + ", mLampWelcome=" + this.mLampWelcomeOn + ", mCarSearchFeedback=" + this.mCarSearchFeedback + ", mFarewellLightMode=" + this.mFarewellLightMode + ", mRegenerativeLevel=" + this.mRegenerativeLevel + ", mRegenerativeLevelDisable=" + this.mRegenerativeLevelDisable + ", mSignalPedal=" + this.mSignalPedal + ", mSignalPedalLnhbReg=" + this.mSignalPedalLnhbReg + ", mLongerEndurance=" + this.mLongerEndurance + ", mLongerEnduranceRecommend=" + this.mLongerEnduranceRecommend + ", mLongerEnduranceDisable=" + this.mLongerEnduranceDisable + ", mBrakeToStandstill=" + this.mBrakeToStandstill + ", mPowerModeSwitch=" + this.mPowerModeSwitch + ", mCarSearchFeedbackSwitch=" + this.mCarSearchFeedbackSwitch + ", mLightRearFogSwitch=" + this.mLightRearFogSwitch + ", mLightFrontFogSwitch=" + this.mLightFrontFogSwitch + ", mTowingModeSwitch=" + this.mTowingModeSwitch + ", mTdmAvlbly=" + this.mTdmAvlbly + ", mTrlrCnctSts=" + this.mTrlrCnctSts + ", mPdcChangeSts=" + this.mPdcChangeSts + '}';
    }

    public int getMarkedSignalId() {
        return this.mMarkedSignalId;
    }

    public void setMarkedSignalId(int id) {
        this.mMarkedSignalId = id;
    }

    public int getAutoModeAirVolume() {
        return this.mAutoModeAirVolume;
    }

    public void setAutoModeAirVolume(int autoModeAirVolume) {
        this.mAutoModeAirVolume = autoModeAirVolume;
    }

    public int getHvacEconLinkage() {
        return this.mHvacEconLinkage;
    }

    public void setHvacEconLinkage(int hvacEconLinkage) {
        this.mHvacEconLinkage = hvacEconLinkage;
    }

    public int getDefrostLinkage() {
        return this.mDefrostLinkage;
    }

    public void setDefrostLinkage(int defrostLinkage) {
        this.mDefrostLinkage = defrostLinkage;
    }

    public int getAqsSensitivity() {
        return this.mAqsSensitivity;
    }

    public void setAqsSensitivity(int sensitivity) {
        this.mAqsSensitivity = sensitivity;
    }

    public int getSeatHeatVentLinkage() {
        return this.mSeatHeatVentLinkage;
    }

    public void setSeatHeatVentLinkage(int seatHeatVentLinkage) {
        this.mSeatHeatVentLinkage = seatHeatVentLinkage;
    }

    public float getElectricTailgatePos() {
        return this.mElectricTailgatePos;
    }

    public void setElectricTailgatePos(float electricTailgatePos) {
        this.mElectricTailgatePos = electricTailgatePos;
    }

    public int getDrivingAutoLock() {
        return this.mDrivingAutoLock;
    }

    public void setDrivingAutoLock(int drivingAutoLock) {
        this.mDrivingAutoLock = drivingAutoLock;
    }

    public int getStallingAutoUnlock() {
        return this.mStallingAutoUnlock;
    }

    public void setStallingAutoUnlock(int stallingAutoUnlock) {
        this.mStallingAutoUnlock = stallingAutoUnlock;
    }

    public int getSeatAutoAdjust() {
        return this.mSeatAutoAdjust;
    }

    public void setSeatAutoAdjust(int seatAutoAdjust) {
        this.mSeatAutoAdjust = seatAutoAdjust;
    }

    public int getKeyUnlockMode() {
        return this.mKeyUnlockMode;
    }

    public void setKeyUnlockMode(int keyUnlockMode) {
        this.mKeyUnlockMode = keyUnlockMode;
    }

    public int getNearfieldUnlockMode() {
        return this.mNearfieldUnlockMode;
    }

    public void setNearfieldUnlockMode(int nearfieldUnlockMode) {
        this.mNearfieldUnlockMode = nearfieldUnlockMode;
    }

    public int getApproachUnlockMode() {
        return this.mApproachUnlockMode;
    }

    public void setApproachUnlockMode(int approachUnlockMode) {
        this.mApproachUnlockMode = approachUnlockMode;
    }

    public int getLeaveAutoLockMode() {
        return this.mLeaveAutoLockMode;
    }

    public void setLeaveAutoLockMode(int leaveAutoLockMode) {
        this.mLeaveAutoLockMode = leaveAutoLockMode;
    }

    public int getInductiveTailgate() {
        return this.mInductiveTailgate;
    }

    public void setInductiveTailgate(int inductiveTailgate) {
        this.mInductiveTailgate = inductiveTailgate;
    }

    public int getInductiveDoorHandle() {
        return this.mInductiveDoorHandle;
    }

    public void setInductiveDoorHandle(int inductiveDoorHandle) {
        this.mInductiveDoorHandle = inductiveDoorHandle;
    }

    public int getLeftRearviewDowndip() {
        return this.mLeftRearviewDowndip;
    }

    public void setLeftRearviewDowndip(int leftRearviewDowndip) {
        this.mLeftRearviewDowndip = leftRearviewDowndip;
    }

    public int getRightRearviewDowndip() {
        return this.mRightRearviewDowndip;
    }

    public void setRightRearviewDowndip(int rightRearviewDowndip) {
        this.mRightRearviewDowndip = rightRearviewDowndip;
    }

    public int getOuterRearviewFold() {
        return this.mOuterRearviewFold;
    }

    public void setOuterRearviewFold(int outerRearviewFold) {
        this.mOuterRearviewFold = outerRearviewFold;
    }

    public int getDriverSeatAutoWlcm() {
        return this.mDriverSeatAutoWlcm;
    }

    public void setDriverSeatAutoWlcm(int driverSeatAutoWlcm) {
        this.mDriverSeatAutoWlcm = driverSeatAutoWlcm;
    }

    public int getSteeringWheelDefine() {
        return this.mSteeringWheelDefine;
    }

    public void setSteeringWheelDefine(int steeringWheelDefine) {
        this.mSteeringWheelDefine = steeringWheelDefine;
    }

    public int getHvacCustomSwitch() {
        return this.mHvacCustomSwitch;
    }

    public void setHvacCustomSwitch(int hvacCustomSwitch) {
        this.mHvacCustomSwitch = hvacCustomSwitch;
    }

    public int getWelcomeSoundOn() {
        return this.mWelcomeSoundOn;
    }

    public void setWelcomeSoundOn(int welcomeSoundOn) {
        this.mWelcomeSoundOn = welcomeSoundOn;
    }

    public int getWelcomeSoundType() {
        return this.mWelcomeSoundType;
    }

    public void setWelcomeSoundType(int welcomeSoundType) {
        this.mWelcomeSoundType = welcomeSoundType;
    }

    public int getWelcomeLightTime() {
        return this.mWelcomeLightTime;
    }

    public void setWelcomeLightTime(int welcomeLightTime) {
        this.mWelcomeLightTime = welcomeLightTime;
    }

    public int getHomeLightTime() {
        return this.mHomeLightTime;
    }

    public void setHomeLightTime(int homeLightTime) {
        this.mHomeLightTime = homeLightTime;
    }

    public int getAutoMainBeamControl() {
        return this.mAutoMainBeamControl;
    }

    public void setAutoMainBeamControl(int autoMainBeam) {
        this.mAutoMainBeamControl = autoMainBeam;
    }

    public int getAmbtLightGlbOn() {
        return this.mAmbtLightGlbOn;
    }

    public void setAmbtLightGlbOn(int ambtLightGlbOn) {
        this.mAmbtLightGlbOn = ambtLightGlbOn;
    }

    public int getAmbtLightOpenMode() {
        return this.mAmbtLightOpenMode;
    }

    public void setAmbtLightOpenMode(int ambtLightOpenMode) {
        this.mAmbtLightOpenMode = ambtLightOpenMode;
    }

    public int getAmbtLightDrvMode() {
        return this.mAmbtLightDrvMode;
    }

    public void setAmbtLightDrvMode(int ambtLightDrvMode) {
        this.mAmbtLightDrvMode = ambtLightDrvMode;
    }

    public int getAmbtLightColor() {
        return this.mAmbtLightColor;
    }

    public void setAmbtLightColor(int ambtLightColor) {
        this.mAmbtLightColor = ambtLightColor;
    }

    public int getAmbtLightBrightness() {
        return this.mAmbtLightBrightness;
    }

    public void setAmbtLightBrightness(int ambtLightBrightness) {
        this.mAmbtLightBrightness = ambtLightBrightness;
    }

    public int getAmbtLightBreathingOn() {
        return this.mAmbtLightBreathingOn;
    }

    public void setAmbtLightBreathingOn(int ambtLightBreathingOn) {
        this.mAmbtLightBreathingOn = ambtLightBreathingOn;
    }

    public int getAmbtLightWlcmOn() {
        return this.mAmbtLightWlcmOn;
    }

    public void setAmbtLightWlcmOn(int ambtLightWlcmOn) {
        this.mAmbtLightWlcmOn = ambtLightWlcmOn;
    }

    public int getAmbtLightWlcmMode() {
        return this.mAmbtLightWlcmMode;
    }

    public void setAmbtLightWlcmMode(int ambtLightWlcmMode) {
        this.mAmbtLightWlcmMode = ambtLightWlcmMode;
    }

    public int getStartLinkage() {
        return this.mStartLinkage;
    }

    public void setStartLinkage(int startLinkage) {
        this.mStartLinkage = startLinkage;
    }

    public int getVoiceLinkage() {
        return this.mVoiceLinkage;
    }

    public void setVoiceLinkage(int voiceLinkage) {
        this.mVoiceLinkage = voiceLinkage;
    }

    public int getMusicLinkage() {
        return this.mMusicLinkage;
    }

    public void setMusicLinkage(int musicLinkage) {
        this.mMusicLinkage = musicLinkage;
    }

    public int getSpeedAsstSlifWarning() {
        return this.mSpeedAsstSlifWarning;
    }

    public void setSpeedAsstSlifWarning(int speedAsstSlifWarning) {
        this.mSpeedAsstSlifWarning = speedAsstSlifWarning;
    }

    public int getSpeedAsstMode() {
        return this.mSpeedAsstMode;
    }

    public void setSpeedAsstMode(int speedAsstMode) {
        this.mSpeedAsstMode = speedAsstMode;
    }

    public int getAccTjaMode() {
        return this.mAccTjaMode;
    }

    public void setAccTjaMode(int accTjaMode) {
        this.mAccTjaMode = accTjaMode;
    }

    public int getLaneKeepingAsstMode() {
        return this.mLaneKeepingAsstMode;
    }

    public void setLaneKeepingAsstMode(int laneKeepingAsstMode) {
        this.mLaneKeepingAsstMode = laneKeepingAsstMode;
    }

    public int getLaneKeepingAsstSen() {
        return this.mLaneKeepingAsstSen;
    }

    public void setLaneKeepingAsstSen(int sensitivity) {
        this.mLaneKeepingAsstSen = sensitivity;
    }

    public int getLaneKeepingWarningSound() {
        return this.mLaneKeepingWarningSound;
    }

    public void setLaneKeepingWarningSound(int laneKeepingWarningSound) {
        this.mLaneKeepingWarningSound = laneKeepingWarningSound;
    }

    public int getLaneKeepingVibration() {
        return this.mLaneKeepingVibration;
    }

    public void setLaneKeepingVibration(int laneKeepingVibration) {
        this.mLaneKeepingVibration = laneKeepingVibration;
    }

    public int getLkaSysFailure() {
        return this.mLkaSysFailure;
    }

    public void setLkaSysFailure(int lkaSysFailure) {
        this.mLkaSysFailure = lkaSysFailure;
    }

    public int getLdwSysFailure() {
        return this.mLdwSysFailure;
    }

    public void setLdwSysFailure(int ldwSysFailure) {
        this.mLdwSysFailure = ldwSysFailure;
    }

    public int getTrafficJamAsstOn() {
        return this.mTrafficJamAsstOn;
    }

    public void setTrafficJamAsstOn(int trafficJamAsstOn) {
        this.mTrafficJamAsstOn = trafficJamAsstOn;
    }

    public int getFcwAlarmMode() {
        return this.mFcwAlarmMode;
    }

    public void setFcwAlarmMode(int fcwAlarmMode) {
        this.mFcwAlarmMode = fcwAlarmMode;
    }

    public int getFcwAutoBrakeMode() {
        return this.mFcwAutoBrakeMode;
    }

    public void setFcwAutoBrakeMode(int fcwAutoBrakeMode) {
        this.mFcwAutoBrakeMode = fcwAutoBrakeMode;
    }

    public int getFcwSensitivity() {
        return this.mFcwSensitivity;
    }

    public void setFcwSensitivity(int sensitivity) {
        this.mFcwSensitivity = sensitivity;
    }

    public int getFcwSysFailure() {
        return this.mFcwSysFailure;
    }

    public void setFcwSysFailure(int fcwSysFailure) {
        this.mFcwSysFailure = fcwSysFailure;
    }

    public int getAutoEmergencyBraking() {
        return this.mAutoEmergencyBraking;
    }

    public void setAutoEmergencyBraking(int autoEmergencyBraking) {
        this.mAutoEmergencyBraking = autoEmergencyBraking;
    }

    public int getRearDriveAsstSys() {
        return this.mRearDriveAsstSys;
    }

    public void setRearDriveAsstSys(int rearDriveAsstSys) {
        this.mRearDriveAsstSys = rearDriveAsstSys;
    }

    public int getBlindSpotDetection() {
        return this.mBlindSpotDetection;
    }

    public void setBlindSpotDetection(int blindSpotDetection) {
        this.mBlindSpotDetection = blindSpotDetection;
    }

    public int getLaneChangeAsst() {
        return this.mLaneChangeAsst;
    }

    public void setLaneChangeAsst(int laneChangeAsst) {
        this.mLaneChangeAsst = laneChangeAsst;
    }

    public int getRearCrossTrafficSys() {
        return this.mRearCrossTrafficSys;
    }

    public void setRearCrossTrafficSys(int rearCrossTrafficSys) {
        this.mRearCrossTrafficSys = rearCrossTrafficSys;
    }

    public int getRearCollisionWarning() {
        return this.mRearCollisionWarning;
    }

    public void setRearCollisionWarning(int rearCollisionWarning) {
        this.mRearCollisionWarning = rearCollisionWarning;
    }

    public int getRctbSysFailure() {
        return this.mRctbSysFailure;
    }

    public void setRctbSysFailure(int rctbSysFailure) {
        this.mRctbSysFailure = rctbSysFailure;
    }

    public int getRdaSysStatus() {
        return this.mRdaSysStatus;
    }

    public void setRdaSysStatus(int rdaSysStatus) {
        this.mRdaSysStatus = rdaSysStatus;
    }

    public int getParkingWarning() {
        return this.mParkingWarning;
    }

    public void setParkingWarning(int parkingWarning) {
        this.mParkingWarning = parkingWarning;
    }

    public int getUnsteadyDrivingWarning() {
        return this.mUnsteadyDrivingWarning;
    }

    public void setUnsteadyDrivingWarning(int unsteadyDrivingWarning) {
        this.mUnsteadyDrivingWarning = unsteadyDrivingWarning;
    }

    public int getUnsteadyDrivingWarningSen() {
        return this.mUnsteadyDrivingWarningSen;
    }

    public void setUnsteadyDrivingWarningSen(int sensitivity) {
        this.mUnsteadyDrivingWarningSen = sensitivity;
    }

    public int getUdwSysFailure() {
        return this.mUdwSysFailure;
    }

    public void setUdwSysFailure(int udwSysFailure) {
        this.mUdwSysFailure = udwSysFailure;
    }

    public int getDriverMonitorSys() {
        return this.mDriverMonitorSys;
    }

    public void setDriverMonitorSys(int driverMonitorSys) {
        this.mDriverMonitorSys = driverMonitorSys;
    }

    public int getDriverMonitorSysWarningSound() {
        return this.mDriverMonitorSysWarningSound;
    }

    public void setDriverMonitorSysWarningSound(int warningSound) {
        this.mDriverMonitorSysWarningSound = warningSound;
    }

    public int getDriverMonitorSysVibration() {
        return this.mDriverMonitorSysVibration;
    }

    public void setDriverMonitorSysVibration(int vibration) {
        this.mDriverMonitorSysVibration = vibration;
    }

    public int getDriveMode() {
        return this.mDriveMode;
    }

    public void setDriveMode(int driveMode) {
        this.mDriveMode = driveMode;
    }

    public int getElectricPowertrainLevel() {
        return this.mElectricPowertrainLevel;
    }

    public void setElectricPowertrainLevel(int electricPowertrainLevel) {
        this.mElectricPowertrainLevel = electricPowertrainLevel;
    }

    public int getSteeringLevel() {
        return this.mSteeringLevel;
    }

    public void setSteeringLevel(int steeringLevel) {
        this.mSteeringLevel = steeringLevel;
    }

    public int getBrakePedalLevel() {
        return this.mBrakePedalLevel;
    }

    public void setBrakePedalLevel(int brakePedalLevel) {
        this.mBrakePedalLevel = brakePedalLevel;
    }

    public int getRegenerativeBrakeSwitch() {
        return this.mRegenerativeBrakeSwitch;
    }

    public void setRegenerativeBrakeSwitch(int regenerativeBrakeSwitch) {
        this.mRegenerativeBrakeSwitch = regenerativeBrakeSwitch;
    }

    public int getPsgSafetyAirbagOn() {
        return this.mPsgSafetyAirbagOn;
    }

    public void setPsgSafetyAirbagOn(int psgAirbagOn) {
        this.mPsgSafetyAirbagOn = psgAirbagOn;
    }

    public int getPsgSafetyAirbagStatus() {
        return this.mPsgSafetyAirbagStatus;
    }

    public void setPsgSafetyAirbagStatus(int psgAirbagStatus) {
        this.mPsgSafetyAirbagStatus = psgAirbagStatus;
    }

    public int getSdmAvailableStatus() {
        return this.mSdmAvailable;
    }

    public void setSdmAvailableStatus(int status) {
        this.mSdmAvailable = status;
    }

    public int getRdaAvlbly() {
        return this.mRdaAvlbly;
    }

    public void setRdaAvlbly(int rdaAvlbly) {
        this.mRdaAvlbly = rdaAvlbly;
    }

    public int getWelcomeSoundAvlbly() {
        return this.mWelcomeSoundAvlbly;
    }

    public void setWelcomeSoundAvlbly(int welcomeSoundAvlbly) {
        this.mWelcomeSoundAvlbly = welcomeSoundAvlbly;
    }

    public int getAmbtLightAvlbly() {
        return this.mAmbtLightAvlbly;
    }

    public void setAmbtLightAvlbly(int ambtLightAvlbly) {
        this.mAmbtLightAvlbly = ambtLightAvlbly;
    }

    public int getBusStatus() {
        return this.mBusOnOffStatus;
    }

    public void setBusStatus(int busStatus) {
        this.mBusOnOffStatus = busStatus;
    }

    public int getLampHomeOn() {
        return this.mLampHomeOn;
    }

    public void setLampHomeOn(int lampHomeOn) {
        this.mLampHomeOn = lampHomeOn;
    }

    public int getLampWelcomeOn() {
        return this.mLampWelcomeOn;
    }

    public void setLampWelcomeOn(int lampWelcomeOn) {
        this.mLampWelcomeOn = lampWelcomeOn;
    }

    public int getCarSearchFeedback() {
        return this.mCarSearchFeedback;
    }

    public void setCarSearchFeedback(int carSearchFeedback) {
        this.mCarSearchFeedback = carSearchFeedback;
    }

    public int getCarSearchFeedbackSwitch() {
        return this.mCarSearchFeedbackSwitch;
    }

    public void setCarSearchFeedbackSwitch(int carSearchFeedbackSwitch) {
        this.mCarSearchFeedbackSwitch = carSearchFeedbackSwitch;
    }

    public int getLightRearFogSwitch() {
        return this.mLightRearFogSwitch;
    }

    public void setLightRearFogSwitch(int lightRearFogSwitch) {
        this.mLightRearFogSwitch = lightRearFogSwitch;
    }

    public int getLightFrontFogSwitch() {
        return this.mLightFrontFogSwitch;
    }

    public void setLightFrontFogSwitch(int lightFrontFogSwitch) {
        this.mLightFrontFogSwitch = lightFrontFogSwitch;
    }

    public int getFarewellLightMode() {
        return this.mFarewellLightMode;
    }

    public void setFarewellLightMode(int farewellLightMode) {
        this.mFarewellLightMode = farewellLightMode;
    }

    public int getRegenerativeLevel() {
        return this.mRegenerativeLevel;
    }

    public void setRegenerativeLevel(int regenerativeLevel) {
        this.mRegenerativeLevel = regenerativeLevel;
    }

    public int getRegenerativeLevelDisable() {
        return this.mRegenerativeLevelDisable;
    }

    public void setRegenerativeLevelDisable(int regenerativeLevelDisable) {
        this.mRegenerativeLevelDisable = regenerativeLevelDisable;
    }

    public int getSignalPedal() {
        return this.mSignalPedal;
    }

    public void setSignalPedal(int signalPedal) {
        this.mSignalPedal = signalPedal;
    }

    public int getSignalPedalLnhbReg() {
        return this.mSignalPedalLnhbReg;
    }

    public void setSignalPedalLnhbReg(int signalPedalLnhbReg) {
        this.mSignalPedalLnhbReg = signalPedalLnhbReg;
    }

    public int getLongerEndurance() {
        return this.mLongerEndurance;
    }

    public void setLongerEndurance(int longerEndurance) {
        this.mLongerEndurance = longerEndurance;
    }

    public int getLongerEnduranceRecommend() {
        return this.mLongerEnduranceRecommend;
    }

    public void setLongerEnduranceRecommend(int longerEnduranceRecommend) {
        this.mLongerEnduranceRecommend = longerEnduranceRecommend;
    }

    public int getLongerEnduranceDisable() {
        return this.mLongerEnduranceDisable;
    }

    public void setLongerEnduranceDisable(int longerEnduranceDisable) {
        this.mLongerEnduranceDisable = longerEnduranceDisable;
    }

    public int getBrakeToStandstill() {
        return this.mBrakeToStandstill;
    }

    public void setBrakeToStandstill(int brakeToStandstill) {
        this.mBrakeToStandstill = brakeToStandstill;
    }

    public int getPowerModeSwitch() {
        return this.mPowerModeSwitch;
    }

    public void setPowerModeSwitch(int powerModeSwitch) {
        this.mPowerModeSwitch = powerModeSwitch;
    }

    public int getAutoHoldSwitch() {
        return this.mAutoHoldSwitch;
    }

    public void setAutoHoldSwitch(int autoHoldSwitch) {
        this.mAutoHoldSwitch = autoHoldSwitch;
    }

    public int getTowingModeSwitch() {
        return this.mTowingModeSwitch;
    }

    public void setTowingModeSwitch(int towingModeSwitch) {
        this.mTowingModeSwitch = towingModeSwitch;
    }

    public int getTdmAvlbly() {
        return this.mTdmAvlbly;
    }

    public void setTdmAvlbly(int tdmAvlbly) {
        this.mTdmAvlbly = tdmAvlbly;
    }

    public int getTrlrCnctSts() {
        return this.mTrlrCnctSts;
    }

    public void setTrlrCnctSts(int trlrCnctSts) {
        this.mTrlrCnctSts = trlrCnctSts;
    }

    public int getPdcChangeSts() {
        return this.mPdcChangeSts;
    }

    public void setPdcChangeSts(int pdcChangeSts) {
        this.mPdcChangeSts = pdcChangeSts;
    }
}
