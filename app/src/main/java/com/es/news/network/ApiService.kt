package com.es.news.network

import com.es.news.model.ResponseSources
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/sources")
    fun getSources(
        @Query("language") language: String
    ): Observable<ResponseSources>

}