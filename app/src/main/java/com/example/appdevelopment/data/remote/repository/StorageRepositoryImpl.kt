package com.example.appdevelopment.data.remote.repository

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import com.example.appdevelopment.data.AuthLogic
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.data.domain.repository.StorageRepository
import com.example.appdevelopment.data.dto.Feed
import com.example.appdevelopment.data.utils.await
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage,
    private val authLogic: AuthLogic,
    private val fireStoreRepository: FireStoreRepository
): StorageRepository {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        .format(System.currentTimeMillis())

    override suspend fun onImageUpload(uri: Uri?) {
        val userID = authLogic.getCurrentUserId()
        /**
         * Code snippets from Firebase docs:
         * https://firebase.google.com/docs/storage/android/upload-files
         **/

        val storageRef = firebaseStorage.reference.child("images/$userID.jpeg")

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
            }.await()
        }
    }

    override suspend fun onGetUrl(description: String) {
        val userID = authLogic.getCurrentUserId()
        val user = userID?.let { fireStoreRepository.getUserInfo(it) }
        val storageRef = firebaseStorage.getReference("images/$userID.jpeg")
        var url = mutableStateOf<String>("")
        println(userID)
        storageRef.downloadUrl.addOnSuccessListener {
            url.value = it.toString()

        }.addOnFailureListener {
            it.printStackTrace()
        }.await()

        if(url.value != "") {
            try {
                if (user != null) {
                    userID.let {
                        Feed(
                            url.value,
                            user.username,
                            description,
                            simpleDateFormat,
                            0,
                            it
                        )
                    }.let {
                        fireStoreRepository.addFeed(
                            it, user
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }

    }
}