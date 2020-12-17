package com.example.jetpacksubmission.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.local.entity.FavoriteMovie
import com.example.jetpacksubmission.data.source.local.entity.FavoriteShow


@Dao
interface CatalogueDao {

    @Insert(entity = FavoriteMovie::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(favoriteMovies: FavoriteMovie)

    @Insert(entity = FavoriteShow::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertShow(favoriteShow: FavoriteShow)

    @Query("SELECT * from favorite_movie_table")
    fun getFavoriteMovies(): DataSource.Factory<Int, Film>

    @Query("SELECT * from favorite_show_table")
    fun getFavoriteShows(): DataSource.Factory<Int, Film>

    @Query("SELECT * from favorite_movie_table WHERE id LIKE :searchID")
    fun checkFavoriteMovie(searchID: Int): Film?

    @Query("SELECT * from favorite_show_table WHERE id LIKE :searchID")
    fun checkFavoriteShow(searchID: Int): Film?

    @Delete(entity = FavoriteMovie::class)
    fun deleteMovie(favoriteMovies: FavoriteMovie)

    @Delete(entity = FavoriteShow::class)
    fun deleteShow(favoriteShow: FavoriteShow)
}