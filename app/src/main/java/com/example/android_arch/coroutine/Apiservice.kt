package com.example.android_arch.coroutine

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface Apiservice {

    @POST("")
    suspend fun getData(@Body body: RequestBody)

}