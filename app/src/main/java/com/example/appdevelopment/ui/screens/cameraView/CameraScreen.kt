package com.example.appdevelopment.ui.screens.cameraView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.screens.loginView.LoginViewModel

@Composable
fun CameraScreen(navController: NavController, viewModel: LoginViewModel?) {
    Text(text = "Hey cameraX")

    Button(
        onClick = {
            viewModel?.logout()
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
            println("logged out")
        },
        modifier = Modifier.border(border = BorderStroke(20.dp, Color.Black))
    ) {
        Text(text = "logout")
    }
}