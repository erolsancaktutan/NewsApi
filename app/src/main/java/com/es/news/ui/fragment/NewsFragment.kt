package com.es.news.ui.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.es.news.abstract.PaginationListener
import com.es.news.databinding.FragmentNewsBinding
import com.es.news.ui.adapter.NewsAdapter
import com.es.news.utility.Constants
import com.es.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_SOURCE_ID = "sourceId"

@AndroidEntryPoint
class NewsFragment : BaseFragment() {
    private var binding: FragmentNewsBinding? = null
    private val newsViewModel: NewsViewModel by viewModels()
    private var sourceId: String? = null
    private var pageCount = 0
    private var isPaginationFinished = false
    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sourceId = it.getString(ARG_SOURCE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.setRVDivider(binding!!.newsRV, 16, 16)
        createNewsAdapter()
        observeNewsList()
        setScrollListener()
        getNews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNews() {
        pageCount++
        newsViewModel.getNews(sourceId!!, Constants.PAGE_SIZE, pageCount, paginationIsFinished = {
            isPaginationFinished = it
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeNewsList() {
        newsViewModel.news().observe(requireActivity(), {
            binding!!.newsRV.adapter!!.notifyDataSetChanged()
            binding!!.loadingTV.visibility = View.GONE
            isLoading = false
            isLastPage = false
        })
    }

    private fun createNewsAdapter() {
        binding!!.newsRV.adapter = NewsAdapter(newsViewModel.news().value!!, click = {position, isOnList->
            newsViewModel.changeReadListStatus(position, isOnList)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setScrollListener() {
        binding!!.newsRV.addOnScrollListener(object :
            PaginationListener(binding!!.newsRV.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                isLastPage = true
                if (!isPaginationFinished) {
                    getNews()
                    binding!!.loadingTV.visibility = View.VISIBLE
                }
            }
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(sourceId: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SOURCE_ID, sourceId)
                }
            }
    }

}