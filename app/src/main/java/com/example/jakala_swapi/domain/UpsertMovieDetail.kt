package com.example.jakala_swapi.domain

import com.example.jakala_swapi.data.db.dao.MovieDetailDao
import com.example.jakala_swapi.data.dto.toMovieDetailEntity
import com.example.jakala_swapi.data.model.MovieDetail
import javax.inject.Inject

class UpsertMovieDetail @Inject constructor(private val movieDetailDao: MovieDetailDao) {
    operator fun invoke(movieDetail: MovieDetail) {
        movieDetailDao.upsertMovieDetail(movieDetail = movieDetail.toMovieDetailEntity())
    }
}