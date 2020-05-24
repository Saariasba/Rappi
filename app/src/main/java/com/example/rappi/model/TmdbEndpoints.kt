package com.example.rappi.model

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.rappi.utils.POPULAR
import com.example.rappi.utils.TOP_RATED
import com.example.rappi.utils.UP_COMING

interface TmdbEndpoints {

    @GET("/3/movie/" + POPULAR)
    fun getPopularMovies(@Query("api_key") key: String): Observable<Movies>

    @GET("/3/movie/"+ TOP_RATED)
    fun getTopRatedMovies(@Query("api_key") key: String): Observable<Movies>

    @GET("/3/movie/"+ UP_COMING)
    fun getUpComingMovies(@Query("api_key") key: String): Observable<Movies>

    @GET("/3/search/movie")
    fun getMovieByName(@Query("api_key") key: String,@Query("query") name: String): Observable<Movies>
}