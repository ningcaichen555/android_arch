package com.example.android_arch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

    }

    fun pullList() {
        val url = "http://182.92.97.72:8080/article/select"
        val map = hashMapOf<String, Int>()
        var page = 1;
        while (page < 200) {
            map["limit"] = 20
            map["page"] = page
            val body = gSon.toJson(map).toRequestBody(json)
            val request = Request.Builder().url(url).post(body).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("ddd", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    val result = response.body?.string()
                    val articleEntity =
                        gSon.fromJson<ArticleEntity>(result, ArticleEntity::class.java)
                    val data = articleEntity.data
                    val jsonText = StringBuilder()
                    data.forEach {
                        val articleId = it.articleId
                        jsonText.append("http://www.toolcards.cn/articleDetail/$articleId\n")
                    }
                    updateBd(jsonText.toString())
                }
            })
            page++
        }
    }

    fun updateBd(list: String) {
//        val bdUrl = "http://data.zz.baidu.com/urls?site=www.rabbetter.cn&token=8sEkQJM5aPDZAVIj"
        val bdUrl = "http://data.zz.baidu.com/urls?site=www.toolcards.cn&token=8sEkQJM5aPDZAVIj"
        val body = list.toRequestBody(text)
        val request = Request.Builder().url(bdUrl).post(body).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("ddd", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                Log.d("ddd", result)
            }
        })
    }
}