package com.example.android_arch.data_bind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil.setContentView
import com.example.android_arch.R
import com.example.android_arch.databinding.ActivityDataBindBinding

class DataBindActivity : AppCompatActivity() {
    val dataBindViewModel = DataBindViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setContentView<ActivityDataBindBinding>(this, R.layout.activity_data_bind)
        binding.dataBindModel = dataBindViewModel
        dataBindViewModel.name = "张三"
    }

    fun click_to_changName(view: View) {
        dataBindViewModel.name = "李四"
    }
}