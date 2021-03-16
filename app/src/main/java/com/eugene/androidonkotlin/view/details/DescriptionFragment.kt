package com.eugene.androidonkotlin.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.eugene.androidonkotlin.databinding.FragmentDescriptionBinding
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.model.MovieDTO

const val DETAILS_INTENT_FILTER = "DETAILS_INTENT_FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val DETAILS_INTENT_EMPTY_EXTRA = "INTENT IS EMPTY"
const val DETAILS_RESPONSE_EMPTY_EXTRA = "RESPONSE IS EMPTY"
const val DETAILS_REQUEST_ERROR_EXTRA = "REQUEST ERROR"
const val DETAILS_REQUEST_ERROR_MESSAGE_EXTRA = "REQUEST ERROR MESSAGE"
const val DETAILS_URL_MALFORMED_EXTRA = "URL MALFORMED"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "RESPONSE SUCCESS"
const val DETAILS_TITILE_EXTRA = "TITLE"
const val DETAILS_RATING_EXTRA = "RATING"
const val DETAILS_DESCRIPTION_EXTRA = "DESCRIPTION"

private const val TITLE_INVALID = "NULL"
private const val RATING_INVALID = "NULL"
private const val PROCESS_ERROR = "Обработка ошибки"

class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieBundle: Movie
    private val loadResultReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                DETAILS_INTENT_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_RESPONSE_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REQUEST_ERROR_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REQUEST_ERROR_MESSAGE_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_URL_MALFORMED_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_RESPONSE_SUCCESS_EXTRA -> renderData(
                        MovieDTO(intent.getStringExtra(DETAILS_TITILE_EXTRA),
                                intent.getStringExtra("IMAGE"),
                                intent.getStringExtra(DETAILS_RATING_EXTRA),
                                intent.getStringExtra(DETAILS_DESCRIPTION_EXTRA))
                )
                else -> TODO(PROCESS_ERROR)
            }
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "movie"

        fun newInstance(bundle: Bundle): DescriptionFragment {
            val fragment = DescriptionFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it).registerReceiver(loadResultReceiver, IntentFilter(DETAILS_INTENT_FILTER))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Movie()
        loadMovie()
    }

    private fun loadMovie() {
        context?.let {
            it.startService(Intent(it, DetailsService::class.java).apply {

            })
        }
    }

    private fun renderData(movieDTO: MovieDTO) {
        val title = movieDTO.title
        val rating = movieDTO.rating_kinopoisk
        val description = movieDTO.description

        if (title == TITLE_INVALID || rating == RATING_INVALID || description == null) {
            TODO(PROCESS_ERROR)
        } else {
            with(binding) {
                titleDescription.text = title.toString()
                textViewAnyInformation.text = rating.toString()
                textViewDescription.text = description.toString()
            }
        }
    }

    override fun onDestroy() {
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultReceiver)
        }
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}