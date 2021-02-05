package com.example.android_arch.hook;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.example.android_arch.R;
import com.top.pluginlibrary.PluginManager;

public class Hook1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook1);
    }

    public void start(View view) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        //01 方式 直接继承方法
//        startActivity(new Intent(this, Hook2Activity.class));
        //02 方式 通过hook字段Instrumentation
//        Object mInstrumentation = RefInvoke.getFieldObject(Activity.class, this, "mInstrumentation");
//        LogUtils.INSTANCE.d("-----通过hook字段Instrumentation-----startActivity-----" + mInstrumentation);
//        EvilInstrumentation evilInstrumentation = new EvilInstrumentation((Instrumentation) mInstrumentation);
//        RefInvoke.setFieldObject(Activity.class, this, "mInstrumentation", evilInstrumentation);
//        Object mInstrumentation2 = RefInvoke.getFieldObject(Activity.class, this, "mInstrumentation");
//        LogUtils.INSTANCE.d("-----通过hook字段Instrumentation-----startActivity-----" + mInstrumentation2);

//        startActivity(new Intent(this, Hook2Activity.class));
        //启动插件中的activity
        Intent intent = new Intent();
        String activityName = PluginManager.plugins.get(0).packageInfo.packageName + ".TestActivity";
        intent.setComponent(new ComponentName(PluginManager.plugins.get(0).packageInfo.packageName, activityName));
        startActivity(intent);
    }

   /* @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            AmsHookHelper.hookAms();

            //转换plugin.apk的位置
//            Utils.extractAssets(newBase, "plugin.apk");

//            //拿到主线程class
//            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
//            //拿到字段mH
//            Field mH = activityThreadClass.getDeclaredField("mH");
//            mH.setAccessible(true);
//            //拿到主线程字段
//            Field sCurrentActivityThread = activityThreadClass.getDeclaredField("sCurrentActivityThread");
//            sCurrentActivityThread.setAccessible(true);
//            //获取主线程对象
//            Object sCurrentActivityThreadObj = sCurrentActivityThread.get(this);
//            Handler h = (Handler) mH.get(sCurrentActivityThreadObj);
//
//            Class<?> handlerClass = Class.forName("android.os.Handler");
//            Field mCallback = handlerClass.getDeclaredField("mCallback");
//            mCallback.setAccessible(true);
//            CallbackHelper callbackHelper = new CallbackHelper(h);
//            mCallback.set(h, callbackHelper);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }*/

}