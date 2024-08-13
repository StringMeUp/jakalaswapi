package com.example.jakala_swapi.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.domain.GetMovieDetailUseCase
import com.example.jakala_swapi.domain.GetMoviesUseCase
import com.example.jakala_swapi.domain.UpsertMovieDetail
import com.example.jakala_swapi.ui.MovieDetailUiState
import com.example.jakala_swapi.ui.MovieUiState
import com.example.jakala_swapi.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesUseCase: GetMoviesUseCase,
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val upsertMovieDetail: UpsertMovieDetail
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

    private var movieIdFlow = MutableStateFlow(Pair("", ""))
    var movieId: Pair<String, String>
        get() = movieIdFlow.value
        set(value) {
            movieIdFlow.value = Pair(value.first.filter { it.isDigit() }, value.second)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieDetailState: StateFlow<UiState> = movieIdFlow
        .filter { it.first.isNotBlank() && it.second.isNotBlank() }
        .flatMapLatest { (id, title) ->
            movieDetailUseCase.invoke(id, title)
                .map { result ->
                    when (result) {
                        is Result.Loading -> UiState.Loading
                        is Result.Error -> UiState.Error
                        is Result.Success -> MovieDetailUiState.SuccessDetail(result.data)
                    }
                }
        }
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UiState.Loading
        )

    fun saveMovieDetail(movieDetail: MovieDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = !movieDetail.isFavorite
            upsertMovieDetail.invoke(movieDetail.copy(isFavorite = isFavorite))
        }
    }
}