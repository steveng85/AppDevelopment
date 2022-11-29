package com.example.appdevelopment.data.dto


data class Feed(
    var image: String,
    var username: String,
    var description: String,
    var timestamp: String?,
    var like: Int,
    var userID: String
)