package com.example.appdevelopment.ui.screens.cameraView

import com.example.appdevelopment.ui.screens.createAccountView.CreateAccountEvent

sealed class CameraEvent {
    data class OnDescriptionChanged(val description: String): CameraEvent()
    object OnImageUpload: CameraEvent()
    object OnChangeDisplayToCameraX: CameraEvent()
}