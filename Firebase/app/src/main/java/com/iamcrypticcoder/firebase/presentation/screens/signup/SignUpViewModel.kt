package com.iamcrypticcoder.firebase.presentation.screens.signup

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamcrypticcoder.firebase.domain.usecase.SignUpUseCase
import com.iamcrypticcoder.firebase.presentation.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SignUpViewModel"

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpScreenState())
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun signUp(name: String, phoneNumber: String, password: String) {
        Log.d(TAG, "signUp()")
        _uiState.update { currentState ->
            currentState.copy(signUpInProgress = true)
        }
        signUpUseCase.setSignUpCredentials(name, phoneNumber, password)
        signUpUseCase.setCallback(object : SignUpUseCase.Callback {
            override fun onSuccess(message: String) {
                Log.d(TAG, "onSuccess()")
                viewModelScope.launch {
                    _navigationEvent.emit(NavigationEvent.NavigateToDashboard)
                }
            }

            override fun onFailed(message: String) {
                Log.d(TAG, "onFailed()")
                _uiState.update { currentState ->
                    currentState.copy(signUpInProgress = false)
                }
            }

        })
        signUpUseCase.execute()
    }


}
