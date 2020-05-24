package com.example.rappi.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rappi.R
import com.example.rappi.`object`.movieObject
import com.example.rappi.utils.URL_IMAGE

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val photo: ImageView = findViewById(R.id.movie_photo_detail)
        val rating: TextView = findViewById(R.id.movie_rating_detail)
        val movieName: TextView = findViewById(R.id.movie_name_detail)

        val movie = intent.getSerializableExtra("Movie") as? movieObject
        if (movie != null) {
            Glide.with(this).load(URL_IMAGE +"${movie.poster_path}").into(photo)
            rating.text = movie.vote_average.toString()
            movieName.text = movie.title
        }
    }
}
