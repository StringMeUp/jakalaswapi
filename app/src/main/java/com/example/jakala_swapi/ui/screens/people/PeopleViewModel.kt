package com.example.jakala_swapi.ui.screens.people

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.domain.GetPeopleUseCase
import com.example.jakala_swapi.ui.PeopleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val peopleUseCase: GetPeopleUseCase,
) : ViewModel() {

    private var defaultUiState by mutableStateOf(PeopleUiState())
    private var currentPage = MutableStateFlow(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiStateStateFlow: StateFlow<PeopleUiState> = currentPage
        .flatMapLatest { page ->
            peopleUseCase.invoke(page)
                .map { result ->
                    when (result) {
                        is Result.Error -> defaultUiState.copy(isLoading = false, isError = true)
                        Result.Loading -> defaultUiState.copy(isLoading = true)
                        is Result.Success -> {
                            val newItems = result.data.results
                            val nextPage = result.data.next?.substringAfter("=")?.toInt() ?: 1
                            defaultUiState = defaultUiState.copy(
                                isLoading = false,
                                isError = false,
                                items = defaultUiState.items.plus(newItems),
                                page = nextPage
                            )
                            defaultUiState
                        }
                    }
                }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, defaultUiState)

    fun loadNextPage(page: Int) {
        currentPage.value = page
    }
}