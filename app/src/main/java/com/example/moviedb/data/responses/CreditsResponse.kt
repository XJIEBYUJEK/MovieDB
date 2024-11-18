package com.example.moviedb.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.moviedb.data.ViewObject
import com.example.moviedb.data.vo.Credits

@Serializable
data class CreditsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<CastResponse>
) : Response {
    override fun toViewObject() = Credits(id, cast.map { it.toViewObject() })
}
