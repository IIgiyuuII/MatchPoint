package com.tennis.matchpoint.data.local.dao

import androidx.room.*
import com.tennis.matchpoint.data.local.entity.TournamentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TournamentDao {

    @Query("SELECT * FROM tournaments ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<TournamentEntity>>

    @Query("SELECT * FROM tournaments WHERE status != 'FINISHED' ORDER BY createdAt DESC")
    fun observeOngoingAndUpcoming(): Flow<List<TournamentEntity>>

    @Query("SELECT * FROM tournaments WHERE status = 'FINISHED' ORDER BY createdAt DESC")
    fun observeFinished(): Flow<List<TournamentEntity>>

    @Query("SELECT * FROM tournaments WHERE id = :id")
    fun observeById(id: Long): Flow<TournamentEntity?>

    @Query("SELECT * FROM tournaments WHERE id = :id")
    suspend fun getById(id: Long): TournamentEntity?

    @Insert
    suspend fun insert(tournament: TournamentEntity): Long

    @Update
    suspend fun update(tournament: TournamentEntity)

    @Query("""
        SELECT t.* FROM tournaments t
        INNER JOIN tournament_players tp ON tp.tournamentId = t.id
        WHERE tp.playerId = :playerId
        ORDER BY t.createdAt DESC
    """)
    fun observeTournamentsForPlayer(playerId: Long): Flow<List<TournamentEntity>>
}
