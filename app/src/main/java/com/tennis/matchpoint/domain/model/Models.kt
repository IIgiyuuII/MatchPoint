package com.tennis.matchpoint.domain.model

/** Формат турнира. */
enum class TournamentFormat {
    ROUND_ROBIN,       // каждый играет с каждым, победитель — по итоговой таблице
    GROUPS_PLAYOFF,    // группы (круговая система) + плей-офф на выбывание
    SINGLE_ELIMINATION // сразу плей-офф на выбывание
}

enum class TournamentStatus { UPCOMING, ONGOING, FINISHED }

enum class MatchStatus { SCHEDULED, IN_PROGRESS, FINISHED, BYE }

enum class Stage { GROUP, PLAYOFF }

/** Модель игрока для доменного/UI слоя. */
data class Player(
    val id: Long,
    val name: String,
    val colorSeed: Int,
    val createdAt: Long
)

/** Настройки формата счёта конкретного турнира. */
data class MatchRules(
    val setsToWin: Int = 2,   // 2 = до двух побед в сетах (best of 3), 1 = один сет
    val gamesPerSet: Int = 6  // сколько геймов нужно выиграть, чтобы взять сет (обычно 4 или 6)
)

/** Строка турнирной таблицы группы/общего рейтинга. */
data class StandingRow(
    val playerId: Long,
    val playerName: String,
    val played: Int,
    val wins: Int,
    val losses: Int,
    val setsWon: Int,
    val setsLost: Int,
    val gamesWon: Int,
    val gamesLost: Int
) {
    val points: Int get() = wins * 2 // 2 очка за победу, 0 за поражение — просто и наглядно
}

/** Агрегированная статистика игрока по всем турнирам — для профиля. */
data class PlayerStats(
    val tournamentsPlayed: Int,
    val tournamentsWon: Int,
    val matchesPlayed: Int,
    val matchesWon: Int,
    val setsWon: Int,
    val setsLost: Int,
    val gamesWon: Int,
    val gamesLost: Int
) {
    val winRate: Float get() = if (matchesPlayed == 0) 0f else matchesWon.toFloat() / matchesPlayed
}
