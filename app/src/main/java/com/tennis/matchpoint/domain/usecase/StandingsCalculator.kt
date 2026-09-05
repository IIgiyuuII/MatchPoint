package com.tennis.matchpoint.domain.usecase

import com.tennis.matchpoint.domain.model.StandingRow

/** Лёгкая, независимая от Room, модель завершённого матча — вход для расчёта таблицы. */
data class FinishedMatchResult(
    val player1Id: Long,
    val player2Id: Long,
    val winnerId: Long,
    val setsP1: Int,
    val setsP2: Int,
    val setsHistory: List<Pair<Int, Int>> // (геймы игрока1, геймы игрока2) по каждому сету
)

/**
 * Считает турнирную таблицу (кол-во побед, разница по сетам/геймам) по списку завершённых матчей.
 * Сортировка: победы -> разница сетов -> разница геймов — стандартная логика мини-лиги.
 */
object StandingsCalculator {

    fun calculate(
        players: Map<Long, String>, // playerId -> name
        finishedMatches: List<FinishedMatchResult>
    ): List<StandingRow> {
        val rows = players.mapValues { (id, name) ->
            StandingRow(
                playerId = id,
                playerName = name,
                played = 0, wins = 0, losses = 0,
                setsWon = 0, setsLost = 0, gamesWon = 0, gamesLost = 0
            )
        }.toMutableMap()

        finishedMatches.forEach { m ->
            val gamesP1 = m.setsHistory.sumOf { it.first }
            val gamesP2 = m.setsHistory.sumOf { it.second }

            rows[m.player1Id]?.let { r ->
                rows[m.player1Id] = r.copy(
                    played = r.played + 1,
                    wins = r.wins + if (m.winnerId == m.player1Id) 1 else 0,
                    losses = r.losses + if (m.winnerId == m.player2Id) 1 else 0,
                    setsWon = r.setsWon + m.setsP1,
                    setsLost = r.setsLost + m.setsP2,
                    gamesWon = r.gamesWon + gamesP1,
                    gamesLost = r.gamesLost + gamesP2
                )
            }
            rows[m.player2Id]?.let { r ->
                rows[m.player2Id] = r.copy(
                    played = r.played + 1,
                    wins = r.wins + if (m.winnerId == m.player2Id) 1 else 0,
                    losses = r.losses + if (m.winnerId == m.player1Id) 1 else 0,
                    setsWon = r.setsWon + m.setsP2,
                    setsLost = r.setsLost + m.setsP1,
                    gamesWon = r.gamesWon + gamesP2,
                    gamesLost = r.gamesLost + gamesP1
                )
            }
        }

        return rows.values.sortedWith(
            compareByDescending<StandingRow> { it.wins }
                .thenByDescending { it.setsWon - it.setsLost }
                .thenByDescending { it.gamesWon - it.gamesLost }
        )
    }
}
