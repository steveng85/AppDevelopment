package com.example.appdevelopment.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.components.DefaultFieldBox
import com.example.appdevelopment.ui.components.LoginButton
import com.example.appdevelopment.ui.components.LoginTopBar
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountEvent
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountUIState


@ExperimentalMaterial3Api
@Composable
fun CreateAccountScreen(
    navController: NavController,
    uiState: CreateAccountUIState,
    authLogic: AuthLogic?,
    onEvent: (CreateAccountEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            LoginTopBar(
                navController = navController,
                "Create account",
                { CreateAccountContent(navController, uiState, authLogic, onEvent) })
        }
    }
}

@Composable
fun CreateAccountContent(
    navController: NavController,
    uiState: CreateAccountUIState,
    authLogic: AuthLogic?,
    onEvent: (CreateAccountEvent) -> Unit
) {
    val authResource = authLogic?.signupFlow?.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = 0.dp, bottom = 20.dp, start = 60.dp, end = 60.dp),
            text = "Welcome to Hunt",
            color = MaterialTheme.colorScheme.outline,
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            lineHeight = 1.em
        )

        Text(
            modifier = Modifier.padding(start = 100.dp, end = 100.dp),
            text = "Create an account, and let's get started!",
            color = MaterialTheme.colorScheme.outline,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)

        Column(
            modifier = Modifier
                .padding(
                    top = 20.dp,
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
            LoginButton { onEvent(CreateAccountEvent.OnCreateUser) }
        }
    }

authResource?.value?.let {
    when (it) {
        is Resource.Failure -> {
            val context = LocalContext.current
            Toast.makeText(context, "Could not create user", Toast.LENGTH_LONG).show()
        }
        Resource.Loading -> {
            CircularProgressIndicator(modifier = Modifier.size(20.dp))
        }
        is Resource.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(Screen.Camera.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
    }
}
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun CreateAccountScreenPreview() {
    CreateAccountScreen(navController = rememberNavController(), CreateAccountUIState(), null, {} )
}

@Composable
fun UserName(usernameValue: String, onEvent: (CreateAccountEvent) -> Unit){
    DefaultFieldBox(
        currentValue = usernameValue,
        onEvent = {onEvent(CreateAccountEvent.OnUsernameChanged(it))},
        focusedColor = MaterialTheme.colorScheme.primary,
        unfocusedColor = Color.LightGray,
        label = "Username",
        type = "username",
        password = false)
}

@Composable
fun CEmailBox(emailValue: String, onEvent: (CreateAccountEvent) -> Unit){
    DefaultFieldBox(
        currentValue = emailValue,
        onEvent = {onEvent(CreateAccountEvent.OnEmailChanged(it))},
        focusedColor = MaterialTheme.colorScheme.primary,
        unfocusedColor = Color.LightGray,
        label = "E-mail",
        type = "email",
        password = false)
}

@Composable
fun CPasswordBox(passwordValue: String, onEvent: (CreateAccountEvent) -> Unit){
    DefaultFieldBox(
        currentValue = passwordValue,
        onEvent = {onEvent(CreateAccountEvent.OnPasswordChanged(it))},
        focusedColor = MaterialTheme.colorScheme.primary,
        unfocusedColor = Color.LightGray,
        label = "Password",
        type = "password",
        password = true
    )
}

@Composable
fun ConfirmPasswordBox(confirmPasswordValue: String, onEvent: (CreateAccountEvent) -> Unit){
    DefaultFieldBox(
        currentValue = confirmPasswordValue,
        onEvent = {onEvent(CreateAccountEvent.OnConfirmPasswordChanged(it))},
        focusedColor = MaterialTheme.colorScheme.primary,
        unfocusedColor = Color.LightGray,
        label = "Confirm Password",
        type = "password",
        password = true
    )
}