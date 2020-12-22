package com.example.android_arch.camera2

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Matrix
import android.graphics.RectF
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Size
import android.view.Surface
import android.view.TextureView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.android_arch.R
import kotlinx.android.synthetic.main.activity_video_record.*
import java.util.*
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit

class VideoRecordActivity : AppCompatActivity() {
    private var backgroundThread: HandlerThread? = null
    private var backgroundHandler: Handler? = null
    private var sensorOrientation = 0
    private lateinit var videoSize: Size
    private lateinit var previewSize: Size
    /**
     * A [Semaphore] to prevent the app from exiting before closing the camera.
     */
    private val cameraOpenCloseLock = Semaphore(1)

    private val surfaceTextureListener = object : TextureView.SurfaceTextureListener {
        override fun onSurfaceTextureSizeChanged(
            surface: SurfaceTexture?,
            width: Int,
            height: Int
        ) {
            openCamera(width, height)
        }

        override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {

        }

        override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean = true
        override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) =
            Unit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_record)
    }

    override fun onResume() {
        super.onResume()
        startBackgroundThread()
        if (recorder_preview_view.isAvailable) {
            openCamera(recorder_preview_view.width, recorder_preview_view.height)
        } else {
            recorder_preview_view.surfaceTextureListener = surfaceTextureListener
        }
    }

    /**
     * 权限检测
     */
    private fun hasPermissionsGranted(permissions: Array<String>) =
        permissions.none {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

    /**
     * 请求权限
     */
    private fun requestVideoPermissions() {

    }

    /**
     * 屏幕大小宽高比3：4不支持1080分辨率以上录制
     */
    private fun chooseVideoSize(choices: Array<Size>) = choices.firstOrNull {
        it.width == it.height * 4 / 3 && it.width <= 1080
    } ?: choices[choices.size - 1]


    private fun chooseOptimalSize(
        choices: Array<Size>,
        width: Int,
        height: Int,
        aspectRatio: Size
    ): Size {

        // Collect the supported resolutions that are at least as big as the preview Surface
        val w = aspectRatio.width
        val h = aspectRatio.height
        val bigEnough = choices.filter {
            it.height == it.width * h / w && it.width >= width && it.height >= height
        }

        // Pick the smallest of those, assuming we found any
        return if (bigEnough.isNotEmpty()) {
            Collections.min(bigEnough, CompareSizesByArea())
        } else {
            choices[0]
        }
    }

    /**
     * 开启相机
     */
    @SuppressLint("MissingPermission")
    private fun openCamera(width: Int, height: Int) {

    }

    /**
     * 开启线程
     */
    private fun startBackgroundThread() {
        backgroundThread = HandlerThread("CameraBackground")
        backgroundThread?.start()
        backgroundHandler = Handler(backgroundThread?.looper!!)
    }
}