package com.example.rappi.utils

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rappi.R
import com.example.rappi.`object`.movieObject
import com.example.rappi.model.Result
import com.example.rappi.ui.Detail

class MoviesAdapter(val movies: List<Result>, val layout:Int): RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MoviesViewHolder(view).listen { pos, type ->
            val movie : Result = movies.get(pos)
            val sendMovie = movieObject(movie)
            Log.d("Prueba",movie.toString())
            val intent = Intent(parent.context, Detail::class.java).apply {
                putExtra("Movie", sendMovie)
            }
            parent.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        return holder.bind(movies[position])
    }
}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(getAdapterPosition(), getItemViewType())
    }
    return this
}

class MoviesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val photo:ImageView = itemView.findViewById(R.id.movie_photo)
    private val rating:TextView = itemView.findViewById(R.id.movie_rating)


    fun bind(movie: Result) {
        Glide.with(itemView.context).load(URL_IMAGE+"${movie.poster_path}").into(photo)
        rating.text = movie.vote_average.toString()
    }

}
