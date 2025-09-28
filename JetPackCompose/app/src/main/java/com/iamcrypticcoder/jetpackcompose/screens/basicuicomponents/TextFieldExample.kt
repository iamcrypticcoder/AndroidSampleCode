package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val TAG = "TextFieldExample"

@Composable
@Preview(showBackground = true)
fun TextFieldExample() {
    var inputValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SectionTitle("Text Field Example")

        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp).fillMaxWidth(),
            enabled = true,
            readOnly = false,
            textStyle = TextStyle(
                color = Color.Companion.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Companion.Bold,
                fontStyle = FontStyle.Companion.Normal,

                // below line is used to change font family.
                fontFamily = FontFamily.Companion.SansSerif,
                letterSpacing = 0.5.sp,
                textDecoration = TextDecoration.Companion.None,
                textAlign = TextAlign.Companion.Start
            ),
            label = { Text("Phone Number") },
            placeholder = { Text("Enter phone number") },
            leadingIcon = {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = Color(0xFF6200EE)
                )
            },
            trailingIcon = {
                Icon(Icons.Filled.Info, contentDescription = null, tint = Color(0xFF6200EE))
            },
            isError = false,
            visualTransformation = VisualTransformation.Companion.None,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Companion.None,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Companion.Text,
                imeAction = ImeAction.Companion.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    Log.d(TAG, "KeyboardActions # onDone")
                }
            ),
            singleLine = true,
            maxLines = 2,
            minLines = 1,
            interactionSource = remember { MutableInteractionSource() },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Companion.Green,
                disabledTextColor = Color.Companion.Green,
                focusedContainerColor = Color.Companion.LightGray,
                unfocusedContainerColor = Color.Companion.LightGray,
                cursorColor = Color.Companion.Blue,
                errorCursorColor = Color.Companion.Red,
                focusedIndicatorColor = Color.Companion.Transparent,
                unfocusedIndicatorColor = Color.Companion.Transparent
            )
        )

        OutlinedTextField(
            modifier = Modifier.Companion.padding(start = 16.dp, end = 16.dp).fillMaxWidth(),
            value = inputValue,
            onValueChange = {
                Log.d(TAG, "OutlinedTextFieldExample: $it")
            },
            label = {
                Text(text = "Enter your name")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "You entered: $inputValue",
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
        )
    }
}