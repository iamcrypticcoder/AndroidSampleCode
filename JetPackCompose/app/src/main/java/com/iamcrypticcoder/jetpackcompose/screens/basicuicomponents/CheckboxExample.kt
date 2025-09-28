package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val TAG = "CheckboxExample"

@Composable
@Preview
fun CheckboxExample() {
    val isChecked = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.Companion.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = {
                Log.d(TAG, "Checked state changed = $it")
                isChecked.value = it
            },
            modifier = Modifier.Companion.padding(0.dp),
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Companion.Green,
                uncheckedColor = Color.Companion.DarkGray,
                checkmarkColor = Color.Companion.White
            ),
            interactionSource = remember { MutableInteractionSource() }
        )
        Text(
            text = if (isChecked.value) "Checked" else "Unchecked",
            modifier = Modifier.Companion.padding(top = 0.dp)
        )
    }
}