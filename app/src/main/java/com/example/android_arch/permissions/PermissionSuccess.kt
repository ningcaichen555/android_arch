package com.example.android_arch.permissions

import java.lang.reflect.Method

/**
 * @name android_arch
 * @class name：com.example.android_arch.permissions
 * @class describe
 * @author caichen QQ:345233199
 * @time 2021/3/3 22:33
 * @class describe
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class PermissionSuccess(val requestCode: Int)
