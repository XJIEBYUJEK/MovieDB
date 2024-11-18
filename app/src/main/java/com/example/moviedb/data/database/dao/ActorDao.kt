package com.example.moviedb.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.moviedb.data.database.entity.ActorEntity

@Dao
interface ActorDao {
    @Insert(onConflict = REPLACE)
    fun insert(actor: ActorEntity)

    @Delete
    fun delete(actor: ActorEntity)

    @Query("SELECT * FROM actor")
    fun getActors(): List<ActorEntity>
}