package com.tennis.matchpoint.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tennis.matchpoint.domain.model.Player
import com.tennis.matchpoint.ui.theme.BallYellow

/** Аватар-заглушка с инициалом игрока, цвет зависит от colorSeed — приятная мелочь без картинок. */
@Composable
fun PlayerAvatar(name: String, colorSeed: Int, modifier: Modifier = Modifier, size: Int = 40) {
    val color = Color.hsv((colorSeed % 360).toFloat(), 0.45f, 0.85f)
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.trim().take(1).uppercase().ifBlank { "?" },
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

/**
 * Поле поиска игрока с подсказками "по мере набора" (как просил заказчик): если игрок уже играл
 * в приложении — предлагается из базы, если нет — можно добавить нового по имени.
 */
@Composable
fun PlayerAutocompleteField(
    query: String,
    suggestions: List<Player>,
    selectedPlayers: List<Player>,
    onQueryChange: (String) -> Unit,
    onSuggestionPicked: (Player) -> Unit,
    onCreateNew: (String) -> Unit,
    onRemoveSelected: (Player) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("Добавить игрока") },
            trailingIcon = {
                if (query.isNotBlank()) {
                    IconButton(onClick = { onCreateNew(query); onQueryChange("") }) {
                        Icon(Icons.Filled.Add, contentDescription = "Добавить нового игрока")
                    }
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (suggestions.isNotEmpty()) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 3.dp,
                modifier = Modifier.padding(top = 4.dp).fillMaxWidth()
            ) {
                LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                    items(suggestions) { player ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            PlayerAvatar(player.name, player.colorSeed, size = 32)
                            Spacer(Modifier.width(12.dp))
                            Text(player.name, modifier = Modifier.weight(1f))
                            TextButton(onClick = {
                                onSuggestionPicked(player)
                            }) { Text("Добавить") }
                        }
                    }
                }
            }
        }

        if (selectedPlayers.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Text("Участники (${selectedPlayers.size})", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            FlowChips(players = selectedPlayers, onRemove = onRemoveSelected)
        }
    }
}

@Composable
private fun FlowChips(players: List<Player>, onRemove: (Player) -> Unit) {
    // Простая обёртка чипов рядами без экспериментального FlowRow — стабильно на любой версии Compose.
    val rows = players.chunked(2)
    Column {
        rows.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth()) {
                row.forEach { player ->
                    AssistChip(
                        onClick = { onRemove(player) },
                        label = { Text(player.name) },
                        leadingIcon = { PlayerAvatar(player.name, player.colorSeed, size = 20) },
                        trailingIcon = {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = "Убрать",
                                modifier = Modifier
                                    .size(16.dp)
                            )
                        },
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f)
                    )
                }
            }
        }
    }
}
