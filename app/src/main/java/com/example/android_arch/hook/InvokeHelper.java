package com.example.android_arch.hook;

import android.content.ComponentName;
import android.content.Intent;

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
        if (method.getName().equals("startActivity")) {
            LogUtils.INSTANCE.d("-------hook startActivity-------- " + method);

            Intent raw = null;
            int index = 0;
            for (int i = 0; i < args.length; i++) {
                LogUtils.INSTANCE.d("args[i]" + args[i]);
                if (args[i] instanceof Intent){
                    raw =(Intent) args[i];
                    index = i;
                    break;
                }
            }

            Intent newIntent =new Intent();
            String packageName = raw.getComponent().getPackageName();

            ComponentName componentName = new ComponentName(packageName,SubActivity.class.getName());
            newIntent.setComponent(componentName);

            newIntent.putExtra("EXTRA_INTENT",raw);

            args[index] = newIntent;
        }
        return method.invoke(object, args);
    }
}
