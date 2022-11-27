package com.example.appdevelopment.ui.screens.cameraView

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevelopment.data.domain.repository.CameraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val repository: CameraRepository
): ViewModel() {

    fun onImagePreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            repository.onImagePreview(previewView, lifecycleOwner)
        }
    }

    fun onImageCapture(context: Context, viewModel: CameraViewModel) {
        viewModelScope.launch {
            repository.onImageCapture(context, viewModel)
        }
    }

    fun onImageUpload(uri: Uri?) {
        viewModelScope.launch {
            repository.onImageUpload(uri)

            onChangeDisplayToCameraX()
        }
    }

    fun onChangeDisplayToImage() {
        viewModelScope.launch {
            displayCameraX.value = false
            displayImageTaken.value = true
        }
    }

    fun onChangeDisplayToCameraX() {
        viewModelScope.launch {
            displayImageTaken.value = false
            displayCameraX.value = true
        }
    }
}