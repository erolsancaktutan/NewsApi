package com.es.news.network

import com.es.news.model.ResponseSources
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("sources")
    fun getSources(
        @Query("language") language: String
    ): Observable<ResponseSources>

  /*  @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path(value = "movie_id") id: Int
    ): Observable<MovieDetail>

    @GET("search/movie")
    fun searchMovie(
        @Query("page") page: Int,
        @Query("query") query: String
    ): Observable<TrendMovies>*/

}