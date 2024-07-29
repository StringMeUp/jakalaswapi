package com.example.jakala_swapi.domain

import com.example.jakala_swapi.data.model.PeopleResponse
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.data.repository.SwapiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(private val repository: SwapiRepository) {
    operator fun invoke(page: Int): Flow<Result<PeopleResponse>> {
        return repository.getPeople(page)
    }
}