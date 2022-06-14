package com.example.android_arch.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.service.SerializationService
import java.lang.reflect.Type

class GsonImpl :SerializationService{
    override fun <T : Any?> json2Object(input: String?, clazz: Class<T>?): T {
        TODO("Not yet implemented")
    }

    override fun init(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun object2Json(instance: Any?): String {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> parseObject(input: String?, clazz: Type?): T {
        TODO("Not yet implemented")
    }
}