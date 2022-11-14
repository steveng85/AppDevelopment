package com.example.appdevelopment.data.domain.repository

import com.example.appdevelopment.data.Resource
import com.example.appdevelopment.data.dataClasses.Board
import com.example.appdevelopment.data.dataClasses.User

interface FireStoreRepository {

suspend fun saveUser(user: User)

suspend fun getLeaderboardList(): List<Board>
}