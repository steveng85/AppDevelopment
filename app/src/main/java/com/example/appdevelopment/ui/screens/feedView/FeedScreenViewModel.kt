package com.example.appdevelopment.ui.screens.feedView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevelopment.data.dto.Feed
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedScreenViewModel @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
): ViewModel(){

    private val _feedList = MutableStateFlow<List<Feed>>(emptyList())
    val feedList: StateFlow<List<Feed>?> = _feedList

    private val _uiState = MutableStateFlow(FeedScreenUIState())
    val uiState = _uiState.asStateFlow()


    fun onEvent(event: FeedScreenEvent){
        when(event){
            is FeedScreenEvent.OnNeedUpdate -> onNeedOpdate(event.needUpdate)
            is FeedScreenEvent.OnClearFeed -> onClearFeed()
        }
    }/*

    fun onUpdate(feed: Feed) = viewModelScope.launch{
        fireStoreRepository.updateLike(feed)
    }*/

    fun onGetFeedList() = viewModelScope.launch {
        val result = fireStoreRepository.getFeedList()
        _feedList.value = result
        println(_feedList.value)
    }

    fun onPressLike(feed: Feed) = viewModelScope.launch{
        feed.like = feed.like + 1
        fireStoreRepository.updateLikeForFeed(feed)
        onNeedOpdate(true)
    }

    fun onNeedOpdate(needUpdate: Boolean) {
        _uiState.value = _uiState.value.copy(needOpdate = needUpdate)
    }

    fun onClearFeed() = viewModelScope.launch{
        fireStoreRepository.clearFeedCollection()
    }
}