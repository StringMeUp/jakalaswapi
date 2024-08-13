package com.example.jakala_swapi.helper.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.data.model.PeopleResponse
import com.example.jakala_swapi.ui.MovieDetailUiState
import com.example.jakala_swapi.ui.MovieUiState
import com.example.jakala_swapi.ui.PeopleUiState
import com.example.jakala_swapi.ui.UiState
import java.time.LocalDate

class PeopleUiStatePreviewProvider : PreviewParameterProvider<PeopleUiState> {
    override val values: Sequence<PeopleUiState>
        get() = sequenceOf(
            PeopleUiState(
                isLoading = false,
                items = listOf(
                    PeopleResponse.PeopleDetail(
                        "Luke",
                        "1.78",
                        "Black",
                        "Blue",
                        "Blur",
                        "1988",
                        "Erath",
                    ), PeopleResponse.PeopleDetail(
                        "Sam",
                        "2.00",
                        "Black",
                        "Blue",
                        "Blur",
                        "1988",
                        "Somewhere",
                    )
                )
            )
        )
}