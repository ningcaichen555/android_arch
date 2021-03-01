package com.example.android_arch.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.LayoutInflaterCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.android_arch.R;

import utils.LogUtils;

public class LayoutInflateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        LayoutInflaterCompat.setFactory2(layoutInflater, new LayoutInflater.Factory2() {
            @Nullable
            @Override
            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                Log.d("TAG", "parent=" + parent + "\nname=" + name + "\nattrs=" + attrs);
                if("Button".equals(name)){
                    TextView textView = new TextView(LayoutInflateActivity.this);
                    textView.setText("这是替换的view");
                    textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    return textView;
                }
                return null;
            }

            @Nullable
            @Override
            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                return null;
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_infalte);

        //最终都会走到 inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
        View view = View.inflate(this, R.layout.activity_layout_infalte, null);
        View view1 = LayoutInflater.from(this).inflate(R.layout.activity_layout_infalte, null);

        View view2 = LayoutInflater.from(this).inflate(R.layout.activity_layout_infalte, null, false);

        startActivity(new Intent());

    }
}