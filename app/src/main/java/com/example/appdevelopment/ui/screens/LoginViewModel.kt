package com.example.appdevelopment.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _emailText = MutableStateFlow("")
    val emailText = _emailText.asStateFlow()
}