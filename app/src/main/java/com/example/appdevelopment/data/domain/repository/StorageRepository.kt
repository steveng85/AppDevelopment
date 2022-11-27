package com.example.appdevelopment.data.domain.repository

import android.net.Uri
import java.net.URI

interface StorageRepository {
    fun uploadImageTask(uri: Uri?)

    suspend fun getUrl(): String
}