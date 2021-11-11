package com.es.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.es.news.model.Category
import com.es.news.model.Source
import com.es.news.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val categories = ArrayList<Category>()
    private val sourceList = MutableLiveData(ArrayList<Source>())
    private val categoryList = MutableLiveData(categories)

    fun newsSourceList() = sourceList
    fun newsCategories() = categoryList

    fun getNewsSourceList(
        lang: String
    ) {
        newsRepository.getNewsSources(lang)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->

                categories.add(Category("All news", true))
                for (i in 0 until result.sources.size) {
                    if (categories.find { it.category == result.sources[i].category } == null) {
                        categories.add(Category(result.sources[i].category, false))
                    }
                }
                categoryList.value = categories
                sourceList.value = result.sources
            }, {})
    }

}