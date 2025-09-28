package com.iamcrypticcoder.contactaccess.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel() {
    private val _myStateFlow = MutableStateFlow<String>("Initial Value")
    val myStateFlow: StateFlow<String> = _myStateFlow

    fun updateUIState(newValue: String) {
        viewModelScope.launch {
            _myStateFlow.value = newValue
        }
    }

}