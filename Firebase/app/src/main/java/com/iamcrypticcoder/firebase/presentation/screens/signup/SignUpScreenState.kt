package com.iamcrypticcoder.firebase.presentation.screens.signup

data class SignUpScreenState(
    var name: String = "",
    var phoneNumber: String = "",
    var password: String = "",
    var signUpInProgress : Boolean = false
)
