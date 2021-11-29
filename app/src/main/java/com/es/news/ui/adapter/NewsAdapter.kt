package com.es.news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.es.news.R
import com.es.news.databinding.NewsItemLayoutBinding
import com.es.news.db.ArticleDao
import com.es.news.db.model.ArticleData
import com.es.news.model.Article

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/10/2021          *
 * * * * * * * * * * * * * * * * *

 */

class NewsAdapter(private val articleDao: ArticleDao) : PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_item_layout,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.item.article = getItem(position).apply {
            this!!.isOnList = articleDao.isThereAnyArticle(this.url)
        }
        holder.item.addRemoveTV.setOnClickListener{
            addRemoveForReadList(position)
        }
    }

    private fun addRemoveForReadList(position: Int){
        if (articleDao.isThereAnyArticle(getItem(position)!!.url)) {
            articleDao.deleteArticle(getItem(position)!!.url)
        } else {
            articleDao.saveArticle(ArticleData(getItem(position)!!.url))
        }
        getItem(position)!!.isOnList = !getItem(position)!!.isOnList
        notifyItemChanged(position)
    }

    inner class NewsViewHolder(val item: NewsItemLayoutBinding) : RecyclerView.ViewHolder(item.root)

    object DiffUtilCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return false
        }
    }
}