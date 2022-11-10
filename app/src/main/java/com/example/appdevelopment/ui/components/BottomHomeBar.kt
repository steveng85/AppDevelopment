package com.example.appdevelopment.ui.components
//
//import android.R.attr.label
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material.icons.rounded.Favorite
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.appdevelopment.navigation.Screen
//
//@Composable
//fun BottomHomeBar(navController: NavController) {
//    NavigationBar {
//        NavigationBarItem(
//            onClick = { navController.navigate(Screen.Feed.route) },
//            icon = { Icon(Icons.Filled.List, contentDescription = "hej") },
//            selected = false
//        )
//
//        NavigationBarItem(
//            onClick = { navController.navigate(Screen.Camera.route) },
//            icon = ,
//            selected = false,
//            colors = MaterialTheme.colorScheme.primary
//        )
//
//        NavigationBarItem(
//            onClick = { navController.navigate(Screen.Profile.route) },
//            icon = { Icon(Icons.Filled.Favorite, contentDescription = "hej") },
//            selected = false
//        )
//    }
//}
//
//@Preview
//@Composable
//fun BottomHomeBarPreview() {
//    BottomHomeBar(navController = rememberNavController())
//}