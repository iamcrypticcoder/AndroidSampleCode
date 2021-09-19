package com.iamcrypticcoder.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process.myPid
import android.util.Log

class RemoteService : Service() {
    private val TAG = RemoteService::class.java.simpleName

    override fun onCreate() {
        Log.d(TAG, "onCreate()")
        // The service is being created
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()")
        // The service is starting, due to a call to startService()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        return RemoteServiceBinder.asBinder()
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "onUnbind()")
        // All clients have unbound with unbindService()
        return false
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