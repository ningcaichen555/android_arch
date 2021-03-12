package com.example.android_arch.okhttp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_arch.R
import okhttp3.*
import java.io.IOException

class OkHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)

        okHttpClient.newCall(
            Request.Builder().url("").post(FormBody.Builder().add("", "").build())
                .addHeader("content-type", "text/json").build()
        ).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

            }
        })

        okHttpClient.newCall(
            Request.Builder().url("").post(FormBody.Builder().add("", "").build())
                .addHeader("content-type", "text/json").build()
        ).execute()
    }
}