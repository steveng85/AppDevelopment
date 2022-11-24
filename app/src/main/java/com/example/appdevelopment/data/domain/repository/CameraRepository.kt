package com.example.appdevelopment.data.domain.repository

import android.content.Context
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraRepository {

    // Using the CameraX ImageCapture usecase, and upload the picture to Firestore.
    suspend fun onImageCaptureAndUpload(context: Context)

    // Using the CameraX ImagePreview usecase.
    suspend fun onImagePreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner)
}