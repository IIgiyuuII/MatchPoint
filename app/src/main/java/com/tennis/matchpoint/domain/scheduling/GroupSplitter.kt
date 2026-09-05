package com.tennis.matchpoint.domain.scheduling

/** Разбивает игроков турнира на группы примерно равного размера (для формата "группы + плей-офф"). */
object GroupSplitter {

    fun splitIntoGroups(playerIds: List<Long>, targetGroupSize: Int = 4): List<List<Long>> {
        if (playerIds.isEmpty()) return emptyList()
        val shuffled = playerIds.shuffled()
        val groupCount = kotlin.math.ceil(shuffled.size / targetGroupSize.toDouble())
            .toInt()
            .coerceAtLeast(1)
        val groups = List(groupCount) { mutableListOf<Long>() }
        shuffled.forEachIndexed { index, id -> groups[index % groupCount].add(id) }
        return groups
    }

    /** Сколько игроков из каждой группы выходит в плей-офф. */
    fun qualifiersPerGroup(groupSize: Int): Int = if (groupSize <= 3) 1 else 2
}
