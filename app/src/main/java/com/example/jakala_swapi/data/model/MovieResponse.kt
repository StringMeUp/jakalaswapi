package com.example.jakala_swapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val results: List<Movie>
){
    @Serializable
    data class Movie(
        val title: String,
        @SerialName("episode_id") val episodeId: Int,
        @SerialName("opening_crawl") val openingCrawl: String,
        val director: String,
        val producer: String,
        @SerialName("release_date") val releaseDate: String,
        val url: String?
    )
}