package com.tennis.matchpoint.ui.playerprofile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tennis.matchpoint.ui.components.PlayerAvatar
import com.tennis.matchpoint.ui.components.formatLabel
import com.tennis.matchpoint.ui.components.statusLabel

@Composable
fun PlayerProfileScreen(
    onTournamentClick: (Long) -> Unit,
    viewModel: PlayerProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading || state.player == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val player = state.player!!
    val stats = state.stats

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PlayerAvatar(player.name, player.colorSeed, size = 64)
                Spacer(Modifier.width(16.dp))
                Text(player.name, style = MaterialTheme.typography.headlineSmall)
            }
            Spacer(Modifier.height(20.dp))
        }

        if (stats != null) {
            item {
                StatsGrid(stats)
                Spacer(Modifier.height(24.dp))
            }
        }

        item {
            Text("Турниры", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
        }

        items(state.tournaments) { tournament ->
            Card(
                onClick = { onTournamentClick(tournament.id) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(tournament.name, style = MaterialTheme.typography.titleMedium)
                        Text(
                            formatLabel(tournament.format),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    if (tournament.winnerPlayerId == player.id) {
                        AssistChip(onClick = {}, label = { Text("🏆 Победа") })
                    } else {
                        AssistChip(onClick = {}, label = { Text(statusLabel(tournament.status)) })
                    }
                }
            }
        }

        if (state.tournaments.isEmpty()) {
            item {
                Text(
                    "Этот игрок ещё не участвовал в турнирах",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun StatsGrid(stats: com.tennis.matchpoint.domain.model.PlayerStats) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                StatItem("Турниры", stats.tournamentsPlayed.toString(), Modifier.weight(1f))
                StatItem("Победы в турнирах", stats.tournamentsWon.toString(), Modifier.weight(1f))
            }
            Spacer(Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                StatItem("Матчи", "${stats.matchesWon}/${stats.matchesPlayed}", Modifier.weight(1f))
                StatItem("% побед", "${(stats.winRate * 100).toInt()}%", Modifier.weight(1f))
            }
            Spacer(Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                StatItem("Сеты", "${stats.setsWon}-${stats.setsLost}", Modifier.weight(1f))
                StatItem("Геймы", "${stats.gamesWon}-${stats.gamesLost}", Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun StatItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
