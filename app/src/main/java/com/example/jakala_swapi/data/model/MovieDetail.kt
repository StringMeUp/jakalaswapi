package com.example.jakala_swapi.data.model

import com.example.jakala_swapi.helper.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
data class MovieDetail(
    val title: String? = null,
    val episodeId: Int? = null,
    @SerialName("opening_crawl")
    val openingCrawl: String? = null,
    @Serializable(with = DateSerializer::class)
    @SerialName("release_date") val releaseDate: LocalDate? = null,
    val isFavorite: Boolean = false
){
    val formattedDate = releaseDate?.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
}

