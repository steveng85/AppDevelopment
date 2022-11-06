package com.example.appdevelopment.ui.screens.createAccountView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.data.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    private val _uiState = MutableStateFlow(CreateAccountUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: CreateAccountEvent){
        when(event){
            is CreateAccountEvent.OnUsernameChanged -> onUsernameChanged(event.username)
            is CreateAccountEvent.OnEmailChanged -> onEmailChanged(event.email)
            is CreateAccountEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            is CreateAccountEvent.OnConfirmPasswordChanged -> onConfirmPasswordChanged(event.confirmPassword)
            is CreateAccountEvent.OnCreateUser -> onCreateUser(
                _uiState.value.usernameText,
                _uiState.value.emailText,
                _uiState.value.passwordText,
                _uiState.value.confirmPasswordText
            )
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

    fun onCreateUser(name: String, email: String, password: String, confirmPassword: String) = viewModelScope.launch{
        if(password == confirmPassword){
            _signupFlow.value = Resource.Loading
            val result = repository.signup(name, email, password)
            _signupFlow.value = result
        }
    }
}