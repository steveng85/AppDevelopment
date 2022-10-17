package com.example.appdevelopment.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun LoginButton(route: String){
    DefaultButton(
        onClick = route,
        text = "Login",
        contentColor = Color.White,
        containerColor = Color(0xFF007FFF)
    )
}