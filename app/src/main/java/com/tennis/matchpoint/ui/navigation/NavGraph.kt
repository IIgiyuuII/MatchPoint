package com.tennis.matchpoint.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.tennis.matchpoint.ui.createtournament.CreateTournamentScreen
import com.tennis.matchpoint.ui.home.HomeScreen
import com.tennis.matchpoint.ui.match.MatchScreen
import com.tennis.matchpoint.ui.playerprofile.PlayerProfileScreen
import com.tennis.matchpoint.ui.players.PlayersScreen
import com.tennis.matchpoint.ui.tournamentdetail.TournamentDetailScreen

private val bottomBarRoutes = setOf(Screen.Home.route, Screen.Players.route)

@Composable
fun MatchPointNavHost() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomAppBar(
                    actions = {
                        NavigationBarItem(
                            selected = currentRoute == Screen.Home.route,
                            onClick = { navController.navigateSingleTop(Screen.Home.route) },
                            icon = { Icon(Icons.Filled.Home, contentDescription = "Главная") },
                            label = { Text("Главная") }
                        )
                        NavigationBarItem(
                            selected = currentRoute == Screen.Players.route,
                            onClick = { navController.navigateSingleTop(Screen.Players.route) },
                            icon = { Icon(Icons.Filled.Groups, contentDescription = "Игроки") },
                            label = { Text("Игроки") }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { navController.navigate(Screen.CreateTournament.route) }) {
                            Icon(Icons.Filled.Add, contentDescription = "Создать турнир")
                        }
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onTournamentClick = { id ->
                    navController.navigate(Screen.TournamentDetail.createRoute(id))
                })
            }
            composable(Screen.Players.route) {
                PlayersScreen(onPlayerClick = { id ->
                    navController.navigate(Screen.PlayerProfile.createRoute(id))
                })
            }
            composable(Screen.CreateTournament.route) {
                CreateTournamentScreen(
                    onCreated = { id ->
                        navController.navigate(Screen.TournamentDetail.createRoute(id)) {
                            popUpTo(Screen.Home.route)
                        }
                    },
                    onCancel = { navController.popBackStack() }
                )
            }
            composable(
                Screen.TournamentDetail.route,
                arguments = listOf(navArgument("tournamentId") { type = NavType.LongType })
            ) {
                TournamentDetailScreen(onOpenMatch = { matchId ->
                    navController.navigate(Screen.Match.createRoute(matchId))
                })
            }
            composable(
                Screen.Match.route,
                arguments = listOf(navArgument("matchId") { type = NavType.LongType })
            ) {
                MatchScreen(onFinish = { navController.popBackStack() })
            }
            composable(
                Screen.PlayerProfile.route,
                arguments = listOf(navArgument("playerId") { type = NavType.LongType })
            ) {
                PlayerProfileScreen(onTournamentClick = { id ->
                    navController.navigate(Screen.TournamentDetail.createRoute(id))
                })
            }
        }
    }
}

private fun NavHostController.navigateSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.startDestinationId) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
