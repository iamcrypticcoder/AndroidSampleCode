package com.iamcrypticcoder.jetpackcompose.material3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamcrypticcoder.jetpackcompose.ui.theme.JetPackComposeTheme

// Reference: https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-outlined-text-field.html
// https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/Card.kt
// https://www.geeksforgeeks.org/android/android-jetpack-compose-tutorial/

// compose-samples
// https://github.com/android/compose-samples/tree/main
// https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/material3/material3/samples/src/main/java/androidx/compose/material3/samples/;bpv=1;bpt=0

class OutlinedTextFieldSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            JetPackComposeTheme {
                SampleScreen()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SampleScreen() {
    // Common parameters
    var text by remember { mutableStateOf("") }
    val enabled by remember { mutableStateOf(true) }
    val readOnly by remember { mutableStateOf(false) }
    val label by remember { mutableStateOf("Label") }
    val placeholder by remember { mutableStateOf("Placeholder") }

    // Variant selection
    val variant by remember { mutableStateOf("Custom Style") }
    val variants = listOf(
        "Basic",
        "With Icons",
        "Password",
        "Error State",
        "With Prefix and Suffix",
        "Custom Style"
    )

    // Icon parameters
    val showLeadingIcon by remember { mutableStateOf(true) }
    val showTrailingIcon by remember { mutableStateOf(true) }

    // Error state parameters
    val isError by remember { mutableStateOf(false) }
    val errorMessage by remember { mutableStateOf("Error message") }

    // Prefix/suffix parameters
    val prefix by remember { mutableStateOf("$") }
    val suffix by remember { mutableStateOf(".00") }

    // Password parameters
    val hidePassword by remember { mutableStateOf(true) }

    // Text field options
    val singleLine by remember { mutableStateOf(false) }
    val maxLines by remember { mutableStateOf(if (singleLine) 1 else 5) }
    val minLines by remember { mutableStateOf(1) }

    // Keyboard options
    val keyboardTypeOptions = listOf(
        "Text", "ASCII", "Number", "Phone", "Uri", "Email", "Password", "NumberPassword"
    )

    val keyboardType by remember { mutableStateOf(keyboardTypeOptions[0]) }

    val imeActionOptions = listOf(
        "Default", "None", "Go", "Search", "Send", "Next", "Previous", "Done"
    )
    val imeAction by remember { mutableStateOf(imeActionOptions[0]) }

    // Shape parameters
    val useCustomShape by remember { mutableStateOf(false) }
    val cornerRadius by remember { mutableStateOf(4f) }

    // Color parameters
    val useCustomColors by remember { mutableStateOf(false) }
    val customContainerColor by remember { mutableStateOf(Color(0xFFE8DEF8)) }
    val customTextColor by remember { mutableStateOf(Color(0xFF1D192B)) }

    // State management
    var passwordVisible by remember { mutableStateOf(false) }

    // Convert parameters to actual values
    val selectedKeyboardType = when (keyboardType) {
        "ASCII" -> KeyboardType.Ascii
        "Number" -> KeyboardType.Number
        "Phone" -> KeyboardType.Phone
        "Uri" -> KeyboardType.Uri
        "Email" -> KeyboardType.Email
        "Password" -> KeyboardType.Password
        "NumberPassword" -> KeyboardType.NumberPassword
        else -> KeyboardType.Text
    }

    val selectedImeAction = when (imeAction) {
        "None" -> ImeAction.None
        "Go" -> ImeAction.Go
        "Search" -> ImeAction.Search
        "Send" -> ImeAction.Send
        "Next" -> ImeAction.Next
        "Previous" -> ImeAction.Previous
        "Done" -> ImeAction.Done
        else -> ImeAction.Default
    }

    val keyboardActionsInstance = KeyboardActions(
        onDone = { /* Handle done action */ },
        onGo = { /* Handle go action */ },
        onNext = { /* Handle next action */ },
        onPrevious = { /* Handle previous action */ },
        onSearch = { /* Handle search action */ },
        onSend = { /* Handle send action */ }
    )

    val shape: Shape = if (useCustomShape) RoundedCornerShape(cornerRadius.dp) else OutlinedTextFieldDefaults.shape

    val colors = if (useCustomColors) {
        OutlinedTextFieldDefaults.colors(
            focusedTextColor = customTextColor,
            unfocusedTextColor = customTextColor,
            disabledTextColor = customTextColor.copy(alpha = 0.38f),
            focusedContainerColor = customContainerColor,
            unfocusedContainerColor = customContainerColor,
            disabledContainerColor = customContainerColor.copy(alpha = 0.38f)
        )
    } else {
        OutlinedTextFieldDefaults.colors()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        when (variant) {
            "Basic" -> {
                // Basic OutlinedTextField
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(label, color = MaterialTheme.colorScheme.onSurface) },
                    placeholder = { Text(placeholder, color = MaterialTheme.colorScheme.onSurface) },
                    leadingIcon = if (showLeadingIcon) {
                        { Icon(Icons.Filled.Email, contentDescription = "Email Icon") }
                    } else null,
                    trailingIcon = if (showTrailingIcon) {
                        {
                            if (text.isNotEmpty()) {
                                IconButton(onClick = { text = "" }) {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "Clear text"
                                    )
                                }
                            }
                        }
                    } else null,
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text(
                                text = errorMessage,
                                color = Color.Red
                            )
                        }
                    },
                    prefix = { Text(prefix) },
                    suffix = { Text(suffix) },
                    enabled = enabled,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    maxLines = maxLines,
                    minLines = minLines,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = selectedKeyboardType,
                        imeAction = selectedImeAction
                    ),
                    keyboardActions = keyboardActionsInstance,
                    shape = shape,
                    colors = colors,
                    modifier = Modifier.padding(8.dp)
                )
            }
            "With Icons" -> {
                // OutlinedTextField with icons
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    leadingIcon = if (showLeadingIcon) {
                        { Icon(Icons.Filled.Email, contentDescription = "Email Icon") }
                    } else null,
                    trailingIcon = if (showTrailingIcon) {
                        {
                            if (text.isNotEmpty()) {
                                IconButton(onClick = { text = "" }) {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "Clear text"
                                    )
                                }
                            }
                        }
                    } else null,
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text(
                                text = errorMessage,
                                color = Color.Red
                            )
                        }
                    },
                    prefix = { Text(prefix) },
                    suffix = { Text(suffix) },
                    enabled = enabled,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    maxLines = maxLines,
                    minLines = minLines,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = selectedKeyboardType,
                        imeAction = selectedImeAction
                    ),
                    keyboardActions = keyboardActionsInstance,
                    shape = shape,
                    colors = colors,
                    modifier = Modifier.padding(8.dp)
                )
            }
            "Password" -> {
                // Password OutlinedTextField
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    visualTransformation = if (!hidePassword)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    leadingIcon = if (showLeadingIcon) {
                        { Icon(Icons.Filled.Email, contentDescription = "Email Icon") }
                    } else null,
                    trailingIcon = if (showTrailingIcon) {
                        {
                            if (text.isNotEmpty()) {
                                IconButton(onClick = { text = "" }) {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "Clear text"
                                    )
                                }
                            }
                        }
                    } else null,
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text(
                                text = errorMessage,
                                color = Color.Red
                            )
                        }
                    },
                    prefix = { Text(prefix) },
                    suffix = { Text(suffix) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = selectedImeAction
                    ),
                    keyboardActions = keyboardActionsInstance,
                    enabled = enabled,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    maxLines = maxLines,
                    minLines = minLines,
                    shape = shape,
                    colors = colors,
                    modifier = Modifier.padding(8.dp)
                )
            }
            "Error State" -> {
                // OutlinedTextField with error state
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    leadingIcon = if (showLeadingIcon) {
                        { Icon(Icons.Filled.Email, contentDescription = "Email Icon") }
                    } else null,
                    trailingIcon = if (showTrailingIcon) {
                        {
                            if (text.isNotEmpty()) {
                                IconButton(onClick = { text = "" }) {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "Clear text"
                                    )
                                }
                            }
                        }
                    } else null,
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text(
                                text = errorMessage,
                                color = Color.Red
                            )
                        }
                    },
                    prefix = { Text(prefix) },
                    suffix = { Text(suffix) },
                    enabled = enabled,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    maxLines = maxLines,
                    minLines = minLines,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = selectedKeyboardType,
                        imeAction = selectedImeAction
                    ),
                    keyboardActions = keyboardActionsInstance,
                    shape = shape,
                    colors = colors,
                    modifier = Modifier.padding(8.dp)
                )
            }
            "With Prefix and Suffix" -> {
                // OutlinedTextField with prefix and suffix
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    leadingIcon = if (showLeadingIcon) {
                        { Icon(Icons.Filled.Email, contentDescription = "Email Icon") }
                    } else null,
                    trailingIcon = if (showTrailingIcon) {
                        {
                            if (text.isNotEmpty()) {
                                IconButton(onClick = { text = "" }) {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "Clear text"
                                    )
                                }
                            }
                        }
                    } else null,
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text(
                                text = errorMessage,
                                color = Color.Red
                            )
                        }
                    },
                    prefix = { Text(prefix) },
                    suffix = { Text(suffix) },
                    enabled = enabled,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    maxLines = maxLines,
                    minLines = minLines,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = selectedKeyboardType,
                        imeAction = selectedImeAction
                    ),
                    keyboardActions = keyboardActionsInstance,
                    shape = shape,
                    colors = colors,
                    modifier = Modifier.padding(8.dp)
                )
            }
            "Custom Style" -> {
                // OutlinedTextField with custom text style
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                    ),
                    leadingIcon = if (showLeadingIcon) {
                        { Icon(Icons.Filled.Email, contentDescription = "Email Icon") }
                    } else null,
                    trailingIcon = if (showTrailingIcon) {
                        {
                            if (text.isNotEmpty()) {
                                IconButton(onClick = { text = "" }) {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "Clear text"
                                    )
                                }
                            }
                        }
                    } else null,
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text(
                                text = errorMessage,
                                color = Color.Red
                            )
                        }
                    },
                    prefix = { Text(prefix) },
                    suffix = { Text(suffix) },
                    enabled = enabled,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    maxLines = maxLines,
                    minLines = minLines,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = selectedKeyboardType,
                        imeAction = selectedImeAction
                    ),
                    keyboardActions = keyboardActionsInstance,
                    shape = shape,
                    colors = colors,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
