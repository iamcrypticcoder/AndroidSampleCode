package com.iamcrypticcoder.foregroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.iamcrypticcoder.foregroundservice.service.ForegroundService
import kotlinx.android.synthetic.main.activity_controller.*

class ControllerActivity : AppCompatActivity() {
    var mNM: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNM = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        setContentView(R.layout.activity_controller)
        initButtons()
    }

    private fun initButtons() {
        startButton.setOnClickListener {
            startService(Intent(this@ControllerActivity, ForegroundService::class.java))
        }
        stopButton.setOnClickListener {
            stopService(Intent(this@ControllerActivity, ForegroundService::class.java))
        }
    }
}