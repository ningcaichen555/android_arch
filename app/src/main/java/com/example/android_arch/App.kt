package com.example.android_arch

import android.app.Application
import android.os.Debug
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


/**
 * @author caichen
 * @Description TODO
 * @createTime 2020年10月26日 14:15:00
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //开始计时 中间为需要统计执行时间的代码
        Debug.startMethodTracing()

        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true) // (Optional) Whether to show thread info or not. Default true
            .methodCount(0) // (Optional) How many method line to show. Default 2
            .methodOffset(5) // (Optional) Hides internal method calls up to offset. Default 5
            .tag("Android_arch") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))


        //停止计时
        Debug.stopMethodTracing();
    }
}