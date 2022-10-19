package com.example.appdevelopment.navigation

sealed class Screen(val route: String) {
    object Splash: Screen(route = "splash_screen")
    object Welcome: Screen(route = "welcome_screen")
    object CreateAcc: Screen(route = "create_account_screen")
    object Login: Screen(route = "login_screen")
    object ForgotPwd: Screen(route = "forgot_password_screen")
    object PwdReset: Screen(route = "password_reset_screen")
    object Camera: Screen(route = "camera_screen")
}
