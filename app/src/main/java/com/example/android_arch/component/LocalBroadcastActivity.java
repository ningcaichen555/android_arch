package com.example.android_arch.component;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.android_arch.R;

public class LocalBroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broadcast);

        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        instance.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        },new IntentFilter());
        instance.sendBroadcast(new Intent().putExtra("key","localBroadCastReceiver"));

    }

    public void CLICK(View view) {
        Intent intent = new Intent("com.top.videolib.action");
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }

//        startActivity(new Intent(this, VideoLibActivity.class));
    }
}