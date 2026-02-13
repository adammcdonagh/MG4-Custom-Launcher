package com.saicmotor.sdk.screen;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.saicmotor.sdk.screen.IScreenStateListener;

/* loaded from: classes2.dex */
public interface IScreenManagerService extends IInterface {
    int getCurrentPowerMode() throws RemoteException;

    void registerListener(IScreenStateListener iScreenStateListener) throws RemoteException;

    void resumeScreenSleep(int i) throws RemoteException;

    void screenSleep(int i, boolean z, int i2) throws RemoteException;

    void screenWakeup(int i, boolean z) throws RemoteException;

    void unregisterListener(IScreenStateListener iScreenStateListener) throws RemoteException;

    public static class Default implements IScreenManagerService {
        @Override // com.saicmotor.sdk.screen.IScreenManagerService
        public void screenWakeup(int pid, boolean trans) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.screen.IScreenManagerService
        public void screenSleep(int pid, boolean showTime, int theme) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.screen.IScreenManagerService
        public void resumeScreenSleep(int pid) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.screen.IScreenManagerService
        public void registerListener(IScreenStateListener callback) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.screen.IScreenManagerService
        public void unregisterListener(IScreenStateListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.screen.IScreenManagerService
        public int getCurrentPowerMode() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IScreenManagerService {
        private static final String DESCRIPTOR = "com.saicmotor.sdk.screen.IScreenManagerService";
        static final int TRANSACTION_getCurrentPowerMode = 6;
        static final int TRANSACTION_registerListener = 4;
        static final int TRANSACTION_resumeScreenSleep = 3;
        static final int TRANSACTION_screenSleep = 2;
        static final int TRANSACTION_screenWakeup = 1;
        static final int TRANSACTION_unregisterListener = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IScreenManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IScreenManagerService)) {
                return (IScreenManagerService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg1;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    _arg1 = data.readInt() != 0;
                    screenWakeup(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    _arg1 = data.readInt() != 0;
                    int _arg2 = data.readInt();
                    screenSleep(_arg02, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    resumeScreenSleep(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    IScreenStateListener _arg04 = IScreenStateListener.Stub.asInterface(data.readStrongBinder());
                    registerListener(_arg04);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    IScreenStateListener _arg05 = IScreenStateListener.Stub.asInterface(data.readStrongBinder());
                    unregisterListener(_arg05);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getCurrentPowerMode();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IScreenManagerService {
            public static IScreenManagerService sDefaultImpl;
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

            @Override // com.saicmotor.sdk.screen.IScreenManagerService
            public void screenWakeup(int pid, boolean trans) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(pid);
                    _data.writeInt(trans ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().screenWakeup(pid, trans);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.screen.IScreenManagerService
            public void screenSleep(int pid, boolean showTime, int theme) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(pid);
                    _data.writeInt(showTime ? 1 : 0);
                    _data.writeInt(theme);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().screenSleep(pid, showTime, theme);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.screen.IScreenManagerService
            public void resumeScreenSleep(int pid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(pid);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resumeScreenSleep(pid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.screen.IScreenManagerService
            public void registerListener(IScreenStateListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.screen.IScreenManagerService
            public void unregisterListener(IScreenStateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.screen.IScreenManagerService
            public int getCurrentPowerMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPowerMode();
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

        public static boolean setDefaultImpl(IScreenManagerService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IScreenManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
