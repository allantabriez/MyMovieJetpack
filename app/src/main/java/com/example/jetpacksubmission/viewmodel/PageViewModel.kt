package com.example.jetpacksubmission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.CatalogueRepository

class PageViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovies(): LiveData<List<Film>> = catalogueRepository.getAllMovies()
    fun getShows(): LiveData<List<Film>> = catalogueRepository.getAllTvShows()
}