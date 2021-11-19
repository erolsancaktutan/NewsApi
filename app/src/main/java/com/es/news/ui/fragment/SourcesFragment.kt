package com.es.news.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.es.news.R
import com.es.news.databinding.FragmentSourcesBinding
import com.es.news.ui.adapter.CategoryAdapter
import com.es.news.ui.adapter.SourceAdapter
import com.es.news.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourcesFragment : BaseFragment(){
    private var binding: FragmentSourcesBinding? = null
    private val sourceViewModel: SourceViewModel by viewModels()
    private val sourcesAdapter by lazy {
        SourceAdapter(sourceViewModel.sourceList.value!!, click = {
            val bundle = Bundle()
            bundle.putString("sourceId", it)
            Navigation.findNavController(binding!!.root)
                .navigate(R.id.action_sourcesFragment_to_newsFragment,bundle)
        })
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(sourceViewModel.categoryList.value!!, click = {isChecked, category ->

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSourcesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.categoryRV.layoutManager = getHLayoutManager(requireContext())
        binding!!.categoryRV.adapter = categoryAdapter
        binding!!.sourcesRV.adapter = sourcesAdapter
        observeCategoryList()
        observeSourceList()
        sourceViewModel.getSourceList("en")
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeCategoryList() {
        sourceViewModel.categoryList.observe(this, {
            categoryAdapter.notifyDataSetChanged()
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeSourceList() {
        sourceViewModel.sourceList.observe(this, {
            sourcesAdapter.notifyDataSetChanged()
        })
    }
}