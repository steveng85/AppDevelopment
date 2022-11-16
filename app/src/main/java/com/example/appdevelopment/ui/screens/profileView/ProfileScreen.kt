package com.example.appdevelopment.ui.screens.profileView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.R
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.data.domain.repository.AuthRepository
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.components.DefaultButton
import com.example.appdevelopment.ui.components.LoginButton
import com.example.appdevelopment.ui.components.LoginTopBar
import com.example.appdevelopment.ui.screens.CEmailBox
import com.example.appdevelopment.ui.screens.CPasswordBox
import com.example.appdevelopment.ui.screens.ConfirmPasswordBox
import com.example.appdevelopment.ui.screens.UserName
import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountEvent
import com.example.appdevelopment.ui.screens.loginView.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, authLogic: AuthLogic?) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            LoginTopBar(navController = navController, "Profile")
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileCard(authLogic)
        }
    }
}

@Preview
@Composable
fun hej() {
    ProfileCard(null)
}

@Composable
fun ProfileCard(authLogic: AuthLogic?) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(650.dp),
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        backgroundColor = Color.White,
        border = BorderStroke(width = 2.dp, MaterialTheme.colorScheme.primary),
        elevation = 5.dp
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 30.dp, bottom = 0.dp, end = 15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //profile.username
            Text(
                text = "Pelle",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileStats()
                ProfileBio()
                ProfileInfo()
                LogoutButton(navController = rememberNavController(), authLogic)
            }
        }
    }
}

@Composable
fun ProfileStats() {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ProfileStat("15", "Photos")
        VerticalDivider()
        ProfileStat("#2", "Rank")
        VerticalDivider()
        ProfileStat("145", "Likes")
    }
}

@Composable
fun VerticalDivider() {
    Divider(
        modifier = Modifier
            .width(1.dp)
            .height(25.dp),
        color = Color.LightGray
    )
}

@Composable
fun ProfileStat(
    statNumber: String,
    statText: String
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = statNumber,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = statText,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Light,
            fontSize = 15.sp
        )
    }
}

@Composable
fun ProfileBio() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier
            .width(300.dp)
            .wrapContentHeight()
            .padding(top = 35.dp),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(40.dp),
            elevation = 5.dp,
            border = BorderStroke(1.dp, androidx.compose.material3.MaterialTheme.colorScheme.primary)

        ) {
            Column(modifier = Modifier.padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 10.dp)) {
                Text(
                    text = "Bio",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(text = "Hi, i'm Pelle! i am the besttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt")
            }
        }
    }
}

@Composable
fun ProfileInfo() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier
            .width(300.dp)
            .wrapContentHeight()
            .padding(top = 35.dp),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(40.dp),
            elevation = 5.dp,
            border = BorderStroke(1.dp, androidx.compose.material3.MaterialTheme.colorScheme.primary)

        ) {
            Column(modifier = Modifier.padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 10.dp)) {
                Text(
                    text = "Info",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(text = "Username: Pelle")
                Text(text = "E-mail: Pelle@gmail.com")
                Text(text = "Birthday: 06-11-2000")
                Text(text = "Gender: Male")
            }
        }
    }
}

// TODO: mangler viewmodel til at logge ud
@Composable
fun LogoutButton(navController: NavController, authLogic: AuthLogic?) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                authLogic?.logout()
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Login.route){ inclusive = true }
                }
                      },
            modifier = Modifier
                .height(45.dp)
                .width(120.dp),
            //.padding(top = 30.dp),
            shape = RoundedCornerShape(40.dp),
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
            colors = androidx.compose.material.ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White)
        ) {
            Text(text = "Log out")
        }
    }
}