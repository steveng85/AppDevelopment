package com.example.appdevelopment.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.R

@ExperimentalMaterial3Api
@Composable
fun LoginTopBar(navController: NavController, text: String, screenContent: @Composable () -> Unit) {
    Scaffold( modifier = Modifier.fillMaxHeight(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = text,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios),
                            contentDescription = "backButton",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.onPrimary)
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            screenContent()
        }
    }

}
