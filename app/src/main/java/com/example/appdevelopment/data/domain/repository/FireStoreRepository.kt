package com.example.appdevelopment.data.domain.repository

import com.google.firebase.firestore.auth.User

interface FireStoreRepository {

suspend fun saveUser(user: com.example.appdevelopment.data.dataClasses.User)

suspend fun getLeaderboards(): List<User>
}