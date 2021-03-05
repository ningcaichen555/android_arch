package com.example.android_arch.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.android_arch.R
import kotlinx.coroutines.*
import utils.LogUtils

class CoroutineActivity : AppCompatActivity() {
    private val viewModel: MyViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        startCoroutine()
        LogUtils.d("main")

    }

    val funTest: suspend CoroutineScope.() -> Unit = {
        LogUtils.d("funTest")
        suspendFun1()
        suspendFun2()
    }


    fun startCoroutine() {
        GlobalScope.launch(Dispatchers.Default, start = CoroutineStart.LAZY, block = funTest)
    }

    // 挂起函数
    suspend fun suspendFun1() {
        LogUtils.d("suspendFun1")
    }

    // 挂起函数
    suspend fun suspendFun2() {
        LogUtils.d("suspendFun2")
    }

}