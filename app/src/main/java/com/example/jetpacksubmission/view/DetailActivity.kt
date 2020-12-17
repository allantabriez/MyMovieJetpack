package com.example.jetpacksubmission.view

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.Details
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.viewmodel.DetailViewModel
import com.example.jetpacksubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_STATE = "extra_state"
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var factory: ViewModelFactory
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val film = intent.getParcelableExtra(EXTRA_DATA) as Film
        setSupportActionBar(toolBar)
        val state = intent.getIntExtra(EXTRA_STATE, 0)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.logo_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolBar.title = getString(R.string.dertails)
        collapsingToolBar.setExpandedTitleColor(Color.WHITE)
        collapsingToolBar.setCollapsedTitleTextColor(Color.WHITE)

        factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        viewModel.getFavorite(film.id, state).observe(this, Observer { film ->
            if (film != null && state == 1){
                setFavorite(true)
                loadMovie(film)
                loadImage(film.imagePath as String)
            }
            else if (film != null && state == 2){
                setFavorite(true)
                loadShow(film)
                loadImage(film.imagePath as String)
            } else setFavorite(false)
        })

        viewModel.getDetailInformation(film.id, state).observe(this, Observer { details ->
            if(details != null){
                if (state == 1){
                    if (!isFavorite){
                        loadMovie(film)
                    }
                    loadDetails(details, state)
                }
                else {
                    if (!isFavorite){
                        loadShow(film)
                    }
                    loadDetails(details, state)
                }
                loadImage(film.imagePath as String)
                showUI()
            }
        })

        favoriteFab.setOnClickListener {
            if (!isFavorite) {
                viewModel.insertFavorite(film, state)
                setFavorite(true)
            }
            else {
                viewModel.deleteFavorite(film, state)
                setFavorite(false)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite(boolean: Boolean){
        if (boolean){
            isFavorite = true
            favoriteFab.setImageResource(R.drawable.logo_favorite)
        }
        else {
            isFavorite = false
            favoriteFab.setImageResource(R.drawable.logo_not_favorite)
        }
    }

    private fun loadImage(imagePath: String){
        Glide.with(this)
            .load(imagePath)
            .into(filmImage)
    }

    private fun showUI(){
        detailBar.visibility = View.GONE
        detailView.visibility = View.VISIBLE
        filmImage.visibility = View.VISIBLE
    }

    private fun loadMovie(movie: Film){
        detailTitle.text = movie.title
        detailVoteAverage.text = movie.voteAverage.toString()
        detailRelease.text = movie.releaseDate
        detailDescription.text = movie.overView
        detailPopularity.text = movie.popularity.toString()
    }

    private fun loadShow(show: Film){
        detailTitle.text = show.title
        detailVoteAverage.text = show.voteAverage.toString()
        detailRelease.text = show.releaseDate
        detailDescription.text = show.overView
        detailPopularity.text = show.popularity.toString()
    }

    private fun loadDetails(details: Details, state: Int){
        if (state == 1){
            detailTagLine.text = details.tagLine
            detailRuntime.text = details.runTime.toString()
            detailGenre.text = details.genres.toString()
        }
        else{
            detailTagLine.visibility = View.GONE
            detailGenre.text = details.genres.toString()
            detailRuntime.text = details.runTimes.toString()
        }
    }
}