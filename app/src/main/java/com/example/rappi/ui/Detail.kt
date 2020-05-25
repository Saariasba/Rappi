package com.example.rappi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rappi.R
import com.example.rappi.`object`.movieObject
import com.example.rappi.utils.URL_IMAGE

//This class show the movie details
class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val photo: ImageView = findViewById(R.id.movie_photo_detail)
        val rating: TextView = findViewById(R.id.movie_rating_detail)
        val movieName: TextView = findViewById(R.id.movie_name_detail)
        val movieOverview: TextView = findViewById(R.id.movie_overview_text_detail)
        val movieReleaseDate: TextView = findViewById(R.id.movie_release_date_text_detail)

        val movie = intent.getSerializableExtra("Movie") as? movieObject
        if (movie != null) {
            Glide.with(this).load(URL_IMAGE +"${movie.poster_path}").into(photo)
            rating.text = movie.vote_average.toString()
            movieName.text = movie.title
            movieOverview.text = movie.overview
            movieReleaseDate.text = movie.release_date
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
