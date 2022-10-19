package com.example.appdevelopment.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun LoginButton(route: String, navController: NavController){
    DefaultButton(
        onClick = route,
        text = "Login",
        contentColor = Color.White,
        containerColor = Color(0xFF007FFF)
    )
}