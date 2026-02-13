package com.saicmotor.sdk.screen;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IScreenStateListener extends IInterface {
    void onScreenOffStateChanged(int i, int i2) throws RemoteException;

    void onStateChanged(int i) throws RemoteException;

    public static class Default implements IScreenStateListener {
        @Override // com.saicmotor.sdk.screen.IScreenStateListener
        public void onScreenOffStateChanged(int state, int theme) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.screen.IScreenStateListener
        public void onStateChanged(int var1) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IScreenStateListener {
        private static final String DESCRIPTOR = "com.saicmotor.sdk.screen.IScreenStateListener";
        static final int TRANSACTION_onScreenOffStateChanged = 1;
        static final int TRANSACTION_onStateChanged = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IScreenStateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IScreenStateListener)) {
                return (IScreenStateListener) iin;
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
                int _arg1 = data.readInt();
                onScreenOffStateChanged(_arg0, _arg1);
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
            int _arg02 = data.readInt();
            onStateChanged(_arg02);
            return true;
        }

        private static class Proxy implements IScreenStateListener {
            public static IScreenStateListener sDefaultImpl;
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

            @Override // com.saicmotor.sdk.screen.IScreenStateListener
            public void onScreenOffStateChanged(int state, int theme) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeInt(theme);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onScreenOffStateChanged(state, theme);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.screen.IScreenStateListener
            public void onStateChanged(int var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStateChanged(var1);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IScreenStateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IScreenStateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
