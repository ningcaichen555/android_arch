package com.example.android_arch.eventbus.custom;

import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.eventbus.custom
 * @class describe
 * @time 2021/2/17 10:16
 * @class describe
 */
public class SubscribeMethod {
    private String methodName;
    private Method method;
    private ThreadMode threadMode;
    private Class<?> eventType;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(ThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public void setEventType(Class<?> eventType) {
        this.eventType = eventType;
    }
}
