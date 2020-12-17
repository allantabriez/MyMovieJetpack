package com.example.jetpacksubmission.data.source.remote

import android.util.Log
import com.example.jetpacksubmission.data.source.remote.response.DetailResponse
import com.example.jetpacksubmission.data.source.remote.response.FilmResponse
import com.example.jetpacksubmission.utils.EspressoIdlingResource
import com.example.jetpacksubmission.utils.LoopJ

class RemoteDataSource private constructor(private  val loopJ: LoopJ){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(j: LoopJ): RemoteDataSource = instance ?: synchronized(this){
            instance ?: RemoteDataSource(j)
        }
    }

    fun getMovies(callback: LoadMoviesCallback){
        EspressoIdlingResource.increment()
        loopJ.getMovies(object : LoopJ.GetMovieCallback{
            override fun onFinishedRetrieving(movieResponse: List<FilmResponse>) {
                Log.d("dataSource", movieResponse.toString())
                callback.onMoviesReceived(movieResponse)
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShows(callback: LoadTvShowsCallback){
        EspressoIdlingResource.increment()
        loopJ.getTvShows(object : LoopJ.GetTvShowsCallback{
            override fun onFinishedRetrieving(tvShowResponse: List<FilmResponse>) {
                callback.onTvShowsReceived(tvShowResponse)
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getMovieDetail(callback: LoadMovieDetailCallback, id: Int){
        EspressoIdlingResource.increment()
        loopJ.getMovieDetail(object : LoopJ.GetMovieDetailCallback{
            override fun onFinishedRetrieving(detailResponse: DetailResponse) {
                callback.onDetailReceived(detailResponse)
                EspressoIdlingResource.decrement()
            }
        }, id)
    }

    fun getTvDetail(callback: LoadTvDetailCallback, id: Int){
        EspressoIdlingResource.increment()
        loopJ.getTvShowDetail(object : LoopJ.GetTvShowDetailCallback{
            override fun onFinishedRetrieving(detailResponse: DetailResponse) {
                callback.onDetailReceived(detailResponse)
                EspressoIdlingResource.decrement()
            }
        }, id)
    }

    interface LoadMoviesCallback {
        fun onMoviesReceived(movieResponses: List<FilmResponse>)
    }

    interface LoadTvShowsCallback {
        fun onTvShowsReceived(tvShowResponses: List<FilmResponse>)
    }

    interface LoadMovieDetailCallback {
        fun onDetailReceived(detailResponse: DetailResponse)
    }

    interface LoadTvDetailCallback {
        fun onDetailReceived(detailResponse: DetailResponse)
    }
}