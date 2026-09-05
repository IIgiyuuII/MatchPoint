package com.tennis.matchpoint.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tennis.matchpoint.data.local.entity.TournamentEntity
import com.tennis.matchpoint.domain.model.TournamentFormat

fun formatLabel(format: String): String = when (format) {
    TournamentFormat.ROUND_ROBIN.name -> "Круговая система"
    TournamentFormat.GROUPS_PLAYOFF.name -> "Группы + плей-офф"
    TournamentFormat.SINGLE_ELIMINATION.name -> "На выбывание"
    else -> format
}

fun statusLabel(status: String): String = when (status) {
    "ONGOING" -> "Идёт"
    "UPCOMING" -> "Скоро"
    "FINISHED" -> "Завершён"
    else -> status
}

@Composable
fun TournamentCard(tournament: TournamentEntity, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (tournament.status == "FINISHED") Icons.Filled.EmojiEvents else Icons.Filled.Groups,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(tournament.name, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(
                    formatLabel(tournament.format),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            AssistChip(onClick = {}, label = { Text(statusLabel(tournament.status)) })
        }
    }
}
