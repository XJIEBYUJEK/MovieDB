package com.example.moviedb.data.vo

import com.example.moviedb.data.ViewObject

data class Genre(
    val id: Int,
    val name: String
) : ViewObject

object Company : ViewObject
object Country : ViewObject
object Language : ViewObject