//package com.example.appdevelopment.ui.screens.cameraView
//
//import android.content.Context
//import androidx.camera.view.PreviewView
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.ui.test.*
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.lifecycle.LifecycleOwner
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.appdevelopment.MainActivity
//import com.example.appdevelopment.data.di.AppModule
//import com.example.appdevelopment.data.domain.repository.CameraRepository
//import com.example.appdevelopment.navigation.Screen
//import com.example.appdevelopment.ui.screens.cameraView.CameraEvent
//import com.example.appdevelopment.ui.screens.cameraView.CameraScreen
//import com.example.appdevelopment.ui.screens.cameraView.CameraUIState
//import com.example.appdevelopment.ui.screens.cameraView.CameraViewModel
//import com.example.appdevelopment.ui.theme.AppDevelopmentTheme
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
/**
 *
 * We haven't been able to pass the tests, and all HiltTest dependencies are
 * removed from the gradle.file
 *
 * Jetpack Compose UI testing, inspired by:
 *
 * https://developer.android.com/training/dependency-injection/hilt-testing
 * https://www.youtube.com/watch?v=nDCCwyS0_MQ&ab_channel=PhilippLackner *
 *
 * */
//
//@ExperimentalMaterial3Api
//@HiltAndroidTest
//@UninstallModules(AppModule::class)
//class CameraScreenTest {
//
//    private lateinit var cameraEvent: CameraEvent
//
//    @get:Rule(order = 0)
//    val hiltRule = HiltAndroidRule(this)
//
//    @get:Rule(order = 1)
//    val composeRule = createAndroidComposeRule<MainActivity>()
//
//    @Before
//    fun setUp() {
//        hiltRule.inject()
//        composeRule.setContent {
//
//            val navController = rememberNavController()
//
//            AppDevelopmentTheme {
//                NavHost(
//                    navController = navController,
//                    startDestination = Screen.Camera.route,
//                ) {
//                    composable(route = Screen.Camera.route) {
//                        CameraScreen(
//                            navController = navController,
//                            uiState = CameraUIState(),
//                            onEvent = { cameraEvent }
//                        )
//                    }
//                }
//            }
//        }
//    }
//
//    @Test
//    fun takeImageAndUpload() {
//
//        // Take a picture
//        composeRule.onNodeWithContentDescription("Take picture").assertExists()
//        composeRule.onNodeWithContentDescription("Take picture").performClick()
//
//        // Give the captured image a description, and upload it
//        composeRule.onNodeWithTag("image description").assertExists()
//        composeRule.onNodeWithTag("image description").performClick()
//        composeRule.onNodeWithTag("image description").performTextInput("I just took this photo!")
//
//        composeRule.onNodeWithTag("Upload").assertExists()
//        composeRule.onNodeWithTag("Upload").performClick()
//
//        composeRule.onNodeWithTag("Go to feed").assertExists()
//        composeRule.onNodeWithTag("Go to feed").performClick()
//
//        // Reassure, that there appear a post
//        composeRule.onNodeWithTag("Go to feed").assert(hasText("I just took this photo!"))
//    }
//}