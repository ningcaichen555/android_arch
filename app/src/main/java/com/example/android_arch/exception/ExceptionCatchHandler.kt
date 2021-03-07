package com.example.android_arch.exception

import android.content.Context
import utils.LogUtils

/**
 * @name android_arch
 * @class name：com.example.android_arch.exception
 * @class describe
 * @author caichen QQ:345233199
 * @time 2021/3/4 21:40
 * @class describe
 */
class ExceptionCatchHandler : Thread.UncaughtExceptionHandler {
    private var context: Context? = null
    private var defaultUncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null

    fun init(context: Context) {
        this.context = context
        Thread.currentThread().uncaughtExceptionHandler = this
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
    }

    override fun uncaughtException(thread: Thread, e: Throwable) {
        LogUtils.d("捕捉的异常是===$e")

        defaultUncaughtExceptionHandler?.uncaughtException(thread, e)

    }
}