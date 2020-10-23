package com.example.android_arch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.viewapp.ButterKnifeActivity
import com.example.viewapp.dispatch.DiapatchActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit

val client = OkHttpClient.Builder()
    .writeTimeout(30000L, TimeUnit.MILLISECONDS)
    .readTimeout(30000L, TimeUnit.MILLISECONDS)
    .build()
val json = "application/json; charset=utf-8".toMediaType();
val text = "text/plain; charset=utf-8".toMediaType();
val gSon: Gson = GsonBuilder().create()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this,DiapatchActivity::class.java))
//        startActivity(Intent(this, ArticleActivity::class.java))
    }

}