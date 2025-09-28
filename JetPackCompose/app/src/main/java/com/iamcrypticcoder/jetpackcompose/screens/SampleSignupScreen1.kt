package com.iamcrypticcoder.jetpackcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
@Preview(showBackground = true)
fun SampleSignupScreen1(navController: NavHostController = rememberNavController()) {
    SampleSignupScreen1Content()
}

@Composable
fun SampleSignupScreen1Content() {
    Column {
        Text("Sample Signup Screen 1")
    }
}