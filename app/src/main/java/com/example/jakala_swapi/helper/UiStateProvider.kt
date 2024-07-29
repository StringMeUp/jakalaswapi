package com.example.jakala_swapi.helper

import com.example.jakala_swapi.data.model.Movie
import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.ui.MovieUiState

object UiStateProvider {
    fun defaultMoviesUiState(): MovieUiState =
        MovieUiState.Success(
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
            "A New Hope",
            1,
            "A long time ago in a galaxy far, far away…",
            "22.07.2020"
        )
    }
}