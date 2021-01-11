package com.example.android_arch.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.example.android_arch.aidl.IServiceConnectService.Stub

class RemoteService : Service() {
    var iService = object :Stub(){
        override fun connect() {
            Thread.sleep(5000)
        }

        override fun disConnect() {
            Toast.makeText(this@RemoteService,"disconnect",Toast.LENGTH_LONG).show()
        }

        override fun isConnected() {
            TODO("Not yet implemented")
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}