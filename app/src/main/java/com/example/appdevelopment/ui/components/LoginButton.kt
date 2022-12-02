package com.example.appdevelopment.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun LoginButton(onEvent: ()-> Unit){
    DefaultButton(
        onClick = onEvent,
        text = "Login",
        contentColor = MaterialTheme.colorScheme.onPrimary,
        containerColor = MaterialTheme.colorScheme.primary
    )
}