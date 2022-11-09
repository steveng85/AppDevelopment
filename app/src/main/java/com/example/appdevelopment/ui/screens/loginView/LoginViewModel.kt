package com.example.appdevelopment.ui.screens.loginView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.data.domain.repository.AuthRepository
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.OnEmailChanged -> onEmailChanged(event.email)
            is LoginEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            is LoginEvent.OnLogin -> login(_uiState.value.emailText, _uiState.value.passwordText)
        }
    }

    fun onEmailChanged(email: String){
        _uiState.value = _uiState.value.copy(emailText = email)
    }

    fun onPasswordChanged(password: String){
        _uiState.value = _uiState.value.copy(passwordText = password)
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

    init {
        if(repository.currentUser != null){
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

    fun logout(){
        repository.logout()
        _loginFlow.value = null

    }



}