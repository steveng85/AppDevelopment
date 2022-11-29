package com.example.appdevelopment.data.remote.repository

import android.annotation.SuppressLint
import com.example.appdevelopment.data.dto.Board
import com.example.appdevelopment.data.dto.Feed
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.data.utils.await
import com.google.firebase.firestore.FirebaseFirestore
import com.example.appdevelopment.data.dto.User
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
): FireStoreRepository{


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
                firebaseFirestore.collection("feed").orderBy("timestamp",Query.Direction.DESCENDING).get().await().map { doc ->
                    Feed(
                        doc.data["image"].toString(),
                        doc.data["username"].toString(),
                        doc.data["description"].toString(),
                        //doc.getDate("timestamp")?.let { getReadableDateTime(it) },
                        doc.data["timestamp"].toString(),
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
                firebaseFirestore.collection("feed").document(feed.userID).update("like", feed.like).await()
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

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    override suspend fun updateStatsInUser(userID: String, feed: Feed) {
        withContext(Dispatchers.IO) {
            try {
                val user = getUserInfo(userID)
                val newRef = firebaseFirestore.collection("users").document(userID)

                if (user.token != "") {
                    newRef.update(
                        "totalLikes", user.totalLikes + feed.like,
                        "photos", user.photos + 1
                    ).await()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override suspend fun getFeed(userID: String): Feed {
            return try {
                val result = firebaseFirestore.collection("feed").document(userID).get().await()

                    Feed(
                        result.data?.get("image").toString(),
                        result.data?.get("username").toString(),
                        result.data?.get("description").toString(),
                        //result.getDate("timestamp")?.let { getReadableDateTime(it) },
                        result.data?.get("timestamp").toString(),
                        result.data?.get("like").toString().toInt(),
                        userID
                    )
            } catch (e: Exception) {
                e.printStackTrace()
                Feed("","","","",0,"")
            }
    }

    override suspend fun clearFeedCollection() {

        withContext(Dispatchers.IO) {
            try {
                firebaseFirestore.collection("feed").get().await().map { doc ->
                    val newRef = firebaseFirestore.collection("feed").document(doc.id)
                    val feed = getFeed(doc.id)
                    if(feed.userID != "") {
                        updateStatsInUser(doc.id, feed)
                        //newRef.delete()
                    }
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
                    result.data?.get("gender").toString(),
                    result.data?.get("photos").toString().toInt()
                )
            } catch(e: Exception){
                e.printStackTrace()
                User("","","", 0, 0, 0, "", "", 0)
            }
    }

    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO){
            try {
                firebaseFirestore.collection("users").document(user.token).update(
                    "description", user.description,
                    "username", user.username
                ).await()
                updateUsernameFeed(user.username, user.token)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override suspend fun updateUsernameFeed(username: String, userID: String) {
        withContext(Dispatchers.IO){
            try {
                firebaseFirestore.collection("feed").document(userID).update("username", username).await()
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
