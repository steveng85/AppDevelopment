package com.example.appdevelopment.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.appdevelopment.ui.components.DefaultButton
import com.example.appdevelopment.ui.components.DefaultFieldBox
import com.example.appdevelopment.ui.components.LoginTopBar
import com.example.appdevelopment.ui.screens.forgotPasswordView.ForgotPasswordEvent
import com.example.appdevelopment.ui.screens.forgotPasswordView.ForgotPasswordUIState

@ExperimentalMaterial3Api
@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    uiState: ForgotPasswordUIState,
    authLogic: AuthLogic?,
    onEvent: (ForgotPasswordEvent) -> Unit
) {
    val authResource = authLogic?.resetPasswordFlow?.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            LoginTopBar(navController = navController, "Forgot password")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(
                modifier = Modifier.padding(
                    top = 70.dp,
                    bottom = 20.dp,
                    start = 60.dp,
                    end = 60.dp),
                text = "Forgot your password?",
                color = Color.Black,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 1.em
            )

            Text(
                modifier = Modifier.padding(start = 100.dp, end = 100.dp),

                text = "Write your e-mail below to reset your password",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
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
                FEmailBox(uiState.emailText, onEvent = {onEvent(it)})
                ResetPasswordButton{onEvent(ForgotPasswordEvent.OnResetPassword)}
            }
            authResource?.value?.let {
                when (it) {
                    is Resource.Failure -> {
                        val context = LocalContext.current
                        Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                        println("faulire")
                    }
                    Resource.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp))
                    }
                    is Resource.Success -> {
                        LaunchedEffect(Unit) {
                            navController.navigate(Screen.PwdReset.route)
                        }
                        println("send email")
                    }
                }
            }
            }
        }
    }


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(navController = rememberNavController(), ForgotPasswordUIState(),null, {})
}

@Composable
fun FEmailBox(emailValue: String, onEvent: (ForgotPasswordEvent) -> Unit){
    DefaultFieldBox(
        currentValue = emailValue,
        onEvent = {onEvent(ForgotPasswordEvent.OnEmailChanged(it))},
        focusedColor = MaterialTheme.colorScheme.primary,
        unfocusedColor = Color.LightGray,
        label = "Email",
        password = false
    )
}

@Composable
fun ResetPasswordButton(route: ()->Unit){
    DefaultButton(
        onClick = route,
        text = "Reset password",
        contentColor = Color.White,
        containerColor = MaterialTheme.colorScheme.primary
    )
}
