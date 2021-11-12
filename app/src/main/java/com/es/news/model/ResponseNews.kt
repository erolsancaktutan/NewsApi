package com.es.news.model

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/11/2021          *
 * * * * * * * * * * * * * * * * *

 */

data class ResponseNews(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Article>
)

data class Article(
    val source: NewsSource,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    var publishedAt: String,
    val content: String,
    var isOnList:Boolean = false
)

data class NewsSource(
    val id: String,
    val name: String
)
