package com.example.android_arch.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.example.android_arch.R

class AidlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)

        bindService(Intent(this,RemoteService::class.java),object :ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

            }

            override fun onServiceDisconnected(name: ComponentName?) {
            }
        },Context.BIND_AUTO_CREATE)
    }

    fun connect(view: View) {

    }

    fun disconnect(view: View) {

    }

    fun isConnected(view: View) {

    }
}