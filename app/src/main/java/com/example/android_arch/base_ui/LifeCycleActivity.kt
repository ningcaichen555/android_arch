package com.example.android_arch.base_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android_arch.R
import utils.LogUtils

class LifeCycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
        LogUtils.d("onCreate")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("onStart")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d("onResume")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d("onPause")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("onDestroy")
    }

    fun click_to_next(view: View) {
        startActivity(Intent(this, LifeCycle2Activity::class.java))
    }
}