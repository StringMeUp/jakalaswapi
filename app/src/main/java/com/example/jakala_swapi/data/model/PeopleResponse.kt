package com.example.jakala_swapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleResponse(
    val next: String?,
    val results: List<PeopleDetail>
) {
    @Serializable
    data class PeopleDetail(
        @SerialName("name")
        val name: String?,
        @SerialName("height")
        val height: String?,
        @SerialName("mass")
        val mass: String?,
        @SerialName("hair_color")
        val hairColor: String?,
        @SerialName("eye_color")
        val eyeColor: String?,
        @SerialName("birth_year")
        val birthYear: String?,
        @SerialName("homeworld")
        val homeWorld: String?,
    )
}