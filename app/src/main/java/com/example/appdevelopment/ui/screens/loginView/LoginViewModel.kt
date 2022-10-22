package com.example.appdevelopment.ui.screens.loginView

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _emailText = MutableStateFlow("")
    val emailText = _emailText.asStateFlow()

    val email = savedStateHandle.getStateFlow("email", "")

    fun saveEmail(email: String){
        savedStateHandle["email"] = email
    }

}