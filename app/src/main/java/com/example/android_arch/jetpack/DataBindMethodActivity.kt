package com.example.android_arch.jetpack

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android_arch.R
import com.example.android_arch.databinding.ActivityDataBindMethodBinding
import com.example.android_arch.jetpack.entity.User

class DataBindMethodActivity : AppCompatActivity() {
    private var dataBinding: ActivityDataBindMethodBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_data_bind_method
        ) as ActivityDataBindMethodBinding

        dataBinding?.user = User().apply {
            name = "xiaoming"
            age = 12
        }
        dataBinding?.presenter = Presenter()
    }

    inner class Presenter {
        fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            dataBinding?.user = User().apply {
                name = s.toString()
                age = 12
            }
        }

        fun onClickTv(text: String) {
            Toast.makeText(this@DataBindMethodActivity,text,Toast.LENGTH_LONG).show()
        }
    }
}