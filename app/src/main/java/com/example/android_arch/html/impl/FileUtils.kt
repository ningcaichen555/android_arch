package com.example.android_arch.html.impl

import android.content.Context
import android.util.Log
import utils.LogUtils
import java.io.File
import java.io.FileOutputStream

object FileUtils {
    fun copy(context: Context, fileName: String, filePath: String) {
        val fileInputStream = context.assets.open(fileName)

        val file = File(filePath)
        if (!file.exists()) {
            Log.d("FileUtils","${file.mkdirs()}")
        }

        val fileOutPutStream = FileOutputStream("$filePath/$fileName")
        val byteArray = ByteArray(1024)
        var count = fileInputStream.read(byteArray)
        while (count > 0) {
            fileOutPutStream.write(byteArray, 0, count)
            count = fileInputStream.read(byteArray)
        }
        fileOutPutStream.flush()
        fileOutPutStream.close()
        fileInputStream.close()
    }
}