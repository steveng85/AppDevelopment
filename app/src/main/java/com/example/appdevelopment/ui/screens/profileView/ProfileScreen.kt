package com.example.appdevelopment.ui.screens.profileView

import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.appdevelopment.ui.components.LoginTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    LoginTopBar(navController = navController, "Profile")
}