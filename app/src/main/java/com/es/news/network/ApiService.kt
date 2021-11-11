package com.es.news.network

import com.es.news.model.ResponseNews
import com.es.news.model.ResponseSources
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/sources")
    fun getSources(
        @Query("language") language: String
    ): Observable<ResponseSources>

    @GET("v2/top-headlines")
    fun getNews(
        @Query("sources") sourceID: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Observable<ResponseNews>

}