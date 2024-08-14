package com.example.jakala_swapi.data.db.entity

import java.time.LocalDate

data class MovieUpdate(
    val title: String,
    val openingCrawl: String?,
    val releaseDate: LocalDate?,
)