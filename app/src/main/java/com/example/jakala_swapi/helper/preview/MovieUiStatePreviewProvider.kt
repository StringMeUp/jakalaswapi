package com.example.jakala_swapi.helper.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.ui.MovieUiState
import com.example.jakala_swapi.ui.UiState

class MovieUiStatePreviewProvider : PreviewParameterProvider<UiState> {
    override val values: Sequence<UiState>
        get() = sequenceOf(
            MovieUiState.Success(
                listOf(
                    MovieResponse.Movie(
                        "Dark Side",
                        1,
                        "Here we go!",
                        "Unknown",
                        "Samir",
                        "21.11.1988",
                        ""
                    )
                )
            )
        )
}