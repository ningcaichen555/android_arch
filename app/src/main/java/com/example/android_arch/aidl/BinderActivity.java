package com.example.android_arch.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.example.android_arch.IMsgInterface;
import com.example.android_arch.R;

public class BinderActivity extends AppCompatActivity {

    private IMsgInterface iMsgInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        bindService();
    }


    private void bindService() {
        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, new ServiceConnectionClass(), Context.BIND_AUTO_CREATE);
    }

    public void sendMsg(View view) throws RemoteException {
        iMsgInterface.sendMsg();
    }

    private class ServiceConnectionClass implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMsgInterface = IMsgInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMsgInterface = null;
        }
    }
}