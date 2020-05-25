package com.example.rappi.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rappi.R
import com.example.rappi.db.Movie
import com.example.rappi.db.MoviesDatabase
import com.example.rappi.model.Result
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

//This class execute the sql request
class SearchOfflineMovies {

    private var moviesDatabase: MoviesDatabase? = null
    val compositeDisposable = CompositeDisposable()

    lateinit var context : Context
    lateinit var progress_bar : ProgressBar

    //this is the function that makes the request to the database
    fun getBy(char: String, type : String, recyclerViewSearchOffline: RecyclerView){
        moviesDatabase = MoviesDatabase.getInstance(context)
        compositeDisposable.add(Observable.fromCallable{moviesDatabase?.moviesDao()?.getBy(char,type)}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response -> onResponse(response, recyclerViewSearchOffline)}, { t -> onFailure(t) }))
    }

    //This is the Negative answer to the function getBy
    private fun onFailure(t: Throwable) {
        progress_bar.visibility = View.GONE
        Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
    }

    //This is the Positive answer to the function getBy
    private fun onResponse(
        response: List<Movie>?,
        recycler: RecyclerView
    ) {
        var listNew : List<Result> = mutableListOf()
        for (i in 0 until response!!.size) {
            val responseU = response.get(i)
            val result = Result(responseU.id,responseU.overview,responseU.poster_path,responseU.release_date,responseU.title,responseU.vote_average,responseU.vote_count)
            Log.d("Prueba",responseU.toString())
            listNew += result
        }
        progress_bar.visibility = View.GONE
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MoviesAdapter(listNew, R.layout.movie_item_search)
        }

    }

}