package com.es.news.utility

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.es.news.R

class Utils {
    fun setRVDivider(rv: RecyclerView, marginStartDp:Int, marginEndDp:Int) {
        val itemDec =
            DividerItemDecoration(rv.context, R.drawable.divider, dpToPx(marginStartDp), dpToPx(marginEndDp))
        rv.addItemDecoration(itemDec)
    }

    fun dpToPx(dp:Int):Int{
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

}