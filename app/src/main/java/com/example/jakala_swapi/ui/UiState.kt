package com.example.jakala_swapi.ui

import com.example.jakala_swapi.data.model.Movie
import com.example.jakala_swapi.data.model.MovieDetail
import javax.annotation.concurrent.Immutable

@Immutable
sealed interface UiState {
    data object Error : UiState
    data object Loading : UiState
}

@Immutable
sealed interface MovieUiState : UiState {
    data class Success(val movies: List<Movie>) : MovieUiState
}

@Immutable
sealed interface MovieDetailUiState : UiState {
    data class SuccessDetail(val movie: MovieDetail) : MovieDetailUiState
}