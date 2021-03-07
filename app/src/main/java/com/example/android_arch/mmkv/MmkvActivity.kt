package com.example.android_arch.mmkv

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_arch.R
import java.io.File
import java.io.RandomAccessFile
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class MmkvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mmkv)

        val randomAccessFile = RandomAccessFile(File(""), "rw")
        val map = randomAccessFile.channel.map(FileChannel.MapMode.READ_WRITE, 0, 2048)
        map.put("hello world".toByte())
    }



    @SuppressLint("BlockedPrivateApi")
    private fun unmmap(mappedByteBuffer: MappedByteBuffer) {
        val fileChannelImplClass = Class.forName("sun.nio.ch.FileChannelImpl")
        val declaredMethod =
            fileChannelImplClass.getDeclaredMethod("unmap", MappedByteBuffer::class.java)
        declaredMethod.invoke(null, mappedByteBuffer)
    }
}