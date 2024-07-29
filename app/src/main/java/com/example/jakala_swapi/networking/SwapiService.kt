package com.example.jakala_swapi.networking

import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.data.model.MovieResponse
import com.example.jakala_swapi.data.model.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SwapiService {
    @GET("api/films")
    suspend fun getMovies(): Response<MovieResponse>

    @GET("api/films/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: String): Response<MovieDetail>

    @GET("api/people")
    suspend fun getPeople(@Query("page") page: Int): Response<PeopleResponse>

    @GET("api/planets")
    fun getPlanets(): Response<Unit>
}