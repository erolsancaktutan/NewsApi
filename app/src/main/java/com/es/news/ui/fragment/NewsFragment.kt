package com.es.news.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.es.news.databinding.FragmentNewsBinding
import com.es.news.ui.adapter.NewsAdapter
import com.es.news.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val ARG_SOURCE_ID = "sourceId"

@AndroidEntryPoint
class NewsFragment : BaseFragment() {
    private var binding: FragmentNewsBinding? = null
    private val newsViewModel: ArticleViewModel by viewModels()
    private var sourceId: String? = null
    private val newsAdapter by lazy {
        NewsAdapter(click = {position, isOnList->

        })
    }

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
        utils.setRVDivider(binding!!.newsRV, 0, 0)
        createNewsAdapter()
        observeNewsList()
    }

    private fun observeNewsList() {
        viewLifecycleOwner.lifecycleScope.launch {
            newsViewModel.getArticles(sourceId!!).observe(viewLifecycleOwner, {
               newsAdapter.submitData(lifecycle, it)
            })
        }
    }

    private fun createNewsAdapter() {
        binding!!.newsRV.adapter = newsAdapter
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