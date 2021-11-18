package com.es.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.es.news.model.Article
import com.es.news.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    //private val articleDao: ArticleDao
) : ViewModel() {

    fun getArticles(
        sourceID: String
    ): LiveData<PagingData<Article>> {
        val response = newsRepository.getNews(sourceID).cachedIn(viewModelScope)
        return response
    }

/*    fun changeReadListStatus(position: Int, isOnList: Boolean) {
        if (!isOnList) {
            articleDao.saveArticle(ArticleData(articleList.value!!..url))
        } else {
            articleDao.deleteArticle(articleList.value!![position].url)
        }
    }

    private fun checkIsOnList(articles: ArrayList<Article>): ArrayList<Article> {
        articles.forEach {
            it.isOnList = articleDao.getCount(it.url) > 0
        }
        return articles
    }

    private fun listByDate(articles: ArrayList<Article>): ArrayList<Article> {
        articles.forEach {
            val dateCalStr = prepareDate(it.publishedAt)
            it.calendar = dateCalStr.first
            it.publishedAt = dateCalStr.second
        }
        articles.sortByDescending {
            it.calendar!!.timeInMillis
        }
        return articles
    }

    private fun prepareDate(publishedAt: String): Pair<Calendar, String> {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
        val cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Istanbul"))
        val date = simpleDateFormat.parse(publishedAt)
        cal.time = date
        val dateStr =
            "${cal.get(Calendar.DAY_OF_MONTH)}.${cal.get(Calendar.MONTH)}.${cal.get(Calendar.YEAR)} - ${
                cal.get(Calendar.HOUR)
            }:${cal.get(Calendar.MINUTE)}:${cal.get(Calendar.SECOND)}"
        return Pair(cal, dateStr)
    }*/
}