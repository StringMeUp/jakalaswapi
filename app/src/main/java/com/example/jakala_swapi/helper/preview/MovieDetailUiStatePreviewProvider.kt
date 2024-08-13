package com.example.jakala_swapi.helper.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.ui.MovieDetailUiState
import com.example.jakala_swapi.ui.MovieUiState
import com.example.jakala_swapi.ui.UiState
import java.time.LocalDate

class MovieDetailUiStatePreviewProvider : PreviewParameterProvider<UiState> {
    override val values: Sequence<UiState>
        get() = sequenceOf(
            MovieDetailUiState.SuccessDetail(
                MovieDetail(
                    "A New Hope", 1, "A long time ago in a galaxy far, far away…", LocalDate.now()
                )
            )
        )
}