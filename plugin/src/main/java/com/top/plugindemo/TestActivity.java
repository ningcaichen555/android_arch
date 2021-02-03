package com.top.plugindemo;

import android.os.Bundle;
import android.widget.Toast;

import com.top.pluginlibrary.ZeusBaseActivity;

public class TestActivity extends ZeusBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Toast.makeText(this, "test init", Toast.LENGTH_LONG).show();
    }
}