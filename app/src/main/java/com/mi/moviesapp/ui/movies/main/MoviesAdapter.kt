package com.mi.moviesapp.ui.movies.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mi.moviesapp.R
import com.mi.moviesapp.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesAdapter(var items: MutableList<Movie>, var listener : OnClickListener) :
    RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val mView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(items[position],listener)
    }
}

class MoviesViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
    fun bind(movie: Movie, listener: OnClickListener) {
        val p: List<String> = movie.image.split("/")
        val imageLink = "https://drive.google.com/uc?export=download&id=" + p[5]
        Picasso.get().load(imageLink).placeholder(R.drawable.ic_launcher_background).into(itemView.imgMovie)
        itemView.setOnClickListener { listener.onClick(movie) }
    }
}
