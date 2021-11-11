package com.es.news.adapter.vh

import androidx.recyclerview.widget.RecyclerView
import com.es.news.databinding.CategoryItemLayoutBinding
import com.es.news.model.Category

class CategoryVH(private var itemViews: CategoryItemLayoutBinding): RecyclerView.ViewHolder(itemViews.root) {
    fun bind(category:Category){
        itemViews.category = category
    }
}