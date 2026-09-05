package com.tennis.matchpoint.ui.tournamentdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tennis.matchpoint.data.local.entity.MatchEntity
import com.tennis.matchpoint.data.local.entity.TournamentEntity
import com.tennis.matchpoint.data.local.entity.TournamentPlayerEntity
import com.tennis.matchpoint.domain.model.StandingRow
import com.tennis.matchpoint.domain.model.TournamentFormat
import com.tennis.matchpoint.domain.repository.PlayerRepository
import com.tennis.matchpoint.domain.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TournamentDetailUiState(
    val tournament: TournamentEntity? = null,
    val matches: List<MatchEntity> = emptyList(),
    val participants: List<TournamentPlayerEntity> = emptyList(),
    val playerNames: Map<Long, String> = emptyMap(),
    val groupStandings: Map<Int, List<StandingRow>> = emptyMap(),
    val isLoading: Boolean = true
) {
    val nextMatch: MatchEntity? get() = matches.firstOrNull { it.status == "SCHEDULED" }
    val currentMatch: MatchEntity? get() = matches.firstOrNull { it.status == "IN_PROGRESS" }
    val groupMatches: List<MatchEntity> get() = matches.filter { it.stage == "GROUP" }
    val playoffMatches: List<MatchEntity> get() = matches.filter { it.stage == "PLAYOFF" }.sortedBy { it.orderIndex }
    fun playerName(id: Long?): String = id?.let { playerNames[it] } ?: "TBD"
}

@HiltViewModel
class TournamentDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tournamentRepository: TournamentRepository,
    private val playerRepository: PlayerRepository
) : ViewModel() {

    private val tournamentId: Long = checkNotNull(savedStateHandle["tournamentId"])

    private val _uiState = MutableStateFlow(TournamentDetailUiState())
    val uiState: StateFlow<TournamentDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                tournamentRepository.observeTournament(tournamentId),
                tournamentRepository.observeMatches(tournamentId),
                tournamentRepository.observeParticipants(tournamentId),
                playerRepository.observeAllPlayers()
            ) { tournament, matches, participants, players ->
                TournamentDetailCombined(tournament, matches, participants, players.associate { it.id to it.name })
            }.collect { (tournament, matches, participants, names) ->
                val standings = if (tournament != null && tournament.format != TournamentFormat.SINGLE_ELIMINATION.name) {
                    participants.map { it.groupIndex }.toSortedSet().associateWith { groupIndex ->
                        tournamentRepository.computeGroupStandings(tournamentId, groupIndex)
                    }
                } else emptyMap()

                _uiState.value = TournamentDetailUiState(
                    tournament = tournament,
                    matches = matches,
                    participants = participants,
                    playerNames = names,
                    groupStandings = standings,
                    isLoading = false
                )
            }
        }
    }

    fun startMatch(matchId: Long) {
        viewModelScope.launch { tournamentRepository.startMatch(matchId) }
    }
}

private data class TournamentDetailCombined(
    val tournament: TournamentEntity?,
    val matches: List<MatchEntity>,
    val participants: List<TournamentPlayerEntity>,
    val playerNames: Map<Long, String>
)
