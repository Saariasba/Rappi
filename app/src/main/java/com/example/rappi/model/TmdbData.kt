package com.example.rappi.model

//this is the structure of the JSON that comes from TMDB
data class Movies(
    val results: List<Result>
)

data class Result(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)