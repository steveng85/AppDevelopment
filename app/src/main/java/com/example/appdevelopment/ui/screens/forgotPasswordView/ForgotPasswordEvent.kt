package com.example.appdevelopment.ui.screens.forgotPasswordView

sealed class ForgotPasswordEvent {
    data class OnEmailChanged(val email: String): ForgotPasswordEvent()
    object OnResetPassword : ForgotPasswordEvent()
}