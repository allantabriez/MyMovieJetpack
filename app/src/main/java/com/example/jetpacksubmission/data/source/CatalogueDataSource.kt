package com.example.jetpacksubmission.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.jetpacksubmission.data.Details
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.local.entity.FavoriteMovie
import com.example.jetpacksubmission.data.source.local.entity.FavoriteShow

interface CatalogueDataSource {
    fun getAllMovies(): LiveData<List<Film>>
    fun getAllTvShows(): LiveData<List<Film>>
    fun getMovieDetail(id: Int): LiveData<Details>
    fun getTvDetail(id: Int): LiveData<Details>
    fun insertMovie(movie: FavoriteMovie)
    fun insertShow(show: FavoriteShow)
    fun deleteMovie(movie: FavoriteMovie)
    fun deleteShow(show: FavoriteShow)
    fun getFavoriteMovies(): LiveData<PagedList<Film>>
    fun getFavoriteShows(): LiveData<PagedList<Film>>
    fun checkFavoriteMove(id: Int): LiveData<Film>
    fun checkFavoriteShow(id: Int): LiveData<Film>
}