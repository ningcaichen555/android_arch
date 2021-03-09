package com.example.android_arch.tinker;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.TinkerDexLoader;
import com.tencent.tinker.loader.TinkerLoader;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.tinker
 * @class describe
 * @time 2021/3/8 21:12
 * @class describe
 */
class TinkerManager {
    private static TinkerManager tinkerManager;

    public static TinkerManager getTinkerManager() {
        if (tinkerManager == null) {
            synchronized (TinkerManager.class) {
                if (tinkerManager == null) {
                    tinkerManager = new TinkerManager();
                }
            }
        }
        return tinkerManager;
    }

    protected void installTinker(ApplicationLike applicationLike) {
        if (!Tinker.isTinkerInstalled()) {
            TinkerInstaller.install(applicationLike);
        }
    }

    protected void loadPatch(String patchName) {

    }
}
