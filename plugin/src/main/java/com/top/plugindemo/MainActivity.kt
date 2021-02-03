package com.top.plugindemo

import android.os.Bundle
import android.widget.TextView
import com.top.pluginlibrary.ZeusBaseActivity

class MainActivity : ZeusBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(TextView(this).apply {
            text = resources.getString(R.string.plugin)
        })
    }
}