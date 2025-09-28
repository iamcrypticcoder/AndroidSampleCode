package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

// https://www.geeksforgeeks.org/android/proressbar-in-android-using-jetpack-compose/
@Composable
fun CircularProgressIndicatorExample() {
    Column(
        modifier = Modifier.Companion.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.Companion
                .size(100.dp)
                .padding(16.dp),

            color = Color.Companion.Blue,
            strokeWidth = 8.dp,
            trackColor = Color.Companion.LightGray,
            strokeCap = StrokeCap.Companion.Round
        )
    }
}