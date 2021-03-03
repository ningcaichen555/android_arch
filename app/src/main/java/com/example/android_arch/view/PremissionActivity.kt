package com.example.android_arch.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.android_arch.R
import kotlinx.android.synthetic.main.activity_premission.*

class PremissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_premission)

        callPhone.setOnClickListener {
            val checkSelfPermission =
                ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
                callPhone()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    String[]{ Manifest.permission.CALL_PHONE },
                    100
                )
            }
        }
    }

    private fun callPhone() {
        Toast.makeText(this, "callPhone", Toast.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("PremissionActivity", "permissions=$permissions grantResults=$grantResults")
    }
}