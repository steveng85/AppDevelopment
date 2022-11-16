package com.example.appdevelopment.data.remote.repository

import com.example.appdevelopment.data.Resource
import com.google.firebase.Timestamp
import com.example.appdevelopment.data.dataClasses.Board
import com.example.appdevelopment.data.dataClasses.Feed
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.data.utils.await
import com.google.firebase.firestore.FirebaseFirestore
import com.example.appdevelopment.data.dataClasses.User
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

class FirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
): FireStoreRepository{
    private fun getReadableDateTime(date: Date): String {
        return SimpleDateFormat("MMMM dd, yyyy - hh:mm a", Locale.getDefault()).format(date)
    }

    override suspend fun saveUser(user: User) {
        withContext(Dispatchers.IO) {
            try {

                firebaseFirestore.collection("users").add(user).await()

            } catch (e: Exception) {
                e.printStackTrace()
            }
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

    override suspend fun getFeedList(): List<Feed> =

        withContext(Dispatchers.IO) {
            return@withContext try {
                firebaseFirestore.collection("feed").orderBy("timestamp").get().await().map { doc ->
                    Feed(
                        doc.data["image"].toString().toInt(),
                        doc.data["username"].toString(),
                        doc.data["description"].toString(),
                        doc.getDate("timestamp")?.let { getReadableDateTime(it) },
                        doc.data["like"].toString().toInt(),
                        doc.id
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }

    override suspend fun updateLike(feed: Feed) {
        withContext(Dispatchers.IO) {

            try {
                firebaseFirestore.collection("feed").document(feed.userID).update("like", feed.likes)
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    override suspend fun addFeed(feed: Feed) {
        withContext(Dispatchers.IO) {
            try {

                firebaseFirestore.collection("feed").add(feed).await()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
