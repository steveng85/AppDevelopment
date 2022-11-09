package com.example.appdevelopment.data.di

import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.data.remote.repository.FirestoreRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FireStoreModule {

    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore =  FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirestoreRepository(impl: FirestoreRepositoryImpl): FireStoreRepository = impl

}