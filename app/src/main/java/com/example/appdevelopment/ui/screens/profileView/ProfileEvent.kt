package com.example.appdevelopment.ui.screens.profileView

import com.example.appdevelopment.ui.screens.loginView.LoginEvent
import java.util.*


sealed class ProfileEvent {
    data class OnBioChanged(val bio: String): ProfileEvent()
    data class OnUsernameChanged(val username: String): ProfileEvent()
    data class OnGenderChanged(val gender: String): ProfileEvent()
    data class OnBirtday(val birthday: Date): ProfileEvent()
    data class OnEditInfo(val edit: Boolean): ProfileEvent()
    object OnSave: ProfileEvent()
}