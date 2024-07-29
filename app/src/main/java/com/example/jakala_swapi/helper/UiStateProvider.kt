package com.example.jakala_swapi.helper

import com.example.jakala_swapi.data.model.Movie
import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.model.PeopleDetail
import com.example.jakala_swapi.ui.MovieUiState

object UiStateProvider {
    fun defaultMoviesUiState(): MovieUiState = MovieUiState.Success(
        listOf(
            Movie(
                "Dark Side",
                1,
                "Here we go!",
                "Unknown",
                "Samir",
                "21.11.1988",
                listOf(),
                listOf(),
                listOf(),
                listOf(),
                listOf(),
                null,
                null,
                null
            )
        )
    )

    fun defaultMovieDetail(): MovieDetail {
        return MovieDetail(
            "A New Hope", 1, "A long time ago in a galaxy far, far away…", "22.07.2020"
        )
    }

    fun defaultPeople(): List<PeopleDetail> {
        return listOf(
            PeopleDetail(
                "Luke",
                "1.78",
                "Black",
                "Blue",
                "Blur",
                "1988",
                "Erath",
                listOf("Movie 1", "Movie 2", "Movie 3")
            ), PeopleDetail(
                "Sam",
                "2.00",
                "Black",
                "Blue",
                "Blur",
                "1988",
                "Somewhere",
                listOf("Movie 3", "Movie 4", "Movie 5")
            )
        )
    }
}