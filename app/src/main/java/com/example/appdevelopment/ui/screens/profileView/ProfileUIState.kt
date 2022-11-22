package com.example.appdevelopment.ui.screens.profileView


import java.util.*


data class ProfileUIState(
    val bioText: String = "",
    val usernameText: String = "",
    val genderText: String = "",
    val birthdayText: Date = Date(0, 0, 0,)
) {
}