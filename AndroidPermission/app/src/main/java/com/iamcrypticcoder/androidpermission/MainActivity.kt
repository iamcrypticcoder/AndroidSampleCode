package com.iamcrypticcoder.androidpermission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNetworkPermission.setOnClickListener {
            val intent = Intent(this, NetworkPermissionActivity::class.java)
            startActivity(intent)
        }
        btnCameraPermission.setOnClickListener {
            val intent = Intent(this, CameraPermissionActivity::class.java)
            startActivity(intent)
        }
    }
}