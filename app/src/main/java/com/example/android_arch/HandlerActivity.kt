package com.example.android_arch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message

class HandlerActivity : AppCompatActivity() {
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)


        //1.第一种方式
        handler.post(object : Runnable {
            override fun run() {

            }
        })

        //2.第二种方法
        handler.sendMessage(Message().apply {
            this.data = Bundle().apply {
                putChar("char", 'a')
            }
        })

        handler.sendEmptyMessage(0)
        handler.sendEmptyMessageDelayed(0, 1000)

        val threadLocal = ThreadLocal<Boolean>()

        Thread {
            threadLocal.set(true)
            val get = threadLocal.get()
        }.start()

        Thread {
            val get = threadLocal.get()
        }.start()

        println(threadLocal.get())
    }
}