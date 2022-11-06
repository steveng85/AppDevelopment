package com.example.appdevelopment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountViewModel
import com.example.appdevelopment.ui.screens.loginView.LoginViewModel
import com.example.appdevelopment.ui.theme.AppDevelopmentTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val createAccViewModel: CreateAccountViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(loginViewModel, createAccViewModel)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun App(loginViewModel: LoginViewModel, createAccViewModel: CreateAccountViewModel) {
    AppDevelopmentTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController, loginViewModel, createAccViewModel)
    }
}