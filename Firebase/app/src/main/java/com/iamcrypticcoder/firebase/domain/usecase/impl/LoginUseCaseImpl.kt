package com.iamcrypticcoder.firebase.domain.usecase.impl

import android.util.Log
import com.iamcrypticcoder.firebase.domain.usecase.LoginUseCase

private const val TAG = "LoginUseCaseImpl"

class LoginUseCaseImpl : LoginUseCase {
    private lateinit var username: String
    private lateinit var password: String
    private var isInitialized = false

    override fun setLoginCredentials(username: String, password: String) {
        if (isInitialized) {
            throw UnsupportedOperationException("Login credentials are immutable after being set")
        }
        this.username = username
        this.password = password
        isInitialized = true
    }

    override fun execute() {
        Log.d(TAG, "execute()")

    }


}