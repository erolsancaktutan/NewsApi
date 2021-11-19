package com.es.news.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.es.news.datasource.NewsPagingSource
import com.es.news.model.Article
import com.es.news.model.ResponseSources
import com.es.news.network.ApiService
import com.es.news.utility.Constants
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getNewsSources(lang: String): Response<ResponseSources> {
        return apiService.getSources(lang)
    }

    fun getNews(sourceID: String): LiveData<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(apiService, sourceID)
            }
        ).liveData
    }
}