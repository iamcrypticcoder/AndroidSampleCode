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
import com.iamcrypticcoder.foregroundservice.databinding.ActivityControllerBinding
import com.iamcrypticcoder.foregroundservice.service.ForegroundService

class ControllerActivity : AppCompatActivity() {
    var mNM: NotificationManager? = null
    private lateinit var binding: ActivityControllerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNM = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        binding = ActivityControllerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtons()
    }

    private fun initButtons() {
        binding.startButton.setOnClickListener {
            startService(Intent(this@ControllerActivity, ForegroundService::class.java))
        }
        binding.stopButton.setOnClickListener {
            stopService(Intent(this@ControllerActivity, ForegroundService::class.java))
        }
    }
}