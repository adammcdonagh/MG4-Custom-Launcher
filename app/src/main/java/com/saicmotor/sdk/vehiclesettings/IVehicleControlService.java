package com.saicmotor.sdk.vehiclesettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.saicmotor.sdk.vehiclesettings.IVehicleControlListener;
import com.saicmotor.sdk.vehiclesettings.bean.VehicleControlBean;

/* loaded from: classes2.dex */
public interface IVehicleControlService extends IInterface {
    void addVehicleControlListener(IVehicleControlListener iVehicleControlListener) throws RemoteException;

    int getDoorLock() throws RemoteException;

    float getDriveWindow() throws RemoteException;

    float getElectricTailgateLock() throws RemoteException;

    int getEspSwitch() throws RemoteException;

    int getHdcSwitch() throws RemoteException;

    float getLeftRearWindow() throws RemoteException;

    float getPassengerWindow() throws RemoteException;

    int getPdcSwitch() throws RemoteException;

    float getRightRearWindow() throws RemoteException;

    int getSunroofSwitch() throws RemoteException;

    int getSunroofVentilation() throws RemoteException;

    int getTcsOpngMd() throws RemoteException;

    int getTcsOpngSts() throws RemoteException;

    VehicleControlBean getVehicleControlStatus() throws RemoteException;

    int getVseSts() throws RemoteException;

    void removeVehicleControlListener(IVehicleControlListener iVehicleControlListener) throws RemoteException;

    boolean resetVehicleSettings(int i) throws RemoteException;

    void setDoorLock(int i) throws RemoteException;

    void setDriveWindow(float f) throws RemoteException;

    void setElectricTailgateLock(float f) throws RemoteException;

    void setEspSwitch(int i) throws RemoteException;

    void setHdcSwitch(int i) throws RemoteException;

    void setLeftRearWindow(float f) throws RemoteException;

    void setPassengerWindow(float f) throws RemoteException;

    void setPdcSwitch(int i) throws RemoteException;

    void setRightRearWindow(float f) throws RemoteException;

    void setSunroofSwitch(int i) throws RemoteException;

    void setSunroofVentilation(int i) throws RemoteException;

