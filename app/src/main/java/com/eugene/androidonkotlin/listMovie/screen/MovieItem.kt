package com.eugene.androidonkotlin.listMovie.screen

import android.view.*
import com.bumptech.glide.Glide
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.databinding.MainMovieItemBinding
import com.eugene.androidonkotlin.listMovie.screen.model.Movie
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class MovieItem(private val mainMovie: Movie): AbstractBindingItem<MainMovieItemBinding>() {
    override val type: Int
        get() = R.id.item_card

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): MainMovieItemBinding {
        return MainMovieItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: MainMovieItemBinding, payloads: List<Any>) {
        binding.apply {
            itemTitle.text = mainMovie.title
            itemRating.text = mainMovie.voteAverage.toString()

            //for rounding corners
            itemImage.clipToOutline = true

            Glide.with(root)
                .load("https://image.tmdb.org/t/p/w500${mainMovie.posterPath}")
                .into(itemImage)
        }
    }
}