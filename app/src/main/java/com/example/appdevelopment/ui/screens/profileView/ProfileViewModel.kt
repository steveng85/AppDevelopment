package com.example.appdevelopment.ui.screens.profileView

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.data.dataClasses.Board
import com.example.appdevelopment.data.dataClasses.User
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.ui.screens.loginView.LoginEvent
import com.example.appdevelopment.ui.screens.loginView.LoginUIState
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fireStoreRepository: FireStoreRepository,
    private val authLogic: AuthLogic
): ViewModel() {

    private val _user = MutableStateFlow<User?>( null)
    val user: StateFlow<User?> = _user

    private val _uiState = MutableStateFlow(ProfileUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ProfileEvent){
        when(event){
            is ProfileEvent.OnBioChanged -> onBioChanged(event.bio)
            is ProfileEvent.OnUsernameChanged -> onUsernameChanged(event.username)
            is ProfileEvent.OnGenderChanged -> onGenderChanged(event.gender)
            is ProfileEvent.OnBirtday -> onBirthdayChanged(event.birthday)
            is ProfileEvent.OnEditInfo -> onEditInfo(event.edit)
            is ProfileEvent.OnSave -> onSave(
                _uiState.value.bioText,
                _uiState.value.usernameText,
                _uiState.value.genderText,
                _uiState.value.birthdayText
            )
        }
    }

    fun onBioChanged(bio: String){
        _uiState.value = _uiState.value.copy(bioText = bio)
    }
    fun onUsernameChanged(username: String){
        _uiState.value = _uiState.value.copy(usernameText = username)
    }
    fun onGenderChanged(gender: String){
        _uiState.value = _uiState.value.copy(genderText = gender)
    }
    fun onBirthdayChanged(date: Date){
        _uiState.value = _uiState.value.copy(birthdayText = date)
    }
    fun onEditInfo(edit: Boolean){
        _uiState.value = _uiState.value.copy(editState = edit)
    }


    fun onSave(bio: String, username: String, gender: String, birthday: Date) = viewModelScope.launch {
        if(username != "") {
            authLogic.getCurrentUserId()
                ?.let { fireStoreRepository.updateUser(User(it, username, "", 0, 0, 0, bio, gender)) }
            onGet()
        }
        onEditInfo(false)
    }

    fun onGet() = viewModelScope.launch{
        val userId = authLogic.getCurrentUserId()
        if (userId != null) {
            _user.value = fireStoreRepository.getUserInfo(userId)
            onUsernameChanged(_user.value!!.username)
            onGenderChanged(_user.value!!.gender)
            onBioChanged(_user.value!!.description)
            println(_user.value)
        }
    }


}