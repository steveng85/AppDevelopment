package com.example.appdevelopment.ui.screens.feedView


sealed class FeedScreenEvent {
    data class OnNeedUpdate(val needUpdate: Boolean): FeedScreenEvent()
    object OnClearFeed: FeedScreenEvent()
}