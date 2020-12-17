package com.example.jetpacksubmission.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jetpacksubmission.data.Details
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.local.LocalDataSource
import com.example.jetpacksubmission.data.source.local.entity.FavoriteMovie
import com.example.jetpacksubmission.data.source.local.entity.FavoriteShow
import com.example.jetpacksubmission.data.source.remote.RemoteDataSource
import com.example.jetpacksubmission.data.source.remote.response.DetailResponse
import com.example.jetpacksubmission.data.source.remote.response.FilmResponse

class FakeCatalogueRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CatalogueDataSource {

    override fun getAllMovies(): LiveData<List<Film>> {
        val movieResults = MutableLiveData<List<Film>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesReceived(movieResponses: List<FilmResponse>) {
                val movieList = ArrayList<Film>()

                for (response in movieResponses.indices) {
                    val movie = Film(
                        movieResponses[response].id,
                        movieResponses[response].title,
                        movieResponses[response].releaseDate,
                        movieResponses[response].overView,
                        movieResponses[response].imagePath,
                        movieResponses[response].voteAverage,
                        movieResponses[response].voteCount,
                        movieResponses[response].popularity
                    )
                    movieList.add(movie)
                }
                movieResults.value = movieList
            }
        })
        return movieResults
    }

    override fun getAllTvShows(): LiveData<List<Film>> {
        val tvShowResults = MutableLiveData<List<Film>>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsReceived(tvShowResponses: List<FilmResponse>) {
                val tvShowList = ArrayList<Film>()
                for (response in tvShowResponses.indices) {
                    val tvShow = Film(
                        tvShowResponses[response].id,
                        tvShowResponses[response].title,
                        tvShowResponses[response].releaseDate,
                        tvShowResponses[response].overView,
                        tvShowResponses[response].imagePath,
                        tvShowResponses[response].voteAverage,
                        tvShowResponses[response].voteCount,
                        tvShowResponses[response].popularity
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.value = tvShowList
            }
        })
        return tvShowResults
    }

    override fun getMovieDetail(id: Int): LiveData<Details> {
        val movieDetailResult = MutableLiveData<Details>()
        remoteDataSource.getMovieDetail(object : RemoteDataSource.LoadMovieDetailCallback {
            override fun onDetailReceived(detailResponse: DetailResponse) {
                val details = Details(
                    detailResponse.genres,
                    null,
                    detailResponse.runTime,
                    detailResponse.tagLine
                )
                movieDetailResult.value = details
            }
        }, id)
        return movieDetailResult
    }

    override fun getTvDetail(id: Int): LiveData<Details> {
        val tvDetailResult = MutableLiveData<Details>()
        remoteDataSource.getTvDetail(object : RemoteDataSource.LoadTvDetailCallback {
            override fun onDetailReceived(detailResponse: DetailResponse) {
                val details = Details(
                    detailResponse.genres,
                    detailResponse.runTimes,
                    null,
                    null
                )
                tvDetailResult.value = details
            }
        }, id)
        return tvDetailResult
    }

    override fun insertMovie(movie: FavoriteMovie) {
        localDataSource.insertMovie(movie)
    }

    override fun insertShow(show: FavoriteShow) {
        localDataSource.insertShow(show)
    }

    override fun deleteMovie(movie: FavoriteMovie) {
        localDataSource.deleteMovie(movie)
    }

    override fun deleteShow(show: FavoriteShow) {
        localDataSource.deleteShow(show)
    }

    override fun getFavoriteMovies(): LiveData<PagedList<Film>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteShows(): LiveData<PagedList<Film>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteShows(), config).build()
    }

    override fun checkFavoriteMove(id: Int): LiveData<Film> {
        val favorite = MutableLiveData<Film>()
        localDataSource.checkFavoriteMovie(object : LocalDataSource.LoadFavoriteMovie{
            override fun onFavoriteReceived(film: Film?) {
                favorite.postValue(film)
            }
        }, id)
        return favorite
    }

    override fun checkFavoriteShow(id: Int): LiveData<Film> {
        val favorite = MutableLiveData<Film>()
        localDataSource.checkFavoriteShow(object : LocalDataSource.LoadFavoriteShow{
            override fun onFavoriteReceived(film: Film?) {
                favorite.postValue(film)
            }
        }, id)
        return favorite
    }
}