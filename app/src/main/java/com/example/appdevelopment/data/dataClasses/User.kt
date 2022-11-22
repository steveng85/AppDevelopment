package com.example.appdevelopment.data.dataClasses

data class User (
    var token: String,
    var username: String,
    var email: String,
    var points: Int,
    var rank: Int,
    var totalLikes: Int,
    var description: String,
    var gender: String
    )