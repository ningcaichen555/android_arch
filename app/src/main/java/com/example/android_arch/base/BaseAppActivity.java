package com.example.android_arch.base;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

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
        LogUtils.INSTANCE.d("-----调用前-----intent" + intent + "requestCode" + requestCode);
        super.startActivityForResult(intent, requestCode);
        LogUtils.INSTANCE.d("-----调用后-----intent" + intent + "requestCode" + requestCode);
    }
}
