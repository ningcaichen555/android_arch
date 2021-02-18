package com.example.android_arch.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.android_arch.R
import kotlinx.android.synthetic.main.activity_handler1.*

class HandlerActivity1 : AppCompatActivity() {
    val handler = object : Handler(Callback {
        //return true 拦截
        true
    }) {
        override fun dispatchMessage(msg: Message) {
            //这里收不到消息
            super.dispatchMessage(msg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler1)

        //新建一个子线程
        Thread {
            //handelr 调用post（runnable）
            handler.post {
                handler_tv.setText("hello post runnable")
            }

            handler.postDelayed({
                handler_tv.setText("hello post runnable delay")
            }, 1000)

            handler.sendMessage(Message())

            handler.sendEmptyMessageDelayed(100, 5000)

            handler.sendMessageAtFrontOfQueue(Message())

            val message = Message.obtain()
            handler.sendMessage(message)

        }.start()
    }


}