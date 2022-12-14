package com.example.appdevelopment.data

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.appdevelopment.data.domain.repository.CameraRepository
import com.example.appdevelopment.ui.screens.cameraView.CameraViewModel
import com.example.appdevelopment.ui.screens.cameraView.imageUri
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CameraLogic @Inject constructor(
    private val cameraProvider: ProcessCameraProvider,
    private val cameraSelector: CameraSelector,
    private val cameraPreview: Preview,
    private val cameraImageAnalysis: ImageAnalysis,
    private val cameraImageCapture: ImageCapture
): CameraRepository {

    override suspend fun onImageCapture(context: Context, viewModel: CameraViewModel): String {

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.ENGLISH)
            .format(System.currentTimeMillis()) + ".jpeg"

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, simpleDateFormat)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
        }

        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ).build()

        fun displayImage(uri: Uri?) {
            //displayCameraX.value = false

            viewModel.onChangeDisplayToImage()
            if (uri != null) {
                imageUri = uri
            }
            //displayImageTaken.value = true
        }

        cameraImageCapture.takePicture(
            outputFileOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val imageUri = outputFileResults.savedUri
                    displayImage(imageUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    println("Error: $exception")
                }
            }
        )
        return simpleDateFormat
    }


    override suspend fun onImagePreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {

        cameraPreview.setSurfaceProvider(previewView.surfaceProvider)

        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            cameraPreview,
            cameraImageCapture,
            cameraImageAnalysis
        )
    }
}