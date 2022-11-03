package com.example.appdevelopment.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun LoginButton(onEvent: ()-> Unit){
    DefaultButton(
        onClick = onEvent,
        text = "Login",
        contentColor = Color.White,
        containerColor = MaterialTheme.colorScheme.primary
    )
}