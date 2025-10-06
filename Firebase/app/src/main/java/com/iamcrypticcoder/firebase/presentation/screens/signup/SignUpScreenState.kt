package com.iamcrypticcoder.firebase.presentation.screens.signup

data class SignUpScreenState(
    var isLoading : Boolean = false,
    var phoneNumber: String = "",
    var password: String = ""
)
