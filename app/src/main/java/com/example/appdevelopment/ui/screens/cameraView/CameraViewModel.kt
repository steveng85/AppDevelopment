package com.example.appdevelopment.ui.screens.cameraView

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.data.domain.repository.CameraRepository
import com.example.appdevelopment.data.domain.repository.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val repository: CameraRepository,
    private val storageRepository: StorageRepository,
    private val authLogic: AuthLogic
): ViewModel() {
    private val userID = authLogic.getCurrentUserId()

    private val _uiState = MutableStateFlow(CameraUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: CameraEvent){
        when(event){
            is CameraEvent.OnDescriptionChanged -> onDescriptionChanged(event.description)
            is CameraEvent.OnImageUpload -> onImageUpload(imageUri, _uiState.value.description)
            is CameraEvent.OnChangeDisplayToCameraX -> onChangeDisplayToCameraX()
        }
    }

    fun onDescriptionChanged(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }


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

    fun onImageUpload(uri: Uri?, description: String) {
        viewModelScope.launch {
            storageRepository.onImageUpload(uri)

            onChangeDisplayToCameraX()
            if (userID != null) {
                storageRepository.onGetUrl(userID, description)
            }
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