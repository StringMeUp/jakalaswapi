package com.example.jakala_swapi.helper

import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.data.model.PeopleResponse
import com.example.jakala_swapi.data.model.PlanetResponse
import com.example.jakala_swapi.ui.MovieUiState

object UiStateProvider {
    fun defaultMoviesUiState(): MovieUiState = MovieUiState.Success(
        listOf(
            MovieResponse.Movie(
                "Dark Side",
                1,
                "Here we go!",
                "Unknown",
                "Samir",
                "21.11.1988",
                ""
            )
        )
    )

    fun defaultMovieDetail(): MovieDetail {
        return MovieDetail(
            "A New Hope", 1, "A long time ago in a galaxy far, far away…", "22.07.2020"
        )
    }

    fun defaultPeople(): List<PeopleResponse.PeopleDetail> {
        return listOf(
            PeopleResponse.PeopleDetail(
                "Luke",
                "1.78",
                "Black",
                "Blue",
                "Blur",
                "1988",
                "Erath",
            ), PeopleResponse.PeopleDetail(
                "Sam",
                "2.00",
                "Black",
                "Blue",
                "Blur",
                "1988",
                "Somewhere",
            )
        )
    }

    fun defaultPlanet(): PlanetResponse.Planet {
        return PlanetResponse.Planet(
            "Earth",
            "1",
            "3",
            "99887",
            "Friendly",
            "Yes",
            "Terrain",
            "4",
            "666",
        )
    }
}