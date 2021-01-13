package com.example.android_arch.aidl;

import android.os.Binder;
import android.os.IBinder;

import java.lang.reflect.Proxy;

public abstract class Stub extends Binder implements MyInterface {
    public static final String DESCRIPTOR = "com.example.android_arch.aidl.Stub";

    public Stub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    @Override
    public IBinder asBinder() {
        return null;
    }

    public static MyInterface asInterface(IBinder obj)
    {
        if ((obj==null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin!=null)&&(iin instanceof MyInterface))) {
            return ((MyInterface)iin);
        }
        return new MyInterface() {
            @Override
            public void connect() {

            }

            @Override
            public void disConnect() {

            }

            @Override
            public boolean isConnected() {
                return false;
            }

            @Override
            public IBinder asBinder() {
                return null;
            }
        };
    }
}
