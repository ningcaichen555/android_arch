
package com.example.android_arch.coroutine

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val okHttpClient = OkHttpClient().newBuilder().connectTimeout(2000, TimeUnit.DAYS).build()

val retrofit = Retrofit.Builder().baseUrl("").client(okHttpClient).build()


