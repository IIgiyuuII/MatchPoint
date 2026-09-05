package com.tennis.matchpoint.ui.players

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tennis.matchpoint.ui.components.PlayerAvatar

@Composable
fun PlayersScreen(
    onPlayerClick: (Long) -> Unit,
    viewModel: PlayersViewModel = hiltViewModel()
) {
    val players by viewModel.players.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 16.dp)) {
        Text("Игроки", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Все, кто хоть раз участвовал в турнире",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(16.dp))

        if (players.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Пока нет ни одного игрока", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        } else {
            LazyColumn(contentPadding = PaddingValues(bottom = 96.dp)) {
                items(players) { player ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onPlayerClick(player.id) }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PlayerAvatar(player.name, player.colorSeed)
                        Spacer(Modifier.width(16.dp))
                        Text(player.name, style = MaterialTheme.typography.titleMedium)
                    }
                    Divider()
                }
            }
        }
    }
}
