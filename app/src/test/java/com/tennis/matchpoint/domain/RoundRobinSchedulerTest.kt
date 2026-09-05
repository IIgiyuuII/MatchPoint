package com.tennis.matchpoint.domain

import com.tennis.matchpoint.domain.scheduling.RoundRobinScheduler
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RoundRobinSchedulerTest {

    @Test
    fun `each player meets every other exactly once - even count`() {
        val players = listOf(1L, 2L, 3L, 4L)
        val pairs = RoundRobinScheduler.generateAllPairs(players)

        assertEquals(6, pairs.size) // C(4,2) = 6

        val expectedPairs = players.flatMapIndexed { i, a ->
            players.drop(i + 1).map { b -> setOf(a, b) }
        }.toSet()

        val actualPairs = pairs.map { setOf(it.first, it.second) }.toSet()
        assertEquals(expectedPairs, actualPairs)
    }

    @Test
    fun `each player meets every other exactly once - odd count with bye`() {
        val players = listOf(1L, 2L, 3L, 4L, 5L)
        val pairs = RoundRobinScheduler.generateAllPairs(players)

        assertEquals(10, pairs.size) // C(5,2) = 10

        players.forEach { p ->
            val matchesForPlayer = pairs.count { it.first == p || it.second == p }
            assertEquals("Player $p should play exactly 4 matches", 4, matchesForPlayer)
        }
    }

    @Test
    fun `no round contains a repeated player`() {
        val players = (1L..7L).toList()
        val rounds = RoundRobinScheduler.generateRounds(players)
        rounds.forEach { round ->
            val playersInRound = round.flatMap { listOf(it.first, it.second) }
            assertEquals(playersInRound.size, playersInRound.toSet().size)
        }
    }

    @Test
    fun `too few players produces no matches`() {
        assertTrue(RoundRobinScheduler.generateAllPairs(listOf(1L)).isEmpty())
        assertTrue(RoundRobinScheduler.generateAllPairs(emptyList()).isEmpty())
    }
}
