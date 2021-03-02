package com.eugene.androidonkotlin.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import com.eugene.androidonkotlin.viewmodel.MainViewModel
import com.eugene.androidonkotlin.R
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.viewmodel.AppState
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    companion object {
        fun  newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //Почему Observer горит серым? и ничего не просходит,
        // Show context предлагаает вообще удалить этот контруктор
        //В итоге у меня ничего не отображается кроме пустого экрана, и дебаг ниего не дает
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getMovieFromLocal()    }

    private fun renderData(appState: AppState) {
        val loadingLayout = view!!.findViewById<FrameLayout>(R.id.loadingLayout)
        val itemCard = view!!.findViewById<CardView>(R.id.item_card)
        when (appState) {
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }

            is AppState.Success -> {
                val movieSuccess = appState.movieSuccess
                loadingLayout.visibility = View.GONE
                setData(movieSuccess)
            }

            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
                Snackbar.make(itemCard, "Error", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Reload") {viewModel.getMovieFromLocal()}
                        .show()
            }
        }
    }

    private fun setData(movieAppState: Movie) {
        val itemTittle = view!!.findViewById<TextView>(R.id.item_title)
        val itemImage = view!!.findViewById<ImageView>(R.id.item_image)
        val itemRating = view!!.findViewById<TextView>(R.id.item_rating)

        itemTittle.text = movieAppState.title
        itemImage.setImageResource(movieAppState.image)
        itemRating.text = movieAppState.rating
    }
}