package com.example.jakala_swapi.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jakala_swapi.helper.UiStateProvider
import com.example.jakala_swapi.ui.SearchResultState
import com.example.jakala_swapi.widgets.PlanetItem
import com.example.jakala_swapi.widgets.PlanetItemEmptyState

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel(), padding: PaddingValues) {
    val searchResults by viewModel.searchResultsState.collectAsStateWithLifecycle()

    Scaffold(Modifier.padding(padding)) {
        SearchScreenContent(
            padding = it,
            searchQuery = viewModel.searchQuery,
            searchResults = searchResults,
            onSearchQueryChange = { viewModel.onSearchQueryChange(it) }
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun SearchScreenContent(
    padding: PaddingValues = PaddingValues(),
    searchQuery: String = "",
    searchResults: SearchResultState = SearchResultState(
        items = listOf(UiStateProvider.defaultPlanet()),
        isLoading = false
    ),
    onSearchQueryChange: (query: String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        onSearch = { keyboardController?.hide() },
        placeholder = {
            Text(text = "Search planets")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearchQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = "Clear search"
                    )
                }
            }
        },
        content = {
            if (searchQuery.isEmpty()) {
                PlanetItemEmptyState()
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        count = searchResults.items.size,
                        key = { index -> searchResults.items[index].name ?: "" },
                        itemContent = { index ->
                            val planet = searchResults.items[index]
                            PlanetItem(planet = planet)
                        }
                    )
                }
            }

        },
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp
    )
}