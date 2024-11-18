package com.example.moviedb.data.vo

import com.example.moviedb.data.ViewObject

data class Movies(val results: List<Movie>) : ViewObject

data class Movie(
    val id: Int,
    val title: String,
    val rating: Float,
    val posterPath: String?
) : ViewObject