package com.example.android_arch

import android.app.Application
import android.content.Context
import android.os.Debug
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import com.example.android_arch.eventbus.MyEventbusIndex
import com.example.android_arch.exception.ExceptionCatchHandler
import com.example.android_arch.hook.AmsHookHelper
import com.dianping.logan.Logan
import com.dianping.logan.LoganConfig
import com.example.android_arch.html.PageInfo
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import org.greenrobot.eventbus.util.AsyncExecutor
import java.io.File
import java.util.*


/**
 * @author caichen
 * @Description TODO
 * @createTime 2020年10月26日 14:15:00
 */
class App : Application() {
//    val asyncExecutor = AsyncExecutor.create()

    companion object {
        var pages: HashMap<String, PageInfo> = HashMap<String, PageInfo>()
        fun getContext(): Context {
            return this.getContext()
        }
    }


    override fun onCreate() {
        super.onCreate()
//        val config = LoganConfig.Builder()
//            .setCachePath(applicationContext.filesDir.absolutePath)
//            .setPath(
//                (applicationContext.getExternalFilesDir(null)!!.absolutePath
//                        + File.separator) + "logan_v1"
//            )
//            .setEncryptKey16("0123456789012345".toByteArray())
//            .setEncryptIV16("0123456789012345".toByteArray())
//            .build()
//        Logan.init(config)
//        ExceptionCatchHandler().apply {
//            init(this@App)
//        }

        val config = LoganConfig.Builder()
            .setCachePath(applicationContext.filesDir.absolutePath)
            .setPath(
                (applicationContext.getExternalFilesDir(null)!!.absolutePath
                        + File.separator) + "logan_v1"
            )
            .setEncryptKey16("0123456789012345".toByteArray())
            .setEncryptIV16("0123456789012345".toByteArray())
            .build()
        Logan.init(config)

        //开始计时 中间为需要统计执行时间的代码
        Debug.startMethodTracing()

//        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
//            .showThreadInfo(true) // (Optional) Whether to show thread info or not. Default true
//            .methodCount(0) // (Optional) How many method line to show. Default 2
//            .methodOffset(5) // (Optional) Hides internal method calls up to offset. Default 5
//            .tag("Android_arch") // (Optional) Global tag for every log. Default PRETTY_LOGGER
//            .build()
//        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))


        //停止计时
        Debug.stopMethodTracing();

        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            //应用前台
            fun onStart() {

            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            //应用前台
            fun onResume() {

            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            //应用后台
            fun onPause() {

            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            //应用后台
            fun onStop() {

            }
        })

//        EventBus.builder().addIndex(MyEventbusIndex()).installDefaultEventBus()

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // 将MultiDex注入到项目中
        MultiDex.install(this);
//        PluginManager.init(this)
//        AmsHookHelper.hookAms()
//        AmsHookHelper.hookActivityThread()
    }
}