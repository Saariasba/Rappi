package com.example.rappi.db

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_app") var idApp: Int,
                 @Nullable@ColumnInfo(name = "tipo") var tipo: String,
                 @Nullable@ColumnInfo(name = "id") var id: Int,
                 @Nullable@ColumnInfo(name = "overview")var overview: String,
                 @Nullable@ColumnInfo(name = "poster_path")var poster_path: String,
                 @Nullable@ColumnInfo(name = "release_date")var release_date: String,
                 @Nullable@ColumnInfo(name = "title")var title: String,
                 @Nullable@ColumnInfo(name = "vote_average")var vote_average: Double,
                 @Nullable@ColumnInfo(name = "vote_count")var vote_count: Int): Parcelable