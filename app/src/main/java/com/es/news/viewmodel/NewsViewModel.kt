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
    private val currentSources = ArrayList<Source>()
    private val allSources = ArrayList<Source>()
    private val sourceList = MutableLiveData(currentSources)
    private val categoryList = MutableLiveData(categories)
    private val selectedCategories = ArrayList<String>()
    private val filteredSources = ArrayList<Source>()

    fun newsSourceList() = sourceList
    fun newsCategories() = categoryList

    fun getNewsSourceList(
        lang: String
    ) {
        newsRepository.getNewsSources(lang)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                allSources.addAll(result.sources)
                createCategoryList(allSources)
                setResult(allSources)
            }, {})
    }

    private fun setResult(result:ArrayList<Source>){
        currentSources.clear()
        currentSources.addAll(result)
        sourceList.value = currentSources
    }

    fun prepareFilterCategoryList(isSelected: Boolean, categoryName: String){
        if (isSelected)
            selectedCategories.add(categoryName)
        else
            selectedCategories.remove(categoryName)
    }

    fun filterResources() {
        filteredSources.clear()
        if (selectedCategories.size>0) {
            for (i in 0 until selectedCategories.size){
               filteredSources.addAll(allSources.filter { it.category == selectedCategories[i] })
            }
            setResult(filteredSources)
        } else{
            setResult(allSources)
        }
    }

    private fun createCategoryList(sources: ArrayList<Source>) {
        for (i in 0 until sources.size) {
            if (categories.find { it.category == sources[i].category } == null) {
                categories.add(Category(sources[i].category, false))
            }
        }
        categoryList.value = categories
    }
}