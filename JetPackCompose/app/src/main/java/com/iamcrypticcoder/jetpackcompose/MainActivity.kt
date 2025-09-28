package com.iamcrypticcoder.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents.BasicUIComponentsScreen
import com.iamcrypticcoder.jetpackcompose.screens.customlazycolumn.CustomLazyColumnScreen
import com.iamcrypticcoder.jetpackcompose.screens.bottomnavigation.BottomNavigationBarScreen
import com.iamcrypticcoder.jetpackcompose.screens.HomeScreen
import com.iamcrypticcoder.jetpackcompose.screens.Material3ComponentsScreen
import com.iamcrypticcoder.jetpackcompose.screens.NavigationDrawerScreen
import com.iamcrypticcoder.jetpackcompose.screens.loginscreen1.SampleLoginScreen1
import com.iamcrypticcoder.jetpackcompose.screens.SampleSignupScreen1
import com.iamcrypticcoder.jetpackcompose.screens.animations.AnimationsScreen
import com.iamcrypticcoder.jetpackcompose.ui.theme.JetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackComposeTheme {
                Surface {
                    MainActivityScreen()
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MainActivityScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        // Home
        composable(Routes.Home.route) {
            HomeScreen(navController = navController)
        }

        // Basic UI Components Screen
        composable(Routes.BasicUIComponents.route) {
            BasicUIComponentsScreen(navController)
        }

        // Basic UI Components Screen
        composable(Routes.BottomNavigationBar.route) {
            BottomNavigationBarScreen(navController)
        }

        composable(Routes.CustomLazyColumn.route) {
            CustomLazyColumnScreen(navController)
        }

        composable(Routes.NavigationDrawer.route) {
            NavigationDrawerScreen(navController)
        }

        composable(Routes.AnimationScreen.route) {
            AnimationsScreen(navController)
        }

        // Material3 Components Screen
        composable(Routes.Material3Components.route) {
            Material3ComponentsScreen(navController)
        }

        // Sample Login Screen 1
        composable(Routes.SampleLoginScreen1.route) {
            SampleLoginScreen1(navController)
        }

        // Sample Signup Screen 1
        composable(Routes.SampleSignupScreen1.route) {
            SampleSignupScreen1(navController)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxWidth().padding(48.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Welcome to Login Screen",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(20.dp))

        // Username input field
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = {
                username = it
            },
            label = { Text("Username") },
            leadingIcon = { Icon(
                Icons.Filled.Person,
                contentDescription = ""
            ) }
        )
        Spacer(modifier = Modifier.size(20.dp))

        // Password input field
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text("Password") },
            leadingIcon = { Icon(
                Icons.Filled.Info,
                contentDescription = ""
            ) }
        )
        Spacer(modifier = Modifier.size(20.dp))

        // Login button
        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Login",
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }

    }
}
