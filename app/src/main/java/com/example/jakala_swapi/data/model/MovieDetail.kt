package com.example.jakala_swapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetail(
    val title: String? = null,
    val episodeId: Int? = null,
    val openingCrawl: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
)