package com.example.jetpacksubmission.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.CatalogueRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest{

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<Film>>

    @Mock
    private lateinit var pagedLst: PagedList<Film>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = FavoriteViewModel(catalogueRepository)
    }

    @Test
    fun getFavorites(){
        val dummyFavorites = pagedLst
        `when`(dummyFavorites.size).thenReturn(10)
        val dummyFavoriteData = MutableLiveData<PagedList<Film>>()
        dummyFavoriteData.value = dummyFavorites

        `when`(catalogueRepository.getFavoriteMovies()).thenReturn(dummyFavoriteData)
        val movies = viewModel.getListFavorite(1)
        verify(catalogueRepository).getFavoriteMovies()
        assertNotNull(movies)
        assertEquals(10, movies.value?.size)

        viewModel.getListFavorite(1).observeForever(observer)
        verify(observer).onChanged(dummyFavorites)
    }
}