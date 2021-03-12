package com.example.android_arch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.Choreographer;

import com.example.android_arch.R;
import com.example.android_arch.databinding.ActivityViewBinding;

import java.util.concurrent.TimeUnit;

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

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            long lastFrameTimeNanos = 0;
            long currentFrameTimeNanos = 0;

            @Override
            public void doFrame(long frameTimeNanos) {
                if (lastFrameTimeNanos == 0) {
                    lastFrameTimeNanos = frameTimeNanos;
                }
                currentFrameTimeNanos = frameTimeNanos;
                long diffMs = TimeUnit.MILLISECONDS.convert(currentFrameTimeNanos - lastFrameTimeNanos, TimeUnit.NANOSECONDS);
                long droppedCount = 0;
                if (diffMs > 100) {
                    droppedCount = (int) (diffMs / 16.6);
                    Log.d("ViewActivity", "Block occur, droppedCount: " + droppedCount);
                }
                lastFrameTimeNanos = frameTimeNanos;
                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("view_3", String.valueOf(activityViewBinding.viewTest.getMeasuredHeight()));
    }
}