package com.example.appdevelopment

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, authLogic: AuthLogic?) {

    val animateLogo = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        animateLogo.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = { OvershootInterpolator(5f).getInterpolation(it) }
            ),
            initialVelocity = 5f
        )
        delay(1000)
        if(authLogic?.loginFlow?.value is Resource.Success) {
            navController.navigate(Screen.Camera.route)
        } else {
            navController.navigate(Screen.Welcome.route)
        }
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(top = 150.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .scale(animateLogo.value)
                .size(100.dp)
                .padding(top = 20.dp),
            painter = painterResource(id = R.drawable.hunt_logo_white),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController(), null)
}

