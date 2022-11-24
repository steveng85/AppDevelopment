package com.example.appdevelopment.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.appdevelopment.R
import com.example.appdevelopment.mockData.dailyWord.DailyWord
import com.example.appdevelopment.mockData.dailyWord.dailyWords
import com.example.appdevelopment.navigation.Screen
import java.util.*

@ExperimentalMaterial3Api
@Composable
fun Scaffoldlayout(navController: NavController, text: String, color: Color, screenContent: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .wrapContentHeight(),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(color),
                title = {
                    androidx.compose.material3.Text(
                        text = text,
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
    ){
        Column(
            modifier = Modifier.padding(it)
        ) {
            screenContent()
        }
    }
}