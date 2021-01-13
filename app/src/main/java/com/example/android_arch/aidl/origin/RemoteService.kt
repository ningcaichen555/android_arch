package com.example.android_arch.aidl.origin

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import com.example.android_arch.aidl.IServiceConnectService

class RemoteService : Service() {
    val handler = Handler(Looper.getMainLooper())
    var isConnected = false
    var iServiceConnectService = object : IServiceConnectService.Stub() {
        //Bindler线程池中运行
        override fun connect() {
            Thread.sleep(5000)
            this@RemoteService.isConnected =true
            handler.post {
                Toast.makeText(this@RemoteService, "isConnected", Toast.LENGTH_LONG).show()
            }
        }

        override fun disConnect() {
            handler.post {
                Toast.makeText(this@RemoteService, "disConnected", Toast.LENGTH_LONG).show()
            }
            this@RemoteService.isConnected = false
        }

        override fun isConnected(): Boolean {
            return this@RemoteService.isConnected
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return iServiceConnectService.asBinder()
    }
}