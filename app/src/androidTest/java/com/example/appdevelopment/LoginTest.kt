package com.example.appdevelopment

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.ui.screens.LoginScreen
import org.junit.Rule
import org.junit.Test

class LoginTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Isolated UI test of LoginScreen(), to verify whether
    // a new user is created or not by being navigated to CameraScreen()
//    @Test
//    fun loginScreenTest() {
//        composeTestRule.setContent {
//            LoginScreen(navController = rememberNavController(), uiState = , authLogic = AuthLogic(), onEvent = )
//        }
//    }
}