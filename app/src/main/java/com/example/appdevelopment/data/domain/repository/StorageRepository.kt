package com.example.appdevelopment.data.domain.repository

import android.net.Uri

interface StorageRepository {

    //Upload the picture to Firebase storage
    suspend fun onImageUpload(uri: Uri?)

    suspend fun onGetUrl(userID: String, description: String, time: String)
}