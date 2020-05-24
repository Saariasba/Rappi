package com.example.rappi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rappi.R
import com.example.rappi.model.Movies
import com.example.rappi.model.ServiceBuilder
import com.example.rappi.utils.API_KEY
import com.example.rappi.utils.MoviesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search_online.*

class SearchOnline : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_online)
    }

    /* Called when the user touches the button Search Offline */
    fun doSearch(view: View?) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ServiceBuilder.buildService().getMovieByName(API_KEY,textToSearch.text.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response, recyclerViewSearchOnline)}, { t -> onFailure(t) }))
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(
        response: Movies,
        recycler: RecyclerView
    ) {
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchOnline, LinearLayoutManager.VERTICAL, false)
            adapter = MoviesAdapter(response.results,R.layout.movie_item_search)
        }

    }
}
