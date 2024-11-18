package com.example.moviedb.data.vo

import com.example.moviedb.data.ViewObject

data class TvShow(
    val id: Int,
    val title: String?,
    val rating: Float,
    val posterPath: String?
) : ViewObject

data class TvShows(val results: List<TvShow>) : ViewObject