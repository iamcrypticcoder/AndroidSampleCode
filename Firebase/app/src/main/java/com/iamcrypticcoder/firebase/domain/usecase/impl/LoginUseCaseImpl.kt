package com.iamcrypticcoder.firebase.domain.usecase.impl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.iamcrypticcoder.firebase.data.model.LoginRequest
import com.iamcrypticcoder.firebase.data.model.LoginResponse
import com.iamcrypticcoder.firebase.data.network.LoginApi
import com.iamcrypticcoder.firebase.domain.usecase.LoginUseCase
import com.iamcrypticcoder.firebase.domain.util.DomainUtil.phoneNumberToEmail
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "LoginUseCaseImpl"

class LoginUseCaseImpl @Inject constructor(
    private val loginApi: LoginApi
) : LoginUseCase {
    private lateinit var phoneNumber: String
    private lateinit var password: String
    private var isInitialized = false
    private var callback: LoginUseCase.Callback? = null

    override fun setLoginCredentials(phoneNumber: String, password: String) {
        if (isInitialized) {
            throw UnsupportedOperationException("Login credentials are immutable after being set")
        }
        this.phoneNumber = phoneNumber
        this.password = password
        isInitialized = true
    }

    override fun setCallback(callback: LoginUseCase.Callback) {
        this.callback = callback
    }

    override fun execute() {
        Log.d(TAG, "execute()")
        
        if (!isInitialized) {
            callback?.onFailed("Login credentials not set")
            return
        }

        if (callback == null) {
            Log.w(TAG, "Callback not set")
            return
        }

        // Execute login in a background thread
        Thread {
            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(phoneNumberToEmail(phoneNumber), password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Firebase", "Login successful")
                    } else {
                        Log.e("Firebase", "Login failed: ${task.exception?.message}")
                    }
                }


//            try {
//                val loginRequest = LoginRequest(username, password)
//                runBlocking {
//                    val response: Response<LoginResponse> = loginApi.login(loginRequest)
//
//                    if (response.isSuccessful) {
//                        val loginResponse = response.body()
//                        if (loginResponse != null && loginResponse.success) {
//                            callback?.onSuccess(loginResponse.message)
//                        } else {
//                            callback?.onFailed(loginResponse?.message ?: "Login failed")
//                        }
//                    } else {
//                        callback?.onFailed("Login failed: ${response.code()}")
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e(TAG, "Login error", e)
//                callback?.onFailed("Network error: ${e.message}")
//            }
        }.start()
    }
}
