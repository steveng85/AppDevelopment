package com.example.appdevelopment

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.data.domain.repository.AuthRepository
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountViewModel
import com.example.appdevelopment.ui.screens.loginView.LoginViewModel
import com.example.appdevelopment.ui.theme.AppDevelopmentTheme
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var authLogic: AuthLogic
    private val loginViewModel: LoginViewModel by viewModels()
    private val createAccViewModel: CreateAccountViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            App(loginViewModel, createAccViewModel, authLogic)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun App(loginViewModel: LoginViewModel, createAccViewModel: CreateAccountViewModel, authLogic: AuthLogic) {
    AppDevelopmentTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController, loginViewModel, createAccViewModel, authLogic)
    }
}