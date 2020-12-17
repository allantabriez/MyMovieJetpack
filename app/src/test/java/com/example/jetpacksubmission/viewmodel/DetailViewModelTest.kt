package com.example.jetpacksubmission.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.jetpacksubmission.data.Details
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.CatalogueRepository
import com.example.jetpacksubmission.utils.DataDummy
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
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Details>

    @Mock
    private lateinit var favoriteObserver: Observer<Film>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovieDetails(){
        val dummyMovie = DataDummy.generateDummyMovies()[0]
        val dummyDetailsData = MutableLiveData<Details>()
        val dummyDetails = DataDummy.generateDummyMovieDetails()
        dummyDetailsData.value = dummyDetails

        `when`(catalogueRepository.getMovieDetail(dummyMovie.id)).thenReturn(dummyDetailsData)
        val movieDetails = viewModel.getDetailInformation(dummyMovie.id, 1).value as Details
        verify(catalogueRepository).getMovieDetail(dummyMovie.id)
        assertNotNull(movieDetails)
        assertEquals(dummyDetails.genres, movieDetails.genres)
        assertEquals(dummyDetails.runTime, movieDetails.runTime)
        assertEquals(dummyDetails.runTimes, movieDetails.runTimes)
        assertEquals(dummyDetails.tagLine, movieDetails.tagLine)

        viewModel.getDetailInformation(dummyMovie.id, 1).observeForever(observer)
        verify(observer).onChanged(dummyDetails)
    }

    @Test
    fun getShowDetails(){
        val dummyShow = DataDummy.generateDummyShows()[0]
        val dummyDetailsData = MutableLiveData<Details>()
        val dummyDetails = DataDummy.generateDummyShowDetails()
        dummyDetailsData.value = dummyDetails

        `when`(catalogueRepository.getTvDetail(dummyShow.id)).thenReturn(dummyDetailsData)
        val showDetails = viewModel.getDetailInformation(dummyShow.id, 2).value as Details
        verify(catalogueRepository).getTvDetail(dummyShow.id)
        assertNotNull(showDetails)
        assertEquals(dummyDetails.genres, showDetails.genres)
        assertEquals(dummyDetails.runTimes, showDetails.runTimes)
        assertEquals(dummyDetails.runTime, showDetails.runTime)
        assertEquals(dummyDetails.tagLine, showDetails.tagLine)

        viewModel.getDetailInformation(dummyShow.id, 2).observeForever(observer)
        verify(observer).onChanged(dummyDetails)
    }

    @Test
    fun getFavorite(){
        val dummyMovie = DataDummy.generateDummyMovies()[0]
        val dummyFavorite = MutableLiveData<Film>()
        dummyFavorite.value = dummyMovie

        `when`(catalogueRepository.checkFavoriteMove(dummyMovie.id)).thenReturn(dummyFavorite)
        val favorite = viewModel.getFavorite(dummyMovie.id, 1).value as Film
        verify(catalogueRepository).checkFavoriteMove(dummyMovie.id)
        assertNotNull(favorite)
        assertEquals(dummyMovie.id, favorite.id)
        assertEquals(dummyMovie.imagePath, favorite.imagePath)
        assertEquals(dummyMovie.overView, favorite.overView)
        assertEquals(dummyMovie.popularity, favorite.popularity)
        assertEquals(dummyMovie.releaseDate, favorite.releaseDate)
        assertEquals(dummyMovie.title, favorite.title)
        assertEquals(dummyMovie.voteCount, favorite.voteCount)

        viewModel.getFavorite(dummyMovie.id, 1).observeForever(favoriteObserver)
        verify(favoriteObserver).onChanged(dummyMovie)
    }
}