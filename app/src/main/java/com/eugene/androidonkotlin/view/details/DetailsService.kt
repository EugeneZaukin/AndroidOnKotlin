package com.eugene.androidonkotlin.view.details

import android.app.IntentService
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.eugene.androidonkotlin.model.MovieDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val REQUEST_GET = "GET"
private const val REQUEST_TIMEOUT = 10_000
private const val REQUEST_API_KEY = "api.kinopoisk.cloud"
private const val MOVIE_KEY_API = "fe198beca21b5f01d844f2db52d2bb2f"

class DetailsService(name: String = "DetailService") : IntentService(name) {

    private val broadcastIntent = Intent(DETAILS_INTENT_FILTER)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        if (intent == null) {
            onEmptyIntent()
        } else {
            loadMovie()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadMovie() {
        try {
            val uri = URL("https://api.kinopoisk.cloud/movies/1143242/token/fe198beca21b5f01d844f2db52d2bb2f")
            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    requestMethod = REQUEST_GET
                    readTimeout = REQUEST_TIMEOUT
                    addRequestProperty(REQUEST_API_KEY, MOVIE_KEY_API)
                }

                val movieDTO: MovieDTO = Gson().fromJson(getLines(BufferedReader(InputStreamReader(urlConnection.inputStream))),
                MovieDTO::class.java)
                onResponse(movieDTO)
            } catch (e: Exception) {
                onErrorRequest(e.message?: "Empty Error")
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            onMalformedURL()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun onResponse(movieDto: MovieDTO) {
        if (movieDto == null) {
            onEmptyResponse()
        } else {
            onSuccessResponse(movieDto.title, movieDto.rating_kinopoisk, movieDto.description)
        }
    }

    private fun onSuccessResponse(title: String?, rating_kinopoisk: String?, description: String?) {
        putLoadResult(DETAILS_RESPONSE_SUCCESS_EXTRA)
        broadcastIntent.putExtra(DETAILS_TITILE_EXTRA, title)
        broadcastIntent.putExtra(DETAILS_RATING_EXTRA, rating_kinopoisk)
        broadcastIntent.putExtra(DETAILS_DESCRIPTION_EXTRA, description)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onMalformedURL() {
        putLoadResult(DETAILS_URL_MALFORMED_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onErrorRequest(error: String) {
        putLoadResult(DETAILS_REQUEST_ERROR_EXTRA)
        broadcastIntent.putExtra(DETAILS_REQUEST_ERROR_MESSAGE_EXTRA, error)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onEmptyResponse() {
        putLoadResult(DETAILS_RESPONSE_EMPTY_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onEmptyIntent() {
        putLoadResult(DETAILS_INTENT_EMPTY_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun putLoadResult(result: String) {
        broadcastIntent.putExtra(DETAILS_LOAD_RESULT_EXTRA, result)
    }
}