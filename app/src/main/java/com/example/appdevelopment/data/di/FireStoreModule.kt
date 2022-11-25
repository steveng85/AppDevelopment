package com.example.appdevelopment.data.di

import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import com.example.appdevelopment.data.domain.repository.StorageRepository
import com.example.appdevelopment.data.remote.repository.FirestoreRepositoryImpl
import com.example.appdevelopment.data.remote.repository.StorageRepositoryImpl
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FireStoreModule {

    //Getting instance of firebase
    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore =  FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirestoreRepository(impl: FirestoreRepositoryImpl): FireStoreRepository = impl

    //getting instance of firestorage
    @Provides
    @Singleton
    fun provideStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideStorageRepository(impl: StorageRepositoryImpl): StorageRepository  = impl

    //get document reference user
    @Provides
    @Singleton
    fun provideUserRef(): DocumentReference = provideFireStore().document("user")

    @Provides
    @Singleton
    //get document reference feed
    fun provideFeedRef(): DocumentReference = provideFireStore().document("feed")
}