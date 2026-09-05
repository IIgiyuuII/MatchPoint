package com.tennis.matchpoint.domain.scoring

import kotlin.math.abs

/**
 * Полное неизменяемое состояние счёта одного матча: очки внутри гейма, геймы внутри сета,
 * выигранные сеты и история завершённых сетов. 1 = игрок 1, 2 = игрок 2.
 */
data class TennisMatchState(
    val pointsP1: Int = 0,
    val pointsP2: Int = 0,
    val gamesP1: Int = 0,
    val gamesP2: Int = 0,
    val setsP1: Int = 0,
    val setsP2: Int = 0,
    val setsHistory: List<Pair<Int, Int>> = emptyList(),
    val isTiebreak: Boolean = false,
    val tiebreakP1: Int = 0,
    val tiebreakP2: Int = 0,
    val winner: Int? = null
) {
    val isFinished: Boolean get() = winner != null
}

/**
 * Движок настоящего теннисного счёта: гейм (0/15/30/40, deuce/ad), сет (до [gamesToWinSet] игр
 * с разницей 2, при равенстве gamesToWinSet:gamesToWinSet — тай-брейк до 7),
 * матч (best of N сетов, задаётся [setsToWin]).
 *
 * Состояние иммутабельно, поэтому у движка есть [replay] — пересчёт счёта с нуля по истории
 * очков. Это же используется для отмены последнего очка (undo): просто убираем последний
 * элемент истории и пересчитываем всё заново, не храня отдельную "обратную" логику.
 */
class TennisScoreEngine(
    private val setsToWin: Int = 2,
    private val gamesToWinSet: Int = 6
) {

    fun addPoint(state: TennisMatchState, winner: Int): TennisMatchState {
        if (state.isFinished) return state
        return if (state.isTiebreak) addTiebreakPoint(state, winner) else addGamePoint(state, winner)
    }

    /** Пересчитывает состояние матча с нуля по последовательности очков (1 или 2 — кто выиграл очко). */
    fun replay(pointsHistory: List<Int>): TennisMatchState {
        var state = TennisMatchState()
        for (winner in pointsHistory) {
            state = addPoint(state, winner)
        }
        return state
    }

    private fun addGamePoint(state: TennisMatchState, winner: Int): TennisMatchState {
        var p1 = state.pointsP1
        var p2 = state.pointsP2
        if (winner == 1) p1++ else p2++

        val gameWon = (p1 >= 4 || p2 >= 4) && abs(p1 - p2) >= 2
        if (!gameWon) return state.copy(pointsP1 = p1, pointsP2 = p2)

        val gameWinner = if (p1 > p2) 1 else 2
        val g1 = state.gamesP1 + if (gameWinner == 1) 1 else 0
        val g2 = state.gamesP2 + if (gameWinner == 2) 1 else 0
        val afterGame = state.copy(pointsP1 = 0, pointsP2 = 0, gamesP1 = g1, gamesP2 = g2)

        return checkSetCompletion(afterGame)
    }

    private fun checkSetCompletion(state: TennisMatchState): TennisMatchState {
        val g1 = state.gamesP1
        val g2 = state.gamesP2

        val setWon = (g1 >= gamesToWinSet || g2 >= gamesToWinSet) && abs(g1 - g2) >= 2
        if (setWon) return applySetWin(state, if (g1 > g2) 1 else 2)

        if (g1 == gamesToWinSet && g2 == gamesToWinSet) {
            return state.copy(isTiebreak = true, tiebreakP1 = 0, tiebreakP2 = 0)
        }
        return state
    }

    private fun addTiebreakPoint(state: TennisMatchState, winner: Int): TennisMatchState {
        var tb1 = state.tiebreakP1
        var tb2 = state.tiebreakP2
        if (winner == 1) tb1++ else tb2++

        val tbWon = (tb1 >= 7 || tb2 >= 7) && abs(tb1 - tb2) >= 2
        if (!tbWon) return state.copy(tiebreakP1 = tb1, tiebreakP2 = tb2)

        val setWinner = if (tb1 > tb2) 1 else 2
        val g1 = state.gamesP1 + if (setWinner == 1) 1 else 0
        val g2 = state.gamesP2 + if (setWinner == 2) 1 else 0
        return applySetWin(
            state.copy(gamesP1 = g1, gamesP2 = g2, tiebreakP1 = tb1, tiebreakP2 = tb2),
            setWinner
        )
    }

    private fun applySetWin(state: TennisMatchState, winner: Int): TennisMatchState {
        val history = state.setsHistory + (state.gamesP1 to state.gamesP2)
        val s1 = state.setsP1 + if (winner == 1) 1 else 0
        val s2 = state.setsP2 + if (winner == 2) 1 else 0
        val matchWinner = when {
            s1 >= setsToWin -> 1
            s2 >= setsToWin -> 2
            else -> null
        }
        return state.copy(
            setsP1 = s1,
            setsP2 = s2,
            setsHistory = history,
            gamesP1 = 0,
            gamesP2 = 0,
            pointsP1 = 0,
            pointsP2 = 0,
            isTiebreak = false,
            tiebreakP1 = 0,
            tiebreakP2 = 0,
            winner = matchWinner
        )
    }

    companion object {
        /** Текстовое представление очка гейма: 0, 15, 30, 40, Deuce("40"), Ad. */
        fun pointDisplay(points: Int, otherPoints: Int): String {
            if (points >= 3 && otherPoints >= 3) {
                return when {
                    points == otherPoints -> "40"
                    points > otherPoints -> "Ad"
                    else -> "40"
                }
            }
            return when (points) {
                0 -> "0"
                1 -> "15"
                2 -> "30"
                else -> "40"
            }
        }
    }
}
