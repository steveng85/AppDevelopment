package com.example.appdevelopment.data.domain.repository

import android.net.Uri

interface StorageRepository {
    suspend fun uploadPictureToStorage(): Uri
}