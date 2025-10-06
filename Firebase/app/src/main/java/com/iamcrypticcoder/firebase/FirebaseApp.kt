package com.iamcrypticcoder.firebase

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FirebaseApp() : Application() {
    override fun onCreate() {
        super.onCreate()
        com.google.firebase.FirebaseApp.initializeApp(this)
    }
}


