package com.example.appdevelopment.data.domain.repository

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.appdevelopment.ui.screens.cameraView.CameraViewModel

interface CameraRepository {

    // Using the CameraX ImageCapture usecase
    suspend fun onImageCapture(context: Context, viewModel: CameraViewModel)

    // Using the CameraX ImagePreview usecase.
    suspend fun onImagePreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner)
}