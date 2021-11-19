package com.es.news.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import com.es.news.databinding.ActivityMainBinding
import com.es.news.ui.adapter.CategoryAdapter
import com.es.news.ui.adapter.SourceAdapter
import com.es.news.ui.fragment.NewsFragment
import com.es.news.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sourceViewModel: SourceViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.categoryRV.layoutManager = horizantalLayoutManager
        utils.setRVDivider(binding.sourcesRV, 16, 16)
        createCategoryListAdapter()
        createSourceListAdapter()
        observeCategoryList()
        observeSourceList()
        sourceViewModel.getSourceList("en")
    }

    private fun createCategoryListAdapter() {

        binding.categoryRV.adapter = CategoryAdapter(sourceViewModel.categoryList.value!!,
            click = { isChecked, categoryName ->
                /*  sourceViewModel.prepareFilterCategoryList(isChecked, categoryName)
                  sourceViewModel.filterResources()*/
            })

    }

    private fun createSourceListAdapter() {
        binding.sourcesRV.adapter = SourceAdapter(sourceViewModel.sourceList.value!!,
            click = { sourceID ->
                openFragment("news", NewsFragment.newInstance(sourceID))
            })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeCategoryList() {
        sourceViewModel.categoryList.observe(this, {
            binding.categoryRV.adapter!!.notifyDataSetChanged()
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeSourceList() {
        sourceViewModel.sourceList.observe(this, {
            binding.sourcesRV.adapter!!.notifyDataSetChanged()
        })
    }

/*private fun bs(){

    viewLifecycleOwner.lifecycleScope.launch {
        sourceViewModel.getSourceList("en").observe(viewLifecycleOwner, {
            newsAdapter.submitData(lifecycle, it)
        })
    }
}*/
}