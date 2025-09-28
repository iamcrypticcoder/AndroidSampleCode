package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun CardExample() {
    Column(
        Modifier.Companion.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Companion.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(Modifier.Companion.padding(24.dp)) {
                Text("Mahbub", fontWeight = FontWeight.Companion.W700)
                Text("+8801520086948")
                Text("Dhaka, Bangladesh", color = Color.Companion.Gray)
            }
        }
    }
}