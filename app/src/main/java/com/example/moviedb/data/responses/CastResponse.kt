package com.example.moviedb.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.moviedb.BuildConfig
import com.example.moviedb.data.ViewObject
import com.example.moviedb.data.vo.Actor

@Serializable
data class CastResponse(
    @SerialName("gender")
    val gender: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String?,
    @SerialName("original_name")
    val originalName: String?,
    @SerialName("character")
    val character: String?,
    @SerialName("order")
    val order: Int
) : Response {
    @SerialName("profile_path")
    var profilePath: String? = null
        get() = "${BuildConfig.IMAGE_URL}$SCALE$field"
    companion object{
        const val SCALE = "w500"
    }

    override fun toViewObject() = Actor(id, name, character, profilePath)
}
