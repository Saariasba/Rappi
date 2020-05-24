package com.example.rappi.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface MoviesDao {
    @Query("SELECT * from movies")
    fun getAll(): List<Movie>

    @Insert(onConflict = REPLACE)
    fun insert(student: Movie)

    @Delete
    fun delete(student: Movie)
}