package com.eugene.androidonkotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.model.Movie

class MainFragmentAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?): RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var movieData: List<Movie> = listOf()

    fun setMovie(data: List<Movie>) {
        movieData = data;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFragmentAdapter.MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
                as View)
    }

    override fun onBindViewHolder(holder: MainFragmentAdapter.MainViewHolder, position: Int) {
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
            itemView.findViewById<CardView>(R.id.main_fragment_recycler_view)
            itemView.findViewById<TextView>(R.id.item_title).text = movie.title
            itemView.findViewById<ImageView>(R.id.item_image).setImageResource(movie.image)
            itemView.findViewById<TextView>(R.id.item_rating).text = movie.rating

            itemView.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(movie)
            }
        }
    }
}


