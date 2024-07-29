package com.example.jakala_swapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetResponse(
    var results: List<Planet> = arrayListOf()
){
    @Serializable
    data class Planet(
        var name: String? = null,
        @SerialName("rotation_period") var rotationPeriod: String? = null,
        @SerialName("orbital_period") var orbitalPeriod: String? = null,
        var diameter: String? = null,
        var climate: String? = null,
        var gravity: String? = null,
        var terrain: String? = null,
        @SerialName("surface_water") var surfaceWater: String? = null,
        var population: String? = null,
        var residents: List<String> = listOf(),
    )
}
