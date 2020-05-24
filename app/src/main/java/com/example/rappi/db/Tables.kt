package com.example.rappi.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(@PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") var id: Int,
                 @ColumnInfo(name = "overview")var overview: String,
                 @ColumnInfo(name = "poster_path")var poster_path: String,
                 @ColumnInfo(name = "release_date")var release_date: String,
                 @ColumnInfo(name = "title")var title: String,
                 @ColumnInfo(name = "vote_average")var vote_average: Double,
                 @ColumnInfo(name = "vote_count")var vote_count: Int): Parcelable