package com.es.news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.es.news.R
import com.es.news.databinding.NewsItemLayoutBinding
import com.es.news.model.Article

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/10/2021          *
 * * * * * * * * * * * * * * * * *

 */

class NewsAdapter(
    private var newsList: ArrayList<Article>,
    private val click: (position:Int, isOnList: Boolean) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_item_layout,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val source = newsList[position]
        holder.item.addRemoveTV.setOnClickListener{
           click(position, source.isOnList)
        }
        holder.bind(source)
    }

    override fun getItemCount(): Int = newsList.size

    inner class NewsViewHolder(val item: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(article: Article) {
            item.article = article
        }
    }
}