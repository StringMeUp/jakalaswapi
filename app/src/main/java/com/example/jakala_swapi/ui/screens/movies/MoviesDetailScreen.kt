package com.example.jakala_swapi.ui.screens.movies

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jakala_swapi.helper.UiStateProvider
import com.example.jakala_swapi.ui.MovieDetailUiState
import com.example.jakala_swapi.ui.UiState
import com.example.jakala_swapi.widgets.DetailItem
import com.example.jakala_swapi.widgets.ErrorItem
import com.example.jakala_swapi.widgets.LoadingItem

@Composable
fun MoviesDetailScreen(
    padding: PaddingValues = PaddingValues(),
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    val state by viewModel.movieDetailState.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier.padding(padding)) {
        MoviesDetailScreenContent(it, state)
    }
}

@Preview(showBackground = true)
@Composable
private fun MoviesDetailScreenContent(
    paddingValues: PaddingValues = PaddingValues(),
    movieDetailUiState: UiState = MovieDetailUiState.SuccessDetail(UiStateProvider.defaultMovieDetail()),
) {
    when (val s = movieDetailUiState) {
        UiState.Error -> ErrorItem()
        UiState.Loading -> LoadingItem()
        is MovieDetailUiState.SuccessDetail -> {
            DetailItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp), movieDetail = s.movie
            )
        }

        else -> {}
    }
}