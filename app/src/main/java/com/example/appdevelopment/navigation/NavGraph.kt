package com.example.appdevelopment

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.screens.CreateAccountScreen
import com.example.appdevelopment.ui.screens.ForgotPasswordScreen
import com.example.appdevelopment.ui.screens.LoginScreen
import com.example.appdevelopment.ui.screens.PasswordResetScreen
import com.example.appdevelopment.ui.screens.cameraView.CameraScreen
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountViewModel
import com.example.appdevelopment.ui.screens.feedView.FeedScreen
import com.example.appdevelopment.ui.screens.feedView.FeedScreenViewModel
import com.example.appdevelopment.ui.screens.forgotPasswordView.ForgotPasswordViewModel
import com.example.appdevelopment.ui.screens.leaderboardsView.LeaderboardScreen
import com.example.appdevelopment.ui.screens.leaderboardsView.LeaderboardViewModel
import com.example.appdevelopment.ui.screens.loginView.LoginViewModel
import com.example.appdevelopment.ui.screens.profileView.ProfileScreen
import com.example.appdevelopment.ui.screens.profileView.ProfileViewModel

@ExperimentalMaterial3Api
@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: LoginViewModel,
    createAccViewModel: CreateAccountViewModel,
    forgotPasswordViewModel: ForgotPasswordViewModel,
    leaderboardViewModel: LeaderboardViewModel,
    profileViewModel: ProfileViewModel,
    feedScreenViewModel: FeedScreenViewModel,
    authLogic: AuthLogic
) {

    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Splash.route) {
                SplashScreen(navController = navController, authLogic)
            }
            composable(route = Screen.Welcome.route) {
                WelcomeScreen(navController = navController)
            }
            composable(route = Screen.CreateAcc.route) {

                CreateAccountScreen(
                    navController = navController,
                    createAccViewModel.uiState.collectAsState().value,
                    authLogic
                ) {
                    createAccViewModel.onEvent(it)
                }
            }
            composable(route = Screen.Login.route) {

                LoginScreen(
                    navController = navController,
                    viewModel.uiState.collectAsState().value,
                    authLogic
                ) {
                    viewModel.onEvent(it)
                }
            }
            composable(route = Screen.ForgotPwd.route) {

                ForgotPasswordScreen(
                    navController = navController,
                    forgotPasswordViewModel.uiState.collectAsState().value,
                    authLogic
                ) {
                    forgotPasswordViewModel.onEvent(it)
                }
            }
            composable(route = Screen.PwdReset.route) {
                PasswordResetScreen(navController = navController)
            }

            composable(route = Screen.Camera.route) {

                CameraScreen(navController = navController)
            }
            
            composable(route = Screen.Profile.route) {
                ProfileScreen(
                    navController = navController,
                    authLogic,
                    profileViewModel,
                    profileViewModel.uiState.collectAsState().value,
                ){
                    profileViewModel.onEvent(it)
                }
            }
            
            composable(route = Screen.Leaderboards.route){
                LeaderboardScreen(navController = navController, leaderboardViewModel)
            }
            
            composable(route = Screen.Feed.route){
                FeedScreen(
                    navController = navController,
                    feedScreenViewModel
                )
            }
        }
    }
}
