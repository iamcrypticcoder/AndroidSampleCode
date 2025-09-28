package com.iamcrypticcoder.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.iamcrypticcoder.firebase.databinding.ActivityAuthenticationBinding
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseUser

/**
 * https://github.com/firebase/quickstart-android/tree/master/auth/app/src/main/java/com/google/firebase/quickstart/auth/kotlin
 */
class AuthenticationActivity : AppCompatActivity() {
    private val TAG = AuthenticationActivity::class.java.simpleName

    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager : CallbackManager
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth


//        FacebookSdk.sdkInitialize(applicationContext);
//        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create()
        // Initialize Facebook Login button
        binding.loginWithFacebookButton.setReadPermissions("email", "public_profile")
        binding.loginWithFacebookButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
                // ...
            }
        })

        initButtons()

    }

    public override fun onStart() {
        Log.d(TAG, "onStart()")
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val user = auth.currentUser
        if (null == user) {
            Log.d(TAG, "User not signed in")
            return
        }
        Log.d(TAG, "UID = " + user.uid)
        Log.d(TAG, "Email = " + user.email)
        Log.d(TAG, "Display Name = " + user.displayName)
        Log.d(TAG, "Phone Number = " + user.phoneNumber)
        Log.d(TAG, "Photo URL = " + user.photoUrl)
        //updateUI(currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun initButtons() {
        binding.createAccountButton.setOnClickListener {
            createAccount(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
        }

        binding.loginButton.setOnClickListener {
            if (binding.loginButton.text == "Login") {
                loginWithEmailPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
            } else {
                logout()
            }
        }

        binding.loginWithGoogleButton.setOnClickListener {
            loginWithGoogle()
        }

        binding.loginWithFacebookButton.setOnClickListener {
            loginWithFacebook()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(TAG, "createAccount() - addOnCompleteListener() - Success")
                val user = auth.currentUser!!
                Log.d(TAG, "addOnCompleteListener() : -----------------------------------")
                Log.d(TAG, "addOnCompleteListener() : UID = " + user.uid)
                Log.d(TAG, "addOnCompleteListener() : Email = " + user.email)
                Log.d(TAG, "addOnCompleteListener() : Display Name = " + user.displayName)
                Log.d(TAG, "addOnCompleteListener() : Phone Number = " + user.phoneNumber)
                Log.d(TAG, "addOnCompleteListener() : Photo URL = " + user.photoUrl)
                Log.d(TAG, "addOnCompleteListener() : -----------------------------------")

                user.sendEmailVerification().addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.resultTextView.text = "Verification email sent to ${user.email}"
                    } else {
                        binding.resultTextView.text = "Failed to send verification email."
                    }
                }
            } else {
                Log.d(TAG, "loginWithEmailPassword() - addOnCompleteListener() - Falied")
                Log.d(TAG, "addOnCompleteListener() - " + it.exception)
            }
        }
    }

    private fun loginWithEmailPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(TAG, "loginWithEmailPassword() - addOnCompleteListener() - Success")
                val user = auth.currentUser!!
                printUserInfoLog(user)
                binding.loginButton.text = "Logout"
            } else {
                Log.d(TAG, "loginWithEmailPassword() - addOnCompleteListener() - Falied")
                Log.d(TAG, "addOnCompleteListener() - " + it.exception)
            }
        }
    }

    private fun loginWithGoogle() {

    }

    private fun loginWithFacebook() {

    }

    private fun logout() {
        auth.signOut()
        binding.loginButton.text = "Login"
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        Log.d(TAG, "handleFacebookAccessToken: " +
                "token = ${token.token}, " +
                "expires = ${token.expires}, " +
                "permissions = ${token.permissions}, " +
                "userId = ${token.userId}")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser!!
                    printUserInfoLog(user)
                    binding.loginButton.text = "Logout"
                } else {
                    Log.d(TAG, "handleFacebookAccessToken() - addOnCompleteListener() - Falied")
                    Log.d(TAG, "addOnCompleteListener() - " + task.exception)
                }
            }
    }

    private fun printUserInfoLog(user : FirebaseUser) {
        Log.d(TAG, "printUserInfoLog() : -----------------------------------")
        Log.d(TAG, "printUserInfoLog() : UID = " + user.uid)
        Log.d(TAG, "printUserInfoLog() : Email = " + user.email)
        Log.d(TAG, "printUserInfoLog() : Display Name = " + user.displayName)
        Log.d(TAG, "printUserInfoLog() : Phone Number = " + user.phoneNumber)
        Log.d(TAG, "printUserInfoLog() : Photo URL = " + user.photoUrl)
        Log.d(TAG, "printUserInfoLog() : -----------------------------------")
    }
}