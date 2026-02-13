package com.saicmotor.sdk.vehiclesettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.bean.ChargingPile;
import java.util.List;

/* loaded from: classes2.dex */
public interface IBleStateCallback extends IInterface {
    void onBluetoothStateChanged(int i) throws RemoteException;

    void onConnectionStateChanged(String str, int i) throws RemoteException;

    void onDeviceDeleted(String str, int i) throws RemoteException;

    void onDeviceStateChanged(List<ChargingPile> list) throws RemoteException;

    void onScanStateChanged(int i) throws RemoteException;

    public static class Default implements IBleStateCallback {
        @Override // com.saicmotor.sdk.vehiclesettings.IBleStateCallback
        public void onBluetoothStateChanged(int state) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IBleStateCallback
        public void onScanStateChanged(int state) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IBleStateCallback
        public void onConnectionStateChanged(String deviceName, int state) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IBleStateCallback
        public void onDeviceStateChanged(List<ChargingPile> list) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IBleStateCallback
        public void onDeviceDeleted(String deviceName, int state) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IBleStateCallback {
        private static final String DESCRIPTOR = "com.saicmotor.sdk.vehiclesettings.IBleStateCallback";
        static final int TRANSACTION_onBluetoothStateChanged = 1;
        static final int TRANSACTION_onConnectionStateChanged = 3;
        static final int TRANSACTION_onDeviceDeleted = 5;
        static final int TRANSACTION_onDeviceStateChanged = 4;
        static final int TRANSACTION_onScanStateChanged = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBleStateCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IBleStateCallback)) {
                return (IBleStateCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0 = data.readInt();
                onBluetoothStateChanged(_arg0);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                onScanStateChanged(_arg02);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                String _arg03 = data.readString();
                int _arg1 = data.readInt();
                onConnectionStateChanged(_arg03, _arg1);
                return true;
            }
            if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                List<ChargingPile> _arg04 = data.createTypedArrayList(ChargingPile.CREATOR);
                onDeviceStateChanged(_arg04);
                return true;
            }
            if (code != 5) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            String _arg05 = data.readString();
            int _arg12 = data.readInt();
            onDeviceDeleted(_arg05, _arg12);
            return true;
        }

        private static class Proxy implements IBleStateCallback {
            public static IBleStateCallback sDefaultImpl;
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

            @Override // com.saicmotor.sdk.vehiclesettings.IBleStateCallback
            public void onBluetoothStateChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBluetoothStateChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IBleStateCallback
            public void onScanStateChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onScanStateChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IBleStateCallback
            public void onConnectionStateChanged(String deviceName, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceName);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnectionStateChanged(deviceName, state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IBleStateCallback
            public void onDeviceStateChanged(List<ChargingPile> list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(list);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceStateChanged(list);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IBleStateCallback
            public void onDeviceDeleted(String deviceName, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceName);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceDeleted(deviceName, state);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBleStateCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IBleStateCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
