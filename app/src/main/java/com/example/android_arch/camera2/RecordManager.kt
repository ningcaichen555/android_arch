package com.example.android_arch.camera2

import android.app.Activity
import android.content.Context
import android.media.MediaRecorder
import android.util.SparseIntArray
import android.view.Surface
import androidx.fragment.app.FragmentActivity

object RecordManager {
    private var mediaRecorder: MediaRecorder? = null
    private var outPutFilePath: String? = null
    private val SENSOR_ORIENTATION_DEFAULT_DEGREES = 90
    private val SENSOR_ORIENTATION_INVERSE_DEGREES = 270
    private var sensorOrientation: Int = 0
    private val DEFAULT_ORIENTATIONS = SparseIntArray().apply {
        append(Surface.ROTATION_0, 90)
        append(Surface.ROTATION_90, 0)
        append(Surface.ROTATION_180, 270)
        append(Surface.ROTATION_270, 180)
    }
    private val INVERSE_ORIENTATIONS = SparseIntArray().apply {
        append(Surface.ROTATION_0, 270)
        append(Surface.ROTATION_90, 180)
        append(Surface.ROTATION_180, 90)
        append(Surface.ROTATION_270, 0)
    }

    /**
     * 初始化recorder
     */
    fun initManager() {
        mediaRecorder = MediaRecorder()
    }

    /**
     * 设置mediaRecorder属性
     */
    fun setUpManager(activity: FragmentActivity?) {
        if (mediaRecorder == null) initManager()

        val cameraActivity = activity ?: return

        if (outPutFilePath.isNullOrEmpty()) {
            outPutFilePath = generateFilePath(cameraActivity)
        }

        val rotation = cameraActivity.windowManager.defaultDisplay.rotation
        when (sensorOrientation) {
            SENSOR_ORIENTATION_DEFAULT_DEGREES ->
                mediaRecorder?.setOrientationHint(DEFAULT_ORIENTATIONS.get(rotation))
            SENSOR_ORIENTATION_INVERSE_DEGREES ->
                mediaRecorder?.setOrientationHint(INVERSE_ORIENTATIONS.get(rotation))
        }

        mediaRecorder?.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setVideoSource(MediaRecorder.VideoSource.SURFACE)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(outPutFilePath)
            setVideoEncodingBitRate(10000000)
            setVideoSize(640, 480)
            setVideoFrameRate(30)
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            prepare()
        }
    }

    /**
     * 视频输出路径
     */
    fun generateFilePath(context: Context): String {
        val filename = "${System.currentTimeMillis()}.mp4"
        val dir = context.getExternalFilesDir(null)

        return if (dir == null) {
            filename
        } else {
            "${dir.absolutePath}/$filename"
        }
    }
}