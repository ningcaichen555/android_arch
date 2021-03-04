package com.example.android_arch.ioc

import android.app.Activity
import android.view.View
import java.lang.reflect.Field
import java.lang.reflect.Method

class ViewInjectUtils {
    companion object {
        fun inject(activity: Activity){
            injectFiled(activity)
            injectEvent(activity)
        }

        fun injectFiled(activity: Activity) {
            val activityClass = activity.javaClass
            val declaredFields = activityClass.declaredFields
            for (field: Field in declaredFields) {
                val annotation = field.getAnnotation(ViewById::class.java)
                val valueID = annotation?.value
                field.isAccessible = true
                field.set(activity, valueID?.let { activity.findViewById<View>(it) })
            }
        }

        fun injectEvent(activity: Activity) {
            val activityClass = activity.javaClass
            val declaredMethods = activityClass.declaredMethods
            for (method: Method in declaredMethods) {
                val annotation = method.getAnnotation(Event::class.java)
                if (annotation!=null){
                    val findViewById = activity.findViewById<View>(annotation.value)
                    findViewById.setOnClickListener {
                        method.invoke(activity)
                    }
                }
            }
        }
    }
}