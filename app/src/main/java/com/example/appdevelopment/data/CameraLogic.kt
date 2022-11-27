package com.example.appdevelopment.data

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.appdevelopment.data.domain.repository.CameraRepository
import com.example.appdevelopment.data.utils.await
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import kotlinx.coroutines.selects.select
import java.io.File
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

    override suspend fun onImageCaptureAndUpload(context: Context) {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.ENGLISH)
            .format(System.currentTimeMillis()) + ".png"

        val imageFile = File(simpleDateFormat)

        val outputFileOptions = ImageCapture.OutputFileOptions
            .Builder(imageFile)
            .build()

//        fun getOutputDir(): File {
//            return File("gs://appdevelopment-3f7db.appspot.com")
//        }
//
//        val outputDir = getOutputDir()
//
//        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.ENGLISH)
//            .format(System.currentTimeMillis())
//
//        val imageFile = File(outputDir, simpleDateFormat)
//
//        val contentValues = ContentValues().apply {
//            put(MediaStore.MediaColumns.DISPLAY_NAME, simpleDateFormat)
//            put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg")
//            if (Build.VERSION.SDK_INT > 28){
//                put(MediaStore.Images.Media.RELATIVE_PATH,"Pictures/My-Camera-App-Images")
//            }
//        }
//
//        val savedUri = Uri.fromFile(imageFile)
//
//        val outputFileOptions = ImageCapture.OutputFileOptions
//            .Builder(context.contentResolver, savedUri, contentValues)
//            .build()

        cameraImageCapture.takePicture(
            outputFileOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback{

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val imageSavedUri = Uri.fromFile(imageFile)

                    uploadImageTask(imageSavedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        context,
                        "There occurred an error when taking the picture. Try again.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )
    }

    private fun uploadImageTask(uri: Uri) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference

        var file = uri
        val imageRef = storageRef.child("images/${file.lastPathSegment}")
        var uploadTask = imageRef.putFile(file)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
            } else {
                // Handle failures
                // ...
            }
        }


//            .child("images/").putFile(uri)
//                    .await().storage.downloadUrl.await()

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