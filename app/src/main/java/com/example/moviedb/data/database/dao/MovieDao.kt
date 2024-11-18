package com.example.moviedb.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import com.example.moviedb.data.database.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Update
    suspend fun update(movie: MovieEntity)

    @Query("SELECT * FROM movie WHERE movieId = :id")
    fun getMovie(id: Int): Single<MovieEntity>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): Single<List<MovieEntity>>

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Single<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE movieId = :id)")
    suspend fun isMovieSaved(id: Int): Boolean
}