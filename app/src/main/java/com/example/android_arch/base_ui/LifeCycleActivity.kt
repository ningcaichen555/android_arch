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

    fun click_to_intent(view: View) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "textMessage")
            type = "text/plain"
        }

        // Verify that the intent will resolve to an activity
        val resolveActivity = sendIntent.resolveActivity(packageManager)
        if (resolveActivity != null) {
            startActivity(sendIntent)
        }

        //强制使用选择器，每次可以选择不同的应用
        val chooser = Intent.createChooser(sendIntent, "请选择")
        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        }
    }
}