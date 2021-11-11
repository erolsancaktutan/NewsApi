package com.es.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.es.news.R
import com.es.news.databinding.CategoryItemLayoutBinding
import com.es.news.databinding.SourceItemLayoutBinding
import com.es.news.model.Category
import com.es.news.model.Source

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/10/2021          *
 * * * * * * * * * * * * * * * * *

 */

class SourceAdapter(
    private var sourceList: ArrayList<Source>
) : RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SourceViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.source_item_layout,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val source = sourceList[position]
        holder.bind(source)
    }

    override fun getItemCount(): Int = sourceList.size

    inner class SourceViewHolder(val item: SourceItemLayoutBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(source: Source) {
            item.source = source
        }
    }
}