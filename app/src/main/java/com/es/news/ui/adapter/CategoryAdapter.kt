package com.es.news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.es.news.R
import com.es.news.databinding.CategoryItemLayoutBinding
import com.es.news.model.Category

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/10/2021          *
 * * * * * * * * * * * * * * * * *

 */

class CategoryAdapter(
    private var categoryList: ArrayList<Category>,
    private val click: (isChecked: Boolean, category: String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.category_item_layout,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
        holder.item.categoryCB.setOnClickListener {
            click(holder.item.categoryCB.isChecked, categoryList[position].category)
        }
    }

    override fun getItemCount(): Int = categoryList.size

    inner class CategoryViewHolder(val item: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(category: Category) {
            item.category = category
        }
    }
}