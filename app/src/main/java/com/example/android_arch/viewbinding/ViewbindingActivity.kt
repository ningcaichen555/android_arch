package com.example.android_arch.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_arch.R
import com.example.android_arch.databinding.ActivityViewbindingBinding

class ViewbindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityViewbindingBinding.inflate(layoutInflater)
        setContentView(inflate.root)
        inflate.name.text = "text"
    }
}