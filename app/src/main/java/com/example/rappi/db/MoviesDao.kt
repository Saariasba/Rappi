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

    @Query("SELECT * from movies WHERE tipo = :type AND title like :char")
    fun getBy(char : String, type : String): List<Movie>

    @Insert(onConflict = REPLACE)
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)
}