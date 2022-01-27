package com.eugene.androidonkotlin.view.main

import android.view.*
import com.bumptech.glide.Glide
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.databinding.RecyclerItemBinding
import com.eugene.androidonkotlin.model.Movie
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class MovieItem(private val movie: Movie): AbstractBindingItem<RecyclerItemBinding>() {
    override val type: Int
        get() = R.id.item_card

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): RecyclerItemBinding {
        return RecyclerItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: RecyclerItemBinding, payloads: List<Any>) {
        binding.apply {
            itemTitle.text = movie.title
            itemRating.text = movie.voteAverage.toString()

            Glide.with(root)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(itemImage)
        }
    }
}