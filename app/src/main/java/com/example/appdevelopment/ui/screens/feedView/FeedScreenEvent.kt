package com.example.appdevelopment.ui.screens.feedView

sealed class FeedScreenEvent {
    data class OnUsernameChanged(val username: String): FeedScreenEvent()
    data class OnEmailChanged(val email: String): FeedScreenEvent()
    data class OnPasswordChanged(val password: String): FeedScreenEvent()
    data class OnConfirmPasswordChanged(val confirmPassword: String): FeedScreenEvent()
}