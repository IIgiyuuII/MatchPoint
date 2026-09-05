package com.tennis.matchpoint.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tennis.matchpoint.data.local.entity.TournamentEntity
import com.tennis.matchpoint.domain.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HomeUiState(
    val ongoing: List<TournamentEntity> = emptyList(),
    val finished: List<TournamentEntity> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    tournamentRepository: TournamentRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = combine(
        tournamentRepository.observeOngoingAndUpcoming(),
        tournamentRepository.observeFinished()
    ) { ongoing, finished -> HomeUiState(ongoing, finished) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeUiState())
}
