package com.example.jetpacksubmission.data.source.local

import androidx.paging.DataSource
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.local.entity.FavoriteMovie
import com.example.jetpacksubmission.data.source.local.entity.FavoriteShow
import com.example.jetpacksubmission.data.source.local.room.CatalogueDao
import com.example.jetpacksubmission.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSource private constructor(private val catalogueDao: CatalogueDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource = INSTANCE ?: LocalDataSource(catalogueDao)
    }

    fun insertMovie(movie: FavoriteMovie) = catalogueDao.insertMovie(movie)
    fun insertShow(show: FavoriteShow) = catalogueDao.insertShow(show)
    fun getFavoriteMovies(): DataSource.Factory<Int, Film>{
        EspressoIdlingResource.increment()
        val dataSource = catalogueDao.getFavoriteMovies()
        EspressoIdlingResource.decrement()
        return dataSource
    }
    fun getFavoriteShows(): DataSource.Factory<Int, Film>{
        EspressoIdlingResource.increment()
        val dataSource = catalogueDao.getFavoriteShows()
        EspressoIdlingResource.decrement()
        return dataSource
    }
    fun checkFavoriteMovie(callback: LoadFavoriteMovie, id: Int) {
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            callback.onFavoriteReceived(catalogueDao.checkFavoriteMovie(id))
            EspressoIdlingResource.decrement()
        }
    }

    fun checkFavoriteShow(callback: LoadFavoriteShow, id: Int){
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            callback.onFavoriteReceived(catalogueDao.checkFavoriteShow(id))
            EspressoIdlingResource.decrement()
        }
    }

    fun deleteMovie(movie: FavoriteMovie) = catalogueDao.deleteMovie(movie)
    fun deleteShow(show: FavoriteShow) = catalogueDao.deleteShow(show)

    interface LoadFavoriteMovie {
        fun onFavoriteReceived(film: Film?)
    }

    interface LoadFavoriteShow {
        fun onFavoriteReceived(film: Film?)
    }
}