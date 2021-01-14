package com.example.android_arch.aidl.origin

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import com.example.android_arch.aidl.IMessageInterface
import com.example.android_arch.aidl.IServiceConnectService
import com.example.android_arch.aidl.MessageReceiverListener
import com.example.android_arch.aidl.custom.MyInterface
import com.example.android_arch.aidl.custom.Stub

class RemoteService : Service() {
    val handler = Handler(Looper.getMainLooper())
    var isConnected = false
    var iServiceConnectService = object : IServiceConnectService.Stub() {
        //Bindler线程池中运行
        override fun connect() {
            Thread.sleep(5000)
            this@RemoteService.isConnected = true
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

    var iMessageInterface = object : IMessageInterface.Stub() {
        override fun sendMessage(message: Message?) {
            handler.post {
                Toast.makeText(this@RemoteService, message?.content, Toast.LENGTH_LONG).show()
            }
        }

        override fun unRegisterMessageReceiveListener(listener: MessageReceiverListener?) {

        }

        override fun registerMessageReceiveListener(listener: MessageReceiverListener?) {

        }
    }

    var myInterface = object : Stub() {
        override fun connect() {
            handler.post {
                Toast.makeText(this@RemoteService, "connect", Toast.LENGTH_LONG).show()
            }
        }

        override fun disConnect() {

        }

        override fun isConnected(): Boolean {
            return false
        }

    }

    override fun onBind(intent: Intent?): IBinder? {
        return myInterface.asBinder()
    }
}