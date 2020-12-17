package com.example.jetpacksubmission.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.data.Film
import kotlinx.android.synthetic.main.item_list.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder>()  {

    private val lists = ArrayList<Film>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(list: List<Film>){
        lists.clear()
        lists.addAll(list)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.bind(lists[position])

    override fun getItemCount(): Int = lists.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Film)
    }
}