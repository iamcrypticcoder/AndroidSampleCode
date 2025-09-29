package com.iamcrypticcoder.firebase.domain.usecase

import com.iamcrypticcoder.firebase.domain.usecase.base.UseCase

interface LoginUseCase : UseCase {
    interface Callback {
        fun onSuccess(message: String)
        fun onFailed(message: String)
    }
    fun setLoginCredentials(username: String, password: String)
}