package com.example.jakala_swapi.ui.screens.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.domain.GetPeopleUseCase
import com.example.jakala_swapi.ui.PeopleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val peopleUseCase: GetPeopleUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow(PeopleUiState())
    val state get() = _state.asStateFlow()

    fun loadPeople() {
        viewModelScope.launch {
            peopleUseCase.invoke(state.value.page)
                .catch { _state.update { it.copy(isLoading = false, isError = true) } }
                .collect() {
                    when (it) {
                        is Result.Error -> {
                            _state.update { it.copy(isLoading = false, isError = true) }
                        }

                        Result.Loading -> {
                            _state.update { it.copy(isLoading = true) }
                        }

                        is Result.Success -> {
                            val items = it.data.results
                            val page = it.data.next?.substringAfter("=")?.toInt() ?: 1

                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    isError = false,
                                    items = it.items.plus(items),
                                    page = page
                                )
                            }
                        }
                    }
                }
        }
    }

    fun loadNextPage() {
        loadPeople()
    }
}