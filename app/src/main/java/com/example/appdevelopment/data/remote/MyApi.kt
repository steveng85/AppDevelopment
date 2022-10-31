package com.example.appdevelopment.data.remote

interface MyApi {

    @get("text")
    suspend fun networkCall()
}