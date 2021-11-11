package com.es.news.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.es.news.adapter.CategoryAdapter
import com.es.news.databinding.ActivityMainBinding
import com.es.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val newsViewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.categoryRV.layoutManager = horizantalLayoutManager
        binding.sourcesRV.layoutManager = verticalLayoutManager
        createCategoryListAdapter()
        observeCategoryList()
        newsViewModel.getNewsSourceList("en")

    }

    private fun observeCategoryList() {
       newsViewModel.newsCategories().observe(this, {
           binding.categoryRV.adapter!!.notifyDataSetChanged()
           var b = ""
       })
    }

    private fun createCategoryListAdapter() {
        binding.categoryRV.adapter = CategoryAdapter(newsViewModel.newsCategories().value!!,
            click={
        })

    }

}