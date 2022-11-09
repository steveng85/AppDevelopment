package com.example.appdevelopment.ui.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.appdevelopment.R
import com.example.appdevelopment.navigation.Screen

//@ExperimentalMaterial3Api
//@Composable
//fun Scaffoldlayout(navController: String, text: String, screenContent: Unit) {
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(
//                        text = text,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis,
//                        color = Color.Black
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = { Screen.Profile.route }) {
//                        Icon(Icons.Default.Person, contentDescription = "Localized description")
//                    }
//                }
//            )
//        },
//        bottomBar = {
//            BottomAppBar(
//                Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight(),
//                backgroundColor = Color.Black,
//            ) {
//                Row(
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    modifier = Modifier.fillMaxWidth(1f)
//                ) {
//                    IconButton(onClick = { Screen.Feed.route }) {
//                        Icon(painter = painterResource(
//                            id = R.drawable.ic_baseline_menu_24),
//                            contentDescription = null,
//                            tint = Color.White
//                        )
//                    }
//                    IconButton(onClick = { Screen.Camera.route }) {
//                        Icon(painter = painterResource(
//                            id = R.drawable.ic_baseline_photo_camera_24),
//                            contentDescription = null,
//                            tint = Color.White
//                        )
//                    }
//                    IconButton(onClick = { Screen.Leaderboards.route }) {
//                        Icon(painter = painterResource(
//                            id = R.drawable.ic_baseline_leaderboard_24),
//                            contentDescription = null,
//                            tint = Color.White
//                        )
//                    }
//                }
//            }
//        }
//    ) {
//        screenContent
//    }
//}