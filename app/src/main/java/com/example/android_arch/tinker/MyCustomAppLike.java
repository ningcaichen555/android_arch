package com.example.android_arch.tinker;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.multidex.MultiDex;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.tinker
 * @class describe
 * @time 2021/3/8 21:16
 * @class describe
 */
@DefaultLifeCycle(application = "MyApp",loadVerifyFlag = false,flags = ShareConstants.TINKER_ENABLE_ALL)
class MyCustomAppLike extends ApplicationLike {
    public MyCustomAppLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);

        MultiDex.install(base);

        TinkerManager.getTinkerManager().installTinker(this);
    }
}
