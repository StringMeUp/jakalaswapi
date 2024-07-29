package com.example.jakala_swapi.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.domain.SearchPlanetsUseCase
import com.example.jakala_swapi.ui.SearchResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val searchPlanetsUseCase: SearchPlanetsUseCase) :
    ViewModel() {
    var searchQuery by mutableStateOf("")
        private set

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchResultsState: StateFlow<SearchResultState> =
        snapshotFlow { searchQuery }
            .debounce(250)
            .flatMapLatest { query ->
                if (query.isBlank()) flowOf(Result.Success(null)) else searchPlanetsUseCase.invoke(query)
            }
            .map {
                when (it) {
                    is Result.Error -> SearchResultState(isEmpty = true, isLoading = false)
                    Result.Loading -> SearchResultState(isLoading = true)
                    is Result.Success -> SearchResultState(
                        isLoading = false,
                        items = it.data?.results ?: emptyList()
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = SearchResultState()
            )

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }
}