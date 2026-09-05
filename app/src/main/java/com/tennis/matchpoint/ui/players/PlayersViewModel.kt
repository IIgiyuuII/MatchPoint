package com.tennis.matchpoint.ui.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tennis.matchpoint.domain.model.Player
import com.tennis.matchpoint.domain.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    playerRepository: PlayerRepository
) : ViewModel() {

    val players: StateFlow<List<Player>> = playerRepository.observeAllPlayers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
