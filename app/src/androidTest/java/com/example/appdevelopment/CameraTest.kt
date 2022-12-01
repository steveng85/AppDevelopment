package com.example.appdevelopment

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.ui.screens.cameraView.CameraScreen
import com.example.appdevelopment.ui.screens.cameraView.CameraViewModel
import com.example.appdevelopment.ui.screens.cameraView.CameraX
import org.junit.Rule
import org.junit.Test

class CameraTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cameraXTest() {
        composeTestRule.setContent { CameraX() }

        composeTestRule.onNodeWithContentDescription(CameraScreen())
    }

//    @Test
//    fun cameraXTest() {
//        composeTestRule.setContent { CameraX() }
//        composeTestRule.onNodeWithContentDescription("Take picture")
//    }
}