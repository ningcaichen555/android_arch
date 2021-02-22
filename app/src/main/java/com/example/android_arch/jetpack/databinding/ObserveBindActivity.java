package com.example.android_arch.jetpack.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableArrayMap;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android_arch.R;
import com.example.android_arch.databinding.ActivityObserveBindBinding;
import com.example.android_arch.jetpack.databinding.entity.Teacher;

public class ObserveBindActivity extends AppCompatActivity {
    private Teacher teacher;
    private ActivityObserveBindBinding inflate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflate = ActivityObserveBindBinding.inflate(getLayoutInflater());
        setContentView(inflate.getRoot());

        ObservableArrayList<String> observerList = new ObservableArrayList<>();
        observerList.add("hello world");
        inflate.setObserverList(observerList);

        teacher = new Teacher();
        ObservableArrayMap<String, String> map = new ObservableArrayMap<>();
        map.put("hello", "world");
        teacher.setMap(map);
        teacher.setName("张三");
        inflate.setTeacher(teacher);
        teacher.setName("李四");

        inflate.setPresenter(new Presenter());

    }

    public class Presenter {
        public void onTextChanged(Teacher teacher) {
            inflate.teacherName.setText((CharSequence) teacher.getName());
        }
    }
}