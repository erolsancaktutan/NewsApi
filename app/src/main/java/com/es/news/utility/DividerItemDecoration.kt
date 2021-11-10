package com.es.news.utility

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(
    context: Context,
    drawable: Int,
    private val marginStart: Int,
    private val marginEnd: Int
) : RecyclerView.ItemDecoration() {
    private val divider = ContextCompat.getDrawable(context, drawable)
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        val left = parent.paddingLeft+marginStart
        val right = parent.width - parent.paddingRight-marginEnd
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}