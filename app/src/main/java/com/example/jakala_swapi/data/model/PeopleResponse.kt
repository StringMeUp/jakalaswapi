package com.example.jakala_swapi.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PeopleResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<PeopleDetail>
)