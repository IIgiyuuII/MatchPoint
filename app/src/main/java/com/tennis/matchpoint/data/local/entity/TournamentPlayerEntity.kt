package com.tennis.matchpoint.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Связь "многие-ко-многим" между турнирами и игроками + место в группе/посев. */
@Entity(tableName = "tournament_players")
data class TournamentPlayerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tournamentId: Long,
    val playerId: Long,
    val groupIndex: Int,   // -1, если формат без групп
    val seed: Int
)
