package com.example.appdevelopment.ui.screens.profileView

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.data.dto.User
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.components.LoginTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    authLogic: AuthLogic?,
    profileViewModel: ProfileViewModel,
    uiState: ProfileUIState,
    onEvent: (ProfileEvent) -> Unit
) {
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
            ProfileCard(navController, authLogic, profileViewModel,uiState, onEvent)
        }
    }
}

@Preview
@Composable
fun hej() {
    ProfileCard(navController = rememberNavController(), null, viewModel(), ProfileUIState(),{})
}

@Composable
fun ProfileCard(navController: NavController, authLogic: AuthLogic?, viewModel: ProfileViewModel,uiState: ProfileUIState, onEvent: (ProfileEvent) -> Unit) {
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
                .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LaunchedEffect(uiState.editState){
                    viewModel.onGet()
                }
                val user = viewModel.user.collectAsState().value
                ProfileStats(user)
                ProfileBio(user, onEvent)
                ProfileInfo(user, uiState, onEvent)
                LogoutButton(navController, authLogic)
            }
        }
    }
}

@Composable
fun ProfileStats(user: User?) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ProfileStat("15", "Photos")
        VerticalDivider()
        ProfileStat("#${user?.rank}", "Rank")
        VerticalDivider()
        ProfileStat("${user?.totalLikes}", "Likes")
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
fun ProfileBio(user: User?, onEvent: (ProfileEvent) -> Unit) {
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
                Text(text = "${user?.description}")
            }
        }
    }
}

@Composable
fun editButton(text: String, onEvent: () -> Unit){
    Button(
        onClick =  onEvent ,
        modifier = Modifier
            .height(45.dp)
            .fillMaxWidth()
            .padding(top = 10.dp),
        shape = RoundedCornerShape(40.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
        colors = androidx.compose.material.ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White)
    ) {
        Text(text = text)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileInfo(user: User?, uiState: ProfileUIState, onEvent: (ProfileEvent) -> Unit) {
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
                if(!uiState.editState){
                    Text(text = "Username: ${user?.username}")
                    Text(text = "E-mail: ${user?.email}")
                    Text(text = "Birthday: 06-11-2000")
                    Text(text = "Gender: Male")
                    editButton(text = "editInfo") { onEvent(ProfileEvent.OnEditInfo(true))}
                } else {
                    TextTest(text =  uiState.usernameText, label = "username", onEvent = {onEvent(ProfileEvent.OnUsernameChanged(it))})
                    TextTest(text = uiState.bioText, label = "bio", onEvent = {onEvent(ProfileEvent.OnBioChanged(it))})
                    TextTest(text = uiState.genderText, label = "gender", onEvent = {onEvent(ProfileEvent.OnGenderChanged(it))})
                    editButton("save") { onEvent(ProfileEvent.OnSave) }
                }
            }
        }
    }
}

@Composable
fun TextTest(text: String, label: String, onEvent: (String) -> Unit){

    OutlinedTextField(
        value = text,
        onValueChange = {onEvent(it)},
        label = { Text(text = label)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            cursorColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            focusedLabelColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = Color.LightGray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        //shape = RoundedCornerShape(ZeroCornerSize)

        )
}

@Composable
fun LogoutButton(navController: NavController, authLogic: AuthLogic?) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                authLogic?.logout()
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Login.route){ inclusive = true }
                }
                println("logged out")
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