package com.example.appdevelopment.data.remote.repository

import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.data.dataClasses.Board
import com.example.appdevelopment.data.dataClasses.Feed
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.data.utils.await
import com.google.firebase.firestore.FirebaseFirestore
import com.example.appdevelopment.data.dataClasses.User
import com.example.appdevelopment.data.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
): FireStoreRepository{

    private fun getReadableDateTime(date: Date?): String {
        return SimpleDateFormat("MMMM dd, yyyy - hh:mm a", Locale.getDefault()).format(date)
    }

    override suspend fun addUser(user: User) {
        withContext(Dispatchers.IO) {
            try {
                val newRef = firebaseFirestore.collection("users").document(user.token)
                newRef.set(user).await()

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
                        firebaseFirestore.collection("users").document(doc.id).update("rank", i).await()
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

    override suspend fun updateLikeForFeed(feed: Feed) {
        withContext(Dispatchers.IO) {

            try {
                firebaseFirestore.collection("feed").document(feed.userID).update("like", feed.likes)
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    override suspend fun addFeed(feed: Feed, user: User) {
        withContext(Dispatchers.IO) {
            try {
                val newRef = firebaseFirestore.collection("feed").document(user.token)
                newRef.set(feed).await()
                //firebaseFirestore.collection("feed").add(feed).await()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    override suspend fun updateStatsInUser(user: User, feed: Feed) {
        withContext(Dispatchers.IO) {

            try {
                firebaseFirestore.collection("users").document(user.token).update(
                    "totalLikes", user.totalLikes,
                    "photos", user.photos )
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    override suspend fun getFeed(user: User): Resource<Feed> {

            return try {
                val result = firebaseFirestore.collection("feed").document(user.token).get().await()
                Resource.Success(
                    Feed(
                        result.data?.get("image").toString().toInt(),
                        result.data?.get("username").toString(),
                        result.data?.get("description").toString(),
                        result.data?.get("timestamp").let { getReadableDateTime(null) },
                        result.data?.get("likes").toString().toInt(),
                        result.data?.get("userID").toString()
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Failure(e)
            }
    }

    override suspend fun clearFeedCollection() {

        withContext(Dispatchers.IO) {
            try {
                firebaseFirestore.collection("feed").get().await().map { doc ->
                   firebaseFirestore.collection("feed").document(doc.id).delete()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun getUserInfo(userID: String): User {

            return try {
                var result = firebaseFirestore.collection("users").document(userID).get().await()
                User(
                    result.data?.get("token").toString(),
                    result.data?.get("username").toString(),
                    result.data?.get("email").toString(),
                    result.data?.get("points").toString().toInt(),
                    result.data?.get("rank").toString().toInt(),
                    result.data?.get("totalLikes").toString().toInt(),
                    result.data?.get("description").toString(),
                    result.data?.get("gender").toString()
                )
            } catch(e: Exception){
                e.printStackTrace()
                User("","","", 0, 0, 0, "", "")
            }
    }

    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO){
            try {
                firebaseFirestore.collection("users").document(user.token).update(
                    "description", user.description,
                    "username", user.username
                )
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
