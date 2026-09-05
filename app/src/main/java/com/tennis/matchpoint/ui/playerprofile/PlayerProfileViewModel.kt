package com.tennis.matchpoint.ui.playerprofile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tennis.matchpoint.data.local.entity.TournamentEntity
import com.tennis.matchpoint.domain.model.Player
import com.tennis.matchpoint.domain.model.PlayerStats
import com.tennis.matchpoint.domain.repository.PlayerRepository
import com.tennis.matchpoint.domain.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlayerProfileUiState(
    val player: Player? = null,
    val stats: PlayerStats? = null,
    val tournaments: List<TournamentEntity> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class PlayerProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val playerRepository: PlayerRepository,
    private val tournamentRepository: TournamentRepository
) : ViewModel() {

    private val playerId: Long = checkNotNull(savedStateHandle["playerId"])

    private val _uiState = MutableStateFlow(PlayerProfileUiState())
    val uiState: StateFlow<PlayerProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                playerRepository.observePlayer(playerId),
                tournamentRepository.observeTournamentsForPlayer(playerId)
            ) { player, tournaments -> player to tournaments }
                .collect { (player, tournaments) ->
                    val stats = playerRepository.getPlayerStats(playerId)
                    _uiState.value = PlayerProfileUiState(
                        player = player,
                        stats = stats,
                        tournaments = tournaments,
                        isLoading = false
                    )
                }
        }
    }
}
