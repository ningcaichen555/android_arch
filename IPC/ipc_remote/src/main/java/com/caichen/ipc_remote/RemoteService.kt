package com.caichen.ipc_remote

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

/**
 * 提供子进程的链接和消息服务
 */
class RemoteService : Service() {
    private var connected: Boolean = false

    private val connectService by lazy {
        object : IConnectionService.Stub() {
            override fun connect() {
                connected = true
            }

            override fun disConnect() {
                connected = false
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