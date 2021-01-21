package com.example.android_arch.hook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.android_arch.R;
import com.example.android_arch.base.BaseAppActivity;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;
import utils.LogUtils;

import static android.os.Environment.DIRECTORY_MUSIC;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.hook
 * @class describe
 * @time 2021/1/19 21:48
 * @class describe
 */
public class RealActivity extends BaseAppActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real);

        File filesDir = this.getFilesDir();
        LogUtils.INSTANCE.d(filesDir.getAbsolutePath());
        File externalFilesDir = this.getExternalFilesDir(DIRECTORY_MUSIC);
        LogUtils.INSTANCE.d(externalFilesDir.getAbsolutePath());

        ///data/user/0/com.example.android_arch/files
        File pluginApk = this.getFileStreamPath("plugin.apk");
        String dexPath = pluginApk.getAbsolutePath();

        File dir = getDir("dex", Context.MODE_PRIVATE);
        String optimizedDirectory = dir.getAbsolutePath();

        DexClassLoader dexClassLoader = new DexClassLoader(dexPath, optimizedDirectory, null, getClassLoader());
        try {
            Class<?> beanClass = dexClassLoader.loadClass("com.example.plugin1.Bean");
            Object o = beanClass.newInstance();
            Method getName = beanClass.getMethod("getName");
            getName.setAccessible(true);
            String name = (String) getName.invoke(o);
            LogUtils.INSTANCE.d("-----name-----" + name);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
