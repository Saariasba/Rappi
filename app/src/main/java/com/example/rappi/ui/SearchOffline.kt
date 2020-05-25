package com.example.rappi.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rappi.R
import com.example.rappi.utils.SearchOfflineMovies
import kotlinx.android.synthetic.main.activity_search_offline.*


class SearchOffline : AppCompatActivity() {

    private val searchOfflineMovies = SearchOfflineMovies()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_offline)
        progress_bar_offline.visibility = View.INVISIBLE
        searchOfflineMovies.context = this
        searchOfflineMovies.progress_bar = progress_bar_offline
    }

    /* Called when the user touches the buttons to Search */
    fun doSearch(view: View?) {
        progress_bar_offline.visibility = View.VISIBLE
        if (view != null) {
            if(view.id == R.id.Popular){
                searchOfflineMovies.getBy("%"+textToSearchOffline.text+"%","Popular",recyclerViewSearchOffline)
            }else if(view.id == R.id.TopRated){
                searchOfflineMovies.getBy("%"+textToSearchOffline.text+"%","TopRated",recyclerViewSearchOffline)
            }else if(view.id == R.id.Upcoming){
                searchOfflineMovies.getBy("%"+textToSearchOffline.text+"%","UpComing",recyclerViewSearchOffline)
            }
        }
    }

    /* Called when the user touches the button Back */
    fun doMain(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
}
