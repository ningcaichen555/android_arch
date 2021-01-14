package com.example.android_arch.aidl.custom;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import utils.LogUtils;

public abstract class Stub extends Binder implements MyInterface {
    public static final String DESCRIPTOR = "com.example.android_arch.aidl.custom.Stub";
    static final int TRANSACTION_connect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_disConnect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_isConnected = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);

    public Stub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    public static MyInterface asInterface(IBinder obj) {
        LogUtils.INSTANCE.d("---------1----------");
        if ((obj == null)) {
            return null;
        }
        LogUtils.INSTANCE.d("---------2----------");
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof MyInterface))) {
            return ((MyInterface) iin);
        }
        LogUtils.INSTANCE.d("---------3----------");
        return new Proxy(obj);
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_connect: {
                data.enforceInterface(DESCRIPTOR);
                this.connect();
                return true;
            }
            case TRANSACTION_disConnect: {
                data.enforceInterface(DESCRIPTOR);
                this.disConnect();
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_isConnected: {
                data.enforceInterface(DESCRIPTOR);
                boolean _result = this.isConnected();
                reply.writeNoException();
                reply.writeInt(((_result) ? (1) : (0)));
                return true;
            }
            default: {
                return super.onTransact(code, data, reply, flags);
            }
        }
    }
}
