package com.saicmotor.sdk.vehiclesettings.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ChargingPile implements Parcelable, Comparable<ChargingPile> {
    public static final Parcelable.Creator<ChargingPile> CREATOR = new Parcelable.Creator<ChargingPile>() { // from class: com.saicmotor.sdk.vehiclesettings.bean.ChargingPile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChargingPile createFromParcel(Parcel in) {
            return new ChargingPile(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChargingPile[] newArray(int size) {
            return new ChargingPile[size];
        }
    };
    private String mAddress;
    private boolean mIsAvailable;
    private boolean mIsConnected;
    private String mName;
    private long mTimestamp;

    public ChargingPile() {
        this.mName = "";
        this.mAddress = "";
        this.mIsConnected = false;
        this.mIsAvailable = false;
        this.mTimestamp = 0L;
    }

    protected ChargingPile(Parcel in) {
        this.mName = "";
        this.mAddress = "";
        this.mIsConnected = false;
        this.mIsAvailable = false;
        this.mTimestamp = 0L;
        this.mName = in.readString();
        this.mAddress = in.readString();
        this.mIsConnected = in.readByte() != 0;
        this.mIsAvailable = in.readByte() != 0;
        this.mTimestamp = in.readLong();
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getKey() {
        return this.mName + this.mAddress;
    }

    public boolean isConnected() {
        return this.mIsConnected;
    }

    public void setConnected(boolean connected) {
        this.mIsConnected = connected;
    }

    public boolean isAvailable() {
        return this.mIsAvailable;
    }

    public void setAvailable(boolean available) {
        this.mIsAvailable = available;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long time) {
        this.mTimestamp = time;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mAddress);
        parcel.writeByte(this.mIsConnected ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mIsAvailable ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.mTimestamp);
    }

    @Override // java.lang.Comparable
    public int compareTo(ChargingPile chargingPile) {
        int i = (chargingPile.mIsAvailable ? 1 : 0) - (this.mIsAvailable ? 1 : 0);
        if (i == 0) {
            int i2 = (chargingPile.mIsConnected ? 1 : 0) - (this.mIsConnected ? 1 : 0);
            if (i2 == 0) {
                return (int) (chargingPile.mTimestamp - this.mTimestamp);
            }
            return i2;
        }
        return i;
    }

    public String toString() {
        return "ChargingPile{mName='" + this.mName + ", mAddress='" + this.mAddress + ", mIsConnected=" + this.mIsConnected + ", mIsAvailable=" + this.mIsAvailable + ", mTimestamp=" + this.mTimestamp + '}';
    }
}
