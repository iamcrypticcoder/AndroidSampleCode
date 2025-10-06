package com.iamcrypticcoder.firebase.domain.usecase.impl

import android.util.Log
import com.iamcrypticcoder.firebase.domain.usecase.SignUpUseCase
import javax.inject.Inject

import com.google.firebase.auth.FirebaseAuth
import com.iamcrypticcoder.firebase.domain.util.DomainUtil.phoneNumberToEmail

private const val TAG = "SignupUseCaseImpl"

class SignupUseCaseImpl @Inject constructor() : SignUpUseCase {
    private lateinit var name: String
    private lateinit var phoneNumber: String
    private lateinit var password: String
    private var isInitialized = false
    private lateinit var callback: SignUpUseCase.Callback

    override fun setSignUpCredentials(
        name: String,
        phoneNumber: String,
        password: String
    ) {
        if (isInitialized) {
            throw UnsupportedOperationException("Signup credentials are immutable after being set")
        }
        this.name = name
        this.phoneNumber = phoneNumber
        this.password = password
        isInitialized = true
    }

    override fun setCallback(callback: SignUpUseCase.Callback) {
        this.callback = callback
    }

    override fun execute() {
        Log.d(TAG, "execute()")
        
        if (!isInitialized) {
            callback.onFailed("Signup credentials not set")
            return
        }

        // Execute signup in a background thread
        Thread {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(phoneNumberToEmail(phoneNumber), password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Log.d("Firebase", "User registered: ${user?.email}")
                        callback.onSuccess("User registered: ${user?.email}")
                    } else {
                        Log.e("Firebase", "Error: ${task.exception?.message}")
                        callback.onFailed("Error: ${task.exception?.message}")
                    }
                }


//            try {
//                val signupRequest = SignupRequest(name, email, username, password, phone, website)
//                runBlocking {
//                    val response: Response<SignupResponse> = signupApi.signup(signupRequest)
//
//                    if (response.isSuccessful) {
//                        val signupResponse = response.body()
//                        if (signupResponse != null && signupResponse.success) {
//                            callback?.onSuccess(signupResponse.message)
//                        } else {
//                            callback?.onFailed(signupResponse?.message ?: "Signup failed")
//                        }
//                    } else {
//                        callback?.onFailed("Signup failed: ${response.code()}")
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e(TAG, "Signup error", e)
//                callback?.onFailed("Network error: ${e.message}")
//            }
        }.start()
    }
}
