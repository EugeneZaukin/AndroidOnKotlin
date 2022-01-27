package com.eugene.androidonkotlin.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.eugene.androidonkotlin.databinding.FragmentDescriptionBinding
import com.eugene.androidonkotlin.model.Movie
import com.eugene.androidonkotlin.model.MovieDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val KEY_API = "fe198beca21b5f01d844f2db52d2bb2f"

class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieBundle: Movie

    companion object {
        const val BUNDLE_EXTRA = "movie"

        fun newInstance(bundle: Bundle): DescriptionFragment {
            val fragment = DescriptionFragment()
            fragment.arguments = bundle
            return fragment
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

//        arguments?.getParcelable<Movie>(BUNDLE_EXTRA)?.let { movie ->
//            binding.titleDescription.text = movie.title
//            binding.imageViewDescription.setImageResource(movie.image)
//            binding.textViewAnyInformation.text = movie.rating
//            binding.textViewDescription.text = movie.description
//        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadMovie() {
        try {
            val uri = URL("https://api.kinopoisk.cloud/movies/1143242/token/fe198beca21b5f01d844f2db52d2bb2f")
            val handler = Handler(Looper.getMainLooper())
            Thread(Runnable {
                var urlConnection: HttpsURLConnection? = null
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty("api.kinopoisk.cloud", KEY_API)
                    urlConnection.readTimeout = 10000
                    val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val movieDTO: MovieDTO = Gson().fromJson(getLines(bufferedReader), MovieDTO::class.java)
                    handler.post { displayMovie(movieDTO) }
                } catch (e:Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                } finally {
                    urlConnection?.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun displayMovie(movieDTO: MovieDTO) {
        with(binding) {
            titleDescription.text = movieDTO.title
            textViewAnyInformation.text = movieDTO.rating_kinopoisk
            textViewDescription.text = movieDTO.description
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}