package com.es.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.es.news.R
import com.es.news.adapter.vh.CategoryVH
import com.es.news.model.Category

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/10/2021          *
 * * * * * * * * * * * * * * * * *

 */

class CategoryAdapter (
    private var categoryList: ArrayList<Category>,
    private val click: (category: String) -> Unit
) : RecyclerView.Adapter<CategoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryVH(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.category_item_layout,
            parent,
            false))

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val character = categoryList[position]
        holder.bind(character)
        holder.itemView.setOnClickListener {
            click(categoryList[position].category)
        }
    }

    override fun getItemCount(): Int = categoryList.size
}