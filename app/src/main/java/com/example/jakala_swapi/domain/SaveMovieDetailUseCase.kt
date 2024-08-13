package com.example.jakala_swapi.domain

import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.data.repository.SwapiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveMovieDetailUseCase @Inject constructor(private val repository: SwapiRepository) {
    operator fun invoke(id: String): Flow<Result<MovieDetail>> {
        return repository.getMovieDetail(id)
    }
}