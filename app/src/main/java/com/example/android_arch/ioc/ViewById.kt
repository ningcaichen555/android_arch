package com.example.android_arch.ioc

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ViewById(
    val value: Int
)