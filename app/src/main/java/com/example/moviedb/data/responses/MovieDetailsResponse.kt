package com.example.moviedb.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.moviedb.BuildConfig
import com.example.moviedb.data.ViewObject
import com.example.moviedb.data.vo.MovieDetails

@Serializable
data class MovieDetailsResponse(
    @SerialName("adult")
    val isAdult: Boolean?,
    @SerialName("backdrop_path")
    val backDropPath: String?,
    /*    @SerialName("belongs_to_collection")
        val belongsToCollection: String?,*/
    @SerialName("budget")
    val budget: Int?,
    @SerialName("genres")
    val genres: List<Genre>?,
    @SerialName("homepage")
    val homepage: String?,
    @SerialName("id")
    val id: Int,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("original_title")
    val originalTitle: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("production_companies")
    val companies: List<Company>?,
    @SerialName("production_countries")
    val countries: List<Country>?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("revenue")
    val revenue: Int?,
    @SerialName("runtime")
    val runtime: Int?,
    @SerialName("spoken_languages")
    val languages: List<Language>?,
    @SerialName("status")
    val status: String?,
    @SerialName("tagline")
    val tagline: String?,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?

): Response {
    val rating: Float
        get() = ratingCalculation(voteAverage)
    @SerialName("poster_path")
    var posterPath: String? = null
        get() = "${BuildConfig.IMAGE_URL}$SCALE$field"

    override fun toViewObject() = MovieDetails(
        id,
        title,
        overview,
        rating,
        posterPath,
        false
    )

    companion object{
        const val SCALE = "w500"
    }
}
