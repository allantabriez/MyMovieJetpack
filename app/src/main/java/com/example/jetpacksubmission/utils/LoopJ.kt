package com.example.jetpacksubmission.utils

import android.util.Log
import com.example.jetpacksubmission.BuildConfig
import com.example.jetpacksubmission.data.source.remote.response.DetailResponse
import com.example.jetpacksubmission.data.source.remote.response.FilmResponse
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.io.IOException

class LoopJ {

    private val client = AsyncHttpClient()
    private val apiKey = BuildConfig.API_KEY
    private lateinit var responseHandler: AsyncHttpResponseHandler
    private val moviesUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=${apiKey}&language=en-US&page=1"
    private val tvShowsUrl = "https://api.themoviedb.org/3/tv/on_the_air?api_key=${apiKey}&page=1"
    private val loopJTag = "LoopJ"
    private val imageUrl = "https://image.tmdb.org/t/p/w300"

    fun getMovies(callback: GetMovieCallback) {
        client.addHeader("Authorization", "token <$apiKey>")
        client.addHeader("User-Agent", "request")
        responseHandler = object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                val result = String(responseBody)
                val itemList = ArrayList<FilmResponse>()
                try {
                    val responseObject = JSONObject(result)
                    val dataArray = responseObject.getJSONArray("results")
                    for (movies in 0 until dataArray.length()) {
                        val dataObject = dataArray.getJSONObject(movies)
                        val imagePathResponse = dataObject.getString("poster_path")
                        val movieResponse = FilmResponse(
                            dataObject.getInt("id"),
                            dataObject.getString("title"),
                            dataObject.getString("release_date"),
                            dataObject.getString("overview"),
                            imageUrl + imagePathResponse,
                            dataObject.getDouble("vote_average"),
                            dataObject.getInt("vote_count"),
                            dataObject.getInt("popularity")
                        )
                        itemList.add(movieResponse)
                    }
                    callback.onFinishedRetrieving(itemList)
                    Log.d("response", itemList.toString())
                } catch (e: IOException) {
                    Log.d(loopJTag, e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray, error: Throwable) {
                Log.d(loopJTag, error.message.toString())
            }
        }
        client.get(moviesUrl, responseHandler)
    }

    fun getTvShows(callback: GetTvShowsCallback) {
        client.addHeader("Authorization", "token <$apiKey>")
        client.addHeader("User-Agent", "request")
        val itemList = ArrayList<FilmResponse>()
        responseHandler = object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                val result = String(responseBody)
                try {
                    val responseObject = JSONObject(result)
                    val dataArray = responseObject.getJSONArray("results")
                    for (tvShows in 0 until dataArray.length()) {
                        val dataObject = dataArray.getJSONObject(tvShows)
                        val imagePathResponse = dataObject.getString("poster_path")
                        val tvShowResponse = FilmResponse(
                            dataObject.getInt("id"),
                            dataObject.getString("name"),
                            dataObject.getString("first_air_date"),
                            dataObject.getString("overview"),
                            imageUrl + imagePathResponse,
                            dataObject.getDouble("vote_average"),
                            dataObject.getInt("vote_count"),
                            dataObject.getInt("popularity")
                        )
                        itemList.add(tvShowResponse)
                    }
                    callback.onFinishedRetrieving(itemList)

                } catch (e: IOException) {
                    Log.d(loopJTag, e.message.toString())
                }

            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable) {
                Log.d(loopJTag, error.message.toString())
            }
        }
        client.get(tvShowsUrl, responseHandler)
    }

    fun getMovieDetail(callback: GetMovieDetailCallback, id: Int){
        client.addHeader("Authorization", "token <$apiKey>")
        client.addHeader("User-Agent", "request")
        responseHandler = object : AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>, responseBody: ByteArray) {
                val result = String(responseBody)
                try {
                    val responseObject = JSONObject(result)
                    val genreResponse = responseObject.getJSONArray("genres")
                    val genreArrayList = ArrayList<String>()
                    for (i in 0 until genreResponse.length()) genreArrayList.add(genreResponse.getJSONObject(i).getString("name"))
                    val detailResponse = DetailResponse(
                        genreArrayList,
                        null,
                        responseObject.getInt("runtime"),
                        responseObject.getString("tagline")
                    )
                    callback.onFinishedRetrieving(detailResponse)
                } catch (e: IOException){
                    Log.d(loopJTag, e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray, error: Throwable) {
                Log.d(loopJTag, error.message.toString())
            }
        }
        val url = "https://api.themoviedb.org/3/movie/$id?api_key=$apiKey&language=en-US"
        client.get(url, responseHandler)
    }

    fun getTvShowDetail(callback: GetTvShowDetailCallback, id: Int){
        client.addHeader("Authorization", "token <$apiKey>")
        client.addHeader("User-Agent", "request")
        responseHandler = object : AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>, responseBody: ByteArray) {
                val result = String(responseBody)
                try {
                    val responseObject = JSONObject(result)
                    val genreResponse = responseObject.getJSONArray("genres")
                    val genreArrayList = ArrayList<String>()
                    for (i in 0 until genreResponse.length()) genreArrayList.add(genreResponse.getJSONObject(i).getString("name"))
                    val runtimeResponse = responseObject.getJSONArray("episode_run_time")
                    val runtimeArrayList = ArrayList<Int>()
                    for (i in 0 until runtimeResponse.length()) runtimeArrayList.add(runtimeResponse.getInt(i))
                    val detailResponse = DetailResponse(
                        genreArrayList,
                        runtimeArrayList,
                        null,
                        null
                    )
                    callback.onFinishedRetrieving(detailResponse)
                } catch (e: IOException){
                    Log.d(loopJTag, e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>, responseBody: ByteArray?, error: Throwable) {
                Log.d(loopJTag, error.message.toString())
            }
        }
        val url = "https://api.themoviedb.org/3/tv/$id?api_key=$apiKey&language=en-US"
        client.get(url, responseHandler)
    }

    interface GetMovieCallback {
        fun onFinishedRetrieving(movieResponse: List<FilmResponse>)
    }

    interface GetTvShowsCallback {
        fun onFinishedRetrieving(tvShowResponse: List<FilmResponse>)
    }

    interface GetMovieDetailCallback {
        fun onFinishedRetrieving(detailResponse: DetailResponse)
    }

    interface GetTvShowDetailCallback {
        fun onFinishedRetrieving(detailResponse: DetailResponse)
    }
}