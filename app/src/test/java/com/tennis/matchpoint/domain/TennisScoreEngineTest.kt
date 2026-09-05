package com.tennis.matchpoint.domain

import com.tennis.matchpoint.domain.scoring.TennisMatchState
import com.tennis.matchpoint.domain.scoring.TennisScoreEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TennisScoreEngineTest {

    private val engine = TennisScoreEngine(setsToWin = 2)

    @Test
    fun `four points in a row wins the game`() {
        var state = TennisMatchState()
        repeat(4) { state = engine.addPoint(state, winner = 1) }
        assertEquals(1, state.gamesP1)
        assertEquals(0, state.pointsP1)
    }

    @Test
    fun `deuce requires two point lead to win game`() {
        var state = TennisMatchState()
        repeat(3) { state = engine.addPoint(state, winner = 1) }
        repeat(3) { state = engine.addPoint(state, winner = 2) } // 40-40 (deuce)
        state = engine.addPoint(state, winner = 1) // Ad-40
        assertEquals(0, state.gamesP1) // ещё не выиграл гейм при преимуществе в 1 очко
        state = engine.addPoint(state, winner = 1) // выигрывает гейм
        assertEquals(1, state.gamesP1)
    }

    @Test
    fun `six games with two game lead wins the set`() {
        var state = TennisMatchState()
        repeat(6) { state = winGame(state, winner = 1) }
        assertEquals(1, state.setsP1)
        assertEquals(0, state.gamesP1) // геймы сброшены для нового сета
    }

    @Test
    fun `six-six triggers a tiebreak`() {
        var state = TennisMatchState()
        repeat(6) { state = winGame(state, winner = 1) }
        // сбрасываем счёт по сетам искусственно, чтобы смоделировать 6-6 в текущем сете
        state = state.copy(setsP1 = 0, gamesP1 = 6, gamesP2 = 6)
        state = engine.addPoint(state, winner = 1)
        assertEquals(true, state.isTiebreak)
    }

    @Test
    fun `winning two sets wins the match`() {
        var state = TennisMatchState()
        repeat(2) { setIndex ->
            repeat(6) { state = winGame(state, winner = 1) }
        }
        assertEquals(1, state.winner)
        assertEquals(2, state.setsP1)
    }

    @Test
    fun `match with no winner yet returns null`() {
        var state = TennisMatchState()
        repeat(6) { state = winGame(state, winner = 1) } // выиграл только 1 сет
        assertNull(state.winner)
    }

    private fun winGame(state: TennisMatchState, winner: Int): TennisMatchState {
        var s = state
        repeat(4) { s = engine.addPoint(s, winner) }
        return s
    }
}
