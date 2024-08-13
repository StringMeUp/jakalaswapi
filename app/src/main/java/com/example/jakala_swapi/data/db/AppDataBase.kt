package com.example.jakala_swapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jakala_swapi.data.db.dao.MovieDetailDao
import com.example.jakala_swapi.data.db.entity.MovieDetailEntity

@Database(entities = [MovieDetailEntity::class], version = 0)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDetailDao(): MovieDetailDao
}