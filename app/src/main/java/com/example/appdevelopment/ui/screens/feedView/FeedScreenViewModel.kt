package com.example.appdevelopment.ui.screens.feedView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevelopment.data.dataClasses.Feed
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedScreenViewModel @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
): ViewModel(){

    private val _feedList = MutableStateFlow<List<Feed>>(emptyList())
    val feedList: StateFlow<List<Feed>?> = _feedList

    /*private val _uiState = MutableStateFlow(FeedScreenUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: FeedScreenEvent){
        when(event){
            is FeedScreenEvent.OnUpdate -> onUpdate(_uiState.value.feed)
        }
    }

    fun onUpdate(feed: Feed) = viewModelScope.launch{
        fireStoreRepository.updateLike(feed)
    }*/

    fun onGetFeedList() = viewModelScope.launch {
        val result = fireStoreRepository.getFeedList()
        _feedList.value = result
        println(_feedList.value)
    }
}