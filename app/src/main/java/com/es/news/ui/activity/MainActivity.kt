package com.es.news.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
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



        newsViewModel.getNewsSourceList("en")

    }

}