package com.example.appdevelopment.data

import com.example.appdevelopment.data.dataClasses.User
import com.example.appdevelopment.data.domain.repository.AuthRepository
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLogic @Inject constructor(
    private val repository: AuthRepository,
    private val firebaseRepository: FireStoreRepository
) {
    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    private val _resetPasswordFlow = MutableStateFlow<Resource<Void>?>(null)
    val resetPasswordFlow: StateFlow<Resource<Void>?> = _resetPasswordFlow


    suspend fun login(email: String, password: String) {
        _loginFlow.value = Resource.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

    suspend fun createUser(name: String, email: String, password: String, confirmPassword: String){
        if(password == confirmPassword){
            _signupFlow.value = Resource.Loading
            val result = repository.signup(name, email, password)
            _signupFlow.value = result
            val userId = repository.currentUser
            if(!userId?.uid.isNullOrEmpty()) {
                userId?.uid?.let { saveUser(name, it, email) }
            }
        }
    }

    suspend fun saveUser(username: String, token: String, email: String) {
        firebaseRepository.saveUser(User(token, username, email, 0))
    }

    suspend fun resetPassword(email: String){
        _resetPasswordFlow.value = Resource.Loading
        val result = repository.forgotPassword(email)
        _resetPasswordFlow.value = result
    }

    init {
        if(repository.currentUser != null){
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

    fun logout(){
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }
}