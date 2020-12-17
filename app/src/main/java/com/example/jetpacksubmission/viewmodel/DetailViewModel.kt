package com.example.jetpacksubmission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacksubmission.data.Details
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.CatalogueRepository
import com.example.jetpacksubmission.data.source.local.entity.FavoriteMovie
import com.example.jetpacksubmission.data.source.local.entity.FavoriteShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    private lateinit var details: LiveData<Details>

    fun getDetailInformation(id: Int, state: Int): LiveData<Details> {
        details = if (state == 1) catalogueRepository.getMovieDetail(id)
        else catalogueRepository.getTvDetail(id)
        return details
    }

    fun getFavorite(filmId: Int, state: Int): LiveData<Film> {
        return if (state == 1) catalogueRepository.checkFavoriteMove(filmId)
        else catalogueRepository.checkFavoriteShow(filmId)
    }

    fun insertFavorite(film: Film, state: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (state == 1) {
                val favoriteMovie = FavoriteMovie(
                    film.id,
                    film.title,
                    film.releaseDate,
                    film.overView,
                    film.imagePath,
                    film.voteAverage,
                    film.voteCount,
                    film.popularity
                )
                catalogueRepository.insertMovie(favoriteMovie)
            } else {
                val favoriteShow = FavoriteShow(
                    film.id,
                    film.title,
                    film.releaseDate,
                    film.overView,
                    film.imagePath,
                    film.voteAverage,
                    film.voteCount,
                    film.popularity
                )
                catalogueRepository.insertShow(favoriteShow)
            }
        }
    }

    fun deleteFavorite(film: Film, state: Int){
        viewModelScope.launch(Dispatchers.IO){
            if (state == 1){
                val favoriteMovie = FavoriteMovie(
                    film.id,
                    film.title,
                    film.releaseDate,
                    film.overView,
                    film.imagePath,
                    film.voteAverage,
                    film.voteCount,
                    film.popularity
                )
                catalogueRepository.deleteMovie(favoriteMovie)
            } else {
                val favoriteShow = FavoriteShow(
                    film.id,
                    film.title,
                    film.releaseDate,
                    film.overView,
                    film.imagePath,
                    film.voteAverage,
                    film.voteCount,
                    film.popularity
                )
                catalogueRepository.deleteShow(favoriteShow)
            }
        }
    }
}