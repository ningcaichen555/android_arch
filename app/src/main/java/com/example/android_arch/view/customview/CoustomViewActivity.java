package com.example.android_arch.view.customview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.android_arch.R;

public class CoustomViewActivity extends AppCompatActivity {
    private ProgressView progressView;
    private int[] i = {0};
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            i[0] = i[0] + 50;
            progressView.setProgress(i[0]);

            sendEmptyMessageDelayed(100, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coustom_view);
        progressView = findViewById(R.id.progressView);

        handler.sendEmptyMessage(100);
    }
}