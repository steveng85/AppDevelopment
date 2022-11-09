package com.example.appdevelopment.data.remote.repository

import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.data.utils.await
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class FirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,

): FireStoreRepository{

    override suspend fun saveUser(user: com.example.appdevelopment.data.dataClasses.User) {
        withContext(Dispatchers.IO) {
            firebaseFirestore.collection("users").add(user).await()
        }
    }

    override suspend fun getLeaderboards(): List<User> {
        TODO("Not yet implemented")
    }


}