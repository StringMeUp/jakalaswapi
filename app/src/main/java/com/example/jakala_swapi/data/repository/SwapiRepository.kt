package com.example.jakala_swapi.data.repository

import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.data.model.PeopleResponse
import com.example.jakala_swapi.data.model.PlanetResponse
import com.example.jakala_swapi.data.remotesource.Result
import kotlinx.coroutines.flow.Flow

interface SwapiRepository {
     fun getMovies(): Flow<Result<MovieResponse>>
     fun getMovieDetail(id: String): Flow<Result<MovieDetail>>
     fun getPeople(id: Int): Flow<Result<PeopleResponse>>
     fun searchPlanets(query: String): Flow<Result<PlanetResponse>>
}