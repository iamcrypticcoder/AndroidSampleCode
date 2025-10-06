package com.iamcrypticcoder.firebase.presentation.navigation

open class Route(val route : String) {
    data object Home : Route("home")
    data object LoginScreen : Route("login")
    data object SignupScreen : Route("signup")
    data object Dashboard : Route("dashboard")
}