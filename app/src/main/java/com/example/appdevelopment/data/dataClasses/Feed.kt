package com.example.appdevelopment.data.dataClasses

import java.util.Date


data class Feed(
    var image: Int,
    var username: String,
    var description: String,
    var timestamp: String?,
    var like: Int,
    var userID: String
)