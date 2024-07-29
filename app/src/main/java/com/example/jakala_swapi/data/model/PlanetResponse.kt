package com.example.jakala_swapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<Planet>?
) {
    @Serializable
    data class Planet(
        var name: String?,
        @SerialName("rotation_period") var rotationPeriod: Int?,
        @SerialName("orbital_period") var orbitalPeriod: Int?,
        var diameter: Int?,
        var climate: String?,
        var gravity: String?,
        var terrain: String?,
        @SerialName("surface_water") var surfaceWater: Int?,
        var population: Int?,
        var created: String?,
        var edited: String?,
        var url: String?
    )
}