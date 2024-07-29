package com.example.jakala_swapi.ui.screens.movies

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jakala_swapi.ui.MovieDetailUiState
import com.example.jakala_swapi.ui.UiState

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
    movieDetailUiState: UiState = UiState.Loading,
) {
    when (val s = movieDetailUiState) {
        UiState.Error -> Text(text = "Error")
        UiState.Loading -> Text(text = "Loading")
        is MovieDetailUiState.SuccessDetail -> {
            Text(text = "Hello detail id:: ${s.movie}")
        }

        else -> {}
    }
}