package com.example.jakala_swapi.networking

import retrofit2.Response
import retrofit2.http.GET

interface SwapiService {
    @GET("/people/")
    fun getPeople(): Response<Unit>
}