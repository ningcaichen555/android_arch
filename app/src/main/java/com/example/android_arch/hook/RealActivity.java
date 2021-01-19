package com.example.android_arch.hook;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.example.android_arch.R;
import com.example.android_arch.base.BaseAppActivity;

/**
 * @author caichen QQ:345233199
 * @name android_arch
 * @class name：com.example.android_arch.hook
 * @class describe
 * @time 2021/1/19 21:48
 * @class describe
 */
class RealActivity extends BaseAppActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real);
    }
}
