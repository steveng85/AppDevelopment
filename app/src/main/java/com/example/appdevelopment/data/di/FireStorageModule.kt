package com.example.appdevelopment.data.di

import com.example.appdevelopment.data.domain.repository.StorageRepository
import com.example.appdevelopment.data.remote.repository.StorageRepositoryImpl
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FireStorageModule {

    //getting instance of firestorage
    @Provides
    @Singleton
    fun provideStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideStorageRepository(impl: StorageRepositoryImpl): StorageRepository = impl
}