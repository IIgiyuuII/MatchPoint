package com.tennis.matchpoint.data.local.dao

import androidx.room.*
import com.tennis.matchpoint.data.local.entity.TournamentPlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TournamentPlayerDao {

    @Query("SELECT * FROM tournament_players WHERE tournamentId = :tournamentId")
    fun observeForTournament(tournamentId: Long): Flow<List<TournamentPlayerEntity>>

    @Query("SELECT * FROM tournament_players WHERE tournamentId = :tournamentId")
    suspend fun getForTournament(tournamentId: Long): List<TournamentPlayerEntity>

    @Insert
    suspend fun insertAll(entries: List<TournamentPlayerEntity>)

    @Query("SELECT COUNT(*) FROM tournament_players WHERE playerId = :playerId")
    fun observeTournamentCountForPlayer(playerId: Long): Flow<Int>
}
