package com.example.android_arch.eventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.android_arch.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.util.AsyncExecutor
import org.greenrobot.eventbus.util.ThrowableFailureEvent
import utils.LogUtils

class EventBusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)
        EventBus.getDefault().register(this)

        AsyncExecutor.create().execute {
            val x = 100 / 0
            EventBus.getDefault().post(MessageEvent("error"))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onError(throwableFailureEvent: ThrowableFailureEvent) {
        LogUtils.d(throwableFailureEvent.toString())
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventBusMessage(messageEvent: MessageEvent) {
//        Toast.makeText(this, messageEvent.message, Toast.LENGTH_LONG).show()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventFather(messageFatherEvent: MessageFatherEvent) {
        Toast.makeText(this, messageFatherEvent.message, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    fun post(view: View) {
        EventBus.getDefault().post(MessageEvent("hello everyone!!"))
    }

    fun postStick() {
        EventBus.getDefault().postSticky(MessageEvent("hello stick"))
    }

    fun clickToFather(view: View) {
        EventBus.getDefault().post(MessageEvent("hello event father"))
    }
}