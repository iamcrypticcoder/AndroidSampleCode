package com.iamcrypticcoder.jetpackcompose.screens.bottomnavigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


// SO Links
// https://stackoverflow.com/questions/69738397/jetpackcompose-navigation-nested-graphs-cause-viewmodelstore-should-be-set-befo

private const val TAG = "BottomNavigationBarScreen"

@Composable
@Preview(showBackground = true)
fun BottomNavigationBarScreen(navController: NavHostController = rememberNavController()) {
    val nestedNavController = rememberNavController()
    Surface {
        Scaffold (
            bottomBar = {
                BottomNavigationBar(navController, nestedNavController)
            },
            content = { paddingValues ->
                NavHostContainer(navController, nestedNavController, paddingValues)
            }
        )
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
        startDestination = "Left",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("Left") {
                LeftScreen()
            }
            composable("Middle") {
                MiddleScreen()
            }
            composable("Right") {
                RightScreen()
            }
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController, nestedNavController: NavHostController) {
    NavigationBar (
        containerColor = Color(0xFF0F9D58)
    ) {
        val navBackStackEntry by nestedNavController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Log.d(TAG, "Current Route = " + navBackStackEntry?.destination?.route)
        Constants.BottomNavItems.forEach { navItem ->
            NavigationBarItem(
                selected = (currentRoute == navItem.route),
                onClick = {
                    nestedNavController.navigate(navItem.route) {
                        popUpTo(nestedNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    indicatorColor = Color(0xFF195334)
                )
            )
        }
    }
}