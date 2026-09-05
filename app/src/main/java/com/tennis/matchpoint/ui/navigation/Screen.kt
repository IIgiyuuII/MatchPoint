package com.tennis.matchpoint.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Players : Screen("players")
    data object Profile : Screen("profile")
    data object CreateTournament : Screen("create_tournament")
    data object TournamentDetail : Screen("tournament/{tournamentId}") {
        fun createRoute(id: Long) = "tournament/$id"
    }
    data object Match : Screen("match/{matchId}") {
        fun createRoute(id: Long) = "match/$id"
    }
    data object PlayerProfile : Screen("player/{playerId}") {
        fun createRoute(id: Long) = "player/$id"
    }
}
