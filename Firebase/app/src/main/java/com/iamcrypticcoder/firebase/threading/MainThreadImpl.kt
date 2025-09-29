package com.iamcrypticcoder.firebase.threading

import android.os.Handler
import android.os.Looper
import com.iamcrypticcoder.firebase.domain.executors.MainThread
import javax.inject.Inject

class MainThreadImpl @Inject constructor() : MainThread {
    private val handler = Handler(Looper.getMainLooper())

    override fun post(runnable: Runnable) {
        handler.post(runnable)
    }
}