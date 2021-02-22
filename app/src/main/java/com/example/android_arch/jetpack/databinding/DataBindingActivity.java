package com.example.android_arch.jetpack.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import android.os.Bundle;

import com.example.android_arch.R;
import com.example.android_arch.databinding.ActivityDataBindingBinding;
import com.example.android_arch.jetpack.databinding.entity.Student;
import com.example.android_arch.jetpack.databinding.entity.User;
import com.example.android_arch.jetpack.databinding.entity.UserInfo;

public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObservableField<String> nameField = new ObservableField<>();
        nameField.set("zhangsan");

        ActivityDataBindingBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        UserInfo userInfo = new UserInfo(nameField, "28", "0");
        viewDataBinding.setUserInfo(userInfo);

        nameField.set("lisi");
        userInfo.setAge("12");

        User user = new User();
        user.setName("wangwu");
        user.setAge(34);
        viewDataBinding.setUser(user);

        user.setAge(35);

        Student student = new Student();
        student.setAge(123);
        student.setName("xiaoming");
        viewDataBinding.setStudent(student);

        student.setAge(321);
    }
}