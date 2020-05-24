package com.example.rappi.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rappi.R
import com.example.rappi.db.Movie
import com.example.rappi.db.MoviesDatabase
import com.example.rappi.model.Movies
import com.example.rappi.model.Result
import com.example.rappi.model.ServiceBuilder
import com.example.rappi.utils.API_KEY
import com.example.rappi.utils.MoviesAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movies : ArrayList<Movie> = ArrayList()

    private var moviesDatabase: MoviesDatabase? = null
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moviesDatabase = MoviesDatabase.getInstance(this)
        loadMovies();
    }

    fun insertToDb(movie:Movie){
        compositeDisposable.add(Observable.fromCallable{moviesDatabase?.moviesDao()?.insert(movie)}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }

    private fun loadMovies() {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ServiceBuilder.buildService().getPopularMovies(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response, recyclerView)}, { t -> onFailure(t) }))
        compositeDisposable.add(
            ServiceBuilder.buildService().getTopRatedMovies(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response,recyclerView2)}, {t -> onFailure(t) }))
        compositeDisposable.add(
            ServiceBuilder.buildService().getUpComingMovies(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response,recyclerView3)}, {t -> onFailure(t) }))
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
    }

    private fun onResponse(
        response: Movies,
        recyclerView: RecyclerView
    ) {
        var list : List<Result> = response.results
        for (i in 0 until list.size) {
            var movieNow = list.get(i)
            val movieBD = Movie(movieNow.id,movieNow.overview,movieNow.poster_path,movieNow.release_date,movieNow.title,movieNow.vote_average,movieNow.vote_count)
            insertToDb(movieBD)
        }
        progress_bar.visibility = View.GONE
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = MoviesAdapter(response.results,R.layout.movie_item)
        }

    }

    /* Called when the user touches the button Search Online */
    fun doSearchOnline(view: View?) {
        val intent = Intent(this, SearchOnline::class.java)
        startActivity(intent)
    }

    /* Called when the user touches the button Search Offline */
    fun doSearchOffline(view: View?) {
        val intent = Intent(this, SearchOffline::class.java)
        startActivity(intent)
    }
}
