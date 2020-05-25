package com.example.rappi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//This Class is the MovieDatabase using RoomDataBase
@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    companion object {
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase? {
            if (INSTANCE == null) {
                synchronized(MoviesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MoviesDatabase::class.java, "moviesdata.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}