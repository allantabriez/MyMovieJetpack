package com.example.jetpacksubmission.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.adapter.FavoriteRecyclerAdapter
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.viewmodel.FavoriteViewModel
import com.example.jetpacksubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteHolderFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var factory: ViewModelFactory
    private lateinit var adapter: FavoriteRecyclerAdapter
    private var state: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        state = arguments?.getInt(ARG_SECTION_NUMBER)

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FavoriteRecyclerAdapter()
        recyclerView.adapter = adapter
        adapter.setOnItemClickCallback(object : FavoriteRecyclerAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Film) {
                val detailFavoriteIntent = Intent(activity, DetailActivity::class.java)
                detailFavoriteIntent.putExtra(DetailActivity.EXTRA_DATA, data)
                detailFavoriteIntent.putExtra(DetailActivity.EXTRA_STATE, state)
                startActivity(detailFavoriteIntent)
            }
        })

        factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        if (state != null) viewModel.getListFavorite(state!!).observe(viewLifecycleOwner, Observer { list ->
            if (list != null) setAdapter(list)
        })
    }

    private fun setAdapter(list: PagedList<Film>){
        favoriteBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): FavoriteHolderFragment {
            return FavoriteHolderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}