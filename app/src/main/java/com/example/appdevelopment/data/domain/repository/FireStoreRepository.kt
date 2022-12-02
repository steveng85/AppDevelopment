package com.example.appdevelopment.data.domain.repository

import com.example.appdevelopment.data.dto.Board
import com.example.appdevelopment.data.dto.Feed
import com.example.appdevelopment.data.dto.User

interface FireStoreRepository {

//User
suspend fun addUser(user: User)

suspend fun updateUser(user: User)

suspend fun getUserInfo(userID: String): User

suspend fun updateStatsInUser(userID: String, feed: Feed, winnerFeed: Feed)

//Feed
suspend fun updateUsernameFeed(username: String, userID: String)

suspend fun getFeedList(): List<Feed>

suspend fun updateLikeForFeed(feed: Feed)

suspend fun addFeed(feed: Feed, user: User)

suspend fun getFeed(userID: String): Feed

suspend fun clearFeedCollection()

//Leaderboards
suspend fun getLeaderboardList(): List<Board>




}