package com.example.rappi.`object`

import com.example.rappi.model.Result
import java.io.Serializable

//Object Movie to use into to the aplication
class movieObject(result: Result) : Serializable{
    val id = result.id
    val overview = result.overview
    val poster_path = result.poster_path
    val release_date = result.release_date
    val title = result.title
    val vote_average = result.vote_average
    val vote_count = result.vote_count
}
