package com.example.rappi.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rappi.R
import com.example.rappi.db.Movie
import com.example.rappi.db.MoviesDatabase
import com.example.rappi.model.Movies
import com.example.rappi.model.Result
import com.example.rappi.model.ServiceBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

//This class Load all the movies in the RecyclerViews and the DataBase
class LoadMovies(){

    private var count : Int = 0
    lateinit var context : Context
    lateinit var progress_bar : ProgressBar

    private var moviesDatabase: MoviesDatabase? = null
    private val compositeDisposable = CompositeDisposable()

    //Function principal called by MainActivity to load the movies
    fun loadMovies(recyclerView : RecyclerView, recyclerView2 : RecyclerView, recyclerView3 : RecyclerView) {
        moviesDatabase = MoviesDatabase.getInstance(context)
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ServiceBuilder.buildService().getPopularMovies(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response, recyclerView,"Popular")}, { t -> onFailure(t) }))
        compositeDisposable.add(
            ServiceBuilder.buildService().getTopRatedMovies(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response, recyclerView2,"TopRated")}, { t -> onFailure(t) }))
        compositeDisposable.add(
            ServiceBuilder.buildService().getUpComingMovies(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response, recyclerView3,"UpComing")}, { t -> onFailure(t) }))
    }

    //Insert each one movie
    fun insertToMovie(movie: Movie){
        compositeDisposable.add(Observable.fromCallable{moviesDatabase?.moviesDao()?.insert(movie)}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }


    //This is the Negative answer to the function loadMovies
    private fun onFailure(t: Throwable) {
        Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
    }

    //This is the Positive answer to the function loadMovies
    private fun onResponse(
        response: Movies,
        recyclerView: RecyclerView,
        categoria : String
    ) {
        val list : List<Result> = response.results
        for (i in 0 until list.size) {
            val movieNow = list.get(i)
            val movieBD = Movie(count,categoria,movieNow.id,movieNow.overview,movieNow.poster_path,movieNow.release_date,movieNow.title,movieNow.vote_average,movieNow.vote_count)
            count++;
            insertToMovie(movieBD)
        }
        progress_bar.visibility = View.GONE
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MoviesAdapter(response.results, R.layout.movie_item)
        }

    }

}