package com.top.plugindemo

import android.os.Bundle
import com.top.pluginlibrary.ZeusBaseActivity

class PluginActivity : ZeusBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plugin)
    }
}