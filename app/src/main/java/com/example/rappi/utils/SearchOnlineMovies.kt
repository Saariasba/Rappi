package com.example.rappi.utils

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rappi.R
import com.example.rappi.model.Movies
import com.example.rappi.model.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchOnlineMovies {

    lateinit var context : Context
    lateinit var progress_bar : ProgressBar

    fun Search(textToSearch : EditText, recyclerViewSearchOnline : RecyclerView){
        progress_bar.visibility = View.VISIBLE
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ServiceBuilder.buildService().getMovieByName(API_KEY,textToSearch.text.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response, recyclerViewSearchOnline)}, { t -> onFailure(t) }))
    }

    //This is the Negative answer to the function doSearch
    private fun onFailure(t: Throwable) {
        progress_bar.visibility = View.GONE
        Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
    }

    //This is the Positive answer to the function doSearch
    private fun onResponse(
        response: Movies,
        recycler: RecyclerView
    ) {
        progress_bar.visibility = View.GONE
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MoviesAdapter(response.results, R.layout.movie_item_search)
        }

    }
}