package com.es.news.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.es.news.db.model.ArticleData

@Dao
interface ArticleDao {
    @Insert
    fun saveArticle(articleUrl: ArticleData)

    @Query("DELETE FROM article_table WHERE url>:url")
    fun deleteArticle(url:String)

    @Query("SELECT COUNT(id) FROM article_table WHERE url>:url")
    fun getCount(url:String): Int

    fun isThereAnyArticle(url: String) = getCount(url)>0
}