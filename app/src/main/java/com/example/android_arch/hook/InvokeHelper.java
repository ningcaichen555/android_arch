package com.example.android_arch.hook;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import utils.LogUtils;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.hook
 * @class describe
 * @time 2021/1/17 19:04
 * @class describe
 */
class InvokeHelper implements InvocationHandler {
    private Object object;

    public InvokeHelper(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogUtils.INSTANCE.d("-------1-------- invoke");
        return method.invoke(object, args);
    }
}
