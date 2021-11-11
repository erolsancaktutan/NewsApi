package com.es.news.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.es.news.utility.Utils
import javax.inject.Inject

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/10/2021          *
 * * * * * * * * * * * * * * * * *

 */

open class BaseActivity:AppCompatActivity() {
    @Inject
    lateinit var utils:Utils

    lateinit var horizantalLayoutManager:LinearLayoutManager
    lateinit var verticalLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        horizantalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        verticalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun openFragment(tag:String, fragment:Fragment){
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(tag)
        transaction.add(android.R.id.content, fragment).commit()
    }
}