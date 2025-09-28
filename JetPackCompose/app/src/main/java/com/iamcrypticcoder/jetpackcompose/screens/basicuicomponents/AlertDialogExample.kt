package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
@Preview
fun AlertDialogExample() {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.Companion.fillMaxWidth().fillMaxHeight().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Show Alert Dialog")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = {
                        Toast.makeText(context, "Confirm Button Clicked", Toast.LENGTH_SHORT).show()
                        showDialog = false
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        Toast.makeText(context, "Dismiss Button Clicked", Toast.LENGTH_SHORT).show()
                        showDialog = false
                    }) {
                        Text("Dismiss")
                    }
                },
                icon = {
                    Icon(imageVector = Icons.Default.Warning, contentDescription = "Warning Icon")
                },
                title = {
                    Text(text = "Alert Dialog Title", color = Color.Companion.Black)
                },
                text = {
                    Text(
                        text = "This is the content of the alert dialog.",
                        color = Color.Companion.DarkGray
                    )
                },
                modifier = Modifier.Companion.padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                containerColor = Color.Companion.White,
                iconContentColor = Color.Companion.Red,
                titleContentColor = Color.Companion.Black,
                textContentColor = Color.Companion.DarkGray,
                tonalElevation = 8.dp,
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false
                )
            )
        }
    }
}