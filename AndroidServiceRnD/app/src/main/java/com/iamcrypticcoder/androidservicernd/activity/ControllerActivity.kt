package com.iamcrypticcoder.androidservicernd.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iamcrypticcoder.androidservicernd.R
import com.iamcrypticcoder.androidservicernd.service.LocalService
import kotlinx.android.synthetic.main.activity_controller.*


class ControllerActivity : AppCompatActivity() {
    private val TAG = ControllerActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controller)

        startButton.setOnClickListener {
            startService(Intent(this@ControllerActivity, LocalService::class.java))
        }

        stopButton.setOnClickListener {
            stopService(Intent(this@ControllerActivity,LocalService::class.java))
        }
        
    }
}