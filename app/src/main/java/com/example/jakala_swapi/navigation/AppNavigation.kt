package com.example.jakala_swapi.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jakala_swapi.helper.setBottomBar
import com.example.jakala_swapi.navigation.NavigationCompositionLocals.NavigationProvider
import com.example.jakala_swapi.ui.screens.movies.MovieDetailViewModel
import com.example.jakala_swapi.ui.screens.movies.MoviesDetailScreen
import com.example.jakala_swapi.ui.screens.movies.MoviesScreen
import com.example.jakala_swapi.ui.screens.people.PeopleScreen
import com.example.jakala_swapi.ui.screens.search.SearchScreen


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

            composable(NavigationItem.BottomNav.Movies.route) { _ ->
                bottomBarState.setBottomBar(true)
                MoviesScreen(
                    padding = padding,
                    navigateToDetail = { id, title ->
                        navController.navigate(
                            NavigationItem.BottomNav.Movies.MovieDetail.createMovieDetailRoute(
                                id = id,
                                title = title
                            )
                        )
                    })
            }

            composable(NavigationItem.BottomNav.People.route) { _ ->
                PeopleScreen(padding = padding)
            }

            composable(NavigationItem.BottomNav.Planets.route) { _ ->
                SearchScreen(padding = padding)
            }

            composable(route = NavigationItem.BottomNav.Movies.MovieDetail.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.StringType },
                    navArgument("title") { type = NavType.StringType }
                )) { backStackEntry ->
                bottomBarState.setBottomBar(false)
                val id = backStackEntry.arguments?.getString("id") ?: ""
                val title = backStackEntry.arguments?.getString("title") ?: ""
                MoviesDetailScreen(
                    padding = padding,
                    viewModel = hiltViewModel<MovieDetailViewModel, MovieDetailViewModel.VmAssistedFactory> { factory ->
                        factory.create(id, title)
                    }
                )
            }
        }
    }
}