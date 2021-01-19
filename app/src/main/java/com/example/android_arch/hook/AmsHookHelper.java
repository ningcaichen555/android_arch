package com.example.android_arch.hook;

import com.example.android_arch.App;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

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

        Class<?> activityTaskManagerClass = Class.forName("android.app.ActivityTaskManager");
        Field iActivityTaskManagerSingleton1 = activityTaskManagerClass.getDeclaredField("IActivityTaskManagerSingleton");
        iActivityTaskManagerSingleton1.setAccessible(true);
        Object iActivityTaskManagerSingletonObject = iActivityTaskManagerSingleton1.get(null);

        Class<?> singTonClass = Class.forName("android.util.Singleton");
        Field mInstanceField = singTonClass.getDeclaredField("mInstance");
        mInstanceField.setAccessible(true);
        Object singTonClassObj = mInstanceField.get(iActivityTaskManagerSingletonObject);

        Class<?> IActivityTaskManagerClass = Class.forName("android.app.IActivityTaskManager");
        Object proxyObj = Proxy.newProxyInstance(iActivityTaskManagerSingletonObject.getClass().getClassLoader(), new Class[]{IActivityTaskManagerClass}, new InvokeHelper(singTonClassObj));
        mInstanceField.set(iActivityTaskManagerSingletonObject, proxyObj);
    }

}
