package com.es.news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
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
    private val click: (position:Int, isOnList: Boolean) -> Unit
) : PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_item_layout,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it)}
    }

    inner class NewsViewHolder(private val item: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(article: Article) {
            item.article = article
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return false
        }
    }
}