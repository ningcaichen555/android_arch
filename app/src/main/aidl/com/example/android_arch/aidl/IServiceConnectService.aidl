// IServiceConnectService.aidl
package com.example.android_arch.aidl;

interface IServiceConnectService {
    //链接
    void connect();

    //断开链接
    void disConnect();

    //链接成功
    void isConnected();
}