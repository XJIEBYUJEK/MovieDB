package com.example.moviedb.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.moviedb.data.ViewObject
import com.example.moviedb.data.vo.Company
import com.example.moviedb.data.vo.Country
import com.example.moviedb.data.vo.Genre
import com.example.moviedb.data.vo.Language

@Serializable
data class Genre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
) : Response {
    override fun toViewObject() = Genre(id, name)
}

@Serializable
data class Company(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("origin_country")
    val originCountry: String?,
    @SerialName("logo_path")
    val logoPath: String?
): Response {
    override fun toViewObject() = Company
}

@Serializable
data class Country(
    @SerialName("name")
    val name: String?,
    @SerialName("iso_3166_1")
    val iso: String?
): Response {
    override fun toViewObject() = Country
}

@Serializable
data class Language(
    @SerialName("name")
    val name: String?,
    @SerialName("iso_639_1")
    val iso: String?,
    @SerialName("english_name")
    val englishName: String?
): Response {
    override fun toViewObject() = Language
}
