package com.example.moviedb.data.database.entity

import androidx.room.*
import com.example.moviedb.data.ViewObject
import com.example.moviedb.data.database.DBEntity
import com.example.moviedb.data.vo.Movie
import com.example.moviedb.data.vo.MovieDetails

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    val id: Int,
    val name: String,
    val overview: String?,
    val posterPath: String?,
    val rating: Float,
    val isFavorite: Boolean
) : DBEntity {
    override fun toViewObject() = MovieDetails(
        id,
        name,
        overview,
        rating,
        posterPath,
        isFavorite)

}