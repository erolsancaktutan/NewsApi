package com.es.news.utility

object Constants {
    init {
        System.loadLibrary("native-lib")
    }
    private external fun getBaseURL(): String
    private external fun getApiKey(): String
    val BASE_URL = getBaseURL()
    val API_KEY = getApiKey()
}