package com.example.jakala_swapi.data.repository

import com.example.jakala_swapi.data.db.dao.MovieDetailDao
import com.example.jakala_swapi.data.dto.toMovieDetail
import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.data.model.PeopleResponse
import com.example.jakala_swapi.data.model.PlanetResponse
import com.example.jakala_swapi.data.remotesource.RemoteSource
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.networking.SwapiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class SwapiRepositoryImpl @Inject constructor(
    private val apiService: SwapiService,
    private val movieDetailDao: MovieDetailDao
) : SwapiRepository {
    override fun getMovies(): Flow<Result<MovieResponse>> =
        RemoteSource.launchResultFlow { apiService.getMovies() }

    override fun getMovieDetail(id: String, title: String): Flow<Result<MovieDetail>> =
        flow {
            emitAll(RemoteSource.launchResultFlow {
                apiService.getMovieDetails(id)
            })
        }.onCompletion {
            emitAll(
                movieDetailDao.getMovieDetail(title)
                    .filterNotNull()
                    .map { Result.Success(it.toMovieDetail()) }
                    .distinctUntilChanged()
            )
        }

    override fun getPeople(id: Int): Flow<Result<PeopleResponse>> =
        RemoteSource.launchResultFlow {
            apiService.getPeople(id)
        }

    override fun searchPlanets(query: String): Flow<Result<PlanetResponse>> =
        RemoteSource.launchResultFlow {
            apiService.getPlanets(query)
        }

}