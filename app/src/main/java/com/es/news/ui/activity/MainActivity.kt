package com.es.news.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.es.news.ui.adapter.CategoryAdapter
import com.es.news.ui.adapter.SourceAdapter
import com.es.news.databinding.ActivityMainBinding
import com.es.news.ui.fragment.NewsFragment
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
        utils.setRVDivider(binding.sourcesRV,16, 16)
        createCategoryListAdapter()
        createSourceListAdapter()

        observeCategoryList()
        observeSourceList()

        newsViewModel.getNewsSourceList("en")
    }

    private fun createCategoryListAdapter() {
        binding.categoryRV.adapter = CategoryAdapter(newsViewModel.newsCategories().value!!,
            click={isChecked, categoryName->
                newsViewModel.prepareFilterCategoryList(isChecked, categoryName)
                newsViewModel.filterResources()
            })
    }

    private fun createSourceListAdapter(){
        binding.sourcesRV.adapter = SourceAdapter(newsViewModel.newsSourceList().value!!,
        click={sourceID->
            openFragment("news", NewsFragment.newInstance(sourceID))
        })
    }

    private fun observeCategoryList() {
       newsViewModel.newsCategories().observe(this, {
           binding.categoryRV.adapter!!.notifyDataSetChanged()
       })
    }

    private fun observeSourceList(){
        newsViewModel.newsSourceList().observe(this, Observer {
            binding.sourcesRV.adapter!!.notifyDataSetChanged()
        })
    }


}