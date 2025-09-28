package com.iamcrypticcoder.androidservice.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent.getActivity
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.*
import android.util.Log
import androidx.core.app.NotificationChannelCompat.DEFAULT_CHANNEL_ID
import androidx.core.app.NotificationCompat
import com.iamcrypticcoder.androidservice.R
import com.iamcrypticcoder.androidservice.activity.ControllerActivity
import java.util.*


const val MSG_REGISTER_CLIENT = 1
const val MSG_UNREGISTER_CLIENT  = 2
const val MSG_SET_VALUE = 3
const val MSG_GEN_RANDOM = 4

private const val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"

class LocalMessengerService : Service() {
    private val TAG = LocalMessengerService::class.java.simpleName
    var mNM: NotificationManager? = null

    /** Keeps track of all current registered clients.  */
    var mClients = mutableListOf<Messenger>()

    // Indicates whether onRebind should be used
    private var allowRebind: Boolean = false

    // Random number generator
    private val mGenerator = Random()

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    private val mMessenger: Messenger = Messenger(IncomingHandler())

    inner class IncomingHandler : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            Log.d(TAG, "handleMessage() - " + msg.what)
            when (msg.what) {
                MSG_REGISTER_CLIENT -> {
                    mClients.add(msg.replyTo);
                }
                MSG_UNREGISTER_CLIENT -> {
                    mClients.remove(msg.replyTo);
                }
                MSG_SET_VALUE -> {
                    var value = msg.arg1
                    for (i in 0..mClients.size - 1) {
                        val client = mClients[i]
                        try {
                            client.send(Message.obtain(null, MSG_SET_VALUE, value, 0))
                        } catch (e: RemoteException) {
                            mClients.removeAt(i)
                        }
                    }
                }
                MSG_GEN_RANDOM -> {
                    val value = mGenerator.nextInt();
                    msg.replyTo.send(Message.obtain(null, MSG_GEN_RANDOM, value, 0))
                }
                else ->
                    super.handleMessage(msg)
            }
        }
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate()")
        mNM = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        showNotification();
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind()")
        return mMessenger.binder
    }

    override fun onRebind(intent: Intent?) {
        Log.d(TAG, "onRebind()")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind()")
        return allowRebind
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
    }

    private fun showNotification() {
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
            mNM?.createNotificationChannel(mChannel)
        }

        var text = getText(R.string.remote_service_started)
        var contentIntent = getActivity(this,
                0, Intent(this, ControllerActivity::class.java), 0)

        // Set the info for the views that show in the notification panel.
        val notification: Notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // the status icon
                .setTicker(text) // the status text
                .setWhen(System.currentTimeMillis()) // the time stamp
                .setContentTitle(getText(R.string.remote_service_started)) // the label of the entry
                .setContentText(text) // the contents of the entry
                .setContentIntent(contentIntent) // The intent to send when the entry is clicked
                .build()

        mNM?.notify(R.string.remote_service_started, notification)
    }
}