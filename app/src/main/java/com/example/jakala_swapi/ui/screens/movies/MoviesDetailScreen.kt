package com.example.jakala_swapi.ui.screens.movies

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.helper.preview.MovieDetailUiStatePreviewProvider
import com.example.jakala_swapi.ui.MovieDetailUiState
import com.example.jakala_swapi.ui.UiState
import com.example.jakala_swapi.ui.widgets.DetailItem
import com.example.jakala_swapi.ui.widgets.ErrorItem
import com.example.jakala_swapi.ui.widgets.LoadingItem

@Composable
fun MoviesDetailScreen(
    padding: PaddingValues = PaddingValues(),
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.movieDetailState.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier.padding(padding)) {
        MoviesDetailScreenContent(it, state) { viewModel.saveMovieDetail(it) }
    }
}

@Composable
private fun MoviesDetailScreenContent(
    paddingValues: PaddingValues = PaddingValues(),
    movieDetailUiState: UiState,
    saveMovieDetail: (movieDetail: MovieDetail) -> Unit = {}
) {
    when (movieDetailUiState) {
        UiState.Error -> ErrorItem()
        UiState.Loading -> LoadingItem()
        is MovieDetailUiState.SuccessDetail -> {
            DetailItem(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                movieDetail = movieDetailUiState.movie
            ) { saveMovieDetail(movieDetailUiState.movie) }
        }

        else -> {}
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailPreview(
    @PreviewParameter(MovieDetailUiStatePreviewProvider::class) movieDetailUiState: UiState,
) {
    MoviesDetailScreenContent(movieDetailUiState = movieDetailUiState)
}