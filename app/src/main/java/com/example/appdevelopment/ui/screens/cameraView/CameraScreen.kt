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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.appdevelopment.navigation.Screen
//import com.example.appdevelopment.ui.components.BottomHomeBar
import com.example.appdevelopment.ui.components.LoginTopBar
import com.example.appdevelopment.ui.components.TopHomeBar


@ExperimentalMaterial3Api
@Composable

fun CameraScreen(navController: NavController, viewModel: LoginViewModel?) {
    Text(text = "Hey cameraX")
    
     Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            TopHomeBar(dailyWord = "find Steven hehe") { navController.navigate(Screen.Profile.route) }
        }

        //cameraX

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            //BottomHomeBar(navController)
        }
    }

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


@ExperimentalMaterial3Api
@Preview
@Composable
fun CameraScreenPreview() {
    //CameraScreen(navController = NavController())
}


