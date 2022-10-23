package com.example.appdevelopment.ui.screens.createAccountView

sealed class CreateAccountEvent {
    data class OnUsernameChanged(val username: String): CreateAccountEvent()
    data class OnEmailChanged(val email: String): CreateAccountEvent()
    data class OnPasswordChanged(val password: String): CreateAccountEvent()
    data class OnConfirmPasswordChanged(val confirmPassword: String): CreateAccountEvent()
}