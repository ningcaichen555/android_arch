package com.example.android_arch.coroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.async
import okhttp3.RequestBody.Companion.toRequestBody

class MyViewModel : ViewModel() {
    val data = MutableLiveData<String>()

    fun getData() {
        launch(
            {
                val json = async {
                    retrofit.create(Apiservice::class.java).getData("".toRequestBody())
                }
            },
            { e: Throwable ->
                //onError

            },
            {
                //success
            }
        )
    }
}