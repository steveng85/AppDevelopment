package com.example.appdevelopment.data.dataClasses

data class User (
    val token: String,
    val username: String,
    val email: String,
    val points: Int,
    val totalLikes: Int,
    val photos: Int
    )