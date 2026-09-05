package com.tennis.matchpoint.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tennis.matchpoint.ui.components.TournamentCard

@Composable
fun HomeScreen(
    onTournamentClick: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp)
    ) {
        item {
            Text("MatchPoint", style = MaterialTheme.typography.headlineMedium)
            Text(
                "Турниры вашей теннисной компании",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(20.dp))
        }

        if (state.ongoing.isEmpty() && state.finished.isEmpty()) {
            item { EmptyHomeHint() }
        }

        if (state.ongoing.isNotEmpty()) {
            item {
                Text("Текущие турниры", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
            }
            items(state.ongoing) { tournament ->
                TournamentCard(tournament) { onTournamentClick(tournament.id) }
                Spacer(Modifier.height(8.dp))
            }
            item { Spacer(Modifier.height(16.dp)) }
        }

        if (state.finished.isNotEmpty()) {
            item {
                Text("Завершённые", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
            }
            items(state.finished) { tournament ->
                TournamentCard(tournament) { onTournamentClick(tournament.id) }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun EmptyHomeHint() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Турниров пока нет", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Text(
            "Нажмите + внизу экрана, чтобы создать первый турнир",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
