package com.tennis.matchpoint.ui.createtournament

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tennis.matchpoint.domain.model.TournamentFormat
import com.tennis.matchpoint.ui.components.PlayerAutocompleteField

@Composable
fun CreateTournamentScreen(
    onCreated: (Long) -> Unit,
    onCancel: () -> Unit,
    viewModel: CreateTournamentViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.createdTournamentId) {
        state.createdTournamentId?.let { onCreated(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("Новый турнир", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = state.name,
            onValueChange = viewModel::onNameChanged,
            label = { Text("Название турнира") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(20.dp))

        Text("Формат", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        FormatOption(
            title = "Группы + плей-офф",
            description = "Круговая система в группах, затем сетка на выбывание — как в настоящих турнирах",
            selected = state.format == TournamentFormat.GROUPS_PLAYOFF,
            onClick = { viewModel.onFormatChanged(TournamentFormat.GROUPS_PLAYOFF) }
        )
        FormatOption(
            title = "Круговая система",
            description = "Каждый играет с каждым, победитель определяется по итоговой таблице",
            selected = state.format == TournamentFormat.ROUND_ROBIN,
            onClick = { viewModel.onFormatChanged(TournamentFormat.ROUND_ROBIN) }
        )
        FormatOption(
            title = "На выбывание",
            description = "Классический плей-офф: проиграл — выбыл",
            selected = state.format == TournamentFormat.SINGLE_ELIMINATION,
            onClick = { viewModel.onFormatChanged(TournamentFormat.SINGLE_ELIMINATION) }
        )
        Text(
            state.minPlayersHint,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (state.format == TournamentFormat.GROUPS_PLAYOFF) {
            Spacer(Modifier.height(16.dp))
            Text("Размер группы: ${state.groupSize}", style = MaterialTheme.typography.titleMedium)
            Slider(
                value = state.groupSize.toFloat(),
                onValueChange = { viewModel.onGroupSizeChanged(it.toInt()) },
                valueRange = 3f..6f,
                steps = 2
            )
        }

        Spacer(Modifier.height(16.dp))
        Text("Формат сета: до ${state.setsToWin} побед", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = state.setsToWin.toFloat(),
            onValueChange = { viewModel.onSetsToWinChanged(it.toInt()) },
            valueRange = 1f..2f,
            steps = 0
        )

        Spacer(Modifier.height(16.dp))
        Text("Геймов на сет: ${state.gamesPerSet}", style = MaterialTheme.typography.titleMedium)
        Text(
            "При счёте ${state.gamesPerSet}:${state.gamesPerSet} начинается тай-брейк",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Slider(
            value = state.gamesPerSet.toFloat(),
            onValueChange = { viewModel.onGamesPerSetChanged(it.toInt()) },
            valueRange = 2f..6f,
            steps = 3 // допустимые значения: 2, 3, 4, 5, 6
        )

        Spacer(Modifier.height(20.dp))
        PlayerAutocompleteField(
            query = state.searchQuery,
            suggestions = state.suggestions,
            selectedPlayers = state.selectedPlayers,
            onQueryChange = viewModel::onSearchQueryChanged,
            onSuggestionPicked = viewModel::addExistingPlayer,
            onCreateNew = viewModel::addPlayerByName,
            onRemoveSelected = viewModel::removePlayer
        )

        state.error?.let {
            Spacer(Modifier.height(12.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(28.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(onClick = onCancel, modifier = Modifier.weight(1f)) {
                Text("Отмена")
            }
            Spacer(Modifier.width(12.dp))
            Button(
                onClick = viewModel::createTournament,
                enabled = state.canSubmit,
                modifier = Modifier.weight(1f)
            ) {
                if (state.isCreating) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                } else {
                    Text("Создать")
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun FormatOption(title: String, description: String, selected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            RadioButton(selected = selected, onClick = onClick)
            Spacer(Modifier.width(8.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
