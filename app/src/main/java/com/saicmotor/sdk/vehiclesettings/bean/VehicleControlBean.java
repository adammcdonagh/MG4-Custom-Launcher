package com.saicmotor.sdk.vehiclesettings.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class VehicleControlBean implements Parcelable, Cloneable {
    public static final Parcelable.Creator<VehicleControlBean> CREATOR = new Parcelable.Creator<VehicleControlBean>() { // from class: com.saicmotor.sdk.vehiclesettings.bean.VehicleControlBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VehicleControlBean createFromParcel(Parcel in) {
            return new VehicleControlBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VehicleControlBean[] newArray(int size) {
            return new VehicleControlBean[size];
        }
    };
    private static final int DEFAULT_PDC_SWITCH = 3;
    private int mDoorLock;
    private float mDriverWindow;
    private float mElectricTailgateLock;
    private int mEspSwitch;
    private int mHdcSwitch;
    private float mLeftRearWindow;
    private int mMarkedSignalId;
    private float mPassengerWindow;
    private int mPdcSwitch;
    private float mRightRearWindow;
    private int mSunroofSwitch;
    private int mSunroofVentilation;
    private int mTcsOpngMd;
    private int mTcsOpngSts;
    private int mVseSts;

    public VehicleControlBean() {
        this.mMarkedSignalId = 0;
        this.mDoorLock = 0;
        this.mDriverWindow = 0.0f;
        this.mPassengerWindow = 0.0f;
        this.mLeftRearWindow = 0.0f;
        this.mRightRearWindow = 0.0f;
        this.mElectricTailgateLock = 0.0f;
        this.mSunroofSwitch = 0;
        this.mSunroofVentilation = 0;
        this.mEspSwitch = 0;
        this.mHdcSwitch = 0;
        this.mPdcSwitch = 3;
        this.mVseSts = 0;
        this.mTcsOpngMd = 0;
        this.mTcsOpngSts = 0;
    }

    protected VehicleControlBean(Parcel in) {
        this.mMarkedSignalId = 0;
        this.mDoorLock = 0;
        this.mDriverWindow = 0.0f;
        this.mPassengerWindow = 0.0f;
        this.mLeftRearWindow = 0.0f;
        this.mRightRearWindow = 0.0f;
        this.mElectricTailgateLock = 0.0f;
        this.mSunroofSwitch = 0;
        this.mSunroofVentilation = 0;
        this.mEspSwitch = 0;
        this.mHdcSwitch = 0;
        this.mPdcSwitch = 3;
        this.mVseSts = 0;
        this.mTcsOpngMd = 0;
        this.mTcsOpngSts = 0;
        this.mMarkedSignalId = in.readInt();
        this.mDoorLock = in.readInt();
        this.mDriverWindow = in.readFloat();
        this.mPassengerWindow = in.readFloat();
        this.mLeftRearWindow = in.readFloat();
        this.mRightRearWindow = in.readFloat();
        this.mElectricTailgateLock = in.readFloat();
        this.mSunroofSwitch = in.readInt();
        this.mSunroofVentilation = in.readInt();
        this.mEspSwitch = in.readInt();
        this.mHdcSwitch = in.readInt();
        this.mPdcSwitch = in.readInt();
        this.mVseSts = in.readInt();
        this.mTcsOpngMd = in.readInt();
        this.mTcsOpngSts = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mMarkedSignalId);
        parcel.writeInt(this.mDoorLock);
        parcel.writeFloat(this.mDriverWindow);
        parcel.writeFloat(this.mPassengerWindow);
        parcel.writeFloat(this.mLeftRearWindow);
        parcel.writeFloat(this.mRightRearWindow);
        parcel.writeFloat(this.mElectricTailgateLock);
        parcel.writeInt(this.mSunroofSwitch);
        parcel.writeInt(this.mSunroofVentilation);
        parcel.writeInt(this.mEspSwitch);
        parcel.writeInt(this.mHdcSwitch);
        parcel.writeInt(this.mPdcSwitch);
        parcel.writeInt(this.mVseSts);
        parcel.writeInt(this.mTcsOpngMd);
        parcel.writeInt(this.mTcsOpngSts);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public VehicleControlBean m9clone() throws CloneNotSupportedException {
        return (VehicleControlBean) super.clone();
    }

    public String toString() {
        return "VehicleControlBean{mMarkedSignalId=" + this.mMarkedSignalId + ", mDoorLock=" + this.mDoorLock + ", mDriverWindow=" + this.mDriverWindow + ", mPassengerWindow=" + this.mPassengerWindow + ", mLeftRearWindow=" + this.mLeftRearWindow + ", mRightRearWindow=" + this.mRightRearWindow + ", mElectricTailgateLock=" + this.mElectricTailgateLock + ", mSunroofSwitch=" + this.mSunroofSwitch + ", mSunroofVentilation=" + this.mSunroofVentilation + ", mEspSwitch=" + this.mEspSwitch + ", mHdcSwitch=" + this.mHdcSwitch + ", mPdcSwitch=" + this.mPdcSwitch + ", mVseSts=" + this.mVseSts + ", mTcsOpngMd=" + this.mTcsOpngMd + ", mTcsOpngSts=" + this.mTcsOpngSts + '}';
    }

    public int getMarkedSignalId() {
        return this.mMarkedSignalId;
    }

    public void setMarkedSignalId(int id) {
        this.mMarkedSignalId = id;
    }

    public int getDoorLock() {
        return this.mDoorLock;
    }

    public void setDoorLock(int doorLock) {
        this.mDoorLock = doorLock;
    }

    public float getDriverWindow() {
        return this.mDriverWindow;
    }

    public void setDriverWindow(float driverWindow) {
        this.mDriverWindow = driverWindow;
    }

    public float getPassengerWindow() {
        return this.mPassengerWindow;
    }

    public void setPassengerWindow(float passengerWindow) {
        this.mPassengerWindow = passengerWindow;
    }

    public float getLeftRearWindow() {
        return this.mLeftRearWindow;
    }

    public void setLeftRearWindow(float leftRearWindow) {
        this.mLeftRearWindow = leftRearWindow;
    }

    public float getRightRearWindow() {
        return this.mRightRearWindow;
    }

    public void setRightRearWindow(float rightRearWindow) {
        this.mRightRearWindow = rightRearWindow;
    }

    public float getElectricTailgateLock() {
        return this.mElectricTailgateLock;
    }

    public void setElectricTailgateLock(float electricTailgateLock) {
        this.mElectricTailgateLock = electricTailgateLock;
    }

    public int getSunroofSwitch() {
        return this.mSunroofSwitch;
    }

    public void setSunroofSwitch(int sunroofSwitch) {
        this.mSunroofSwitch = sunroofSwitch;
    }

    public int getSunroofVentilation() {
        return this.mSunroofVentilation;
    }

    public void setSunroofVentilation(int sunroofVentilation) {
        this.mSunroofVentilation = sunroofVentilation;
    }

    public int getEspSwitch() {
        return this.mEspSwitch;
    }

    public void setEspSwitch(int espSwitch) {
        this.mEspSwitch = espSwitch;
    }

    public int getHdcSwitch() {
        return this.mHdcSwitch;
    }

    public void setHdcSwitch(int hdcSwitch) {
        this.mHdcSwitch = hdcSwitch;
    }

    public int getPdcSwitch() {
        return this.mPdcSwitch;
    }

    public void setPdcSwitch(int pdcSwitch) {
        this.mPdcSwitch = pdcSwitch;
    }

    public int getVseSts() {
        return this.mVseSts;
    }

    public void setVseSts(int vseSts) {
        this.mVseSts = vseSts;
    }

    public int getTcsOpngMd() {
        return this.mTcsOpngMd;
    }

    public void setTcsOpngMd(int tcsOpngMd) {
        this.mTcsOpngMd = tcsOpngMd;
    }

    public int getTcsOpngSts() {
        return this.mTcsOpngSts;
    }

    public void setTcsOpngSts(int tcsOpngSts) {
        this.mTcsOpngSts = tcsOpngSts;
    }
}
