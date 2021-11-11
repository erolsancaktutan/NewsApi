package com.es.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.es.news.model.Article
import com.es.news.model.Category
import com.es.news.model.Source
import com.es.news.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val categoryArr = ArrayList<Category>()
    private val categoryList = MutableLiveData(categoryArr)
    private val selectedCategories = ArrayList<String>()

    private val currentSourcesArr = ArrayList<Source>()
    private val allSources = ArrayList<Source>()
    private val sourceList = MutableLiveData(currentSourcesArr)
    private val filteredSources = ArrayList<Source>()

    private val newsArr = ArrayList<Article>()
    private val newsList = MutableLiveData(newsArr)

    fun newsSourceList() = sourceList
    fun newsCategories() = categoryList
    fun news() = newsList

    fun getNewsSourceList(
        lang: String
    ) {
        newsRepository.getNewsSources(lang)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                allSources.addAll(result.sources)
                createCategoryList(allSources)
                setSourceResult(allSources)
            }, {})
    }

    fun getNews(
        sourceID: String, pageSize: Int, page: Int,
        paginationIsFinished: (isPaginationDone: Boolean) -> Unit
    ) {
        newsRepository.getNews(sourceID, pageSize, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                paginationIsFinished(newsArr.size >= result.totalResults)
                newsArr.addAll(result.articles)
                newsList.value = newsArr
            }, {})
    }

    private fun setSourceResult(result: ArrayList<Source>) {
        currentSourcesArr.clear()
        currentSourcesArr.addAll(result)
        sourceList.value = currentSourcesArr
    }

    fun prepareFilterCategoryList(isSelected: Boolean, categoryName: String) {
        if (isSelected)
            selectedCategories.add(categoryName)
        else
            selectedCategories.remove(categoryName)
    }

    fun filterResources() {
        filteredSources.clear()
        if (selectedCategories.size > 0) {
            for (i in 0 until selectedCategories.size) {
                filteredSources.addAll(allSources.filter { it.category == selectedCategories[i] })
            }
            setSourceResult(filteredSources)
        } else {
            setSourceResult(allSources)
        }
    }

    private fun createCategoryList(sources: ArrayList<Source>) {
        for (i in 0 until sources.size) {
            if (categoryArr.find { it.category == sources[i].category } == null) {
                categoryArr.add(Category(sources[i].category, false))
            }
        }
        categoryList.value = categoryArr
    }
}