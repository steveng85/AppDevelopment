package com.example.appdevelopment.data.domain.repository

import com.example.appdevelopment.data.Resource
import com.google.firebase.auth.FirebaseUser


interface AuthRepository {
    //current user if there isn't any it returns null
    val currentUser: FirebaseUser?
    //functions for the Auth Repository
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(username: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}