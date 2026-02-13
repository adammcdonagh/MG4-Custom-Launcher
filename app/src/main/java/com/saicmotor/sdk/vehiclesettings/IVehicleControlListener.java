package com.saicmotor.sdk.vehiclesettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleControlBean;

/* loaded from: classes2.dex */
public interface IVehicleControlListener extends IInterface {
    void onChangeEvent(VehicleControlBean vehicleControlBean) throws RemoteException;

    void onErrorEvent(String str, int i) throws RemoteException;

    public static class Default implements IVehicleControlListener {
        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlListener
        public void onChangeEvent(VehicleControlBean vehicleControlBean) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlListener
        public void onErrorEvent(String signalName, int code) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVehicleControlListener {
        private static final String DESCRIPTOR = "com.saicmotor.sdk.vehiclesettings.IVehicleControlListener";
        static final int TRANSACTION_onChangeEvent = 1;
        static final int TRANSACTION_onErrorEvent = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVehicleControlListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IVehicleControlListener)) {
                return (IVehicleControlListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            VehicleControlBean _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = VehicleControlBean.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                onChangeEvent(_arg0);
                return true;
            }
            if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            String _arg02 = data.readString();
            int _arg1 = data.readInt();
            onErrorEvent(_arg02, _arg1);
            return true;
        }

        private static class Proxy implements IVehicleControlListener {
            public static IVehicleControlListener sDefaultImpl;
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

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlListener
            public void onChangeEvent(VehicleControlBean vehicleControlBean) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (vehicleControlBean != null) {
                        _data.writeInt(1);
                        vehicleControlBean.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onChangeEvent(vehicleControlBean);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlListener
            public void onErrorEvent(String signalName, int code) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(signalName);
                    _data.writeInt(code);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onErrorEvent(signalName, code);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVehicleControlListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IVehicleControlListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
