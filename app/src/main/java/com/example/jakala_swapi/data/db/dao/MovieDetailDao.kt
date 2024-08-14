package com.example.jakala_swapi.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.jakala_swapi.data.db.entity.MovieDetailEntity
import com.example.jakala_swapi.data.db.entity.MovieUpdate
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {
    @Upsert
    fun upsertMovieDetail(movieDetail: MovieDetailEntity)

    @Update(entity = MovieDetailEntity::class)
    fun updateMovieDetail(movieDetail: MovieUpdate)

    @Query("SELECT * FROM moviedetail WHERE title == :title")
    fun getMovieDetail(title: String): Flow<MovieDetailEntity?>

    @Query("SELECT EXISTS(SELECT 1 FROM moviedetail WHERE title == :title)")
    fun isMovieDetailPresent(title: String): Boolean
}