package com.iamcrypticcoder.firebase.presentation.screens.login.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamcrypticcoder.firebase.domain.usecase.LoginUseCase
import com.iamcrypticcoder.firebase.presentation.navigation.NavigationEvent
import com.iamcrypticcoder.firebase.presentation.screens.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState : StateFlow<LoginState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun login(phoneNumber: String, password: String) {
        Log.d(TAG, "login()");
        _uiState.update { currentState ->
            currentState.copy(isLoginInProgress = true)
        }
        loginUseCase.setLoginCredentials(phoneNumber, password)
        loginUseCase.setCallback(object : LoginUseCase.Callback {
            override fun onSuccess(message: String) {
                Log.d(TAG, "onSuccess()")
                viewModelScope.launch {
                    _navigationEvent.emit(NavigationEvent.NavigateToDashboard)
                }
            }

            override fun onFailed(message: String) {
                Log.d(TAG, "onFailed()")
                _uiState.update { currentState ->
                    currentState.copy(isLoginInProgress = false)
                }
            }
        })
        loginUseCase.execute()
    }
}