package com.iamcrypticcoder.firebase.domain.usecase.impl

import android.util.Log
import com.google.firebase.FirebaseException
import com.iamcrypticcoder.firebase.domain.usecase.SignUpUseCase
import javax.inject.Inject

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.iamcrypticcoder.firebase.domain.util.DomainUtil.phoneNumberToEmail
import java.util.concurrent.TimeUnit

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
                        Log.d("Firebase", "phoneNumber: ${user?.phoneNumber}")
                        Log.d("Firebase", "uid: ${user?.uid}")
                        Log.d("Firebase", "metadata: ${user?.metadata}")
                        Log.d("Firebase", "isAnonymous: ${user?.isAnonymous}")

                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()

                        user?.updateProfile(profileUpdates)?.addOnCompleteListener {
                            if (task.isSuccessful) {
                                Log.d("Firebase", "User profile updated: ${user.displayName}")
                            }
                        }

                        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                            .setPhoneNumber("+88${phoneNumber}") // The phone number to verify
                            .setTimeout(60L, TimeUnit.SECONDS)
                            //.setActivity(this)
                            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                                    // Automatically verified — link to current user
                                    user?.linkWithCredential(credential)
                                        ?.addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Log.d("Firebase", "Phone number linked: ${task.result?.user?.phoneNumber}")
                                            } else {
                                                Log.e("Firebase", "Link failed", task.exception)
                                            }
                                        }
                                }

                                override fun onVerificationFailed(p0: FirebaseException) {
                                    Log.e("Firebase", "Verification failed: ${p0.message}")
                                }

                                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                                    Log.d("Firebase", "Code sent: $verificationId")
                                    callback.onSuccess("User registered: ${user?.email}")
                                }
                            }).build()

                        PhoneAuthProvider.verifyPhoneNumber(options)

                        //callback.onSuccess("User registered: ${user?.email}")
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
