package com.tennis.matchpoint.data.repository

import com.tennis.matchpoint.data.local.dao.MatchDao
import com.tennis.matchpoint.data.local.dao.PlayerDao
import com.tennis.matchpoint.data.local.dao.TournamentDao
import com.tennis.matchpoint.data.local.dao.TournamentPlayerDao
import com.tennis.matchpoint.data.local.entity.MatchEntity
import com.tennis.matchpoint.data.local.entity.TournamentEntity
import com.tennis.matchpoint.data.local.entity.TournamentPlayerEntity
import com.tennis.matchpoint.domain.model.MatchRules
import com.tennis.matchpoint.domain.model.Stage
import com.tennis.matchpoint.domain.model.StandingRow
import com.tennis.matchpoint.domain.model.TournamentFormat
import com.tennis.matchpoint.domain.repository.TournamentRepository
import com.tennis.matchpoint.domain.scheduling.GroupSplitter
import com.tennis.matchpoint.domain.scoring.TennisScoreEngine
import com.tennis.matchpoint.domain.usecase.FinishedMatchResult
import com.tennis.matchpoint.domain.usecase.StandingsCalculator
import com.tennis.matchpoint.domain.usecase.TournamentProgressPlanner
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TournamentRepositoryImpl @Inject constructor(
    private val tournamentDao: TournamentDao,
    private val matchDao: MatchDao,
    private val tournamentPlayerDao: TournamentPlayerDao,
    private val playerDao: PlayerDao
) : TournamentRepository {

    override fun observeOngoingAndUpcoming(): Flow<List<TournamentEntity>> =
        tournamentDao.observeOngoingAndUpcoming()

    override fun observeFinished(): Flow<List<TournamentEntity>> =
        tournamentDao.observeFinished()

    override fun observeTournament(id: Long): Flow<TournamentEntity?> =
        tournamentDao.observeById(id)

    override fun observeTournamentsForPlayer(playerId: Long): Flow<List<TournamentEntity>> =
        tournamentDao.observeTournamentsForPlayer(playerId)

    override fun observeMatches(tournamentId: Long): Flow<List<MatchEntity>> =
        matchDao.observeForTournament(tournamentId)

    override fun observeMatch(matchId: Long): Flow<MatchEntity?> =
        matchDao.observeById(matchId)

    override fun observeParticipants(tournamentId: Long): Flow<List<TournamentPlayerEntity>> =
        tournamentPlayerDao.observeForTournament(tournamentId)

    override suspend fun createTournament(
        name: String,
        format: TournamentFormat,
        rules: MatchRules,
        groupSize: Int,
        playerIds: List<Long>
    ): Long {
        val tournamentId = tournamentDao.insert(
            TournamentEntity(
                name = name,
                format = format.name,
                status = "ONGOING",
                setsToWin = rules.setsToWin,
                gamesPerSet = rules.gamesPerSet,
                groupSize = groupSize
            )
        )

        val shuffled = playerIds.shuffled()

        when (format) {
            TournamentFormat.ROUND_ROBIN -> {
                val groups = listOf(shuffled)
                saveParticipants(tournamentId, groups)
                val plans = TournamentProgressPlanner.planGroupStageMatches(groups)
                insertPlans(tournamentId, plans)
            }
            TournamentFormat.GROUPS_PLAYOFF -> {
                val groups = GroupSplitter.splitIntoGroups(shuffled, groupSize)
                saveParticipants(tournamentId, groups)
                val plans = TournamentProgressPlanner.planGroupStageMatches(groups)
                insertPlans(tournamentId, plans)
            }
            TournamentFormat.SINGLE_ELIMINATION -> {
                saveParticipants(tournamentId, listOf(shuffled))
                val plans = TournamentProgressPlanner.planSingleEliminationFirstRound(shuffled)
                insertPlans(tournamentId, plans, roundNumber = 1)
            }
        }

        advanceTournament(tournamentId)
        return tournamentId
    }

    private suspend fun saveParticipants(tournamentId: Long, groups: List<List<Long>>) {
        val entries = mutableListOf<TournamentPlayerEntity>()
        groups.forEachIndexed { groupIndex, group ->
            group.forEachIndexed { seed, playerId ->
                entries.add(
                    TournamentPlayerEntity(
                        tournamentId = tournamentId,
                        playerId = playerId,
                        groupIndex = groupIndex,
                        seed = seed
                    )
                )
            }
        }
        tournamentPlayerDao.insertAll(entries)
    }

    private suspend fun insertPlans(
        tournamentId: Long,
        plans: List<TournamentProgressPlanner.MatchPlan>,
        roundNumber: Int = 0
    ) {
        val now = System.currentTimeMillis()
        val entities = plans.map { plan ->
            MatchEntity(
                tournamentId = tournamentId,
                stage = plan.stage.name,
                roundLabel = plan.roundLabel,
                groupIndex = plan.groupIndex,
                orderIndex = plan.orderIndex,
                roundNumber = roundNumber,
                player1Id = plan.player1Id,
                player2Id = plan.player2Id,
                status = if (plan.autoWinnerId != null) "FINISHED" else "SCHEDULED",
                winnerId = plan.autoWinnerId,
                finishedAt = if (plan.autoWinnerId != null) now else null
            )
        }
        matchDao.insertAll(entities)
    }

    override suspend fun startMatch(matchId: Long) {
        val match = matchDao.getById(matchId) ?: return
        if (match.status == "SCHEDULED") {
            matchDao.update(match.copy(status = "IN_PROGRESS", startedAt = System.currentTimeMillis()))
        }
    }

    override suspend fun addPoint(matchId: Long, winnerIsPlayer1: Boolean) {
        val match = matchDao.getById(matchId) ?: return
        if (match.status == "FINISHED") return

        val history = parsePointsHistory(match.pointsHistory) + if (winnerIsPlayer1) 1 else 2
        applyPointsHistory(match, history)
    }

    override suspend fun removeLastPoint(matchId: Long) {
        val match = matchDao.getById(matchId) ?: return
        val history = parsePointsHistory(match.pointsHistory)
        if (history.isEmpty()) return
        applyPointsHistory(match, history.dropLast(1))
    }

    /** Пересчитывает счёт матча с нуля по всей истории очков и сохраняет результат.
     * Используется и для добавления, и для отмены очка — единая логика без дублирования. */
    private suspend fun applyPointsHistory(match: MatchEntity, history: List<Int>) {
        val tournament = tournamentDao.getById(match.tournamentId) ?: return
        val engine = TennisScoreEngine(setsToWin = tournament.setsToWin, gamesToWinSet = tournament.gamesPerSet)

        val updated = engine.replay(history)
        val isFinished = updated.winner != null
        val winnerId = if (updated.winner == 1) match.player1Id else if (updated.winner == 2) match.player2Id else null

        // Если матч уже был завершён, а после отмены очка перестал быть завершённым —
        // статус нужно вернуть в IN_PROGRESS, чтобы админ мог продолжить его вести.
        val newStatus = if (isFinished) "FINISHED" else "IN_PROGRESS"

        matchDao.update(
            match.copy(
                pointsHistory = serializePointsHistory(history),
                pointsP1 = updated.pointsP1, pointsP2 = updated.pointsP2,
                gamesP1 = updated.gamesP1, gamesP2 = updated.gamesP2,
                setsP1 = updated.setsP1, setsP2 = updated.setsP2,
                setsHistory = serializeSetsHistory(updated.setsHistory),
                isTiebreak = updated.isTiebreak,
                tiebreakP1 = updated.tiebreakP1, tiebreakP2 = updated.tiebreakP2,
                status = newStatus,
                winnerId = winnerId,
                finishedAt = if (isFinished) System.currentTimeMillis() else null
            )
        )

        if (isFinished) advanceTournament(match.tournamentId)
    }

    private fun parsePointsHistory(raw: String): List<Int> =
        raw.split(",").filter { it.isNotBlank() }.mapNotNull { it.toIntOrNull() }

    private fun serializePointsHistory(history: List<Int>): String = history.joinToString(",")

    /** Проверяет, не завершилась ли стадия/турнир, и при необходимости создаёт следующие матчи. */
    private suspend fun advanceTournament(tournamentId: Long) {
        val tournament = tournamentDao.getById(tournamentId) ?: return
        if (tournament.status == "FINISHED") return
        val format = TournamentFormat.valueOf(tournament.format)
        val allMatches = matchDao.getForTournament(tournamentId)

        when (format) {
            TournamentFormat.ROUND_ROBIN -> {
                if (allMatches.isNotEmpty() && allMatches.all { it.status == "FINISHED" }) {
                    finishTournamentByStandings(tournament)
                }
            }
            TournamentFormat.SINGLE_ELIMINATION -> {
                advancePlayoff(tournament, allMatches)
            }
            TournamentFormat.GROUPS_PLAYOFF -> {
                val playoffMatches = allMatches.filter { it.stage == Stage.PLAYOFF.name }
                if (playoffMatches.isEmpty()) {
                    val groupMatches = allMatches.filter { it.stage == Stage.GROUP.name }
                    if (groupMatches.isNotEmpty() && groupMatches.all { it.status == "FINISHED" }) {
                        val qualifiers = buildPlayoffSeedFromGroups(tournamentId, groupMatches)
                        val startOrder = (allMatches.maxOfOrNull { it.orderIndex } ?: -1) + 1
                        val plans = TournamentProgressPlanner.planSingleEliminationFirstRound(qualifiers, startOrder)
                        insertPlans(tournamentId, plans, roundNumber = 1)
                        advanceTournament(tournamentId) // на случай мгновенных "баев" в первом раунде плей-офф
                    }
                } else {
                    advancePlayoff(tournament, allMatches)
                }
            }
        }
    }

    private suspend fun advancePlayoff(tournament: TournamentEntity, allMatches: List<MatchEntity>) {
        val playoffMatches = allMatches.filter { it.stage == Stage.PLAYOFF.name }
        if (playoffMatches.isEmpty()) return
        val currentRound = playoffMatches.maxOf { it.roundNumber }
        val roundMatches = playoffMatches.filter { it.roundNumber == currentRound }.sortedBy { it.orderIndex }

        if (roundMatches.any { it.status != "FINISHED" }) return // раунд ещё не завершён

        if (roundMatches.size == 1) {
            val winner = roundMatches.first().winnerId
            tournamentDao.update(tournament.copy(status = "FINISHED", winnerPlayerId = winner))
            return
        }

        val winners = roundMatches.map { requireNotNull(it.winnerId) }
        val startOrder = (allMatches.maxOfOrNull { it.orderIndex } ?: -1) + 1
        val plans = TournamentProgressPlanner.planNextPlayoffRound(winners, startOrder)
        insertPlans(tournament.id, plans, roundNumber = currentRound + 1)
    }

    private suspend fun buildPlayoffSeedFromGroups(tournamentId: Long, groupMatches: List<MatchEntity>): List<Long> {
        val participants = tournamentPlayerDao.getForTournament(tournamentId)
        val groups = participants.groupBy { it.groupIndex }
        val qualifiersPerGroup = GroupSplitter.qualifiersPerGroup(participants.size / groups.size.coerceAtLeast(1))

        val standingsByGroup = groups.map { (groupIndex, entries) ->
            groupIndex to computeStandingsFor(entries.map { it.playerId }, groupMatches.filter { it.groupIndex == groupIndex })
        }.toMap()

        val seeded = mutableListOf<Long>()
        var rank = 0
        while (seeded.size < groups.size * qualifiersPerGroup) {
            var addedAny = false
            for ((_, standings) in standingsByGroup) {
                if (rank < standings.size && rank < qualifiersPerGroup) {
                    seeded.add(standings[rank].playerId)
                    addedAny = true
                }
            }
            rank++
            if (!addedAny) break
        }
        return seeded
    }

    override suspend fun computeGroupStandings(tournamentId: Long, groupIndex: Int): List<StandingRow> {
        val participants = tournamentPlayerDao.getForTournament(tournamentId).filter { it.groupIndex == groupIndex }
        val matches = matchDao.getForTournament(tournamentId).filter { it.groupIndex == groupIndex && it.stage == Stage.GROUP.name }
        return computeStandingsFor(participants.map { it.playerId }, matches)
    }

    override suspend fun computeOverallStandings(tournamentId: Long): List<StandingRow> {
        val participants = tournamentPlayerDao.getForTournament(tournamentId)
        val matches = matchDao.getForTournament(tournamentId).filter { it.stage == Stage.GROUP.name }
        return computeStandingsFor(participants.map { it.playerId }, matches)
    }

    private suspend fun computeStandingsFor(playerIds: List<Long>, matches: List<MatchEntity>): List<StandingRow> {
        val names = playerIds.associateWith { id -> playerDao.getById(id)?.name ?: "?" }
        val finished = matches.filter { it.status == "FINISHED" && it.winnerId != null && it.player1Id != null && it.player2Id != null }
            .map {
                FinishedMatchResult(
                    player1Id = it.player1Id!!,
                    player2Id = it.player2Id!!,
                    winnerId = it.winnerId!!,
                    setsP1 = it.setsP1,
                    setsP2 = it.setsP2,
                    setsHistory = parseSetsHistory(it.setsHistory)
                )
            }
        return StandingsCalculator.calculate(names, finished)
    }

    private suspend fun finishTournamentByStandings(tournament: TournamentEntity) {
        val standings = computeOverallStandings(tournament.id)
        val winner = standings.firstOrNull()?.playerId
        tournamentDao.update(tournament.copy(status = "FINISHED", winnerPlayerId = winner))
    }

    private fun parseSetsHistory(raw: String): List<Pair<Int, Int>> =
        raw.split(";").filter { it.isNotBlank() }.mapNotNull { part ->
            val nums = part.split("-")
            if (nums.size == 2) (nums[0].toIntOrNull() ?: 0) to (nums[1].toIntOrNull() ?: 0) else null
        }

    private fun serializeSetsHistory(sets: List<Pair<Int, Int>>): String =
        sets.joinToString(";") { "${it.first}-${it.second}" }
}
