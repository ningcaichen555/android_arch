// IConnectionService.aidl
package com.caichen.ipc_remote;

// Declare any non-default types here with import statements

interface IConnectionService {

    void connect();

    void disConnect();

    boolean isConnected();
}