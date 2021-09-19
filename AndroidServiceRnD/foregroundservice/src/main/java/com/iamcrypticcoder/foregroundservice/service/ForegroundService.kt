package com.iamcrypticcoder.foregroundservice.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.iamcrypticcoder.foregroundservice.R
import com.iamcrypticcoder.foregroundservice.ResultActivity

const val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"

class ForegroundService : Service() {
    private val TAG = ForegroundService::class.java.simpleName

    private var startMode: Int = START_NOT_STICKY
    private val binder = LocalBinder()                      // interface for clients that bind
    private var allowRebind: Boolean = false                // indicates whether onRebind should be used

    inner class LocalBinder : Binder() {
        fun getService() : ForegroundService {
            return this@ForegroundService
        }
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate()")
        // The service is being created
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()")
        val pendingIntent: PendingIntent =
            Intent(this, ResultActivity::class.java).let { intent ->
                PendingIntent.getActivity(this, 0, intent, 0)
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "my_channel"
            val Description = "This is my channel"
            val importance: Int = NotificationManager.IMPORTANCE_HIGH
            val mChannel: NotificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance)
            mChannel.description = Description
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            mChannel.setShowBadge(false)
            val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(mChannel)
        }

        val notification: Notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(getText(R.string.notification_title))
            .setContentText(getText(R.string.notification_message))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setTicker(getText(R.string.ticker_text))
            .build()

        // Notification ID cannot be 0.
        startForeground(1, notification)

        return startMode
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind()")
        // A client is binding to the service with bindService()
        return binder
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "onUnbind()")
        // All clients have unbound with unbindService()
        return allowRebind
    }

    override fun onRebind(intent: Intent) {
        Log.d(TAG, "onRebind()")
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        // The service is no longer used and is being destroyed
    }

}