package com.example.jakala_swapi.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.jakala_swapi.data.db.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {
    @Upsert
    fun upsertMovieDetail(movieDetail: MovieDetailEntity)
    @Query("SELECT * FROM moviedetail WHERE title == :title")
    fun getMovieDetail(title: String): Flow<MovieDetailEntity?>
}