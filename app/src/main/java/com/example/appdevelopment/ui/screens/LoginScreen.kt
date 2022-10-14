package com.example.appdevelopment.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.createSavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.navigation.Screen




@Composable
fun LoginScreen(
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
                    text = "Login to your account",
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.displayMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
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

                EmailBox()
                PasswordBox()
                LoginButton()
                forgotPassword()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}

@Composable
fun EmailBox(){
    val emailState = remember { mutableStateOf("h") }

    OutlinedTextField(
        value = emailState.value,
        onValueChange = {emailState.value },
        label = { Text("E-mail")},
        placeholder = { Text("E-mail")},
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = Color.Black
        )
    )
}


@Composable
fun PasswordBox(){
    val passwordState = remember { mutableStateOf("h") }

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {passwordState.value },
        label = { Text("Password")},
        placeholder = { Text("Password")},
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF007FFF),
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color(0xFF007FFF),
            unfocusedLabelColor = Color.Black
        )
    )
}

@Composable
fun LoginButton(){
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.dp, RoundedCornerShape(16.dp), true),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 8.dp,
            disabledElevation = 4.dp,
            hoveredElevation = 4.dp,
            focusedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF007FFF)
        )
    ) {
        Text(text = "Login")
    }
}

@Composable
fun forgotPassword(){
    val annotatedString = buildAnnotatedString {

        withStyle(
            style = SpanStyle(
                color = Color(0xFF007FFF)
            )
        ){
            append("forgot password?")
        }
    }
    ClickableText(
        text = annotatedString,
        onClick = { /*TODO*/ },
    )

}



