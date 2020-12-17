package com.example.jetpacksubmission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.CatalogueRepository

class FavoriteViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getListFavorite(state: Int): LiveData<PagedList<Film>> {
        return if (state == 1) catalogueRepository.getFavoriteMovies()
        else catalogueRepository.getFavoriteShows()
    }
}