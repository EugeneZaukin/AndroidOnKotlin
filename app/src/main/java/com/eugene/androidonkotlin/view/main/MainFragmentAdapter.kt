package com.eugene.androidonkotlin.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.model.Movie
import com.squareup.picasso.Picasso

class MainFragmentAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?): RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var movieData: List<Movie> = listOf()

    fun setMovie(data: List<Movie>) {
        movieData = data;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
                as View)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            itemView.apply {
                findViewById<CardView>(R.id.main_fragment_recycler_view)
                findViewById<TextView>(R.id.item_title).text = movie.title
                Picasso.get().load("https://res.cloudinary.com/demo/video/upload/dog.png").into(findViewById<ImageView>(R.id.item_image))
                findViewById<TextView>(R.id.item_rating).text = movie.rating
                setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
            }
        }
    }
}


