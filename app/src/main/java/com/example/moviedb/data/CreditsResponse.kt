package com.example.moviedb.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<Cast>
)
