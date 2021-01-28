package com.example.android_arch.hook;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;

import java.lang.reflect.InvocationTargetException;

import utils.LogUtils;


/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.hook
 * @class describe
 * @time 2021/1/17 14:37
 * @class describe
 */
public class EvilInstrumentation extends Instrumentation {
    private Instrumentation instrumentation;

    public EvilInstrumentation(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, String target,
            Intent intent, int requestCode, Bundle options) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LogUtils.INSTANCE.d("-----调用-----execStartActivity");
        Class[] argType = {Context.class, IBinder.class, IBinder.class, String.class, Intent.class, int.class, Bundle.class};
        Object[] args = {who, contextThread, token, target, intent, requestCode, options};
        return (ActivityResult) RefInvoke.invokeInstanceMethod(instrumentation, "execStartActivity", argType, args);
    }

    public Activity newActivity(ClassLoader cl, String className,
                                Intent intent) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        Intent extra_activity_intent = intent.getParcelableExtra("EXTRA_ACTIVITY_INTENT");
        if (extra_activity_intent!=null){
            String realClassName = extra_activity_intent.getComponent().getClassName();
            LogUtils.INSTANCE.d("-----调用-----extra_activity_intent realClassName"+realClassName);
            return instrumentation.newActivity(cl, realClassName, extra_activity_intent);
        }
        LogUtils.INSTANCE.d("-----调用-----intent className"+className);
        return instrumentation.newActivity(cl,className,intent);
    }
}
