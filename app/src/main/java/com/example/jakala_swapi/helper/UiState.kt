package com.example.jakala_swapi.helper

import com.example.jakala_swapi.data.model.Movie
import com.example.jakala_swapi.ui.MainViewModel

object UiState {
    fun defaultMoviesUiState(): MainViewModel.MoviesUiState =
        MainViewModel.MoviesUiState.Success(
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
}