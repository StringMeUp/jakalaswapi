package com.example.jakala_swapi.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.domain.GetPeopleUseCase
import com.example.jakala_swapi.domain.SearchPlanetsUseCase
import com.example.jakala_swapi.ui.PeopleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val searchPlanetsUseCase: SearchPlanetsUseCase) :
    ViewModel() {
    var searchQuery by mutableStateOf("")
        private set


}