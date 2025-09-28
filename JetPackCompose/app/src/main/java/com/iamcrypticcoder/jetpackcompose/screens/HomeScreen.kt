package com.iamcrypticcoder.jetpackcompose.screens

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iamcrypticcoder.jetpackcompose.Routes

private const val TAG = "HomeScreen"

@Composable
@Preview(showBackground = true)
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    HomeContent(navController)
}

@Composable
fun HomeContent(navController: NavHostController) {
    Column (
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(48.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                Log.d(TAG, "BasicUIComponents button clicked")
                navController.navigate(Routes.BasicUIComponents.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            contentPadding = PaddingValues(16.dp),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            Text(
                text = "Basic UI Components",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif)
        }

        Button(
            onClick = {
                navController.navigate(Routes.BottomNavigationBar.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            contentPadding = PaddingValues(16.dp),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            Text(
                text = "Bottom Navigation Bar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif)
        }

        Button(
            onClick = {
                navController.navigate(Routes.CustomLazyColumn.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            contentPadding = PaddingValues(16.dp),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            Text(
                text = "Custom Lazy Column",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif)
        }

        Button(
            onClick = {
                navController.navigate(Routes.NavigationDrawer.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            contentPadding = PaddingValues(16.dp),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            Text(
                text = "Navigation Drawer",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif)
        }

        Button(
            onClick = {
                navController.navigate(Routes.AnimationScreen.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            contentPadding = PaddingValues(16.dp),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            Text(
                text = "Animations",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif)
        }

        Button(
            onClick = {
                navController.navigate(Routes.Material3Components.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            contentPadding = PaddingValues(16.dp),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            Text(
                text = "Material 3 Components",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif)
        }

        Button(
            onClick = {
                navController.navigate(Routes.SampleLoginScreen1.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            contentPadding = PaddingValues(16.dp),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            Text(
                text = "Sample Login Screen 1",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif)
        }

        Button(
            onClick = {
                navController.navigate(Routes.SampleSignupScreen1.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            contentPadding = PaddingValues(16.dp),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            Text(
                text = "Sample Sign up Screen 1",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif)
        }
    }
}