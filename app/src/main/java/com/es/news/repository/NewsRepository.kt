package com.es.news.repository

import com.es.news.model.ResponseNews
import com.es.news.model.ResponseSources
import com.es.news.network.ApiService
import com.es.news.utility.Constants
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService) {
    fun getNewsSources(lang: String): Observable<ResponseSources> {
        return apiService.getSources(lang)
    }

    fun getNews(sourceID: String, pageSize:Int, page:Int): Observable<ResponseNews> {
        return apiService.getNews(sourceID,Constants.API_KEY,pageSize, page)
    }

}