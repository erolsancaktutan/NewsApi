package com.es.news.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/11/2021          *
 * * * * * * * * * * * * * * * * *

 */
@Entity(tableName = "article_table")
data class ArticleData(
    @NotNull
    @ColumnInfo(name = "url")
    var url: String,
    /*,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int*/

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

)