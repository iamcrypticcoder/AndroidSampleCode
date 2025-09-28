package com.iamcrypticcoder.firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.iamcrypticcoder.firebase.databinding.ActivityAnalyticsBinding

/**
 * To see events in dashboard you have to follow this - https://firebase.google.com/docs/analytics/debugview
 * https://github.com/firebase/quickstart-android/blob/master/analytics/app/src/main/java/com/google/firebase/quickstart/analytics/kotlin/MainActivity.kt
 */
class AnalyticsActivity : AppCompatActivity() {
    private val TAG = AnalyticsActivity::class.java.simpleName

    private lateinit var analytics: FirebaseAnalytics
    private lateinit var binding: ActivityAnalyticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalyticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the FirebaseAnalytics instance.
        analytics = Firebase.analytics

        initButtons()
    }

    private fun initButtons() {
        binding.button1.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("button_number", "1")
            analytics.logEvent("button_click", bundle)
        }
        binding.button2.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("button_number", "2")
            analytics.logEvent("button_click", bundle)
        }
        binding.button3.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("button_number", "3")
            analytics.logEvent("button_click", bundle)
        }
    }


}