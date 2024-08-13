package com.example.jakala_swapi.helper.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jakala_swapi.data.model.PeopleResponse
import com.example.jakala_swapi.data.model.PlanetResponse
import com.example.jakala_swapi.ui.PeopleUiState
import com.example.jakala_swapi.ui.SearchResultState

class SearchResultUiStatePreviewProvider : PreviewParameterProvider<SearchResultState> {
    override val values: Sequence<SearchResultState>
        get() = sequenceOf(
            SearchResultState(
                isLoading = false,
                items = listOf(
                    PlanetResponse.Planet(
                        "Earth",
                        "1",
                        "3",
                        "99887",
                        "Friendly",
                        "Yes",
                        "Terrain",
                        "4",
                        "666",
                    )
                )
            )
        )
}