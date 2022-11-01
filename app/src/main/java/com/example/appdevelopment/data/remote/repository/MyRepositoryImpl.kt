package com.example.appdevelopment.data.remote.repository

import com.example.appdevelopment.data.domain.repository.MyRepository
import com.example.appdevelopment.data.remote.MyApi

class MyRepositoryImpl(
    private val api: MyApi
): MyRepository {

    override suspend fun networkCall() {

    }
}