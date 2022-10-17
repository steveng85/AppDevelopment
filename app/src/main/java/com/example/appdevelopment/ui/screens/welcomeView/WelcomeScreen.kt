package com.example.appdevelopment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.navigation.Screen

@ExperimentalMaterial3Api
@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Card() {

            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                WelcomeAnimation(navController = navController)
                GetStartedButton(navController = navController)
                LoginButton(navController = navController)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}

@ExperimentalMaterial3Api
@Composable
fun WelcomeAnimation(navController: NavController) {
    Box(
        modifier = Modifier.padding(bottom = 500.dp),

    ) {
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.Splash.route)
//                {
//                    popUpTo(Screen.Splash.route) {
//                        inclusive = true
//                    }
//                }
            },
            text = "Welcome",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.displayLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
    }

}

@ExperimentalMaterial3Api
@Composable
fun GetStartedButton(navController: NavController) {
        Button(
            onClick = { navController.navigate(route = Screen.CreateAcc.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Get Started")
        }
}

@ExperimentalMaterial3Api
@Composable
fun LoginButton(navController: NavController) {
         Button(
            onClick = { navController.navigate(route = Screen.Login.route) },
            modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(
//                start = 100.dp,
//                top = 12.dp,
//                end = 100.dp,
//                bottom = 12.dp
//            ),
             shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Login")
        }
}
