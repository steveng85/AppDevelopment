package com.example.appdevelopment.data.remote

import retrofit2.http.GET

interface MyApi {

    @GET("test")
    suspend fun networkCall()
}