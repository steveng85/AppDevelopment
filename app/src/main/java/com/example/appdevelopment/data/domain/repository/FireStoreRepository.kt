package com.example.appdevelopment.data.domain.repository

import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.data.dataClasses.Board
import com.example.appdevelopment.data.dataClasses.Feed
import com.example.appdevelopment.data.dataClasses.User

interface FireStoreRepository {

suspend fun addUser(user: User)

suspend fun getLeaderboardList(): List<Board>

suspend fun getFeedList(): List<Feed>

suspend fun updateLike(feed: Feed)

suspend fun addFeed(feed: Feed, user: User)
}