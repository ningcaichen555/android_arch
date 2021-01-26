package com.example.android_arch.hook;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.android_arch.R;
import com.example.android_arch.base.BaseAppActivity;
import com.top.pluginlibrary.IBean;
import com.top.pluginlibrary.IcallBack;
import com.top.pluginlibrary.Idynamic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    private AssetManager mAssetManager;
    private Resources.Theme mTheme;
    private Resources mResources;
    private DexClassLoader dexClassLoader;
    private String dexPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real);

        //获取assets目录下的文件
        try {
            InputStream open = getResources().getAssets().open("plugin.apk");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String string = getResources().getString(R.string.app_name);

        File filesDir = this.getFilesDir();
        LogUtils.INSTANCE.d(filesDir.getAbsolutePath());
        File externalFilesDir = this.getExternalFilesDir(DIRECTORY_MUSIC);
        LogUtils.INSTANCE.d(externalFilesDir.getAbsolutePath());

        ///data/user/0/com.example.android_arch/files
        File pluginApk = this.getFileStreamPath("plugin.apk");
        dexPath = pluginApk.getAbsolutePath();

        File dir = getDir("dex", Context.MODE_PRIVATE);
        String optimizedDirectory = dir.getAbsolutePath();

        dexClassLoader = new DexClassLoader(dexPath, optimizedDirectory, null, getClassLoader());
        try {
            Class<?> beanClass = dexClassLoader.loadClass("com.top.plugindemo.Bean");
            IBean iBean = (IBean) beanClass.newInstance();
            Method getName = beanClass.getMethod("getName");
            getName.setAccessible(true);
            String name = (String) getName.invoke(iBean);
            iBean.setIcallBack(new IcallBack() {
                @Override
                public void sendResult(String result) {
                    LogUtils.INSTANCE.d("-----result-----" + result);
                }
            });
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

    @Override
    public AssetManager getAssets() {
        if (mAssetManager==null){
            return super.getAssets();
        }
        return mAssetManager;
    }

    @Override
    public Resources getResources() {
        if (mResources==null){
            return super.getResources();
        }
        return mResources;
    }

    @Override
    public Resources.Theme getTheme() {
        if (mTheme==null){
            return super.getTheme();
        }
        return mTheme;
    }

    private void loadPluginRes(String dexPath) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        AssetManager assetManager = AssetManager.class.newInstance();
        mAssetManager = assetManager;

        Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath",String.class);
        addAssetPath.setAccessible(true);
        addAssetPath.invoke(assetManager,dexPath);

        mResources = new Resources(mAssetManager,super.getResources().getDisplayMetrics(),super.getResources().getConfiguration());
        mTheme = mResources.newTheme();
        mTheme.setTo(super.getTheme());
    }


    public void clickToPlugin(View view) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        loadPluginRes(dexPath);
        Class<?> pluginClass = dexClassLoader.loadClass("com.top.plugindemo.Dynamic");
        Idynamic idynamicObj = (Idynamic) pluginClass.newInstance();
        String stringForResId = idynamicObj.getStringForResId(this);
        Toast.makeText(this, stringForResId, Toast.LENGTH_LONG).show();
    }
}
