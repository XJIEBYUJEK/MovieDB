package com.example.moviedb.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["actorId", "movieId"], tableName = "actor_movie")
data class ActorMovieEntity(
    val actorId: Int,
    val movieId: Int
)

data class ActorAndMovies(
    @Embedded
    val actor: ActorEntity,
    @Relation(
        parentColumn = "actorId",
        entityColumn = "movieId",
        associateBy = Junction(
            ActorMovieEntity::class,
            parentColumn = "actorId",
            entityColumn = "movieId"
        )
    )
    val movies: List<MovieEntity>
)