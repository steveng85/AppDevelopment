package com.example.appdevelopment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.ui.screens.cameraView.CameraViewModel
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountViewModel
import com.example.appdevelopment.ui.screens.feedView.FeedScreenViewModel
import com.example.appdevelopment.ui.screens.forgotPasswordView.ForgotPasswordViewModel
import com.example.appdevelopment.ui.screens.leaderboardsView.LeaderboardViewModel
import com.example.appdevelopment.ui.screens.loginView.LoginViewModel
import com.example.appdevelopment.ui.screens.profileView.ProfileViewModel
import com.example.appdevelopment.ui.theme.AppDevelopmentTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var authLogic: AuthLogic
    private val loginViewModel: LoginViewModel by viewModels()
    private val createAccViewModel: CreateAccountViewModel by viewModels()
    private val forgotPasswordViewModel: ForgotPasswordViewModel by viewModels()
    private val leaderboardViewModel: LeaderboardViewModel by viewModels()
    private val feedScreenViewModel: FeedScreenViewModel by viewModels()
    private val cameraViewModel: CameraViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(loginViewModel, createAccViewModel, forgotPasswordViewModel, leaderboardViewModel, feedScreenViewModel, cameraViewModel, authLogic)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun App(
    loginViewModel: LoginViewModel,
    createAccViewModel: CreateAccountViewModel,
    forgotPasswordViewModel: ForgotPasswordViewModel,
    leaderboardViewModel: LeaderboardViewModel,
    feedScreenViewModel: FeedScreenViewModel,
    cameraViewModel: CameraViewModel,
    authLogic: AuthLogic
) {
    AppDevelopmentTheme {
        val navController = rememberNavController()
        val profileViewModel: ProfileViewModel = hiltViewModel()
        NavGraph(navController = navController, loginViewModel, createAccViewModel, forgotPasswordViewModel, leaderboardViewModel, profileViewModel, feedScreenViewModel, cameraViewModel, authLogic)
    }
}