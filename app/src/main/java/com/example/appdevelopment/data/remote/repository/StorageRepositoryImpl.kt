package com.example.appdevelopment.data.remote.repository

import android.net.Uri
import com.example.appdevelopment.data.domain.repository.StorageRepository
import com.example.appdevelopment.data.utils.await
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage
): StorageRepository {

    override suspend fun uploadPictureToStorage(): Uri {
        try {
            firebaseStorage.reference.child(TODO()).putFile(TODO())
                .await().storage.downloadUrl.await()
        }catch (e: Exception){

        }
    }
}