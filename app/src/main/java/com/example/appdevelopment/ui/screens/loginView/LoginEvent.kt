package com.example.appdevelopment.ui.screens.loginView

sealed class LoginEvent {
    data class  OnEmailChanged(val email: String): LoginEvent()
}