package com.example.android_arch.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_arch.R;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}