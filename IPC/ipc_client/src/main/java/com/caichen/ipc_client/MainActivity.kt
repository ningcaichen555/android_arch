package com.caichen.ipc_client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.caichen.ipc_remote.IConnectionService

class MainActivity : AppCompatActivity() {
    private var iConnectionService: IConnectionService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent()
        intent.action = "com.caichen.ipc"
        intent.setPackage("com.caichen.ipc_remote")
        val flag = bindService(
            intent, object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    iConnectionService = IConnectionService.Stub.asInterface(service)
                    Log.d("Remote", "onServiceConnected")
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    Log.d("Remote", "onServiceDisconnected")
                }
            },
            BIND_AUTO_CREATE
        )
        Toast.makeText(this, "$flag", Toast.LENGTH_SHORT).show()
    }

    fun connect(view: View) {
        iConnectionService?.connect()
    }

    fun disConnect(view: View) {
        iConnectionService?.disConnect()
    }

    fun isConnected(view: View) {
        Toast.makeText(this, "${iConnectionService?.isConnected}", Toast.LENGTH_SHORT).show()
    }
}