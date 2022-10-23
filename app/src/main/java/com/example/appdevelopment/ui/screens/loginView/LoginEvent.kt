package com.example.appdevelopment.ui.screens.loginView

sealed class LoginEvent {
    data class OnEmailChanged(val email: String): LoginEvent()
    data class OnPasswordChanged(val password: String): LoginEvent()
}