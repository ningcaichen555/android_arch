package com.example.android_arch.eventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.android_arch.R

/**
 * 主线程向子线程发送消息
 */
class HandlerSendActivity1 : AppCompatActivity() {
    var handler:Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_send1)

        handler?.sendMessage(Message())
    }

    inner class MyThread :Thread(){
        override fun run() {
            Looper.prepare()
            handler = object :Handler(){
                override fun handleMessage(msg: Message) {
                    //处理主线程消息
                    super.handleMessage(msg)
                }
            }
            Looper.loop()
            super.run()
        }
    }
}