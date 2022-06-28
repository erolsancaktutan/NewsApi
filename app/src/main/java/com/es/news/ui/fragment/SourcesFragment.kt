package com.es.news.ui.fragment

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
class SourcesFragment : BaseFragment() {
    private lateinit var binding: FragmentSourcesBinding
    private val sourceViewModel: SourceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initAdapters() {
        with(binding) {
            categoryRV.layoutManager = getHLayoutManager(requireContext())
            categoryRV.adapter = CategoryAdapter(
                sourceViewModel.categoryList.value!!,
                click = { isChecked, category ->

                })
            sourcesRV.adapter = SourceAdapter(sourceViewModel.sourceList.value!!, click = {
                val bundle = Bundle()
                bundle.putString("sourceId", it)
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_sourcesFragment_to_newsFragment, bundle)
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
        observeCategoryList()
        observeSourceList()
        sourceViewModel.getSourceList("en")
    }

    private fun observeCategoryList() {
        sourceViewModel.categoryList.observe(viewLifecycleOwner) {
            binding.categoryRV.adapter?.notifyDataSetChanged()
        }
    }

    private fun observeSourceList() {
        sourceViewModel.sourceList.observe(viewLifecycleOwner) {
            binding.sourcesRV.adapter?.notifyDataSetChanged()
        }
    }
}