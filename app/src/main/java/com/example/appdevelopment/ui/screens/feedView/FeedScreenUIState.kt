package com.example.appdevelopment.ui.screens.feedView

import com.example.appdevelopment.data.dataClasses.Feed


data class FeedScreenUIState(
    val needOpdate: Boolean = false,
    val like: Boolean = false,
    val dislike: Boolean = false
)