package com.example.appdevelopment

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.screens.CreateAccountScreen
import com.example.appdevelopment.ui.screens.ForgotPasswordScreen
import com.example.appdevelopment.ui.screens.LoginScreen
import com.example.appdevelopment.ui.screens.PasswordResetScreen
import com.example.appdevelopment.ui.screens.cameraView.CameraScreen
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountViewModel
import com.example.appdevelopment.ui.screens.forgotPasswordView.ForgotPasswordViewModel
import com.example.appdevelopment.ui.screens.loginView.LoginViewModel

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
                val createAccountVM by remember {
                    mutableStateOf(CreateAccountViewModel())
                }
                CreateAccountScreen(navController = navController, createAccountVM.uiState.collectAsState().value){
                    createAccountVM.onEvent(it)
                }
            }
            composable(route = Screen.Login.route) {
                val loginVM by remember {
                    mutableStateOf(LoginViewModel())
                }
                LoginScreen(navController = navController, loginVM.uiState.collectAsState().value){
                    loginVM.onEvent(it)
                }
            }
            composable(route = Screen.ForgotPwd.route) {
                val forgotPasswordVM by remember {
                    mutableStateOf(ForgotPasswordViewModel())
                }
                ForgotPasswordScreen(navController = navController, forgotPasswordVM.uiState.collectAsState().value){
                    forgotPasswordVM.onEvent(it)
                }
            }
            composable(route = Screen.PwdReset.route) {
                PasswordResetScreen(navController = navController)
            }
            composable(route = Screen.Camera.route) {
                CameraScreen(navController = navController)
            }
        }
    }
}