    public static class Default implements IVehicleControlService {
        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void addVehicleControlListener(IVehicleControlListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void removeVehicleControlListener(IVehicleControlListener listener) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public int getDoorLock() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setDoorLock(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public float getDriveWindow() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setDriveWindow(float signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public float getPassengerWindow() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setPassengerWindow(float signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public float getLeftRearWindow() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setLeftRearWindow(float signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public float getRightRearWindow() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setRightRearWindow(float signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public float getElectricTailgateLock() throws RemoteException {
            return 0.0f;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setElectricTailgateLock(float signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public int getSunroofSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setSunroofSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public int getSunroofVentilation() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setSunroofVentilation(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public int getEspSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setEspSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public int getHdcSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setHdcSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public int getPdcSwitch() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public void setPdcSwitch(int signalValue) throws RemoteException {
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public VehicleControlBean getVehicleControlStatus() throws RemoteException {
            return null;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public boolean resetVehicleSettings(int signalValue) throws RemoteException {
            return false;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public int getVseSts() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public int getTcsOpngMd() throws RemoteException {
            return 0;
        }

        @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
        public int getTcsOpngSts() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVehicleControlService {
        private static final String DESCRIPTOR = "com.saicmotor.sdk.vehiclesettings.IVehicleControlService";
        static final int TRANSACTION_addVehicleControlListener = 1;
        static final int TRANSACTION_getDoorLock = 3;
        static final int TRANSACTION_getDriveWindow = 5;
        static final int TRANSACTION_getElectricTailgateLock = 13;
        static final int TRANSACTION_getEspSwitch = 19;
        static final int TRANSACTION_getHdcSwitch = 21;
        static final int TRANSACTION_getLeftRearWindow = 9;
        static final int TRANSACTION_getPassengerWindow = 7;
        static final int TRANSACTION_getPdcSwitch = 23;
        static final int TRANSACTION_getRightRearWindow = 11;
        static final int TRANSACTION_getSunroofSwitch = 15;
        static final int TRANSACTION_getSunroofVentilation = 17;
        static final int TRANSACTION_getTcsOpngMd = 28;
        static final int TRANSACTION_getTcsOpngSts = 29;
        static final int TRANSACTION_getVehicleControlStatus = 25;
        static final int TRANSACTION_getVseSts = 27;
        static final int TRANSACTION_removeVehicleControlListener = 2;
        static final int TRANSACTION_resetVehicleSettings = 26;
        static final int TRANSACTION_setDoorLock = 4;
        static final int TRANSACTION_setDriveWindow = 6;
        static final int TRANSACTION_setElectricTailgateLock = 14;
        static final int TRANSACTION_setEspSwitch = 20;
        static final int TRANSACTION_setHdcSwitch = 22;
        static final int TRANSACTION_setLeftRearWindow = 10;
        static final int TRANSACTION_setPassengerWindow = 8;
        static final int TRANSACTION_setPdcSwitch = 24;
        static final int TRANSACTION_setRightRearWindow = 12;
        static final int TRANSACTION_setSunroofSwitch = 16;
        static final int TRANSACTION_setSunroofVentilation = 18;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVehicleControlService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IVehicleControlService)) {
                return (IVehicleControlService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    addVehicleControlListener(IVehicleControlListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeVehicleControlListener(IVehicleControlListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int doorLock = getDoorLock();
                    parcel2.writeNoException();
                    parcel2.writeInt(doorLock);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDoorLock(parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    float driveWindow = getDriveWindow();
                    parcel2.writeNoException();
                    parcel2.writeFloat(driveWindow);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDriveWindow(parcel.readFloat());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    float passengerWindow = getPassengerWindow();
                    parcel2.writeNoException();
                    parcel2.writeFloat(passengerWindow);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPassengerWindow(parcel.readFloat());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    float leftRearWindow = getLeftRearWindow();
                    parcel2.writeNoException();
                    parcel2.writeFloat(leftRearWindow);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLeftRearWindow(parcel.readFloat());
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    float rightRearWindow = getRightRearWindow();
                    parcel2.writeNoException();
                    parcel2.writeFloat(rightRearWindow);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRightRearWindow(parcel.readFloat());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    float electricTailgateLock = getElectricTailgateLock();
                    parcel2.writeNoException();
                    parcel2.writeFloat(electricTailgateLock);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setElectricTailgateLock(parcel.readFloat());
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sunroofSwitch = getSunroofSwitch();
                    parcel2.writeNoException();
                    parcel2.writeInt(sunroofSwitch);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSunroofSwitch(parcel.readInt());
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sunroofVentilation = getSunroofVentilation();
                    parcel2.writeNoException();
                    parcel2.writeInt(sunroofVentilation);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSunroofVentilation(parcel.readInt());
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int espSwitch = getEspSwitch();
                    parcel2.writeNoException();
                    parcel2.writeInt(espSwitch);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setEspSwitch(parcel.readInt());
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hdcSwitch = getHdcSwitch();
                    parcel2.writeNoException();
                    parcel2.writeInt(hdcSwitch);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    setHdcSwitch(parcel.readInt());
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int pdcSwitch = getPdcSwitch();
                    parcel2.writeNoException();
                    parcel2.writeInt(pdcSwitch);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPdcSwitch(parcel.readInt());
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    VehicleControlBean vehicleControlStatus = getVehicleControlStatus();
                    parcel2.writeNoException();
                    if (vehicleControlStatus != null) {
                        parcel2.writeInt(1);
                        vehicleControlStatus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zResetVehicleSettings = resetVehicleSettings(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zResetVehicleSettings ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int vseSts = getVseSts();
                    parcel2.writeNoException();
                    parcel2.writeInt(vseSts);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tcsOpngMd = getTcsOpngMd();
                    parcel2.writeNoException();
                    parcel2.writeInt(tcsOpngMd);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tcsOpngSts = getTcsOpngSts();
                    parcel2.writeNoException();
                    parcel2.writeInt(tcsOpngSts);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IVehicleControlService {
            public static IVehicleControlService sDefaultImpl;
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

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void addVehicleControlListener(IVehicleControlListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addVehicleControlListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void removeVehicleControlListener(IVehicleControlListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeVehicleControlListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public int getDoorLock() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDoorLock();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setDoorLock(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDoorLock(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public float getDriveWindow() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDriveWindow();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setDriveWindow(float signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(signalValue);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDriveWindow(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public float getPassengerWindow() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPassengerWindow();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setPassengerWindow(float signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(signalValue);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPassengerWindow(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public float getLeftRearWindow() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeftRearWindow();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setLeftRearWindow(float signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(signalValue);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLeftRearWindow(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public float getRightRearWindow() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRightRearWindow();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setRightRearWindow(float signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(signalValue);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRightRearWindow(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public float getElectricTailgateLock() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElectricTailgateLock();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setElectricTailgateLock(float signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(signalValue);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setElectricTailgateLock(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public int getSunroofSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSunroofSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setSunroofSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSunroofSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public int getSunroofVentilation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSunroofVentilation();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setSunroofVentilation(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSunroofVentilation(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public int getEspSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEspSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setEspSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setEspSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public int getHdcSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHdcSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setHdcSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHdcSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public int getPdcSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPdcSwitch();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public void setPdcSwitch(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPdcSwitch(signalValue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public VehicleControlBean getVehicleControlStatus() throws RemoteException {
                VehicleControlBean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleControlStatus();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VehicleControlBean.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public boolean resetVehicleSettings(int signalValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signalValue);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resetVehicleSettings(signalValue);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public int getVseSts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVseSts();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public int getTcsOpngMd() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTcsOpngMd();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.saicmotor.sdk.vehiclesettings.IVehicleControlService
            public int getTcsOpngSts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTcsOpngSts();
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

        public static boolean setDefaultImpl(IVehicleControlService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IVehicleControlService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
