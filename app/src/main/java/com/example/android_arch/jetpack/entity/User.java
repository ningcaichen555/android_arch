package com.example.android_arch.jetpack.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.android_arch.BR;

public class User extends BaseObservable {
    private String name;
    private int age;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
