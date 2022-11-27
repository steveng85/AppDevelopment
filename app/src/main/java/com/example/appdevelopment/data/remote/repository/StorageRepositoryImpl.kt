package com.example.appdevelopment.data.remote.repository

import android.net.Uri
import com.example.appdevelopment.data.domain.repository.StorageRepository
import com.example.appdevelopment.data.utils.await
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URI
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage
): StorageRepository {

    override fun uploadImageTask(uri: Uri?) {

        /**
         * Code snippets from Firebase docs:
         * https://firebase.google.com/docs/storage/android/upload-files
         **/

        val storageRef = firebaseStorage.reference.child("images/image")

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

    override suspend fun getUrl(): String {
       val storageRef = firebaseStorage.reference.child("images")


        return try {
            storageRef.downloadUrl.toString()
        }catch (e: Exception){
            e.printStackTrace()
            ""
        }
    }
}