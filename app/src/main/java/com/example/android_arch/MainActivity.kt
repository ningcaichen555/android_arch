package com.example.android_arch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_arch.camera2.CameraActivity
import com.example.viewapp.dispatch.DiapatchActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, DiapatchActivity::class.java))
//        startActivity(Intent(this, ArticleActivity::class.java))
//        startActivity(Intent(this, CameraActivity::class.java))
    }

}