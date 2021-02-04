package com.example.android_arch.html.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private String courseName;
    private int score;

    public Course(String courseName, int score) {
        this.courseName = courseName;
        this.score = score;
    }
}