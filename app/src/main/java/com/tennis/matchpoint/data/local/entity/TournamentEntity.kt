package com.tennis.matchpoint.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tournaments")
data class TournamentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val format: String,          // TournamentFormat.name
    val status: String,          // TournamentStatus.name
    val setsToWin: Int,
    val gamesPerSet: Int = 6,
    val groupSize: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val winnerPlayerId: Long? = null
)
