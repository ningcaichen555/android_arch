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

            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field mH = activityThreadClass.getDeclaredField("mH");
            mH.setAccessible(true);
            Field sCurrentActivityThread = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThread.setAccessible(true);
            Object sCurrentActivityThreadObj = sCurrentActivityThread.get(this);
            Object h = mH.get(sCurrentActivityThreadObj);

            Class<?> hClass = Class.forName("android.app.ActivityThread$H");
            Field mCallbackField = hClass.getSuperclass().getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);

            Object callBackObj = mCallbackField.get(h);
            Handler.Callback proxyInstance = (Handler.Callback) Proxy.newProxyInstance(getClassLoader(), new Class[]{Handler.Callback.class}, new CallbackHelper(callBackObj));
            mCallbackField.set(h, proxyInstance);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    class CallbackHelper implements InvocationHandler {
        private Object object;

        public CallbackHelper(Object o) {
            this.object = o;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            LogUtils.INSTANCE.d("----1----- invoke");
            return method.invoke(object, args);
        }
    }
}