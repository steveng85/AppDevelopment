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

@Composable
fun CreateAccountScreen(
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,


            ) {
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.Welcome.route)
                },
                text = "Welcome to the Hunt",
                color = Color.Black,
                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
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
                    )
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                UserName()
                EmailBox()
                PasswordBox()
                ConfirmPassword()
                LoginButton()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateAccountScreenPreview() {
    CreateAccountScreen(navController = rememberNavController())
}
@Composable
fun UserName(){
    DefaultFieldBox(
        focusedColor = Color(0xFF007FFF),
        unfocusedColor = Color.LightGray,
        label = "Username",
        password = false)
}

@Composable
fun ConfirmPassword(){
    DefaultFieldBox(
        focusedColor = Color(0xFF007FFF),
        unfocusedColor = Color.LightGray,
        label = "Confirm Password",
        password = true
    )
}