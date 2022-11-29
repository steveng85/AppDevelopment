package com.example.appdevelopment.ui.screens.leaderboardsView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevelopment.data.dto.Board
import com.example.appdevelopment.data.domain.repository.FireStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
): ViewModel() {
    private val _leaderboard = MutableStateFlow<List<Board>>(emptyList())
    val leaderboard: StateFlow<List<Board>?> = _leaderboard


    fun onGet() = viewModelScope.launch {
        val result = fireStoreRepository.getLeaderboardList()
        _leaderboard.value = result
        println(_leaderboard.value)
    }
}