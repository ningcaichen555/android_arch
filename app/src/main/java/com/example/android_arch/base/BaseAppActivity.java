package com.example.android_arch.base;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_arch.hook.EvilInstrumentation;
import com.example.android_arch.hook.RefInvoke;

import utils.LogUtils;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.base
 * @class describe
 * @time 2021/1/17 14:09
 * @class describe
 */
public class BaseAppActivity extends AppCompatActivity {
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
//        LogUtils.INSTANCE.d("-----调用前-----intent" + intent + "requestCode" + requestCode);
        super.startActivityForResult(intent, requestCode);
//        LogUtils.INSTANCE.d("-----调用后-----intent" + intent + "requestCode" + requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //02 方式 通过hook字段Instrumentation
        Object sCurrentActivityThread = RefInvoke.getFieldObject("android.app.ActivityThread", this, "sCurrentActivityThread");
        LogUtils.INSTANCE.d("-----通过hook字段Instrumentation-----" + sCurrentActivityThread);
        Object mInstrumentation = RefInvoke.getFieldObject(sCurrentActivityThread, "mInstrumentation");
        EvilInstrumentation evilInstrumentation = new EvilInstrumentation((Instrumentation) mInstrumentation);

        RefInvoke.setFieldObject(sCurrentActivityThread, "mInstrumentation", evilInstrumentation);
        Object mInstrumentation2 = RefInvoke.getFieldObject(Activity.class, this, "mInstrumentation");
        LogUtils.INSTANCE.d("-----通过hook字段Instrumentation-----" + mInstrumentation2);

        super.onCreate(savedInstanceState);
    }
}
