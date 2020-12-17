package com.example.jetpacksubmission.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.adapter.RecyclerViewAdapter
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.viewmodel.PageViewModel
import com.example.jetpacksubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*

class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var factory: ViewModelFactory
    private lateinit var adapter: RecyclerViewAdapter
    private var state: Int? = null

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter
        adapter.setOnItemClickCallback(object : RecyclerViewAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Film) {
                moveToDetailActivity(data)
            }
        })

        state = arguments?.getInt(ARG_SECTION_NUMBER)
        factory = ViewModelFactory.getInstance(requireActivity())
        pageViewModel = ViewModelProvider(this, factory)[PageViewModel::class.java]
        if (state == 2){
            pageViewModel.getShows().observe(viewLifecycleOwner, Observer { shows ->
                if (shows != null) setAdapter(shows)
            })
        }
        else{
            pageViewModel.getMovies().observe(viewLifecycleOwner, Observer { movies ->
                if (movies != null) setAdapter(movies)
            })
        }
    }

    private fun setAdapter(list: List<Film>){
        mainBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter.setData(list)
        adapter.notifyDataSetChanged()
    }

    private fun moveToDetailActivity(selectedData: Film){
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
        intent.putExtra(DetailActivity.EXTRA_STATE, state)
        startActivity(intent)
    }
}