package com.example.jakala_swapi.data.db

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let {
            LocalDate.from(
                Instant.ofEpochSecond(value).atZone(ZoneId.systemDefault())
            )
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.atStartOfDay(ZoneId.systemDefault())?.toEpochSecond()
    }
}