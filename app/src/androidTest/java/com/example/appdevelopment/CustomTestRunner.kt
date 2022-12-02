//package com.example.appdevelopment
//
//import android.app.Application
//import android.content.Context
//import androidx.test.runner.AndroidJUnitRunner
//import dagger.hilt.android.testing.HiltTestApplication
//
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
//// A custom runner to set up the instrumented application class for tests.
//class CustomTestRunner : AndroidJUnitRunner() {
//
//    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
//        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
//    }
//}