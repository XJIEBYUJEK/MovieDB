package com.example.moviedb.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.moviedb.data.ViewObject
import com.example.moviedb.data.vo.Movies

@Serializable
data class MoviesResponse(
    @SerialName("page")
    var page: Int,
    @SerialName("results")
    var results: List<MovieResponse>,
    @SerialName("total_results")
    var totalResults: Int,
    @SerialName("total_pages")
    var totalPages: Int
): Response {
    override fun toViewObject() = Movies(results.map { it.toViewObject() })
}
