package com.tennis.matchpoint.data.local.dao

import androidx.room.*
import com.tennis.matchpoint.data.local.entity.MatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {

    @Query("SELECT * FROM matches WHERE tournamentId = :tournamentId ORDER BY orderIndex ASC")
    fun observeForTournament(tournamentId: Long): Flow<List<MatchEntity>>

    @Query("SELECT * FROM matches WHERE tournamentId = :tournamentId ORDER BY orderIndex ASC")
    suspend fun getForTournament(tournamentId: Long): List<MatchEntity>

    @Query("SELECT * FROM matches WHERE id = :id")
    fun observeById(id: Long): Flow<MatchEntity?>

    @Query("SELECT * FROM matches WHERE id = :id")
    suspend fun getById(id: Long): MatchEntity?

    @Query("""
        SELECT * FROM matches
        WHERE tournamentId = :tournamentId AND status = 'SCHEDULED'
        ORDER BY orderIndex ASC LIMIT 1
    """)
    suspend fun getNextScheduled(tournamentId: Long): MatchEntity?

    @Query("SELECT * FROM matches WHERE tournamentId = :tournamentId AND status = 'IN_PROGRESS' LIMIT 1")
    suspend fun getInProgress(tournamentId: Long): MatchEntity?

    @Query("""
        SELECT * FROM matches
        WHERE (player1Id = :playerId OR player2Id = :playerId) AND status = 'FINISHED'
    """)
    suspend fun getFinishedForPlayer(playerId: Long): List<MatchEntity>

    @Insert
    suspend fun insert(match: MatchEntity): Long

    @Insert
    suspend fun insertAll(matches: List<MatchEntity>): List<Long>

    @Update
    suspend fun update(match: MatchEntity)
}
