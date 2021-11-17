package com.es.news.network

import androidx.lifecycle.LiveData
import com.es.news.model.ResponseNews
import com.es.news.model.ResponseSources
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/sources")
    suspend fun getSources(
        @Query("language") language: String
    ): Response<ResponseSources>

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("sources") sourceID: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): ResponseNews

}