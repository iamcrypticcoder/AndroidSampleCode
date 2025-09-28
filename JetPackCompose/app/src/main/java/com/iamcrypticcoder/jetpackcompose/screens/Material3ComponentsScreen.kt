package com.iamcrypticcoder.jetpackcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
@Preview(showBackground = true)
fun Material3ComponentsScreen(navController: NavHostController = rememberNavController()) {
    Material3ComponentsContent()
}

@Composable
fun Material3ComponentsContent() {
    Column {
        Text("Material 3 Components Screen")
    }
}
