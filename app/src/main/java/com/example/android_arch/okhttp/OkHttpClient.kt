package com.example.android_arch.okhttp

import okhttp3.*
import java.util.concurrent.TimeUnit

val okHttpClient = OkHttpClient.Builder()
//    .addInterceptor {  }
    .connectTimeout(30_000, TimeUnit.MILLISECONDS)
    .callTimeout(30_000, TimeUnit.MILLISECONDS)
    .readTimeout(30_000, TimeUnit.MILLISECONDS)
    .build()


