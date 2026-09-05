package com.tennis.matchpoint.ui.match

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tennis.matchpoint.ui.components.MatchStartAnimation
import com.tennis.matchpoint.ui.components.MatchWinnerAnimation
import com.tennis.matchpoint.ui.components.PlayerAvatar
import com.tennis.matchpoint.ui.theme.BallYellow

@Composable
fun MatchScreen(
    onFinish: () -> Unit,
    viewModel: MatchViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var showStartAnimation by remember { mutableStateOf(true) }

    if (state.isLoading || state.match == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
        return
    }
    val match = state.match!!

    LaunchedEffect(Unit) { viewModel.startMatchIfNeeded() }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    if (match.isTiebreak) "Тай-брейк" else "Сет ${match.setsP1 + match.setsP2 + 1}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                TextButton(
                    onClick = { viewModel.undoLastPoint() },
                    enabled = state.canUndo
                ) {
                    Icon(Icons.Filled.Undo, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Отменить очко")
                }
            }
            Spacer(Modifier.height(12.dp))

            PlayerScoreRow(
                name = state.player1Name,
                colorSeed = state.player1Name.hashCode(),
                games = match.gamesP1,
                sets = match.setsP1,
                point = state.pointDisplayP1,
                highlight = match.pointsP1 > match.pointsP2 || match.tiebreakP1 > match.tiebreakP2,
                onPoint = { viewModel.addPoint(true) }
            )
            Spacer(Modifier.height(12.dp))
            PlayerScoreRow(
                name = state.player2Name,
                colorSeed = state.player2Name.hashCode(),
                games = match.gamesP2,
                sets = match.setsP2,
                point = state.pointDisplayP2,
                highlight = match.pointsP2 > match.pointsP1 || match.tiebreakP2 > match.tiebreakP1,
                onPoint = { viewModel.addPoint(false) }
            )

            Spacer(Modifier.height(24.dp))
            if (match.setsHistory.isNotBlank()) {
                Text("Завершённые сеты: ${match.setsHistory.replace(";", "  ")}", style = MaterialTheme.typography.bodyMedium)
            }
        }

        if (showStartAnimation) {
            MatchStartAnimation(
                player1Name = state.player1Name,
                player2Name = state.player2Name,
                onFinished = { showStartAnimation = false }
            )
        }

        AnimatedVisibility(
            visible = !showStartAnimation && match.status == "FINISHED",
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            state.winnerName?.let { winner ->
                MatchWinnerAnimation(winnerName = winner, onDismiss = onFinish)
            }
        }
    }
}

@Composable
private fun PlayerScoreRow(
    name: String,
    colorSeed: Int,
    games: Int,
    sets: Int,
    point: String,
    highlight: Boolean,
    onPoint: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (highlight) 1.08f else 1f,
        animationSpec = spring(),
        label = "pointScale"
    )

    Card(
        onClick = onPoint,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (highlight) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayerAvatar(name, colorSeed)
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text("Сеты: $sets   Геймы: $games", style = MaterialTheme.typography.bodyMedium)
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(BallYellow)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .scale(scale)
            ) {
                Text(point, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
    Text(
        "Нажмите на карточку, если игрок выиграл очко",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
    )
}
