package com.iamcrypticcoder.firebase.domain.usecase

import com.iamcrypticcoder.firebase.domain.usecase.base.UseCase

interface SignUpUseCase : UseCase {
    interface Callback {
        fun onSuccess(message: String)
        fun onFailed(message: String)
    }
    fun setSignUpCredentials(
        name: String,
        phoneNumber: String,
        password: String)
    fun setCallback(callback: Callback)
}
