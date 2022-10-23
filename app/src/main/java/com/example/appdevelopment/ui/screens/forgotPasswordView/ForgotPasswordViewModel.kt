package com.example.appdevelopment.ui.screens.forgotPasswordView


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ForgotPasswordViewModel {
    private val _uiState = MutableStateFlow(ForgotPasswordUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ForgotPasswordEvent){
        when(event){
            is ForgotPasswordEvent.OnEmailChanged -> onEmailChanged(event.email)
        }
    }

    fun onEmailChanged(email: String){
        _uiState.value = _uiState.value.copy(emailText = email)
    }
}