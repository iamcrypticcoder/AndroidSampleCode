package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun FABExample() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.Companion.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Text(
            text = "Floating Action Buttons in Android\nJetpack Compose",
            color = Color.Companion.Green,
            fontFamily = FontFamily.Companion.Default,
            fontWeight = FontWeight.Companion.Bold, textAlign = TextAlign.Companion.Center
        )
        Spacer(modifier = Modifier.Companion.height(20.dp))

        FloatingActionButton(
            onClick = {
                Toast.makeText(
                    context, "Simple Floating Action Button",
                    Toast.LENGTH_SHORT
                ).show()
            },
            containerColor = Color.Companion.Green,
            contentColor = Color.Companion.White
        ) {
            Icon(Icons.Filled.Add, "")
        }
        Spacer(modifier = Modifier.Companion.height(20.dp))


        FloatingActionButton(
            onClick = {
                Toast.makeText(
                    context, "Square Floating Action Button",
                    Toast.LENGTH_SHORT
                ).show()
            },
            shape = RectangleShape,
            containerColor = Color.Companion.Green,
            contentColor = Color.Companion.White
        ) {
            Icon(Icons.Filled.Add, "")
        }
        Spacer(modifier = Modifier.Companion.height(20.dp))

        ExtendedFloatingActionButton(
            text = { Text(text = "Extended FAB") },
            onClick = {
                Toast.makeText(
                    context, "Extended Floating Action Button",
                    Toast.LENGTH_SHORT
                ).show()
            },
            containerColor = Color.Companion.Green,
            contentColor = Color.Companion.White,
            icon = { Icon(Icons.Filled.Add, "") }
        )
    }
}