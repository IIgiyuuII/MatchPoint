package com.tennis.matchpoint.domain.repository

import com.tennis.matchpoint.domain.model.Player
import com.tennis.matchpoint.domain.model.PlayerStats
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun observeAllPlayers(): Flow<List<Player>>
    fun observePlayer(playerId: Long): Flow<Player?>
    suspend fun searchPlayers(query: String): List<Player>
    suspend fun getOrCreatePlayer(name: String): Player
    suspend fun getPlayerStats(playerId: Long): PlayerStats
}
