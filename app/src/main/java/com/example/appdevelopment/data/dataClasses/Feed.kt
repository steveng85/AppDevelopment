package com.example.appdevelopment.data.dataClasses

import java.util.Date


data class Feed(
    val image: Int,
    val username: String,
    val description: String,
    val timestamp: String?,
    val likes: Int,
    val userID: String
)