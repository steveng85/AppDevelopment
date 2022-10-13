package com.example.appdevelopment

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.screens.CreateAccountScreen
import com.example.appdevelopment.ui.screens.ForgotPasswordScreen
import com.example.appdevelopment.ui.screens.LoginScreen
import com.example.appdevelopment.ui.screens.PasswordResetScreen

@ExperimentalMaterial3Api
@Composable
fun NavGraph(
    navController: NavHostController
) {
    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(route = Screen.Welcome.route) {
                WelcomeScreen(navController = navController)
            }
            composable(route = Screen.CreateAcc.route) {
                CreateAccountScreen(navController = navController)
            }
            composable(route = Screen.Login.route) {
                LoginScreen(navController = navController)
            }
            composable(route = Screen.ForgotPwd.route) {
                ForgotPasswordScreen(navController = navController)
            }
            composable(route = Screen.PwdReset.route) {
                PasswordResetScreen(navController = navController)
            }
        }
    }
}