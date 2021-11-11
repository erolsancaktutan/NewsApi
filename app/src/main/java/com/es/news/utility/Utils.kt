package com.es.news.utility

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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