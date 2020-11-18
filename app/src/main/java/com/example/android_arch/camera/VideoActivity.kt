package com.example.android_arch.camera

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_arch.R
import com.example.android_arch.camera.manager.VideoRecordManager
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : AppCompatActivity() {
    private var flagPlay: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        val videoRecordManager = VideoRecordManager(camera,this)
        bt.setOnClickListener {
            if (flagPlay) {
                videoRecordManager.stop()
                bt.text = "开始"
                Toast.makeText(this@VideoActivity, "结束", Toast.LENGTH_SHORT).show()
            } else {
                videoRecordManager.start()
                bt.text = "结束"
                Toast.makeText(this@VideoActivity, "开始", Toast.LENGTH_SHORT).show()
            }
            flagPlay = !flagPlay
        }
        bt_play.setOnClickListener {
            startActivity(Intent(this@VideoActivity, PlayerRecordActivity::class.java))
        }
    }
}
