package com.example.android_arch.hook;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;

import com.top.pluginlibrary.RefInvoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import utils.LogUtils;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.hook
 * @class describe
 * @time 2021/1/17 18:50
 * @class describe
 */
public class AmsHookHelper {
    public static final String EXTRA_TARGET_INTENT = "EXTRA_TARGET_INTENT";
    public static void hookAms() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        /*Object iActivityTaskManagerSingleton = RefInvoke.getStaticFieldObject("android.app.ActivityTaskManager", "IActivityTaskManagerSingleton");

        Object mInstance = RefInvoke.getFieldObject("android.util.Singleton", iActivityTaskManagerSingleton, "mInstance");

        Class<?> aClass = Class.forName("android.app.IActivityTaskManager");

        Object proxyInstance = Proxy.newProxyInstance(mInstance.getClass().getClassLoader(), new Class[]{aClass}, new InvokeHelper(mInstance));

        RefInvoke.setFieldObject("android.util.Singleton", iActivityTaskManagerSingleton, "mInstance", proxyInstance);*/

        /* -----------------------------*/

        //原生方式hook Amn.getDefault
//        Class<?> activityTaskManagerClass = Class.forName("android.app.ActivityTaskManager");
//        Field iActivityTaskManagerSingletonField = activityTaskManagerClass.getDeclaredField("IActivityTaskManagerSingleton");
//        iActivityTaskManagerSingletonField.setAccessible(true);
//        Object iActivityTaskManagerSingleton = iActivityTaskManagerSingletonField.get(null);
//
//        Class<?> singleTonClass = Class.forName("android.util.Singleton");
//        Field mInstance = singleTonClass.getDeclaredField("mInstance");
//        mInstance.setAccessible(true);
//
//        //IActivityTaskManager
//        Object activityTaskManagerObj = mInstance.get(iActivityTaskManagerSingleton);
//        Class<?> iActivityTaskManagerClass = Class.forName("android.app.IActivityTaskManager");
//        Object proxyInstance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{iActivityTaskManagerClass}, new Handle(activityTaskManagerObj));
//        mInstance.set(iActivityTaskManagerSingleton, proxyInstance);

        if (Build.VERSION.SDK_INT <= 25) {
           /* //老版本 获取AMN的gDefault单例gDefault，gDefault是final静态的
            Object gDefault = RefInvoke.getStaticFieldObject("android.app.ActivityManagerNative", "gDefault");
            // gDefault是一个 android.util.Singleton<T>对象; 我们取出这个单例里面的mInstance字段
            Object mInstance = RefInvoke.getFieldObject("android.util.Singleton", gDefault, "mInstance");
            // 创建一个这个对象的代理对象MockClass1, 然后替换这个字段, 让我们的代理对象帮忙干活
            Class<?> classB2Interface = Class.forName("android.app.IActivityManager");
            Object proxy = Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{classB2Interface},
                    new Handle(mInstance));

            //把gDefault的mInstance字段，修改为proxy
            RefInvoke.setFieldObject("android.util.Singleton", gDefault, "mInstance", proxy);*/

            //获取AMN的gDefault单例gDefault，gDefault是final静态的
            Object gDefault = RefInvoke.getStaticFieldObject("android.app.ActivityManagerNative", "gDefault");

            // gDefault是一个 android.util.Singleton<T>对象; 我们取出这个单例里面的mInstance字段
            Object mInstance = RefInvoke.getFieldObject("android.util.Singleton", gDefault, "mInstance");

            // 创建一个这个对象的代理对象MockClass1, 然后替换这个字段, 让我们的代理对象帮忙干活
            Class<?> classB2Interface = Class.forName("android.app.IActivityManager");
            Object proxy = Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class<?>[] { classB2Interface },
                    new MockClass1(mInstance));

            //把gDefault的mInstance字段，修改为proxy
            Class class1 = gDefault.getClass();
            RefInvoke.setFieldObject("android.util.Singleton", gDefault, "mInstance", proxy);
        } else {
            Object iActivityTaskManagerSingleton = RefInvoke.getStaticFieldObject("android.app.ActivityTaskManager", "IActivityTaskManagerSingleton");
            // gDefault是一个 android.util.Singleton<T>对象; 我们取出这个单例里面的mInstance字段
            Object mInstance = RefInvoke.getFieldObject("android.util.Singleton", iActivityTaskManagerSingleton, "mInstance");
            // 创建一个这个对象的代理对象MockClass1, 然后替换这个字段, 让我们的代理对象帮忙干活
            Class<?> classB2Interface = Class.forName("android.app.IActivityTaskManager");
            Object proxy = Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{classB2Interface},
                    new Handle(mInstance));

            //把gDefault的mInstance字段，修改为proxy
            RefInvoke.setFieldObject("android.util.Singleton", iActivityTaskManagerSingleton, "mInstance", proxy);
        }
    }

    static class Handle implements InvocationHandler {
        private Object object;

        public Handle(Object o) {
            this.object = o;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.getName().equals("startActivity")) {
                LogUtils.INSTANCE.d("-------hook startActivity-------- " + method);
                Intent raw = null;
                int index = 0;
                for (int i = 0; i < args.length; i++) {
                    LogUtils.INSTANCE.d("args[i]" + args[i]);
                    if (args[i] instanceof Intent) {
                        raw = (Intent) args[i];
                        index = i;
                        break;
                    }
                }
                Intent newIntent = new Intent();
                ComponentName componentName = new ComponentName("com.example.android_arch", SubActivity.class.getName());
                newIntent.setComponent(componentName);
                newIntent.putExtra(AmsHookHelper.EXTRA_TARGET_INTENT, raw);
                args[index] = newIntent;
            }
            return method.invoke(object, args);
        }
    }

    public static void hookActivityThread() throws Exception {
        // 先获取到当前的ActivityThread对象
        Object currentActivityThread = RefInvoke.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread");

        // 由于ActivityThread一个进程只有一个,我们获取这个对象的mH
        Handler mH = (Handler) RefInvoke.getFieldObject(currentActivityThread, "mH");

        //把Handler的mCallback字段，替换为new MockClass2(mH)
        RefInvoke.setFieldObject(Handler.class, mH, "mCallback", new MockClass2(mH));
    }
}
