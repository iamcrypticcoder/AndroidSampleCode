package com.iamcrypticcoder.firebase.presentation.navigation

sealed class NavigationEvent {
    object NavigateToHome : NavigationEvent()
    object NavigateToSignUp : NavigationEvent()
    object NavigateToDashboard : NavigationEvent()
}