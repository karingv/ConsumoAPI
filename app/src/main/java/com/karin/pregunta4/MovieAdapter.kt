package com.karin.pregunta4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieAdapter(private val movieList: MutableList<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie= movieList[position]
        holder.title.text=movie.Title
        holder.year.text=movie.Year
        Picasso.get().load(movie.Poster).into(holder.poster)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title:TextView= itemView.findViewById(R.id.tvTitle)
        val year:TextView= itemView.findViewById(R.id.tvYear)
        val poster: ImageView= itemView.findViewById(R.id.ivPoster)
    }

}


