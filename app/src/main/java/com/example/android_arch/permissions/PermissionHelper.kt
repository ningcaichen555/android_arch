package com.example.android_arch.permissions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.android_arch.App
import java.util.*

/**
 * @name android_arch
 * @class name：com.example.android_arch.permissions
 * @class describe
 * @author caichen QQ:345233199
 * @time 2021/3/3 22:25
 * @class describe
 */
class PermissionHelper(val any: Any, val requestCode: Int, val permissions: Array<String>) {

    private fun request() {
        //判断是不是6.0以上
        val overM = PermissionUtils.isOverM()
        if (overM) {
            if (PermissionUtils.checkHasPermission(permissions)) {
                PermissionUtils.excute(any, requestCode)
            } else {

                val denyPermission = mutableListOf<String>()
                for (permission: String in permissions) {
                    val checkSelfPermission =
                        ContextCompat.checkSelfPermission(App.getContext(), permission)
                    if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {
                        denyPermission.add(permission)
                    }
                }

                if (denyPermission.size == 0) {
                    PermissionUtils.excute(any, requestCode)
                } else {
                    ActivityCompat.requestPermissions(
                        getActivity(any),
                        denyPermission.toTypedArray(),
                        requestCode
                    )
                }

            }
        } else {
            //没超过6.0直接执行方法
            PermissionUtils.excute(any, requestCode)
        }
    }

    fun getActivity(any: Any): Activity {
        return any as Activity
    }
}