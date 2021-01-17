package com.example.android_arch.hook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_arch.R;
import com.example.android_arch.base.BaseAppActivity;

public class Hook2Activity extends BaseAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook2);
    }
}