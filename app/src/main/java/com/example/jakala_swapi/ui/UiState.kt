package com.example.jakala_swapi.ui

import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.data.model.PeopleResponse
import com.example.jakala_swapi.data.model.PlanetResponse
import javax.annotation.concurrent.Immutable

/** Both data classes and sealed classes have their place when representing state in vms, depending on the usecase.*/
@Immutable
sealed interface UiState {
    data object Error : UiState
    data object Loading : UiState
}

@Immutable
sealed interface MovieUiState : UiState {
    data class Success(val movies: List<MovieResponse.Movie>) : MovieUiState
}

@Immutable
sealed interface MovieDetailUiState : UiState {
    data class SuccessDetail(val movie: MovieDetail) : MovieDetailUiState
}

data class PeopleUiState(
    val items: List<PeopleResponse.PeopleDetail> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val page: Int = 1,
)

data class SearchResultState(
    val items: List<PlanetResponse.Planet> = emptyList(),
    val isLoading: Boolean = true,
    val isEmpty: Boolean = false,
)