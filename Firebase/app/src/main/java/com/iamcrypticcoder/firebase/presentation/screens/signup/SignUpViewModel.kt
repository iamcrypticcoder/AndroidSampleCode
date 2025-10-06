package com.iamcrypticcoder.firebase.presentation.screens.signup

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.iamcrypticcoder.firebase.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val TAG = "SignUpViewModel"

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpScreenState())
    val uiState = _uiState.asStateFlow()

    fun signUp(name: String, phoneNumber: String, password: String) {
        Log.d(TAG, "signUp()")
        _uiState.update { currentState ->
            currentState.copy(isLoading = true)
        }
        signUpUseCase.setSignUpCredentials(name, phoneNumber, password)
        signUpUseCase.setCallback(object : SignUpUseCase.Callback {
            override fun onSuccess(message: String) {
                Log.d(TAG, "onSuccess()")
            }

            override fun onFailed(message: String) {
                Log.d(TAG, "onFailed()")
            }

        })
        signUpUseCase.execute()
    }
}
