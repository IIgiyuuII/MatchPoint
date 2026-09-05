package com.tennis.matchpoint.domain.scheduling

/**
 * Генератор сетки плей-офф на выбывание. Если число участников не является степенью двойки,
 * недостающие места заполняются "пустыми" слотами (bye) — сильнейшие по посеву проходят автоматически.
 */
object BracketGenerator {

    /** Первый раунд сетки. null во второй позиции пары означает бай (автоматический проход). */
    fun generateFirstRound(seededPlayerIds: List<Long>): List<Pair<Long?, Long?>> {
        if (seededPlayerIds.isEmpty()) return emptyList()
        val size = nextPowerOfTwo(seededPlayerIds.size)
        val padded: MutableList<Long?> = seededPlayerIds.toMutableList()
        while (padded.size < size) padded.add(null)

        val pairs = mutableListOf<Pair<Long?, Long?>>()
        for (i in 0 until size / 2) {
            pairs.add(padded[i] to padded[size - 1 - i])
        }
        return pairs
    }

    /** Формирует пары следующего раунда из списка победителей предыдущего раунда (по порядку). */
    fun nextRoundFromWinners(winners: List<Long>): List<Pair<Long, Long>> {
        val pairs = mutableListOf<Pair<Long, Long>>()
        var i = 0
        while (i + 1 < winners.size) {
            pairs.add(winners[i] to winners[i + 1])
            i += 2
        }
        return pairs
    }

    /** Человекочитаемое название раунда по количеству матчей в этом раунде. */
    fun roundLabel(matchesInRound: Int): String = when (matchesInRound) {
        1 -> "Финал"
        2 -> "Полуфинал"
        4 -> "Четвертьфинал"
        else -> "1/${matchesInRound * 2} финала"
    }

    private fun nextPowerOfTwo(n: Int): Int {
        var p = 1
        while (p < n) p *= 2
        return p.coerceAtLeast(2)
    }
}
