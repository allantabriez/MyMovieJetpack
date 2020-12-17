package com.example.jetpacksubmission.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.Film
import kotlinx.android.synthetic.main.item_list.view.*

class FavoriteRecyclerAdapter internal constructor(): PagedListAdapter<Film, FavoriteRecyclerAdapter.FilmViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Film>(){
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class FilmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(film: Film){
            with(itemView){
                Glide.with(itemView.context)
                    .load(film.imagePath)
                    .centerInside()
                    .into(itemImage)
                itemTitle.text = film.title
                itemReleaseDate.text = film.releaseDate
                itemOverview.text = film.overView
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(film) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = getItem(position)
        if (film != null) holder.bind(film)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Film)
    }
}