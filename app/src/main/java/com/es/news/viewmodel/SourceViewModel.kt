package com.es.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.es.news.model.Category
import com.es.news.model.Source
import com.es.news.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val sourceArr = ArrayList<Source>()
    private val categoryArr = ArrayList<Category>()

    val errorMessage = MutableLiveData<String>()
    val sourceList = MutableLiveData(sourceArr)
    val categoryList = MutableLiveData(categoryArr)
    var job: Job? = null
    private val loadingSources = MutableLiveData(false)

    fun getSourceList(
        lang: String
    ) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = newsRepository.getNewsSources(lang)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    sourceArr.addAll(response.body()!!.sources)
                    sourceList.value = sourceArr
                    updateCategoryList()
                    loadingSources.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loadingSources.value = false
    }

    private fun updateCategoryList() {
        val catArr = categoryList.value
        for (i in 0 until sourceList.value!!.size) {
            if (catArr?.find { it.category == sourceList.value!![i].category } == null) {
                catArr?.add(Category(sourceList.value!![i].category, false))
            }
        }
        categoryList.value = catArr!!
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}