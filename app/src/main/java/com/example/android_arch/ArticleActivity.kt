package com.example.android_arch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

import java.io.IOException
import java.lang.StringBuilder

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        pullList()
    }

    /**
     * 上传收录地址
     */
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
//                        jsonText.append("http://www.toolcards.cn/articleDetail/$articleId\n")
                        jsonText.append("http://www.rabbetter.cn/articleDetail/$articleId\n")
                    }
                    updateBd(jsonText.toString())
                }
            })
            page++
        }
    }

    fun updateBd(list: String) {
        val bdUrl = "http://data.zz.baidu.com/urls?site=www.rabbetter.cn&token=8sEkQJM5aPDZAVIj"
//        val bdUrl = "http://data.zz.baidu.com/urls?site=www.toolcards.cn&token=8sEkQJM5aPDZAVIj"
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