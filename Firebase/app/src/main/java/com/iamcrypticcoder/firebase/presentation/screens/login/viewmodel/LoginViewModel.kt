package com.iamcrypticcoder.firebase.presentation.screens.login.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.iamcrypticcoder.firebase.presentation.screens.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState : StateFlow<LoginState> = _uiState.asStateFlow()


}