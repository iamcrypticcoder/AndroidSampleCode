package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun SnackBarExample() {
    Text(
        text = "Snack bars",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.Companion.padding(8.dp)
    )
    // Normal Snackbar
    Snackbar(
        modifier = Modifier.Companion.padding(4.dp),
        dismissAction = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }
    ) {
        Text(text = "This is a basic snackbar")
    }
    // Snackbar with action item
    Snackbar(
        modifier = Modifier.Companion.padding(4.dp),
        action = {
            TextButton(onClick = {}) {
                Text(text = "Action")
            }
        },
        dismissAction = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }
    ) {
        Text(text = "This is a basic Snackbar with action item")
    }

    // Snackbar with action item below text
    Snackbar(
        modifier = Modifier.Companion.padding(4.dp),
        actionOnNewLine = true,
        action = {
            TextButton(onClick = {}) {
                Text(text = "Action")
            }
        },
        dismissAction = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }
    ) {
        Text(text = "Snackbar with action item below text")
    }
}