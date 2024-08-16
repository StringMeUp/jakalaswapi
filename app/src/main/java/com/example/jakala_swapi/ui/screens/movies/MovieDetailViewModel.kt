package com.example.jakala_swapi.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.domain.GetMovieDetailUseCase
import com.example.jakala_swapi.domain.UpsertMovieDetailUseCase
import com.example.jakala_swapi.ui.MovieDetailUiState
import com.example.jakala_swapi.ui.UiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
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

@HiltViewModel(assistedFactory = MovieDetailViewModel.VmAssistedFactory::class)
class MovieDetailViewModel @AssistedInject constructor(
    @Assisted("id") private val id: String,
    @Assisted("title") private val title: String,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val upsertMovieDetailUseCase: UpsertMovieDetailUseCase
) : ViewModel() {

    @AssistedFactory
    interface VmAssistedFactory {
        fun create(
            @Assisted("id") id: String,
            @Assisted("title") title: String
        ): MovieDetailViewModel
    }

    private var movieIdFlow = MutableStateFlow(Pair(id, title))

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieDetailState: StateFlow<UiState> = movieIdFlow
        .filter { it.first.isNotBlank() && it.second.isNotBlank() }
        .flatMapLatest { (id, title) ->
            getMovieDetailUseCase.invoke(id, title)
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
            upsertMovieDetailUseCase.invoke(movieDetail.copy(isFavorite = isFavorite))
        }
    }
}