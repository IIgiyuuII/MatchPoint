package com.tennis.matchpoint.data.local.dao

import androidx.room.*
import com.tennis.matchpoint.data.local.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Query("SELECT * FROM players ORDER BY name COLLATE NOCASE ASC")
    fun observeAll(): Flow<List<PlayerEntity>>

    @Query("SELECT * FROM players WHERE name LIKE '%' || :query || '%' ORDER BY name LIMIT 10")
    suspend fun search(query: String): List<PlayerEntity>

    @Query("SELECT * FROM players WHERE id = :id")
    suspend fun getById(id: Long): PlayerEntity?

    @Query("SELECT * FROM players WHERE id = :id")
    fun observeById(id: Long): Flow<PlayerEntity?>

    @Query("SELECT * FROM players WHERE name = :name COLLATE NOCASE LIMIT 1")
    suspend fun findByName(name: String): PlayerEntity?

    @Insert
    suspend fun insert(player: PlayerEntity): Long

    @Update
    suspend fun update(player: PlayerEntity)
}
