package com.example.moviedb.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviedb.data.database.dao.ActorDao
import com.example.moviedb.data.database.dao.MovieDao
import com.example.moviedb.data.database.entity.ActorEntity
import com.example.moviedb.data.database.entity.MovieEntity

@Database(entities = [ActorEntity::class, MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun actorDao(): ActorDao
    abstract fun movieDao(): MovieDao

    companion object{
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    AppDatabase::class.simpleName
                ).build()
            }
            return  instance!!
        }
    }

}