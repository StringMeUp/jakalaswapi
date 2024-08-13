package com.example.jakala_swapi.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jakala_swapi.data.db.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieDetail(movieDetail: MovieDetailEntity): Long

    @Query("SELECT * FROM moviedetail WHERE episodeId == :id")
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity?>
}