package com.top.plugindemo;

import android.content.Context;

import com.top.pluginlibrary.Idynamic;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.top.plugindemo
 * @class describe
 * @time 2021/1/23 20:46
 * @class describe
 */
public class Dynamic implements Idynamic {
    @Override
    public String getStringForResId(Context context) {
        return context.getResources().getString(R.string.plugin);
    }
}
