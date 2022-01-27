package com.eugene.androidonkotlin.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.databinding.RecyclerItemBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class MovieItem(): AbstractBindingItem<RecyclerItemBinding>() {
    override val type: Int
        get() = R.id.item_card

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): RecyclerItemBinding {
        return RecyclerItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: RecyclerItemBinding, payloads: List<Any>) {
        binding.itemTitle.text = "Android"
        binding.itemRating.text = "7.8"
    }
}