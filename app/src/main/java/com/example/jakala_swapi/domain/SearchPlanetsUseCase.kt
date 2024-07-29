package com.example.jakala_swapi.domain

import com.example.jakala_swapi.data.model.PlanetResponse
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.data.repository.SwapiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPlanetsUseCase @Inject constructor(private val repository: SwapiRepository) {
    operator fun invoke(query: String): Flow<Result<PlanetResponse>> {
        return repository.searchPlanets(query)
    }
}