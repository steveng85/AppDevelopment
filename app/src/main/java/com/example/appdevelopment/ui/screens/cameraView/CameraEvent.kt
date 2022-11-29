package com.example.appdevelopment.ui.screens.cameraView

sealed class CameraEvent {
    data class OnDescriptionChanged(val description: String): CameraEvent()
    object OnImageUpload: CameraEvent()
    object OnChangeDisplayToCameraX: CameraEvent()
}