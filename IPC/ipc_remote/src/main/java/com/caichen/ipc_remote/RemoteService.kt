package com.caichen.ipc_remote

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

/**
 * 提供子进程的链接和消息服务
 */
class RemoteService : Service() {
    var connected: Boolean = false

    val connectService by lazy {
        object : IConnectionService.Stub() {
            override fun connect() {
                connected = true
                Thread.sleep(5000)
            }

            override fun disConnect() {
                connected = false
                Toast.makeText(this@RemoteService, "disconnect", Toast.LENGTH_SHORT).show()
            }

            override fun isConnected(): Boolean {
                return connected
            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return connectService.asBinder()
    }
}