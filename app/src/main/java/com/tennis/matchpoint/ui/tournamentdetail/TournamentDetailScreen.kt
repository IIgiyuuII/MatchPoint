package com.tennis.matchpoint.ui.tournamentdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tennis.matchpoint.data.local.entity.MatchEntity
import com.tennis.matchpoint.domain.model.StandingRow
import com.tennis.matchpoint.ui.components.formatLabel

@Composable
fun TournamentDetailScreen(
    onOpenMatch: (Long) -> Unit,
    viewModel: TournamentDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading || state.tournament == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
        return
    }
    val tournament = state.tournament!!

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp)
    ) {
        item {
            Text(tournament.name, style = MaterialTheme.typography.headlineMedium)
            Text(
                formatLabel(tournament.format),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(16.dp))
        }

        if (tournament.status == "FINISHED") {
            item {
                val winnerName = state.playerName(tournament.winnerPlayerId)
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🏆", style = MaterialTheme.typography.headlineMedium)
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text("Победитель турнира", style = MaterialTheme.typography.bodyMedium)
                            Text(winnerName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
            }
        } else {
            val next = state.currentMatch ?: state.nextMatch
            if (next != null) {
                item {
                    NextMatchCard(
                        match = next,
                        player1Name = state.playerName(next.player1Id),
                        player2Name = state.playerName(next.player2Id),
                        onOpen = {
                            if (next.status == "SCHEDULED") viewModel.startMatch(next.id)
                            onOpenMatch(next.id)
                        }
                    )
                    Spacer(Modifier.height(20.dp))
                }
            }
        }

        if (state.groupStandings.isNotEmpty()) {
            item {
                Text("Турнирная таблица", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
            }
            state.groupStandings.toSortedMap().forEach { (groupIndex, standings) ->
                if (state.groupStandings.size > 1) {
                    item {
                        Text("Группа ${'A' + groupIndex}", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                    }
                }
                item {
                    StandingsTable(standings)
                    Spacer(Modifier.height(16.dp))
                }
            }
        }

        if (state.playoffMatches.isNotEmpty()) {
            item {
                Text("Плей-офф", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
            }
            val byRound = state.playoffMatches.groupBy { it.roundLabel }
            byRound.forEach { (label, matches) ->
                item { Text(label, style = MaterialTheme.typography.titleMedium) }
                items(matches) { match ->
                    MatchRow(match, state.playerName(match.player1Id), state.playerName(match.player2Id))
                }
                item { Spacer(Modifier.height(8.dp)) }
            }
        }

        if (state.groupMatches.isNotEmpty()) {
            item {
                Text("Все матчи", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
            }
            items(state.groupMatches) { match ->
                MatchRow(match, state.playerName(match.player1Id), state.playerName(match.player2Id))
            }
        }
    }
}

@Composable
private fun NextMatchCard(match: MatchEntity, player1Name: String, player2Name: String, onOpen: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                if (match.status == "IN_PROGRESS") "Матч идёт сейчас" else "Следующий матч",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(8.dp))
            Text("$player1Name  vs  $player2Name", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(12.dp))
            Button(onClick = onOpen, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Filled.PlayArrow, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(if (match.status == "IN_PROGRESS") "Продолжить матч" else "Начать матч")
            }
        }
    }
}

@Composable
private fun MatchRow(match: MatchEntity, player1Name: String, player2Name: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("$player1Name vs $player2Name", modifier = Modifier.weight(1f))
        if (match.status == "FINISHED") {
            Text("${match.setsP1} : ${match.setsP2}", fontWeight = FontWeight.Bold)
        } else {
            AssistChip(onClick = {}, label = { Text(if (match.status == "IN_PROGRESS") "Идёт" else "Ожидание") })
        }
    }
    Divider()
}

@Composable
private fun StandingsTable(standings: List<StandingRow>) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Игрок", modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)
                Text("И", modifier = Modifier.width(28.dp), style = MaterialTheme.typography.labelLarge)
                Text("В", modifier = Modifier.width(28.dp), style = MaterialTheme.typography.labelLarge)
                Text("Сеты", modifier = Modifier.width(56.dp), style = MaterialTheme.typography.labelLarge)
            }
            Divider(modifier = Modifier.padding(vertical = 4.dp))
            standings.forEachIndexed { index, row ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text("${index + 1}. ${row.playerName}", modifier = Modifier.weight(1f))
                    Text(row.played.toString(), modifier = Modifier.width(28.dp))
                    Text(row.wins.toString(), modifier = Modifier.width(28.dp))
                    Text("${row.setsWon}-${row.setsLost}", modifier = Modifier.width(56.dp))
                }
            }
        }
    }
}
