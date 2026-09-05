package com.tennis.matchpoint.domain.usecase

import com.tennis.matchpoint.domain.model.Stage
import com.tennis.matchpoint.domain.scheduling.BracketGenerator
import com.tennis.matchpoint.domain.scheduling.GroupSplitter
import com.tennis.matchpoint.domain.scheduling.RoundRobinScheduler

/**
 * Чистая (без побочных эффектов и без Room) бизнес-логика "что дальше" для турнира:
 * какие матчи создать в начале, и какие матчи создать, когда стадия закончилась.
 * Работает с обычными Long-идентификаторами, поэтому легко покрывается unit-тестами.
 */
object TournamentProgressPlanner {

    /** План одного матча, который нужно сохранить в БД. */
    data class MatchPlan(
        val stage: Stage,
        val roundLabel: String,
        val groupIndex: Int,
        val orderIndex: Int,
        val player1Id: Long?,
        val player2Id: Long?,
        /** Если не null — матч сразу завершён автоматически (бай, соперника нет). */
        val autoWinnerId: Long? = null
    )

    /** Группы игроков турнира формата GROUPS_PLAYOFF/ROUND_ROBIN. Для ROUND_ROBIN — одна "группа". */
    fun buildGroups(playerIds: List<Long>, groupSize: Int, groupsEnabled: Boolean): List<List<Long>> =
        if (groupsEnabled) GroupSplitter.splitIntoGroups(playerIds, groupSize) else listOf(playerIds)

    /** Начальные матчи для круговых групп (используется и для ROUND_ROBIN, и для стадии групп GROUPS_PLAYOFF). */
    fun planGroupStageMatches(groups: List<List<Long>>): List<MatchPlan> {
        val plans = mutableListOf<MatchPlan>()
        var order = 0
        groups.forEachIndexed { groupIndex, group ->
            val roundLabel = if (groups.size > 1) "Группа ${'A' + groupIndex}" else "Круговой этап"
            RoundRobinScheduler.generateAllPairs(group).forEach { (p1, p2) ->
                plans.add(
                    MatchPlan(
                        stage = Stage.GROUP,
                        roundLabel = roundLabel,
                        groupIndex = groupIndex,
                        orderIndex = order++,
                        player1Id = p1,
                        player2Id = p2
                    )
                )
            }
        }
        return plans
    }

    /** Начальные матчи для чистого плей-офф (SINGLE_ELIMINATION) по посеянному списку игроков. */
    fun planSingleEliminationFirstRound(seededPlayerIds: List<Long>, startOrderIndex: Int = 0): List<MatchPlan> {
        val firstRound = BracketGenerator.generateFirstRound(seededPlayerIds)
        val label = BracketGenerator.roundLabel(firstRound.size)
        return firstRound.mapIndexed { i, (p1, p2) ->
            MatchPlan(
                stage = Stage.PLAYOFF,
                roundLabel = label,
                groupIndex = -1,
                orderIndex = startOrderIndex + i,
                player1Id = p1,
                player2Id = p2,
                autoWinnerId = if (p2 == null) p1 else if (p1 == null) p2 else null
            )
        }
    }

    /** Следующий раунд плей-офф из победителей предыдущего (в порядке их матчей). */
    fun planNextPlayoffRound(previousRoundWinnersInOrder: List<Long>, startOrderIndex: Int): List<MatchPlan> {
        val pairs = BracketGenerator.nextRoundFromWinners(previousRoundWinnersInOrder)
        val label = BracketGenerator.roundLabel(pairs.size)
        return pairs.mapIndexed { i, (p1, p2) ->
            MatchPlan(
                stage = Stage.PLAYOFF,
                roundLabel = label,
                groupIndex = -1,
                orderIndex = startOrderIndex + i,
                player1Id = p1,
                player2Id = p2
            )
        }
    }
}
