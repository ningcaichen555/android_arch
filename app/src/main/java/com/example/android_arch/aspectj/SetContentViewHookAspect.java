package com.example.android_arch.aspectj;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Array;
import java.util.Arrays;

import utils.LogUtils;

@Aspect
public class SetContentViewHookAspect {
    @Pointcut("call(* android.app.Activity.setContentView(..))")
    public void setContentView() {
        int[] ins = new int[12];
    }

    @Around("setContentView()")
    public void aroundSetContentView(ProceedingJoinPoint joinPoint) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long totalTimes = System.currentTimeMillis() - currentTimeMillis;

        LogUtils.INSTANCE.d("SetContentViewHookAspect", "totalTimes= " + totalTimes);
    }

    final String TAG = SetContentViewHookAspect.class.getSimpleName();

    @Before("execution(* *..MainActivity+.on**(..))")
    public void method(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getThis().getClass().getSimpleName();

        Log.e(TAG, "class:" + className);
        Log.e(TAG, "method:" + methodSignature.getName());
    }
}
