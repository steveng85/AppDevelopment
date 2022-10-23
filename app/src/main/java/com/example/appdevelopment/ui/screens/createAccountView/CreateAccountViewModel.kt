package com.example.appdevelopment.ui.screens.createAccountView

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateAccountViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(CreateAccountUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: CreateAccountEvent){
        when(event){
            is CreateAccountEvent.OnUsernameChanged -> onUsernameChanged(event.username)
            is CreateAccountEvent.OnEmailChanged -> onEmailChanged(event.email)
            is CreateAccountEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            is CreateAccountEvent.OnConfirmPasswordChanged -> onConfirmPasswordChanged(event.confirmPassword)
        }
    }

    fun onUsernameChanged(username: String){
        _uiState.value = _uiState.value.copy(usernameText = username)
    }

    fun onEmailChanged(email: String){
        _uiState.value = _uiState.value.copy(emailText = email)
    }

    fun onPasswordChanged(password: String){
        _uiState.value = _uiState.value.copy(passwordText = password)
    }

    fun onConfirmPasswordChanged(confirmPassword: String){
        _uiState.value = _uiState.value.copy(confirmPasswordText = confirmPassword)
    }

}