package com.example.appdevelopment.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.navigation.Screen
import com.google.android.material.textfield.TextInputLayout



@Composable
fun LoginScreen(
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(
                    modifier = Modifier.clickable {
                        navController.navigate(route = Screen.Welcome.route)
                    },
                    text = "LoginScreen",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
                    fontWeight = FontWeight.Bold
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
                UserNameBox()
                EmailBox()
                PasswordBox()
                LoginButton()
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
fun UserNameBox(){
    val userNameState = remember { mutableStateOf(TextFieldValue) }
    //OutlinedTextField(value = userNameState.value, onValueChange = {userNameState.value})


}
@Composable
fun EmailBox(){
    OutlinedButton(onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(text = "Email")
    }
}

@Composable
fun PasswordBox(){
    OutlinedButton(onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(text = "Password")
    }
}

@Composable
fun LoginButton(){
    OutlinedButton(onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
        
    ) {
        Text(text = "Login")
    }
}

