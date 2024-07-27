package com.example.jakala_swapi.data

import com.example.jakala_swapi.data.repository.SwapiRepository
import com.example.jakala_swapi.networking.SwapiService
import javax.inject.Inject

class SwapiRepositoryImpl @Inject constructor(private val apiService: SwapiService) : SwapiRepository {
    override fun testDi(): String = "Test Di $apiService"
}