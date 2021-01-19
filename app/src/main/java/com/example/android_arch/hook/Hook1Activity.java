package com.example.android_arch.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import com.example.android_arch.R;
import com.example.android_arch.base.BaseAppActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import okhttp3.internal.http2.Http2Reader;
import utils.LogUtils;

public class Hook1Activity extends BaseAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook1);
    }

    public void startActivity(View view) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        //01 方式 直接继承方法
//        startActivity(new Intent(this, Hook2Activity.class));
        //02 方式 通过hook字段Instrumentation
//        Object mInstrumentation = RefInvoke.getFieldObject(Activity.class, this, "mInstrumentation");
//        LogUtils.INSTANCE.d("-----调用-----startActivity-----" + mInstrumentation);
//        EvilInstrumentation evilInstrumentation = new EvilInstrumentation((Instrumentation) mInstrumentation);
//        RefInvoke.setFieldObject(Activity.class, this, "mInstrumentation", evilInstrumentation);
//        Object mInstrumentation2 = RefInvoke.getFieldObject(Activity.class, this, "mInstrumentation");
//        LogUtils.INSTANCE.d("-----调用-----startActivity-----" + mInstrumentation2);

        startActivity(new Intent(this, Hook2Activity.class));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            AmsHookHelper.hookAms();

            //拿到主线程class
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            //拿到字段mH
            Field mH = activityThreadClass.getDeclaredField("mH");
            mH.setAccessible(true);
            //拿到主线程字段
            Field sCurrentActivityThread = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThread.setAccessible(true);
            //获取主线程对象
            Object sCurrentActivityThreadObj = sCurrentActivityThread.get(this);
            Handler h = (Handler) mH.get(sCurrentActivityThreadObj);

            Class<?> handlerClass = Class.forName("android.os.Handler");
            Field mCallback = handlerClass.getDeclaredField("mCallback");
            mCallback.setAccessible(true);
            CallbackHelper callbackHelper = new CallbackHelper(h);
            mCallback.set(h, callbackHelper);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    class CallbackHelper implements Handler.Callback {
        private Handler object;

        public CallbackHelper(Handler o) {
            this.object = o;
        }

        @Override
        public boolean handleMessage(@NonNull Message msg) {
            LogUtils.INSTANCE.d("----hook CallbackHelper-----" + msg);
            LogUtils.INSTANCE.d("----hook CallbackHelper-----" + msg);
//            if (msg.what == 159) {
//                LogUtils.INSTANCE.d("----hook CallbackHelper-----" + msg);
//                Object obj = msg.obj;
//                Object intent1 = RefInvoke.getFieldObject(obj, "intent");
//                Class<?> objClass = obj.getClass();
//                try {
//                    Field intent = objClass.getDeclaredField("intent");
//                    Intent intentObj = (Intent) intent.get(obj);
//                    Intent extra_intent = intentObj.getParcelableExtra("EXTRA_INTENT");
//                    intentObj.setComponent(extra_intent.getComponent());
//                } catch (NoSuchFieldException | IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
            object.handleMessage(msg);
            return true;
        }
    }
}