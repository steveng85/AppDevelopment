package com.example.appdevelopment.ui.screens.cameraView

import android.content.Context
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

    fun onImageCaptureAndUpload(context: Context) {
        viewModelScope.launch {
            repository.onImageCaptureAndUpload(context)
        }
    }
}