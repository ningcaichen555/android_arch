// IServiceConnectService.aidl
package com.example.android_arch.aidl;

interface IServiceConnectService {
    //链接
    //oneway 可以不阻塞Client端
    oneway void connect();

    //断开链接
    void disConnect();

    //链接成功
    boolean isConnected();
}