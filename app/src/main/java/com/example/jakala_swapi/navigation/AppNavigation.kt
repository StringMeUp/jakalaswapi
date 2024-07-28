package com.example.jakala_swapi.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jakala_swapi.ui.screens.MoviesScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    bottomBarState: MutableState<Boolean>,
    padding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.BottomNav.Movies.route,
        route = Graph.BOTTOM
    ) {
        composable(NavigationItem.BottomNav.Movies.route) { backStackEntry ->
            MoviesScreen(padding = padding)
        }

        composable(NavigationItem.BottomNav.People.route) { backStackEntry ->
            MoviesScreen(padding = padding)
        }

        composable(NavigationItem.BottomNav.Planets.route) { backStackEntry ->
            MoviesScreen(padding = padding)
        }
    }
}