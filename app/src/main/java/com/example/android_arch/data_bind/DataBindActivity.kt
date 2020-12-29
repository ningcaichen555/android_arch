package com.example.android_arch.data_bind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.example.android_arch.R
import com.example.android_arch.databinding.ActivityDataBindBinding

class DataBindActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setContentView<ActivityDataBindBinding>(this, R.layout.activity_data_bind)
        val dataBindViewModel = DataBindViewModel()
        binding.dataBindModel = dataBindViewModel
        dataBindViewModel.name = "张三"
    }
}