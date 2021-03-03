package com.example.android_arch.permissions

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.android_arch.App
import java.lang.reflect.Method
import java.lang.reflect.ReflectPermission
import java.util.*

/**
 * @name android_arch
 * @class name：com.example.android_arch.permissions
 * @class describe
 * @author caichen QQ:345233199
 * @time 2021/3/3 22:29
 * @class describe
 */
class PermissionUtils {
    companion object {
        fun isOverM(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        }

        fun excute(any: Any, requestCode: Int) {
            val declaredMethods = any.javaClass.declaredMethods
            for (method: Method in declaredMethods) {
                val annotation = method.getAnnotation(PermissionSuccess::class.java)
                if (annotation.requestCode == requestCode) {
                    method.isAccessible = true
                    method.invoke(any, null)
                }
            }
        }

        fun checkHasPermission(permissions: Array<String>): Boolean {
            val denyPermission = mutableListOf<String>()
            for (permission: String in permissions) {
                val checkSelfPermission =
                    ContextCompat.checkSelfPermission(App.getContext(), permission)
                if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {
                    denyPermission.add(permission)
                }
            }

            if (denyPermission.size == 0) {
                return true
            }

            return false
        }


    }
}