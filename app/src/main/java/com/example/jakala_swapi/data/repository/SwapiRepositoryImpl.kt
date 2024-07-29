package com.example.jakala_swapi.data.repository

import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.data.remotesource.RemoteSource
import com.example.jakala_swapi.data.remotesource.Result
import com.example.jakala_swapi.networking.SwapiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SwapiRepositoryImpl @Inject constructor(private val apiService: SwapiService) :
    SwapiRepository {
    override fun getMovies(): Flow<Result<MovieResponse>> =
        RemoteSource.launchResultFlow { apiService.getMovies() }

    override fun getMovieDetail(id: String): Flow<Result<MovieDetail>> =
        RemoteSource.launchResultFlow {
            apiService.getMovieDetails(id)
        }

}