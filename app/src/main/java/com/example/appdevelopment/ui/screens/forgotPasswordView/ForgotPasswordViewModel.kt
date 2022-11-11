package com.example.appdevelopment.ui.screens.forgotPasswordView


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevelopment.data.AuthLogic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authLogic: AuthLogic
) : ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ForgotPasswordEvent){
        when(event){
            is ForgotPasswordEvent.OnEmailChanged -> onEmailChanged(event.email)
            is ForgotPasswordEvent.OnResetPassword -> onResetPassword(_uiState.value.emailText)
        }
    }

    fun onEmailChanged(email: String){
        _uiState.value = _uiState.value.copy(emailText = email)
    }

    fun onResetPassword(email: String) = viewModelScope.launch{
        authLogic.resetPassword(email)
    }
}