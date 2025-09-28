package com.iamcrypticcoder.jetpackcompose.screens.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Left",
            icon = Icons.Filled.Home,
            route = "Left"
        ),
        BottomNavItem(
            label = "Middle",
            icon = Icons.Filled.Search,
            route = "Middle"
        ),
        BottomNavItem(
            label = "Right",
            icon = Icons.Filled.Person,
            route = "Right"
        )
    )
}