package com.example.android_arch.conroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_arch.R
import com.example.android_arch.text
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine2)
        GlobalScope.launch {
            val job = test()

            async {

            }.await()
        }
    }

    suspend fun test() {

    }
}