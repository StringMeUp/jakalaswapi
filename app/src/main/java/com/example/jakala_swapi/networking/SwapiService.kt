package com.example.jakala_swapi.networking

import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SwapiService {
    @GET("api/films")
    suspend fun getMovies(): Response<MovieResponse>
    @GET("api/films/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: String): Response<MovieDetail>

    @GET("api/people")
    fun getPeople(): Response<Unit>
    @GET("api/planets")
    fun getPlanets(): Response<Unit>
}