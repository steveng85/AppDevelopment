package com.example.appdevelopment.data.remote.repository

import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.data.domain.repository.StorageRepository
import com.example.appdevelopment.data.dto.Feed
import com.example.appdevelopment.data.utils.await
import com.google.firebase.storage.FirebaseStorage
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

    override suspend fun onGetUrl(userID: String, description: String, time: String) {
        val storageRef = firebaseStorage.getReference("images/$userID.jpeg")
        val user = fireStoreRepository.getUserInfo(userID)
        var url = mutableStateOf<String>("")

        storageRef.downloadUrl.addOnSuccessListener {
            url.value = it.toString()

        }.addOnFailureListener {
            it.printStackTrace()
        }.await()
        if(url.value != "") {
            try {
                fireStoreRepository.addFeed(
                    Feed(
                        url.value,
                        user.username,
                        description,
                        "",
                        0,
                        userID
                    ), user
                )
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }

    }
}