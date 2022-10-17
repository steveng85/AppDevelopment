package com.example.appdevelopment.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.components.DefaultButton
import com.example.appdevelopment.ui.components.LoginButton

@Composable
fun ForgotPasswordScreen(
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.Welcome.route)
                },

                text = "Forgot your password?",

                color = Color.Black,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
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
                EmailBox()
                ResetPasswordButton()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(navController = rememberNavController())
}

@Composable
fun ResetPasswordButton(){
    DefaultButton(
        onClick = "",
        text = "Reset password",
        contentColor = Color.White,
        containerColor = Color(0xFF007FFF)
    )
}
