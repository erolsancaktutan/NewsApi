package com.es.news.repository

import com.es.news.model.ResponseSources
import com.es.news.network.ApiService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService) {
    fun getTrendMovies(lang: String): Observable<ResponseSources> {
        return apiService.getSources(lang)
    }
}