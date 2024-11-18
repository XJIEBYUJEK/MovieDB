package com.example.moviedb.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.moviedb.data.ViewObject
import com.example.moviedb.data.vo.TvShows

@Serializable
data class TvShowsResponse(
    @SerialName("page")
    var page: Int,
    @SerialName("results")
    var results: List<TvShowResponse>,
    @SerialName("total_results")
    var totalResults: Int,
    @SerialName("total_pages")
    var totalPages: Int
): Response {
    override fun toViewObject() = TvShows(results.map { it.toViewObject() })
}
