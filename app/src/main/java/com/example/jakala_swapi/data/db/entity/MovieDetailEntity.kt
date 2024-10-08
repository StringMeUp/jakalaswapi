package com.example.jakala_swapi.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jakala_swapi.helper.DateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Entity("moviedetail")
data class MovieDetailEntity(
    @PrimaryKey
    val title: String,
    val episodeId: Int?,
    val openingCrawl: String?,
    val releaseDate: LocalDate?,
    val isFavorite: Boolean = false
)