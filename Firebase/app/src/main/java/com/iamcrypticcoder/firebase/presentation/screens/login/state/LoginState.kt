package com.iamcrypticcoder.firebase.presentation.screens.login.state

data class LoginState(
    var isLoading : Boolean = false,
    var success : Int = -1,
    var loginList : List<String> = emptyList(),
    var error : String = "",
    var internet : Boolean = false
)
