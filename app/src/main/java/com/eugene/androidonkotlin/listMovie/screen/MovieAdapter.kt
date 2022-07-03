package com.eugene.androidonkotlin.listMovie.screen

import android.view.*
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.eugene.androidonkotlin.databinding.MainMovieItemBinding
import com.eugene.androidonkotlin.listMovie.screen.model.Movie

class MovieAdapter(
    private val selectMovieId: (Long) -> Unit
) : PagingDataAdapter<Movie, MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MainMovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            selectMovieId = selectMovieId
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MovieViewHolder(
    private val binding: MainMovieItemBinding,
    private val selectMovieId: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie?) {
        with(binding) {
            itemTitle.text = movie?.title
            itemRating.text = movie?.voteAverage.toString()

            itemImage.clipToOutline = true

            Glide.with(root)
                .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
                .into(itemImage)

            root.setOnClickListener {
                selectMovieId(movie?.id!!)
            }
        }
    }
}

class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}