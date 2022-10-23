package com.example.appdevelopment.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountEvent
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountUIState

@Composable
fun CreateAccountScreen(
    navController: NavController,
    uiState: CreateAccountUIState,
    onEvent: (CreateAccountEvent) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,


            ) {
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                text = "Welcome to the Hunt",
                color = Color.Black,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 1.em
            )

            Text(
                modifier = Modifier.padding(start = 100.dp, end = 100.dp),
                text = "Create an acoount, and let's get started!",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)

            Column(
                modifier = Modifier
                    .padding(
                        start = 75.dp,
                        end = 75.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                UserName(uiState.usernameText, onEvent = {onEvent(it)})
                CEmailBox(uiState.emailText, onEvent = {onEvent(it)})
                CPasswordBox(uiState.passwordText, onEvent = {onEvent(it)})
                ConfirmPasswordBox(uiState.confirmPasswordText, onEvent = {onEvent(it)})
                LoginButton { navController.navigate(Screen.Camera.route) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateAccountScreenPreview() {
    CreateAccountScreen(navController = rememberNavController(), CreateAccountUIState(), {})
}
@Composable
fun UserName(usernameValue: String, onEvent: (CreateAccountEvent) -> Unit){
    DefaultFieldBox(
        currentValue = usernameValue,
        onEvent = {onEvent(CreateAccountEvent.OnUsernameChanged(it))},
        focusedColor = Color(0xFF007FFF),
        unfocusedColor = Color.LightGray,
        label = "Username",
        password = false)
}

@Composable
fun CEmailBox(emailValue: String, onEvent: (CreateAccountEvent) -> Unit){
    DefaultFieldBox(
        currentValue = emailValue,
        onEvent = {onEvent(CreateAccountEvent.OnEmailChanged(it))},
        focusedColor = Color(0xFF007FFF),
        unfocusedColor = Color.LightGray,
        label = "Username",
        password = false)
}

@Composable
fun CPasswordBox(passwordValue: String, onEvent: (CreateAccountEvent) -> Unit){
    DefaultFieldBox(
        currentValue = passwordValue,
        onEvent = {onEvent(CreateAccountEvent.OnPasswordChanged(it))},
        focusedColor = Color(0xFF007FFF),
        unfocusedColor = Color.LightGray,
        label = "Confirm Password",
        password = true
    )
}

@Composable
fun ConfirmPasswordBox(confirmPasswordValue: String, onEvent: (CreateAccountEvent) -> Unit){
    DefaultFieldBox(
        currentValue = confirmPasswordValue,
        onEvent = {onEvent(CreateAccountEvent.OnConfirmPasswordChanged(it))},
        focusedColor = Color(0xFF007FFF),
        unfocusedColor = Color.LightGray,
        label = "Confirm Password",
        password = true
    )
}