package com.example.jakala_swapi.ui

import androidx.lifecycle.ViewModel
import com.example.jakala_swapi.data.repository.SwapiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: SwapiRepository) : ViewModel() {

    init {
        println(repository.testDi())
    }
}