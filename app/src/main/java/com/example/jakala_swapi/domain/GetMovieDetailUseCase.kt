package com.example.jakala_swapi.domain

import com.example.jakala_swapi.data.db.dao.MovieDetailDao
import com.example.jakala_swapi.data.dto.toMovieDetail
import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.data.repository.SwapiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: SwapiRepository,
    private val movieDetailDao: MovieDetailDao
) {
    operator fun invoke(id: String, title: String): Flow<Result<MovieDetail>> = combine(
        movieDetailDao.getMovieDetail(title),
        repository.getMovieDetail(id)
    ) { a, b -> a to b }.map { (movieDetailEntity, movieDetail) ->
        movieDetailEntity?.let { Result.Success(it.toMovieDetail()) } ?: movieDetail
    }
}