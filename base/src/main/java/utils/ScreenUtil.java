package utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;

public class ScreenUtil {

    private static final String TAG = "Demo.ScreenUtil";

    private static double RATIO = 0.85;

    public static int screenWidth;

    public static int screenHeight;

    public static int screenMin;// 宽高中，小的一边

    private static int screenMax;// 宽高中，较大的值

    private static float density;

    private static float scaleDensity;

    private static float xdpi;

    private static float ydpi;

    private static int densityDpi;

    private static int dialogWidth;

    private static int statusbarheight;

    private static int navbarheight;

    public int dip2px(float dipValue) {
        return (int) (dipValue * density + 0.5f);
    }

    public int px2dip(float pxValue) {
        return (int) (pxValue / density + 0.5f);
    }

    public static int sp2px(float spValue) {
        return (int) (spValue * scaleDensity + 0.5f);
    }

    private Context context;

    private static ScreenUtil screenUtil;

    public static int getDialogWidth() {
        dialogWidth = (int) (screenMin * RATIO);
        return dialogWidth;
    }

    public static synchronized ScreenUtil getInstance(Context context) {
        if (screenUtil == null) {
            screenUtil = new ScreenUtil();
            screenUtil.init(context);
        }
        return screenUtil;
    }

    public void init(Context context) {
        this.context = context;
        if (null == context) {
            return;
        }
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
        Log.d(TAG, "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density);
    }

    public int getDisplayWidth() {
        if (screenWidth == 0) {
            GetInfo(context);
        }
        return screenWidth;
    }

    public int getDisplayHeight() {
        if (screenHeight == 0) {
            GetInfo(context);
        }
        return screenHeight;
    }

    private void GetInfo(Context context) {
        if (null == context) {
            return;
        }
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        screenMax = (screenWidth < screenHeight) ? screenHeight : screenWidth;
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
        statusbarheight = getStatusBarHeight(context);
        navbarheight = getNavBarHeight(context);
        Log.d(TAG, "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density);
    }

    public int getStatusBarHeight(Context context) {
        if (statusbarheight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusbarheight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statusbarheight == 0) {
            statusbarheight = screenUtil.dip2px(25);
        }
        return statusbarheight;
    }

    private static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
