package com.example.moviedb.data.vo

import com.example.moviedb.MovieFinderApp.Companion.appContext
import com.example.moviedb.data.ViewObject
import com.example.moviedb.data.database.AppDatabase
import com.example.moviedb.data.database.entity.MovieEntity

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String?,
    val rating: Float,
    val posterPath: String?,
    var isFavorite: Boolean
) : ViewObject {
    private val db = AppDatabase.getInstance(appContext).movieDao()

    private fun toDb() = MovieEntity(
        id,
        title,
        overview,
        posterPath,
        rating,
        isFavorite
    )

    suspend fun saveToDb(){
        db.insert(toDb())
    }

    suspend fun removeFromDb(){
        db.delete(toDb())
    }

    suspend fun update(){
        db.update(toDb())
    }

}
