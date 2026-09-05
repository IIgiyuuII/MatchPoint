package com.tennis.matchpoint.ui.createtournament

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tennis.matchpoint.domain.model.MatchRules
import com.tennis.matchpoint.domain.model.Player
import com.tennis.matchpoint.domain.model.TournamentFormat
import com.tennis.matchpoint.domain.repository.PlayerRepository
import com.tennis.matchpoint.domain.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateTournamentUiState(
    val name: String = "",
    val format: TournamentFormat = TournamentFormat.GROUPS_PLAYOFF,
    val setsToWin: Int = 2,
    val gamesPerSet: Int = 6,
    val groupSize: Int = 4,
    val selectedPlayers: List<Player> = emptyList(),
    val searchQuery: String = "",
    val suggestions: List<Player> = emptyList(),
    val isCreating: Boolean = false,
    val error: String? = null,
    val createdTournamentId: Long? = null
) {
    val canSubmit: Boolean get() =
        name.isNotBlank() && selectedPlayers.size >= 2 && !isCreating

    val minPlayersHint: String get() = when (format) {
        TournamentFormat.ROUND_ROBIN -> "Минимум 2 игрока — каждый сыграет с каждым"
        TournamentFormat.GROUPS_PLAYOFF -> "Минимум 4 игрока — группы + плей-офф"
        TournamentFormat.SINGLE_ELIMINATION -> "Минимум 2 игрока — сразу на выбывание"
    }
}

@HiltViewModel
class CreateTournamentViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val tournamentRepository: TournamentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateTournamentUiState())
    val uiState: StateFlow<CreateTournamentUiState> = _uiState.asStateFlow()

    fun onNameChanged(value: String) {
        _uiState.value = _uiState.value.copy(name = value)
    }

    fun onFormatChanged(format: TournamentFormat) {
        _uiState.value = _uiState.value.copy(format = format)
    }

    fun onSetsToWinChanged(value: Int) {
        _uiState.value = _uiState.value.copy(setsToWin = value)
    }

    fun onGamesPerSetChanged(value: Int) {
        _uiState.value = _uiState.value.copy(gamesPerSet = value)
    }

    fun onGroupSizeChanged(value: Int) {
        _uiState.value = _uiState.value.copy(groupSize = value)
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        viewModelScope.launch {
            val already = _uiState.value.selectedPlayers.map { it.id }.toSet()
            val results = if (query.isBlank()) emptyList() else playerRepository.searchPlayers(query)
            _uiState.value = _uiState.value.copy(suggestions = results.filterNot { it.id in already })
        }
    }

    fun addExistingPlayer(player: Player) {
        val state = _uiState.value
        if (state.selectedPlayers.any { it.id == player.id }) return
        _uiState.value = state.copy(
            selectedPlayers = state.selectedPlayers + player,
            searchQuery = "",
            suggestions = emptyList()
        )
    }

    /** Добавляет игрока по имени: если такого игрока ещё не было в приложении — создаёт нового,
     * если был — подтягивает его существующий профиль и статистику (как просил заказчик). */
    fun addPlayerByName(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            val player = playerRepository.getOrCreatePlayer(name)
            addExistingPlayer(player)
        }
    }

    fun removePlayer(player: Player) {
        val state = _uiState.value
        _uiState.value = state.copy(selectedPlayers = state.selectedPlayers.filterNot { it.id == player.id })
    }

    fun createTournament() {
        val state = _uiState.value
        if (!state.canSubmit) return
        _uiState.value = state.copy(isCreating = true, error = null)
        viewModelScope.launch {
            try {
                val id = tournamentRepository.createTournament(
                    name = state.name.trim(),
                    format = state.format,
                    rules = MatchRules(setsToWin = state.setsToWin, gamesPerSet = state.gamesPerSet),
                    groupSize = state.groupSize,
                    playerIds = state.selectedPlayers.map { it.id }
                )
                _uiState.value = _uiState.value.copy(isCreating = false, createdTournamentId = id)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isCreating = false, error = e.message ?: "Не удалось создать турнир")
            }
        }
    }
}
