package com.example.android_arch.hook;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

import com.example.android_arch.App;

import java.lang.reflect.Field;
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
class AmsHookHelper {
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
                String packageName = raw.getComponent().getPackageName();

                ComponentName componentName = new ComponentName("com.example.android_arch", SubActivity.class.getName());
                newIntent.setComponent(componentName);

                newIntent.putExtra("EXTRA_ACTIVITY_INTENT", raw);

                args[index] = newIntent;
            }
            return method.invoke(object, args);
        }
    }
}
