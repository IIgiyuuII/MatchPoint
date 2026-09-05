package com.tennis.matchpoint.data.repository

import com.tennis.matchpoint.data.local.dao.MatchDao
import com.tennis.matchpoint.data.local.dao.PlayerDao
import com.tennis.matchpoint.data.local.dao.TournamentDao
import com.tennis.matchpoint.data.local.entity.PlayerEntity
import com.tennis.matchpoint.domain.model.Player
import com.tennis.matchpoint.domain.model.PlayerStats
import com.tennis.matchpoint.domain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.random.Random

class PlayerRepositoryImpl @Inject constructor(
    private val playerDao: PlayerDao,
    private val matchDao: MatchDao,
    private val tournamentDao: TournamentDao
) : PlayerRepository {

    override fun observeAllPlayers(): Flow<List<Player>> =
        playerDao.observeAll().map { list -> list.map { it.toDomain() } }

    override fun observePlayer(playerId: Long): Flow<Player?> =
        playerDao.observeById(playerId).map { it?.toDomain() }

    override suspend fun searchPlayers(query: String): List<Player> =
        playerDao.search(query).map { it.toDomain() }

    override suspend fun getOrCreatePlayer(name: String): Player {
        val trimmed = name.trim()
        val existing = playerDao.findByName(trimmed)
        if (existing != null) return existing.toDomain()
        val entity = PlayerEntity(name = trimmed, colorSeed = Random.nextInt(0, 360))
        val id = playerDao.insert(entity)
        return entity.copy(id = id).toDomain()
    }

    override suspend fun getPlayerStats(playerId: Long): PlayerStats {
        val finished = matchDao.getFinishedForPlayer(playerId)
        var matchesWon = 0
        var setsWon = 0
        var setsLost = 0
        var gamesWon = 0
        var gamesLost = 0

        finished.forEach { m ->
            val isP1 = m.player1Id == playerId
            val mySets = if (isP1) m.setsP1 else m.setsP2
            val theirSets = if (isP1) m.setsP2 else m.setsP1
            setsWon += mySets
            setsLost += theirSets
            if (m.winnerId == playerId) matchesWon++

            m.setsHistory.split(";").filter { it.isNotBlank() }.forEach { setScore ->
                val parts = setScore.split("-")
                if (parts.size == 2) {
                    val g1 = parts[0].toIntOrNull() ?: 0
                    val g2 = parts[1].toIntOrNull() ?: 0
                    gamesWon += if (isP1) g1 else g2
                    gamesLost += if (isP1) g2 else g1
                }
            }
        }

        val tournamentIds = finished.map { it.tournamentId }.toSet()
        val tournamentsWon = tournamentIds.count { tid ->
            tournamentDao.getById(tid)?.winnerPlayerId == playerId
        }

        return PlayerStats(
            tournamentsPlayed = tournamentIds.size,
            tournamentsWon = tournamentsWon,
            matchesPlayed = finished.size,
            matchesWon = matchesWon,
            setsWon = setsWon,
            setsLost = setsLost,
            gamesWon = gamesWon,
            gamesLost = gamesLost
        )
    }

    private fun PlayerEntity.toDomain() = Player(id, name, colorSeed, createdAt)
}
