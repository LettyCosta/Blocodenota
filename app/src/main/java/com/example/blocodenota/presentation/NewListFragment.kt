package com.example.blocodenota.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.blocodenota.R
import com.example.blocodenota.data.local.News
import com.example.blocodenota.data.remote.RetrofitModule


/**
 * A simple [Fragment] subclass.
 * Use the [NewListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewListFragment : Fragment() {
    private val adapter = NewsListAdapter()

    private val viewModel by lazy {
        NewsListViewModel.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvNewsList = view.findViewById<RecyclerView>(R.id.rv_News)
        rvNewsList.adapter = adapter
        //Fake List

        viewModel.newsListLiveData.observe(viewLifecycleOwner) {newsListDTO ->
            val newsList = newsListDTO.map { newsDTO ->
                News(
                    Title = newsDTO.title,
                    ImgURL = newsDTO.imageURL
                )
            }
            adapter.submitList(newsList)

        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment NewsListFragment.
         */

        @JvmStatic
        fun newInstance() = NewListFragment()
    }
    }
