package com.example.appdevelopment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.navigation.Screen
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@ExperimentalMaterial3Api
@Composable
fun WelcomeScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onPrimary)
            .padding(
                PaddingValues(
                    start = 75.dp,
                    top = 12.dp,
                    end = 75.dp,
                    bottom = 12.dp
                ),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeTitle()
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .padding(top = 20.dp),
                painter = painterResource(
                    id = R.drawable.hunt_logo_white
                ),
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeText()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 560.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GetStartedButton(navController = navController)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 620.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginButton(navController = navController)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}

@ExperimentalMaterial3Api
@Composable
fun WelcomeTitle() {
        Text(
            text = "Hunt",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
        )
    }

@Composable
fun WelcomeText() {
    Column(
        modifier = Modifier.padding(top = 300.dp),
        horizontalAlignment = Alignment.CenterHorizontally

        ) {
        Text(
            text = "You have the world at your feet. Go out and experience it.",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
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
            Text(
                text = "Get Started",
                color = MaterialTheme.colorScheme.primary
            )
        }
}

@ExperimentalMaterial3Api
@Composable
fun LoginButton(navController: NavController) {
         Button(
            onClick = { navController.navigate(route = Screen.Login.route) },
            modifier = Modifier.fillMaxWidth(),
             shape = RoundedCornerShape(16.dp),
             colors = ButtonDefaults.buttonColors(
                 contentColor = MaterialTheme.colorScheme.primary,
                 containerColor = MaterialTheme.colorScheme.onPrimary
             ),
             border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Login")
        }
}