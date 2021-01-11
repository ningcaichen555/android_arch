package com.example.android_arch.aidl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android_arch.R

class AidlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)

        startService(Intent(this, RemoteService::class.java))
    }

    fun connect(view: View) {

    }

    fun disconnect(view: View) {

    }

    fun isConnected(view: View) {

    }
}