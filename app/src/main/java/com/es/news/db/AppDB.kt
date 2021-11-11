package com.es.news.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.es.news.db.model.ArticleData

@Database(entities = [ArticleData::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun articleDao(): ArticleDao


}