package com.example.appdevelopment.data.domain.repository

import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.data.dataClasses.Board
import com.example.appdevelopment.data.dataClasses.Feed
import com.example.appdevelopment.data.dataClasses.User
import com.google.firebase.auth.FirebaseUser

interface FireStoreRepository {

suspend fun addUser(user: User)

suspend fun getLeaderboardList(): List<Board>

suspend fun getFeedList(): List<Feed>

suspend fun updateLikeForFeed(feed: Feed)

suspend fun addFeed(feed: Feed, user: User)

suspend fun updateStatsInUser(user: User, feed: Feed)

suspend fun getFeed(user: User): Resource<Feed>

suspend fun clearFeedCollection()

suspend fun getUserInfo(userID: String): User

suspend fun updateUser(user: User)

}