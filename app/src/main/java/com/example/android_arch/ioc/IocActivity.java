package com.example.android_arch.ioc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.android_arch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IocActivity extends AppCompatActivity {
    @BindView(R.id.btn_butter_knife)
    Button myButton;

    @ViewById(R.id.btn_custom_ioc)
    Button customBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioc);

        ButterKnife.bind(this);

        ViewInjectUtils.Companion.inject(this);

        customBtn.setText("hello ioc");
    }

    @OnClick(R.id.btn_butter_knife)
    public void onClickBtn() {
        Toast.makeText(this, "btn点击了", Toast.LENGTH_LONG).show();
    }

    @Event(R.id.btn_custom_ioc)
    public void onClickCustomBtn() {
        Toast.makeText(this, "customBtn点击了", Toast.LENGTH_LONG).show();
    }
}