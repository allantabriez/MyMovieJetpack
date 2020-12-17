package com.example.jetpacksubmission.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.CatalogueRepository
import com.example.jetpacksubmission.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PageViewModelTest {

    private lateinit var viewModel: PageViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<Film>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = PageViewModel(catalogueRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movieData = MutableLiveData<List<Film>>()
        movieData.value = dummyMovies

        `when`(catalogueRepository.getAllMovies()).thenReturn(movieData)
        val movies = viewModel.getMovies().value
        verify<CatalogueRepository>(catalogueRepository).getAllMovies()
        assertNotNull(movies)
        assertEquals(5, movies?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getShows(){
        val dummyShows = DataDummy.generateDummyShows()
        val showData = MutableLiveData<List<Film>>()
        showData.value = dummyShows

        `when`(catalogueRepository.getAllTvShows()).thenReturn(showData)
        val shows = viewModel.getShows().value
        verify<CatalogueRepository>(catalogueRepository).getAllTvShows()
        assertNotNull(shows)
        assertEquals(5, shows?.size)

        viewModel.getShows().observeForever(observer)
        verify(observer).onChanged(dummyShows)
    }
}