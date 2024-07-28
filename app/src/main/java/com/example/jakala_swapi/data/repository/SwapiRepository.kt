package com.example.jakala_swapi.data.repository

import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.data.remotesource.RemoteSource
import com.example.jakala_swapi.data.remotesource.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SwapiRepository {
     fun getMovies(): Flow<Result<MovieResponse>>
}