package com.es.news.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import androidx.paging.rxjava2.observable
import com.es.news.datasource.NewsPagingSource
import com.es.news.model.Article
import com.es.news.model.ResponseNews
import com.es.news.model.ResponseSources
import com.es.news.network.ApiService
import com.es.news.utility.Constants
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getNewsSources(lang: String): Response<ResponseSources> {
        return apiService.getSources(lang)
    }

 /*   suspend fun getNews(sourceID: String, pageSize:Int, page:Int): Response<ResponseNews> {
        return apiService.getNews(sourceID,Constants.API_KEY,pageSize, page)
    }*/

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