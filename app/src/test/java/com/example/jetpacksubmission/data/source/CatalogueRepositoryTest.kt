package com.example.jetpacksubmission.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.example.jetpacksubmission.PagedListUtil
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.local.LocalDataSource
import com.example.jetpacksubmission.data.source.remote.RemoteDataSource
import com.example.jetpacksubmission.utils.DataDummy
import com.example.jetpacksubmission.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote, local)
    private val movieResponses = DataDummy.generateDummyMovieResponses()
    private val showResponses = DataDummy.generateDummyShowResponses()
    private val movieDetailResponse = DataDummy.generateDummyMovieDetailsResponse()
    private val showDetailResponse = DataDummy.generateDummyShowDetailsResponse()
    private val movieID = movieResponses[0].id
    private val showID = showResponses[0].id

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMoviesReceived(movieResponses)
            null
        }.`when`(remote).getMovies(any())
        val movies = LiveDataTestUtil.getValue(catalogueRepository.getAllMovies())
        verify(remote).getMovies(any())
        assertNotNull(movies)
        assertEquals(movieResponses.size, movies.size)
    }

    @Test
    fun getAllShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onTvShowsReceived(showResponses)
            null
        }.`when`(remote).getTvShows(any())
        val shows = LiveDataTestUtil.getValue(catalogueRepository.getAllTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(shows)
        assertEquals(movieResponses.size, shows.size)
    }

    @Test
    fun getMovieDetails(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieDetailCallback).onDetailReceived(movieDetailResponse)
            null
        }.`when`(remote).getMovieDetail(any(), eq(movieID))
        val movieDetails = LiveDataTestUtil.getValue(catalogueRepository.getMovieDetail(movieID))
        verify(remote).getMovieDetail(any(), eq(movieID))
        assertNotNull(movieDetails)
        assertEquals(movieDetailResponse.genres, movieDetails.genres)
        assertEquals(movieDetailResponse.tagLine, movieDetails.tagLine)
        assertEquals(movieDetailResponse.runTime, movieDetails.runTime)
        assertEquals(movieDetailResponse.runTimes, movieDetails.runTimes)
    }

    @Test
    fun getShowDetails(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvDetailCallback).onDetailReceived(showDetailResponse)
            null
        }.`when`(remote).getTvDetail(any(), eq(showID))
        val showDetails = LiveDataTestUtil.getValue(catalogueRepository.getTvDetail(showID))
        verify(remote).getTvDetail(any(), eq(showID))
        assertNotNull(showDetails)
        assertEquals(showDetailResponse.genres, showDetails.genres)
        assertEquals(showDetailResponse.tagLine, showDetails.tagLine)
        assertEquals(showDetailResponse.runTime, showDetails.runTime)
        assertEquals(showDetailResponse.runTimes, showDetails.runTimes)
    }

    @Test
    fun getFavoriteMovies(){
        val dataSourceFactory = mock(androidx.paging.DataSource.Factory::class.java) as DataSource.Factory<Int, Film>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoriteMovies()
        val favoriteMovies = PagedListUtil.mockPagedList(DataDummy.generateDummyMovies())
        verify(local).getFavoriteMovies()
        assertNotNull(favoriteMovies)
        assertEquals(movieResponses.size.toLong(), favoriteMovies.size.toLong())
    }

    @Test
    fun getFavoriteShows(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Film>
        `when`(local.getFavoriteShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoriteShows()
        val favoriteShows = PagedListUtil.mockPagedList(DataDummy.generateDummyShows())
        verify(local).getFavoriteShows()
        assertNotNull(favoriteShows)
        assertEquals(showResponses.size.toLong(), favoriteShows.size.toLong())
    }

    @Test
    fun checkFavoriteMovie(){
        val dummyMovie = DataDummy.generateDummyMovies()[0]
        doAnswer { invocation ->
            (invocation.arguments[0] as LocalDataSource.LoadFavoriteMovie).onFavoriteReceived(dummyMovie)
            null
        }.`when`(local).checkFavoriteMovie(any(), eq(movieID))
        val favoriteMovie = LiveDataTestUtil.getValue(catalogueRepository.checkFavoriteMove(movieID))
        verify(local).checkFavoriteMovie(any(), eq(movieID))
        assertNotNull(favoriteMovie)
        assertEquals(dummyMovie.id, favoriteMovie.id)
        assertEquals(dummyMovie.title, favoriteMovie.title)
        assertEquals(dummyMovie.imagePath, favoriteMovie.imagePath)
        assertEquals(dummyMovie.overView, favoriteMovie.overView)
        assertEquals(dummyMovie.releaseDate, favoriteMovie.releaseDate)
        assertEquals(dummyMovie.voteCount, favoriteMovie.voteCount)
        assertEquals(dummyMovie.popularity, favoriteMovie.popularity)
    }

    @Test
    fun checkFavoriteShow(){
        val dummyShow = DataDummy.generateDummyShows()[0]
        doAnswer { invocation ->
            (invocation.arguments[0] as LocalDataSource.LoadFavoriteShow).onFavoriteReceived(dummyShow)
            null
        }.`when`(local).checkFavoriteShow(any(), eq(showID))
        val favoriteShow = LiveDataTestUtil.getValue(catalogueRepository.checkFavoriteShow(showID))
        verify(local).checkFavoriteShow(any(), eq(showID))
        assertNotNull(favoriteShow)
        assertEquals(dummyShow.id, favoriteShow.id)
        assertEquals(dummyShow.title, favoriteShow.title)
        assertEquals(dummyShow.imagePath, favoriteShow.imagePath)
        assertEquals(dummyShow.overView, favoriteShow.overView)
        assertEquals(dummyShow.releaseDate, favoriteShow.releaseDate)
        assertEquals(dummyShow.voteCount, favoriteShow.voteCount)
        assertEquals(dummyShow.popularity, favoriteShow.popularity)
    }
}