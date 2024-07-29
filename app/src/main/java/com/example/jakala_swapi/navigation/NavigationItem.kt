package com.example.jakala_swapi.navigation

import com.example.jakala_swapi.R
import com.example.jakala_swapi.navigation.NavigationItem.BottomNav.bottomNavDestinations


sealed class NavigationItem(
    val route: String,
    val icon: Int = R.drawable.ic_default,
    val selectedIcon: Int = R.drawable.ic_default_selected,
    val name: Int,
) {
    data object BottomNav : NavigationItem(
        route = "Main",
        name = R.string.bottom_nav_main
    ) {
        data object Movies : NavigationItem(
            route = "Movies",
            icon = R.drawable.ic_movie,
            selectedIcon = R.drawable.ic_movie_selected,
            name = R.string.bottom_nav_movie
        ) {
            data object MovieDetail : NavigationItem(
                route = "MovieDetail",
                name = R.string.bottom_nav_movie_detail
            )
        }

        data object People : NavigationItem(
            route = "People",
            icon = R.drawable.ic_people,
            selectedIcon = R.drawable.ic_people_selected,
            name = R.string.bottom_nav_people
        ) {
            data object PeopleDetail : NavigationItem(
                route = "PeopleDetail/{id}",
                name = R.string.bottom_nav_people_detail
            )

        }

        data object Planets : NavigationItem(
            route = "Planets",
            icon = R.drawable.ic_planet,
            selectedIcon = R.drawable.ic_planet_selected,
            name = R.string.bottom_nav_planets
        ) {
            data object PlanetsDetail : NavigationItem(
                route = "Planets/{id}",
                name = R.string.bottom_nav_planets_detail
            )
        }

        fun bottomNavDestinations() = listOf(
            Movies,
            People,
            Planets,
        )
    }

    companion object {
        private fun all() = bottomNavDestinations()
        fun findNavItem(route: String?): NavigationItem {
            return all().find { it.route == route } ?: BottomNav
        }
    }
}