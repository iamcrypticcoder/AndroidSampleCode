package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val TAG = "TextExample"

@Composable
@Preview(showBackground = true)
fun TextExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SectionTitle("Text Example")

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "This is text",
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 1.5.sp,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center,
                maxLines = 2,
                minLines = 1,
                onTextLayout = {
                    Log.d(TAG, "Line Count ${it.lineCount}")
                },
                style = TextStyle(
                    background = Color.Green,
                    shadow = Shadow(color = Color.LightGray, blurRadius = 40f)
                )
            )
        }
    }
}