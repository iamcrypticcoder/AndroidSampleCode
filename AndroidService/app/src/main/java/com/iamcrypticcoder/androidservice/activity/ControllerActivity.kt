package com.iamcrypticcoder.androidservice.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iamcrypticcoder.androidservice.R
import com.iamcrypticcoder.androidservice.databinding.ActivityControllerBinding
import com.iamcrypticcoder.androidservice.service.LocalService


class ControllerActivity : AppCompatActivity() {
    private val TAG = ControllerActivity::class.java.simpleName
    private lateinit var binding: ActivityControllerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControllerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            startService(Intent(this@ControllerActivity, LocalService::class.java))
        }

        binding.stopButton.setOnClickListener {
            stopService(Intent(this@ControllerActivity,LocalService::class.java))
        }
        
    }
}