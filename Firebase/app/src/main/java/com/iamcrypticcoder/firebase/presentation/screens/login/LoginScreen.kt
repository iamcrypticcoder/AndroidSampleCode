package com.iamcrypticcoder.firebase.presentation.screens.login

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iamcrypticcoder.firebase.R
import com.iamcrypticcoder.firebase.domain.usecase.LoginUseCase
import com.iamcrypticcoder.firebase.presentation.navigation.NavigationEvent
import com.iamcrypticcoder.firebase.presentation.navigation.Route
import com.iamcrypticcoder.firebase.presentation.screens.login.viewmodel.LoginViewModel

@Composable
@Preview(showBackground = true)
fun LoginScreen(
    navController : NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by loginViewModel.uiState.collectAsState()

    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Collect navigation events safely
    LaunchedEffect(Unit) {
        loginViewModel.navigationEvent.collect { event ->
            when (event) {
                NavigationEvent.NavigateToDashboard -> {
                    navController.navigate(Route.Dashboard.route) {
                        popUpTo(Route.SignupScreen.route) { inclusive = true }
                    }
                }
                else -> {
                    TODO()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.gfg_logo),
                contentDescription = null,
            )

            Text(text = "Welcome Back!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary)

            Text(text = "Login to continue",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary)

            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Username") },
                    placeholder = { Text("Enter your username") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = false,
                )
                Spacer(modifier = Modifier.height(4.dp)) // Space for error message
                AnimatedVisibility(visible = phoneNumber.isNotEmpty()) {
                    Text(
                        text = phoneNumber,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    placeholder = { Text("Enter your password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                modifier = Modifier.padding(end = 4.dp),
                                painter = painterResource(if (isPasswordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed),
                                contentDescription = "Password Visibility Toggle",
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = false,
                )
                Spacer(modifier = Modifier.height(4.dp))
                AnimatedVisibility(visible = password.isBlank()) {
                    Text(
                        text = password,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

//            AnimatedVisibility(visible = state.loginError != null) {
//                Text(
//                    text = state.loginError.orEmpty(),
//                    color = MaterialTheme.colorScheme.error,
//                    style = MaterialTheme.typography.bodyMedium,
//                )
//            }

            Button(
                onClick = {
                    loginViewModel.login(phoneNumber, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                enabled = !uiState.isLoginInProgress,
            ) {
                if (uiState.isLoginInProgress) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                } else {
                    Text("Login")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(onClick = {
                    Toast.makeText(context, "Nothing to do", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Forgot Password?", fontSize = 12.sp)
                }
                TextButton(onClick = {
                    navController.navigate(Route.SignupScreen.route)
                }) {
                    Text("Sign Up", fontSize = 12.sp)
                }
            }
        }
    }
}