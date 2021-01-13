package com.example.android_arch.aidl.origin

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import com.example.android_arch.R
import com.example.android_arch.aidl.IServiceConnectService

class AidlActivity : AppCompatActivity() {
    var iServiceConnectServiceProxy: IServiceConnectService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)

        bindService(Intent(this, RemoteService::class.java), object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Toast.makeText(this@AidlActivity, "服务绑定成功", Toast.LENGTH_SHORT).show();
                iServiceConnectServiceProxy =
                    IServiceConnectService.Stub.asInterface(service)
            }

            override fun onServiceDisconnected(name: ComponentName?) = Unit
        }, Context.BIND_AUTO_CREATE)
    }

    fun connect(view: View) {
        iServiceConnectServiceProxy?.connect()
    }

    fun disconnect(view: View) {
        iServiceConnectServiceProxy?.disConnect()
    }

    fun isConnected(view: View) {
        Toast.makeText(this, "${iServiceConnectServiceProxy?.isConnected}", Toast.LENGTH_LONG)
            .show()
    }
}