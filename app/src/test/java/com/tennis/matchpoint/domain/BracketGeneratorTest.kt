package com.tennis.matchpoint.domain

import com.tennis.matchpoint.domain.scheduling.BracketGenerator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BracketGeneratorTest {

    @Test
    fun `power of two players produces no byes`() {
        val players = listOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L)
        val firstRound = BracketGenerator.generateFirstRound(players)
        assertEquals(4, firstRound.size)
        firstRound.forEach { (a, b) -> assertEquals(false, a == null || b == null) }
    }

    @Test
    fun `non power of two players gets padded with byes`() {
        val players = listOf(1L, 2L, 3L, 4L, 5L) // -> next pow2 = 8
        val firstRound = BracketGenerator.generateFirstRound(players)
        assertEquals(4, firstRound.size)
        val byeCount = firstRound.count { it.first == null || it.second == null }
        assertEquals(3, byeCount) // 8 - 5 = 3 byes
    }

    @Test
    fun `next round pairs winners sequentially`() {
        val winners = listOf(10L, 20L, 30L, 40L)
        val nextRound = BracketGenerator.nextRoundFromWinners(winners)
        assertEquals(listOf(10L to 20L, 30L to 40L), nextRound)
    }
}
