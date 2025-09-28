package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true)
fun ButtonExample() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SectionTitle("Button Examples")

        Spacer(modifier = Modifier.size(8.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp, 16.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Filled Button", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Button(
                modifier = Modifier.padding(0.dp),
                onClick = {
                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
                },
                enabled = true,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                interactionSource = remember { MutableInteractionSource() },
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(
                    text = "Tap Me",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Companion.Bold,
                    fontStyle = FontStyle.Companion.Normal
                )
            }
        }

        Spacer(modifier = Modifier.size(8.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp, 16.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Filled tonal button", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            FilledTonalButton(
                modifier = Modifier.padding(0.dp),
                onClick = {
                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
                },
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
            ) {
                Text(
                    text = "Tap Me",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Companion.Bold,
                    fontStyle = FontStyle.Companion.Normal
                )
            }
        }

        Spacer(modifier = Modifier.size(8.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp, 16.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Outlined button", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            OutlinedButton(
                modifier = Modifier.padding(0.dp),
                onClick = {
                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
                },
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = "Tap Me",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Companion.Bold,
                    fontStyle = FontStyle.Companion.Normal
                )
            }
        }

        Spacer(modifier = Modifier.size(8.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp, 16.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Text Button", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            TextButton(
                modifier = Modifier.padding(0.dp),
                onClick = {
                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
                },
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
            ) {
                Text(
                    text = "Tap Me",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Companion.Bold,
                    fontStyle = FontStyle.Companion.Normal
                )
            }
        }

        Spacer(modifier = Modifier.size(8.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp, 16.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Elevated Button", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            ElevatedButton(
                modifier = Modifier.padding(0.dp),
                onClick = {
                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
                },
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
            ) {
                Text(
                    text = "Tap Me",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Companion.Bold,
                    fontStyle = FontStyle.Companion.Normal
                )
            }
        }
    }
}