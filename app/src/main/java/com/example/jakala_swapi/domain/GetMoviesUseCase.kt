package com.example.jakala_swapi.domain

import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.data.repository.SwapiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: SwapiRepository) {
    operator fun invoke(): Flow<Result<MovieResponse>> {
        return repository.getMovies()
    }
}