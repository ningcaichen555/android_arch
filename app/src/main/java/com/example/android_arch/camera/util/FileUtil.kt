package com.example.android_arch.camera.util

import android.content.Context
import java.io.File

object FileUtil {
    /**
     * 获取视频保存路径
     */
    fun getVideoSavePath(context: Context): String {
        //创建文件路径
        //创建文件路径
        val dir =
            File(context.getExternalFilesDir(null)?.path.toString() + "myApk")
        if (!dir.exists()) {
            dir.mkdir()
        }
        //创建文件
        //创建文件
        val file = File("$dir/test.mp4")
        if (!file.exists()) {
            file.createNewFile()
        }
        return file.path
    }
}