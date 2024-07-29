package com.example.jakala_swapi.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.domain.GetMovieDetailUseCase
import com.example.jakala_swapi.domain.GetMoviesUseCase
import com.example.jakala_swapi.ui.MovieDetailUiState
import com.example.jakala_swapi.ui.MovieUiState
import com.example.jakala_swapi.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesUseCase: GetMoviesUseCase,
    private val movieDetailUseCase: GetMovieDetailUseCase,
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

    /*_________________Detail_________________*/

    private val movieIdState = MutableStateFlow<String?>(null)
    fun setMovieId(input: String?) {
        movieIdState.value = input?.filter { it.isDigit() } ?: ""
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieDetailState: StateFlow<UiState> = movieIdState
        .filterNotNull()
        .flatMapLatest { id ->
            movieDetailUseCase.invoke(id)
                .map { result ->
                    when (result) {
                        is Result.Loading -> UiState.Loading
                        is Result.Error -> UiState.Error
                        is Result.Success -> MovieDetailUiState.SuccessDetail(result.data)
                    }
                }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UiState.Loading
        )
}