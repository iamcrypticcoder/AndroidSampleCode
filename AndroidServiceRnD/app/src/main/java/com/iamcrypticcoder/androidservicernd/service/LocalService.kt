package com.iamcrypticcoder.androidservicernd.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

/**
 * https://android.googlesource.com/platform/development/+/master/samples/ApiDemos/src/com/example/android/apis/app/LocalService.java
 */
class LocalService : Service() {
    private val TAG = LocalService::class.java.simpleName

    private var startMode: Int = START_NOT_STICKY             // indicates how to behave if the service is killed
    private val binder = LocalBinder()                      // interface for clients that bind
    private var allowRebind: Boolean = false                // indicates whether onRebind should be used

    // Random number generator
    private val mGenerator = Random()

    inner class LocalBinder : Binder() {
        fun getService() : LocalService {
            return this@LocalService
        }
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate()")
        // The service is being created
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()")
        // The service is starting, due to a call to startService()
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

    fun generateRandomNumber() : Int {
        return mGenerator.nextInt();
    }
}