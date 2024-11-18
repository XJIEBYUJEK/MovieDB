package com.example.moviedb.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.moviedb.BuildConfig
import com.example.moviedb.data.ViewObject
import com.example.moviedb.data.vo.Movie

@Serializable
data class MovieResponse(
    @SerialName("adult")
    val isAdult: Boolean?,
    @SerialName("backdrop_path")
    val backDropPath: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int>?,
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
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?
) : Response {
    val rating: Float
        get() = ratingCalculation(voteAverage)
    @SerialName("poster_path")
    var posterPath: String? = null
        get() = "${BuildConfig.IMAGE_URL}$SCALE$field"
    companion object{
        const val SCALE = "w500"
    }

    override fun toViewObject() = Movie(id, title, rating, posterPath)
}
