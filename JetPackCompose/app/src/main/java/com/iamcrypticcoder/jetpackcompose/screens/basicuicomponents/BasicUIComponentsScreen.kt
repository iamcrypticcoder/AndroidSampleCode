package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

private const val TAG = "BasicUIComponentsScreen"

/**
 * Examples taken from:
 * https://www.geeksforgeeks.org/android/android-jetpack-compose-tutorial/
 */

@Composable
@Preview(showBackground = true)
fun BasicUIComponentsScreen(navController: NavHostController = rememberNavController()) {
    BasicUIComponentsContent()
}

@Composable
fun BasicUIComponentsContent() {
    Column (
        modifier = Modifier
            .fillMaxWidth().fillMaxHeight().padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        TextExample()
        ButtonExample()
        TextFieldExample()
        SwitchExample()
        RadioButtonExample()
        CheckboxExample()
        FABExample()
        IconToggleButtonExample()
        CardExample()
        SnackBarExample()
        AlertDialogExample()
        CircularImageExample()
        CircularProgressIndicatorExample()
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        text = title,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}
