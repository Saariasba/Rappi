package com.example.rappi.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rappi.R
import com.example.rappi.utils.LoadMovies
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val loadMovies = LoadMovies()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress_bar.visibility = View.VISIBLE
        loadMovies.context = this
        loadMovies.progress_bar = progress_bar
        loadMovies.loadMovies(recyclerView,recyclerView2,recyclerView3)
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
