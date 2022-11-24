package com.example.appdevelopment.ui.screens.cameraView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.screens.loginView.LoginViewModel

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.R
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.mockData.dailyWord.pickDailyWord
import com.example.appdevelopment.mockData.posts.posts
//import com.example.appdevelopment.ui.components.BottomHomeBar
import com.example.appdevelopment.ui.components.TopHomeBar
import com.example.appdevelopment.ui.layout.Scaffoldlayout
import com.example.appdevelopment.ui.screens.feedView.FeedScreen
import com.example.appdevelopment.ui.screens.feedView.PostList

@ExperimentalMaterial3Api
@Composable

fun CameraScreen(navController: NavController) {

    Scaffoldlayout(navController = navController, pickDailyWord(), Color.Black) { CameraX() }
    
   /*Button(
       onClick = {
           authLogic?.logout()
           navController.navigate(Screen.Login.route) {
               popUpTo(Screen.Login.route) { inclusive = true }
            }
            println("logged out")
       },
       modifier = Modifier.border(border = BorderStroke(20.dp, Color.Black))
   ) {
       Text(text = "logout")
   }*/
}

@Composable
fun CameraX() {
    Text(text = "Hej CameraX")
}