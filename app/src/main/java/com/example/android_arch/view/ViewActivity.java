package com.example.android_arch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.example.android_arch.R;
import com.example.android_arch.databinding.ActivityViewBinding;

/**
 * view绘制流程
 */
public class ViewActivity extends AppCompatActivity {
    private ActivityViewBinding activityViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_view);
        Log.d("view_1", String.valueOf(activityViewBinding.viewTest.getMeasuredHeight()));

        activityViewBinding.viewTest.post(new Runnable() {
            @Override
            public void run() {
                Log.d("view_2", String.valueOf(activityViewBinding.viewTest.getMeasuredHeight()));
            }
        });

        activityViewBinding.viewTest.post(new Runnable() {
            @Override
            public void run() {
                Log.d("view_2", String.valueOf(activityViewBinding.viewTest.getMeasuredHeight()));
            }
        });

        activityViewBinding.viewTest.post(new Runnable() {
            @Override
            public void run() {
                Log.d("view_2", String.valueOf(activityViewBinding.viewTest.getMeasuredHeight()));
            }
        });
        //添加到handlerActionQueue中，
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("view_3", String.valueOf(activityViewBinding.viewTest.getMeasuredHeight()));
    }
}