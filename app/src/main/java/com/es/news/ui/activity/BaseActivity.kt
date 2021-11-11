package com.es.news.ui.activity

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.es.news.R
import com.es.news.utility.DividerItemDecoration

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/10/2021          *
 * * * * * * * * * * * * * * * * *

 */

open class BaseActivity:AppCompatActivity() {
    lateinit var transaction: FragmentTransaction
    lateinit var horizantalLayoutManager:LinearLayoutManager
    lateinit var verticalLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transaction= supportFragmentManager.beginTransaction()
        horizantalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        verticalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun setRVDivider(rv: RecyclerView, marginStart:Int, marginEnd:Int) {
        val itemDec =
            DividerItemDecoration(this, R.drawable.divider, dpToPx(marginStart), dpToPx(marginEnd))
        rv.addItemDecoration(itemDec)
    }

    fun dpToPx(dp:Int):Int{
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}