package com.iamcrypticcoder.firebase.presentation.screens.login.state

data class LoginState(
    var phoneNumber : String = "",
    var password : String = "",
    var isLoginInProgress : Boolean = false,
)
