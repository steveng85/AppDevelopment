package com.example.appdevelopment.data.remote.repository

import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.data.dataClasses.Board
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.data.utils.await
import com.google.firebase.firestore.FirebaseFirestore
import com.example.appdevelopment.data.dataClasses.User
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class FirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
): FireStoreRepository{

    override suspend fun saveUser(user: User) {
        try {
            withContext(Dispatchers.IO) {
                firebaseFirestore.collection("users").add(user).await()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getLeaderboardList(): List<Board> =

        withContext(Dispatchers.IO) {
            var i = 1
            return@withContext try {
                firebaseFirestore.collection("users").orderBy("points", Query.Direction.DESCENDING).get().await()
                    .map { doc ->
                        Board(i++,
                            doc.data["username"].toString(), doc.data["points"].toString().toInt())
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
