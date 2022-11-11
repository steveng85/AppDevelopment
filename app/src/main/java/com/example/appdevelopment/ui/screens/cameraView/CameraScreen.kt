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
import com.example.appdevelopment.mockData.posts.posts
//import com.example.appdevelopment.ui.components.BottomHomeBar
import com.example.appdevelopment.ui.components.TopHomeBar
import com.example.appdevelopment.ui.screens.feedView.FeedScreen
import com.example.appdevelopment.ui.screens.feedView.PostList
import com.example.appdevelopment.ui.screens.feedView.Scaffoldlayout


@ExperimentalMaterial3Api
@Composable


fun CameraScreen(navController: NavController, authLogic: AuthLogic?) {

    Scaffoldcamlayout(navController = navController, "Sailboat")
    
   Button(
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
   }
}

@ExperimentalMaterial3Api
@Composable
fun Scaffoldcamlayout(navController: NavController, dailyWord: String) {
    androidx.compose.material3.Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    androidx.compose.material3.Text(
                        text = dailyWord,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                backgroundColor = Color.Black,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(1f)
                ) {
                    IconButton(onClick = { navController.navigate(Screen.Feed.route) }) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_baseline_menu_24
                            ),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = { navController.navigate(Screen.Camera.route) }) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_baseline_photo_camera_24
                            ),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = { navController.navigate(Screen.Leaderboards.route) }) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_baseline_leaderboard_24
                            ),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    ) {
        Text(text = "Hej CameraX")
    }
}


@ExperimentalMaterial3Api
@Preview
@Composable
fun CameraScreenPreview() {
    //CameraScreen(navController = NavController())
}


