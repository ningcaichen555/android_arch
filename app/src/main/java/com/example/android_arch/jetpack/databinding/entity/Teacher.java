package com.example.android_arch.jetpack.databinding.entity;

import android.database.Observable;

import androidx.databinding.ObservableArrayMap;

public class Teacher {
    private ObservableArrayMap<String, String> map;
    private String name;

    public ObservableArrayMap<String, String> getMap() {
        return map;
    }

    public void setMap(ObservableArrayMap<String, String> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
