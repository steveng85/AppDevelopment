package com.example.appdevelopment.data.remote.repository

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.data.domain.repository.AuthRepository
import com.example.appdevelopment.data.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    //need firebase auth instance for all the functions so it gets passed in
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun signup(
        username: String,
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(username).build())?.await()
            Resource.Success(result.user!!)
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun forgotPassword(email: String): Resource<Void> {
        return try {
            val result = firebaseAuth.sendPasswordResetEmail(email).await()
            Resource.Success(result)
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}