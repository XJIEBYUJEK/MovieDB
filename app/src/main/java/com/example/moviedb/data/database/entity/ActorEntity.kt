package com.example.moviedb.data.database.entity

import androidx.room.*
import com.example.moviedb.data.database.DBEntity
import com.example.moviedb.data.vo.Actor

@Entity(tableName = "actor")
data class ActorEntity(
    @PrimaryKey
    @ColumnInfo(name = "actorId")
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String,
) : DBEntity {
    override fun toViewObject() = Actor(id, name, character, profilePath)
}
