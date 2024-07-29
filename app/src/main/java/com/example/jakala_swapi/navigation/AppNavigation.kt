package com.example.jakala_swapi.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jakala_swapi.navigation.NavigationCompositionLocals.NavigationProvider
import com.example.jakala_swapi.navigation.NavigationCompositionLocals.viewModelScopedTo
import com.example.jakala_swapi.ui.screens.movies.MoviesDetailScreen
import com.example.jakala_swapi.ui.screens.movies.MoviesScreen
import com.example.jakala_swapi.ui.screens.movies.MoviesViewModel


@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    bottomBarState: MutableState<Boolean>,
    padding: PaddingValues,
) {
    NavigationProvider(navController) {
        NavHost(
            navController = navController,
            startDestination = NavigationItem.BottomNav.Movies.route,
            route = Graph.BOTTOM
        ) {

            composable(NavigationItem.BottomNav.Movies.route) { backStackEntry ->
                MoviesScreen(
                    padding = padding,
                    navigateToDetail = {
                        navController.navigate(
                            NavigationItem.BottomNav.Movies.MovieDetail.route
                        )
                    })
            }

            composable(NavigationItem.BottomNav.People.route) { backStackEntry ->
                MoviesScreen(padding = padding)
            }

            composable(NavigationItem.BottomNav.Planets.route) { backStackEntry ->
                MoviesScreen(padding = padding)
            }

            composable(route = NavigationItem.BottomNav.Movies.MovieDetail.route) { backStackEntry ->
                val sharedViewmodel: MoviesViewModel =
                    backStackEntry.viewModelScopedTo(route = NavigationItem.BottomNav.Movies.route)
                MoviesDetailScreen(
                    padding = padding,
                    viewModel = sharedViewmodel
                )
            }
        }
    }
}