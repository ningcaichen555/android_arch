package com.example.android_arch.hook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.android_arch.R;
import com.example.android_arch.base.BaseAppActivity;

public class Hook1Activity extends BaseAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook1);
    }

    public void startActivity(View view) {
        startActivity(new Intent(this, Hook2Activity.class));
    }
}