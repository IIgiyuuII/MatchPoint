package com.tennis.matchpoint.domain.repository

import com.tennis.matchpoint.domain.model.MatchRules
import com.tennis.matchpoint.domain.model.StandingRow
import com.tennis.matchpoint.domain.model.TournamentFormat
import com.tennis.matchpoint.data.local.entity.MatchEntity
import com.tennis.matchpoint.data.local.entity.TournamentEntity
import com.tennis.matchpoint.data.local.entity.TournamentPlayerEntity
import kotlinx.coroutines.flow.Flow

interface TournamentRepository {
    fun observeOngoingAndUpcoming(): Flow<List<TournamentEntity>>
    fun observeFinished(): Flow<List<TournamentEntity>>
    fun observeTournament(id: Long): Flow<TournamentEntity?>
    fun observeTournamentsForPlayer(playerId: Long): Flow<List<TournamentEntity>>
    fun observeMatches(tournamentId: Long): Flow<List<MatchEntity>>
    fun observeMatch(matchId: Long): Flow<MatchEntity?>
    fun observeParticipants(tournamentId: Long): Flow<List<TournamentPlayerEntity>>

    suspend fun createTournament(
        name: String,
        format: TournamentFormat,
        rules: MatchRules,
        groupSize: Int,
        playerIds: List<Long>
    ): Long

    suspend fun startMatch(matchId: Long)
    suspend fun addPoint(matchId: Long, winnerIsPlayer1: Boolean)
    /** Отменяет последнее засчитанное очко (например, если админ нажал не на того игрока). */
    suspend fun removeLastPoint(matchId: Long)
    suspend fun computeGroupStandings(tournamentId: Long, groupIndex: Int): List<StandingRow>
    suspend fun computeOverallStandings(tournamentId: Long): List<StandingRow>
}
