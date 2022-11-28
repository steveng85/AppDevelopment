package com.example.appdevelopment.data.remote.repository

import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.data.domain.repository.StorageRepository
import com.example.appdevelopment.data.dto.Feed
import com.example.appdevelopment.data.utils.await
import com.example.appdevelopment.ui.screens.cameraView.imageUri
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.storage.FirebaseStorage
import com.google.rpc.Help.Link
import kotlinx.coroutines.delay
import java.io.File
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage,
    private val authLogic: AuthLogic,
    private val fireStoreRepository: FireStoreRepository
): StorageRepository {

    override suspend fun onImageUpload(uri: Uri?) {
        val userID = authLogic.getCurrentUserId()
        /**
         * Code snippets from Firebase docs:
         * https://firebase.google.com/docs/storage/android/upload-files
         **/

        val storageRef = firebaseStorage.reference.child("images/$userID.jpeg")
        //withContext(Dispatchers.IO){

        var file = uri
        var uploadTask = file?.let { storageRef.putFile(it) }
        if (uploadTask != null){
            uploadTask.continueWithTask{ task ->
                if (!task.isSuccessful){
                    task.exception?.let {
                        throw it
                    }
                }
                storageRef.downloadUrl
            }
        }
        //}

    }

    override suspend fun onGetUrl(userID: String) {
       val storageRef = firebaseStorage.getReference("images/$userID.jpeg")
        val user = fireStoreRepository.getUserInfo(userID)
        var url = mutableStateOf<String>("")
        println("testt")
        println("bucket: " + storageRef.toString())
        println("name: " + storageRef.name)
        println("storage: " + storageRef.storage.toString())

        storageRef.downloadUrl.addOnSuccessListener {
            url.value = it.toString()
            println(it.toString())

        }.addOnFailureListener {

        }.await()

        println("addURL: " + url.value)
         try  {
             fireStoreRepository.addFeed(Feed(
                 url.value,
                 user.username,
                 "",
                 "",
                 0,
                 userID
             ),user)
        } catch (e: Exception){
            e.printStackTrace()

        }

    }
}