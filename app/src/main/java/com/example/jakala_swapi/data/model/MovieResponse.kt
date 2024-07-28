package com.example.jakala_swapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<Movie>
)