package com.iamcrypticcoder.hilt

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HiltRnDApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }
}