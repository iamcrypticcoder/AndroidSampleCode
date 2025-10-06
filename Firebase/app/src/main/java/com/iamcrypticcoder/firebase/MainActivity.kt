package com.iamcrypticcoder.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamcrypticcoder.firebase.presentation.screens.HomeScreen
import com.iamcrypticcoder.firebase.presentation.screens.login.LoginScreen
import com.iamcrypticcoder.firebase.presentation.screens.signup.SignUpScreen
import com.iamcrypticcoder.firebase.presentation.ui.theme.FirebaseTheme
import dagger.hilt.android.AndroidEntryPoint

// ViewModel and State in Compose
// https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state#5
// CleanArchitectureRnD
// https://github.com/iamcrypticcoder/CleanArchitectureRnD/tree/master
// Jetpack-Compose-Login-Sample
// https://github.com/Ryuk-C/Jetpack-Compose-Login-Sample/tree/master

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //com.google.firebase.FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            FirebaseTheme {
                Surface {
                    MainActivityScreen()
                }
            }
        }
    }
}

@Composable
fun MainActivityScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
    }
}