package com.example.android_arch.aidl.origin

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import com.example.android_arch.ArticleActivity
import com.example.android_arch.R
import com.example.android_arch.aidl.IMessageInterface
import com.example.android_arch.aidl.IServiceConnectService
import com.example.android_arch.aidl.custom.MyInterface
import com.example.android_arch.aidl.custom.Stub
import utils.LogUtils

class AidlActivity : AppCompatActivity() {
    var iServiceConnectServiceProxy: IServiceConnectService? = null
    var iMessageInterfaceProxy: IMessageInterface? = null
    var myInterface: MyInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)
        LogUtils.d("----------- -1------------")
        bindService(Intent(this, RemoteService::class.java), object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                LogUtils.d("-----------0------------")
                Toast.makeText(this@AidlActivity, "服务绑定成功", Toast.LENGTH_SHORT).show();
//                iServiceConnectServiceProxy = IServiceConnectService.Stub.asInterface(service)
//                iMessageInterfaceProxy = IMessageInterface.Stub.asInterface(service)
                myInterface = Stub.asInterface(service)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                LogUtils.d("-----------01------------")
            }
        }, Context.BIND_AUTO_CREATE)

        startActivity(Intent(this, ArticleActivity::class.java))
    }

    fun connect(view: View) {
//        iServiceConnectServiceProxy?.connect()
        myInterface?.connect()
    }

    fun disconnect(view: View) {
        iServiceConnectServiceProxy?.disConnect()
    }

    fun isConnected(view: View) {
        Toast.makeText(this, "${iServiceConnectServiceProxy?.isConnected}", Toast.LENGTH_LONG)
            .show()
    }

    fun sendMsg(view: View) {
        val message = Message()
        message.content = "我是message"
        iMessageInterfaceProxy?.sendMessage(message)
    }

    fun bind(view: View) {

    }
}