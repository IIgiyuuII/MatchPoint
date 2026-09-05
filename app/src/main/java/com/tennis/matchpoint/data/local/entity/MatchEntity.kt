package com.tennis.matchpoint.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tournamentId: Long,
    val stage: String,          // Stage.name
    val roundLabel: String,
    val groupIndex: Int,        // -1 для плей-офф
    val orderIndex: Int,        // порядок проведения внутри турнира
    val roundNumber: Int = 0,   // номер раунда плей-офф (1,2,3...), для групп не используется

    val player1Id: Long?,       // null означает "бай" — соперника нет, авто-победа player2
    val player2Id: Long?,

    val pointsP1: Int = 0,
    val pointsP2: Int = 0,
    val gamesP1: Int = 0,
    val gamesP2: Int = 0,
    val setsP1: Int = 0,
    val setsP2: Int = 0,
    val setsHistory: String = "", // "6-4;3-6;7-5"
    val isTiebreak: Boolean = false,
    val tiebreakP1: Int = 0,
    val tiebreakP2: Int = 0,

    /** Последовательность очков за весь матч: "1,2,1,1,2,...", 1/2 = кто выиграл очко.
     * Хранится, чтобы можно было отменить последнее очко — состояние просто пересчитывается
     * заново движком счёта (TennisScoreEngine.replay) без отдельной "обратной" логики. */
    val pointsHistory: String = "",

    val status: String,         // MatchStatus.name
    val winnerId: Long? = null,
    val startedAt: Long? = null,
    val finishedAt: Long? = null
)
