package com.example.appdevelopment.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.components.DefaultFieldBox
import com.example.appdevelopment.ui.components.LoginButton
import com.example.appdevelopment.ui.screens.loginView.LoginEvent
import com.example.appdevelopment.ui.screens.loginView.LoginUIState


@Composable
fun LoginScreen(
    navController: NavController,
    uiState: LoginUIState,
    onEvent: (LoginEvent) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,


        ) {
            Text(
                    text = "Login to your account",
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.displayMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.em
            )

            Text(
                modifier = Modifier.padding(start = 100.dp, end = 100.dp),
                text = "Welcome back, login to your already existing account",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)

            Column(
                modifier = Modifier
                    .padding(
                        start = 75.dp,
                        end = 75.dp
                    )
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                EmailBox(uiState.emailText, onEvent = {onEvent(it)})
                PasswordBox(uiState.passwordText, onEvent = {onEvent(it)})
                LoginButton { navController.navigate(Screen.Camera.route) }
                ForgotPassword { navController.navigate(Screen.ForgotPwd.route) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(), LoginUIState(), {})
}

@Composable
fun EmailBox(emailValue: String, onEvent: (LoginEvent) -> Unit){
    DefaultFieldBox(
        currentValue = emailValue,
        onEvent = {onEvent(LoginEvent.OnEmailChanged(it))},
        focusedColor = MaterialTheme.colorScheme.primary,
        unfocusedColor = Color.LightGray,
        label = "Email",
        password = false
    )
}


@Composable
fun PasswordBox(passwordValue: String, onEvent: (LoginEvent) -> Unit){
    DefaultFieldBox(
        currentValue = passwordValue,
        onEvent = {onEvent(LoginEvent.OnPasswordChanged(it))},
        focusedColor = MaterialTheme.colorScheme.primary ,
        unfocusedColor = Color.LightGray,
        label = "Password",
        password = true
    )
}


@Composable
fun ForgotPassword(route: (Int)-> Unit){
    val annotatedString = buildAnnotatedString {

        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary
            )
        ){
            append("forgot password?")
        }
    }
    ClickableText(
        text = annotatedString,
        onClick = route
    )

}



