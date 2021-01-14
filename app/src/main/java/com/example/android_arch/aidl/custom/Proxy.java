package com.example.android_arch.aidl.custom;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class Proxy extends Binder implements MyInterface {
    private IBinder mRemote;
    private boolean isConnected;
    static final int TRANSACTION_connect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_disConnect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_isConnected = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    public static MyInterface sDefaultImpl;

    public static boolean setDefaultImpl(MyInterface impl) {
        if (sDefaultImpl == null && impl != null) {
            sDefaultImpl = impl;
            return true;
        }
        return false;
    }

    public static MyInterface getDefaultImpl() {
        return sDefaultImpl;
    }

    public Proxy(IBinder remote) {
        mRemote = remote;
    }

    @Override
    public void connect() {
        Parcel _data = Parcel.obtain();
        Parcel _replay = Parcel.obtain();
        _data.writeInterfaceToken(Stub.DESCRIPTOR);
        try {
            boolean _status = mRemote.transact(TRANSACTION_connect, _data, _replay, 0);
            if (!_status && getDefaultImpl() != null) {
                getDefaultImpl().connect();
            }
            _replay.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            _data.recycle();
            _replay.recycle();
        }
    }

    @Override
    public void disConnect() {
        isConnected = false;
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }

}
