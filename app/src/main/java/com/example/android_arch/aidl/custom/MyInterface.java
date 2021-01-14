package com.example.android_arch.aidl.custom;

import android.os.IInterface;

public interface MyInterface extends IInterface {
    void connect();

    void disConnect();

    boolean isConnected();
}
