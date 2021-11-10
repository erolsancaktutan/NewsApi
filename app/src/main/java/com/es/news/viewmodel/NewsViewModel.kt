package com.es.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.es.news.model.Source
import com.es.news.repository.NewsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository)  : ViewModel(){
    private val sourceList = MutableLiveData(ArrayList<Source>())

    fun newsSourceList() = sourceList

    fun getNewsSourceList(
        lang: String
    ) {
        newsRepository.getNewsSources(lang)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                newsSourceList().value = result.sources
            }, {})
    }

}