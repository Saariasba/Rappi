package com.example.rappi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rappi.R
import com.example.rappi.utils.SearchOnlineMovies
import kotlinx.android.synthetic.main.activity_search_online.*

class SearchOnline : AppCompatActivity() {

    private val searchOnlineMovies = SearchOnlineMovies()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_online)
        progress_bar_online.visibility = View.INVISIBLE
        searchOnlineMovies.context = this
        searchOnlineMovies.progress_bar = progress_bar_online
    }

    /* Called when the user touches the button Search Online */
    fun doSearch(view: View?) {
        searchOnlineMovies.Search(textToSearchOnline,recyclerViewSearchOnline)
    }

    /* Called when the user touches the button Back */
    fun doMain(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
}
