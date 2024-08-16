package com.example.jakala_swapi.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.domain.GetMoviesUseCase
import com.example.jakala_swapi.ui.MovieUiState
import com.example.jakala_swapi.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    val movieUiState: StateFlow<UiState> =
        moviesUseCase.invoke().map {
            when (it) {
                Result.Loading -> UiState.Loading
                is Result.Error -> UiState.Error
                is Result.Success -> MovieUiState.Success(it.data.results)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UiState.Loading
        )
}