package com.example.android_arch.hook;

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
    public static void hookAms() throws ClassNotFoundException {
        Object iActivityTaskManagerSingleton = RefInvoke.getStaticFieldObject("android.app.ActivityTaskManager", "IActivityTaskManagerSingleton");

        Object mInstance = RefInvoke.getFieldObject("android.util.Singleton",iActivityTaskManagerSingleton, "mInstance");

        Class<?> aClass = Class.forName("android.app.IActivityTaskManager");

        Object proxyInstance = Proxy.newProxyInstance(mInstance.getClass().getClassLoader(), new Class[]{aClass}, new InvokeHelper(mInstance));

        RefInvoke.setFieldObject("android.util.Singleton", iActivityTaskManagerSingleton,"mInstance", proxyInstance);
    }

}
