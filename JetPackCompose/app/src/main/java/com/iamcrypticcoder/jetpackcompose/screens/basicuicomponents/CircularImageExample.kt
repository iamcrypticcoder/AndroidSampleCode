package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamcrypticcoder.jetpackcompose.R

@Composable
@Preview
fun CircularImageExample() {
    val painter = painterResource(id = R.drawable.ic_launcher_foreground)

    Row(
        modifier = Modifier.Companion.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.Companion.size(64.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(Color.Companion.White),
            elevation = CardDefaults.cardElevation(18.dp)
        ) {
            Image(
                painter,
                contentDescription = "GFG Logo",
                modifier = Modifier.Companion.fillMaxSize().clip(CircleShape).padding(16.dp),
                alignment = Alignment.Companion.Center,
                contentScale = ContentScale.Companion.Fit,
                alpha = DefaultAlpha
            )
        }
    }
}