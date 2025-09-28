package com.iamcrypticcoder.jetpackcompose.screens.bottomnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun LeftScreen() {
    Column(
        modifier = Modifier.fillMaxWidth().background(Color.Red),
        verticalArrangement = Arrangement.Center
    ) {
        Text("This is Left Screen")
    }
}