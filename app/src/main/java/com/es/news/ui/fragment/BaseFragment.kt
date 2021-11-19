package com.es.news.ui.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.es.news.db.ArticleDao
import com.es.news.utility.Utils
import javax.inject.Inject

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/11/2021          *
 * * * * * * * * * * * * * * * * *

 */

open class BaseFragment : Fragment() {
    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var articleDao: ArticleDao

    fun getHLayoutManager(context: Context) =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}