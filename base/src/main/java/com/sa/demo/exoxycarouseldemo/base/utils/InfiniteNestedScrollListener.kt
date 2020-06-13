package com.sa.demo.exoxycarouseldemo.base.utils

import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class InfiniteNestedScrollListener(val recyclerView: RecyclerView) : NestedScrollView.OnScrollChangeListener {
    override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (v?.getChildAt(v.childCount - 1) != null) {

            if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight && scrollY > oldScrollY) {
                val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
                val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
                val pastVisibleItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                    fetchNext()
                }
            }
        }

    }

    abstract fun fetchNext()
}