package com.example.android_arch.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.android_arch.IMsgInterface;

import utils.LogUtils;

public class RemoteService extends Service {
    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Stub();
    }

    private class Stub extends IMsgInterface.Stub {
        @Override
        public void sendMsg() throws RemoteException {
            LogUtils.INSTANCE.d("sendMsg");
            Log.d("RemoteService", "sendMsg");
        }

        @Override
        public void getMsg() throws RemoteException {
            LogUtils.INSTANCE.d("getMsg");
            Log.d("RemoteService", "getMsg");
        }

        @Override
        public void addMsg() throws RemoteException {
            LogUtils.INSTANCE.d("addMsg");
            Log.d("RemoteService", "addMsg");
        }
    }
}