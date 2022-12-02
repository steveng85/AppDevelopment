//package com.example.appdevelopment
//
//import android.app.Application
//import androidx.camera.core.*
//import androidx.camera.lifecycle.ProcessCameraProvider
//import com.example.appdevelopment.data.CameraLogic
//import com.example.appdevelopment.data.di.AppModule
//import com.example.appdevelopment.data.domain.repository.AuthRepository
//import com.example.appdevelopment.data.domain.repository.CameraRepository
//import com.example.appdevelopment.data.remote.repository.AuthRepositoryImpl
//import com.google.firebase.auth.FirebaseAuth
//import dagger.Binds
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.components.SingletonComponent
//import dagger.hilt.testing.TestInstallIn
//import javax.inject.Singleton

/**
 *
 * We haven't been able to pass the tests, and all HiltTest dependencies are
 * removed from the gradle.file
 *
 * Jetpack Compose UI testing, inspired by:
 *
 * https://developer.android.com/training/dependency-injection/hilt-testing
 * https://www.youtube.com/watch?v=nDCCwyS0_MQ&ab_channel=PhilippLackner *
 *
 * */
//
//@Module
//@TestInstallIn(components = [SingletonComponent::class], replaces = [AppModule::class])
//object TestAppModule {
//
//    // Firebase Auth. logic //
//
//    @Provides
//    @Singleton
//    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
//
//    @Provides
//    @Singleton
//    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
//
//
//    // Camera Logic //
//
//    @Provides
//    @Singleton
//    fun provideCameraRepository(
//        cameraProvider: ProcessCameraProvider,
//        cameraSelector: CameraSelector,
//        cameraPreview: Preview,
//        imageAnalysis: ImageAnalysis,
//        imageCapture: ImageCapture
//    ): CameraRepository {
//        return CameraLogic(
//            cameraProvider,
//            cameraSelector,
//            cameraPreview,
//            imageAnalysis,
//            imageCapture
//        )
//    }
//
//    @Provides
//    @Singleton
//    fun provideCameraSelector(): CameraSelector {
//        return CameraSelector.Builder()
//            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideCameraProvider(application: Application)
//            : ProcessCameraProvider {
//        return ProcessCameraProvider.getInstance(application).get()
//
//    }
//
//    @Provides
//    @Singleton
//    fun provideCameraPreview(): Preview {
//        return Preview.Builder().build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideImageAnalysis(): ImageAnalysis {
//        return ImageAnalysis.Builder()
//            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideImageCapture(): ImageCapture {
//        return ImageCapture.Builder()
//            .setFlashMode(ImageCapture.FLASH_MODE_ON)
//            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
//            .build()
//    }
//}