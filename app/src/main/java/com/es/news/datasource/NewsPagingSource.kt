package com.es.news.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.es.news.model.Article
import com.es.news.network.ApiService
import com.es.news.utility.Constants
import java.lang.Exception
import javax.inject.Inject

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/15/2021          *
 * * * * * * * * * * * * * * * * *

 */

class NewsPagingSource @Inject constructor(private val apiService: ApiService, private val sourceId:String):
    PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPage = params.key ?: 1
            val response =
                apiService.getNews(sourceId, Constants.API_KEY, Constants.PAGE_SIZE, nextPage)
            val articles = response.articles
            LoadResult.Page(
                data = articles,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}