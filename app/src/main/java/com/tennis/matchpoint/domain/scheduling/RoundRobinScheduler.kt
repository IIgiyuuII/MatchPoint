package com.tennis.matchpoint.domain.scheduling

/**
 * Классический "circle method" для круговой системы: каждый играет с каждым ровно один раз.
 * Если игроков нечётное количество — добавляется фиктивный "BYE" (пропуск тура) для одного игрока за раунд.
 */
object RoundRobinScheduler {

    const val BYE_ID = -1L

    /** Возвращает список раундов, каждый раунд — список пар (playerId1 to playerId2). */
    fun generateRounds(playerIds: List<Long>): List<List<Pair<Long, Long>>> {
        if (playerIds.size < 2) return emptyList()

        val arr = playerIds.toMutableList()
        if (arr.size % 2 != 0) arr.add(BYE_ID)

        val n = arr.size
        val rounds = mutableListOf<List<Pair<Long, Long>>>()
        val current = arr.toMutableList()

        repeat(n - 1) {
            val roundPairs = mutableListOf<Pair<Long, Long>>()
            for (i in 0 until n / 2) {
                val a = current[i]
                val b = current[n - 1 - i]
                if (a != BYE_ID && b != BYE_ID) {
                    roundPairs.add(a to b)
                }
            }
            rounds.add(roundPairs)

            // Ротация: первый элемент фиксирован, остальные сдвигаются по кругу.
            val fixed = current[0]
            val rest = current.subList(1, n).toMutableList()
            rest.add(0, rest.removeAt(rest.size - 1))
            current.clear()
            current.add(fixed)
            current.addAll(rest)
        }
        return rounds
    }

    /** Плоский список всех пар турнира (без разбивки на раунды) — удобно для сохранения в БД. */
    fun generateAllPairs(playerIds: List<Long>): List<Pair<Long, Long>> =
        generateRounds(playerIds).flatten()
}
