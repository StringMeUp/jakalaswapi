package com.example.jakala_swapi.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jakala_swapi.helper.DateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
@Entity("moviedetail")
data class MovieDetailEntity(
    val title: String,
    @PrimaryKey
    val episodeId: Int,
    val openingCrawl: String,
    @Serializable(with = DateSerializer::class)
    val releaseDate: LocalDate,
)