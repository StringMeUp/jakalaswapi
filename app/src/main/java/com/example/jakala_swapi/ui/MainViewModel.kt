package com.example.jakala_swapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jakala_swapi.data.model.Movie
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(moviesUseCase: GetMoviesUseCase) : ViewModel() {
    @Immutable
    sealed interface MoviesUiState {
        data class Success(val movies: List<Movie>) : MoviesUiState
        data object Error : MoviesUiState
        data object Loading : MoviesUiState
    }

    val uiState: StateFlow<MoviesUiState> =
        moviesUseCase.invoke().map {
            when (it) {
                Result.Loading -> MoviesUiState.Loading
                is Result.Error -> MoviesUiState.Error
                is Result.Success -> MoviesUiState.Success(it.data.results)
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, MoviesUiState.Loading)

}