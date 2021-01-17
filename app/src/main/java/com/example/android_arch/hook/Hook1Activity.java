package com.example.android_arch.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_arch.R;
import com.example.android_arch.base.BaseAppActivity;

import java.lang.reflect.Field;

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
        Object mInstrumentation = RefInvoke.getFieldObject(Activity.class, this, "mInstrumentation");
        LogUtils.INSTANCE.d("-----调用-----startActivity-----" + mInstrumentation);
        EvilInstrumentation evilInstrumentation = new EvilInstrumentation((Instrumentation) mInstrumentation);
        RefInvoke.setFieldObject(Activity.class, this, "mInstrumentation", evilInstrumentation);
        Object mInstrumentation2 = RefInvoke.getFieldObject(Activity.class, this, "mInstrumentation");
        LogUtils.INSTANCE.d("-----调用-----startActivity-----" + mInstrumentation2);

        startActivity(new Intent(this, Hook2Activity.class));
    }
}