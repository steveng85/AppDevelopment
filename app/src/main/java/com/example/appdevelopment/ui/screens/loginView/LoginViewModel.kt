package com.example.appdevelopment.ui.screens.loginView

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel() : ViewModel() {
    private val _emailText = MutableStateFlow("")
    val emailText = _emailText.asStateFlow()

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.OnEmailChanged -> onEmailChanged(event.email)
        }
    }

    fun onEmailChanged(email: String){
        _uiState.value = _uiState.value.copy(emailText = email)
    }

}