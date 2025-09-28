package com.iamcrypticcoder.jetpackcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamcrypticcoder.jetpackcompose.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun NavigationDrawerScreen(navController: NavHostController = rememberNavController()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val nestedNavController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(navController, nestedNavController, drawerState)
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.topAppBarColors(Color.White),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavHostContainer(navController, nestedNavController, paddingValues)
            }
        }
    }
}

@Composable
fun NavHostContainer(
    navController : NavHostController,
    nestedNavController : NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = nestedNavController,
        startDestination = "drawer_page_1",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("drawer_page_1") { ScreenContent("drawer_page_1") }
            composable("drawer_page_2") { ScreenContent("drawer_page_2") }
            composable("drawer_page_3") { ScreenContent("drawer_page_3") }
        }
    )
}

@Composable
fun ScreenContent(text: String) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center) {
        Text(text = text, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun DrawerContent(
    navController: NavHostController,
    nestedNavController: NavHostController,
    drawerState: DrawerState
) {
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))

        val scope = rememberCoroutineScope()
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            listOf("drawer_page_1", "drawer_page_2", "drawer_page_3").forEach { screen ->
                Text(
                    text = screen,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            nestedNavController.navigate(screen)
                            scope.launch { drawerState.close() }
                        }
                        .padding(12.dp)
                )
            }
        }
    }
}
