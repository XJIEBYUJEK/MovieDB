package com.example.moviedb.data.vo

import com.example.moviedb.data.ViewObject

data class Actor(
    val id: Int,
    val name: String?,
    val character: String?,
    val profilePath: String?
) : ViewObject

data class Credits(
    val id: Int,
    val cast: List<Actor>
) : ViewObject