package com.es.news.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.es.news.databinding.FragmentNewsBinding
import com.es.news.ui.adapter.NewsAdapter
import com.es.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_SOURCE_ID = "sourceId"

@AndroidEntryPoint
class NewsFragment : BaseFragment() {
    private var binding: FragmentNewsBinding? = null
    private val newsViewModel: NewsViewModel by viewModels()
    private var sourceId: String? = null

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



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.setRVDivider(binding!!.newsRV,16,16)
        createNewsAdapter()
        observeNewsList()
        newsViewModel.getNews(sourceId!!,20,1)


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeNewsList(){
        newsViewModel.news().observe(requireActivity(), {
            binding!!.newsRV.adapter!!.notifyDataSetChanged()
        })
    }

    private fun createNewsAdapter(){
        binding!!.newsRV.adapter = NewsAdapter(newsViewModel.news().value!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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