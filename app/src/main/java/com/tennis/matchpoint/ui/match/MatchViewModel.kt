package com.tennis.matchpoint.ui.match

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tennis.matchpoint.data.local.entity.MatchEntity
import com.tennis.matchpoint.domain.repository.PlayerRepository
import com.tennis.matchpoint.domain.repository.TournamentRepository
import com.tennis.matchpoint.domain.scoring.TennisScoreEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MatchUiState(
    val match: MatchEntity? = null,
    val player1Name: String = "",
    val player2Name: String = "",
    val isLoading: Boolean = true,
    /** true ровно один раз — в момент, когда матч только что завершился (триггер анимации победы). */
    val justFinished: Boolean = false
) {
    val pointDisplayP1: String get() {
        val m = match ?: return "0"
        return if (m.isTiebreak) m.tiebreakP1.toString() else TennisScoreEngine.pointDisplay(m.pointsP1, m.pointsP2)
    }
    val pointDisplayP2: String get() {
        val m = match ?: return "0"
        return if (m.isTiebreak) m.tiebreakP2.toString() else TennisScoreEngine.pointDisplay(m.pointsP2, m.pointsP1)
    }
    val winnerName: String? get() {
        val m = match ?: return null
        return when (m.winnerId) {
            m.player1Id -> player1Name
            m.player2Id -> player2Name
            else -> null
        }
    }

    /** Есть ли что отменять — прячем/дизейблим кнопку "Отменить очко", если очков ещё не было. */
    val canUndo: Boolean get() = !match?.pointsHistory.isNullOrBlank()
}

@HiltViewModel
class MatchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tournamentRepository: TournamentRepository,
    private val playerRepository: PlayerRepository
) : ViewModel() {

    private val matchId: Long = checkNotNull(savedStateHandle["matchId"])

    private val _uiState = MutableStateFlow(MatchUiState())
    val uiState: StateFlow<MatchUiState> = _uiState.asStateFlow()

    private var wasFinishedAlready = false

    init {
        viewModelScope.launch {
            combine(
                tournamentRepository.observeMatch(matchId),
                playerRepository.observeAllPlayers()
            ) { match, players -> match to players.associate { it.id to it.name } }
                .collect { (match, names) ->
                    val becameFinished = match?.status == "FINISHED" && !wasFinishedAlready
                    if (match?.status == "FINISHED") wasFinishedAlready = true
                    _uiState.value = MatchUiState(
                        match = match,
                        player1Name = match?.player1Id?.let { names[it] } ?: "Игрок 1",
                        player2Name = match?.player2Id?.let { names[it] } ?: "Игрок 2",
                        isLoading = false,
                        justFinished = becameFinished
                    )
                }
        }
    }

    fun addPoint(player1Wins: Boolean) {
        val current = _uiState.value.match
        if (current == null || current.status == "FINISHED") return
        viewModelScope.launch { tournamentRepository.addPoint(matchId, player1Wins) }
    }

    /** Отменяет последнее очко — например, если админ случайно нажал не на того игрока.
     * Работает даже если очко было победным (матч "расфинишится" и продолжится). */
    fun undoLastPoint() {
        wasFinishedAlready = false
        viewModelScope.launch { tournamentRepository.removeLastPoint(matchId) }
    }

    fun startMatchIfNeeded() {
        viewModelScope.launch { tournamentRepository.startMatch(matchId) }
    }
}
